package com.example.employeeoffice.exception;

public class InvalidPhoneNumberException extends RuntimeException{

    public InvalidPhoneNumberException(String s) {

        super(s);
    }
}
