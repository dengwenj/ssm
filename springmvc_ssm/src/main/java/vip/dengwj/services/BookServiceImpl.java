package vip.dengwj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.dengwj.domain.Book;
import vip.dengwj.mapper.BookMapper;
import vip.dengwj.services.Impl.BookService;

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
        return bookMapper.getBookById(id);
    }

    @Override
    public List<Book> bookAll() {
        return bookMapper.bookAll();
    }
}
