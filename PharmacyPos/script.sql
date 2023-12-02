SHOW DATABASES;

-- # remove the exists database
DROP DATABASE IF EXISTS Pharmacy;

-- # create a new database pharmacy
CREATE DATABASE IF NOT EXISTS Pharmacy;

-- # use the database
USE Pharmacy;

-- # --------------------------------------
<<<<<<< HEAD
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
=======
-- # create Login
DROP TABLE IF EXISTS Login;
CREATE TABLE IF NOT EXISTS login(
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR (50) NOT NULL,
    CONSTRAINT PRIMARY KEY (user_name)
);

-- # --------------------------------------
-- # create Jpb Role
DROP TABLE IF EXISTS Jobrole;
CREATE TABLE IF NOT EXISTS Jobrole(
    job_id INT NOT NULL,
    job_role VARCHAR (50) NOT NULL,
    CONSTRAINT PRIMARY KEY (job_id)
);

-- # --------------------------------------
-- # create Registration
DROP TABLE IF EXISTS Registration;
CREATE TABLE IF NOT EXISTS Registration(
    user_name VARCHAR(50) NOT NULL,
    password VARCHAR (50) NOT NULL,
    confirm_password VARCHAR(50) NOT NULL,
    job_role VARCHAR(50) NOT NULL,
    CONSTRAINT PRIMARY KEY (user_name),
    CONSTRAINT FOREIGN KEY (job_role) REFERENCES Jobrole(job_role) ON DELETE CASCADE ON UPDATE CASCADE
);

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
    CONSTRAINT PRIMARY KEY (emp_id),
    CONSTRAINT FOREIGN KEY (job_role) REFERENCES Jobrole(job_role) ON DELETE CASCADE ON UPDATE CASCADE
);

select * from employee;


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

-- # --------------------------------------
-- # create Order table
DROP TABLE IF EXISTS `Order`;
CREATE TABLE `Order`(
  invoice_number VARCHAR(50) NOT NULL,
  cashier_name VARCHAR(100) NOT NULL,
  date DATE NOT NULL,
  time TIME NOT NULL,
  total DOUBLE NOT NULL,
  CONSTRAINT PRIMARY KEY (invoice_number)
);

SELECT * FROM `Order`;
DESC `Order`;

-- # --------------------------------------
-- # create Order_Details table
DROP TABLE IF EXISTS `Order_Details`;
CREATE TABLE IF NOT EXISTS `Order_Details`(
    drug_id VARCHAR(50) NOT NULL,
    invoice_number VARCHAR(50) NOT NULL,
    description VARCHAR(350) NOT NULL,
    unitPrice DOUBLE  NOT NULL,
    qty INT NOT NULL,
    discount DOUBLE,
    total DOUBLE NOT NULL,
    CONSTRAINT PRIMARY KEY (drug_id,invoice_number),
    CONSTRAINT FOREIGN KEY (invoice_number) REFERENCES `Order` (invoice_number) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (drug_id) REFERENCES Drugs (drug_id) ON DELETE CASCADE ON UPDATE CASCADE
);

SELECT * FROM `Order_Details`;
DESC `Order_Details`;

SHOW TABLES;
>>>>>>> d2866314c3555b102d5d518f153681fd0e7824e3
