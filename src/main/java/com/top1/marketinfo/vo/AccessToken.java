package com.top1.marketinfo.vo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/*
* Author GQ
* Date:2018/3/23
* Time:下午5:24
*/
@Data
public class AccessToken {

    private String access_token;

    private int expires_in;

    private Date time;
}
