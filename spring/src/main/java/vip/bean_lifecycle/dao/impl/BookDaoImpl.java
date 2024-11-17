package vip.bean_lifecycle.dao.impl;

import vip.bean_lifecycle.dao.BookDao;

public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("BookDaoImpl save...");
    }

    public void init() {
        System.out.println("BookDaoImpl init...");
    }

    public void destroy() {
        System.out.println("BookDaoImpl destroy...");
    }
}
