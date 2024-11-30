package vip.dengwj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.aop.config.SpringConfig;
import vip.aop.mapper.UserMapper;
import vip.aop.service.UserService;
import vip.spring_mybatis.pojo.User;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TestUser {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    public void testSelectUser() {
        List<User> users = userMapper.findAll();
        System.out.println("users -> " + users);
    }

    @Test
    public void testGetUserByUsernamePassword() {
        User user = userService.findByUsernamePassword("朴睦", "123456 ");
        System.out.println("user -> " + user);
    }
}
