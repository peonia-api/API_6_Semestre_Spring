DROP SCHEMA IF EXISTS login;
DROP USER IF EXISTS 'user'@'localhost';

CREATE SCHEMA login;

CREATE USER 'user'@'localhost' IDENTIFIED BY 'pass123';

GRANT SELECT, INSERT, DELETE, UPDATE ON spring.* TO 'user'@'localhost';

USE login;

CREATE TABLE usr_user (
  usr_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  usr_name VARCHAR(255) NOT NULL,
  usr_surname VARCHAR(255),
  usr_password VARCHAR(150) NOT NULL,
  usr_email VARCHAR(255),
  usr_function VARCHAR(255),
  PRIMARY KEY (usr_id),
  UNIQUE KEY uni_usr_email (usr_email),
  UNIQUE KEY uni_usr_name (usr_name)
);
