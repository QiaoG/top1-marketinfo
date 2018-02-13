package com.top1.marketinfo.service;

import com.top1.marketinfo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/*
* Author GQ
* Date:2018/1/5
* Time:下午4:12
*/
public interface UserService {
    public int demo();

    public User saveUser(User user);

    public List<User> findByNickName(String name,int page,int size);

    public User getByWxOpenid(String openid);
}
