package vip.di.dao.impl;

import vip.di.dao.UserDao;

public class UserDaoImpl implements UserDao {
    private String name;
    private Integer age;

    public UserDaoImpl(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public void save() {
        System.out.println("UserDaoImpl save..." + name + age);
    }
}
