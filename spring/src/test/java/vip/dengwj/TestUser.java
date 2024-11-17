package vip.dengwj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vip.spring_mybatis.config.SpringConfig;
import vip.spring_mybatis.mapper.UserMapper;
import vip.spring_mybatis.pojo.User;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class TestUser {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectUser() {
        List<User> users = userMapper.selectAll();
        System.out.println("users -> " + users);
    }
}
