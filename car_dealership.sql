-- Create Users Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('customer', 'dealership') NOT NULL
);

-- Insert Dummy Users
INSERT INTO users (id, email, password, role)
VALUES
    (1, 'c1', 'john.doe@example.com', 'hashed_password_1', 'customer'),
    (2, 'c1', 'jane.smith@example.com', 'hashed_password_2', 'customer'),
    (3, 'd1', 'dealer.one@example.com', 'hashed_password_3', 'dealership'),
    (4, 'd2', 'dealer.two@example.com', 'hashed_password_4', 'dealership');

-- Create Customers Table
CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    tax_number VARCHAR(20) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert Dummy Customers
INSERT INTO customers (id, user_id, tax_id, first_name, last_name)
VALUES
    (1, 1, 'CUST001', 'John', 'Doe'),
    (2, 2, 'CUST002', 'Jane', 'Smith');

-- Create Corporate Customers Table
CREATE TABLE corporate_customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    company_name VARCHAR(100) NOT NULL,
    tax_number VARCHAR(20) NOT NULL,
    contact_person VARCHAR(100) NOT NULL,
    contact_email VARCHAR(100) NOT NULL,
    contact_phone VARCHAR(15) NOT NULL,
    address VARCHAR(15) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Create Independent Customers Table
CREATE TABLE independent_customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    tax_number VARCHAR(100) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    contact_email VARCHAR(100) NOT NULL,
    contact_phone VARCHAR(15) NOT NULL,
    address VARCHAR(15) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Create Dealerships Table
CREATE TABLE dealerships (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    tax_number VARCHAR(20) NOT NULL,
    name VARCHAR(100) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Insert Dummy Dealerships
INSERT INTO dealerships (id, user_id, tax_number, name)
VALUES
    (1, 3, 'DEAL001', 'Gryffindor Cars'),
    (2, 4, 'DEAL002', 'Slytherin Cars');

-- Create Franchise Dealerships Table
CREATE TABLE franchise_dealerships (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dealership_id INT NOT NULL,
    franchise_name VARCHAR(100) NOT NULL,
    number_of_branches INT NOT NULL,
    FOREIGN KEY (dealership_id) REFERENCES dealerships(id)
);

-- Create Independent Dealerships Table
CREATE TABLE independent_dealerships (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dealership_id INT NOT NULL,
    founding_year YEAR(4) NOT NULL,
    owner_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (dealership_id) REFERENCES dealerships(id)
);

-- Create Cars Table
CREATE TABLE cars (
    id INT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    fuel_type VARCHAR(30) NOT NULL,
    engine VARCHAR(50),
    seats INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    additional_info TEXT,
    stock_quantity INT NOT NULL DEFAULT 0,
    dealership_id INT NOT NULL,
    FOREIGN KEY (dealership_id) REFERENCES dealerships(id)
);

-- Insert Dummy Cars
INSERT INTO cars (id, brand, model, fuel_type, engine, seats, price, additional_info, stock_quantity, dealership_id)
VALUES
    (1, 'Toyota', 'Corolla', 'Gasoline', '1.8L I4', 5, 22000.00, 'Reliable and fuel-efficient', 10, 1),
    (2, 'Honda', 'Civic', 'Gasoline', '2.0L I4', 5, 24000.00, 'Compact sedan with modern features', 8, 1),
    (3, 'Tesla', 'Model 3', 'Electric', 'Dual Motor', 5, 50000.00, 'Electric car with autopilot', 5, 2),
    (4, 'Ford', 'Mustang', 'Gasoline', '5.0L V8', 4, 35000.00, 'Sports car with powerful engine', 3, 2);

-- Create Bookings Table
CREATE TABLE test_drive_bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    car_id INT NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (car_id) REFERENCES cars(id)
);

-- Insert Dummy Test Drive Bookings
INSERT INTO test_drive_bookings (id, customer_id, car_id, date, time)
VALUES
    (1, 1, 1, '2025-01-10', '10:00:00'),
    (2, 1, 2, '2025-01-11', '15:30:00'),
    (3, 2, 3, '2025-01-12', '13:00:00');

-- Create Purchases Table
CREATE TABLE purchases (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    car_id INT NOT NULL,
    purchase_date DATE NOT NULL DEFAULT CURRENT_DATE,
    amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (car_id) REFERENCES cars(id)
);

-- Insert Dummy Purchases
INSERT INTO purchases (id, customer_id, car_id, purchase_date, amount)
VALUES
    (1, 1, 1, '2025-01-05', 22000.00),
    (2, 2, 4, '2025-01-06', 35000.00);