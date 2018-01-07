package com.top1.marketinfo.entity;
/*
* Author GQ
* Date:2018/1/5
* Time:下午2:45
*/

import javax.persistence.*;

@Entity
public class User {

//    public User() {
//        super();
//    }
//
//    public User(Long id,String wxNumber, String mobile, String nickname, Role role) {
//        super();
//        this.id = id;
//        this.wxNumber = wxNumber;
//        this.mobile = mobile;
//        this.nickname = nickname;
//        this.role = role;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWxNumber() {
        return wxNumber;
    }

    public void setWxNumber(String wxNumber) {
        this.wxNumber = wxNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String wxNumber;

    private String mobile;

    private String nickname;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private Role role=Role.GENERAL;

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private Enterprise enterprise;
}
