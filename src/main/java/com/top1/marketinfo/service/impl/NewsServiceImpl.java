package com.top1.marketinfo.service.impl;

import com.top1.marketinfo.entity.News;
import com.top1.marketinfo.service.AbstractJdbcService;
import com.top1.marketinfo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/*
* Author GQ
* Date:2018/1/12
* Time:下午7:42
*/
@Service
public class NewsServiceImpl extends AbstractJdbcService implements NewsService {

    private final String sqlAll = "SELECT id,title,content,author_id,news_source,publisher_id,create_date,`status`," +
            "(SELECT count(discuss.id) FROM discuss WHERE discuss.discuss_source=news.id) discuss_count " +
            "FROM news ORDER BY create_date DESC";

    @Autowired
    @SuppressWarnings("unchecked")
    public NewsServiceImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<News> findAll() {
        final List<News> list = new ArrayList<>();
        this.jdbcTemplate.query(sqlAll, rs -> {
            News news = new News();
            news.setId(rs.getLong(1));
            news.setTitle(rs.getString(2));
            news.setContent(rs.getString(3));
            news.setAuthorId(rs.getInt(4));
            news.setNewsSource(rs.getString(5));
            news.setPublisherId(rs.getInt(6));
            news.setCreateDate(rs.getDate(7));
            news.setStatus(rs.getInt(8));
            news.setDiscussCount(rs.getInt(9));
            list.add(news);
        });
        return list;
    }
}
