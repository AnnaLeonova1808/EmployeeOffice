package com.example.employeeoffice.utils;

import com.example.employeeoffice.annotation.CreateEvent;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.Address;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.entity.Event;
import com.example.employeeoffice.entity.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ExpectedData {

    public static Address returnAddress() {
        Address address = new Address();
        address.setAddressId(UUID.fromString("b23a92eb-398c-4ba9-9680-b4b3a72a910d"));
        address.setCountry("Germany");
        address.setCity("Berlin");
        address.setStreet("Hauptstrasse");
        address.setHouseNumber("123");
        address.setApartNumber("Wohnung 101");
        address.setAddressType(AddressType.HOME);

        return address;
    }
    public static VacancyCreateDto returnVacancyCreateDto(){
        VacancyCreateDto vacancyCreateDto = new VacancyCreateDto();
        vacancyCreateDto.setPosition(Position.STOREKEEPER);
        vacancyCreateDto.setVacancyDescription("STOREKEEPER required");
        vacancyCreateDto.setVacancyRequirements("Requirements for STOREKEEPER Position");
        vacancyCreateDto.setVacancyContactInfo("contact@example.com");
        vacancyCreateDto.setDepName(String.valueOf(DepartmentName.WAREHOUSE));

        return vacancyCreateDto;
    }
    public static Event returnEventCreate() {
        Event event = new Event();
        event.setEvType(EventType.CONFERENCE);
        event.setStartDateTime(LocalDateTime.parse("2024-05-20T10:00:00"));
        event.setLocation("Conference Hall A");
        event.setDescription("Annual company conference");

        return event;
    }
    public static Employee returnEmployeeById() {
       Employee employee = new Employee();
        employee.setEmpId(UUID.fromString("7270910c-cc71-4634-97a0-a242eb5b6064"));
        employee.setFirstName("Michael");
        employee.setLastName("Johnson");
        employee.setPosition(Position.HR_MANAGER);
        employee.setHireDate(LocalDate.parse("2021-03-20"));

    return employee;
    }
    public static EmployeeRegistrationDto returnEmployeeRegistrationDto() {
        EmployeeRegistrationDto employeeRegistrationDto = new EmployeeRegistrationDto();
        employeeRegistrationDto.setFirstName("Mary");
        employeeRegistrationDto.setLastName("Brown");
        employeeRegistrationDto.setPosition(String.valueOf(Position.PROGRAMMER));
        employeeRegistrationDto.setHireDate(LocalDate.parse("2021-03-20"));
        employeeRegistrationDto.setBirthday("1988-07-07");
        employeeRegistrationDto.setDepartment(String.valueOf(DepartmentName.IT));
        employeeRegistrationDto.setEmail("mary@example.com");
        employeeRegistrationDto.setPassword("d2ecce31ab3f5a11d0f6d389f68686c75c80ead1857c7f38d21fecd4da1209b6");
        employeeRegistrationDto.setPhoneNumber("333-111-555");
        employeeRegistrationDto.setUsername("null");
        //employeeRegistrationDto.setRoles(RolesName.GUEST);

        return employeeRegistrationDto;
    }

}
