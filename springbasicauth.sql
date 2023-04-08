-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 08, 2023 at 02:04 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `springbasicauth`
--

-- --------------------------------------------------------

--
-- Table structure for table `repair`
--

CREATE TABLE `repair` (
  `rid` int(11) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` bigint(20) NOT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `last_modified_date` bigint(20) NOT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `repair`
--

INSERT INTO `repair` (`rid`, `created_by`, `created_date`, `last_modified_by`, `last_modified_date`, `detail`, `name`, `status`) VALUES
(1, NULL, 0, 'semih@mail.com', 1680951313342, 'Phone\'s screen will change.', 'Iphone 14 repair', 1),
(2, 'samet@mail.com', 1680951436431, 'samet@mail.com', 1680951436431, 'Phone\'s camera will change.', 'Samsung Galaxy S23 repair', 0),
(3, 'mustafa@mail.com', 1680951563604, 'mustafa@mail.com', 1680951563604, 'Laptop\'s microphone will change.', 'Macbook repair', 0),
(4, 'kadir@mail.com', 1680951637508, 'kadir@mail.com', 1680951637508, 'Tablet\'s screen will change.', 'Tablet repair', 0),
(5, 'emre@mail.com', 1680953490744, 'emre@mail.com', 1680953490744, 'Tablet\'s screen will change.', 'Tablet repair', 0);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_admin'),
(2, 'ROLE_customer');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `token_expired` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `enabled`, `first_name`, `last_name`, `password`, `token_expired`) VALUES
(1, 'semih@mail.com', b'1', 'Semih', 'Tekin', '$2a$10$8pgXtflzGBT8KYbRmJ4eouswx3shWggDuzdMuGBvFo.zUOQZkK26e', b'1'),
(2, 'muhammet@mail.com', b'1', 'Muhammet Furkan', 'Ekici', '$2a$10$m0C4jnPX9ESneorJtaXbmeZarX73H7xn9tPGskuXTkA1UNs4nd0HK', b'1'),
(3, 'samet@mail.com', b'1', 'Samet', 'Acar', '$2a$10$pL2HnSPFk/z20yTJZUABkewxaYag1MlsEkoNaIosgMZOj9BGp/MPG', b'1'),
(4, 'mustafa@mail.com', b'1', 'Mustafa', 'Katlan', '$2a$10$WlKN.QbAQOOSvK9qyRDvluCTlCCNtkTrPcJts8oOpXwdYp3F1uvY6', b'1'),
(5, 'kadir@mail.com', b'1', 'Kadir', 'Kesecek', '$2a$10$6eOeBCSNC8SlTsab6MijMezS7KvVv3mgKJa9jEbnZXW42tXOOINoi', b'1'),
(6, 'emre@mail.com', b'1', 'Emre', 'Tozan', '$2a$10$lEje1ZEve93FfXMjT.Qdhe3fsQyODTCrsVaPAHvrGp0nRjfGFd9o2', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `repair`
--
ALTER TABLE `repair`
  ADD PRIMARY KEY (`rid`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  ADD KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `repair`
--
ALTER TABLE `repair`
  MODIFY `rid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
