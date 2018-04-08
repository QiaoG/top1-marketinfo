package com.top1.marketinfo.entity;

import com.fasterxml.jackson.annotation.JsonValue;

/*
* Author GQ
* Date:2018/1/6
* Time:下午8:06
*/
public enum DemandType {
    STOCK_TRANSFER(0, "选择需求"), INVITE_SURVEY(1, "约调研"), INVITE_INVESTMENT(2, "约投资"), STOCK_MORTGAGE(3, "股权抵押"),
    MERGER(4,"并购"),B_SHELL(5,"借壳"),ADVICE(6,"咨询服务"),OTHER(7, "其他");

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonValue
    public String getName() {
        return name() + "|" + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    DemandType(int id, String name) {
        this.id = id;
        this.name = name;

    }
}
