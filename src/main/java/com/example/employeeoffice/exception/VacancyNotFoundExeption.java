package com.example.employeeoffice.exception;

public class VacancyNotFoundExeption extends RuntimeException{
    public VacancyNotFoundExeption (String message){
        super(message);
    }
}
