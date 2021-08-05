package com.sirius.exceptions;

public class BookNotEnoughException extends Exception{
    public BookNotEnoughException() {
        super();
    }

    public BookNotEnoughException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
