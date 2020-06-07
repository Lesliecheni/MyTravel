package com.leslie.travel.dao;

import com.leslie.travel.domain.Route;

import java.util.List;

/**
 * @Author LeslieCheni
 * @Date Created in 9:11 2020/5/23 0023
 * @Version JDK1.8
 */
public interface RouteDao {

    /**
     * 根据cid查询总记录数
     * @param cid
     * @return
     */
    public int findTotalCount(int cid,String rname);


    /**
     * 根据cid，start，pageSize查询当前页的数据集合
     * @param cid
     * @param start
     * @param pageSize
     * @return
     */
    public List<Route> findByPage(int cid,int start,int pageSize,String rname);

    //根据id查询
    public Route findOne(int rid);
}


