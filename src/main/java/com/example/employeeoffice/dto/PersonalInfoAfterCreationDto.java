package com.example.employeeoffice.dto;
import com.example.employeeoffice.entity.Role;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class PersonalInfoAfterCreationDto {
    private String persInfoId;
    private String roles;
    private String PersonalInfoStatus = "PERSONAL INFO CREATED";
}
