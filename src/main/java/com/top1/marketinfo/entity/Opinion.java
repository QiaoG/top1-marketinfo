package com.top1.marketinfo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/*
* Author GQ
* Date:2018/3/26
* Time:上午11:22
*/
@Data
@Entity
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition="varchar(50)")
    private String contact;

    @Column(columnDefinition="varchar(500)")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();
}
