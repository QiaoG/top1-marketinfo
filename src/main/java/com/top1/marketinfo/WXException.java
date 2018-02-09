package com.top1.marketinfo;

import lombok.Data;

/*
* Author GQ
* Date:2018/2/9
* Time:下午7:30
*/
@Data
public class WXException extends RuntimeException {
    private String message;
    private String code;

    public WXException(String c,String message){
        this.code = c;
        this.message = message;
    }
}
