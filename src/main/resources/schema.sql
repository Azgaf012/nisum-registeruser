CREATE TABLE UserApp (
                         id UUID PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) UNIQUE NOT NULL,
                         created TIMESTAMP(6) NOT NULL,
                         updated TIMESTAMP(6) NOT NULL,
                         last_login TIMESTAMP(6) NOT NULL
);

CREATE TABLE Phone (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       number VARCHAR(20) NOT NULL,
                       cityCode INT,
                       countryCode INT,
                       userId UUID,
                       FOREIGN KEY (userId) REFERENCES UserApp(id)
);




