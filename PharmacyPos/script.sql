SHOW DATABASES;

-- # remove the exists database
DROP DATABASE IF EXISTS Pharmacy;

-- # create a new database pharmacy
CREATE DATABASE IF NOT EXISTS Pharmacy;

-- # use the database
USE Pharmacy;

-- # --------------------------------------
-- # create Employee table
DROP DATABASE IF EXISTS Employee;
CREATE TABLE IF NOT EXISTS Employee(
    nic_number VARCHAR(50) NOT NULL,
    first_name VARCHAR(150) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    date_of_birth DATE NOT NULL,
    age INT NOT NULL,
    telephone_number INT NOT NULL,
    Address VARCHAR(350) NOT NULL,
    job_role VARCHAR(200) NOT NULL,
    description VARCHAR(350) NOT NULL,
    CONSTRAINT PRIMARY KEY (nic_number)
);

-- Employee Address Table

