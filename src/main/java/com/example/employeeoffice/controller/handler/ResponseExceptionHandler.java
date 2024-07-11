package com.example.employeeoffice.controller.handler;

import com.example.employeeoffice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import org.springframework.web.server.ResponseStatusException;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(EventNotExistException.class)
    public ResponseEntity<ErrorExtension> handleEventNotExistException(EventNotExistException e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EventAlreadyExistException.class)
    public ResponseEntity<ErrorExtension> handleEventAlreadyExistException(EventAlreadyExistException e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((DefaultMessageSourceResolvable) error).getDefaultMessage();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = ((DefaultMessageSourceResolvable) violation.getPropertyPath()).getDefaultMessage();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorExtension> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

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

    @ExceptionHandler(DepartmentAlreadyExistException.class)
    public ResponseEntity<ErrorExtension> handleDepartmentAlreadyExistException(DepartmentAlreadyExistException e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ListOfDepartmentIsEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, Object>> handleListOfDepartmentIsEmptyException(ListOfDepartmentIsEmptyException e) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", e.getMessage());
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WorkScheduleNotFoundException.class)
    public ResponseEntity<ErrorExtension> handleWorkScheduleNotFoundException(WorkScheduleNotFoundException e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WorkScheduleAlreadyExistException.class)
    public ResponseEntity<ErrorExtension> handleWorkScheduleAlreadyExistException(WorkScheduleAlreadyExistException e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ListOfWorkScheduleIsEmptyException.class)
    ResponseEntity<Map<String, Object>> handleListOfWorkScheduleIsEmptyException(ListOfWorkScheduleIsEmptyException e) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", e.getMessage());
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
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

    @ExceptionHandler(ListOfEmployeeIsEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, Object>> handleListOfEmployeeIsEmptyException(ListOfEmployeeIsEmptyException e) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", e.getMessage());
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ListOfEventIsEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, Object>> handleListOfEventIsEmptyException(ListOfEventIsEmptyException e) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", e.getMessage());
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
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
    @ExceptionHandler(ListOfVacancyIsEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorExtension> handleListOfVacancyIsEmptyException(ListOfVacancyIsEmptyException e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(), HttpStatus.NOT_FOUND),
                HttpStatus.NOT_FOUND);
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

    @ExceptionHandler(ListOfAuthorityIsEmptyException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Map<String, Object>> handleListOfAuthorityIsEmptyException(ListOfAuthorityIsEmptyException e) {
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("message", e.getMessage());
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorExtension> handleInvalidWorkScheduleException(ResponseStatusException ex) {
        return new ResponseEntity<>(new ErrorExtension(
                ex.getReason(), HttpStatus.BAD_REQUEST),
                HttpStatus.BAD_REQUEST);
    }
}
