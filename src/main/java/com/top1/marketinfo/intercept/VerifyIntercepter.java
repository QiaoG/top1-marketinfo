package com.top1.marketinfo.intercept;

import com.top1.marketinfo.service.NotifyForVerifyServcie;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
* 审核拦截器
* 当新增新闻，需求，评论时触发
* 审核通过时触发
* Author GQ
* Date:2018/1/10
* Time:上午10:38
*/
@Component
public class VerifyIntercepter {
    private Logger log = LoggerFactory.getLogger(VerifyIntercepter.class);

    @Autowired
    private NotifyForVerifyServcie notifyService;

    public Object handle(MethodInvocation invocation) throws Throwable{
        log.info(invocation.getMethod().getName()+" will be called!");
        Object obj=invocation.proceed();
        notifyService.handle(null);
        return obj;
    }
}
