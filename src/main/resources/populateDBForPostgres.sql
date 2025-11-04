\c hibernate;

INSERT INTO departments (department_id, department_name, location)
VALUES    ('A01', 'Engineers', 'rondo Daszyńskiego 1, 00-843 Warszawa');
INSERT INTO departments (department_id, department_name, location)
VALUES    ('A07', 'Testers', 'rondo Daszyńskiego 1, 00-843 Warszawa');
INSERT INTO departments (department_id, department_name, location)
VALUES    ('B04', 'Very smart guys', 'J.E. Irausquin Boulevard 20-A Oranjestad, Aruba');

INSERT INTO developers (name, specialty, experience, department_id) VALUES
('Это я', 'Студент', 1, 'B04');
 INSERT INTO developers (name, specialty, experience, department_id) VALUES
('Владимир', 'Java-dev', 5, 'B04');
INSERT INTO developers (name, specialty, experience, department_id) VALUES
('Серый', 'Scrum-мастер', 3, 'A01');

INSERT INTO users VALUES (1, 'admin', 'admin', 'ROLE_ADMIN');
INSERT INTO users VALUES (2, 'user', 'user', 'ROLE_USER');
