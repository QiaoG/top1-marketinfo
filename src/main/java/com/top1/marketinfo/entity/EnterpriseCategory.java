package com.top1.marketinfo.entity;

/*
* Author GQ
* Date:2018/1/5
* Time:下午8:14
*/
public enum EnterpriseCategory implements EnumName {

    LISTED_COMPANIES{
        @Override
        public String getDescribe() {
            return "挂牌企业";
        }
    }
    ,INVESTMENT_INSTITUTION{
        @Override
        public String getDescribe() {
            return "投资机构";
        }
    }
    ,FINANCIAL_ADVISOR{
        @Override
        public String getDescribe() {
            return "财务顾问";
        }
    }
    ,SECURITIES_COMPANY{
        @Override
        public String getDescribe() {
            return "证劵公司";
        }
    }
    ,ANALYST {
        @Override
        public String getDescribe() {
            return "分析师";
        }
    }
    ,OTHER{
        @Override
        public String getDescribe() {
            return "其他机构";
        }
    };

}

