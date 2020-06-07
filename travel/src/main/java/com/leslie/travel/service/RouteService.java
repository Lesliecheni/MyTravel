package com.leslie.travel.service;

import com.leslie.travel.domain.PageBean;
import com.leslie.travel.domain.Route;

/**
 *
 * 线路Service
 * @Author LeslieCheni
 * @Date Created in 9:07 2020/5/23 0023
 * @Version JDK1.8
 */
public interface RouteService {

    //分页查询
    public PageBean<Route> pageQuery(int cid,int currentPage,int pageSize,String rname);


    //根据rid查询
    public Route findOne(String rid);
}
