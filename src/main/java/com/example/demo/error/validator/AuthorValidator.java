package com.example.demo.error.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class AuthorValidator implements ConstraintValidator<Author, String> {
    List<String> authors = Arrays.asList("Cury", "Marine", "Thong", "Luong", "Hai");

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {

        return authors.contains(s);
    }
}
