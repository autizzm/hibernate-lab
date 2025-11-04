USE hibernate;

INSERT INTO departments (department_id, department_name, location)
VALUES
    ('A01', 'Инженеры', 'rondo Daszyńskiego 1, 00-843 Warszawa'),
    ('A07', 'Тестировщики', 'rondo Daszyńskiego 1, 00-843 Warszawa'),
    ('B04', 'Очень умные человеки', 'J.E. Irausquin Boulevard 20-A Oranjestad, Aruba');

INSERT INTO developers (name, specialty, experience, department_id) VALUES
 ('Это я', 'Студент', 1, 'B04'), ('Владимир', 'Java-dev', 5, 'B04'), ('Серый', 'Scrum-мастер', 3, 'A01');
INSERT INTO users VALUES (1, 'admin', 'admin', 'ROLE_ADMIN'), (2, 'user', 'user', 'ROLE_USER');
