package com.example.employeeoffice.exception;

public class DepartmentWithTheSameNameAlreadyExistsException extends RuntimeException{
    public DepartmentWithTheSameNameAlreadyExistsException(String message) {

        super(message);
    }
}
