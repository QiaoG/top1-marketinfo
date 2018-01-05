package com.top1.marketinfo.entity;

/*
* Author GQ
* Date:2018/1/5
* Time:下午8:03
*/
public enum Role implements EnumName{
    SUPER{
        @Override
        public String getDescribe() {
            return "超级用户";
        }
    }
    ,MANAGER{
        @Override
        public String getDescribe() {
            return "管理员";
        }
    }
    ,GENERAL{
        @Override
        public String getDescribe() {
            return "普通用户";
        }
    };
}
