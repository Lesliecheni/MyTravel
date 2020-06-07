package com.leslie.travel.dao.impl;

import com.leslie.travel.dao.RouteDao;
import com.leslie.travel.domain.Route;
import com.leslie.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author LeslieCheni
 * @Date Created in 9:14 2020/5/23 0023
 * @Version JDK1.8
 */
public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid,String rname) {
        //String sql = "select count(*) from tab_route where cid = ?";

        //定义一个sql模板
        String sql = "select count(*) from tab_route where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();// 条件参数



        if (cid!=0){
            sb.append(" and cid = ?");
            params.add(cid);

        }

        if (rname.length()>0 && rname!=null){
            sb.append(" and rname like ?");
            params.add("%"+rname+"%");
        }

        if ("null".equals(rname)){
            sql = "select count(*) from tab_route where cid = ?";
            return template.queryForObject(sql,Integer.class,cid);
        }


        sql = sb.toString();
        //System.out.println(sql);
        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
        //String sql = "select * from tab_route where cid = ? limit ?,? ";

        String sql = "select * from tab_route where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();
        if (cid!=0){
            sb.append(" and cid = ?");
            params.add(cid);
        }

        if (rname != null && rname.length() >0){
            sb.append(" and rname like ? ");
            rname = '%'+rname+'%';
            params.add(rname);
        }

        if ("null".equals(rname)){
            sql = "select * from tab_route where cid = ? limit ?,? ";
            return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),cid,start,pageSize);
        }


        sb.append(" limit ?,? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);
        System.out.println("rname->"+rname+"start->"+start+"pageSize->"+pageSize);

        System.out.println(sql);
        List<Route> lists = template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
        for (Route list : lists) {
            System.out.println(list);
        }

        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Route.class),rid);
    }
}
