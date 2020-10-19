CREATE DATABASE IF NOT EXISTS BookBayDBPrototype;

USE BookBayDBPrototype;

CREATE TABLE IF NOT EXISTS UserType (
	TypeID int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(TypeID),
    # Might not be needed since TypeID will probably
    # be all that is necessary in the actual Java program
    TypeName varchar(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS `User` (
	UserID int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(UserID),
    FirstName varchar(255) NOT NULL,
    LastName varchar(255) NOT NULL,
	Email varchar(255) NOT NULL,
    `Password` varchar(255) NOT NULL UNIQUE,
    `Status` int UNIQUE, # Based on the Status enumeration
    EnrollmentForPromotions boolean,
    TypeID int,
    FOREIGN KEY(TypeID) REFERENCES UserType(TypeID)
);

CREATE TABLE IF NOT EXISTS PaymentCard (
	CardNum int NOT NULL,
    PRIMARY KEY(CardNum),
    `Type` int NOT NULL UNIQUE,
    ExpDate varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Book (
	BookID int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(BookID),
    Title varchar(255) NOT NULL,
    Author varchar(255) NOT NULL,
    SellingPrice float NOT NULL,
    ISBN int,
    Genre varchar(255),
    `Description` varchar(255),
    PublicationYear int,
    ImagePath varchar(255),
    BuyPrice float NOT NULL,
    Edition int,
    Publisher varchar(255),
    Quantity int NOT NULL,
    MinThreshold int
);

CREATE TABLE IF NOT EXISTS `Order` (
	OrderID int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(OrderID),
    UserID int,
    GrandTotal float,
    promoID int,
    OrderDateTime varchar(255)
);

CREATE TABLE IF NOT EXISTS ShoppingCart (
	CartID int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(CartID),
    UserID int,
    OrderID int,
	FOREIGN KEY(UserID) REFERENCES `User`(UserID),
    FOREIGN KEY(OrderID) REFERENCES `Order`(OrderID)
);

CREATE TABLE IF NOT EXISTS Promotion (
	`Code` int NOT NULL,
    PRIMARY KEY(`Code`),
    Percentage int NOT NULL,
    ExpDate varchar(255),
    StartDate varchar(255)
);
