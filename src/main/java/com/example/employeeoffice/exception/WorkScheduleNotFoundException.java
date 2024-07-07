package com.example.employeeoffice.exception;

public class WorkScheduleNotFoundException extends RuntimeException{

    public WorkScheduleNotFoundException(String message){
        super(message);
    }
}
