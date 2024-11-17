package vip.di.service.impl;

import vip.di.dao.UserDao;
import vip.di.service.StudentService;
import vip.di.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private StudentService studentService;

    //public UserServiceImpl(UserDao userDao) {
    //    this.userDao = userDao;
    //}

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void save() {
        System.out.println("UserServiceImpl save...");
        studentService.save();
        userDao.save();
    }
}
