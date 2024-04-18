package com.example.employeeoffice.exception;

public class VacancyAlreadyExistsException extends RuntimeException{
    public VacancyAlreadyExistsException  (String message){
        super(message);
    }
}
