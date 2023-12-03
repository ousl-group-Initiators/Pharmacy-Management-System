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
    address VARCHAR(350) NOT NULL,
    job_role VARCHAR(200) NOT NULL,
    description VARCHAR(350) NOT NULL,
    CONSTRAINT PRIMARY KEY (emp_id)
);

-- Employee Address Table

--create Item table
DROP TABLE IF EXISTS Item;

CREATE TABLE IF NOT EXISTS Item(
    item_id VARCHAR(50) NOT NULL,
    item_name VARCHAR(150) NOT NULL,
    item_code VARCHAR(150) NOT NULL,
    manufacture_date DATE NOT NULL,
    expire_date DATE NULL,
    item_qty INT NOT NULL,
    unit_price DOUBLE NOT NULL,
    unit_discount DOUBLE NOT NULL,
    item_description VARCHAR(350) NOT NULL,
    supplier_id VARCHAR (50) NOT NULL,
    CONSTRAINT PRIMARY KEY (item_id)
    --  FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id)
    );


