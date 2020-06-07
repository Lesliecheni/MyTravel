package com.leslie.travel.service.impl;

import com.leslie.travel.dao.CategoryDao;
import com.leslie.travel.dao.impl.CategoryDaoImpl;
import com.leslie.travel.domain.Category;
import com.leslie.travel.service.CategoryService;
import com.leslie.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author LeslieCheni
 * @Date Created in 18:11 2020/5/22 0022
 * @Version JDK1.8
 */
public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {

        //通过缓存优化
        Jedis jedis = JedisUtil.getJedis();

        //Set<String> categorys = jedis.zrange("category", 0, -1);

        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);

        List<Category> cs = null;

        if (categorys==null || categorys.size() == 0){

           // System.out.println("从数据库查询");
           cs = categoryDao.findAll();

            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }

        }else {
           // System.out.println("从redis获取");
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }


        return cs;
    }
}
