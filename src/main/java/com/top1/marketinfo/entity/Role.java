package com.top1.marketinfo.entity;

import com.fasterxml.jackson.annotation.JsonValue;

/*
* Author GQ
* Date:2018/1/5
* Time:下午8:03
*/
public enum Role {
    SUPER(0,"超级用户"),MANAGER(1,"管理员"),GENERAL(2,"普通用户");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonValue
    public String getName() {
        return name()+"|" + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name;

    Role(int id, String s) {
        this.id = id;
        this.name = s;
    }
}
