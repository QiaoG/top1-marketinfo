package com.top1.marketinfo.service;

import com.top1.marketinfo.entity.Opinion;

import java.util.List;

/*
* Author GQ
* Date:2018/3/26
* Time:上午11:34
*/
public interface OpinionService {

    public List<Opinion> findAllPageable(int page,int size);
}
