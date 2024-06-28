package com.example.employeeoffice.exception;

public class PersonalInfoNotFoundException extends RuntimeException {
    public PersonalInfoNotFoundException(String message) {
        super(message);
    }
}
