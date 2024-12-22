package vip.dengwj.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.dengwj.controller.Code;
import vip.dengwj.domain.Book;
import vip.dengwj.exception.BusinessException;
import vip.dengwj.exception.SystemException;
import vip.dengwj.mapper.BookMapper;
import vip.dengwj.services.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public boolean addBook(Book book) {
        bookMapper.addBook(book);
        return true;
    }

    @Override
    public boolean updateBook(Book book) {
        bookMapper.updateBook(book);
        return true;
    }

    @Override
    public boolean deleteBook(Integer id) {
        bookMapper.deleteBook(id);
        return true;
    }

    @Override
    public Book getBookById(Integer id) {
        if (id == 1) {
            throw new BusinessException(Code.BUSINESS_ERR, "业务异常");
        }

        try {
            int i = 1 / 0;
        } catch (Exception e) {
            throw new SystemException(Code.SYSTEM_ERR, "系统异常");
        }
        return bookMapper.getBookById(id);
    }

    @Override
    public List<Book> bookAll() {
        System.out.println("bookAll...");
        return bookMapper.bookAll();
    }
}
