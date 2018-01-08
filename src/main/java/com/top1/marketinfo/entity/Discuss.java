package com.top1.marketinfo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/*
* 评论
* Author GQ
* Date:2018/1/7
* Time:下午4:51
*/
@Entity
@Table(indexes = {@Index(name = "",columnList = "discussSource")})
public class Discuss {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "bigint(20) COMMENT 'new_id/demand_id'")
    private int discussSource;

    @Column(columnDefinition = "tinyint COMMENT '0:news,1:demand'")
    private int sourceType;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private User author;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private User publisher;

    @Column(columnDefinition = "tinyint default 0 COMMENT '0:no verify,1:has verify'")
    private int status = 0;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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

    public int getDiscussSource() {
        return discussSource;
    }

    public void setDiscussSource(int discussSource) {
        this.discussSource = discussSource;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getAuthor() {
        return author;
    }


    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
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
