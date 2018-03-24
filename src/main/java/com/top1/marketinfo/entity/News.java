package com.top1.marketinfo.entity;
/*
* Author GQ
* Date:2018/1/5
* Time:下午2:51
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition="varchar(20480)",nullable = false)
    private String content = "";

    private long authorId;

    @Column(columnDefinition="VARCHAR(50) BINARY")
    private String authorNickName;

    private String newsSource;

    private String title = "";

    @Column(columnDefinition = "bigint default 0 ")
    private int publisherId;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date verifyDate;

    @Column(columnDefinition = "tinyint default 0 COMMENT '0:no verify,1:has verify'")
    private int status = 0;

    //证劵代码6位
    @Column(columnDefinition="VARCHAR(6) ")
    private String code;

    private String formId;//提交的表单id

    @Transient
    private String action;//拦截是识别操作，目前用于审核

    @Transient
    private int discussCount;

}
