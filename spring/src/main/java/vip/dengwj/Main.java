package vip.dengwj;

import vip.dengwj.service.BookService;
import vip.dengwj.service.impl.BookServiceImpl;

public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookServiceImpl();
        bookService.save();
    }
}