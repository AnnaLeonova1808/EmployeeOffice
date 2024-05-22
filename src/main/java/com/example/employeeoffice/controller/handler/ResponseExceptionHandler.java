package com.example.employeeoffice.controller.handler;

import com.example.employeeoffice.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
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
    }
    @ExceptionHandler(EmployeeAlreadyExistException.class)
    public ResponseEntity<ErrorExtension> handleEmployeeAlreadyExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PersonalInfoNotExistException.class)
    public ResponseEntity<ErrorExtension> handlePersonalInfoNotExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(VacancyNotFoundExeption.class)
    public ResponseEntity<ErrorExtension> handleVacancyNotExistException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RoleIdNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleRoleIdNotFoundException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleRoleNotFoundException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ListOfPersonalInfoIsEmptyException.class)
    public ResponseEntity<ErrorExtension> handleListOfPersonalInfoIsEmptyException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(RuntimeException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        HttpStatus errorCode = HttpStatus.BAD_REQUEST;
        if (ex instanceof ConstraintViolationException) {
            errorMessage = ex.getMessage();
        }
        ErrorExtension errorExtension = new ErrorExtension(errorMessage, errorCode);
        return new ResponseEntity<>(errorExtension, errorCode);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorExtension> handleIllegalArgumentException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

}
