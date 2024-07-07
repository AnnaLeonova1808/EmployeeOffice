package com.example.employeeoffice.exception;

public class ListOfVacancyIsEmptyException extends RuntimeException{
    public ListOfVacancyIsEmptyException  (String message) {
        super(message);
    }
}
