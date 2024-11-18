package vip.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vip.aop.config.SpringConfig;
import vip.aop.dao.BookDao;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        // 用了 AOP 之后 getBean 获取 bean 用接口的类型
        BookDao bookDao = context.getBean(BookDao.class);
        bookDao.save();
        bookDao.update();
    }
}
