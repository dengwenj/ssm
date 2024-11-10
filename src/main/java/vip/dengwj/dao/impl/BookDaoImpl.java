package vip.dengwj.dao.impl;

import vip.dengwj.dao.BookDao;

public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("保存 book dao");
    }
}
