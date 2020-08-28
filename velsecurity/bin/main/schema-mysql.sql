CREATE DATABASE springbootsecurity;
SHOW DATABASES;

USE springbootsecurity;
CREATE TABLE user(  
id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,  
username VARCHAR(255) NOT NULL unique,  
name VARCHAR(100) NOT NULL,
password VARCHAR(1000) NOT NULL
) ENGINE=InnoDB;

USE springbootsecurity;
CREATE TABLE customers(  
cus_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,  
cus_login_id VARCHAR(255) NOT NULL unique,  
cus_name VARCHAR(100) NOT NULL,
cus_password VARCHAR(1000) NOT NULL
) ENGINE=InnoDB;
