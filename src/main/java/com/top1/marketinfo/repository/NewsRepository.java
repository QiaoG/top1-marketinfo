package com.top1.marketinfo.repository;

import com.top1.marketinfo.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
* Author GQ
* Date:2018/1/8
* Time:下午1:49
*/
@RepositoryRestResource(path = "news")
public interface NewsRepository extends JpaRepository<News, Long> {
}
