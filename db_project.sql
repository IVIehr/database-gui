-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 28, 2022 at 08:27 PM
-- Server version: 8.0.27
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `factory`
--

DROP TABLE IF EXISTS `factory`;
CREATE TABLE IF NOT EXISTS `factory` (
  `idfactory` int NOT NULL AUTO_INCREMENT,
  `factoryName` varchar(100) NOT NULL,
  `phoneNo` varchar(45) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zipCode` int DEFAULT NULL,
  `noFinalProducts` int DEFAULT NULL,
  PRIMARY KEY (`idfactory`),
  UNIQUE KEY `factoryName_UNIQUE` (`factoryName`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `factory`
--

INSERT INTO `factory` (`idfactory`, `factoryName`, `phoneNo`, `address`, `zipCode`, `noFinalProducts`) VALUES
(2, 'Factory 1', '09390000000', 'Street no.5', 123, 1),
(3, 'Factory 2', '09390000001', 'Street no.1', 1234, 12),
(4, 'Factory 3', '09390000002', 'Street no.52', 12345, 6),
(5, 'Factory 4', '09390000003', 'Street no.8', 5432, 3),
(6, 'Factory 5', '09390000004', 'Street no.7', 6565, 5);

-- --------------------------------------------------------

--
-- Table structure for table `factoryperson`
--

DROP TABLE IF EXISTS `factoryperson`;
CREATE TABLE IF NOT EXISTS `factoryperson` (
  `idfactoryperson` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `passcode` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `baseSalary` int NOT NULL,
  `phoneNo` int DEFAULT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `factory_id` int DEFAULT NULL,
  PRIMARY KEY (`idfactoryperson`),
  UNIQUE KEY `firstName_UNIQUE` (`firstName`),
  UNIQUE KEY `lastName_UNIQUE` (`lastName`),
  KEY `factory_id` (`factory_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9849 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `factoryperson`
--

INSERT INTO `factoryperson` (`idfactoryperson`, `firstName`, `lastName`, `passcode`, `address`, `baseSalary`, `phoneNo`, `qualification`, `factory_id`) VALUES
(6979, 'Ali', 'Alizade', '89982', 'street no.12', 1000000, 915000000, 'able to do several tasks', 2),
(425, 'mohammad', 'mohammadi', '46545', 'street no.11', 500000, 915000001, 'able to do several tasks', 3),
(268, 'hasan', 'amiri', '98522', 'street no.9', 600000, 915000002, 'able to do several tasks', 4),
(6545, 'kamran', 'hasani', '492', 'street no.8', 580000, 915000003, 'able to do several tasks', 5),
(9848, 'ramin', 'akbari', '8487', 'street no.15', 7800000, 2147483647, 'able to do several tasks', 6);

-- --------------------------------------------------------

--
-- Table structure for table `finalproduct`
--

DROP TABLE IF EXISTS `finalproduct`;
CREATE TABLE IF NOT EXISTS `finalproduct` (
  `idfinalproduct` int NOT NULL AUTO_INCREMENT,
  `finalproductfactory_id` int DEFAULT NULL,
  `finalproductmachine_id` int DEFAULT NULL,
  PRIMARY KEY (`idfinalproduct`),
  KEY `finalproductfactory_id_idx` (`finalproductfactory_id`),
  KEY `finalproductmachine_id_idx` (`finalproductmachine_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `finalproduct`
--

INSERT INTO `finalproduct` (`idfinalproduct`, `finalproductfactory_id`, `finalproductmachine_id`) VALUES
(1, 2, 15),
(6, 4, 58),
(3, 5, 16),
(5, 6, 23);

-- --------------------------------------------------------

--
-- Table structure for table `machine`
--

DROP TABLE IF EXISTS `machine`;
CREATE TABLE IF NOT EXISTS `machine` (
  `idmachine` int NOT NULL AUTO_INCREMENT,
  `machineName` varchar(255) NOT NULL,
  `machineModel` varchar(255) DEFAULT NULL,
  `idmanager` int DEFAULT NULL,
  PRIMARY KEY (`idmachine`),
  UNIQUE KEY `machineName_UNIQUE` (`machineName`),
  KEY `idmanager_idx` (`idmanager`)
) ENGINE=MyISAM AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `machine`
--

INSERT INTO `machine` (`idmachine`, `machineName`, `machineModel`, `idmanager`) VALUES
(15, 'machine 1', '2022-m', 32),
(87, 'machine 2', '2022-m', 32),
(58, 'machine 3', '2422-m', 48),
(16, 'machine 4', '5664-kj', 12),
(23, 'machine 5', '4686-b', 36);

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
CREATE TABLE IF NOT EXISTS `manager` (
  `idfactoryperson_manager` int NOT NULL,
  PRIMARY KEY (`idfactoryperson_manager`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`idfactoryperson_manager`) VALUES
(14),
(25),
(39),
(41),
(54),
(128);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `salary` int DEFAULT NULL,
  `paymentmanager_id` int NOT NULL,
  `paymentworker_id` int NOT NULL,
  PRIMARY KEY (`paymentmanager_id`,`paymentworker_id`),
  KEY `paymentworker_id_idx` (`paymentworker_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`salary`, `paymentmanager_id`, `paymentworker_id`) VALUES
(380000, 14, 19),
(270000, 39, 33),
(267500, 41, 395),
(150000, 54, 43),
(267500, 128, 34);

-- --------------------------------------------------------

--
-- Table structure for table `rawmaterial`
--

DROP TABLE IF EXISTS `rawmaterial`;
CREATE TABLE IF NOT EXISTS `rawmaterial` (
  `idrawmaterial` int NOT NULL AUTO_INCREMENT,
  `rawmaterialfactory_id` int DEFAULT NULL,
  `rawmaterialmachine_id` int DEFAULT NULL,
  PRIMARY KEY (`idrawmaterial`),
  KEY `factory_id_idx` (`rawmaterialfactory_id`),
  KEY `machine_id_idx` (`rawmaterialmachine_id`)
) ENGINE=MyISAM AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `rawmaterial`
--

INSERT INTO `rawmaterial` (`idrawmaterial`, `rawmaterialfactory_id`, `rawmaterialmachine_id`) VALUES
(98, 2, 15),
(99, 2, 87),
(100, 3, 58),
(101, 6, 16),
(102, 5, 23);

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task` (
  `idtask` int NOT NULL AUTO_INCREMENT,
  `taskDescription` varchar(255) DEFAULT NULL,
  `idinputMaterial` int NOT NULL,
  `inputQuantity` int DEFAULT NULL,
  `idoutputMaterial` int NOT NULL,
  `outputProductionDate` datetime DEFAULT NULL,
  `taskmachine_id` int DEFAULT NULL,
  `taskworker_id` int DEFAULT NULL,
  `taskmanager_id` int DEFAULT NULL,
  PRIMARY KEY (`idtask`,`idinputMaterial`,`idoutputMaterial`),
  KEY `taskmachine_id_idx` (`taskmachine_id`),
  KEY `taskmanager_id_idx` (`taskmanager_id`),
  KEY `taskworker_id_idx` (`taskworker_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`idtask`, `taskDescription`, `idinputMaterial`, `inputQuantity`, `idoutputMaterial`, `outputProductionDate`, `taskmachine_id`, `taskworker_id`, `taskmanager_id`) VALUES
(1, 'task description 1', 1, 50, 5, '2022-05-31 15:30:06', 15, 19, 14),
(2, 'task description 2', 2, 52, 7, '2022-05-31 15:30:06', 87, 33, 39),
(3, 'task description 3', 3, 84, 16, '2022-05-31 15:30:06', 58, 395, 41),
(4, 'task description 4', 4, 55, 27, '2022-05-31 15:30:06', 16, 43, 54),
(5, 'task description 5', 5, 75, 9, '2022-05-31 15:30:06', 23, 34, 128);

-- --------------------------------------------------------

--
-- Table structure for table `taskassignment`
--

DROP TABLE IF EXISTS `taskassignment`;
CREATE TABLE IF NOT EXISTS `taskassignment` (
  `taskid` int DEFAULT NULL,
  `taskassignmentmanager_id` int NOT NULL,
  `taskassignmentmachine_id` int NOT NULL,
  PRIMARY KEY (`taskassignmentmanager_id`,`taskassignmentmachine_id`),
  KEY `taskassignmentmachine_id_idx` (`taskassignmentmachine_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `taskassignment`
--

INSERT INTO `taskassignment` (`taskid`, `taskassignmentmanager_id`, `taskassignmentmachine_id`) VALUES
(1, 14, 15),
(2, 39, 87),
(2, 41, 58),
(3, 54, 16),
(4, 128, 23);

-- --------------------------------------------------------

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
CREATE TABLE IF NOT EXISTS `worker` (
  `idfactoryperson_worker` int NOT NULL,
  `noTasksCompleted` int DEFAULT NULL,
  PRIMARY KEY (`idfactoryperson_worker`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `worker`
--

INSERT INTO `worker` (`idfactoryperson_worker`, `noTasksCompleted`) VALUES
(19, 3),
(33, 9),
(395, 5),
(43, 7),
(34, 6);

-- --------------------------------------------------------

--
-- Table structure for table `workson`
--

DROP TABLE IF EXISTS `workson`;
CREATE TABLE IF NOT EXISTS `workson` (
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `worksontask_id` int NOT NULL,
  `worksonworker_id` int NOT NULL,
  PRIMARY KEY (`worksontask_id`,`worksonworker_id`),
  KEY `worksonworker_id_idx` (`worksonworker_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3;

--
-- Dumping data for table `workson`
--

INSERT INTO `workson` (`startTime`, `endTime`, `worksontask_id`, `worksonworker_id`) VALUES
('2022-05-31 16:57:08', '2022-05-31 16:57:08', 1, 19),
('2022-05-31 16:57:08', '2022-05-31 16:57:08', 2, 33),
('2022-05-31 16:58:03', '2022-05-31 16:58:03', 3, 395),
('2022-05-31 16:58:03', '2022-05-31 16:58:03', 4, 43);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
