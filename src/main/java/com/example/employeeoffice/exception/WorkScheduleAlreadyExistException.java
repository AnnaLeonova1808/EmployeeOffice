package com.example.employeeoffice.exception;

public class WorkScheduleAlreadyExistException extends RuntimeException{
    public WorkScheduleAlreadyExistException(String message) {

        super(message);
    }
}
