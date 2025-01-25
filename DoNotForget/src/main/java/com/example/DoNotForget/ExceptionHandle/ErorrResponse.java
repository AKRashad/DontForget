package com.example.DoNotForget.ExceptionHandle;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ErorrResponse {

    private  Boolean success;
    private  String message;
    private LocalDateTime dateTime;

    public ErorrResponse(String message) {
        this.message = message;
        this.success = Boolean.FALSE;
        this.dateTime = LocalDateTime.now();
    }

    public ErorrResponse() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
