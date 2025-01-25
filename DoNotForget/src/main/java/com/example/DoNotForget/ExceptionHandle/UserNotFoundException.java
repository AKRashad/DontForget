package com.example.DoNotForget.ExceptionHandle;

public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
