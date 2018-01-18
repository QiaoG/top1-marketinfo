package com.top1.marketinfo.controller;

import lombok.Data;

/*
* Author GQ
* Date:2018/1/9
* Time:下午3:58
*/
@Data
public class ResponseMessage {
    private int code;
    private String message;
    private Object data;

    public ResponseMessage(int code, String message,Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
