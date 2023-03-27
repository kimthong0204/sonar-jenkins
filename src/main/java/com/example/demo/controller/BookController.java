package com.example.demo.controller;

import com.example.demo.DTO.PageResponseDTO;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
@Validated
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ModelMapper modelMapper;

    //Find all book
    @GetMapping
    public PageResponseDTO findAll(Pageable pageable) {
        Page<Book> bookPage = bookService.findAll(pageable);
        List<Book> bookList = bookPage.stream().map(book -> modelMapper.map(book, Book.class)).collect(Collectors.toList());

        return new PageResponseDTO(bookPage.getTotalElements(), bookPage.getTotalPages(), bookPage.getSize(), bookPage.getNumber(), bookList);
    }

    //Add new books
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Book newBook(@Valid @RequestBody Book newBook) {
        return bookService.newBooks(newBook);
    }

    //Find book id
    @GetMapping("/{id}")
    Book findOne(@PathVariable @Min(1) Long id) {
        return bookService.findOne(id);
    }

    //Save or update
    @PutMapping("/{id}")
    Book saveOrUpdate(@RequestBody Book updateBook, @PathVariable Long id) {
        return bookService.updateBook(updateBook, id);
    }

    @DeleteMapping("/{id}")
    void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

}
