package com.gusrubin.lab.jpa.utils;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TestAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
    public Object exceptionHandler(Exception e) {
        return new HttpEntity<>(e.getMessage());
    }
}