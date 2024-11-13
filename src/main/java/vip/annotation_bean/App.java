package vip.annotation_bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vip.annotation_bean.config.SpringConfig;
import vip.annotation_bean.dao.BookDao;
import vip.annotation_bean.service.BookService;
import vip.annotation_bean.service.impl.BookServiceImpl;

import javax.sql.DataSource;

public class App {
    public static void main(String[] args) {
        //"applicationContext3.xml 配置文件，可以通过配置类替换
        // 通过 xml 配置文件的方式
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");
        //BookDao bookDao = (BookDao) context.getBean("BookDao");
        //bookDao.save();
        //BookService bookService = context.getBean(BookService.class);
        //bookService.save();

        // 通过 java 配置类的方式
        //AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        //BookDao bookDao = (BookDao) context.getBean("BookDao");
        //System.out.println(bookDao);
        //bookDao.save();
        //BookDao bookDao1 = (BookDao) context.getBean("BookDao");
        //System.out.println(bookDao1);
        //BookService bookService = context.getBean(BookService.class);
        //bookService.save();
        //context.close();

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookServiceImpl bookService = context.getBean(BookServiceImpl.class);
        bookService.save();
        DataSource dataSource = context.getBean(DataSource.class);
        System.out.println(dataSource);
    }
}
