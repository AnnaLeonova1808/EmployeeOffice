package com.example.employeeoffice.exception;

public class InvalidEmailException extends RuntimeException{

    public InvalidEmailException(String s) {
        super(s);
    }
}
