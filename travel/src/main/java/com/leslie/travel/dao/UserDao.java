package com.leslie.travel.dao;

import com.leslie.travel.domain.User;

/**
 * @Author LeslieCheni
 * @Date Created in 15:48 2020/5/21 0021
 * @Version JDK1.8
 */
public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);


    /**
     * 保存用户
     * @param user
     */
    public void save(User user);


    User findByCode(String code);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}
