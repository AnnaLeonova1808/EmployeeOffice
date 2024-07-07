package com.example.employeeoffice.exception;

public class ListOfWorkScheduleIsEmptyException extends RuntimeException{
    public ListOfWorkScheduleIsEmptyException  (String message) {
        super(message);
    }
}
