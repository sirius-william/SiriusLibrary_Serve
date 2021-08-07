package com.sirius.controller.books;

import com.sirius.annotation.UseToken;
import com.sirius.domain.Book;
import com.sirius.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

// 管理图书借还
@RestController
@UseToken
public class BookLendController {
    @Autowired
    BookService bookService;

    @PostMapping(value = "/borrow")
    public Object borrow(String isbn, Integer id, Integer nums, @RequestParam(required = false)String name){
        if (nums == null){
            nums = 1;
        }
        HashMap<Book, Integer> books = new HashMap();
        Book newBook = new Book();
        newBook.setIsbn(isbn);
        newBook.setName(name);
        books.put(newBook, nums);
        HashMap<String, String> n = bookService.borrow(books, id);
        n.put("success", "1");
        return n;
    }
    @PostMapping(value = "/return")
    public Object returnBook(String isbn, Integer id, @RequestParam(required = false) Integer nums, @RequestParam(required = false) String name){
        if (nums == null){
            nums = 1;
        }
        HashMap<Book, Integer> books = new HashMap();
        Book newBook = new Book();
        newBook.setIsbn(isbn);
        newBook.setName(name);
        books.put(newBook, nums);
        HashMap<String, String> n = bookService.returnBook(books, id);
        n.put("success", "1");
        return n;
    }
}
