package com.leslie.travel.dao;

import com.leslie.travel.domain.Category;

import java.util.List;

/**
 * @Author LeslieCheni
 * @Date Created in 18:08 2020/5/22 0022
 * @Version JDK1.8
 */
public interface CategoryDao {

    //查询所有
    public List<Category> findAll();
}
