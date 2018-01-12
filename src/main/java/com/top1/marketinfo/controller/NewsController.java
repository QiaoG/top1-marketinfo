package com.top1.marketinfo.controller;

import com.top1.marketinfo.entity.News;
import com.top1.marketinfo.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
* Author GQ
* Date:2018/1/12
* Time:下午7:37
*/
@Slf4j
@RestController
@RequestMapping("/newses")
public class NewsController {

    @Autowired
    private NewsService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<News> findAll(){
        List list = service.findAll();
        log.info("all news size = "+list.size());
        return list;
    }

}
