package com.top1.marketinfo.controller;

import com.top1.marketinfo.service.StatistisService;
import com.top1.marketinfo.vo.VerifyCountVO;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/*
* Author GQ
* Date:2018/2/5
* Time:下午5:49
*/
@Controller
@Slf4j
@RequestMapping(value = "/statistics")
public class StatisticsController {

    @Autowired
    private StatistisService service;

    @RequestMapping(value = "/verifyCount", method = RequestMethod.GET)
    public @ResponseBody
    VerifyCountVO verifyCount(){
        VerifyCountVO json = service.aboutVerifyCount();
        return json;
    }
}
