package com.leslie.travel.dao.impl;

import com.leslie.travel.dao.CategoryDao;
import com.leslie.travel.domain.Category;
import com.leslie.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @Author LeslieCheni
 * @Date Created in 18:09 2020/5/22 0022
 * @Version JDK1.8
 */
public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //查询所有
    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category";

        return template.query(sql,new BeanPropertyRowMapper<Category>(Category.class));
    }
}
