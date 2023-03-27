package com.example.demo.service.impl;

import com.example.demo.entity.Book;
import com.example.demo.error.BookDuplicateException;
import com.example.demo.error.BookNotFoundException;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book newBooks(@RequestBody Book newBook) {
        if (bookRepository.existsByName(newBook.getName())) {
            throw new BookDuplicateException(newBook.getName());
        } else {
            return bookRepository.save(newBook);
        }
    }

    @Override
    public Book findOne(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book updateBook(@RequestBody Book updateBook, @PathVariable Long id) {
        return bookRepository.findById(id).map(x -> {
            x.setName(updateBook.getName());
            x.setPrice(updateBook.getPrice());
            return bookRepository.save(x);
        }).orElseThrow(() -> {
            return new BookNotFoundException(id);
        });
    }

    @Override
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

//    @Override
//    public void deleteBookByAuthorId(Long authorId) {
//        bookRepository.deleteBookByAuthorId(authorId);
//    }
}
