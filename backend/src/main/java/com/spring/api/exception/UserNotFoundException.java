package com.spring.api.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Could not found the book with id "+ id);
    }
}
