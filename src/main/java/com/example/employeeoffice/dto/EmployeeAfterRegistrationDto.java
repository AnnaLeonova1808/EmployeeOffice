package com.example.employeeoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;
/**
 * Data Transfer Object for employee details after registration.
 */
@Data
@Schema(description = "Details of an employee after registration")
public class EmployeeAfterRegistrationDto {

    /**
     * Employee.class
     */

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
