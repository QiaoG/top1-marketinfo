package com.top1.marketinfo.entity;

import com.fasterxml.jackson.annotation.JsonValue;

/*
* Author GQ
* Date:2018/1/5
* Time:下午8:14
*/
public enum EnterpriseCategory{

    LISTED_COMPANIES(0, "挂牌企业"), INVESTMENT_INSTITUTION(1, "投资机构"), FINANCIAL_ADVISOR(2, "财务顾问"), SECURITIES_COMPANY(3, "证劵公司"), ANALYST(4, "分析师"), OTHER(5, "其他机构");

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonValue
    public String getName() {
        return id+"|"+name() + "|" + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    EnterpriseCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

