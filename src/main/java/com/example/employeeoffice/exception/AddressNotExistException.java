package com.example.employeeoffice.exception;

public class AddressNotExistException extends RuntimeException {
    public AddressNotExistException(String message) {
        super(message);
    }
}
