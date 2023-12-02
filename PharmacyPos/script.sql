SHOW DATABASES;

-- # remove the exists database
DROP DATABASE IF EXISTS Pharmacy;

-- # create a new database pharmacy
CREATE DATABASE IF NOT EXISTS Pharmacy;

-- # use the database
USE Pharmacy;

-- # --------------------------------------
-- # create Employee table
DROP TABLE IF EXISTS Employee;
CREATE TABLE IF NOT EXISTS Employee(
    emp_id VARCHAR(50) NOT NULL,
    first_name VARCHAR(150) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    date_of_birth DATE NOT NULL,
    age INT NOT NULL,
    telephone_number INT NOT NULL,
    Address VARCHAR(350) NOT NULL,
    job_role VARCHAR(200) NOT NULL,
    description VARCHAR(350) NOT NULL,
    CONSTRAINT PRIMARY KEY (emp_id)
);

-- Employee Address Table
-- # use the database
USE Pharmacy;

-- # create Supplier table
DROP TABLE IF EXISTS Supplier;
CREATE TABLE IF NOT EXISTS Supplier(
    supplier_id VARCHAR(50) NOT NULL,
    first_name VARCHAR(150) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    date_of_birth DATE NOT NULL,
    age INT NOT NULL,
    telephone_number INT NOT NULL,
    Address VARCHAR(350) NOT NULL,
    email VARCHAR(255) NOT NULL,
    description VARCHAR(350) NOT NULL,
    CONSTRAINT PRIMARY KEY (supplier_id)
    );
