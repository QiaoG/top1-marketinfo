package com.top1.marketinfo.service.impl;

import com.top1.marketinfo.entity.Demand;
import com.top1.marketinfo.entity.News;
import com.top1.marketinfo.entity.Role;
import com.top1.marketinfo.entity.User;
import com.top1.marketinfo.exception.WXException;
import com.top1.marketinfo.service.UserService;
import com.top1.marketinfo.service.WeiXinService;
import com.top1.marketinfo.utils.SSLClient;
import com.top1.marketinfo.vo.AccessToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.text.SimpleDateFormat;
import java.util.*;

/*
* Author GQ
* Date:2018/1/24
* Time:上午10:01
*/
@Slf4j
@Service
public class WeiXinServiceImpl implements WeiXinService {

    public Map<String, JSONObject> cach = new HashMap<>();

    //用于发送模版消息
    private JSONObject jsonAccessToken;

    @Autowired
    private UserService service;

    private AccessToken token;

    private final String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WEIXIN_APP_ID+"&secret="+WEIXIN_APP_SECRET;

    private final String messageUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";

    private SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public JSONObject getSessionInCach(String key) {
        JSONObject json = cach.get(key);
        cach.remove(key);
        return json;
    }

    @Override
    public String getSessionKey(String jscode) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+WEIXIN_APP_ID+
                "&secret="+WEIXIN_APP_SECRET+"&js_code="+jscode+"&grant_type=authorization_code";
        String v = doHttpsGet2(url,"utf-8");
        String r = "-1";
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(v);
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
    public JSONObject decrypt(String sessionKey,String encData,String iv) throws Exception{
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
        JSONObject value = new JSONObject(new String(cipher.doFinal(encrypData)));
        value.put("openid",json.getString("openid"));
        return value;
    }

    @Override
    public User handlePhone(JSONObject json) {
        String phoneNumber = json.getString("phoneNumber");
        int c = service.count();
        User user = new User();
        user.setCreateDate(new Date());
        user.setWxCode(json.getString("openid"));
        user.setMobile(phoneNumber);
        user.setNickname(json.getString("nickname"));
        user.setAvatarUrl(json.getString("avatarUrl"));
        if(c == 0){
            user.setRole(Role.MANAGER);
        }
        User ru = service.saveUser(user);
        return ru;
    }

    @Override
    public void sendVerifyMessage(User user, News news, Demand demand,boolean pass) {
        JSONObject json = new JSONObject();
        json.put("touser",user.getWxCode());
        json.put("template_id",WEIXIN_TEMPLATE_ID);
        if(pass) {
            json.put("page", news == null ? "demand?id=" + demand.getId() : "news?id=" + news.getId());
        }
        json.put("form_id",news == null ? demand.getFormId() : news.getFormId());

        JSONObject data = new JSONObject();
        data.put("keyword1",new JSONObject("{\"value\":\""+(news==null?demand.getTitle():news.getTitle())+"\",\"color\": \"#173177\"}"));
        data.put("keyword2",new JSONObject("{\"value\":\""+(pass?"通过":"不通过")+"\",\"color\": \"#173177\"}"));
        data.put("keyword3",new JSONObject("{\"value\":\""+df.format(news==null?demand.getPublishDate():news.getCreateDate())+"\",\"color\": \"#173177\"}"));
        data.put("keyword4",new JSONObject("{\"value\":\""+df.format(new Date())+"\",\"color\": \"#173177\"}"));
        data.put("keyword5",new JSONObject("{\"value\":\""+(news==null?"需求审核":"热点审核")+"\",\"color\": \"#173177\"}"));

        data.put("keyword4",new JSONObject("{\"value\":\"\",\"color\": \"#173177\"}"));
        json.put("data",data);

        String result = this.doHttpsPost(messageUrl+this.getAccessToke(),json,"utf-8");
        log.info(String.format("verify %s(%b), wechar notify author,response: %s",(news!=null?"news:"+news.getTitle():"demand:"+demand.getTitle()),pass,result));
    }

    private String getAccessToke(){
        if(this.token == null || ((System.currentTimeMillis()-token.getTime().getTime())/1000+20) > token.getExpires_in()){
            String v = doHttpsGet2(tokenUrl,"utf-8");
            JSONObject json = new JSONObject(v);
            if(!json.has("access_token")){
                throw new WXException("2001","获取微信access_token失败！");
            }
            token = new AccessToken();
            token.setAccess_token(json.getString("access_token"));
            token.setExpires_in(json.getInt("expires_in"));
            token.setTime(new Date());
            log.info("get access_token: "+token);
        }
        return token.getAccess_token();


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

    private String doHttpsGet2(String url,String charset){
        CloseableHttpClient httpClient = createSSLClientDefault();

        CloseableHttpResponse response = null;
        HttpGet get = new HttpGet(url);
        String result = "";

        try {
            response = httpClient.execute(get);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    result = EntityUtils.toString(entity, charset);
                }
            }
        } catch (IOException ex) {
            log.error("ling 204",ex);
        } finally {
            if (null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException ex) {
                    log.error("line 210",ex);
                }
            }
        }
        return result;
    }

    private String doHttpsPost(String url,JSONObject json,String charset){
        CloseableHttpClient httpClient = createSSLClientDefault();
        HttpPost post = new HttpPost(url);

        CloseableHttpResponse response = null;

        post.addHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(json.toString(),Charset.forName(charset)));

        String result = "";

        try {
            response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    result = EntityUtils.toString(entity, charset);
                }
            }
        } catch (IOException ex) {
            log.error("",ex);
        } finally {
            if (null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException ex) {
                    log.error("",ex);
                }
            }
        }
        return result;
    }

    private static CloseableHttpClient createSSLClientDefault() {

        SSLContext sslContext;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                @Override
                public boolean isTrusted(X509Certificate[] xcs, String string){
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyStoreException ex) {
            log.error(ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log.error("",ex);
        } catch (KeyManagementException ex) {
            log.error("",ex);
        }

        return HttpClients.createDefault();
    }

    public static void main(String[] args) {
        //openId:oRtgV0Sx13cNvscdc0oHeRPDHCuA

//        WeiXinServiceImpl s = new WeiXinServiceImpl();
//        String key = s.getSessionKey("071TFBv72CC2kL0toRy72vXDv72TFBvH");
//        System.out.println(s.cach.get(key));

//        String encData = "mshk65+k7tUMD3opkX/dJeNNbv9fTBbCz8+Z6A+EmtBIPMRmHaIGst+JjZ7YYfyydn9368o8ginwZucZuQOsAe0uNdnXZshD0CuxsMrY8ggaRlR12iy5rq5CxwHBfb5j1XLdkvG0TVzJDaZjN8bTjxk+rBQ4XGzGhzLEdJ2TsJrjdPqqX/QUCwN+RJWAhBGOvqycCFt6VNIDpHFFxAZ8Ejg1dnJiRMs/tAoqj07qB5gYOZAGehfs8DfYO0hgA0nZqPZk3yDt+XXAsYK4IEE9NJT3vnyuTZKy7c59msFnWH8P3LbSsqxvNiIObV43tnN6aN4QL6LrCYjX22BNg9Cv8NdGfLFkOV+TWz0oojK5qIZ5HLBHF3xodELYXAwaJrLBUQisMs2VqA+sGnYSZfAg7ZvC9i0BFPpf1oOJT7/xbtMQ5I7EibDOisOea1bEJQjt7LCIsgeC7eswOZiwRNGr/g==";
//        String iv = "keICWhQLUEP882P9pPfFQQ==";
//        try {
//            String v = s.decrypt(key, encData, iv);
//            System.out.println("### "+v);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String s = "{\"phoneNumber\":\"13501391707\",\"watermark\":{\"appid\":\"wxa2fad04343e01afe\",\"timestamp\":1518247162},\"purePhoneNumber\":\"13501391707\",\"countryCode\":\"86\",\"openid\":\"oj_n84682dxsXds0UH72A-4grrVs\"}";
        JSONObject json = new JSONObject(s);
        System.out.println(json);
    }

}
