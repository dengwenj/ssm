package vip.dengwj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vip.dengwj.domain.Book;
import vip.dengwj.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public Result addBook(@RequestBody Book book) {
        boolean flag = bookService.addBook(book);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }

    @PutMapping
    public Result updateBook(@RequestBody Book book) {
        boolean flag = bookService.updateBook(book);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    @DeleteMapping("/{id}")
    public Result deleteBook(@PathVariable Integer id) {
        boolean flag = bookService.deleteBook(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @GetMapping("/{id}")
    public Result getBookById(@PathVariable Integer id) {
        Book book = bookService.getBookById(id);
        int code = book != null ? Code.GET_OK : Code.GET_ERR;
        String msg = book != null ? "查询成功" : "查询失败";
        return new Result(code, book, msg);
    }

    @GetMapping
    public Result getBooks() {
        List<Book> books = bookService.bookAll();
        int code = books != null ? Code.GET_OK : Code.GET_ERR;
        String msg = books != null ? "查询成功" : "查询失败";
        return new Result(code, books, msg);
    }
}
