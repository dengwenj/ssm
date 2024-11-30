package vip.aop.service;

import vip.spring_mybatis.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByUsernamePassword(String username, String password);
}
