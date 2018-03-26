package com.top1.marketinfo.repository;

import com.top1.marketinfo.entity.Opinion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
* Author GQ
* Date:2018/3/26
* Time:上午11:26
*/
@RepositoryRestResource(path="opinion")
public interface OpinionRepository extends JpaRepository<Opinion,Long> {

}
