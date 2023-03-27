package com.example.demo.error;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(Long id) {
        super("Book is not found " + id);
    }
}
