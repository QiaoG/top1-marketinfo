package com.top1.marketinfo.controller;

import com.top1.marketinfo.entity.User;
import com.top1.marketinfo.service.UserService;
import com.top1.marketinfo.vo.VerifyCountVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/*
* Author GQ
* Date:2018/2/6
* Time:下午4:34
*/
@Controller
@Slf4j
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/findByNicknameLike", method = RequestMethod.GET)
    public @ResponseBody
    List<User> findByNickname(String name,Integer page,Integer size){

        List<User> list = new ArrayList<>();
        list = service.findByNickName(name==null?"":name,page==null?0:page,size==null?20:size);
        return list;
    }
}
