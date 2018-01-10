package com.top1.marketinfo.service.impl;

import com.top1.marketinfo.service.NotifyForVerifyServcie;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/*
* Author GQ
* Date:2018/1/10
* Time:上午11:54
*/
@Slf4j
@Service
public class NotifyForVerifyServiceImpl implements NotifyForVerifyServcie {

    @Override
    public Object handle(Object para) {
        log.info("will handle verify notify ...");
        return null;
    }
}
