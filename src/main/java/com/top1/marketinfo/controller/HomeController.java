package com.top1.marketinfo.controller;/*
* Author GQ
* Date:2018/1/5
* Time:上午9:40
*/

import com.top1.marketinfo.entity.Discuss;
import com.top1.marketinfo.entity.User;
import com.top1.marketinfo.repository.DiscussRepository;
import com.top1.marketinfo.repository.UserRepository;
import com.top1.marketinfo.service.UserService;
import com.top1.marketinfo.service.WeiXinService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

@Controller
@Slf4j
public class HomeController {


    @Value("${jwt.secretkey:market_info}")
    private String secretkey;


   @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody String home(){
        return "home";
    }


    @RequestMapping(value = "/api/add/disc", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage addDiscuss(@RequestBody Discuss discuss){

        log.info(discuss.getDiscussSource()+":"+discuss.getContent());
//        discuss.setAuthor(userRepository.findOne(discuss.getAuthor().getId()));
//        discuss.setPublisher(userRepository.findOne(discuss.getPublisher().getId()));
//        repository.save(discuss);
        return new ResponseMessage(0,"",null);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage auth(@Param("code") String code){
        String password = getPassword();
        if(password.equals(code)){
            long m = System.currentTimeMillis()+(1000*60*60*10);
            String token = Jwts.builder().setSubject("Authorization")
                    .claim("name", "topone").setIssuedAt(new Date()).setExpiration(new Date(m))
                    .signWith(SignatureAlgorithm.HS256, secretkey).compact();
            return new ResponseMessage(0, "success", token);
        }else {
            return new ResponseMessage(1, "无效的code值!", "");
        }
    }


    private String getPassword(){
        String pass = "";
        try {
            ResourcePropertySource source = new ResourcePropertySource("classpath:application.properties");
            pass = (String)source.getProperty("auth.password");
        } catch (IOException e) {
            pass = e.getMessage();
        }
        return pass;
    }
}
