--liquibase formatted sql

--changeset liquibase:4

-- Inserting work schedule data into the "workSchedules" table

INSERT INTO work_schedules (sched_id, sched_name, is_day_off, start_time, end_time)
VALUES
    (UUID_TO_BIN('f4d730e8-1bef-47ef-ba90-24d28dfc57e6'), 'FIVE_DAY_SHIFT_08_17', FALSE, '08:00:00', '17:00:00'),
    (UUID_TO_BIN('94752fc8-87e8-4cf6-ab17-f91d1720c20d'), 'FIVE_DAY_SHIFT_09_18', FALSE, '09:00:00', '18:00:00'),
    (UUID_TO_BIN('b6892135-bc87-427b-b23e-be0ccafecb7e'), 'SHIFT_WORK', TRUE, '06:00:00', '18:00:00');
