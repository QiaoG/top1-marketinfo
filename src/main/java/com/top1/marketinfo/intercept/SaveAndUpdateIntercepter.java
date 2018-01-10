package com.top1.marketinfo.intercept;

import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
* Author GQ
* Date:2018/1/10
* Time:上午10:38
*/
public class SaveAndUpdateIntercepter {
    private static Logger log = LoggerFactory.getLogger(SaveAndUpdateIntercepter.class);

    public static Object handle(MethodInvocation invocation) throws Throwable{
        log.info(invocation.getMethod().getName()+" will be called!");
        Object obj=invocation.proceed();
        log.info(invocation.getMethod().getName()+"已经被执行");
        return obj;
    }
}
