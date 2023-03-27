package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtReponseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private final String token;
}
