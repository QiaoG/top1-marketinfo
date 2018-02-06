package com.top1.marketinfo.repository;

import com.top1.marketinfo.entity.Discuss;
import com.top1.marketinfo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/*
* Author GQ
* Date:2018/1/7
* Time:上午8:35
*/
@RepositoryRestResource(path="user")
public interface UserRepository extends JpaRepository<User,Long> {

    @RestResource(path = "findByNickname",rel = "findByNickname")
    List<User> findByNickname(@Param("name") String name);

    Page<User> findByNicknameLike(String name, Pageable pageable);

}
