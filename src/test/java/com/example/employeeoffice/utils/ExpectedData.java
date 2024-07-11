package com.example.employeeoffice.utils;

import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.*;
import com.example.employeeoffice.entity.enums.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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

    public static Set<Authority> returnAllAuthorities() {
        Set<Authority> authorities = new HashSet<>();

        Authority authorities_READ_DOCUMENT = new Authority();
        authorities_READ_DOCUMENT.setAuthId(UUID.fromString("fcbf07ae-7d86-4a16-935a-36ee7ac89b02"));
        authorities_READ_DOCUMENT.setAuthority(AuthorityName.READ_DOCUMENT);
        authorities.add(authorities_READ_DOCUMENT);

        Authority authorities_CREATE_DOCUMENT = new Authority();
        authorities_CREATE_DOCUMENT.setAuthId(UUID.fromString("cfd9f19b-c9e2-4953-ac31-c61803c0baa3"));
        authorities_CREATE_DOCUMENT.setAuthority(AuthorityName.CREATE_DOCUMENT);
        authorities.add(authorities_CREATE_DOCUMENT);

        Authority authorities_DELETE_DOCUMENT = new Authority();
        authorities_DELETE_DOCUMENT.setAuthId(UUID.fromString("a1a62aae-38fa-4900-b392-c2ce5afbb5da"));
        authorities_DELETE_DOCUMENT.setAuthority(AuthorityName.DELETE_DOCUMENT);
        authorities.add(authorities_DELETE_DOCUMENT);

        Authority authorities_UPDATE_DOCUMENT = new Authority();
        authorities_UPDATE_DOCUMENT.setAuthId(UUID.fromString("16001a26-ce5f-423a-afde-4bea256cec90"));
        authorities_UPDATE_DOCUMENT.setAuthority(AuthorityName.UPDATE_DOCUMENT);
        authorities.add(authorities_UPDATE_DOCUMENT);

        return authorities;
    }
    public static Set<Department> returnAllDepartments() {
        Set<Department> departments = new HashSet<>();

        Department department_1 = new Department();
        department_1.setDepId(UUID.fromString("ef6869b7-2402-48c7-bff4-141563be2d8c"));
        department_1.setDepName(DepartmentName.HR);
        department_1.setDepPhone("+12-345-678-90-24");
        department_1.setDepEmail("hr@example.com");
        departments.add(department_1);

        Department department_2 = new Department();
        department_2.setDepId(UUID.fromString("3c004a2b-3ff3-4413-8ce3-e72ec557b6fc"));
        department_2.setDepName(DepartmentName.WAREHOUSE);
        department_2.setDepPhone("+12-345-678-90-25");
        department_2.setDepEmail("warehouse@example.com");
        departments.add(department_2);

        Department department_3 = new Department();
        department_3.setDepId(UUID.fromString("f80a04a4-8015-41b7-8458-8ca40416b4a3"));
        department_3.setDepName(DepartmentName.FINANCE);
        department_3.setDepPhone("+12-345-678-90-26");
        department_3.setDepEmail("finance@example.com");
        departments.add(department_3);

        Department department_4 = new Department();
        department_4.setDepId(UUID.fromString("88a71c7e-d011-40e3-b9b5-78315c983b21"));
        department_4.setDepName(DepartmentName.IT);
        department_4.setDepPhone("+12-345-678-90-22");
        department_4.setDepEmail("operations@example.com");
        departments.add(department_4);

        Department department_5 = new Department();
        department_5.setDepId(UUID.fromString("2e88a78d-b4a7-4a00-b590-4d0f7abe6c04"));
        department_5.setDepName(DepartmentName.SALES);
        department_5.setDepPhone("+12-345-678-90-23");
        department_5.setDepEmail("sales@example.com");
        departments.add(department_5);

        return departments;
    }
    public static Set<Employee> returnAllEmployees() {
        Set<Employee> employees = new HashSet<>();

        Employee employee_1 = new Employee();
        employee_1.setEmpId(UUID.fromString("55035fe9-37e3-466f-ba4a-197f23fc5700"));
        employee_1.setFirstName("Daniel");
        employee_1.setLastName("Brown");
        employee_1.setPosition(Position.valueOf("SALES_MANAGER"));
        employee_1.setStatusEmp(StatusEmployee.valueOf("VACATION"));
        employees.add(employee_1);

        Employee employee_2 = new Employee();
        employee_2.setEmpId(UUID.fromString("7270910c-cc71-4634-97a0-a242eb5b6064"));
        employee_2.setFirstName("Michael");
        employee_2.setLastName("Johnson");
        employee_2.setPosition(Position.valueOf("HR_MANAGER"));
        employee_2.setStatusEmp(StatusEmployee.valueOf("WORK"));
        employees.add(employee_2);

        return employees;
    }
    public static Set<Event> returnAllEvents() {
        Set<Event> events = new HashSet<>();

        Event event_1 = new Event();
        event_1.setEvId(UUID.fromString("92683b96-579e-4fee-9329-b442639582e7"));
        event_1.setEvType(EventType.valueOf("CONFERENCE"));
        events.add(event_1);

        Event event_2 = new Event();
        event_2.setEvId(UUID.fromString("d4379c09-f871-45a1-93b4-a52d9f91ac57"));
        event_2.setEvType(EventType.valueOf("SEMINAR"));
        events.add(event_2);


        return events;
    }
    public static Set<Vacancy> returnAllVacancies() {
        Set<Vacancy> vacancies = new HashSet<>();

        Vacancy vacancy_1 = new Vacancy();
        vacancy_1.setVacancyId(UUID.fromString("51b02a7e-e57c-4321-ba34-73d59bfbddec"));
        vacancy_1.setPosition(Position.PROGRAMMER);
        vacancy_1.setVacancyDescription("Receptionist Position Description");
        vacancy_1.setVacancyRequirements("Requirements for Receptionist Position");
        vacancy_1.setVacancySalary(Double.parseDouble("48000.00"));
        vacancy_1.setWorkplaceLocation(WorkPlaceLocation.OFFICE);
        vacancy_1.setVacancyStatus(VacancyStatus.valueOf("ACTIVE"));
        vacancy_1.setVacancyContactInfo("contact@example.com");
        vacancies.add(vacancy_1);

        Vacancy vacancy_2 = new Vacancy();
        vacancy_2.setVacancyId(UUID.fromString("5c2f14e9-e9f2-4e67-9911-2c3bed21b74d"));
        vacancy_2.setPosition(Position.DRIVER);
        vacancy_2.setVacancyDescription("Driver Position Description");
        vacancy_2.setVacancyRequirements("Requirements for Driver Position");
        vacancy_2.setVacancySalary(Double.parseDouble("55000.00"));
        vacancy_2.setWorkplaceLocation(WorkPlaceLocation.WAREHOUSE);
        vacancy_2.setVacancyStatus(VacancyStatus.valueOf("ACTIVE"));
        vacancy_2.setVacancyContactInfo("contact@example.com");
        vacancies.add(vacancy_2);

        Vacancy vacancy_3 = new Vacancy();
        vacancy_3.setVacancyId(UUID.fromString("6f8fdfd4-419c-46a7-9ba2-33a32723cd6c"));
        vacancy_3.setPosition(Position.SALES_MANAGER);
        vacancy_3.setVacancyDescription("Analyst Position Description");
        vacancy_3.setVacancyRequirements("Requirements for Analyst Position");
        vacancy_3.setVacancySalary(Double.parseDouble("75000.00"));
        vacancy_3.setWorkplaceLocation(WorkPlaceLocation.SALES_AGENT_ROUTE);
        vacancy_3.setVacancyStatus(VacancyStatus.valueOf("ACTIVE"));
        vacancy_3.setVacancyContactInfo("contact@example.com");
        vacancies.add(vacancy_3);

        Vacancy vacancy_4 = new Vacancy();
        vacancy_4.setVacancyId(UUID.fromString("aa51c3f9-0329-4633-9811-882bd21ec67b"));
        vacancy_4.setPosition(Position.ECONOMIST);
        vacancy_4.setVacancyDescription("Analyst Position Description");
        vacancy_4.setVacancyRequirements("Requirements for Security Guard Position");
        vacancy_4.setVacancySalary(Double.parseDouble("60000.00"));
        vacancy_4.setWorkplaceLocation(WorkPlaceLocation.OFFICE);
        vacancy_4.setVacancyStatus(VacancyStatus.valueOf("ACTIVE"));
        vacancy_4.setVacancyContactInfo("contact@example.com");
        vacancies.add(vacancy_4);

        Vacancy vacancy_5 = new Vacancy();
        vacancy_5.setVacancyId(UUID.fromString("efff9467-a80e-447d-8763-ee7acfa5c29c"));
        vacancy_5.setPosition(Position.HR_MANAGER);
        vacancy_5.setVacancyDescription("HR Position Description");
        vacancy_5.setVacancyRequirements("Requirements for HR manager Position");
        vacancy_5.setVacancySalary(Double.parseDouble("90000.00"));
        vacancy_5.setWorkplaceLocation(WorkPlaceLocation.OFFICE);
        vacancy_5.setVacancyStatus(VacancyStatus.valueOf("ACTIVE"));
        vacancy_5.setVacancyContactInfo("contact@example.com");
        vacancies.add(vacancy_5);

        return vacancies;
    }

    public static WorkSchedule returnWorkScheduleByName() {
        WorkSchedule workSchedule = new WorkSchedule();
        workSchedule.setSchedId(UUID.fromString("b6892135-bc87-427b-b23e-be0ccafecb7e"));
        workSchedule.setSchedName(WorkScheduleName.SHIFT_WORK);
        workSchedule.setStartTime(LocalTime.parse("06:00:00"));
        workSchedule.setEndTime(LocalTime.parse("18:00:00"));

        return workSchedule;
    }

    public static Set<WorkSchedule> returnAllWorkSchedules() {
        Set<WorkSchedule> workSchedules = new HashSet<>();

        WorkSchedule workSchedule_1 = new WorkSchedule();
        workSchedule_1.setSchedId(UUID.fromString("94752fc8-87e8-4cf6-ab17-f91d1720c20d"));
        workSchedule_1.setSchedName(WorkScheduleName.FIVE_DAY_SHIFT_09_18);
        workSchedules.add(workSchedule_1);

        WorkSchedule workSchedule_2 = new WorkSchedule();
        workSchedule_2.setSchedId(UUID.fromString("b6892135-bc87-427b-b23e-be0ccafecb7e"));
        workSchedule_2.setSchedName(WorkScheduleName.SHIFT_WORK);
        workSchedules.add(workSchedule_2);


        return workSchedules;
    }
}
