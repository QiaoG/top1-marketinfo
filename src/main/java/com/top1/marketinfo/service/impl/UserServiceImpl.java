package com.top1.marketinfo.service.impl;

import com.top1.marketinfo.service.AbstractJdbcService;
import com.top1.marketinfo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

/*
* Author GQ
* Date:2018/1/8
* Time:下午6:13
*/
@Service
public class UserServiceImpl extends AbstractJdbcService implements UserService{

    private final String sqlOne = "select count(*) from user";

    @Autowired
    public UserServiceImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public int demo() {
        int c = this.jdbcTemplate.queryForObject(sqlOne, Integer.class);
        return c;
    }
}
