package com.leslie.travel.service;

import com.leslie.travel.domain.Category;

import java.util.List;

/**
 * @Author LeslieCheni
 * @Date Created in 18:11 2020/5/22 0022
 * @Version JDK1.8
 */
public interface CategoryService {

    public List<Category> findAll();
}
