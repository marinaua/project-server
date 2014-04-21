-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 22, 2014 at 12:18 AM
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
  `credit_programm_id` int(11) NOT NULL,
  `remaining_sum` int(11) NOT NULL,
  `credit_status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `client_credit`
--

INSERT INTO `client_credit` (`id`, `client_id`, `credit_programm_id`, `remaining_sum`, `credit_status`) VALUES
(2, 1, 0, 300, 'ACTIVE'),
(3, 2, 0, 600, 'ACTIVE'),
(4, 2, 0, 400, 'ACTIVE'),
(5, 1, 0, 300, 'ACTIVE'),
(6, 2, 0, 600, 'ACTIVE'),
(7, 2, 0, 400, 'ACTIVE'),
(8, 1, 1, 300, 'ACTIVE'),
(9, 2, 4, 600, 'ACTIVE'),
(10, 2, 2, 400, 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `credit_programm`
--

CREATE TABLE IF NOT EXISTS `credit_programm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(120) NOT NULL,
  `short_description` varchar(255) NOT NULL,
  `full_description` varchar(600) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `credit_programm`
--

INSERT INTO `credit_programm` (`id`, `title`, `short_description`, `full_description`) VALUES
(1, 'house', 'short descr1', 'long descr1'),
(3, 'flat', 'short descr3', 'long descr3'),
(4, 'dresses', 'short descr4', 'long descr4');

-- --------------------------------------------------------

--
-- Table structure for table `credit_request`
--

CREATE TABLE IF NOT EXISTS `credit_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `credit_programm_id` int(11) NOT NULL,
  `request_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `request_status` varchar(50) NOT NULL,
  `credit_summ` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `credit_request`
--

INSERT INTO `credit_request` (`id`, `client_id`, `credit_programm_id`, `request_timestamp`, `request_status`, `credit_summ`) VALUES
(1, 1, 4, '2014-04-20 09:47:11', 'NEW', 800),
(3, 1, 4, '2014-04-20 09:42:57', 'NEW', 800);

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
(1, 'Ivan', 'Dorn', 500, '1', 1, 'vanja@mail.ru', '5678', 'client'),
(2, 'Евгений', 'Пожарский', 800, '0', 2, '', '', 'client'),
(4, 'Oleg', 'Lyashko', 600, 'y', 2, 'lyashko@best.com', '11111', 'client'),
(5, 'Павел', 'Шеремет', NULL, NULL, NULL, 'sheremet@gmail.com', '12345', 'administrator'),
(8, 'Oleg', 'Lyashko', 600, 'y', 2, 'lyashko@best.com', '11111', 'client'),
(9, 'Oleg', 'Lyashko', 600, 'y', 2, 'lyashko@best.com', '11111', 'client'),
(10, 'Oleg', 'Lyashko', 600, 'y', 2, 'lyashko@best.com', '11111', 'client'),
(12, 'Ivan', 'Dorn', NULL, NULL, NULL, 'vanja@mail.ru', '5678', 'manager'),
(13, 'Ivan', 'Dorn', NULL, NULL, NULL, 'vanja@mail.ru', '5678', 'manager'),
(14, 'Ivan', 'Dorn', NULL, NULL, NULL, 'vanja@mail.ru', '5678', 'manager');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
