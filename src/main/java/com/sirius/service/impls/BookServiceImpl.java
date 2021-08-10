/*
 * BookServiceImpl
 * 描述
 @author Sirius
 */
package com.sirius.service.impls;

import com.sirius.dao.mysql.BookDao;
import com.sirius.dao.mysql.BorrowDao;
import com.sirius.domain.Book;
import com.sirius.domain.BorrowInfo;
import com.sirius.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private BorrowDao borrowDao;
    public BookServiceImpl() {
    }

    //借还书书功能操作方法，目前仅有mysql，待完善

    @Override
    public HashMap<String, String> borrow(HashMap<Book, Integer> books, int userid) {
        HashMap<String, String> res = new HashMap<>();
        for (Book book: books.keySet()){
            int remain = bookDao.selectBookRemain(book.getIsbn());
            int num = books.get(book);
            if (num > remain || remain <= 0){
                res.put(book.getIsbn(), "not enough");
            }else if (num <= remain){
                int d = bookDao.updateRemain(book, num, "reduce");
                BorrowInfo info = new BorrowInfo();
                info.setIsbn(book.getIsbn());
                info.setUserId(userid);
                Date b_date = new Date();
                Date r_date = new Date(b_date.getTime() + 60L * 60 * 24 * 1000 * 30);
                info.setB_time(b_date);
                info.setR_time(r_date);
                borrowDao.insertBorrowBook(info, userid);
                res.put(book.getIsbn(), String.valueOf(d));
            }
        }
        return res;
    }

    @Override
    public HashMap<String, String> returnBook(HashMap<Book, Integer> books, int userid) {
        HashMap<String, String> res = new HashMap<>();
        for (Book book: books.keySet()){
            int num = books.get(book);
            int d = bookDao.updateRemain(book, num, "add");
            BorrowInfo info = new BorrowInfo();
            info.setIsbn(book.getIsbn());
            info.setUserId(userid);
            borrowDao.deleteBorrowBook(info, userid);
            res.put(book.getIsbn(), String.valueOf(d));
        }
        return res;
    }


    @Override
    public ArrayList<Book> showBookInfo(String bookName) {
        if (bookName != null) {
            return bookDao.selectBookByName(bookName);
        }
        return null;
    }

    @Override
    public Book showBookByISBN(String isbn) {
        if (isbn != null) {
            return bookDao.selectBookByISBN(isbn);
        }
        return null;
    }

    @Override
    public ArrayList<Book> showByOnCondition(Book book) {
        if (book != null) {
            return bookDao.selectBookByOtherCondition(book);
        }
        return null;
    }

    @Override
    public ArrayList<Book> showBooksByAuthor(String author) {
        if (author != null) {
            return bookDao.selectBookByAuthor(author);
        }
        return null;
    }

    @Override
    public ArrayList<Book> showBooksByTime(Date left, Date right) {
        if (left != null && right != null) {
            return bookDao.selectBookByPublishTimeSection(left, right);
        }
        return null;
    }

    @Override
    public ArrayList<Book> showAllBook() {
        return bookDao.selectAllBook();
    }

    @Override
    public ArrayList<Book> showBooksByNameAndAuthor(String name, String author) {
        if (name != null && author != null) {
            ArrayList<Book> books = bookDao.selectBookByNameAndAuthor(name, author);
            return books;
        }
        return null;
    }

    @Override
    public ArrayList<Book> showBooksByType(String type) {
        if (type != null) {
            ArrayList<Book> books = bookDao.selectBookByType(type);
            return books;
        }
        return null;
    }

    @Override
    public Book showBookInDetail(Book book) {
        Book _book = bookDao.selectBook(book);
        return _book;
    }

    @Override
    public Boolean changeISBN(String oldIsbn, String newIsbn) {
        if (oldIsbn != null || newIsbn != null) {
            int check = bookDao.updateBookByISBN(oldIsbn, newIsbn);
            return check == 1;
        }
        return false;
    }

    @Override
    public Boolean changeId(Integer oldId, Integer newId) {
        try {
            int check = bookDao.updateBookById(oldId, newId);
            return check == 1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<Book> showBookDependsOnUser(Integer id) {
        // 基于mongodb
        return null;
    }

    @Override
    public Boolean changeBook(Book oldBook, Book newBook) {
        if (oldBook == null || newBook == null) {
            return false;
        }
        int check = bookDao.updateBook(oldBook, newBook);
        return check == 1;
    }

    @Override
    public ArrayList<Book> showBookNearByTimeout(Integer id) {
        return null;
    }

    @Override
    public ArrayList<Book> showBooksAlreadyTimeout(Integer id) {
        return null;
    }

    @Override
    public Boolean addBook(Book book) {
        if (book != null) {
            int check = bookDao.insertBook(book);
            return check == 1;
        }
        return false;
    }

    @Override
    public Boolean addBookNums(Book book, Integer nums) {
        if (book != null && nums != null) {
            int check = bookDao.updateTotal(book, nums);
            return check == 1;
        }
        return false;
    }
}
