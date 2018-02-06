package com.top1.marketinfo.service.impl;

import com.top1.marketinfo.entity.User;
import com.top1.marketinfo.repository.UserRepository;
import com.top1.marketinfo.service.AbstractJdbcService;
import com.top1.marketinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

/*
* Author GQ
* Date:2018/1/8
* Time:下午6:13
*/
@Service
public class UserServiceImpl extends AbstractJdbcService implements UserService{

    private final String sqlOne = "select count(*) from user";

    @Autowired
    private UserRepository repository;

    @Autowired
    public UserServiceImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public int demo() {
        int c = this.jdbcTemplate.queryForObject(sqlOne, Integer.class);
        return c;
    }

    @Override
    public List<User> findByNickName(String name,int page,int size) {
        Page<User> pages = repository.findByNicknameLike("%"+name+"%",new PageRequest(page,size));
        return pages.getContent();
    }

    @Override
    public int saveUser(User user) {
        return 0;
    }
}
