--liquibase formatted sql

--changeset liquibase:10

-- Inserting data about company events into the "events" table

INSERT INTO events (ev_id, ev_type, start_date_time, location, description)
VALUES
    (UUID_TO_BIN('92683b96-579e-4fee-9329-b442639582e7'), 'CONFERENCE', '2024-04-20 09:00:00', 'Conference Hall A', 'Annual company conference'),
    (UUID_TO_BIN('d4379c09-f871-45a1-93b4-a52d9f91ac57'), 'SEMINAR', '2024-05-10 13:00:00', 'Seminar Room B', 'Sales techniques seminar'),
    (UUID_TO_BIN('ea243166-62d3-4fa1-b168-ae9fd0dc5d6b'), 'HOLIDAY', '2024-06-01 00:00:00', 'Beach Resort', 'Company summer holiday'),
    (UUID_TO_BIN('f6f19f38-1597-4422-a35b-1ba8008996da'), 'TRAINING', '2024-07-15 10:00:00', 'Training Center', 'New employee orientation'),
    (UUID_TO_BIN('e4931cb9-2dea-4a75-bdf2-262d7361f343'), 'EMPLOYEE_BIRTHDAY', '2024-08-10 00:00:00', 'Office', 'Employee birthday celebration');
