package vip.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import vip.aop.config.SpringConfig;
import vip.aop.dao.BookDao;
import vip.aop.service.UserService;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        // 用了 AOP 之后 getBean 获取 bean 用接口的类型
        BookDao bookDao = context.getBean(BookDao.class);
        //System.out.println(bookDao); // vip.aop.dao.impl.BookDaoImpl@140c9f39
        //System.out.println(bookDao.getClass()); // class com.sun.proxy.$Proxy22
        //bookDao.save();
        //bookDao.update();
        int select = bookDao.select(100);
        System.out.println(select);
    }
}
