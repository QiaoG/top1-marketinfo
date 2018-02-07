package com.top1.marketinfo.service.impl;

import com.top1.marketinfo.entity.Discuss;
import com.top1.marketinfo.repository.DiscussRepository;
import com.top1.marketinfo.service.AbstractJdbcService;
import com.top1.marketinfo.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* Author GQ
* Date:2018/2/7
* Time:上午10:01
*/
@Service
public class DiscussServiceImpl implements DiscussService {

    @Autowired
    private DiscussRepository repository;

    @Override
    public List<Discuss> findByStatusAndSourceAndTypeAndTitleLike(int status,int source, int type,int offset,int size) {
        return repository.findByStatusAndSourceAndTypeAndTitle(status,source,type,offset,size);
    }

    @Override
    public List<Discuss> findByStatusAndTypeAndTitleLike(int status, int type, String title,int offset,int size) {
        return repository.findByStatusAndTypeAndTitle(status,type,"%"+(title==null?"":title.trim())+"%",offset,size);
    }

    @Override
    public List<Discuss> findByAuthorAndTypeAndTitleLike(long author, int type, String title,int offset,int size) {
        return repository.findByAuthorAndTypeAndTitle(author,type,"%"+(title==null?"":title.trim())+"%",offset,size);
    }
}
