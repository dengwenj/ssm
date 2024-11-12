package vip.annotation_bean.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vip.annotation_bean.service.BookService;

//@Component("BookService")
// 不写 value，getBean 获取的时候通过 Class 类型获取
@Service
public class BookServiceImpl implements BookService {
    @Override
    public void save() {
        System.out.println("BookServiceImpl...");
    }
}
