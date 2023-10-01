CREATE TABLE UserApp (
                         id UUID PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL
);

CREATE TABLE Phone (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       number VARCHAR(20) NOT NULL,
                       cityCode INT,
                       countryCode INT,
                       userId UUID,
                       FOREIGN KEY (userId) REFERENCES UserApp(id)
);




