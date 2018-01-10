package com.top1.marketinfo.service;

/*
* 新增新闻、需求、评论时通知管理员
* 审核通过后，需要通知创建者
* Author GQ
* Date:2018/1/10
* Time:上午11:43
*/
public interface NotifyForVerifyServcie {
    public Object handle(Object para);
}
