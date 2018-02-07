package com.top1.marketinfo.service;

import com.top1.marketinfo.entity.Demand;

import java.util.List;

/*
* Author GQ
* Date:2018/1/5
* Time:下午4:14
*/
public interface DemandService {

    public List<Demand> findAll(String title,int verify,int page,int size);
}
