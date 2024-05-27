package com.example.employeeoffice.exception;

public class VacancyNotExistException extends RuntimeException{

    public VacancyNotExistException (String message) {

        super(message);
    }
}
