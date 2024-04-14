package com.example.employeeoffice.exception;

public class EmployeeNotExistException extends RuntimeException {
    public EmployeeNotExistException(String message) {

        super(message);
    }
}
