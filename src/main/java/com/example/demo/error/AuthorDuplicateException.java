package com.example.demo.error;

import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorDuplicateException extends RuntimeException {
    public AuthorDuplicateException(String authorName) {
        super("This author name is existed " + authorName);
    }
}
