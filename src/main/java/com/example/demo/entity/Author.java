package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    @com.example.demo.error.validator.Author
    @NotEmpty(message = "Please provide a name author")
    private String authorName;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Book> books = new ArrayList<>();

}
