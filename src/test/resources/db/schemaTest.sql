CREATE TABLE IF NOT EXISTS roles
(
    role_id   BINARY(16) PRIMARY KEY,
    role_name ENUM ('ROLE_ADMIN', 'ROLE_MANAGER', 'ROLE_USER', 'ROLE_GUEST') DEFAULT 'ROLE_USER'
);

CREATE TABLE IF NOT EXISTS authorities
(
    auth_id   BINARY(16) PRIMARY KEY,
    authority ENUM ('READ_DOCUMENT', 'CREATE_DOCUMENT', 'DELETE_DOCUMENT', 'UPDATE_DOCUMENT')
);

CREATE TABLE IF NOT EXISTS personal_info
(
    pers_info_id BINARY(16) PRIMARY KEY,
    username     VARCHAR(50) NOT NULL,
    birthday     DATE,
    phone_number VARCHAR(20) UNIQUE,
    email        VARCHAR(60) UNIQUE,
    password     VARCHAR(128) NOT NULL,
    salary       DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS work_schedules
(
    sched_id   BINARY(16) PRIMARY KEY,
    sched_name ENUM ('FIVE_DAY_SHIFT_08_17', 'FIVE_DAY_SHIFT_09_18', 'SHIFT_WORK'),
    is_day_off BOOLEAN,
    start_time TIME DEFAULT NULL,
    end_time   TIME DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS departments
(
    dep_id         BINARY(16) PRIMARY KEY,
    dep_name       ENUM ('IT', 'WAREHOUSE', 'SALES', 'FINANCE', 'HR'),
    dep_phone      VARCHAR(20),
    dep_email      VARCHAR(60),
    dep_manager_id BINARY(16) UNIQUE
);

CREATE TABLE IF NOT EXISTS employees
(
    emp_id             BINARY(16) PRIMARY KEY,
    first_name         VARCHAR(50) NOT NULL,
    last_name          VARCHAR(50) NOT NULL,
    position           ENUM ('MANAGER', 'ECONOMIST', 'SALES_MANAGER', 'PROGRAMMER', 'HR_MANAGER', 'STOREKEEPER', 'DRIVER') NOT NULL,
    hire_date          DATE,
    term_date          DATE,
    workplace_location ENUM ('OFFICE', 'WAREHOUSE', 'SALES_AGENT_ROUTE'),
    status_emp         ENUM ('WORK', 'VACATION', 'SICK_LEAVE'),
    created_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    vac_plan           VARCHAR(255) DEFAULT NULL,
    dep_id             BINARY(16) ,
    dep_manager_id     BINARY(16),
    sched_id           BINARY(16),
    pers_info_id       BINARY(16) NOT NULL,
    FOREIGN KEY (dep_id) REFERENCES departments (dep_id),
    FOREIGN KEY (dep_manager_id) REFERENCES employees (emp_id),
    FOREIGN KEY (sched_id) REFERENCES work_schedules (sched_id),
    FOREIGN KEY (pers_info_id) REFERENCES personal_info (pers_info_id)
);


CREATE TABLE IF NOT EXISTS vacancies
(
    vacancy_id           BINARY(16) PRIMARY KEY,
    position             ENUM ('MANAGER', 'ECONOMIST', 'SALES_MANAGER', 'PROGRAMMER', 'HR_MANAGER', 'STOREKEEPER', 'DRIVER') NOT NULL,
    vacancy_description  VARCHAR(255),
    vacancy_requirements VARCHAR(255),
    vacancy_salary       DECIMAL(10, 2),
    workplace_location   ENUM ('OFFICE', 'WAREHOUSE', 'SALES_AGENT_ROUTE'),
    vacancy_status       ENUM ('ACTIVE', 'CLOSE'),
    vacancy_contact_info VARCHAR(255),
    dep_id               BINARY(16) NOT NULL,
    FOREIGN KEY (dep_id) REFERENCES departments (dep_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS vacations
(
    vac_id              BINARY(16) PRIMARY KEY,
    vac_start_date      DATE,
    vac_end_date        DATE,
    vac_type            BOOLEAN,
    vac_category        ENUM ('ANNUAL', 'MATERNITY', 'PARENTAL', 'UNPAID', 'OTHER'),
    substitution_emp_id BINARY(16) NOT NULL,
    emp_id              BINARY(16) NOT NULL,
    FOREIGN KEY (substitution_emp_id) REFERENCES employees (emp_id),
    FOREIGN KEY (emp_id) REFERENCES employees (emp_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS addresses
(
    address_id   BINARY(16) PRIMARY KEY,
    country      VARCHAR(100),
    city         VARCHAR(100),
    street       VARCHAR(255),
    house_number VARCHAR(20),
    apart_number VARCHAR(20),
    address_type ENUM ('HOME', 'WORK'),
    pers_info_id BINARY(16) NOT NULL,
    FOREIGN KEY (pers_info_id) REFERENCES personal_info (pers_info_id)
    );
CREATE TABLE IF NOT EXISTS events
(
    ev_id           BINARY(16) PRIMARY KEY,
    ev_type         ENUM ('CONFERENCE', 'SEMINAR', 'HOLIDAY', 'UNPAID', 'EMPLOYEE_BIRTHDAY', 'TRAINING', 'SURVEY'),
    start_date_time TIMESTAMP,
    location        VARCHAR(255),
    description     VARCHAR(255)
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






