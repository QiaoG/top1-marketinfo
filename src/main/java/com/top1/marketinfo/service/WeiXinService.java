package com.top1.marketinfo.service;

import org.json.JSONObject;

/*
* Author GQ
* Date:2018/1/24
* Time:上午9:49
*/
public interface WeiXinService {
    public static final String WEIXIN_APP_SECRET = "27b6e4c5cd70ec795d686480c059f1b9";
    public static final String WEIXIN_APP_ID = "wx0a6f77e12b17b7f6";
    //https://api.weixin.qq.com/sns/jscode2session?appid=wx0a6f77e12b17b7f6&secret=27b6e4c5cd70ec795d686480c059f1b9&js_code=0013HDdj1bgjZx0H1ofj1DNxdj13HDd7&grant_type=authorization_code
    public String getSession(String jscode);
    public String decrypt(String sessionKey,String encData,String iv) throws Exception;
    public String handlePhone(String info);
}
