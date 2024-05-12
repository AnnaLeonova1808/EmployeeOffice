package com.example.employeeoffice.dto;

import com.example.employeeoffice.entity.*;
import com.example.employeeoffice.validation.annotation.EmailChecker;
import com.example.employeeoffice.validation.annotation.PhoneNumberChecker;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Data
public class EmployeeRegistrationDto {
    String firstName;
    String lastName;
    String position;
    LocalDate hireDate;
    Timestamp createdAt;
    String department;

    String birthday;
    String username;
    @PhoneNumberChecker
    String phoneNumber;
    @EmailChecker
    String email;
    String password;
    double salary;
    private Set<Role> roles;


}
