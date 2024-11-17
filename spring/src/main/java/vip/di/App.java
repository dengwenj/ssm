package vip.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vip.di.dao.CollectionDao;
import vip.di.service.UserService;

public class App {
    public static void main(String[] args) {
        // 获取 ioc 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = (UserService) context.getBean("userService");
        userService.save();

        System.out.println("----------------");
        CollectionDao collectionDao = (CollectionDao) context.getBean("collectionDao");
        collectionDao.save();
    }
}
