package com.leslie.travel.service;

import com.leslie.travel.domain.User;

/**
 * @Author LeslieCheni
 * @Date Created in 15:47 2020/5/21 0021
 * @Version JDK1.8
 */
public interface UserService {


    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);

    boolean active(String code);

    User login(User user);
}
