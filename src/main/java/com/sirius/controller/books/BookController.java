/*
 * BookController
 * 描述
 @author Sirius
 */
package com.sirius.controller.books;

import com.sirius.annotation.UseToken;
import com.sirius.domain.Book;
import com.sirius.service.interfaces.BookService;
import com.sirius.service.interfaces.SecurityService;
import com.sirius.service.interfaces.UserIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@UseToken
public class BookController {
    @Autowired
    UserIdService userIdService;
    @Autowired
    BookService bookService;
    @Autowired
    SecurityService securityService;

    @RequestMapping(value = "/books/get_user_books", method = RequestMethod.POST)
    public Object getUserBooks(int id) {
        return bookService.showBookDependsOnUser(id);
    }

    @RequestMapping(value = "/books/all", method = RequestMethod.POST)
    public Object getBooks() {
        return bookService.showAllBook();
    }
    // 按条件查找书籍，书名必选

    @RequestMapping(value = "/book/search", method = RequestMethod.GET)
    public Object searchBooks(@RequestParam(required = false) Integer id, @RequestParam(required = true) String name,
                              @RequestParam(required = false) String outcome, @RequestParam(required = false)String type,
                              @RequestParam(required = false)String author, @RequestParam(required = false)String isbn,
                              @RequestParam(required = false)Integer total, @RequestParam(required = false) Integer remain,
                              @RequestParam(required = false) Integer ordered, @RequestParam(required = false) Integer price,
                              @RequestParam(required = false) String publish) {
        Book book = new Book(id, name, author, outcome, type, isbn, null, total, remain, ordered, publish, price);

        return bookService.showByOnCondition(book);
    }
}
