/*
 * UserBooks
 * 描述
 @author Sirius
 */
package com.sirius.dao.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "user_borrowed")
public class UserBooks {
    @Id
    private Long id;
    private String userName;
    private int userId;
    private ArrayList<UserBooks> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList<UserBooks> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<UserBooks> books) {
        this.books = books;
    }
}
