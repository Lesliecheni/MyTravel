package com.leslie.travel.service.impl;

import com.leslie.travel.dao.RouteDao;
import com.leslie.travel.dao.RouteImgDao;
import com.leslie.travel.dao.SellerDao;
import com.leslie.travel.dao.impl.RouteDaoImpl;
import com.leslie.travel.dao.impl.RouteImgDaoImpl;
import com.leslie.travel.dao.impl.SellerDaoImpl;
import com.leslie.travel.domain.PageBean;
import com.leslie.travel.domain.Route;
import com.leslie.travel.domain.RouteImg;
import com.leslie.travel.domain.Seller;
import com.leslie.travel.service.RouteService;

import java.util.List;

/**
 * @Author LeslieCheni
 * @Date Created in 9:08 2020/5/23 0023
 * @Version JDK1.8
 */
public class RouServiceImpl implements RouteService {

    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        PageBean<Route> pb = new PageBean<>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);


        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);


        int start = (currentPage-1)*pageSize;
        List<Route> list = routeDao.findByPage(cid,start,pageSize,rname);
        System.out.println("service-->"+rname);
        //System.out.println(list);
        pb.setList(list);

        int totalPage = totalCount % pageSize ==0 ? totalCount/pageSize : (totalCount/pageSize) + 1;
        pb.setTotalPage(totalPage);
        //System.out.println(pb);
        return pb;
    }

    @Override
    public Route findOne(String rid) {
        Route route = routeDao.findOne(Integer.parseInt(rid));
        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());

        route.setRouteImgList(routeImgList);

        Seller seller = sellerDao.findById(route.getSid());

        route.setSeller(seller);


        return route;
    }
}
