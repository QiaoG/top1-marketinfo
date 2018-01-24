package com.top1.marketinfo.controller;

import com.top1.marketinfo.entity.Demand;
import com.top1.marketinfo.entity.News;
import com.top1.marketinfo.service.DemandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
* Author GQ
* Date:2018/1/12
* Time:下午9:02
*/
@Slf4j
@RestController
@RequestMapping(path = "/demands")
public class DemandController {

    @Autowired
    private DemandService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Demand> findAll(Integer page,Integer size) {
        List list = service.findAll(page==null?1:page,size==null?20:size);
        return list;
    }
}
