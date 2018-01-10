package com.top1.marketinfo.repository;

import com.top1.marketinfo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/*
* Author GQ
* Date:2018/1/7
* Time:上午8:35
*/
//@RepositoryRestResource(path="user")
public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    List<User> findAll();
}
