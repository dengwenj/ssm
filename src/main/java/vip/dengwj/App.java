package vip.dengwj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vip.bean_lifecycle.dao.BookDao;
import vip.dengwj.service.BookService;

public class App {
    public static void main(String[] args) {
        // 获取 ioc 容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取 bean 对象
        //BookService bookService = (BookService) context.getBean("bookService");
        //BookService bookService2 = (BookService) context.getBean("bookService2");
        //BookService bookService3 = (BookService) context.getBean("bookService3");
        //System.out.println(bookService);
        //System.out.println(bookService2);
        //System.out.println(bookService3);
        //bookService.save();
        //bookService2.save();
        //bookService3.save();
        //
        //System.out.println("-------------------");
        //BookDao bookDao1 = (BookDao) context.getBean("bookDao1");
        //bookDao1.save();
        //
        //System.out.println("-------------------");
        //BookDao bookDao2 = (BookDao) context.getBean("bookDao2");
        //bookDao2.save();
        //
        //System.out.println("-------------------");
        //BookDao bookDao3 = (BookDao) context.getBean("bookDao3");
        //bookDao3.save();

        BookDao lifeCycleBookDao = (BookDao) context.getBean("lifeCycleBookDao");
        lifeCycleBookDao.save();
        context.close();
    }
}
