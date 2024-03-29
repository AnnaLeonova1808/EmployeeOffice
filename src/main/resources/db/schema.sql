DROP TABLE IF EXISTS events_employee;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS vacations;
DROP TABLE IF EXISTS addresses;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS role_authority;
DROP TABLE IF EXISTS personal_info_role;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS personal_info;

CREATE TABLE IF NOT EXISTS personal_info (
    pers_info_id BINARY(16) PRIMARY KEY,
    emp_id BINARY(16) NOT NULL,
    birthday DATE,
    phone_number VARCHAR(20),
    email VARCHAR(255),
    salary DECIMAL(10, 2),
    home_address_id BINARY(16) NOT NULL,
    office_address_id BINARY(16) NOT NULL,
    FOREIGN KEY (emp_id) REFERENCES employees(emp_id),
    FOREIGN KEY (home_address_id) REFERENCES addresses(address_id),
    FOREIGN KEY (office_address_id) REFERENCES addresses(address_id)
);

CREATE TABLE IF NOT EXISTS roles (
    role_id BINARY(16) PRIMARY KEY,
    role_name VARCHAR(255) NOT NULL UNIQUE,
);

CREATE TABLE IF NOT EXISTS authorities (
    auth_id BINARY(16) PRIMARY KEY,
    authority VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS pers_info_role (
    pers_info_id BINARY(16),
    role_id BINARY(16) NOT NULL,
    PRIMARY KEY (pers_info_id, role_id),
    FOREIGN KEY (pers_info_id) REFERENCES personal_info(pers_info_id),
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

CREATE TABLE role_authority(
    role_id      BINARY(16) NOT NULL,
    authority_id BINARY(16) NOT NULL,
    PRIMARY KEY (role_id, auth_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id),
    FOREIGN KEY (auth_id) REFERENCES authorities (auth_id)
);

CREATE TABLE employees (
    emp_id BINARY(16) PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    emp_grade VARCHAR(50),
    hire_date DATE,
    term_date DATE,
    workplace_location VARCHAR(50),
    status_emp VARCHAR(50),
    created_at TIMESTAMP,
    vac_plan VARCHAR(255),
    dep_id BINARY(16) NOT NULL,
    dep_manager_id BINARY(16) NOT NULL,
    schedule_id BINARY(16) NOT NULL,
    pers_info_id BINARY(16) NOT NULL,
    FOREIGN KEY (dep_id) REFERENCES departments(dep_id),
    FOREIGN KEY (dep_manager_id) REFERENCES employees(emp_id),
    FOREIGN KEY (schedule_id) REFERENCES work_schedules(schedule_id),
    FOREIGN KEY (pers_info_id) REFERENCES personal_info(pers_info_id)
);

CREATE TABLE addresses (
    address_id BINARY(16) PRIMARY KEY,
    country VARCHAR(100),
    city VARCHAR(100),
    street VARCHAR(255),
    house_number VARCHAR(20),
    apart_number VARCHAR(20),
    address_type VARCHAR(50),
    emp_id BINARY(16) NOT NULL,
    FOREIGN KEY (emp_id) REFERENCES employees(emp_id)
);

CREATE TABLE vacations (
    vac_id BINARY(16) PRIMARY KEY,
    vac_start_date DATE,
    vac_end_date DATE,
    vac_type BOOLEAN,
    vac_category VARCHAR(50),
    substitution_emp_id BINARY(16) NOT NULL,
    emp_id BINARY(16) NOT NULL,
    FOREIGN KEY (substitution_emp_id) REFERENCES employees(emp_id),
    FOREIGN KEY (emp_id) REFERENCES employees(emp_id)
);

CREATE TABLE events (
     ev_id BINARY(16) PRIMARY KEY,
     ev_type VARCHAR(50),
     start_date_time TIMESTAMP,
     location VARCHAR(255),
     description TEXT,
     ev_status VARCHAR(50)
);

CREATE TABLE events_employee (
      ev_id BINARY(16) NOT NULL,
      emp_id BINARY(16) NOT NULL,
      PRIMARY KEY (emp_id, ev_id),
      FOREIGN KEY (emp_id) REFERENCES employees(emp_id),
      FOREIGN KEY (ev_id) REFERENCES events(ev_id)
);