CREATE TABLE users(
    id INT  PRIMARY KEY   NOT NULL AUTO_INCREMENT ,
    name TEXT NOT NULL,
    surname TEXT ,
    email CHAR(255) ,
    birthdate DATE NOT NULL
);
