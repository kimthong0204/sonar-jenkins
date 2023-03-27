package com.example.demo.controller;


import com.example.demo.entity.Author;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Author> findAll() {
        return authorService.findAllAuthor();
    }

    @GetMapping("/{id}")
    public Author findOne(@PathVariable Long id) {
        return authorService.findOneAuthor(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Author newAuthor(@Valid @RequestBody Author newAuthor) {
        return authorService.newAuthor(newAuthor);
    }

    @PutMapping("/{id}")
    Author updateAuthor(@PathVariable Long id, @RequestBody Author updateAuthors) {
        return authorService.Update(updateAuthors, id);
    }


    @DeleteMapping("/{id}")
    void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }


}
