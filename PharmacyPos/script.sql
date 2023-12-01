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
CREATE TABLE IF NOT EXISTS Employee
(
    emp_id           VARCHAR(50)  NOT NULL,
    first_name       VARCHAR(150) NOT NULL,
    last_name        VARCHAR(150) NOT NULL,
    date_of_birth    DATE         NOT NULL,
    age              INT          NOT NULL,
    telephone_number INT          NOT NULL,
    address          VARCHAR(350) NOT NULL,
    job_role         VARCHAR(200) NOT NULL,
    description      VARCHAR(350) NOT NULL,
    CONSTRAINT PRIMARY KEY (emp_id)
);

-- Employee Address Table

select *
from employee;

-- # --------------------------------------
-- # create Drugs table
DROP TABLE IF EXISTS Drugs;
CREATE TABLE Drugs
(
    drug_id         VARCHAR(50)     NOT NULL,
    drug_name       VARCHAR(100)    NOT NULL,
    batch_number    VARCHAR(50)     NOT NULL,
    MFD             DATE            NOT NULL,
    EXD             DATE            NOT NULL,
    drug_quantity   INT             NOT NULL,
    unit_price      DECIMAL(10, 2)  NOT NULL,
    unit_discount   DECIMAL(10, 2)  NOT NULL,
    Supply_id       VARCHAR(50)     NOT NULL,
    description     VARCHAR(350)    NOT NULL,
    CONSTRAINT PRIMARY KEY (drug_id)
);