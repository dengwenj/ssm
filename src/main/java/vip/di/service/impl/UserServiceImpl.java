package vip.di.service.impl;

import vip.di.dao.UserDao;
import vip.di.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    //public void setUserDao(UserDao userDao) {
    //    this.userDao = userDao;
    //}

    @Override
    public void save() {
        System.out.println("UserServiceImpl save...");
        userDao.save();
    }
}
