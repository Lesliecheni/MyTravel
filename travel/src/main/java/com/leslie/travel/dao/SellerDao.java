package com.leslie.travel.dao;

import com.leslie.travel.domain.Seller;

/**
 * @Author LeslieCheni
 * @Date Created in 17:33 2020/5/23 0023
 * @Version JDK1.8
 */
public interface SellerDao {


    /**
     * 根据id查询
     * @param id
     * @return
     */
    public Seller findById(int id);
}
