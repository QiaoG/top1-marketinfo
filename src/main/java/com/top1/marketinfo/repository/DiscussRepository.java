package com.top1.marketinfo.repository;

import com.top1.marketinfo.entity.Discuss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/*
* Author GQ
* Date:2018/1/8
* Time:下午1:53
*/
@RepositoryRestResource(path = "discuss")
public interface DiscussRepository extends JpaRepository<Discuss,Long> {

    @RestResource(path = "sourceAndType",rel = "sourceAndType")
    List<Discuss> findByDiscussSourceAndSourceType(@Param("source")int source, @Param("type") int type);
}
