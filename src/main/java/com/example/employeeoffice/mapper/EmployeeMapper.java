package com.example.employeeoffice.mapper;

import com.example.employeeoffice.dto.EmployeeAfterRegistrationDto;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.generator.PasswordGenerator;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Mapper interface for converting between EmployeeRegistrationDto and Employee entities.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {Timestamp.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface EmployeeMapper {

    /**
     * Converts EmployeeRegistrationDto to Employee entity.
     *
     * @param employeeRegistrationDto the DTO containing employee registration data
     * @return the Employee entity
     */
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

    /**
     * Generates and sets PersonalInfo for the given Employee based on EmployeeRegistrationDto.
     *
     * @param employee the Employee entity to set PersonalInfo for
     * @param employeeRegistrationDto the DTO containing employee registration data
     */
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

    /**
     * Converts Employee entity to EmployeeAfterRegistrationDto.
     *
     * @param employee the Employee entity
     * @return the DTO containing employee details after registration
     */
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
