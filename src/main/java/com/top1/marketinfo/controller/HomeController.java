package com.top1.marketinfo.controller;/*
* Author GQ
* Date:2018/1/5
* Time:上午9:40
*/

import com.top1.marketinfo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService service;

   @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody String home(){
        return "home";
    }

    @RequestMapping(value = "/api/count/users", method = RequestMethod.GET)
    public @ResponseBody String countUsers(){
        return service.demo()+"";
    }


}
