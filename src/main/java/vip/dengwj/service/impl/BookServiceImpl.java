package vip.dengwj.service.impl;

import vip.dengwj.dao.BookDao;
import vip.dengwj.dao.impl.BookDaoImpl;
import vip.dengwj.service.BookService;

public class BookServiceImpl implements BookService {
    private BookDao bookDao;

    @Override
    public void save() {
        System.out.println("save book service");
        bookDao.save();
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
