package com.example.employeeoffice.controller.handler;

import com.example.employeeoffice.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AddressNotExistException.class)
    public ResponseEntity<ErrorExtension> handleAddressNotExistException(AddressNotExistException e) {

        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleAddressNotFoundException(AddressNotFoundException e) {

        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleDepartmentNotFoundException(DepartmentNotFoundException e) {

        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(EmployeeNotExistException.class)
    public ResponseEntity<ErrorExtension> handleEmployeeNotExistException(EmployeeNotExistException e) {

        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(EmployeeAlreadyExistException.class)
    public ResponseEntity<ErrorExtension> handleEmployeeAlreadyExistException(EmployeeAlreadyExistException e) {

        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(PersonalInfoNotExistException.class)
    public ResponseEntity<ErrorExtension> handlePersonalInfoNotExistException(PersonalInfoNotExistException e) {

        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(PersonalInfoNotFoundException.class)
    public ResponseEntity<ErrorExtension> handlePersonalInfoNotFoundException(PersonalInfoNotFoundException e) {
        ErrorExtension errorExtension = new ErrorExtension(e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(VacancyNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleVacancyNotExistException(VacancyNotFoundException e) {

        return new ResponseEntity<>(new ErrorExtension(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(VacancyAlreadyExistsException.class)
    public ResponseEntity<ErrorExtension> handleVacancyAlreadyExistsException(VacancyAlreadyExistsException e) {

        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.CONFLICT),
                HttpStatus.CONFLICT);

    }

    @ExceptionHandler(RoleIdNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleRoleIdNotFoundException(RoleIdNotFoundException e) {

        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ListOfPersonalInfoIsEmptyException.class)
    public ResponseEntity<ErrorExtension> handleListOfPersonalInfoIsEmptyException(ListOfPersonalInfoIsEmptyException e) {

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

    @ExceptionHandler(ListOfEmployeeIsEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorExtension> handleListOfEmployeeIsEmptyException(ListOfEmployeeIsEmptyException e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorExtension> handleInvalidWorkScheduleException(ResponseStatusException ex) {
        return new ResponseEntity<>(new ErrorExtension(
                ex.getReason(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

}
