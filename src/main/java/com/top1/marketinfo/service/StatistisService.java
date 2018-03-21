package com.top1.marketinfo.service;


import com.top1.marketinfo.vo.VerifyCountVO;

/*
* Author GQ
* Date:2018/2/5
* Time:下午5:35
*/
public interface StatistisService {

    public VerifyCountVO aboutVerifyCount();

    public int getCountOfVerfy();//获取未审核（需求，热点，评论）数量
}
