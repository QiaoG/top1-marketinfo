package com.top1.marketinfo;

import com.top1.marketinfo.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MarketinfoApplication {

//	@Autowired
//	@Bean
//	public FilterRegistrationBean jwtFilter(JwtFilter filter) {
//		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		registrationBean.setFilter(filter);
//		registrationBean.addUrlPatterns("/api/*");
//		return registrationBean;
//	}

	public static void main(String[] args) {
		SpringApplication.run(MarketinfoApplication.class, args);
	}
}
