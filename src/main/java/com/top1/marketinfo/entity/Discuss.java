package com.top1.marketinfo.entity;

import javax.persistence.*;
import java.util.Date;

/*
* 评论
* Author GQ
* Date:2018/1/7
* Time:下午4:51
*/
@Entity
public class Discuss {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private User author;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private User publisher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }
}
