package com.example.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PageResponseDTO<T> {

    private Long totalElements;
    private int totalPages;
    private int size;
    private int number;

    private List<T> data;
}
