package com.top1.marketinfo.service.impl;

import com.top1.marketinfo.service.WeiXinService;
import com.top1.marketinfo.utils.SSLClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
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
            r = UUID.randomUUID().toString();
            cach.put(r,jObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public String decrypt(String sessionKey,String encData,String iv) throws Exception{
        String session = cach.get(sessionKey).getString("session_key");

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
        String key = s.getSession("001uvfvw0hnWmi1e5tyw0owivw0uvfvm");
        System.out.println(s.cach.get(key));

//        String encData = "mIxOXOE3dzUkfmzepsNaIy64iR9TyBs77WOkP7MK6YhTynlsPWI49Ttk7r058E9eIMaoCsiGSszaObKkSXUmfo1+gnPHuuuYSFSiZl6fqzhx9459G35M+XyG2kEPcvWcTUwpX1qKPYs/vg80nOr01i6gAcT6cNPMDpjcXd1Xx1BnVG0ehxsoR2lWt4X8ObXEyERUwpiQS6UUV+B9xvMzpKZeE+gYW5SxoGmWIQWhtTowoDVrsjQBE6uy1MFY68ykdMb6vtlqq9rufeE1XGNk5P63r6UHhAfTLER+Ic6jI0+Wpe4qlOLUwtrhCbZfkgdzsrD26Z0PYBMhhY7BSzhkR0/2oWvbSdP+kixDDgMecAEEgBQObMegSacu0X41ZE6blhpLzZ8DifV/AiZljK/UqyD5DsMMoJo3AwFgQZ+IqfFe/B1IPURwcDoA8b1RU7BltNpsFGI46NKDGe4xPB+y1g==";
//        String iv = "g7Sycl5/+BRJ6U7F04WFKg==";
//        try {
//            String v = s.decrypt(key, encData, iv);
//            System.out.println("### "+v);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
