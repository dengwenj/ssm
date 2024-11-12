package vip.annotation_bean.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import vip.annotation_bean.dao.BookDao;

//@Component("BookDao")
@Repository("BookDao")
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("BookDaoImpl...");
    }
}
