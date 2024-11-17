package vip.spring_mybatis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vip.spring_mybatis.config.SpringConfig;
import vip.spring_mybatis.mapper.UserMapper;
import vip.spring_mybatis.pojo.User;

import java.util.List;

public class App2 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserMapper userMapper = context.getBean(UserMapper.class);
        List<User> users = userMapper.selectAll();
        System.out.println(users);
    }
}
