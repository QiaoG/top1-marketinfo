package com.top1.marketinfo.configuration;

import com.top1.marketinfo.repository.SubRepositoryFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
* Author GQ
* Date:2018/1/10
* Time:上午10:36
*/
@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass = SubRepositoryFactoryBean.class,
basePackages = "com.top1.marketinfo.repository")
public class RepositoryConfiguration {
}
