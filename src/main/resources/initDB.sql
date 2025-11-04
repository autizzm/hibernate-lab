CREATE DATABASE IF NOT EXISTS hibernate;
USE hibernate;
CREATE TABLE IF NOT EXISTS developers (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name`  varchar(50) DEFAULT NULL,
  `specialty` varchar(50) DEFAULT NULL,
  `experience` int DEFAULT NULL
)

CREATE TABLE IF NOT EXISTS departments (
    department_id CHAR(3) PRIMARY KEY CHECK (LENGTH(department_id) = 3),
    department_name VARCHAR(100) NOT NULL UNIQUE,
    location TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    user_role ENUM('ROLE_ADMIN', 'ROLE_USER') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES developers(id)
);
