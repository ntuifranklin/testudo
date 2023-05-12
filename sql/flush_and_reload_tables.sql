DROP DATABASE IF EXISTS `fnkokamn_terpdb` ;
CREATE DATABASE IF NOT EXISTS `fnkokamn_terpdb` ;
USE `fnkokamn_terpdb` ;
DROP TABLE IF EXISTS ASSIGNMENT ;
DROP TABLE IF EXISTS COURSE ;
DROP TABLE IF EXISTS STUDENT ;
CREATE TABLE IF NOT EXISTS STUDENT  (  
 UID VARCHAR(100) NOT NULL PRIMARY KEY,  
 USERNAME VARCHAR(100),  
 PASSWORD VARCHAR(100),   
 DOB DATE NOT NULL,  
 FIRSTNAME VARCHAR(100),  
 MIDDLENAME VARCHAR(100),    
 LASTNAME VARCHAR(100)
) ;
CREATE TABLE IF NOT EXISTS COURSE  (  
 CID VARCHAR(100) NOT NULL PRIMARY KEY,  
 COURSETITLE VARCHAR(100),  
 SEMESTER VARCHAR(100),   
 YEAR DATE NOT NULL,
 CREDIT INT UNSIGNED
) ;

CREATE TABLE IF NOT EXISTS `ASSIGNMENT` (
  `ASSIGNMENTID` varchar(100) PRIMARY KEY NOT NULL,
  `ASSIGNTITLE` varchar(100) DEFAULT NULL,
  `WEIGHT` FLOAT NOT NULL,
  `STUDENTUID` VARCHAR(100) NOT NULL REFERENCES STUDENT(`UID`),
  `EARNEDGRADE` FLOAT NOT NULL
) ;


INSERT INTO STUDENT VALUES (
    '48332382',
    'mfisher',
    'fhsgd',
    '2000-03-23',
    'Mary',
    'Rose',
    'Fisher'
);
INSERT INTO STUDENT VALUES (
    '7698560',
    'jsilverman',
    'sh3425', 
    '2001-06-14',
    'John',
    'Kand',
    'Silverman'
);

INSERT INTO STUDENT VALUES (
    '5835653',
    'ccharlotte',
    'hghd5435sdf',
    '1999-11-05',
    'Christine',
    'Beane',
    'Charlotte'
);
INSERT INTO COURSE VALUES (
    'CMSC131',
    'Introduction To Object Oriented Programming I',
    'SPRING',
    '2023-01-01',
    4
);
INSERT INTO COURSE VALUES (
    'CMSC216',
    'Introduction To System Programming In C',
    'SPRING',
    '2023-01-01',
    4
);
INSERT INTO COURSE VALUES (
    'CMSC351',
    'Algorithms',
    'SPRING',
    '2023-01-01',
    3
);
INSERT INTO COURSE VALUES (
    'CMSC132',
    'Introduction To Object Oriented Programming II',
    'SPRING',
    '2023-01-01',
    4
);

INSERT INTO COURSE VALUES (
    'CMSC250',
    'Discrete Mathematics',
    'SPRING',
    '2023-01-01',
    3
);
INSERT INTO COURSE VALUES (
    'CMSC330',
    'Organisation Of Programming Languages',
    'SPRING',
    '2023-01-01',
    3
);

INSERT INTO COURSE VALUES (
    'CMSC412',
    'Operating Systems',
    'SPRING',
    '2023-01-01',
    3
);

INSERT INTO COURSE VALUES (
    'CMSC420',
    'Advanced Data Strctures',
    'SPRING',
    '2023-01-01',
    3
);
INSERT INTO COURSE VALUES (
    'CMSC430',
    'Introduction To Compilers',
    'SPRING',
    '2023-01-01',
    3
);

INSERT INTO COURSE VALUES (
    'CMSC433',
    'Programming Language Technologies and Paradigms',
    'SPRING',
    '2023-01-01',
    3
);

INSERT INTO COURSE VALUES (
    'CMSC436',
    'Programming Handheld Systems',
    'SPRING',
    '2023-01-01',
    3
);

INSERT INTO COURSE VALUES (
    'CMSC451',
    'Design and Analysis of Computer Algorithms',
    'SPRING',
    '2023-01-01',
    3
);

INSERT INTO COURSE VALUES (
    'CMSC456',
    'Introduction to Cryptography',
    'SPRING',
    '2023-01-01',
    3
);
