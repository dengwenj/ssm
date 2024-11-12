package vip.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import vip.controller.dao.UserDaoImpl;

public class App {
    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
        //UserDaoImpl userDao = (UserDaoImpl) context.getBean("userDao");
        //userDao.save();
        ApplicationContext context1 = new FileSystemXmlApplicationContext("src/main/resources/applicationContext2.xml");
        // 获取 bean 的方式
        context1.getBean("userDao", UserDaoImpl.class).save();
        context1.getBean(UserDaoImpl.class).save();
    }
}
