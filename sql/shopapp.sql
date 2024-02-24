-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 15, 2024 at 11:29 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shopapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT 'Category name'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(1, 'Phone'),
(2, 'Laptop'),
(3, 'Tivi'),
(4, 'Samsung');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `fullname` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT '',
  `phone_number` varchar(20) NOT NULL,
  `address` varchar(200) NOT NULL,
  `note` varchar(100) DEFAULT '',
  `order_date` datetime NOT NULL DEFAULT current_timestamp(),
  `status` enum('pending','processing','shipped','delivered','cancelled') NOT NULL COMMENT 'Order status',
  `total_price` float DEFAULT NULL CHECK (`total_price` >= 0),
  `shipping_method` varchar(100) DEFAULT NULL,
  `shipping_address` varchar(200) DEFAULT NULL,
  `shipping_date` date DEFAULT NULL,
  `tracking_number` varchar(100) DEFAULT NULL,
  `payment_method` varchar(100) DEFAULT NULL,
  `active` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `user_id`, `fullname`, `email`, `phone_number`, `address`, `note`, `order_date`, `status`, `total_price`, `shipping_method`, `shipping_address`, `shipping_date`, `tracking_number`, `payment_method`, `active`) VALUES
(1, 1, 'Thanh', 'Thanh@gmail.com', '12341234', 'Binh chanh', 'Nothing', '2024-02-01 20:49:05', 'shipped', 12.2, 'Express', 'Binh chanh1', '2024-02-02', NULL, 'COD', 0),
(2, 1, 'Thanh', 'Thanh@gmail.com', '123123', 'Binh chanh', 'Nothing', '2024-01-31 05:56:26', 'processing', 12.2, 'Express1', 'Binh chanh', '2024-02-01', NULL, 'COD', 0),
(3, 1, 'Thanh', 'thanh@gmail.com', '123123', 'Binh chanh', '', '2024-01-31 07:39:47', 'pending', 12.2, 'Express', 'Binh chanh', '2024-02-01', NULL, 'COD', 0),
(4, 1, 'Thanh', 'thanh@gmail.com', '123123', 'Binh chanh', 'Nothing', '2024-02-01 19:29:01', 'pending', 12.2, 'Express1', 'Binh chanh', '2024-02-03', NULL, 'COD', 0),
(5, 1, 'Thanh', 'thanh@gmail.com', '123123', 'Binh chanh', 'Nothing', '2024-02-01 19:30:24', 'pending', 12.2, 'Express1', 'Binh chanh', '2024-02-03', NULL, 'COD', 0),
(6, 1, 'Thanh', 'thanh@gmail.com', '123123', 'Binh chanh', 'Nothing', '2024-02-01 19:30:37', 'pending', 12.2, 'Express1', 'Binh chanh', '2024-02-03', NULL, 'COD', 0),
(7, 1, 'Thanh', 'thanh@gmail.com', '123123', 'Binh chanh', 'Nothing', '2024-02-01 19:33:25', 'pending', 12.2, 'Express1', 'Binh chanh', '2024-02-03', NULL, 'COD', 1),
(8, 1, 'Thanh', 'thanh@gmail.com', '123123', 'Binh chanh', 'Nothing', '2024-02-01 19:36:11', 'pending', 12.2, 'Express1', 'Binh chanh', '2024-02-03', NULL, 'COD', 1),
(9, 1, 'Thanh', 'Thanh@gmail.com', '123123', 'Binh chanh', 'Nothing', '2024-02-01 19:41:48', 'pending', 12.2, 'Express1', 'Binh chanh', '2024-02-03', NULL, 'COD', 1),
(10, 1, 'Thanh', 'Thanh@gmail.com', '123123', 'Binh chanh', 'Nothing', '2024-02-01 19:50:35', 'pending', 12.2, 'Express1', 'Binh chanh', '2024-02-03', NULL, 'COD', 1),
(11, 1, 'Thanh', 'Thanh@gmail.com', '123123', 'Binh chanh', 'Nothing', '2024-02-01 19:52:18', 'pending', 12.2, 'Express1', 'Binh chanh', '2024-02-03', NULL, 'COD', 0),
(12, 1, 'Thanh', 'Thanh@gmail.com', '123123', 'Binh chanh', 'Nothing', '2024-02-01 19:52:45', 'pending', 12.2, 'Express1', 'Binh chanh', '2024-02-03', NULL, 'COD', 0),
(13, 1, 'Thanh', 'Thanh@gmail.com', '123123', 'Binh chanh', 'Nothing', '2024-02-01 19:56:05', 'pending', 12.2, 'Express1', 'Binh chanh', '2024-02-03', NULL, 'COD', 0),
(14, 1, 'Thanh', 'thanh@gmail.com', '123123', 'Binh chanh', 'Nothing', '2024-02-01 19:57:55', 'pending', 12.2, 'Express1', 'Binh chanh', '2024-02-03', NULL, 'COD', 0),
(15, 1, 'Thanh', 'thanh@gmail.com', '123123', 'Binh chanh', 'Nothing', '2024-02-01 19:59:10', 'pending', 12.2, 'Express1', 'Binh chanh', '2024-02-03', NULL, 'COD', 0);

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL CHECK (`price` >= 0),
  `number_of_products` int(11) DEFAULT NULL CHECK (`number_of_products` > 0),
  `total_price` float DEFAULT NULL CHECK (`total_price` >= 0),
  `color` varchar(20) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`id`, `order_id`, `product_id`, `price`, `number_of_products`, `total_price`, `color`) VALUES
(1, 1, 1, 2000, 1, 5500, ''),
(2, 2, 1, 2420, 2, 4840, NULL),
(3, 2, 2, 2618, 5, 13090, NULL),
(4, 3, 1, 2420, 5, 12100, NULL),
(5, 3, 2, 2618, 3, 7854, NULL),
(6, 3, 3, 3646, 4, 14584, NULL),
(7, 4, 1, 2420, 3, 7260, NULL),
(8, 5, 1, 2420, 3, 7260, NULL),
(9, 6, 1, 2420, 3, 7260, NULL),
(10, 7, 1, 2420, 3, 7260, NULL),
(11, 8, 1, 2420, 3, 7260, NULL),
(12, 9, 3, 3646, 2, 7292, NULL),
(13, 9, 4, 5987, 4, 23948, NULL),
(14, 10, 3, 3646, 2, 7292, NULL),
(15, 10, 4, 5987, 4, 23948, NULL),
(16, 11, 3, 3646, 2, 7292, NULL),
(17, 11, 4, 5987, 4, 23948, NULL),
(18, 12, 3, 3646, 2, 7292, NULL),
(19, 12, 4, 5987, 4, 23948, NULL),
(20, 13, 3, 3646, 2, 7292, NULL),
(21, 13, 4, 5987, 4, 23948, NULL),
(22, 14, 1, 2420, 3, 7260, NULL),
(23, 15, 1, 2420, 6, 14520, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(350) NOT NULL,
  `price` float NOT NULL CHECK (`price` >= 0),
  `thumbnail` varchar(300) DEFAULT '''''',
  `description` longtext DEFAULT '',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `thumbnail`, `description`, `created_at`, `updated_at`, `category_id`) VALUES
(1, 'Synergistic Linen Wallet', 2420, '1481d89c-24e3-44d7-96e0-e537194a315a-images.png', 'Sunt minima nostrum doloremque hic suscipit.', '2024-01-22 00:22:21', '2024-01-29 04:35:09', 2),
(2, 'Ergonomic Leather Plate', 2618, 'cce940b9-1424-4a39-82aa-52257adcc382-images - Copy.png', 'Voluptatem est et necessitatibus aspernatur.', '2024-01-22 00:22:21', '2024-01-29 05:02:27', 1),
(3, 'Fantastic Rubber Hat', 3646, 'f1335efb-c3f7-4505-a606-2171b5b3d975-images - Copy.png', 'Delectus debitis et.', '2024-01-22 00:22:21', '2024-01-29 05:02:27', 2),
(4, 'Mediocre Paper Knife', 5987, '115f297f-72e2-4b66-b350-c2a21e40d2b2-images - Copy.png', 'Nulla et possimus dignissimos consequatur.', '2024-01-22 00:22:21', '2024-01-29 05:02:27', 2),
(5, 'Small Aluminum Bench', 1599, 'de97a8ba-34d6-4312-9598-c739692852d1-images - Copy.png', 'Quis deserunt sunt corporis aliquid cum natus expedita.', '2024-01-22 00:22:21', '2024-01-29 05:02:27', 2),
(6, 'Incredible Steel Lamp', 4022, '2a97e91c-a43a-4a06-8d0e-a04d05641d78-images - Copy.png', 'Pariatur molestiae sint corrupti officia.', '2024-01-22 00:22:21', '2024-01-29 05:02:27', 2),
(7, 'Enormous Iron Car', 5656, '5fdcdcac-4f3f-4315-82ff-4a74b1dc2dcc-images - Copy.png', 'Veritatis inventore aut consequatur.', '2024-01-22 00:22:21', '2024-01-29 05:02:27', 2),
(8, 'Synergistic Paper Lamp', 3252, 'f705a6cc-e3ff-4bb1-80f5-2438ff7d92db-images - Copy.png', 'Est totam ut perferendis deserunt.', '2024-01-22 00:22:21', '2024-01-29 05:02:27', 2),
(9, 'Enormous Marble Car', 2331, '8cd1c1a6-f8b6-4374-88a0-815be039dac6-images - Copy.png', 'Expedita quas non iusto.', '2024-01-22 00:22:21', '2024-01-29 05:02:27', 1),
(10, 'Incredible Wool Pants', 4444, 'c0467977-d506-4385-968e-d750ceb95fff-images - Copy.png', 'Quibusdam aut ad.', '2024-01-22 00:22:21', '2024-01-29 05:02:27', 2),
(11, 'Practical Steel Bottle', 6202, NULL, 'Consequatur nihil ipsam et ad.', '2024-01-22 00:22:21', '2024-01-29 05:02:27', 1),
(12, 'Heavy Duty Copper Computer', 9080, NULL, 'Ea sit rerum sed atque doloremque.', '2024-01-22 00:22:21', '2024-01-29 05:02:27', 1),
(13, 'Durable Copper Computer', 4699, NULL, 'Ut quibusdam sunt aut.', '2024-01-22 00:22:21', '2024-01-29 05:02:27', 2),
(14, 'Synergistic Wooden Table', 3102, NULL, 'Possimus sequi nobis ipsum rerum quia rerum.', '2024-01-22 00:22:21', '2024-01-29 05:02:27', 3),
(15, 'Rustic Plastic Shoes', 9846, NULL, 'Vel aspernatur debitis aliquid ea ullam ab perspiciatis.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(16, 'Lightweight Bronze Knife', 7167, NULL, 'Vero voluptatem vero odio non.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(17, 'Mediocre Silk Shoes', 7036, NULL, 'Omnis vel consequuntur eaque fuga.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(18, 'Intelligent Plastic Shirt', 2227, NULL, 'Omnis architecto omnis nihil provident hic dolorem.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(19, 'Sleek Steel Bag', 1168, NULL, 'Soluta et perspiciatis.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(20, 'Practical Copper Watch', 3392, NULL, 'Sed unde ut cupiditate at recusandae.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(21, 'Practical Copper Car', 9527, NULL, 'Et quasi aperiam.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(22, 'Fantastic Iron Chair', 5797, NULL, 'Sit quod voluptatum deleniti et sit.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(23, 'Lightweight Paper Bench', 4716, NULL, 'Officiis tempore quia aut nesciunt in.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(24, 'Gorgeous Wool Bottle', 3316, NULL, 'Et eos asperiores eos qui libero.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(25, 'Durable Plastic Knife', 7350, NULL, 'Aut aut soluta qui nemo saepe eaque.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(26, 'Rustic Rubber Shoes', 2773, NULL, 'Iusto sapiente tempore eos magni omnis.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(27, 'Small Leather Knife', 2804, NULL, 'Vero qui earum nihil.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(28, 'Aerodynamic Granite Lamp', 7707, NULL, 'Qui explicabo qui aut.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(29, 'Incredible Rubber Bag', 6875, NULL, 'Et neque sint consectetur ratione vel.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(30, 'Rustic Iron Computer', 6585, NULL, 'Quis nesciunt eos recusandae commodi culpa.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(31, 'Awesome Bronze Car', 3878, NULL, 'Dolorum dolorem ducimus.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(32, 'Lightweight Wool Pants', 498, NULL, 'Iste esse alias praesentium laboriosam sunt repudiandae.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(33, 'Sleek Concrete Coat', 6723, NULL, 'Sequi omnis a et.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(34, 'Enormous Steel Car', 6865, NULL, 'Accusantium eius quia dolor.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(35, 'Sleek Copper Computer', 2894, NULL, 'Harum pariatur omnis autem.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(36, 'Durable Plastic Wallet', 8812, NULL, 'Laborum aspernatur occaecati a sint ab voluptate.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(37, 'Enormous Leather Pants', 60, NULL, 'Ea amet aut repellat dicta atque corrupti et.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(38, 'Mediocre Iron Pants', 3800, NULL, 'Eligendi cum iste.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(39, 'Incredible Concrete Clock', 3135, NULL, 'Enim ut nesciunt.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(40, 'Rustic Paper Keyboard', 6749, NULL, 'Et rerum atque alias.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(41, 'Awesome Paper Keyboard', 5504, NULL, 'Officiis sunt dolorem maiores consequatur quia.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(42, 'Practical Steel Car', 4917, NULL, 'Possimus sapiente voluptatem ipsum qui.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(43, 'Practical Concrete Shoes', 346, NULL, 'Sint eos deserunt debitis.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(44, 'Small Copper Computer', 6599, NULL, 'Ut corporis recusandae et.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(45, 'Mediocre Paper Clock', 5197, NULL, 'Quis possimus reiciendis.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(46, 'Aerodynamic Steel Knife', 3519, NULL, 'Veniam perferendis atque at deserunt.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(47, 'Ergonomic Steel Plate', 5138, NULL, 'Vel mollitia exercitationem est.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(48, 'Synergistic Granite Gloves', 7183, NULL, 'Repellendus in id molestiae voluptatem rerum ut.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(49, 'Awesome Paper Watch', 2300, NULL, 'Quia soluta iusto sapiente cumque.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(50, 'Fantastic Steel Watch', 8506, NULL, 'Quia nisi corrupti aut vel repudiandae facilis est.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(51, 'Aerodynamic Cotton Table', 7867, NULL, 'Sed velit quaerat rerum saepe voluptas.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(52, 'Rustic Concrete Pants', 2790, NULL, 'Exercitationem vel facilis iste eum.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(53, 'Intelligent Rubber Table', 4991, NULL, 'Quidem nam cupiditate aut illo id et consequatur.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(54, 'Rustic Silk Gloves', 4782, NULL, 'Quos dolores non ut.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(55, 'Gorgeous Paper Plate', 8993, NULL, 'In voluptatem maxime fugit molestias commodi quo explicabo.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(56, 'Intelligent Leather Table', 9040, NULL, 'Odio inventore nulla.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(57, 'Sleek Paper Shoes', 9567, NULL, 'Exercitationem molestias qui fuga culpa qui nihil.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(58, 'Sleek Iron Shirt', 3847, NULL, 'Corporis nisi nam reiciendis quo.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(59, 'Fantastic Linen Bag', 881, NULL, 'Aperiam id suscipit suscipit quia.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(60, 'Gorgeous Rubber Car', 3904, NULL, 'Quas numquam corrupti non.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(61, 'Intelligent Paper Watch', 7693, NULL, 'Illo ab porro explicabo deserunt amet voluptatem.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(62, 'Gorgeous Steel Coat', 2900, NULL, 'Minus ipsum tempora.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(63, 'Durable Rubber Watch', 7206, NULL, 'Unde excepturi culpa quidem sed quam.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(64, 'Sleek Paper Table', 7663, NULL, 'Laudantium quam amet est corrupti hic voluptas magnam.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(65, 'Awesome Paper Wallet', 8169, NULL, 'Magnam occaecati et assumenda molestiae mollitia qui.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(66, 'Practical Wooden Computer', 3307, NULL, 'Quam sit ut.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(67, 'Intelligent Wooden Car', 7496, NULL, 'Et reiciendis sint.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(68, 'Heavy Duty Iron Computer', 5765, NULL, 'Aut sed quas pariatur optio autem non ut.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(69, 'Aerodynamic Wool Chair', 1482, NULL, 'Autem ut rerum non officia et autem aut.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(70, 'Enormous Granite Gloves', 9025, NULL, 'Sed itaque dolore vero et.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(71, 'Practical Wool Watch', 326, NULL, 'Sit adipisci labore blanditiis.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(72, 'Incredible Linen Table', 4190, NULL, 'Non assumenda assumenda similique rerum quia.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(73, 'Heavy Duty Bronze Hat', 5625, NULL, 'Eos explicabo vero repellat et maxime ducimus.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(74, 'Incredible Plastic Watch', 3931, NULL, 'Nostrum est eaque eos labore similique eos.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(75, 'Aerodynamic Linen Table', 5348, NULL, 'Est et quae.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(76, 'Mediocre Concrete Table', 8791, NULL, 'Est deleniti inventore earum.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(77, 'Sleek Concrete Keyboard', 601, NULL, 'Doloremque rerum beatae fugiat et quibusdam.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(78, 'Incredible Bronze Bench', 3496, NULL, 'Doloremque ut mollitia sed voluptatem doloribus eaque.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(79, 'Incredible Wool Car', 8463, NULL, 'Accusantium velit quos.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(80, 'Durable Granite Keyboard', 5388, NULL, 'Et incidunt magnam reiciendis maiores.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(81, 'Mediocre Wool Keyboard', 331, NULL, 'Dolores enim quis aut provident.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(82, 'Enormous Bronze Knife', 7905, NULL, 'Reprehenderit atque optio.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(83, 'Fantastic Plastic Hat', 2424, NULL, 'Rerum sed aut quisquam.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(84, 'Awesome Linen Coat', 809, NULL, 'Quibusdam cupiditate saepe.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(85, 'Ergonomic Paper Lamp', 974, NULL, 'Error culpa quod possimus aut.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(86, 'Gorgeous Iron Bench', 7032, NULL, 'Id doloribus optio saepe tempore.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(87, 'Durable Cotton Bag', 7745, NULL, 'Dolorem sit beatae.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(88, 'Practical Concrete Chair', 8452, NULL, 'Odit quibusdam aliquid in.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(89, 'Practical Iron Watch', 8522, NULL, 'Dolores veritatis et aspernatur beatae et eos quidem.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(90, 'Enormous Marble Plate', 216, NULL, 'Quod officiis eligendi omnis velit.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(91, 'Enormous Cotton Pants', 9620, NULL, 'Quidem deleniti recusandae.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(92, 'Mediocre Marble Table', 3665, NULL, 'Quod consectetur veritatis inventore.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(93, 'Fantastic Linen Computer', 6943, NULL, 'Totam dignissimos aperiam.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2),
(94, 'Aerodynamic Concrete Bag', 2288, NULL, 'Quod aut earum.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(95, 'Small Wool Gloves', 5718, NULL, 'Modi quo asperiores et quisquam.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(96, 'Rustic Concrete Lamp', 1658, NULL, 'Perspiciatis nostrum quis numquam.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(97, 'Ergonomic Leather Wallet', 6507, NULL, 'Aut qui sunt perferendis qui.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 1),
(98, 'Incredible Granite Computer', 6526, NULL, 'Nihil vero est qui vel laboriosam quae nihil.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 3),
(99, 'Enormous Marble Knife', 406, NULL, 'Expedita et excepturi aliquam quos.', '2024-01-22 00:22:22', '2024-01-29 05:02:27', 2);

-- --------------------------------------------------------

--
-- Table structure for table `product_images`
--

CREATE TABLE `product_images` (
  `id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `image_url` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product_images`
--

INSERT INTO `product_images` (`id`, `product_id`, `image_url`) VALUES
(1, 1, '1481d89c-24e3-44d7-96e0-e537194a315a-images.png'),
(2, 1, '66dc0acf-f81a-42d1-8cda-c7c25cf332c1-ins.jpg'),
(3, 1, 'af81365f-1e1c-4ce1-adf2-e26bd2dc6623-twitter-logo.png'),
(4, 1, 'cd27abfa-461d-4439-b9dc-b57db6f64d5f-twitter-logo.png'),
(5, 2, 'cce940b9-1424-4a39-82aa-52257adcc382-images - Copy.png'),
(6, 2, '73e62c19-d09e-42a6-a783-81398c44d7b8-ins - Copy.jpg'),
(7, 2, '8eac92a2-d06d-4600-af7d-ce857a7ee466-twitter-logo - Copy.png'),
(8, 3, 'f1335efb-c3f7-4505-a606-2171b5b3d975-images - Copy.png'),
(9, 3, 'b5a2222b-02de-492c-ad14-ddc9eabeaeac-ins - Copy.jpg'),
(10, 3, '2c77c58e-cf93-4b77-94bc-a36152449e43-twitter-logo - Copy.png'),
(11, 4, '115f297f-72e2-4b66-b350-c2a21e40d2b2-images - Copy.png'),
(12, 4, 'ee4ebb78-26d7-411e-b025-575d4a0d9912-ins - Copy.jpg'),
(13, 4, '7f6eeac2-4608-4178-8c96-e5822a38df33-twitter-logo - Copy.png'),
(14, 5, 'de97a8ba-34d6-4312-9598-c739692852d1-images - Copy.png'),
(15, 5, 'a8ef04e2-180d-4c4b-8da7-186e34d15757-ins - Copy.jpg'),
(16, 5, '2c8d8040-b695-4590-a72a-735cbd084b3d-twitter-logo - Copy.png'),
(17, 6, '2a97e91c-a43a-4a06-8d0e-a04d05641d78-images - Copy.png'),
(18, 6, 'd204649f-cea3-454f-8ec2-50d25ff904ec-ins - Copy.jpg'),
(19, 6, '2d476680-029d-47de-a077-f6f043bc971a-twitter-logo - Copy.png'),
(20, 7, '5fdcdcac-4f3f-4315-82ff-4a74b1dc2dcc-images - Copy.png'),
(21, 7, 'abf290ce-bd60-4fef-b10d-2265ce66678c-ins - Copy.jpg'),
(22, 7, 'f762546a-8b9c-408b-aec8-08cb5032fb6b-twitter-logo - Copy.png'),
(23, 8, 'f705a6cc-e3ff-4bb1-80f5-2438ff7d92db-images - Copy.png'),
(24, 8, 'e3122198-0bee-47aa-bab7-f35ed8b1aa4d-ins - Copy.jpg'),
(25, 8, 'bdf3981c-be24-4eb2-8265-dffdd18272a5-twitter-logo - Copy.png'),
(26, 9, '8cd1c1a6-f8b6-4374-88a0-815be039dac6-images - Copy.png'),
(27, 9, '83b47e68-3dc8-4530-80c5-837f3b7bc4b9-ins - Copy.jpg'),
(28, 9, '7affcaae-b17a-4fd9-943a-1b2ef0247a92-twitter-logo - Copy.png'),
(29, 10, 'c0467977-d506-4385-968e-d750ceb95fff-images - Copy.png'),
(30, 10, 'd7d6f6fd-10d8-4511-a9db-ab691d6de47b-ins - Copy.jpg'),
(31, 10, '698cbeaa-afa1-4df6-89cb-6c125bd2b220-twitter-logo - Copy.png');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'admin'),
(2, 'user');

-- --------------------------------------------------------

--
-- Table structure for table `social_accounts`
--

CREATE TABLE `social_accounts` (
  `id` int(11) NOT NULL,
  `provider` varchar(20) NOT NULL COMMENT 'Social media platform',
  `provider_id` varchar(50) NOT NULL,
  `email` varchar(150) NOT NULL COMMENT 'User email',
  `name` varchar(100) NOT NULL COMMENT 'User name',
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tokens`
--

CREATE TABLE `tokens` (
  `id` int(11) NOT NULL,
  `token` varchar(255) NOT NULL,
  `token_type` varchar(50) NOT NULL,
  `expiration_date` datetime DEFAULT NULL,
  `revoked` tinyint(1) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `expired` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `fullname` varchar(100) DEFAULT '',
  `phone_number` varchar(10) NOT NULL,
  `address` varchar(200) DEFAULT '',
  `password` varchar(100) NOT NULL DEFAULT '',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT 1,
  `date_of_birth` date DEFAULT NULL,
  `facebook_account_id` int(11) DEFAULT 0,
  `google_account_id` int(11) DEFAULT 0,
  `role_id` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `fullname`, `phone_number`, `address`, `password`, `created_at`, `updated_at`, `is_active`, `date_of_birth`, `facebook_account_id`, `google_account_id`, `role_id`) VALUES
(1, 'Thanh', '12312312', 'Binh chanh', 'passworddddd', '2024-01-22 16:48:02', '2024-01-22 16:48:02', 1, '2024-01-01', 0, 0, 1),
(2, 'Thanh1', '12345678', 'Binh chanh', '$2a$10$IHeddBkF7v9IVTRFx6wCuenINHFO5KHUQY3fTcqAAKaUyNekeXYY.', '2024-01-24 08:47:09', '2024-01-24 08:47:09', 1, '2001-01-01', 0, 0, 2),
(3, 'Thanh1', '22345678', 'Binh chanh', '$2a$10$DiNi2.pSjWP58Q5oOHm5nO/lB9x2uS8IkMgWvRE/fGQ2bBdurfTe6', '2024-01-24 08:50:51', '2024-01-24 08:50:51', NULL, '2001-01-01', 0, 0, 2),
(4, 'Thanh12', '32345678', 'Binh chanh', '$2a$10$KPuh1hYgMFilfW8i3rHkDeHs/9nMnGdK26V3CJjzPw96OqMy7Ji3W', '2024-01-24 09:00:40', '2024-01-24 09:00:40', 1, '2001-01-01', 0, 0, 2),
(5, 'Thanh1233', '42345678', 'Binh chanh', '$2a$10$EJLxCbDYfjKqcdTLZ/QV4udjAcpJdX1w5GyQEZAxrLfw.Y5Q9gzSK', '2024-01-24 09:02:38', '2024-02-03 05:09:10', 1, '2001-01-01', 0, 0, 1),
(6, 'Thanh12', '13345678', 'Binh chanh', '$2a$10$ENb.ODRGOO1S894DG4EutOdaAhVOHazrd.4xIlOzJ49T6jZ2o3dsW', '2024-01-24 09:07:48', '2024-01-24 09:07:48', 1, '2001-01-01', 0, 0, 2),
(7, 'Thanh12', '13345679', 'Binh chanh', '$2a$10$ad9hvRpu7ScdvUm7AH.nFO8uD9FMJlRFSn9sv/iHEPhXq9YriLQAu', '2024-01-25 09:25:49', '2024-01-25 09:25:49', 1, '2001-01-01', 0, 0, 2),
(8, 'thanh13oct', '123123312', 'TPHCM', '$2a$10$xOYeZ672luS5Cf96eKkX/..xG4cRlTnCT1a8rh/I/0x56Ai4jEAjC', '2024-01-26 09:28:53', '2024-01-26 09:28:53', 1, '2001-12-12', 0, 0, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indexes for table `product_images`
--
ALTER TABLE `product_images`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_product_images_product_id` (`product_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `social_accounts`
--
ALTER TABLE `social_accounts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `tokens`
--
ALTER TABLE `tokens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `token` (`token`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- AUTO_INCREMENT for table `product_images`
--
ALTER TABLE `product_images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `social_accounts`
--
ALTER TABLE `social_accounts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tokens`
--
ALTER TABLE `tokens`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `order_details_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`);

--
-- Constraints for table `product_images`
--
ALTER TABLE `product_images`
  ADD CONSTRAINT `fk_product_images_product_id` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `product_images_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`);

--
-- Constraints for table `social_accounts`
--
ALTER TABLE `social_accounts`
  ADD CONSTRAINT `social_accounts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `tokens`
--
ALTER TABLE `tokens`
  ADD CONSTRAINT `tokens_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
