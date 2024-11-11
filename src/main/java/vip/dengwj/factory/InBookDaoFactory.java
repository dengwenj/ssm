package vip.dengwj.factory;

import vip.dengwj.dao.BookDao;
import vip.dengwj.dao.impl.BookDaoImpl;

public class InBookDaoFactory {
    public BookDao getBookDao() {
        return new BookDaoImpl();
    }
}
