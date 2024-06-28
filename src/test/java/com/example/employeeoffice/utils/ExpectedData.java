package com.example.employeeoffice.utils;

import com.example.employeeoffice.annotation.CreateEvent;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.*;
import com.example.employeeoffice.entity.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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

    public static Address returnUpdateAddress() {
        Address address = new Address();
        address.setAddressId(UUID.fromString("0b751135-128c-46c9-b554-8c6e05bcd2d8"));
        address.setCountry("Germany");
        address.setCity("Hamburg");
        address.setStreet("Am Markt");
        address.setHouseNumber("789");
        address.setApartNumber("Büro 301");
        address.setAddressType(AddressType.HOME);

        return address;
    }

    public static VacancyCreateDto returnVacancyCreateDto() {
        VacancyCreateDto vacancyCreateDto = new VacancyCreateDto();
        vacancyCreateDto.setPosition(Position.PROGRAMMER);
        vacancyCreateDto.setVacancyDescription("Programmer P D");
        vacancyCreateDto.setVacancyRequirements("Requirements for Programmer Position");
        vacancyCreateDto.setVacancyContactInfo("contact@example.com");
        vacancyCreateDto.setDepName(String.valueOf(DepartmentName.IT));


        return vacancyCreateDto;
    }

    public static VacancyCreateDto returnVacancyCreateAlreadyExistsException() {
        VacancyCreateDto vacancyCreateAlreadyExistsException = new VacancyCreateDto();
        vacancyCreateAlreadyExistsException.setPosition(Position.HR_MANAGER);
        vacancyCreateAlreadyExistsException.setVacancyDescription("HR Position Description");
        vacancyCreateAlreadyExistsException.setVacancyRequirements("Requirements for HR manager Position");
        vacancyCreateAlreadyExistsException.setVacancyContactInfo("contact@example.com");
        vacancyCreateAlreadyExistsException.setDepName(String.valueOf(DepartmentName.HR));


        return vacancyCreateAlreadyExistsException;
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
        employeeRegistrationDto.setPassword("$2a$10$MByyu5H7ri30E21X4E.LT.WifbW999olodp3w6x/rjLz1cgLSEtdW");
        employeeRegistrationDto.setPhoneNumber("+12-345-678-90-18");
        employeeRegistrationDto.setUsername("null");

        return employeeRegistrationDto;
    }

    public static Department returnDepartmentByName() {
        Department department = new Department();
        department.setDepId(UUID.fromString("88a71c7e-d011-40e3-b9b5-78315c983b21"));
        department.setDepName(DepartmentName.IT);
        department.setDepPhone("+12-345-678-90-22");
        department.setDepEmail("operations@example.com");

        return department;
    }

    public static PersonalInfo returnPersonalInfo() {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setPersInfoId(UUID.fromString("1f486486-97dc-4f50-8fb1-cd87d5dd37e1"));
        personalInfo.setUsername("olivia");
        personalInfo.setBirthday(LocalDate.parse("1995-04-05"));
        personalInfo.setPhoneNumber("+12-345-678-90-15");
        personalInfo.setEmail("olivia@example.com");
        personalInfo.setPassword("$2y$10$x4S2cfdehzD62UMhIPequ.IJhSQYPKxU4s7.ZYJKSaJHY71IGrFLC");
        personalInfo.setSalary(45000.00);

        return personalInfo;
    }

    public static Set<Role> returnAllRoles() {
        Set<Role> roles = new HashSet<>();

        Role role_admin = new Role();
        role_admin.setRoleId(UUID.fromString("541eac0e-4609-47b7-8fae-bc55c44ec18d"));
        role_admin.setRoleName(RolesName.ROLE_ADMIN);
        roles.add(role_admin);

        Role role_manager = new Role();
        role_manager.setRoleId(UUID.fromString("2a4e17fe-75af-4e05-be97-0d08089f59b0"));
        role_manager.setRoleName(RolesName.ROLE_MANAGER);
        roles.add(role_manager);

        Role role_user = new Role();
        role_user.setRoleId(UUID.fromString("64d1e267-7034-4c72-989b-0e3214f264c"));
        role_user.setRoleName(RolesName.ROLE_USER);
        roles.add(role_user);

        Role role_guest = new Role();
        role_guest.setRoleId(UUID.fromString("10ffff51-6e14-4e87-8421-33f4e53f38ac"));
        role_guest.setRoleName(RolesName.ROLE_GUEST);
        roles.add(role_guest);

        return Set.of(role_admin, role_manager, role_user, role_guest);

    }

}
