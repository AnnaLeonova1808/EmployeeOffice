package com.example.employeeoffice.exception;

public class EventNotExistException extends RuntimeException {
    public EventNotExistException (String message) {
        super(message);
    }
}
