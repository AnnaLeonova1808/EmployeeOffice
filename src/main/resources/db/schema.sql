DROP TABLE IF EXISTS role_authority;
DROP TABLE IF EXISTS pers_info_role;
DROP TABLE IF EXISTS events_employee;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS vacations;
DROP TABLE IF EXISTS vacancies;
DROP TABLE IF EXISTS departments;
DROP TABLE IF EXISTS work_schedules;
DROP TABLE IF EXISTS personal_info;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS roles;


CREATE TABLE IF NOT EXISTS roles
(
    role_id   BINARY(16) PRIMARY KEY,
    role_name VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS authorities
(
    auth_id   BINARY(16) PRIMARY KEY,
    authority VARCHAR(60) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS personal_info
(
    pers_info_id BINARY(16) PRIMARY KEY,
    emp_id       BINARY(16)     NOT NULL,
    birthday     DATE,
    phone_number VARCHAR(20)    NOT NULL UNIQUE,
    email        VARCHAR(60)    NOT NULL UNIQUE,
    password     VARCHAR(128)   NOT NULL UNIQUE,
    salary       DECIMAL(10, 2) NOT NULL
);
CREATE TABLE IF NOT EXISTS work_schedules
(
    sched_id   BINARY(16) PRIMARY KEY,
    sched_name VARCHAR(255),
    is_day_off BOOLEAN,
    start_time TIME,
    end_time   TIME
);
CREATE TABLE IF NOT EXISTS departments
(
    dep_id         BINARY(16) PRIMARY KEY,
    dep_name       VARCHAR(60),
    dep_phone      VARCHAR(20),
    dep_email      VARCHAR(60),
    dep_manager_id BINARY(16),
    FOREIGN KEY (dep_manager_id) REFERENCES employees (emp_id)
);

CREATE TABLE IF NOT EXISTS vacancies
(
    vacancy_id           BINARY(16) PRIMARY KEY,
    position_title       VARCHAR(255),
    vacancy_description  VARCHAR(255),
    vacancy_requirements VARCHAR(255),
    vacancy_salary       DECIMAL(10, 2),
    workplace_location   VARCHAR(255),
    vacancy_status       VARCHAR(20),
    vacancy_contact_info VARCHAR(255),
    employment_type      VARCHAR(255),
    dep_id               BINARY(16) NOT NULL,
    FOREIGN KEY (dep_id) REFERENCES departments (dep_id)
);

CREATE TABLE IF NOT EXISTS vacations
(
    vac_id              BINARY(16) PRIMARY KEY,
    vac_start_date      DATE,
    vac_end_date        DATE,
    vac_type            BOOLEAN,
    vac_category        VARCHAR(50),
    substitution_emp_id BINARY(16) NOT NULL,
    emp_id              BINARY(16) NOT NULL,
    FOREIGN KEY (substitution_emp_id) REFERENCES employees (emp_id),
    FOREIGN KEY (emp_id) REFERENCES employees (emp_id)
);
CREATE TABLE IF NOT EXISTS addresses
(
    address_id   BINARY(16) PRIMARY KEY,
    country      VARCHAR(100),
    city         VARCHAR(100),
    street       VARCHAR(255),
    house_number VARCHAR(20),
    apart_number VARCHAR(20),
    address_type VARCHAR(20),
    pers_info_id BINARY(16) NOT NULL,
    FOREIGN KEY (pers_info_id) REFERENCES personal_info (pers_info_id)
);
CREATE TABLE IF NOT EXISTS events
(
    ev_id           BINARY(16) PRIMARY KEY,
    ev_type         VARCHAR(50),
    start_date_time TIMESTAMP,
    location        VARCHAR(255),
    description     VARCHAR(255),
    ev_status       VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS employees
(
    emp_id             BINARY(16) PRIMARY KEY,
    first_name         VARCHAR(50),
    last_name          VARCHAR(50),
    emp_grade          VARCHAR(50),
    hire_date          DATE,
    term_date          DATE,
    workplace_location VARCHAR(50),
    status_emp         VARCHAR(50),
    created_at         TIMESTAMP,
    vac_plan           VARCHAR(255),
    dep_id             BINARY(16) NOT NULL,
    dep_manager_id     BINARY(16),
    sched_id           BINARY(16) NOT NULL,
    pers_info_id       BINARY(16) NOT NULL,
    FOREIGN KEY (dep_id) REFERENCES departments (dep_id),
    FOREIGN KEY (dep_manager_id) REFERENCES employees (emp_id),
    FOREIGN KEY (sched_id) REFERENCES work_schedules (sched_id),
    FOREIGN KEY (pers_info_id) REFERENCES personal_info (pers_info_id)
);

CREATE TABLE IF NOT EXISTS events_employee
(
    ev_id  BINARY(16) NOT NULL,
    emp_id BINARY(16) NOT NULL,
    PRIMARY KEY (emp_id, ev_id),
    FOREIGN KEY (emp_id) REFERENCES employees (emp_id),
    FOREIGN KEY (ev_id) REFERENCES events (ev_id)
);


CREATE TABLE IF NOT EXISTS pers_info_role
(
    pers_info_id BINARY(16) NOT NULL,
    role_id      BINARY(16) NOT NULL,
    PRIMARY KEY (pers_info_id, role_id),
    FOREIGN KEY (pers_info_id) REFERENCES personal_info (pers_info_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

CREATE TABLE IF NOT EXISTS role_authority
(
    role_id BINARY(16) NOT NULL,
    auth_id BINARY(16) NOT NULL,
    PRIMARY KEY (role_id, auth_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id),
    FOREIGN KEY (auth_id) REFERENCES authorities (auth_id)
);






