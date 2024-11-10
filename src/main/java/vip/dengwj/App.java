package vip.dengwj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import vip.dengwj.service.BookService;

public class App {
    public static void main(String[] args) {
        // 获取 ioc 容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 获取 bean 对象
        BookService bookService = (BookService) context.getBean("bookService");
        bookService.save();
    }
}
