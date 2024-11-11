package vip.dengwj.factory;

import vip.dengwj.dao.BookDao;
import vip.dengwj.dao.impl.BookDaoImpl;

public class BookDaoFactory {
    public static BookDao getBookDao() {
        return new BookDaoImpl();
    }
}
