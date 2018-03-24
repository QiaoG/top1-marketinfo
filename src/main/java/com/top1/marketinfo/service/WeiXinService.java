package com.top1.marketinfo.service;

import com.top1.marketinfo.entity.Demand;
import com.top1.marketinfo.entity.News;
import com.top1.marketinfo.entity.User;
import org.json.JSONObject;

/*
* Author GQ
* Date:2018/1/24
* Time:上午9:49
*/
public interface WeiXinService {
//    public static final String WEIXIN_APP_SECRET = "27b6e4c5cd70ec795d686480c059f1b9";
    public static final String WEIXIN_APP_SECRET = "f53416823f532a9021a420b1e121e49a";
    public static final String WEIXIN_APP_ID = "wxa2fad04343e01afe";
    public static final String WEIXIN_TEMPLATE_ID = "sXRTygkPimK3jFPVn0sFw6lrVI850ggZqWOEL7yrK34";
    //https://api.weixin.qq.com/sns/jscode2session?appid=wx0a6f77e12b17b7f6&secret=27b6e4c5cd70ec795d686480c059f1b9&js_code=0013HDdj1bgjZx0H1ofj1DNxdj13HDd7&grant_type=authorization_code
    public JSONObject getSessionInCach(String key);
    public String getSessionKey(String jscode);
    public JSONObject decrypt(String sessionKey,String encData,String iv) throws Exception;
    public User handlePhone(JSONObject info);

    /*
    * 审核完成后，通知发布者
    * type:0 热点,1 需求
    * pass:是否通过审核
    */
    public void sendVerifyMessage(User user, News news, Demand demand,boolean pass);
}
