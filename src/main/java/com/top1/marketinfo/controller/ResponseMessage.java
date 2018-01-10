package com.top1.marketinfo.controller;

/*
* Author GQ
* Date:2018/1/9
* Time:下午3:58
*/
public class ResponseMessage {
    private int code;
    private String message;

    public ResponseMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
