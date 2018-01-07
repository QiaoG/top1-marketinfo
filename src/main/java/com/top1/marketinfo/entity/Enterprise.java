package com.top1.marketinfo.entity;
/*
* Author GQ
* Date:2018/1/5
* Time:下午2:50
*/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnterpriseCategory getCategory() {
        return category;
    }

    public void setCategory(EnterpriseCategory category) {
        this.category = category;
    }

    public String getSecuritiesCode() {
        return securitiesCode;
    }

    public void setSecuritiesCode(String securitiesCode) {
        this.securitiesCode = securitiesCode;
    }

    private String name;

    private EnterpriseCategory category;

    private String securitiesCode;

}
