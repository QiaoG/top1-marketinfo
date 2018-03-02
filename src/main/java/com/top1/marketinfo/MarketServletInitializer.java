package com.top1.marketinfo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/*
* Author GQ
* Date:2018/3/2
* Time:下午7:42
*/
public class MarketServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MarketinfoApplication.class);
    }
}
