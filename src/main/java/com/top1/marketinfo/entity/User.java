package com.top1.marketinfo.entity;
/*
* Author GQ
* Date:2018/1/5
* Time:下午2:45
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String wxNumber;

    private String mobile;

    @Column(columnDefinition="VARCHAR(50) BINARY")
    private String nickname;

    //用户在微信系统中的唯一标示：openid
    private String wxCode;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Enterprise enterprise;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate = new Date();

    private Role role = Role.GENERAL;

    private String avatarUrl;//头像地址

    @Column(columnDefinition="varchar(50)")
    private String job = "";//职位

    @Column(columnDefinition="varchar(50)")
    private String email = "";

    @Transient
    private String token;

    @Transient
    private int verifyCount;//如是管理员，待审核（需求，热点，评论）数量

}
