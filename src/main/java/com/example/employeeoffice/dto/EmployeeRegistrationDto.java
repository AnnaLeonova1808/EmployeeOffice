package com.example.employeeoffice.dto;

import com.example.employeeoffice.entity.*;
import com.example.employeeoffice.entity.enums.Position;
import lombok.Value;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Value
public class EmployeeRegistrationDto {
    String firstName;
    String lastName;
    String position;
    LocalDate hireDate;
    Timestamp createdAt;
    String department;

    String birthday;
    String username;
    String phoneNumber;
    String email;
    String password;
    double salary;
    private Set<Role> roles;


}
