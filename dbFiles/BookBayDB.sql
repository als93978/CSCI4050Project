CREATE DATABASE IF NOT EXISTS BookBayDB;

USE BookBayDB;

CREATE TABLE IF NOT EXISTS UserType (
	TypeID int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(TypeID),
    # Might not be needed since TypeID will probably
    # be all that is necessary in the actual Java program
    TypeName varchar(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS Address (
	AddressID int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(AddressID),
    Street varchar(255),
    City varchar(255),
    State varchar(255),
    ZipCode int
);

CREATE TABLE IF NOT EXISTS `User` (
	UserID int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(UserID),
    FirstName varchar(255) NOT NULL,
    LastName varchar(255) NOT NULL,
	Email varchar(255) NOT NULL UNIQUE,
    `Password` varchar(255) NOT NULL UNIQUE,
    `Status` ENUM('ACTIVE', 'INACTIVE', 'SUSPENDED') NOT NULL,
    EnrollmentForPromotions boolean,
    NumOfCards int DEFAULT 0,
    CHECK (NumOfCards <= 3), # User should have up to 3 cards (or none)
    ConfirmationCode varchar(255),
    `Type` ENUM('ADMIN', 'CUSTOMER', 'EMPLOYEE') NOT NULL,
    AddressID int,
    FOREIGN KEY(AddressID) REFERENCES Address(AddressID)
);

CREATE TABLE IF NOT EXISTS PaymentCard (
	CardNum varchar(255) NOT NULL,
    PRIMARY KEY(CardNum),
    `Type` ENUM('DISCOVER', 'VISA', 'MASTERCARD', 'AMERICANEXPRESS') NOT NULL,
    ExpDate varchar(255) NOT NULL,
    UserID int UNIQUE,
    FOREIGN KEY(UserID) REFERENCES `User`(UserID)
);

CREATE TABLE IF NOT EXISTS Book (
	BookID int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(BookID),
    Title varchar(255) NOT NULL,
    Author varchar(255) NOT NULL,
    SellingPrice float NOT NULL,
    ISBN varchar(255) UNIQUE,
    Genre varchar(255),
    `Description` text,
    PublicationYear int,
    ImagePath varchar(255),
    BuyPrice float NOT NULL,
    Edition int,
    Publisher varchar(255),
    Quantity int NOT NULL,
    MinThreshold int,
    Archived boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS ManageBooks (
	UserID int,
    FOREIGN KEY(UserID) REFERENCES `User`(UserID),
    BookID int,
    FOREIGN KEY(BookID) REFERENCES Book(BookID)
);

CREATE TABLE IF NOT EXISTS Promotion (
	`PromotionCode` int NOT NULL,
    PRIMARY KEY(`PromotionCode`),
    Percentage int NOT NULL,
    ExpDate varchar(255),
    StartDate varchar(255)
);

CREATE TABLE IF NOT EXISTS `Order` (
	OrderID int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(OrderID),
    UserID int,
    FOREIGN KEY(UserID) REFERENCES `User`(UserID),
    TotalPrice float,
    promoID int,
    OrderDateTime varchar(255),
    PaymentMethod varchar(255),
    CardNum varchar(255),
    FOREIGN KEY(CardNum) REFERENCES PaymentCard(CardNum),
    `PromotionCode` int,
    FOREIGN KEY(`PromotionCode`) REFERENCES Promotion(`PromotionCode`)
);

CREATE TABLE IF NOT EXISTS OrderItem (
	BookID int,
    FOREIGN KEY(BookID) REFERENCES Book(BookID),
    OrderID int,
    FOREIGN KEY(OrderID) REFERENCES `Order`(OrderID),
    Quantity int
);

CREATE TABLE IF NOT EXISTS ShoppingCart (
	CartID int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY(CartID),
    UserID int NOT NULL UNIQUE,
    OrderID int UNIQUE,
	FOREIGN KEY(UserID) REFERENCES `User`(UserID),
    FOREIGN KEY(OrderID) REFERENCES `Order`(OrderID)
);

CREATE TABLE IF NOT EXISTS ManagePromotions (
	UserID int NOT NULL,
    `PromotionCode` int,
    FOREIGN KEY(UserID) REFERENCES `User`(UserID),
    FOREIGN KEY(`PromotionCode`) REFERENCES Promotion(`PromotionCode`)
);

INSERT INTO `User`(FirstName, LastName, Email, Password, `Status`, `Type`)
VALUES("Austin", "Schultz", "aschultz086@gmail.com", sha2("lordfarquaad", 256), "ACTIVE", "ADMIN");

INSERT INTO `User`(FirstName, LastName, Email, Password, `Status`, `Type`)
VALUES("David", "Doan", "daviddoan@bookbay.com", sha2("daviddoanpassword", 256), "ACTIVE", "ADMIN");

INSERT INTO `User`(FirstName, LastName, Email, Password, `Status`, `Type`)
VALUES("Devin", "Hajjari", "devinhajjari@bookbay.com", sha2("devinhajjaripassword", 256), "ACTIVE", "ADMIN");

INSERT INTO `User`(FirstName, LastName, Email, Password, `Status`, `Type`)
VALUES("Jonah", "Kim", "jonahkim@bookbay.com", sha2("jonahkimpassword", 256), "ACTIVE", "ADMIN");

INSERT INTO `User`(FirstName, LastName, Email, Password, `Status`, `Type`)
VALUES("Captain", "Alex", "sojey61001@0335g.com", sha2("whokilledme", 256), "ACTIVE", "EMPLOYEE");

INSERT INTO `User`(FirstName, LastName, Email, Password, `Status`, `Type`)
VALUES("Marcus", "Aurelius", "marcusaurelius@spqr.com", sha2("amorfati", 256), "ACTIVE", "CUSTOMER");

INSERT INTO `User`(FirstName, LastName, Email, Password, `Status`, `Type`)
VALUES("George", "Costanza", "georgeconstanza@seinfeld.com", sha2("lordoftheidiots", 256), "ACTIVE", "CUSTOMER");

INSERT INTO `User`(FirstName, LastName, Email, Password, `Status`, `Type`)
VALUES("Cosmo", "Kramer", "kramer@kramericaindustries.com", sha2("kramerica", 256), "ACTIVE", "CUSTOMER");

INSERT INTO Book(Title, Author, SellingPrice, ISBN, Genre, `Description`, PublicationYear, ImagePath, BuyPrice,
				 Publisher, Quantity, MinThreshold, Archived)
VALUES("Harry Potter and the Sorcerer's Stone", "J. K. Rowling", 4.99, "0-7475-3269-9", "Fantasy", 
"The first novel of the Harry Potter series.",
1998, "img/book1.png", 2.99, "Scholastic", 50, 10, false);

INSERT INTO Book(Title, Author, SellingPrice, ISBN, Genre, `Description`, PublicationYear, ImagePath, BuyPrice,
				 Publisher, Quantity, MinThreshold, Archived)
VALUES("The Outsider", "Stephen King", 14.99, "978-1501180989", "Horror", 
"A thrilling crime novel by Stephen King.",
2018, "img/book2.png", 7.99, "Scribner", 15, 5, false);

INSERT INTO Book(Title, Author, SellingPrice, ISBN, Genre, `Description`, PublicationYear, ImagePath, BuyPrice,
				 Publisher, Quantity, MinThreshold, Archived)
VALUES("Crazy Rich Asians", "Kevin Kwan", 10.99, "978-0-385-53697-4", "Romantic Comedy", 
"A comedic novel about contemporary Asian culture.",
2013, "img/book3.png", 4.99, "Anchor", 50, 10, false);

INSERT INTO Book(Title, Author, SellingPrice, ISBN, Genre, `Description`, PublicationYear, ImagePath, BuyPrice,
				 Publisher, Quantity, MinThreshold, Archived)
VALUES("Little Fires Everywhere", "Celeste Ng", 11.99, "0735224293", "Fiction", 
"An interesting and dramatic novel set in a small Ohio town.",
2017, "img/book4.png", 5.99, "Penguin Press", 20, 5, false);