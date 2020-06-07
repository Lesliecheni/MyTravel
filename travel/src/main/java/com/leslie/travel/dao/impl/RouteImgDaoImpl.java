package com.leslie.travel.dao.impl;

import com.leslie.travel.dao.RouteImgDao;
import com.leslie.travel.domain.RouteImg;
import com.leslie.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author LeslieCheni
 * @Date Created in 17:26 2020/5/23 0023
 * @Version JDK1.8
 */
public class RouteImgDaoImpl implements RouteImgDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    /**
     * 图片的查询
     * @param rid
     * @return
     */
    @Override
    public List<RouteImg> findByRid(int rid) {

        String sql = "select * from tab_route_img where rid = ? ";
        return template.query(sql,new BeanPropertyRowMapper<>(RouteImg.class),rid);
    }
}
