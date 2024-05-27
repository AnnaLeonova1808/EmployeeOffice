--liquibase formatted sql

--changeset liquibase:3

-- Inserting personal data into the "personal_info" table
INSERT INTO personal_info (pers_info_id, username, birthday, phone_number, email, password, salary)
VALUES
    (UUID_TO_BIN('caee169a-b588-4575-8cfd-4b9c2e711cbe'), 'michael', '1992-06-30', '+12-345-678-90-12', 'michael@example.com', 'd2ecce31ab3f5a11d0f6d389f68686c75c80ead1857c7f38d21fecd4da1209b5', 55000.00),
    (UUID_TO_BIN('7a3cc0fb-6d9e-453a-b329-29796e1b3045'), 'emily', '1987-09-25', '+12-345-678-90-13', 'emily@example.com', '8dce5f0012304c06d98cfb0cd11bc7eaaafaf32c4da26df424bcdd59ccfe0a5d', 65000.00),
    (UUID_TO_BIN('2ba57f16-7ac3-4007-97d5-9d6d6f246e95'), 'daniel', '1980-12-10', '+12-345-678-90-14', 'daniel@example.com', '15acd26889bccf5865c22aa472ec069cf0a31512f9a53dadc0bc8f2aea3f84cd', 75000.00),
    (UUID_TO_BIN('1f486486-97dc-4f50-8fb1-cd87d5dd37e1'), 'olivia', '1995-04-05', '+12-345-678-90-15', 'olivia@example.com', 'c7b702284ddd187ddb240fa0ee6d77019e280eedbec7290c886a7dd193fb0e99', 45000.00),
    (UUID_TO_BIN('b514d190-b72f-4dd5-948c-d871c3cc1d0b'), 'william', '1990-10-20', '+12-345-678-90-16', 'william@example.com', '37c60d066e0fc140a4751f274112a25d5b263f4f0dec1e6c555ee2178387b1c8', 60000.00);
