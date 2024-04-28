package com.example.employeeoffice.controller.handler;

import com.example.employeeoffice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AddressNotExistException.class)
    public ResponseEntity<ErrorExtension> handleAddressNotExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleDepartmentNotFoundException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(EmployeeNotExistException.class)
    public ResponseEntity<ErrorExtension> handleEmployeeNotExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);

    }@ExceptionHandler(EmployeeAlreadyExistException.class)
    public ResponseEntity<ErrorExtension> handleEmployeeAlreadyExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(PersonalInfoNotExistException.class)
    public ResponseEntity<ErrorExtension> handlePersonalInfoNotExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }
}
