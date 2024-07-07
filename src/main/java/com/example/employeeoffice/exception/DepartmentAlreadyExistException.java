package com.example.employeeoffice.exception;

public class DepartmentAlreadyExistException extends RuntimeException{
    public DepartmentAlreadyExistException(String message) {

        super(message);
    }
}
