package vip.aop.dao.impl;

import org.springframework.stereotype.Repository;
import vip.aop.dao.BookDao;

@Repository
public class BookDaoImpl implements BookDao {
    @Override
    public void save() {
        System.out.println("save book...");
    }

    @Override
    public void update() {
        System.out.println("update book...");
    }

    @Override
    public void delete() {
        System.out.println("delete book...");
    }

    @Override
    public int select(int a) {
        System.out.println("select book...");
        //int a = 10 / 0;
        return 24;
    }
}
