package com.example.employeeoffice.controller.handler;

import org.springframework.http.HttpStatus;
import lombok.Value;

@Value
public class ErrorExtension {
    String message;
    String errorCode;

    public ErrorExtension(String message, HttpStatus errorCode) {
        this.message = message;
        this.errorCode = errorCode.toString();
    }
}
