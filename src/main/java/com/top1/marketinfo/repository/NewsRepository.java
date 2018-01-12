package com.top1.marketinfo.repository;

import com.top1.marketinfo.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/*
* Author GQ
* Date:2018/1/8
* Time:下午1:49
*/
@RepositoryRestResource(path = "news")
public interface NewsRepository extends JpaRepository<News, Long> {

    @Override
    @RestResource(path = "all",rel = "all")
//    @Query(value = "select id,title,content,author_id,news_source,publisher_id," +
//            "create_date,`status`,(select count(discuss.id) from discuss where discuss.discuss_source=news.id) discussCount " +
//            "from news ORDER BY create_date desc",nativeQuery = true)
    @Query("select n from News n order by n.createDate desc ")
    List<News> findAll();
}
