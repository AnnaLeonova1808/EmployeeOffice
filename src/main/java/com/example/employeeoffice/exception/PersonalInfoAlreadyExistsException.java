package com.example.employeeoffice.exception;

public class PersonalInfoAlreadyExistsException extends RuntimeException{
    public PersonalInfoAlreadyExistsException  (String message){
        super(message);
    }
}
