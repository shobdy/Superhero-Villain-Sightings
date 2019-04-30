DROP DATABASE IF EXISTS HeroVillainSightingsDatabase;
DROP DATABASE IF EXISTS HeroVillainSightingsDatabase_Test;

CREATE DATABASE HeroVillainSightingsDatabase;
CREATE DATABASE HeroVillainSightingsDatabase_Test;

USE HeroVillainSightingsDatabase;

CREATE TABLE Person (
	Person_ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Person_Type varchar(20) NOT NULL,
    Person_Name varchar(30) NOT NULL,
    Person_Description varchar(1024) NULL,
    Person_Power varchar(256) NULL,
    Person_Active boolean NOT NULL DEFAULT 1
);

CREATE TABLE Addresses(
	Address_ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Address_Line1 varchar(50) NOT NULL,
    Address_Line2 varchar(50) NULL,
    Address_City varchar(30) NOT NULL,
    Address_State varchar(30) NOT NULL,
    Address_Zip varchar(10) NOT NULL,
    Address_Country varchar(30) NOT NULL
);

CREATE TABLE Organizations(
	Organization_ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Organization_Name varchar(30) NOT NULL,
    Organization_Description varchar(50) NOT NULL,
    Organization_Address_ID int(10) NOT NULL,
    Organization_Contact_Name varchar(30) NOT NULL,
    Organization_Contact_Phone varchar(20) NULL,
    Organization_Contact_Email varchar(50) NULL,
    Organization_Active boolean NOT NULL DEFAULT 1,
    FOREIGN KEY (Organization_Address_ID) REFERENCES Addresses (Address_ID)
);

CREATE TABLE Locations(
	Location_ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Location_Name varchar(30) NOT NULL,
    Location_Description varchar(1024) NOT NULL,
    Location_Address_ID int(10) NOT NULL,
    Location_Latitude varchar(20) NOT NULL,
    Location_Longitude varchar(20) NOT NULL,
    Location_Active boolean NOT NULL DEFAULT 1,
    FOREIGN KEY (Location_Address_ID) REFERENCES Addresses (Address_ID)
);

CREATE TABLE Person_Location(
	Person_Location_ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Person_ID int(10) NOT NULL,
    Location_ID int(10) NOT NULL,
    Date_Sighted DATE NOT NULL,
    FOREIGN KEY (Person_ID) REFERENCES Person(Person_ID),
    FOREIGN KEY (Location_ID) REFERENCES Locations (Location_ID)
);

CREATE TABLE Person_Organization(
	Person_Organization_ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Person_ID int(10) NOT NULL,
    Organization_ID int(10) NOT NULL,
    FOREIGN KEY (Person_ID) REFERENCES Person(Person_ID),
    FOREIGN KEY (Organization_ID) REFERENCES Organizations (Organization_ID)
);

-- User Setup and table default populate
CREATE TABLE users (
 user_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 username varchar(20) NOT NULL UNIQUE,
 password varchar(100) NOT NULL,
 enabled boolean NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;
--
-- Dumping data for table `users`
--
INSERT INTO users (user_id, username, password, enabled) VALUES
(1, 'admin', '$2a$10$Tr6ltGimm.StYrodeK9ST.k3rvf/GOvE2zXEd7MU1SIydigrbAZre', 1),
(2, 'user', '$2a$10$Tr6ltGimm.StYrodeK9ST.k3rvf/GOvE2zXEd7MU1SIydigrbAZre', 1);
--
-- Table structure for table `authorities`
--
CREATE TABLE authorities (
 authority_id int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 user_id int(11) NOT NULL,
 authority varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Dumping data for table `authorities`
--
INSERT INTO `authorities` (`user_id`, `authority`) VALUES
 (1, 'ROLE_ADMIN'),
 (1, 'ROLE_USER'),
 (2, 'ROLE_USER');
--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
 ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);



USE HeroVillainSightingsDatabase_Test;

CREATE TABLE Person (
	Person_ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Person_Type varchar(20) NOT NULL,
    Person_Name varchar(30) NOT NULL,
    Person_Description varchar(1024) NULL,
    Person_Power varchar(256) NULL,
    Person_Active BOOLEAN NOT NULL DEFAULT 1
);

CREATE TABLE Addresses(
	Address_ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Address_Line1 varchar(50) NOT NULL,
    Address_Line2 varchar(50) NULL,
    Address_City varchar(30) NOT NULL,
    Address_State varchar(30) NOT NULL,
    Address_Zip varchar(10) NOT NULL,
    Address_Country varchar(30) NOT NULL
);

CREATE TABLE Organizations(
	Organization_ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Organization_Name varchar(30) NOT NULL,
    Organization_Description varchar(50) NOT NULL,
    Organization_Address_ID int(10) NOT NULL,
    Organization_Contact_Name varchar(30) NOT NULL,
    Organization_Contact_Phone varchar(20) NULL,
    Organization_Contact_Email varchar(50) NULL,
    Organization_Active boolean NOT NULL DEFAULT 1,
    FOREIGN KEY (Organization_Address_ID) REFERENCES Addresses (Address_ID)
);

CREATE TABLE Locations(
	Location_ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    Location_Name varchar(30) NOT NULL,
    Location_Description varchar(1024) NOT NULL,
    Location_Address_ID int(10) NOT NULL,
    Location_Latitude varchar(20) NOT NULL,
    Location_Longitude varchar(20) NOT NULL,
    Location_Active boolean NOT NULL DEFAULT 1,
    FOREIGN KEY (Location_Address_ID) REFERENCES Addresses (Address_ID)
);

CREATE TABLE Person_Location(
	Person_Location_ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Person_ID int(10) NOT NULL,
    Location_ID int(10) NOT NULL,
    Date_Sighted DATE NOT NULL,
    FOREIGN KEY (Person_ID) REFERENCES Person(Person_ID),
    FOREIGN KEY (Location_ID) REFERENCES Locations (Location_ID)
);

CREATE TABLE Person_Organization(
	Person_Organization_ID int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Person_ID int(10) NOT NULL,
    Organization_ID int(10) NOT NULL,
    FOREIGN KEY (Person_ID) REFERENCES Person(Person_ID),
    FOREIGN KEY (Organization_ID) REFERENCES Organizations (Organization_ID)
);

-- User Setup and table default populate
CREATE TABLE users (
 user_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 username varchar(20) NOT NULL UNIQUE,
 password varchar(100) NOT NULL,
 enabled boolean NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;
--
-- Dumping data for table `users`
--
INSERT INTO users (user_id, username, password, enabled) VALUES
(1, 'admin', '$2a$10$Tr6ltGimm.StYrodeK9ST.k3rvf/GOvE2zXEd7MU1SIydigrbAZre', 1),
(2, 'user', '$2a$10$Tr6ltGimm.StYrodeK9ST.k3rvf/GOvE2zXEd7MU1SIydigrbAZre', 1);
--
-- Table structure for table `authorities`
--
CREATE TABLE authorities (
 authority_id int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
 user_id int(11) NOT NULL,
 authority varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Dumping data for table `authorities`
--
INSERT INTO `authorities` (`user_id`, `authority`) VALUES
 (1, 'ROLE_ADMIN'),
 (1, 'ROLE_USER'),
 (2, 'ROLE_USER');
--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
 ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
 