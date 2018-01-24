package com.top1.marketinfo.entity;
/*
* 需求
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
public class Demand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private DemandType type = DemandType.OTHER;

    private String content = "";

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date invalidDate = new Date();

    private int publisherId;

    @Column(columnDefinition = "tinyint default 0 COMMENT '0:no verify,1:has verify'")
    private int status = 0;

    private String title = "";

    @Transient
    private int discussCount;

}
