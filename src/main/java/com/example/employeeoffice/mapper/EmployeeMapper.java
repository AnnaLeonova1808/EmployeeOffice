package com.example.employeeoffice.mapper;

import com.example.employeeoffice.dto.EmployeeAfterRegistrationDto;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.entity.enums.RolesName;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.exception.RoleNotFoundException;
import com.example.employeeoffice.generator.PasswordGenerator;
import com.example.employeeoffice.mapper.util.DateFormatterUtil;
import com.example.employeeoffice.mapper.util.UserDataGeneratorUtil;
import jakarta.persistence.PrePersist;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {Timestamp.class, DateFormatterUtil.class, UserDataGeneratorUtil.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface EmployeeMapper {
    @Mappings({
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "position", source = "position"),
            @Mapping(target = "hireDate", source = "hireDate"),
            @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))"),
            @Mapping(target = "persInfo", source = "employeeRegistrationDto"),
            @Mapping(target = "persInfo.password", ignore = true),
            @Mapping(target = "persInfo.username", ignore = true),
            @Mapping(target = "empId", ignore = true),
            @Mapping(target = "termDate", ignore = true),
            @Mapping(target = "workPlaceLocation", ignore = true),
            @Mapping(target = "statusEmp", ignore = true),
            @Mapping(target = "vacPlan", ignore = true),
            @Mapping(target = "department", ignore = true),
            @Mapping(target = "depManager", ignore = true),
            @Mapping(target = "managedDepartment", ignore = true),
            @Mapping(target = "workSchedule", ignore = true),
            @Mapping(target = "vacations", ignore = true),
            @Mapping(target = "substitutedVacations", ignore = true),
            @Mapping(target = "events", ignore = true),
    })
    Employee toEntity(EmployeeRegistrationDto employeeRegistrationDto);

    @AfterMapping
       default void generatePersonalInfo(@MappingTarget Employee employee, EmployeeRegistrationDto employeeRegistrationDto) {
        PersonalInfo personalInfo = new PersonalInfo();

        personalInfo.setBirthday(LocalDate.parse(employeeRegistrationDto.getBirthday()));
        personalInfo.setUsername((employeeRegistrationDto.getUsername()));
        personalInfo.setPhoneNumber(employeeRegistrationDto.getPhoneNumber());
        personalInfo.setEmail(employeeRegistrationDto.getEmail());
        personalInfo.setPassword(PasswordGenerator.generatePasswordBasedOnUUID());
        personalInfo.setSalary(employeeRegistrationDto.getSalary());

        employee.setPersInfo(personalInfo);
    }

    @Mappings({

            @Mapping(target = "empId", source = "empId"),
            @Mapping(target = "persInfoId", source = "persInfo.persInfoId"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "username", source = "persInfo.username"),
            @Mapping(target = "password", source = "persInfo.password"),
            @Mapping(target = "roleName", constant = "USER")

    })
    EmployeeAfterRegistrationDto toDto(Employee employee);

}
