package com.example.employeeoffice.exception;

public class EventAlreadyExistException extends RuntimeException {
    public EventAlreadyExistException (String message) {
        super(message);
    }
}
