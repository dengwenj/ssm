package vip.annotation_bean.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vip.annotation_bean.dao.BookDao;
import vip.annotation_bean.dao.impl.BookDaoImpl;
import vip.annotation_bean.service.BookService;

import java.util.Random;

//@Component("BookService")
// 不写 value，getBean 获取的时候通过 Class 类型获取
@Service
//@Import({BookDaoImpl.class})
public class BookServiceImpl implements BookService {
    @Autowired
    @Qualifier("BookDao") // 指定
    private BookDao bookDao;

    @Override
    public void save() {
        System.out.println("BookServiceImpl...");
        bookDao.save();
    }

    @Bean
    public Random getRandom() {
        return new Random();
    }
}
