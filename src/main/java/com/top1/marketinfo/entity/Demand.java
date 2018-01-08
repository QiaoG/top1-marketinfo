package com.top1.marketinfo.entity;
/*
* 需求
* Author GQ
* Date:2018/1/5
* Time:下午2:51
*/

import javax.persistence.*;
import java.util.Date;

@Entity
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private DemandType type = DemandType.OTHER;

    private String content = "";

    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date invalidDate = new Date();

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private User publisher;

    @Column(columnDefinition = "tinyint default 0 COMMENT '0:no verify，1:has verify'")
    private int status = 0;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DemandType getType() {
        return type;
    }

    public void setType(DemandType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

}
