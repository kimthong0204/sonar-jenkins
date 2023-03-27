package com.example.demo.repository;

import com.example.demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

//truy xuất thông tin từ DB.
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByName(String name);
}
