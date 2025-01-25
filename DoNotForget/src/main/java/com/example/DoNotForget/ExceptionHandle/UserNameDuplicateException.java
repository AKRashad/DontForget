package com.example.DoNotForget.ExceptionHandle;

public class UserNameDuplicateException extends  RuntimeException{
    public UserNameDuplicateException(String message) {
        super(message);
    }
}
