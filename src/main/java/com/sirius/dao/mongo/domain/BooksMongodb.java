/*
 * BooksMongodb
 * 描述
 @author Sirius
 */
package com.sirius.dao.mongo.domain;

import java.sql.Time;

public class BooksMongodb {
    private int id;
    private Time borrowTime;
    private int nums;

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Time borrowTime) {
        this.borrowTime = borrowTime;
    }
}
