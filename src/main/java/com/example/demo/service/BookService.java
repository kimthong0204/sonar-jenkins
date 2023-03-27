package com.example.demo.service;

import com.example.demo.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BookService {
    Page<Book> findAll(Pageable pageable);

    Book newBooks(@RequestBody Book newBook);

    Book findOne(@PathVariable Long Id);

    Book updateBook(@RequestBody Book updateBook, @PathVariable Long id);

    void deleteBook(@PathVariable Long id);

//    void deleteBookByAuthorId(Long authorId);

}
