package com.top1.marketinfo.controller;

import com.top1.marketinfo.entity.Discuss;
import com.top1.marketinfo.service.DiscussService;
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
* Date:2018/2/7
* Time:上午10:22
*/
@Controller
@Slf4j
@RequestMapping(value = "/discusses")
public class DiscussController {

    @Autowired
    private DiscussService service;

    @RequestMapping(value = "/bySource", method = RequestMethod.GET)
    public @ResponseBody
    List<Discuss> findBySource(Integer status,Integer source,Integer type,Integer offset, Integer size){
        int sa = status==null?0:status;
        if(source == null){
            log.error("discuss source(news or demand) id is null!");
            return new ArrayList<>();
        }
        int t = type == null ? 0 : type;
        return service.findByStatusAndSourceAndTypeAndTitleLike(sa,source,t,(offset==null?0:offset),(size==null?20:size));
    }

    @RequestMapping(value = "/byStatus", method = RequestMethod.GET)
    public @ResponseBody
    List<Discuss> findByStatus(Integer status,Integer type,String title, Integer offset, Integer size){
        int sa = status==null?0:status;
        int t = type == null ? 0 : type;
        return service.findByStatusAndTypeAndTitleLike(sa,t,"%"+(title==null?"":title)+"%",(offset==null?0:offset),(size==null?20:size));
    }

    @RequestMapping(value = "/byAuthor", method = RequestMethod.GET)
    public @ResponseBody
    List<Discuss> findByStatus(Long author,Integer type,String title, Integer offset, Integer size){
        if(author == null){
            log.error("discuss author id is null!");
            return new ArrayList<>();
        }
        int t = type == null ? 0 : type;
        return service.findByAuthorAndTypeAndTitleLike(author,t,"%"+(title==null?"":title)+"%",(offset==null?0:offset),(size==null?20:size));
    }
}
