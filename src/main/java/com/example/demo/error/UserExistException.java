package com.example.demo.error;

public class UserExistException extends RuntimeException{
    public UserExistException(){
        super("User is existed");
    }
}
