-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 05, 2023 at 06:24 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `fnkokamn_terpdb`
--
CREATE DATABASE IF NOT EXISTS `fnkokamn_terpdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `fnkokamn_terpdb`;

-- --------------------------------------------------------

--
-- Table structure for table `ASSIGNMENT`
--
-- Creation: May 05, 2023 at 04:23 PM
--

DROP TABLE IF EXISTS `ASSIGNMENT`;
CREATE TABLE IF NOT EXISTS `ASSIGNMENT` (
  `ASSIGNMENTID` varchar(100) NOT NULL,
  `CID` varchar(100) NOT NULL,
  `ASSIGNTITLE` varchar(100) DEFAULT NULL,
  `DUEDATE` date NOT NULL,
  `MAXPOINTS` float NOT NULL,
  PRIMARY KEY (`ASSIGNMENTID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `ASSIGNMENT`:
--

--
-- Truncate table before insert `ASSIGNMENT`
--

TRUNCATE TABLE `ASSIGNMENT`;
-- --------------------------------------------------------

--
-- Table structure for table `COURSE`
--
-- Creation: May 05, 2023 at 04:23 PM
--

DROP TABLE IF EXISTS `COURSE`;
CREATE TABLE IF NOT EXISTS `COURSE` (
  `CID` varchar(100) NOT NULL,
  `COURSETITLE` varchar(100) DEFAULT NULL,
  `SEMESTER` varchar(100) DEFAULT NULL,
  `YEAR` date NOT NULL,
  `CREDIT` int(10) UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`CID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `COURSE`:
--

--
-- Truncate table before insert `COURSE`
--

TRUNCATE TABLE `COURSE`;
-- --------------------------------------------------------

--
-- Table structure for table `STUDENT`
--
-- Creation: May 05, 2023 at 04:20 PM
-- Last update: May 05, 2023 at 04:20 PM
--

DROP TABLE IF EXISTS `STUDENT`;
CREATE TABLE IF NOT EXISTS `STUDENT` (
  `UID` varchar(100) NOT NULL,
  `USERNAME` varchar(100) DEFAULT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  `DOB` date NOT NULL,
  `FIRSTNAME` varchar(100) DEFAULT NULL,
  `MIDDLENAME` varchar(100) DEFAULT NULL,
  `LASTNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `STUDENT`:
--

--
-- Truncate table before insert `STUDENT`
--

TRUNCATE TABLE `STUDENT`;
--
-- Dumping data for table `STUDENT`
--

INSERT INTO `STUDENT` VALUES('48332382', 'mfisher', 'fhsgd', '2000-03-23', 'Mary', 'Rose', 'Fisher');
INSERT INTO `STUDENT` VALUES('5835653', 'ccharlotte', 'hghd5435sdf', '1999-11-05', 'Christine', 'Beane', 'Charlotte');
INSERT INTO `STUDENT` VALUES('7698560', 'jsilverman', 'sh3425', '2001-06-14', 'John', 'Kand', 'Silverman');

-- --------------------------------------------------------

--
-- Table structure for table `STUDENTSUBMITSASSIGNMENT`
--
-- Creation: May 05, 2023 at 04:20 PM
--

DROP TABLE IF EXISTS `STUDENTSUBMITSASSIGNMENT`;
CREATE TABLE IF NOT EXISTS `STUDENTSUBMITSASSIGNMENT` (
  `SUBMISSIONID` varchar(100) NOT NULL,
  `ASSIGNMENTID` varchar(100) NOT NULL,
  `CID` varchar(100) NOT NULL,
  `STUDENTUID` varchar(100) NOT NULL,
  `SUBMISSIONDATE` date NOT NULL,
  `ASSIGNMENTGRADE` float NOT NULL,
  PRIMARY KEY (`SUBMISSIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- RELATIONSHIPS FOR TABLE `STUDENTSUBMITSASSIGNMENT`:
--

--
-- Truncate table before insert `STUDENTSUBMITSASSIGNMENT`
--

TRUNCATE TABLE `STUDENTSUBMITSASSIGNMENT`;SET FOREIGN_KEY_CHECKS=1;
COMMIT;
