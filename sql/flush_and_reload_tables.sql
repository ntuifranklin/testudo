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
CREATE TABLE IF NOT EXISTS ASSIGNMENT (  
 CID VARCHAR(100) NOT NULL PRIMARY KEY,  
 COURSETITLE VARCHAR(100),  
 SEMESTER VARCHAR(100),   
 YEAR DATE NOT NULL
) ;

INSERT INTO STUDENT VALUES (
    '48332382',
    'mfisher',
    'fhsgdfj473fdg',
    '2000-03-23',
    'Mary',
    'Rose',
    'Fisher'
);
INSERT INTO STUDENT VALUES (
    '7698560',
    'jsilverman',
    'sh34252vfsdf', 
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


