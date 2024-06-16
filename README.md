# Employee Office Backend

## Description

The EmployeeOffice application is an integrated system for managing information about employees and work processes
in an organization. It is designed to simplify the interaction of employees between various departments
of the enterprise, promptly obtaining the necessary information, such as data on trainings, vacations,
events and contact information.

### Class Diagram EmployeeOffice

![PhotoDependencyClasses](https://github.com/AnnaLeonova1808/EmployeeOffice/blob/master/Diagram.jpg)

### Database Structure

![PhotoDependencyClasses](https://github.com/AnnaLeonova1808/EmployeeOffice/blob/master/Database_Diagram.png)

### Table Employees (Company employees table)

| Column name         | Type                                                                                                | Description                                                                                               |
|---------------------|-----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| emp_Id              | BINARY(16)                                                                                          | PRIMARY KEY Unique identifier for the employee                                                            |
| first_name          | VARCHAR(50)                                                                                         | First name of the employee                                                                                |
| last_name           | VARCHAR(50)                                                                                         | Last name of the employee                                                                                 |
| position            | ENUM ('MANAGER', 'ECONOMIST', 'SALES_MANAGER', 'PROGRAMMER', 'HR_MANAGER', 'STOREKEEPER', 'DRIVER') | Employee's position from Enum Position                                                                    |
| hire_date           | DATE                                                                                                | Date when the employee was hired                                                                          |
| term_date           | DATE                                                                                                | Date when the employee was terminated                                                                     |
| workplace_location  | ENUM('OFFICE', 'WAREHOUSE', 'SALES AGENT ROUTE')                                                    | Employee's work location from enum WorkplaceLocation                                                      |
| status_emp          | ENUM('WORK', 'VACATION', 'SICK_LEAVE')                                                              | Employee's status from Enum StatusEmployee                                                                |
| created_at          | TIMESTAMP                                                                                           | Date added                                                                                                |
| vaс_plan            | VARCHAR(255)                                                                                        | Vacation plan description                                                                                 |
| dep_id              | BINARY(16)                                                                                          | FOREIGN KEY (department_id) REFERENCES Department(department_id) Department to which the employee belongs |
| dep_manager_id      | BINARY(16)                                                                                          | FOREIGN KEY (supervisorID) REFERENCES Employee(id) ID of the employee's supervisor                        |
| managed_department  | VARCHAR(60)                                                                                         | Managed Department Communication between the department and its head                                      |
| schedule_id         | BINARY(16)                                                                                          | FOREIGN KEY Unique schedule identifier                                                                    |
| pers_info_id        | BINARY(16)                                                                                          | FOREIGN KEY (personal_info_id) REFERENCES Personal_Info(personal_info_id)                                 |

### Table Roles (Company users roles)

| Column name         | Type                                                                                                | Description                                                                                               |
|---------------------|-----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| role_id             | BINARY(16)                                                                                          | PRIMARY KEY Unique role ID                                                                                |
| role_name           | ENUM ('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER', 'ROLE_GUEST')                                      | Role name from enum RolesName                                                                             |

### Table Authorities (Company authorities)

| Column name         | Type                                                                                                | Description                                                                                               |
|---------------------|-----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| auth_id             | BINARY(16)                                                                                          | PRIMARY KEY Unique identifier of the authority                                                            |
| authority           | ENUM ('READ_DOCUMENT', 'CREATE_DOCUMENT', 'DELETE_DOCUMENT')                                        | Name of authority from enum AuthorityName                                                                 |

### Table Personal Info (Company employees personal information)

| Column name         | Type                                                                                                | Description                                                                                               |
|---------------------|-----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| pers_info_id        | BINARY(16)                                                                                          | PRIMARY KEY Unique identifier of personal information                                                     |
| username            | VARCHAR(50)                                                                                         | Username of the employee                                                                                  |
| emp_id              | BINARY(16)                                                                                          | Employee(employee_id), to the Employee table                                                              |
| birthday            | DATE                                                                                                | Date of birth of the employee                                                                             |
| phone_number        | VARCHAR(20)                                                                                         | Phone number of the employee                                                                              |
| email               | VARCHAR(60)                                                                                         | Email address of the employee                                                                             |
| password            | VARCHAR(128)                                                                                        | User's password                                                                                           |
| salary              | DECIMAL(10,2)                                                                                       | Salary of the employee                                                                                    |

### Table Address (Address information)

| Column name         | Type                                                                                                | Description                                                                                               |
|---------------------|-----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| address_id          | BINARY(16)                                                                                          | PRIMARY KEY Unique address identifier                                                                     |
| country             | VARCHAR(100)                                                                                        | Country                                                                                                   |
| city                | VARCHAR(100)                                                                                        | City                                                                                                      |
| street              | VARCHAR(255)                                                                                        | Street                                                                                                    |
| house_number        | VARCHAR(20)                                                                                         | House number                                                                                              |
| apart_number        | VARCHAR(20)                                                                                         | Apartment/office number                                                                                   |
| address_type        | ENUM ('HOME', 'WORK')                                                                               | Address type from enum AdressType                                                                         |
| pers_info_id        | BINARY(16)                                                                                          | FOREIGN KEY (pers_info_id) REFERENCES Personal Info (of personal information)                             |

### Table Departments (Company departments table)

| Column name         | Type                                                                                                | Description                                                                                               |
|---------------------|-----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| dep_id              | BINARY(16)                                                                                          | PRIMARY KEY Unique department identifier                                                                  |
| dep_name            | ENUM ('IT', 'WAREHOUSE', 'SALES', 'FINANCE', 'HR')                                                  | Department name from Enum DepartmentName                                                                  |
| dep_phone           | VARCHAR(20)                                                                                         | Phone number of the department                                                                            |
| dep_email           | VARCHAR(60)                                                                                         | Email address of the department                                                                           |
| dep_manager_id      | BINARY(16)                                                                                          | ID of the employee's supervisor                                                                           |

### Table Vacancies (Company vacancies table)

| Column name          | Type                                                                                                | Description                                                                                              |
|----------------------|-----------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| vacancy_id           | BINARY(16)                                                                                          | PRIMARY KEY Unique vacancy identifier                                                                    |
| position             | ENUM('MANAGER', 'ECONOMIST', 'SALES_MANAGER','PROGRAMMER','DRIVER')                                 | Title of the position from Enum Position                                                                 |
| vacancy_description  | VARCHAR(255)                                                                                        | Description of the vacancy                                                                               |
| vacancy_requirements | VARCHAR(255)                                                                                        | Requirements for candidates                                                                              |
| vacancy_salary       | DECIMAL(10, 2)                                                                                      | Salary for the position                                                                                  |
| workplace_location   | ENUM('OFFICE', 'WAREHOUSE', 'SALES AGENT ROUTE')                                                    | Vacancee's work location from Enum WorkplaceLocation                                                     |
| vacancy_status       | ENUM('ACTIVE', 'CLOSE')                                                                             | Status of the vacancy from Enum VacancyStatus                                                            |
| vacancy_contact_info | VARCHAR(255)                                                                                        | Contact information for the vacancy                                                                      |
| dep_id               | BINARY(16)                                                                                          | FOREIGN KEY (department_id) REFERENCES Department(department_id). Unique department identifier           |

### Table WorkSchedule (Company schedules table)

| Column name           | Type                                                                                               | Description                                                                                              |
|-----------------------|----------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| sched_id              | BINARY(16)                                                                                         | PRIMARY KEY Unique schedule identifier                                                                   |
| sched_name            | ENUM ('FIVE_DAY_SHIFT_08_17', 'FIVE_DAY_SHIFT_09_18', 'SHIFT_WORK')                                | Name of workschedule from Enum WorkScheduleName                                                          |
| is_day_off            | BOOLEAN                                                                                            | Is the workday a day off or not?                                                                         |
| start_time            | TIME                                                                                               | Start time of the workday                                                                                |
| end_time              | TIME                                                                                               | End time of the workday                                                                                  |

### Table Vacations (Company vacations table)

| Column name           | Type                                                                                               | Description                                                                                              |
|-----------------------|----------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| vac_id                | BINARY(16)                                                                                         | PRIMARY KEY Unique vacation identifier                                                                   |
| vac_start_date        | DATE                                                                                               | Start date of the vacation                                                                               |
| vac_end_date          | DATE                                                                                               | End date of the vacation                                                                                 |
| vac_type              | BOOLEAN                                                                                            | Type of vacation, is paid?                                                                               |
| vac_category          | ENUM ('ANNUAL', 'MATERNITY', 'PARENTAL', 'UNPAID', 'OTHER')                                        | Category of vacation from ENUM VacationCategory                                                          |
| substitution_emp_id   | BINARY(16)                                                                                         | FOREIGN KEY (substitution_emp_id) REFERENCES Employee(employee_id)                                       |
| emp_Id                | BINARY(16)                                                                                         | FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)                                               |

### Table Events (Company events table)

| Column name           | Type                                                                                               | Description                                                                                              |
|-----------------------|----------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| ev_id                 | BINARY(16)                                                                                         | PRIMARY KEY Unique event identifier                                                                      |
| ev_type               | ENUM ('CONFERENCE', 'SEMINAR', 'HOLIDAY', 'UNPAID', 'EMPLOYEE_BIRTHDAY','TRAINING','SURVEY')       | Type of event from ENUM EventType                                                                        |
| start_date_time       | TIMESTAMP                                                                                          | Start date and time of the event                                                                         |
| location              | VARCHAR(255)                                                                                       | Location where the event takes place                                                                     |
| description           | VARCHAR(255)                                                                                       | Description of the event                                                                                 |

### Link Table Pers_info<->Role

| Column name           | Type                                                                                               | Description                                                                                              |
|-----------------------|----------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| pers_info_id          | BINARY(16)                                                                                         |                                                                                                          |
| role_id               | BINARY(16)                                                                                         |                                                                                                          |

### Link Table Role<->Authority

| Column name           | Type                                                                                               | Description                                                                                              |
|-----------------------|----------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| role_id               | BINARY(16)                                                                                         |                                                                                                          |
| auth_id               | BINARY(16)                                                                                         |                                                                                                          |

### Link Table Events<->Employee

| Column name           | Type                                                                                               | Description                                                                                              |
|-----------------------|----------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| ev_id                 | BINARY(16)                                                                                         |                                                                                                          |
| emp_id                | BINARY(16)                                                                                         |                                                                                                          |

### Project structure

- `src/main/java/com/example/employeeoffice` — source
- `src/main/resources` — configuration resources

### Used Technologies:

Programming Language: Java
Frameworks and Libraries:
Spring Framework:
Spring Boot (including spring-boot-starter-web and spring-boot-starter-data-jpa)
Spring Security (for securing the application, managing authentication and authorization)
Hibernate (via Spring Data JPA)
SpringDoc OpenAPI (including springdoc-openapi-starter-webmvc-ui and springdoc-openapi-ui)
Lombok (for simplifying code)
MapStruct (for object mapping)
Swagger (via swagger-annotations)
BCrypt (for password hashing)
Liquibase (for database migrations management)
JavaFaker (for generating test data)
Database: MySQL (main database), H2 (for testing)
Build and Testing Tools:
Maven
Jacoco (for code coverage analysis)
API: RESTful API for client-server interaction

### Validators

- `@UuidFormatChecker` — custom annotation for checking UUID format
- `@PhoneNumberChecker` — custom annotation for checking phone number format
- `@EmailChecker` — custom annotation for checking email format

## Contacts

If you have questions or suggestions, please contact me:

- Name: Anna Leonova
- Email: annaleonova88888@gmail.com
- GitHub: [AnnaLeonova1808](https://github.com/AnnaLeonova1808)


