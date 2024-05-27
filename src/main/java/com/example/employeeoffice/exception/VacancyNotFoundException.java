package com.example.employeeoffice.exception;

public class VacancyNotFoundException extends RuntimeException{
    public VacancyNotFoundException(String message){
        super(message);
    }
}
