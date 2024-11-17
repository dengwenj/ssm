package vip.bean_lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import vip.bean_lifecycle.dao.BookDao;
import vip.bean_lifecycle.service.BookService;

public class App {
    public static void main(String[] args) {
        // 获取 ioc 容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取 bean 对象

        //BookDao lifeCycleBookDao = (BookDao) context.getBean("lifeCycleBookDao");
        //lifeCycleBookDao.save();
        BookService lifeCycleBookService = (BookService) context.getBean("lifeCycleBookService");
        lifeCycleBookService.save();
        context.close();
        context.registerShutdownHook();
    }
}
