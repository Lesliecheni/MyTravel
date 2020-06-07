package com.leslie.travel.dao;

import com.leslie.travel.domain.RouteImg;

import java.util.List;

/**
 * @Author LeslieCheni
 * @Date Created in 17:25 2020/5/23 0023
 * @Version JDK1.8
 */
public interface RouteImgDao {


    /**
     * 根据route的rid查询图片
     * @param rid
     * @return
     */
    public List<RouteImg> findByRid(int rid);
}
