package vip.dengwj.services;

import org.springframework.transaction.annotation.Transactional;
import vip.dengwj.domain.Book;

import java.util.List;

// 添加事务
@Transactional
public interface BookService {
    boolean addBook(Book book);

    boolean updateBook(Book book);

    boolean deleteBook(Integer id);

    Book getBookById(Integer id);

    List<Book> bookAll();
}
