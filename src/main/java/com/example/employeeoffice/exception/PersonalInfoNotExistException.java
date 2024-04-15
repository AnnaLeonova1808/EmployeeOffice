package com.example.employeeoffice.exception;

public class PersonalInfoNotExistException extends RuntimeException {
    public PersonalInfoNotExistException(String message) {
        super(message);
    }
}
