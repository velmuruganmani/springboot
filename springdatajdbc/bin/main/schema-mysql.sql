CREATE DATABASE springdatajdbc;
SHOW DATABASES;

USE springdatajdbc;
CREATE TABLE customers(  
cus_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,  
cus_login VARCHAR(255) NOT NULL unique,  
cus_first_name VARCHAR(100) NOT NULL,
cus_last_name VARCHAR(100) NOT NULL,
cus_telephone VARCHAR(100) NOT NULL,
cus_email VARCHAR(100) NOT NULL,
cus_status INT NOT NULL,
cus_iso_code VARCHAR(100) NOT NULL,
cus_fax VARCHAR(100) NOT NULL,
cus_language VARCHAR(100) NOT NULL,
cus_serial_no INT NOT NULL
) ENGINE=InnoDB;
insert into springdatajdbc.customers values(
99,"velmani","Vel","Murugan",1234567,"velmurugan@outlook.com",0,"EUR","123","FR",0);

USE springdatajdbc;
CREATE TABLE applications(  
app_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  
app_name VARCHAR(255) NOT NULL
) ENGINE=InnoDB;
insert into springdatajdbc.applications values(1,"Customers Application 1");
insert into springdatajdbc.applications values(2,"Customers Application 2");
insert into springdatajdbc.applications values(3,"Customers Application 3");

USE springdatajdbc;
CREATE TABLE access(  
access_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,  
access_name VARCHAR(255) NOT NULL
) ENGINE=InnoDB;
insert into springdatajdbc.access values(1,"Admin");
insert into springdatajdbc.access values(2,"Writer");
insert into springdatajdbc.access values(3,"Reader");
insert into springdatajdbc.access values(4,"None");

USE springdatajdbc;
CREATE TABLE applications_config(  
app_config_id INT NOT NULL,  
app_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,  
app_config_name VARCHAR(255) NOT NULL,
FOREIGN KEY(app_id) REFERENCES applications(app_id)
) ENGINE=InnoDB;
insert into springdatajdbc.applications_config values(1,1,'All');
insert into springdatajdbc.applications_config values(1,2,'All');
insert into springdatajdbc.applications_config values(1,3,'All');

USE springdatajdbc;
CREATE TABLE customers_access(  
cus_id INT NOT NULL,  
appconfig_id INT NOT NULL,  
app_id INT NOT NULL ,  
access_id INT NOT NULL, 
FOREIGN KEY(app_id) REFERENCES applications(app_id), 
FOREIGN KEY(appconfig_id) REFERENCES applications_config(app_id), 
FOREIGN KEY(access_id) REFERENCES access(access_id),
FOREIGN KEY(cus_id) REFERENCES customers(cus_id)
) ENGINE=InnoDB;
insert into springdatajdbc.customers_access values 
(99,1,1,1),(99,1,2,1),(99,1,3,1),(99,2,1,1),(99,3,1,3),(99,3,2,3),(99,3,3,3);