package com.example.employeeoffice.controller.handler;

import lombok.Value;
import org.springframework.http.HttpStatus;
@Value
public class ErrorExtension {
    String message;
    HttpStatus errorCode;
}