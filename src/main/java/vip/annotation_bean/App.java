package vip.annotation_bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vip.annotation_bean.dao.BookDao;
import vip.annotation_bean.service.BookService;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");
        BookDao bookDao = (BookDao) context.getBean("BookDao");
        bookDao.save();
        BookService bookService = context.getBean(BookService.class);
        bookService.save();
    }
}
