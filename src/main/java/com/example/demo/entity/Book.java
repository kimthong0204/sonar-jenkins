package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "book") //tên này trùng với tên table trong database
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NotEmpty(message = "Please provide a name")
    private String name;
    @NotNull(message = "Please provide a price of book")
    @DecimalMin("1.00")
    private BigDecimal price;

    @ManyToMany
    // Jointable chỉ rõ bảng trung gian của thuộc tính name, thuộc tính JoinColums chỉ rõ column mapping
    // thuộc tính inverseJoinColums để chỉ rõ column của bảng còn lại.
    @JoinTable(name = "books_authors", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "authorId"))
    private List<Author> authors = new ArrayList<>();
}
