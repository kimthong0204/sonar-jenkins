package com.example.demo.error;

public class BookDuplicateException extends RuntimeException {
    public BookDuplicateException(String name) {
        super("This book existed");
    }
}
