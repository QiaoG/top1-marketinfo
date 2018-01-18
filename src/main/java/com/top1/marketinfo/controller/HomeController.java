package com.top1.marketinfo.controller;/*
* Author GQ
* Date:2018/1/5
* Time:上午9:40
*/

import com.top1.marketinfo.entity.Discuss;
import com.top1.marketinfo.repository.DiscussRepository;
import com.top1.marketinfo.repository.UserRepository;
import com.top1.marketinfo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class HomeController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscussRepository repository;

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

    @RequestMapping(value = "/api/add/discuss", method = RequestMethod.POST)
    public @ResponseBody ResponseMessage addDiscuss(@RequestBody Discuss discuss){

        log.info(discuss.getDiscussSource()+":"+discuss.getContent());
//        discuss.setAuthor(userRepository.findOne(discuss.getAuthor().getId()));
//        discuss.setPublisher(userRepository.findOne(discuss.getPublisher().getId()));
//        repository.save(discuss);
        return new ResponseMessage(0,"",null);
    }


}
