package vip.dengwj.services.Impl;

import vip.dengwj.domain.Book;

import java.util.List;

public interface BookService {
    boolean addBook(Book book);

    boolean updateBook(Book book);

    boolean deleteBook(Integer id);

    Book getBookById(Integer id);

    List<Book> bookAll();
}
