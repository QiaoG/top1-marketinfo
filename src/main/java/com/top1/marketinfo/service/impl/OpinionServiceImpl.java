package com.top1.marketinfo.service.impl;

import com.top1.marketinfo.entity.Opinion;
import com.top1.marketinfo.repository.OpinionRepository;
import com.top1.marketinfo.service.OpinionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* Author GQ
* Date:2018/3/26
* Time:上午11:37
*/
@Service
@Slf4j
public class OpinionServiceImpl implements OpinionService {

    @Autowired
    private OpinionRepository repository;

    @Override
    public List<Opinion> findAllPageable(int page, int size) {
        Page<Opinion> list = repository.findAll(new PageRequest(page,size,new Sort(Sort.Direction.DESC,"createTime")));
        return list.getContent();
    }
}
