package com.top1.marketinfo.entity;
/*
* Author GQ
* Date:2018/1/5
* Time:下午2:51
*/

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content = "";

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private User author;

    private String source;

    private String title = "";

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private User publisher;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
