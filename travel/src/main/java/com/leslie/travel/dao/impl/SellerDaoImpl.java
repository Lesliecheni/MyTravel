package com.leslie.travel.dao.impl;

import com.leslie.travel.dao.SellerDao;
import com.leslie.travel.domain.Seller;
import com.leslie.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author LeslieCheni
 * @Date Created in 17:34 2020/5/23 0023
 * @Version JDK1.8
 */
public class SellerDaoImpl implements SellerDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Seller findById(int id) {
        String sql = "select * from tab_seller where sid = ? ";

        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Seller.class),id);
    }
}
