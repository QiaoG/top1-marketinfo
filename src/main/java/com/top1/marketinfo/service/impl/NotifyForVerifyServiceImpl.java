package com.top1.marketinfo.service.impl;

import com.top1.marketinfo.entity.Demand;
import com.top1.marketinfo.entity.News;
import com.top1.marketinfo.entity.User;
import com.top1.marketinfo.repository.DemandRepository;
import com.top1.marketinfo.repository.NewsRepository;
import com.top1.marketinfo.repository.UserRepository;
import com.top1.marketinfo.service.NotifyForVerifyServcie;
import com.top1.marketinfo.service.UserService;
import com.top1.marketinfo.service.WeiXinService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* Author GQ
* Date:2018/1/10
* Time:上午11:54
*/
@Slf4j
@Service
public class NotifyForVerifyServiceImpl implements NotifyForVerifyServcie {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeiXinService wxService;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private DemandRepository demandRepository;

    @Override
    public Object handle(Object para) {
        log.info("[INTERCEPT] will handle verify notify ... "+para.getClass().getName());
        User user = null;
        boolean pass = false;
        if(para instanceof News){
            News news = (News)para;
            log.info("[INTERCEPT] news verify result: "+news.getAction());
            if("verify".equals(news.getAction()) || "verify_no".equals(news.getAction())){//审核通过/不通过
                if("verify_no".equals(news.getAction())){
                    newsRepository.delete(news.getId());
                }
                pass = "verify".equals(news.getAction()) ? true :false;
                user = userRepository.findOne(news.getAuthorId());
                if(user != null && news.getFormId() != null){
                    wxService.sendVerifyMessage(user,news,null,pass);
                }else{
                    log.error("[INTERCEPT] verify news complement,not find user by news.author_id for weixin notify");
                }
            }
        }
        if(para instanceof Demand){
            Demand demand = (Demand)para;
            log.info("[INTERCEPT] demand verify result: "+demand.getAction());
            if("verify".equals(demand.getAction()) || "verify_no".equals(demand.getAction())){//审核通过/不通过
                if("verify_no".equals(demand.getAction())){
                    demandRepository.delete(demand.getId());
                }
                pass = "verify".equals(demand.getAction()) ? true :false;
                user = userRepository.findOne(demand.getVerifyId());
                if(user != null && demand.getFormId() != null){
                    wxService.sendVerifyMessage(user,null,demand,pass);
                }else{
                    log.error("[INTERCEPT] verify demand complement, not find user by demand.author_id for weixin notify");
                }
            }
        }
        return null;
    }
}
