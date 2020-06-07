package com.leslie.travel.dao.impl;

import com.leslie.travel.dao.UserDao;
import com.leslie.travel.domain.User;
import com.leslie.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author LeslieCheni
 * @Date Created in 15:48 2020/5/21 0021
 * @Version JDK1.8
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    //实现根据用户名查找用户的方法
    @Override
    public User findByUsername(String username) {

        User user = null;
        try {
            String sql = "select * from tab_user where username = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {

        }


        return user;
    }



    //实现保存用户的方法
    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());

    }

    //实现根据激活码查询对象
    @Override
    public User findByCode(String code) {

        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return user;
    }


    //修改指定对象的激活状态
    @Override
    public void updateStatus(User user) {

        String sql = "update tab_user set status = 'Y' where uid = ?";
        template.update(sql,user.getUid());

    }

    //根据用户名和密码查询用户
    @Override
    public User findByUsernameAndPassword(String username, String password) {

        User user = null;
        try {
            String sql = "select * from tab_user where username =? and password = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }
}
