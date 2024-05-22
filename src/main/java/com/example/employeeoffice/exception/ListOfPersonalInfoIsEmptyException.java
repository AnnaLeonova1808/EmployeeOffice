package com.example.employeeoffice.exception;

public class ListOfPersonalInfoIsEmptyException extends RuntimeException{
    public ListOfPersonalInfoIsEmptyException  (String message){
        super(message);
    }
}
