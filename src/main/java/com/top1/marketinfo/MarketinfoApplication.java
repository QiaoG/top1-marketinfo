package com.top1.marketinfo;

import com.top1.marketinfo.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class MarketinfoApplication {

	@Autowired
	private Environment env;

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		JwtFilter filter = new JwtFilter();
		filter.setSecretkey(env.getProperty("jwt.secretkey"));
		registrationBean.setFilter(filter);
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(MarketinfoApplication.class, args);
	}
}
