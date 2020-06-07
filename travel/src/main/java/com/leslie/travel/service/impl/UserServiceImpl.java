package com.leslie.travel.service.impl;

import com.leslie.travel.dao.UserDao;
import com.leslie.travel.dao.impl.UserDaoImpl;
import com.leslie.travel.domain.User;
import com.leslie.travel.service.UserService;
import com.leslie.travel.util.MailUtils;
import com.leslie.travel.util.UuidUtil;

/**
 * @Author LeslieCheni
 * @Date Created in 15:47 2020/5/21 0021
 * @Version JDK1.8
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        User u = userDao.findByUsername(user.getUsername());

        if (u != null){
            //用户名存在
            return false;

        }

        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");

        userDao.save(user);

        String content = "<a href ='http://localhost:8080/travel/user/active?code="+user.getCode()+"'>点击激活【旅游网】</a>";
        MailUtils.sendMail(user.getEmail(),content,"激活邮件");

        return true;
    }


    /**
     * 激活用户
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        User user = userDao.findByCode(code);
        if (user!=null){

            userDao.updateStatus(user);
            return true;

        }
        return false;


    }

    /**
     * 登录方法
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());

    }
}





















