-- Inserting roles into the "roles" table
INSERT INTO roles (role_id, role_name)
VALUES
    (X'64d1e26770344c72989b0e3214f264ce', 'ROLE_USER'),
    (X'541eac0e460947b78faebc55c44ec18d', 'ROLE_ADMIN'),
    (X'2a4e17fe75af4e05be970d08089f59b0', 'ROLE_MANAGER'),
    (X'10ffff516e144e87842133f4e53f38ac', 'ROLE_GUEST');


-- Inserting rights into the "authorities" table
INSERT INTO authorities (auth_id, authority)
VALUES
    (X'fcbf07ae7d864a16935a36ee7ac89b02', 'READ_DOCUMENT'),
    (X'cfd9f19bc9e24953ac31c61803c0baa3', 'CREATE_DOCUMENT'),
    (X'16001a26ce5f423aafde4bea256cec90', 'UPDATE_DOCUMENT'),
    (X'a1a62aae38fa4900b392c2ce5afbb5da', 'DELETE_DOCUMENT');

-- Inserting personal data into the "personal_info" table
INSERT INTO personal_info (pers_info_id, username, birthday, phone_number, email, password, salary)
VALUES
    (X'caee169ab58845758cfd4b9c2e711cbe', 'michael', '1992-06-30', '+12-345-678-90-12', 'michael@example.com', '$2y$10$.gJe4xE2FoTm3oYiBmOMm.P3SxUoc6gs8m0RzMGSdeGeYWuv35SJO', 55000.00),
    (X'7a3cc0fb6d9e453ab32929796e1b3045', 'emily', '1987-09-25', '+12-345-678-90-13', 'emily@example.com', '$2y$10$4Srvl0sN273rzqGFTN2uleYi9.BfWKssSPxJ03vwAPYYXXRIY96Ai', 65000.00),
    (X'2ba57f167ac3400797d59d6d6f246e95', 'daniel', '1980-12-10', '+12-345-678-90-14', 'daniel@example.com', '$2y$10$4Srvl0sN273rzqGFTN2uleYi9.BfWKssSPxJ03vwAPYYXXRIY96Ai', 75000.00),
    (X'1f48648697dc4f508fb1cd87d5dd37e1', 'olivia', '1995-04-05', '+12-345-678-90-15', 'olivia@example.com', '$2y$10$x4S2cfdehzD62UMhIPequ.IJhSQYPKxU4s7.ZYJKSaJHY71IGrFLC', 45000.00),
    (X'b514d190b72f4dd5948cd871c3cc1d0b', 'william', '1990-10-20', '+12-345-678-90-16', 'william@example.com', '$2y$10$Bbuw142l6Xm/8BllifvEnuJrOnIZgDcoF5qestIpTRWS6PLNztZvi', 60000.00);

-- Inserting work schedule data into the "workSchedules" table
INSERT INTO work_schedules (sched_id, sched_name, is_day_off, start_time, end_time)
VALUES
    (X'f4d730e81bef47efba9024d28dfc57e6', 'FIVE_DAY_SHIFT_08_17', FALSE, '08:00:00', '17:00:00'),
    (X'94752fc887e84cf6ab17f91d1720c20d', 'FIVE_DAY_SHIFT_09_18', FALSE, '09:00:00', '18:00:00'),
    (X'b6892135bc87427bb23ebe0ccafecb7e', 'SHIFT_WORK', TRUE, '06:00:00', '18:00:00');

-- Inserting department data into the "departments" table
INSERT INTO departments (dep_id, dep_name, dep_phone, dep_email, dep_manager_id)
VALUES
    (X'ef6869b7240248c7bff4141563be2d8c', 'HR', '+12-345-678-90-24', 'hr@example.com', X'7270910ccc71463497a0a242eb5b6064'),
    (X'3c004a2b3ff344138ce3e72ec557b6fc', 'WAREHOUSE', '+12-345-678-90-25', 'warehouse@example.com', X'dfb0689f5f694825a15433c897fa1b38'),
    (X'f80a04a4801541b784588ca40416b4a3', 'FINANCE', '+12-345-678-90-26', 'finance@example.com', X'7881bf3e73a947da8baee2e253a30ddd'),
    (X'88a71c7ed01140e3b9b578315c983b21', 'IT', '+12-345-678-90-22', 'operations@example.com', X'cd8edecd0d2742288fe6911c1cf7fd7c'),
    (X'2e88a78db4a74a00b5904d0f7abe6c04', 'SALES', '+12-345-678-90-23', 'sales@example.com', X'55035fe937e3466fba4a197f23fc5700');

-- Inserting employees into the "employees" table
INSERT INTO employees (emp_id, first_name, last_name, position, hire_date, term_date, workplace_location, status_emp, created_at, vac_plan, dep_id, dep_manager_id, sched_id, pers_info_id)
VALUES
    (X'7270910ccc71463497a0a242eb5b6064', 'Michael', 'Johnson', 'HR_MANAGER', '2021-03-20', NULL, 'OFFICE', 'WORK', CURRENT_TIMESTAMP, 'Flexible Plan', X'ef6869b7240248c7bff4141563be2d8c', X'7270910ccc71463497a0a242eb5b6064', X'94752fc887e84cf6ab17f91d1720c20d', X'caee169ab58845758cfd4b9c2e711cbe'),
    (X'dfb0689f5f694825a15433c897fa1b38', 'Emily', 'Anderson', 'STOREKEEPER', '2019-08-10', NULL, 'WAREHOUSE', 'WORK', CURRENT_TIMESTAMP, 'Standard Plan', X'3c004a2b3ff344138ce3e72ec557b6fc', X'dfb0689f5f694825a15433c897fa1b38', X'b6892135bc87427bb23ebe0ccafecb7e', X'7a3cc0fb6d9e453ab32929796e1b3045'),
    (X'55035fe937e3466fba4a197f23fc5700', 'Daniel', 'Brown', 'SALES_MANAGER', '2017-05-05', NULL, 'SALES_AGENT_ROUTE', 'VACATION', CURRENT_TIMESTAMP, 'Unlimited Plan', X'2e88a78db4a74a00b5904d0f7abe6c04', X'55035fe937e3466fba4a197f23fc5700', X'f4d730e81bef47efba9024d28dfc57e6', X'2ba57f167ac3400797d59d6d6f246e95'),
    (X'cd8edecd0d2742288fe6911c1cf7fd7c', 'Olivia', 'Martinez', 'PROGRAMMER', '2022-01-15', NULL, 'OFFICE', 'SICK_LEAVE', CURRENT_TIMESTAMP, 'Basic Plan', X'88a71c7ed01140e3b9b578315c983b21', X'cd8edecd0d2742288fe6911c1cf7fd7c', X'94752fc887e84cf6ab17f91d1720c20d', X'1f48648697dc4f508fb1cd87d5dd37e1'),
    (X'7881bf3e73a947da8baee2e253a30ddd', 'William', 'Wilson', 'ECONOMIST', '2020-11-30', NULL, 'OFFICE', 'WORK', CURRENT_TIMESTAMP, 'Standard Plan', X'f80a04a4801541b784588ca40416b4a3', X'7881bf3e73a947da8baee2e253a30ddd', X'94752fc887e84cf6ab17f91d1720c20d', X'b514d190b72f4dd5948cd871c3cc1d0b');

-- Inserting vacancy data into the "vacancies" table
INSERT INTO vacancies (vacancy_id, position, vacancy_description, vacancy_requirements, vacancy_salary, workplace_location, vacancy_status, vacancy_contact_info, dep_id)
VALUES
    (X'efff9467a80e447d8763ee7acfa5c29c', 'HR_MANAGER', 'Programmer Position Description', 'Requirements for Programmer Position', 90000.00, 'OFFICE', 'ACTIVE', 'contact@example.com', X'ef6869b7240248c7bff4141563be2d8c'),
    (X'5c2f14e9e9f24e6799112c3bed21b74d', 'DRIVER', 'Driver Position Description', 'Requirements for Driver Position', 55000.00, 'WAREHOUSE', 'ACTIVE', 'contact@example.com', X'3c004a2b3ff344138ce3e72ec557b6fc'),
    (X'aa51c3f9032946339811882bd21ec67b', 'ECONOMIST', 'Security Guard Position Description', 'Requirements for Security Guard Position', 60000.00, 'OFFICE', 'ACTIVE', 'contact@example.com', X'f80a04a4801541b784588ca40416b4a3'),
    (X'51b02a7ee57c4321ba3473d59bfbddec', 'PROGRAMMER', 'Receptionist Position Description', 'Requirements for Receptionist Position', 48000.00, 'OFFICE', 'ACTIVE', 'contact@example.com', X'88a71c7ed01140e3b9b578315c983b21'),
    (X'6f8fdfd4419c46a79ba233a32723cd6c', 'SALES_MANAGER', 'Analyst Position Description', 'Requirements for Analyst Position', 75000.00, 'SALES_AGENT_ROUTE', 'ACTIVE', 'contact@example.com', X'2e88a78db4a74a00b5904d0f7abe6c04');

-- Inserting employee vacation data into the "vacations" table
INSERT INTO vacations (vac_id, vac_start_date, vac_end_date, vac_type, vac_category, substitution_emp_id, emp_id)
VALUES
    (X'99b464245a6045a0b378cbe9f8a49c73', '2024-04-01', '2024-04-10', TRUE, 'ANNUAL', X'7881bf3e73a947da8baee2e253a30ddd', X'7270910ccc71463497a0a242eb5b6064'),
    (X'45c423a12a2f4d2eb5f39a844866164d', '2024-05-15', '2024-05-25', TRUE, 'MATERNITY', X'7881bf3e73a947da8baee2e253a30ddd', X'dfb0689f5f694825a15433c897fa1b38'),
    (X'530ddcadd61d4fc196143d3f526f3675', '2024-06-10', '2024-06-20', TRUE, 'PARENTAL', X'7270910ccc71463497a0a242eb5b6064', X'55035fe937e3466fba4a197f23fc5700'),
    (X'7df490a208a94c448fa0476c904cd4d5', '2024-07-01', '2024-07-10', FALSE, 'UNPAID', X'7881bf3e73a947da8baee2e253a30ddd', X'cd8edecd0d2742288fe6911c1cf7fd7c'),
    (X'0628ad729f214dd498eaee08bcfbd36e', '2024-08-15', '2024-08-25', TRUE, 'OTHER', X'cd8edecd0d2742288fe6911c1cf7fd7c', X'7881bf3e73a947da8baee2e253a30ddd');

-- Inserting addresses into the "addresses" table
INSERT INTO addresses (address_id, country, city, street, house_number, apart_number, address_type, pers_info_id)
VALUES (X'b23a92eb398c4ba99680b4b3a72a910d', 'Germany', 'Berlin', 'Hauptstrasse', '123', 'Wohnung 101', 'HOME', X'caee169ab58845758cfd4b9c2e711cbe'),
       (X'15ef8acb09804074a8be0dbb1589ef02', 'Germany', 'Munich', 'Schlossstrasse', '456', 'Büro 201', 'WORK', X'7a3cc0fb6d9e453ab32929796e1b3045'),
       (X'0b751135128c46c9b5548c6e05bcd2d8', 'Germany', 'Hamburg', 'Am Markt', '789', 'Büro 301', 'WORK', X'2ba57f167ac3400797d59d6d6f246e95'),
       (X'53fb4d7f1f5c44aebf0b1087275c04dc', 'Germany', 'Cologne', 'Königsallee', '1010', 'Büro 401', 'WORK', X'1f48648697dc4f508fb1cd87d5dd37e1'),
       (X'3c1ed47ce8354096a7e073c765b44a64', 'Germany', 'Frankfurt', 'Bahnhofstrasse', '222', 'Wohnung 102', 'HOME', X'b514d190b72f4dd5948cd871c3cc1d0b');

-- Inserting data about company events into the "events" table
INSERT INTO events (ev_id, ev_type, start_date_time, location, description)
VALUES
    (X'92683b96579e4fee9329b442639582e7', 'CONFERENCE', '2024-04-20 09:00:00', 'Conference Hall A', 'Annual company conference'),
    (X'd4379c09f87145a193b4a52d9f91ac57', 'SEMINAR', '2024-05-10 13:00:00', 'Seminar Room B', 'Sales techniques seminar'),
    (X'ea24316662d34fa1b168ae9fd0dc5d6b', 'HOLIDAY', '2024-06-01 00:00:00', 'Beach Resort', 'Company summer holiday'),
    (X'f6f19f3815974422a35b1ba8008996da', 'TRAINING', '2024-07-15 10:00:00', 'Training Center', 'New employee orientation'),
    (X'e4931cb92dea4a75bdf2262d7361f343', 'EMPLOYEE_BIRTHDAY', '2024-08-10 00:00:00', 'Office', 'Employee birthday celebration');

-- Inserting data into the "events_employee" table. Linking events and employees
INSERT INTO events_employee (ev_id, emp_id)
VALUES
    (X'92683b96579e4fee9329b442639582e7', X'7270910ccc71463497a0a242eb5b6064'),
    (X'd4379c09f87145a193b4a52d9f91ac57', X'dfb0689f5f694825a15433c897fa1b38'),
    (X'ea24316662d34fa1b168ae9fd0dc5d6b', X'55035fe937e3466fba4a197f23fc5700'),
    (X'e4931cb92dea4a75bdf2262d7361f343', X'cd8edecd0d2742288fe6911c1cf7fd7c'),
    (X'92683b96579e4fee9329b442639582e7', X'7881bf3e73a947da8baee2e253a30ddd');

-- Inserting data into the "pers_info_role" table. Linking Users and Roles
INSERT INTO pers_info_role (pers_info_id, role_id)
VALUES
    (X'caee169ab58845758cfd4b9c2e711cbe', X'64d1e26770344c72989b0e3214f264ce'),
    (X'7a3cc0fb6d9e453ab32929796e1b3045', X'541eac0e460947b78faebc55c44ec18d'),
    (X'2ba57f167ac3400797d59d6d6f246e95', X'2a4e17fe75af4e05be970d08089f59b0'),
    (X'1f48648697dc4f508fb1cd87d5dd37e1', X'64d1e26770344c72989b0e3214f264ce'),
    (X'b514d190b72f4dd5948cd871c3cc1d0b', X'64d1e26770344c72989b0e3214f264ce');

-- Inserting data into the "role_authority" table. Linking roles and rights
INSERT INTO role_authority (role_id, auth_id)
VALUES
    (X'64d1e26770344c72989b0e3214f264ce', X'fcbf07ae7d864a16935a36ee7ac89b02'),
    (X'541eac0e460947b78faebc55c44ec18d', X'cfd9f19bc9e24953ac31c61803c0baa3'),
    (X'2a4e17fe75af4e05be970d08089f59b0', X'16001a26ce5f423aafde4bea256cec90'),
    (X'10ffff516e144e87842133f4e53f38ac', X'fcbf07ae7d864a16935a36ee7ac89b02'),
    (X'64d1e26770344c72989b0e3214f264ce', X'a1a62aae38fa4900b392c2ce5afbb5da');


