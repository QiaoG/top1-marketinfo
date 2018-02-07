package com.top1.marketinfo.repository;

import com.top1.marketinfo.entity.Discuss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

//    @RestResource(path = "sourceAndType",rel = "sourceAndType")
    @Query(value = "select * from discuss where status=? and discuss_source=? and source_type=? ORDER BY create_date DESC limit ?,?",nativeQuery = true)
    List<Discuss> findByStatusAndSourceAndTypeAndTitle(int status,int source, int type,int offset,int size);

    //@RestResource(path = "statusAndType",rel = "statusAndType")
    @Query(value = "select * from discuss where status=? and source_type=? and source_title like ? ORDER BY create_date DESC limit ?,?",nativeQuery = true)
    List<Discuss> findByStatusAndTypeAndTitle(int status, int type, String title,int offset,int size);

    //@RestResource(path = "authorAndType",rel = "authorAndType")
    @Query(value = "select * from discuss where author_id=? and source_type=? and source_title like ? ORDER BY create_date DESC limit ?,?",nativeQuery = true)
    List<Discuss> findByAuthorAndTypeAndTitle(long author, int type, String title, int offset,int size);

}
