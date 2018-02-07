package com.top1.marketinfo.service;

import com.top1.marketinfo.entity.Discuss;

import java.util.List;

/*
* Author GQ
* Date:2018/2/7
* Time:上午9:58
*/
public interface DiscussService {

    public List<Discuss> findByStatusAndSourceAndTypeAndTitleLike(int status,int source,int type,int offset,int size);

    public List<Discuss> findByStatusAndTypeAndTitleLike(int status,int type,String title,int offset,int size);

    public List<Discuss> findByAuthorAndTypeAndTitleLike(long author,int type,String title,int offset,int size);
}
