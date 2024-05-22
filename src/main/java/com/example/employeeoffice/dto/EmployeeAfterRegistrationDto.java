package com.example.employeeoffice.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;
@Data
public class EmployeeAfterRegistrationDto {

    private String empId;
    private Timestamp createdAt;

    /**
     * PersonalInfo.class
     */

    private String persInfoId;
    private String username;
    private String password;

    private String roleName;
    private String employeeStatus = "EMPLOYEE CREATED";
}
