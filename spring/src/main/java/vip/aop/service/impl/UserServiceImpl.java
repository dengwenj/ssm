package vip.aop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.aop.mapper.UserMapper;
import vip.aop.service.UserService;
import vip.spring_mybatis.pojo.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public User findByUsernamePassword(String username, String password) {
        return userMapper.findByUsernamePassword(username, password);
    }
}
