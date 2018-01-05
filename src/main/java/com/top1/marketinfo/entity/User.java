package com.top1.marketinfo.entity;
/*
* Author GQ
* Date:2018/1/5
* Time:下午2:45
*/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String wxNumber;

    private String mobile;

    private String nickname;

    /**
     * 0:超级管理员
     * 1：管理员
     * 2：普通用户
     */
    private int role=2;
}
