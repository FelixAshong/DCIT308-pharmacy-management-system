CREATE DATABASE pharmacy_management;

USE pharmacy_management;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);

-- Inserting a sample user (username: admin, password: admin)
INSERT INTO users (username, password) VALUES ('admin', 'admin');


CREATE TABLE drugs (
    drug_id INT AUTO_INCREMENT PRIMARY KEY,
    drug_name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);

CREATE TABLE suppliers (
    supplier_id INT AUTO_INCREMENT PRIMARY KEY,
    supplier_name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL,
    contact_info VARCHAR(100)
);

CREATE TABLE drug_suppliers (
    drug_id INT,
    supplier_id INT,
    PRIMARY KEY (drug_id, supplier_id),
    FOREIGN KEY (drug_id) REFERENCES drugs(drug_id),
    FOREIGN KEY (supplier_id) REFERENCES suppliers(supplier_id)
);

CREATE TABLE purchases (
    purchase_id INT AUTO_INCREMENT PRIMARY KEY,
    drug_id INT,
    customer_name VARCHAR(100),
    purchase_date DATETIME,
    quantity INT,
    total_price DECIMAL(10, 2),
    FOREIGN KEY (drug_id) REFERENCES drugs(drug_id)
);


-- Batch insert drugs query
INSERT INTO drugs (drug_name, description, price, stock) VALUES
('Paracetamol', 'Used to treat pain and fever.', 2.50, 100),
('Ibuprofen', 'Nonsteroidal anti-inflammatory drug (NSAID) used to reduce fever and treat pain or inflammation.', 3.00, 200),
('Amoxicillin', 'Antibiotic used to treat a number of bacterial infections.', 5.00, 150),
('Lisinopril', 'Used to treat high blood pressure and heart failure.', 4.50, 80),
('Metformin', 'Medication used to treat type 2 diabetes.', 7.25, 120);
