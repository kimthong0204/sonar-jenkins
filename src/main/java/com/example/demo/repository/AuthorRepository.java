package com.example.demo.repository;

import com.example.demo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//truy xuất thông tin từ DB.
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByAuthorName(String name);

    boolean existsByAuthorName(String authorName);
}
