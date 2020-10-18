CREATE DATABASE IF NOT EXISTS BookBayDBPrototype;

USE BookBayDBPrototype;

CREATE TABLE IF NOT EXISTS UserType (
	TypeID int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(TypeID),
    # Might not be needed since TypeID will probably
    # be all that is necessary in the actual Java program
    TypeName varchar(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS User (
	UserID int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(UserID),
    FirstName varchar(255) NOT NULL,
    LastName varchar(255) NOT NULL,
	Email varchar(255) NOT NULL,
    Password varchar(255) NOT NULL UNIQUE,
    Status int, # Based on the Status enumeration
    TypeID int,
    FOREIGN KEY(TypeID) REFERENCES UserType(TypeID)
);
