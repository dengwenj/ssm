package vip.annotation_bean.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vip.annotation_bean.dao.BookDao;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component("BookDao")
@Repository("BookDao")
//@Scope("prototype")
@Scope("singleton")
public class BookDaoImpl implements BookDao {
    @Value("${jdbc.username}")
    private String name;

    @Override
    public void save() {
        System.out.println("BookDaoImpl..." + name);
    }

    @PostConstruct
    public void init() {
        System.out.println("BookDaoImpl init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("BookDaoImpl.destroy...");
    }
}
