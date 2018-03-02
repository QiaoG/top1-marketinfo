package com.top1.marketinfo.configuration;

import com.top1.marketinfo.filter.JwtFilter;
import com.top1.marketinfo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/*
* Author GQ
* Date:2018/3/2
* Time:下午7:52
*/
@Configuration
public class WebConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        JwtFilter filter = new JwtFilter();
        filter.setSecretkey(env.getProperty("logging.level.root"));
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }
}
