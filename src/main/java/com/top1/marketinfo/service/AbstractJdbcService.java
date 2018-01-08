package com.top1.marketinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/*
* Author GQ
* Date:2018/1/8
* Time:下午6:15
*/
public abstract class AbstractJdbcService {

    public AbstractJdbcService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected JdbcTemplate jdbcTemplate;
}
