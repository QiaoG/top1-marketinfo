package com.top1.marketinfo.db;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/*
* Author GQ
* Date:2018/1/8
* Time:上午11:58
*/
public class MySQL5InnoDBUtf8Dialect extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}
