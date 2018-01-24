package com.top1.marketinfo.service.impl;

import com.top1.marketinfo.entity.Demand;
import com.top1.marketinfo.entity.DemandType;
import com.top1.marketinfo.service.AbstractJdbcService;
import com.top1.marketinfo.service.DemandService;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/*
* Author GQ
* Date:2018/1/12
* Time:下午8:49
*/
@Service
public class DemandServiceImpl extends AbstractJdbcService implements DemandService {

    private final String sqlAll = "SELECT id,type,content,publisher_id,publish_date,invalid_date,`status`,title," +
            "(SELECT count(discuss.id) FROM discuss WHERE discuss.discuss_source=demand.id and source_type=1) discuss_count " +
            "FROM demand ORDER BY publish_date DESC LIMIT ?,?";

    public DemandServiceImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Demand> findAll(int page,int size) {
        final List<Demand> list = new ArrayList<>();
        int offset = page * size;
        this.jdbcTemplate.query(sqlAll,new Object[]{offset,size}, rs -> {
            Demand demand = new Demand();
            demand.setId(rs.getLong(1));
            demand.setType(DemandType.values()[rs.getInt(2)]);
            demand.setContent(rs.getString(3));
            demand.setPublisherId(rs.getInt(4));
            demand.setPublishDate(rs.getTimestamp(5));
            demand.setInvalidDate(rs.getTimestamp(6));
            demand.setStatus(rs.getInt(7));
            demand.setTitle(rs.getString(8));
            demand.setDiscussCount(rs.getInt(9));
            list.add(demand);
        });
        return list;
    }
}
