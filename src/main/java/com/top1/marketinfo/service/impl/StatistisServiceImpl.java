package com.top1.marketinfo.service.impl;

import com.top1.marketinfo.service.AbstractJdbcService;
import com.top1.marketinfo.service.StatistisService;
import com.top1.marketinfo.vo.VerifyCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/*
* Author GQ
* Date:2018/2/5
* Time:下午5:35
*/
@Service
public class StatistisServiceImpl extends AbstractJdbcService implements StatistisService{

    private final String sqlCount = "select (select count(id) from news where status=0) nc," +
            "(select count(id) from demand where status=0) dc," +
            "(select count(id) from discuss where status = 0) sc, (select count(id) from opinion) oc;";
    private final String sqlTotal = "select (select count(id) from news where status=0)+" +
            "(select count(id) from demand where status=0) + " +
            "(select count(id) from discuss where status = 0) as total;";

    @Autowired
    public StatistisServiceImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public VerifyCountVO aboutVerifyCount() {
        List<VerifyCountVO> list = new ArrayList();
        this.jdbcTemplate.query(sqlCount, rs -> {
            VerifyCountVO s = new VerifyCountVO();
            s.setNewsCount(rs.getInt(1));
            s.setDemandCount(rs.getInt(2));
            s.setDiscussCount(rs.getInt(3));
            s.setOpinionCount(rs.getInt(4));
            list.add(s);
        });
        if(list.size() > 0){
            return list.get(0);
        }else{
            return new VerifyCountVO();
        }
    }

    @Override
    public int getCountOfVerfy() {
        return 0;
    }
}
