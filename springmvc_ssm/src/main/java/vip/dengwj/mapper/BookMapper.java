package vip.dengwj.mapper;

import org.apache.ibatis.annotations.Mapper;
import vip.dengwj.domain.Book;

import java.util.List;

@Mapper
public interface BookMapper {
    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(Integer id);

    Book getBookById(Integer id);

    List<Book> bookAll();
}
