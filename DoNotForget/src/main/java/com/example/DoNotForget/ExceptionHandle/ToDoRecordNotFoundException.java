package com.example.DoNotForget.ExceptionHandle;

public class ToDoRecordNotFoundException extends  RuntimeException{
    public ToDoRecordNotFoundException(String message) {
        super(message);
    }
}
