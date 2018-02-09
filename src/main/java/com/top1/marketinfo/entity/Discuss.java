package com.top1.marketinfo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/*
* 评论
* Author GQ
* Date:2018/1/7
* Time:下午4:51
*/
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
@Data
@Table(indexes = {@Index(name = "index_source",columnList = "discussSource")})
public class Discuss {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "bigint(20) COMMENT 'new_id/demand_id'")
    private int discussSource;

    @Column(columnDefinition = "tinyint COMMENT '0:news,1:demand'")
    private int sourceType;

    private String sourceTitle;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private Integer authorId=-1;

    @Column(columnDefinition="VARCHAR(50) BINARY")
    private String authorNickName;

    private int publisherId;

    @Column(columnDefinition = "tinyint default 0 COMMENT '0:no verify,1:has verify'")
    private int status = 0;

}
