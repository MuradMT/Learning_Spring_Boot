package org.example.learning_spring_boot.common.dto;

import java.time.LocalDateTime;

public class CommonDto {
    private Object obj;
    private String message;
    private LocalDateTime dateTime;

    public Object getObj() {
        return obj;
    }

    public CommonDto setObj(Object obj) {
        this.obj = obj;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public CommonDto setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}
