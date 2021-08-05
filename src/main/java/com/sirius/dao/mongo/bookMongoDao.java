package com.sirius.dao.mongo;

import com.sirius.dao.mongo.domain.BooksMongodb;
import com.sirius.domain.Book;

import java.util.ArrayList;

public interface bookMongoDao {
    // 获取用户借阅状态
    BooksMongodb getUserBorrowed(int id);
    // 借书
    void borrowBooks(int id, Book book);
    // 还书
    void returnBooks(int id, ArrayList<Book> books);
}
