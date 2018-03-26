package com.top1.marketinfo.controller;

import com.top1.marketinfo.entity.Opinion;
import com.top1.marketinfo.service.OpinionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/*
* Author GQ
* Date:2018/3/26
* Time:下午12:05
*/
@Controller
@Slf4j
@RequestMapping(value = "/opinions")
public class OpinionController {

    @Autowired
    private OpinionService service;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public @ResponseBody
    List<Opinion> findAll(Integer page, Integer size) {
        List<Opinion> list = service.findAllPageable(page==null?0:page,size==null?20:size);
        return list;
    }
}
