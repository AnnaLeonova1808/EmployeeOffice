package com.example.employeeoffice.dto;

import com.example.employeeoffice.entity.*;
import com.example.employeeoffice.validation.annotation.EmailChecker;
import com.example.employeeoffice.validation.annotation.PhoneNumberChecker;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

@Data
@Validated
public class EmployeeRegistrationDto {
    /**
     * Employee.class
     */
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    String firstName;
    @NotEmpty(message = "Lastname should not be empty")
    @Size(min = 2, max = 30, message = "Lastname should be between 2 and 30 characters")
    String lastName;
    String position;
    LocalDate hireDate;
    Timestamp createdAt;
    String department;

    /**
     * PersonalInfo.class
     */

    String birthday;
    String username;
    //@PhoneNumberChecker
    String phoneNumber;
    @EmailChecker
    String email;
    String password;

    @Min(value = 0, message = "Salary should be greater than 0")
    double salary;
    private Set<Role> roles;


}
