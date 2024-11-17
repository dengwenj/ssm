package vip.dengwj.factory;

import org.springframework.beans.factory.FactoryBean;
import vip.dengwj.dao.BookDao;
import vip.dengwj.dao.impl.BookDaoImpl;

public class BookDaoFactoryImpl implements FactoryBean<BookDao> {
    @Override
    public BookDao getObject() throws Exception {
        return new BookDaoImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return BookDao.class;
    }
}
