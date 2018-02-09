package com.top1.marketinfo.service.impl;

import com.top1.marketinfo.WXException;
import com.top1.marketinfo.service.WeiXinService;
import com.top1.marketinfo.utils.SSLClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
* Author GQ
* Date:2018/1/24
* Time:上午10:01
*/
@Slf4j
@Service
public class WeiXinServiceImpl implements WeiXinService {

    public Map<String, JSONObject> cach = new HashMap<>();

    @Override
    public String getSession(String jscode) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+WEIXIN_APP_ID+
                "&secret="+WEIXIN_APP_SECRET+"&js_code="+jscode+"&grant_type=authorization_code";

        String v = doHttpsGet(url,"utf-8");
        String r = "-1";
        try {
            JSONObject jObject = new JSONObject(v);
            if(!jObject.has("session_key")){
                throw new WXException("1001","获取微信session失败！");
            }
            r = UUID.randomUUID().toString();
            cach.put(r,jObject);
            log.info("[wx] put session in cach, ("+r+","+v+")");
        } catch (JSONException e) {
            log.error("json error:",e);
            throw new WXException("0001",e.getMessage());
        }
        return r;
    }

    @Override
    public String decrypt(String sessionKey,String encData,String iv) throws Exception{
        JSONObject json = cach.get(sessionKey);
        if(json == null){
            throw new WXException("1002","从缓存获取session失败！");
        }
        String session = "";
        if(json.has("session_key")) {
            session = json.getString("session_key");
        }else{
            cach.remove(sessionKey);
            throw new WXException("1003","session值获取失败！");
        }

        byte[] encrypData = Base64.decodeBase64(encData);
        byte[] ivData = Base64.decodeBase64(iv);
        byte[] key = Base64.decodeBase64(session);

        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        cach.remove(sessionKey);
        return new String(cipher.doFinal(encrypData));
    }

    @Override
    public String handlePhone(String info) {
        return null;
    }


    private String doHttpsGet(String url,String charset){
        HttpClient httpClient = null;
        HttpGet httpGet = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        //openId:oRtgV0Sx13cNvscdc0oHeRPDHCuA

        WeiXinServiceImpl s = new WeiXinServiceImpl();
        String key = s.getSession("0714rP1Y1SQzET0qyw1Y1FcI1Y14rP1c");
        System.out.println(s.cach.get(key));

        String encData = "mshk65+k7tUMD3opkX/dJeNNbv9fTBbCz8+Z6A+EmtBIPMRmHaIGst+JjZ7YYfyydn9368o8ginwZucZuQOsAe0uNdnXZshD0CuxsMrY8ggaRlR12iy5rq5CxwHBfb5j1XLdkvG0TVzJDaZjN8bTjxk+rBQ4XGzGhzLEdJ2TsJrjdPqqX/QUCwN+RJWAhBGOvqycCFt6VNIDpHFFxAZ8Ejg1dnJiRMs/tAoqj07qB5gYOZAGehfs8DfYO0hgA0nZqPZk3yDt+XXAsYK4IEE9NJT3vnyuTZKy7c59msFnWH8P3LbSsqxvNiIObV43tnN6aN4QL6LrCYjX22BNg9Cv8NdGfLFkOV+TWz0oojK5qIZ5HLBHF3xodELYXAwaJrLBUQisMs2VqA+sGnYSZfAg7ZvC9i0BFPpf1oOJT7/xbtMQ5I7EibDOisOea1bEJQjt7LCIsgeC7eswOZiwRNGr/g==";
        String iv = "keICWhQLUEP882P9pPfFQQ==";
        try {
            String v = s.decrypt(key, encData, iv);
            System.out.println("### "+v);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
