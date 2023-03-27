package com.example.demo.service;

import com.example.demo.entity.Author;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AuthorService {

    List<Author> findAllAuthor();

    Author newAuthor(@RequestBody Author newAuthors);


    Author findOneAuthor(@PathVariable Long id);


    Author Update(@RequestBody Author updateAuthor, @PathVariable Long id);

    void deleteAuthor(@PathVariable Long id);

}
