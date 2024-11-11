package vip.bean_lifecycle.service.impl;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import vip.bean_lifecycle.service.BookService;

public class BookServiceImpl implements BookService, InitializingBean, DisposableBean {
    @Override
    public void save() {
        System.out.println("BookServiceImpl save...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("BookServiceImpl destroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BookServiceImpl afterPropertiesSet...");
    }
}
