package com.ch.Service;

import com.ch.Dao.UserDao;
import com.ch.Dao.UserDaoImpl;

public class UserServiceImpl implements UserService{

    private UserDao userDao;

    //利用set进行动态实现值的注入
    @Override
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }


}
