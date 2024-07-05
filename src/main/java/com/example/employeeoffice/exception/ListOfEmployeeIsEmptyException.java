package com.example.employeeoffice.exception;

public class ListOfEmployeeIsEmptyException extends RuntimeException{
    public ListOfEmployeeIsEmptyException  (String message){
        super(message);
    }
}
