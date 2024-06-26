-- liquibase formatted sql

-- changeset liquibase:13 pass michael -> 111  emily -> 222  daniel -> 333  olivia -> 444  william -> 555

-- Inserting roles into the "roles" table
INSERT INTO roles (role_id, role_name)
VALUES
    (UUID_TO_BIN('64d1e267-7034-4c72-989b-0e3214f264ce'), 'ROLE_USER'), -- emily  olivia  william
    (UUID_TO_BIN('541eac0e-4609-47b7-8fae-bc55c44ec18d'), 'ROLE_ADMIN'), -- michael
    (UUID_TO_BIN('2a4e17fe-75af-4e05-be97-0d08089f59b0'), 'ROLE_MANAGER'), -- daniel
    (UUID_TO_BIN('10ffff51-6e14-4e87-8421-33f4e53f38ac'), 'ROLE_GUEST');


-- Inserting rights into the "authorities" table
INSERT INTO authorities (auth_id, authority)
VALUES
    (UUID_TO_BIN('fcbf07ae-7d86-4a16-935a-36ee7ac89b02'), 'READ_DOCUMENT'),
    (UUID_TO_BIN('cfd9f19b-c9e2-4953-ac31-c61803c0baa3'), 'CREATE_DOCUMENT'),
    (UUID_TO_BIN('16001a26-ce5f-423a-afde-4bea256cec90'), 'UPDATE_DOCUMENT'),
    (UUID_TO_BIN('a1a62aae-38fa-4900-b392-c2ce5afbb5da'), 'DELETE_DOCUMENT');

-- Inserting personal data into the "personal_info" table
INSERT INTO personal_info (pers_info_id, username, birthday, phone_number, email, password, salary)
VALUES
    (UUID_TO_BIN('caee169a-b588-4575-8cfd-4b9c2e711cbe'), 'michael', '1992-06-30', '+12-345-678-90-12', 'michael@example.com', '$2y$10$.gJe4xE2FoTm3oYiBmOMm.P3SxUoc6gs8m0RzMGSdeGeYWuv35SJO', 55000.00),
    (UUID_TO_BIN('7a3cc0fb-6d9e-453a-b329-29796e1b3045'), 'emily', '1987-09-25', '+12-345-678-90-13', 'emily@example.com', '$2y$10$4Srvl0sN273rzqGFTN2uleYi9.BfWKssSPxJ03vwAPYYXXRIY96Ai', 65000.00),
    (UUID_TO_BIN('2ba57f16-7ac3-4007-97d5-9d6d6f246e95'), 'daniel', '1980-12-10', '+12-345-678-90-14', 'daniel@example.com', '$2y$10$4Srvl0sN273rzqGFTN2uleYi9.BfWKssSPxJ03vwAPYYXXRIY96Ai', 75000.00),
    (UUID_TO_BIN('1f486486-97dc-4f50-8fb1-cd87d5dd37e1'), 'olivia', '1995-04-05', '+12-345-678-90-15', 'olivia@example.com', '$2y$10$x4S2cfdehzD62UMhIPequ.IJhSQYPKxU4s7.ZYJKSaJHY71IGrFLC', 45000.00),
    (UUID_TO_BIN('b514d190-b72f-4dd5-948c-d871c3cc1d0b'), 'william', '1990-10-20', '+12-345-678-90-16', 'william@example.com', '$2y$10$Bbuw142l6Xm/8BllifvEnuJrOnIZgDcoF5qestIpTRWS6PLNztZvi', 60000.00);

-- Inserting work schedule data into the "workSchedules" table
INSERT INTO work_schedules (sched_id, sched_name, is_day_off, start_time, end_time)
VALUES
    (UUID_TO_BIN('f4d730e8-1bef-47ef-ba90-24d28dfc57e6'), 'FIVE_DAY_SHIFT_08_17', FALSE, '08:00:00', '17:00:00'),
    (UUID_TO_BIN('94752fc8-87e8-4cf6-ab17-f91d1720c20d'), 'FIVE_DAY_SHIFT_09_18', FALSE, '09:00:00', '18:00:00'),
    (UUID_TO_BIN('b6892135-bc87-427b-b23e-be0ccafecb7e'), 'SHIFT_WORK', TRUE, '06:00:00', '18:00:00');

-- Inserting department data into the "departments" table
INSERT INTO departments (dep_id, dep_name, dep_phone, dep_email, dep_manager_id)
VALUES
    (UUID_TO_BIN('ef6869b7-2402-48c7-bff4-141563be2d8c'), 'HR', '+12-345-678-90-24', 'hr@example.com', UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064')),
    (UUID_TO_BIN('3c004a2b-3ff3-4413-8ce3-e72ec557b6fc'), 'WAREHOUSE', '+12-345-678-90-25', 'warehouse@example.com', UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38')),
    (UUID_TO_BIN('f80a04a4-8015-41b7-8458-8ca40416b4a3'), 'FINANCE', '+12-345-678-90-26', 'finance@example.com', UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd')),
    (UUID_TO_BIN('88a71c7e-d011-40e3-b9b5-78315c983b21'), 'IT', '+12-345-678-90-22', 'operations@example.com', UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c')),
    (UUID_TO_BIN('2e88a78d-b4a7-4a00-b590-4d0f7abe6c04'), 'SALES', '+12-345-678-90-23', 'sales@example.com', UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700'));

-- Inserting employees into the "employees" table
INSERT INTO employees (emp_id, first_name, last_name, position, hire_date, term_date, workplace_location, status_emp, created_at, vac_plan, dep_id, dep_manager_id, sched_id, pers_info_id)
VALUES
    (UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064'), 'Michael', 'Johnson', 'HR_MANAGER', '2021-03-20', NULL, 'OFFICE', 'WORK', CURRENT_TIMESTAMP, 'Flexible Plan', UUID_TO_BIN('ef6869b7-2402-48c7-bff4-141563be2d8c'), UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064'), UUID_TO_BIN('94752fc8-87e8-4cf6-ab17-f91d1720c20d'), UUID_TO_BIN('caee169a-b588-4575-8cfd-4b9c2e711cbe')),
    (UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38'), 'Emily', 'Anderson', 'STOREKEEPER', '2019-08-10', NULL, 'WAREHOUSE', 'WORK', CURRENT_TIMESTAMP, 'Standard Plan', UUID_TO_BIN('3c004a2b-3ff3-4413-8ce3-e72ec557b6fc'), UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38'), UUID_TO_BIN('b6892135-bc87-427b-b23e-be0ccafecb7e'), UUID_TO_BIN('7a3cc0fb-6d9e-453a-b329-29796e1b3045')),
    (UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700'), 'Daniel', 'Brown', 'SALES_MANAGER', '2017-05-05', NULL, 'SALES_AGENT_ROUTE', 'VACATION', CURRENT_TIMESTAMP, 'Unlimited Plan', UUID_TO_BIN('2e88a78d-b4a7-4a00-b590-4d0f7abe6c04'), UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700'), UUID_TO_BIN('f4d730e8-1bef-47ef-ba90-24d28dfc57e6'), UUID_TO_BIN('2ba57f16-7ac3-4007-97d5-9d6d6f246e95')),
    (UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c'), 'Olivia', 'Martinez', 'PROGRAMMER', '2022-01-15', NULL, 'OFFICE', 'SICK_LEAVE', CURRENT_TIMESTAMP, 'Basic Plan', UUID_TO_BIN('88a71c7e-d011-40e3-b9b5-78315c983b21'), UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c'), UUID_TO_BIN('94752fc8-87e8-4cf6-ab17-f91d1720c20d'), UUID_TO_BIN('1f486486-97dc-4f50-8fb1-cd87d5dd37e1')),
    (UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'), 'William', 'Wilson', 'ECONOMIST', '2020-11-30', NULL, 'OFFICE', 'WORK', CURRENT_TIMESTAMP, 'Standard Plan', UUID_TO_BIN('f80a04a4-8015-41b7-8458-8ca40416b4a3'), UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'), UUID_TO_BIN('94752fc8-87e8-4cf6-ab17-f91d1720c20d'), UUID_TO_BIN('b514d190-b72f-4dd5-948c-d871c3cc1d0b'));

-- Inserting vacancy data into the "vacancies" table
INSERT INTO vacancies (vacancy_id, position, vacancy_description, vacancy_requirements, vacancy_salary, workplace_location, vacancy_status, vacancy_contact_info, dep_id)
VALUES
    (UUID_TO_BIN('efff9467-a80e-447d-8763-ee7acfa5c29c'), 'HR_MANAGER', 'HR Position Description', 'Requirements for HR manager Position', 90000.00, 'OFFICE', 'ACTIVE', 'contact@example.com', UUID_TO_BIN('ef6869b7-2402-48c7-bff4-141563be2d8c')),
    (UUID_TO_BIN('5c2f14e9-e9f2-4e67-9911-2c3bed21b74d'), 'DRIVER', 'Driver Position Description', 'Requirements for Driver Position', 55000.00, 'WAREHOUSE', 'ACTIVE', 'contact@example.com', UUID_TO_BIN('3c004a2b-3ff3-4413-8ce3-e72ec557b6fc')),
    (UUID_TO_BIN('aa51c3f9-0329-4633-9811-882bd21ec67b'), 'ECONOMIST', 'Security Guard Position Description', 'Requirements for Security Guard Position', 60000.00, 'OFFICE', 'ACTIVE', 'contact@example.com', UUID_TO_BIN('f80a04a4-8015-41b7-8458-8ca40416b4a3')),
    (UUID_TO_BIN('51b02a7e-e57c-4321-ba34-73d59bfbddec'), 'PROGRAMMER', 'Receptionist Position Description', 'Requirements for Receptionist Position', 48000.00, 'OFFICE', 'ACTIVE', 'contact@example.com', UUID_TO_BIN('88a71c7e-d011-40e3-b9b5-78315c983b21')),
    (UUID_TO_BIN('6f8fdfd4-419c-46a7-9ba2-33a32723cd6c'), 'SALES_MANAGER', 'Analyst Position Description', 'Requirements for Analyst Position', 75000.00, 'SALES_AGENT_ROUTE', 'ACTIVE', 'contact@example.com', UUID_TO_BIN('2e88a78d-b4a7-4a00-b590-4d0f7abe6c04'));

-- Inserting employee vacation data into the "vacations" table
INSERT INTO vacations (vac_id, vac_start_date, vac_end_date, vac_type, vac_category, substitution_emp_id, emp_id)
VALUES
    (UUID_TO_BIN('99b46424-5a60-45a0-b378-cbe9f8a49c73'), '2024-04-01', '2024-04-10', TRUE, 'ANNUAL', UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'), UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064')),
    (UUID_TO_BIN('45c423a1-2a2f-4d2e-b5f3-9a844866164d'), '2024-05-15', '2024-05-25', TRUE, 'MATERNITY', UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'), UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38')),
    (UUID_TO_BIN('530ddcad-d61d-4fc1-9614-3d3f526f3675'), '2024-06-10', '2024-06-20', TRUE, 'PARENTAL', UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064'), UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700')),
    (UUID_TO_BIN('7df490a2-08a9-4c44-8fa0-476c904cd4d5'), '2024-07-01', '2024-07-10', FALSE, 'UNPAID', UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'), UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c')),
    (UUID_TO_BIN('0628ad72-9f21-4dd4-98ea-ee08bcfbd36e'), '2024-08-15', '2024-08-25', TRUE, 'OTHER', UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c'), UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd'));

-- Inserting addresses into the "addresses" table
INSERT INTO addresses (address_id, country, city, street, house_number, apart_number, address_type, pers_info_id)
VALUES
    (UUID_TO_BIN('b23a92eb-398c-4ba9-9680-b4b3a72a910d'), 'Germany', 'Berlin', 'Hauptstrasse', '123', 'Wohnung 101', 'HOME', UUID_TO_BIN('caee169a-b588-4575-8cfd-4b9c2e711cbe')),
    (UUID_TO_BIN('15ef8acb-0980-4074-a8be-0dbb1589ef02'), 'Germany', 'Munich', 'Schlossstrasse', '456', 'Büro 201', 'WORK', UUID_TO_BIN('7a3cc0fb-6d9e-453a-b329-29796e1b3045')),
    (UUID_TO_BIN('0b751135-128c-46c9-b554-8c6e05bcd2d8'), 'Germany', 'Hamburg', 'Am Markt', '789', 'Büro 301', 'WORK', UUID_TO_BIN('2ba57f16-7ac3-4007-97d5-9d6d6f246e95')),
    (UUID_TO_BIN('53fb4d7f-1f5c-44ae-bf0b-1087275c04dc'), 'Germany', 'Cologne', 'Königsallee', '1010', 'Büro 401', 'WORK', UUID_TO_BIN('1f486486-97dc-4f50-8fb1-cd87d5dd37e1')),
    (UUID_TO_BIN('3c1ed47c-e835-4096-a7e0-73c765b44a64'), 'Germany', 'Frankfurt', 'Bahnhofstrasse', '222', 'Wohnung 102', 'HOME', UUID_TO_BIN('b514d190-b72f-4dd5-948c-d871c3cc1d0b'));

-- Inserting data about company events into the "events" table
INSERT INTO events (ev_id, ev_type, start_date_time, location, description)
VALUES
    (UUID_TO_BIN('92683b96-579e-4fee-9329-b442639582e7'), 'CONFERENCE', '2024-04-20 09:00:00', 'Conference Hall A', 'Annual company conference'),
    (UUID_TO_BIN('d4379c09-f871-45a1-93b4-a52d9f91ac57'), 'SEMINAR', '2024-05-10 13:00:00', 'Seminar Room B', 'Sales techniques seminar'),
    (UUID_TO_BIN('ea243166-62d3-4fa1-b168-ae9fd0dc5d6b'), 'HOLIDAY', '2024-06-01 00:00:00', 'Beach Resort', 'Company summer holiday'),
    (UUID_TO_BIN('f6f19f38-1597-4422-a35b-1ba8008996da'), 'TRAINING', '2024-07-15 10:00:00', 'Training Center', 'New employee orientation'),
    (UUID_TO_BIN('e4931cb9-2dea-4a75-bdf2-262d7361f343'), 'EMPLOYEE_BIRTHDAY', '2024-08-10 00:00:00', 'Office', 'Employee birthday celebration');

-- Inserting data into the "events_employee" table. Linking events and employees
INSERT INTO events_employee (ev_id, emp_id)
VALUES
    (UUID_TO_BIN('92683b96-579e-4fee-9329-b442639582e7'), UUID_TO_BIN('7270910c-cc71-4634-97a0-a242eb5b6064')), -- Michael Johnson
    (UUID_TO_BIN('d4379c09-f871-45a1-93b4-a52d9f91ac57'), UUID_TO_BIN('dfb0689f-5f69-4825-a154-33c897fa1b38')), -- Emily Anderson
    (UUID_TO_BIN('ea243166-62d3-4fa1-b168-ae9fd0dc5d6b'), UUID_TO_BIN('55035fe9-37e3-466f-ba4a-197f23fc5700')), -- Daniel Brown
    (UUID_TO_BIN('e4931cb9-2dea-4a75-bdf2-262d7361f343'), UUID_TO_BIN('cd8edecd-0d27-4228-8fe6-911c1cf7fd7c')), -- Olivia Martinez
    (UUID_TO_BIN('92683b96-579e-4fee-9329-b442639582e7'), UUID_TO_BIN('7881bf3e-73a9-47da-8bae-e2e253a30ddd')); -- William Wilson

-- Inserting data into the "pers_info_role" table. Linking Users and Roles
INSERT INTO pers_info_role (pers_info_id, role_id)
VALUES
    (UUID_TO_BIN('caee169a-b588-4575-8cfd-4b9c2e711cbe'), UUID_TO_BIN('541eac0e-4609-47b7-8fae-bc55c44ec18d')),
    (UUID_TO_BIN('7a3cc0fb-6d9e-453a-b329-29796e1b3045'), UUID_TO_BIN('64d1e267-7034-4c72-989b-0e3214f264ce')),
    (UUID_TO_BIN('2ba57f16-7ac3-4007-97d5-9d6d6f246e95'), UUID_TO_BIN('2a4e17fe-75af-4e05-be97-0d08089f59b0')),
    (UUID_TO_BIN('1f486486-97dc-4f50-8fb1-cd87d5dd37e1'), UUID_TO_BIN('64d1e267-7034-4c72-989b-0e3214f264ce')),
    (UUID_TO_BIN('b514d190-b72f-4dd5-948c-d871c3cc1d0b'), UUID_TO_BIN('64d1e267-7034-4c72-989b-0e3214f264ce'));

-- Inserting data into the "role_authority" table. Linking roles and rights
INSERT INTO role_authority (role_id, auth_id)
VALUES
    (UUID_TO_BIN('541eac0e-4609-47b7-8fae-bc55c44ec18d'), UUID_TO_BIN('fcbf07ae-7d86-4a16-935a-36ee7ac89b02')),
    (UUID_TO_BIN('64d1e267-7034-4c72-989b-0e3214f264ce'), UUID_TO_BIN('cfd9f19b-c9e2-4953-ac31-c61803c0baa3')),
    (UUID_TO_BIN('2a4e17fe-75af-4e05-be97-0d08089f59b0'), UUID_TO_BIN('16001a26-ce5f-423a-afde-4bea256cec90')),
    (UUID_TO_BIN('10ffff51-6e14-4e87-8421-33f4e53f38ac'), UUID_TO_BIN('fcbf07ae-7d86-4a16-935a-36ee7ac89b02')),
    (UUID_TO_BIN('64d1e267-7034-4c72-989b-0e3214f264ce'), UUID_TO_BIN('a1a62aae-38fa-4900-b392-c2ce5afbb5da'));


