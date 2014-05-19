-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 19, 2014 at 09:30 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `creditsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `client_credit`
--

CREATE TABLE IF NOT EXISTS `client_credit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `credit_program_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `client_id` (`client_id`),
  KEY `credit_programm_id` (`credit_program_id`),
  KEY `status_id` (`status_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=50 ;

--
-- Dumping data for table `client_credit`
--

INSERT INTO `client_credit` (`id`, `client_id`, `credit_program_id`, `status_id`) VALUES
(49, 1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `credit_program`
--

CREATE TABLE IF NOT EXISTS `credit_program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(120) NOT NULL,
  `short_description` varchar(255) NOT NULL,
  `full_description` varchar(600) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `credit_program`
--

INSERT INTO `credit_program` (`id`, `title`, `short_description`, `full_description`) VALUES
(1, 'house', 'short descr 1111', 'long descr1'),
(3, 'flat', 'short descr 22222', 'long descr3'),
(4, 'dresses', 'short descr 3', 'long descr4'),
(5, 'car', 'short descr 4', 'full descr 5');

-- --------------------------------------------------------

--
-- Table structure for table `credit_status`
--

CREATE TABLE IF NOT EXISTS `credit_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `credit_status`
--

INSERT INTO `credit_status` (`id`, `status`) VALUES
(1, 'active'),
(2, 'closed');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE IF NOT EXISTS `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status_id` int(1) NOT NULL,
  `client_id` int(11) NOT NULL,
  `manager_id` int(11) DEFAULT NULL,
  `credit_program_id` int(11) NOT NULL,
  `credit_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `client_id` (`client_id`),
  KEY `manager_id` (`manager_id`),
  KEY `status_id` (`status_id`),
  KEY `credit_program_id` (`credit_program_id`),
  KEY `credit_id` (`credit_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=52 ;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`id`, `status_id`, `client_id`, `manager_id`, `credit_program_id`, `credit_id`) VALUES
(50, 5, 1, 12, 1, 49),
(51, 2, 1, 12, 4, 0);

-- --------------------------------------------------------

--
-- Table structure for table `order_status`
--

CREATE TABLE IF NOT EXISTS `order_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `order_status`
--

INSERT INTO `order_status` (`id`, `status`) VALUES
(2, 'new'),
(3, 'accepted'),
(4, 'declined'),
(5, 'archive');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `surname` varchar(40) NOT NULL,
  `income` double DEFAULT NULL,
  `surety` varchar(5) DEFAULT NULL,
  `manager_id` int(11) DEFAULT NULL,
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `surname`, `income`, `surety`, `manager_id`, `login`, `password`, `role`) VALUES
(1, 'UserName1', 'UserSurname2', 500, '1', 12, 'client@gmail.com', '1234', 'client'),
(5, 'AdministratorName', 'AdministratorSurname', NULL, NULL, NULL, 'admin@gmail.com', '1234', 'administrator'),
(12, 'ManagerName', 'ManagerSurname', NULL, NULL, NULL, 'manager@gmail.com', '1234', 'manager'),
(13, 'ClientName2', 'ClientSurname2', 700, NULL, 14, 'client2@gmail.com', '1234', 'client'),
(14, 'ManagerName2', 'ManagerSurname2', NULL, NULL, NULL, 'manager2@gmail.com', '1234', 'manager');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `client_credit`
--
ALTER TABLE `client_credit`
  ADD CONSTRAINT `client_credit_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `client_credit_ibfk_3` FOREIGN KEY (`status_id`) REFERENCES `credit_status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `client_credit_ibfk_4` FOREIGN KEY (`credit_program_id`) REFERENCES `credit_program` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `order_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `order_ibfk_2` FOREIGN KEY (`manager_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `order_ibfk_3` FOREIGN KEY (`credit_program_id`) REFERENCES `credit_program` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
