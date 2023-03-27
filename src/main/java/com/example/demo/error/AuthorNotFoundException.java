package com.example.demo.error;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(Long id) {
        super("Author not found" + id);
    }
}
