DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO meals (datetime, description, calories, user_id)
VALUES ('2021-06-17 08:00:00.0', 'Завтрак', 500, 100000),
       ('2021-06-17 13:00:00.0', 'Обед', 1000, 100000),
       ('2021-06-17 18:00:00.0', 'Ужин', 500, 100000),
       ('2021-06-16 09:00:00.0', 'Завтрак', 400, 100000),
       ('2021-06-16 13:30:00.0', 'Обед', 1300, 100000),
       ('2021-06-16 18:35:00.0', 'Ужин', 500, 100000),
       ('2021-06-17 09:30:00.0', 'Завтрак', 400, 100001),
       ('2021-06-17 14:30:00.0', 'Обед', 1300, 100001)
