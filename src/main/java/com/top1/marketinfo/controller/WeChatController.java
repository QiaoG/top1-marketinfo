package com.top1.marketinfo.controller;

import com.top1.marketinfo.entity.User;
import com.top1.marketinfo.exception.WXException;
import com.top1.marketinfo.service.UserService;
import com.top1.marketinfo.service.WeiXinService;
import com.top1.marketinfo.vo.WxSession;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/*
* Author GQ
* Date:2018/1/24
* Time:下午4:50
*/
@Controller
@Slf4j
@RequestMapping(value = "/wx")
public class WeChatController {

    @Value("${jwt.secretkey:market_info}")
    private String secretkey;

    @Autowired
    private WeiXinService wxService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage login(@Param("code") String code){
        String key = wxService.getSessionKey(code);
        JSONObject json = wxService.getSessionInCach(key);
        User user = userService.getByWxOpenid(json.getString("openid"));
        String token = Jwts.builder().setSubject("Authorization")
                .claim("nickname", user.getNickname()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretkey).compact();
        user.setToken(token);
        return new ResponseMessage(0,"",user);
    }

    @RequestMapping(value = "/sessionKey", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getSessionKey(@Param("code") String code){
        if(code == null || code.isEmpty()){
            return new ResponseMessage(-1,"code不能为空！",null);
        }
        WxSession js = null;
        try {
            String key = wxService.getSessionKey(code);
            log.info("[wx] jscode:"+code+" -> "+"key:"+key);
            js = new WxSession();
            js.setSessionKey(key);
            return new ResponseMessage(0,"ok",js);
        } catch (WXException e) {
            log.error(e.toString());
            return new ResponseMessage(1,e.getMessage(),null);
        }

    }

    @RequestMapping(value = "/dcymobile", method = RequestMethod.GET)
    public @ResponseBody ResponseMessage decryptMobile(@Param("key") String key,@Param("encData") String encData,@Param("iv") String iv){
        log.info("[wx] decrypt,key = "+key);
        try {
            JSONObject info = wxService.decrypt(key,encData,iv);
            if(info == null){
                return new ResponseMessage(1,"解析手机号异常！",null);
            }
            log.info("[wx] decrypt,info:"+info);
            User user = wxService.handlePhone(info);
            return new ResponseMessage(0,"ok",user);
        } catch (WXException e) {
            log.error("[wx] decrypt error:"+e.getMessage(),e);
            return new ResponseMessage(1,e.getMessage(),null);
        } catch (Exception e) {
           log.error("未知异常：",e);
            return new ResponseMessage(1,"后台程序错误："+e.getMessage(),null);
        }

    }
}
