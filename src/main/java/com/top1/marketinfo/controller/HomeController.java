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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@Slf4j
public class HomeController {

    @Value("${auth.password:marketinfo}")
    private String password;

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
}
