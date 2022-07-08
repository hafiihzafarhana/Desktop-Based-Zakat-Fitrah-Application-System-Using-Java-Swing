-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 29, 2022 at 04:11 AM
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
-- Database: `dbzakat`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `nama_admin` varchar(30) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` int(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `nama_admin`, `username`, `password`, `role`) VALUES
(1, 'Hafi Ihza Farhana', 'hafiza', 'hafiza', 2),
(2, 'Wilhelm  Hegel', 'hegelis', 'hegelis', 2),
(4, 'gramsci', 'grams', 'grams', 1),
(5, 'Fidel', 'giwara', 'giwara', 1);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `ID` int(11) NOT NULL,
  `Item` varchar(20) NOT NULL,
  `Keterangan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`ID`, `Item`, `Keterangan`) VALUES
(1, 'Rupiah', '40000'),
(2, 'Beras', '3'),
(3, 'Terima beras', '5');

-- --------------------------------------------------------

--
-- Table structure for table `logging`
--

CREATE TABLE `logging` (
  `No` int(11) NOT NULL,
  `id_admin` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `nama_admin` varchar(30) NOT NULL,
  `waktu_log` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `logging`
--

INSERT INTO `logging` (`No`, `id_admin`, `username`, `nama_admin`, `waktu_log`) VALUES
(1, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/16 14:55:38'),
(2, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/16 15:00:25'),
(3, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/16 15:13:30'),
(4, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/16 15:15:16'),
(5, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/16 16:03:53'),
(6, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/16 16:58:14'),
(7, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/17 08:56:36'),
(8, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/19 14:10:29'),
(9, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/19 17:46:07'),
(10, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/19 17:59:33'),
(11, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/19 21:37:11'),
(12, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/19 21:52:24'),
(13, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/19 21:53:47'),
(14, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/19 21:54:41'),
(15, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/19 22:07:12'),
(16, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/19 22:07:32'),
(17, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/19 22:08:54'),
(18, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 11:28:38'),
(19, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 11:47:24'),
(20, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 11:47:54'),
(21, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 11:56:23'),
(22, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 11:57:09'),
(23, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 12:02:15'),
(24, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 12:31:47'),
(25, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 13:31:33'),
(26, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 13:32:36'),
(27, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 13:37:49'),
(28, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 22:22:42'),
(29, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 22:28:56'),
(30, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 22:30:31'),
(31, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 22:38:07'),
(32, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/20 22:38:53'),
(33, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 06:33:18'),
(34, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 06:33:54'),
(35, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 07:02:52'),
(36, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 07:10:33'),
(37, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 07:11:08'),
(38, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 08:03:26'),
(39, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 21:45:11'),
(40, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 21:45:48'),
(41, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 21:46:50'),
(42, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 21:47:41'),
(43, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 21:48:47'),
(44, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 21:51:01'),
(45, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 21:52:28'),
(46, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 21:55:32'),
(47, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 21:57:08'),
(48, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 21:58:29'),
(49, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 22:00:47'),
(50, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 22:01:41'),
(51, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 22:12:47'),
(52, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 22:15:16'),
(53, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 22:18:10'),
(54, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 22:40:42'),
(55, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 22:44:33'),
(56, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 22:53:24'),
(57, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 22:55:04'),
(58, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/21 23:31:14'),
(59, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 18:46:37'),
(60, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 18:50:00'),
(61, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 19:34:06'),
(62, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 19:35:36'),
(63, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 19:38:54'),
(64, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 19:41:08'),
(65, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 19:41:53'),
(66, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 19:44:34'),
(67, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 19:47:15'),
(68, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 19:50:34'),
(69, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 19:51:37'),
(70, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 19:52:36'),
(71, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 19:55:20'),
(72, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/26 19:57:00'),
(73, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 06:02:21'),
(74, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 06:08:43'),
(75, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 06:17:52'),
(76, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 06:18:52'),
(77, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 06:20:32'),
(78, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 23:17:19'),
(79, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 23:19:52'),
(80, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 23:20:53'),
(81, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 23:23:11'),
(82, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 23:33:51'),
(83, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 23:34:32'),
(84, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 23:38:00'),
(85, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 23:41:26'),
(86, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/28 23:54:11'),
(87, 4, 'grams', 'gramsci', '2022/06/29 00:13:08'),
(88, 4, 'grams', 'gramsci', '2022/06/29 00:19:04'),
(89, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:20:58'),
(90, 4, 'grams', 'gramsci', '2022/06/29 00:21:04'),
(91, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:21:47'),
(92, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:23:08'),
(93, 4, 'grams', 'gramsci', '2022/06/29 00:23:49'),
(94, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:25:23'),
(95, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:25:56'),
(96, 4, 'grams', 'gramsci', '2022/06/29 00:26:08'),
(97, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:26:23'),
(98, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:27:16'),
(99, 4, 'grams', 'gramsci', '2022/06/29 00:27:50'),
(100, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:28:24'),
(101, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:35:24'),
(102, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:42:33'),
(103, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:44:14'),
(104, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:46:08'),
(105, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:46:37'),
(106, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:48:57'),
(107, 4, 'grams', 'gramsci', '2022/06/29 00:49:06'),
(108, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:49:56'),
(109, 4, 'grams', 'gramsci', '2022/06/29 00:50:47'),
(110, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:50:57'),
(111, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:51:56'),
(112, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 00:52:17'),
(113, 4, 'grams', 'gramsci', '2022/06/29 00:52:44'),
(114, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 01:07:43'),
(115, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 01:11:15'),
(116, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 01:16:33'),
(117, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 01:22:58'),
(118, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 01:26:50'),
(119, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 01:33:29'),
(120, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 08:34:45'),
(121, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 08:40:37'),
(122, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 08:44:20'),
(123, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 08:51:39'),
(124, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 08:53:09'),
(125, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 08:56:01'),
(126, 1, 'hafiza', 'Hafi Ihza Farhana', '2022/06/29 08:58:04');

-- --------------------------------------------------------

--
-- Table structure for table `log_item`
--

CREATE TABLE `log_item` (
  `id_log_item` int(11) NOT NULL,
  `id_admin` int(11) NOT NULL,
  `nama_items` varchar(20) NOT NULL,
  `jumlah` varchar(20) NOT NULL,
  `waktu` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `log_item`
--

INSERT INTO `log_item` (`id_log_item`, `id_admin`, `nama_items`, `jumlah`, `waktu`) VALUES
(1, 1, 'Beras', '2022/06/21 22:53:29', '3'),
(2, 1, 'Rupiah', '2022/06/26 18:53:08', '50000'),
(3, 1, 'Rupiah', '2022/06/26 18:53:18', '40000'),
(4, 1, 'Beras', '2022/06/28 23:17:29', '4'),
(5, 1, 'Beras', '2022/06/28 23:17:36', '3'),
(6, 1, 'Beras', '2022/06/28 23:56:43', '4'),
(7, 1, 'Beras', '2022/06/28 23:56:59', '3');

-- --------------------------------------------------------

--
-- Table structure for table `log_mustahik`
--

CREATE TABLE `log_mustahik` (
  `id_log_mustahik` int(11) NOT NULL,
  `id_admin` int(11) NOT NULL,
  `id_mustahik` int(11) NOT NULL,
  `aksi` varchar(20) NOT NULL,
  `waktu` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `log_mustahik`
--

INSERT INTO `log_mustahik` (`id_log_mustahik`, `id_admin`, `id_mustahik`, `aksi`, `waktu`) VALUES
(1, 1, 1, 'Tambah', '2022/06/16 14:56:31'),
(2, 1, 1, 'Update', '2022/06/21 22:50:51'),
(3, 1, 2, 'Tambah', '2022/06/26 18:58:26'),
(4, 1, 1, 'Update', '2022/06/26 18:58:52'),
(5, 1, 2, 'Hapus', '2022/06/26 18:59:31'),
(6, 1, 2, 'Tambah', '2022/06/28 23:17:54'),
(7, 1, 3, 'Tambah', '2022/06/28 23:18:37'),
(8, 1, 1, 'Update', '2022/06/28 23:20:22'),
(9, 1, 4, 'Tambah', '2022/06/28 23:21:07'),
(10, 1, 5, 'Tambah', '2022/06/28 23:57:51'),
(11, 1, 5, 'Update', '2022/06/28 23:58:50');

-- --------------------------------------------------------

--
-- Table structure for table `log_muzaki`
--

CREATE TABLE `log_muzaki` (
  `id_log_muzaki` int(11) NOT NULL,
  `id_admin` int(11) NOT NULL,
  `id_muzaki` int(11) NOT NULL,
  `aksi` varchar(20) NOT NULL,
  `waktu` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `log_muzaki`
--

INSERT INTO `log_muzaki` (`id_log_muzaki`, `id_admin`, `id_muzaki`, `aksi`, `waktu`) VALUES
(1, 1, 1, 'Tambah', '2022/06/16 15:00:41'),
(2, 1, 1, 'Update', '2022/06/16 15:15:21'),
(3, 1, 11, 'Tambah', '2022/06/21 22:45:58'),
(4, 1, 12, 'Tambah', '2022/06/21 22:47:21'),
(5, 1, 11, 'Hapus', '2022/06/21 22:47:37'),
(6, 1, 1, 'Update', '2022/06/21 22:50:26'),
(7, 1, 13, 'Tambah', '2022/06/26 19:03:42'),
(8, 1, 14, 'Tambah', '2022/06/26 19:06:43'),
(9, 1, 13, 'Update', '2022/06/26 19:12:28'),
(10, 1, 15, 'Tambah', '2022/06/28 06:37:19'),
(11, 1, 16, 'Tambah', '2022/06/29 00:07:10'),
(12, 1, 19, 'Tambah', '2022/06/29 08:35:17'),
(13, 1, 20, 'Tambah', '2022/06/29 08:36:52');

-- --------------------------------------------------------

--
-- Table structure for table `mustahik`
--

CREATE TABLE `mustahik` (
  `id` int(11) NOT NULL,
  `nama_mustahik` varchar(100) NOT NULL,
  `alamat` text NOT NULL,
  `status` varchar(30) NOT NULL,
  `tgl_tambah` text NOT NULL,
  `tgl_update` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mustahik`
--

INSERT INTO `mustahik` (`id`, `nama_mustahik`, `alamat`, `status`, `tgl_tambah`, `tgl_update`) VALUES
(1, 'Roji', 'Surabayah', 'Menerima', '2022/06/16 14:56:31', '2022/06/28 23:20:22'),
(2, 'Ani', 'Solo', 'Tidak Menerima', '2022/06/28 23:17:54', '2022/06/28 23:17:54'),
(3, 'Andis', 'Surabaya', 'Tidak Menerima', '2022/06/28 23:18:37', '2022/06/28 23:18:37'),
(4, 'Kanji', 'Papua', 'Tidak Menerima', '2022/06/28 23:21:07', '2022/06/28 23:21:07'),
(5, 'yun', 'Jawa Barat', 'Tidak Menerima', '2022/06/28 23:57:51', '2022/06/28 23:58:50');

-- --------------------------------------------------------

--
-- Table structure for table `muzaki`
--

CREATE TABLE `muzaki` (
  `id_muzaki` int(11) NOT NULL,
  `nama_muzaki` varchar(100) NOT NULL,
  `jumlah_jiwa` int(11) NOT NULL,
  `jumlah_zakat_beras` int(11) NOT NULL DEFAULT 0,
  `jumlah_zakat_uang` int(11) NOT NULL,
  `shodaqoh` int(11) NOT NULL,
  `nik` char(20) DEFAULT NULL,
  `tgl_tambah` text NOT NULL,
  `tgl_update` text NOT NULL,
  `subTotal` int(11) NOT NULL,
  `pembayaran` int(11) NOT NULL,
  `kembalian` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `muzaki`
--

INSERT INTO `muzaki` (`id_muzaki`, `nama_muzaki`, `jumlah_jiwa`, `jumlah_zakat_beras`, `jumlah_zakat_uang`, `shodaqoh`, `nik`, `tgl_tambah`, `tgl_update`, `subTotal`, `pembayaran`, `kembalian`) VALUES
(1, 'Raboh', 2, 6, 0, 0, '351', '2022/06/16 15:00:41', '2022/06/21 22:50:26', 0, 0, 0),
(2, 'Hafi', 3, 0, 120000, 30000, '351', '2022/06/18 13:56:50', '2022/06/18 13:56:50', 150000, 150000, 0),
(3, 'Hafi', 1, 0, 40000, 0, '351', '2022/06/18 14:05:06', '2022/06/18 14:05:06', 40000, 50000, 10000),
(4, 'Hafi', 1, 0, 40000, 20000, '351', '2022/06/18 14:20:14', '2022/06/18 14:20:14', 60000, 100000, 40000),
(5, 'Hafi', 2, 0, 80000, 10000, '351', '2022/06/18 14:21:58', '2022/06/18 14:21:58', 90000, 100000, 10000),
(6, 'fahreza', 3, 0, 120000, 10000, '990', '2022/06/18 22:11:40', '2022/06/18 22:11:40', 130000, 150000, 20000),
(7, 'Hafi Ihza', 3, 0, 120000, 0, '351', '2022/06/19 09:02:10', '2022/06/19 09:02:10', 120000, 150000, 30000),
(8, 'Robinsyong', 3, 0, 120000, 10000, '352', '2022/06/19 14:04:39', '2022/06/19 14:04:39', 130000, 150000, 20000),
(9, 'fahreza', 7, 0, 280000, 30000, '990', '2022/06/20 12:43:58', '2022/06/20 12:43:58', 310000, 350000, 40000),
(10, 'Hafi Ihza Farhans', 4, 0, 200000, 20000, '351', '2022/06/20 13:37:20', '2022/06/20 13:37:20', 220000, 300000, 80000),
(12, 'Rommel', 3, 0, 150000, 80000, '352', '2022/06/21 22:47:21', '2022/06/21 22:47:21', 230000, 230000, 0),
(13, 'Sandi', 3, 9, 0, 30000, '778', '2022/06/26 19:03:42', '2022/06/26 19:12:28', 30000, 30000, 0),
(14, 'Kaka', 2, 3, 40000, 0, '909', '2022/06/26 19:06:43', '2022/06/26 19:06:43', 40000, 40000, 0),
(15, 'Raditsunk', 10, 27, 40000, 0, '778', '2022/06/28 06:37:19', '2022/06/28 06:37:19', 40000, 40000, 0),
(16, 'Haridinata', 4, 9, 40000, 0, '880', '2022/06/29 00:07:10', '2022/06/29 00:07:10', 40000, 50000, 10000),
(17, 'Fahreza', 3, 0, 120000, 0, '843', '2022/06/29 00:38:24', '2022/06/29 00:38:24', 120000, 150000, 30000),
(18, 'Fahreza', 3, 0, 120000, 30000, '843', '2022/06/29 00:40:24', '2022/06/29 00:40:24', 150000, 150000, 0),
(19, 'hai', 3, 6, 40000, 0, '676', '2022/06/29 08:35:17', '2022/06/29 08:35:17', 40000, 40000, 0),
(20, 'funl', 3, 9, 0, 0, '44', '2022/06/29 08:36:52', '2022/06/29 08:36:52', 0, 0, 0),
(21, 'daffa', 3, 0, 120000, 30000, '218', '2022/06/29 09:05:27', '2022/06/29 09:05:27', 150000, 160000, 10000);

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id_pengguna` int(11) NOT NULL,
  `nama_pengguna` varchar(30) NOT NULL,
  `password_pengguna` varchar(30) NOT NULL,
  `nik` char(20) NOT NULL,
  `username_pengguna` varchar(30) NOT NULL,
  `waktu_daftar` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id_pengguna`, `nama_pengguna`, `password_pengguna`, `nik`, `username_pengguna`, `waktu_daftar`) VALUES
(1, 'Hafi Ihza Farhans', 'hafi2019', '351', 'farhana', '2022/06/17 09:34:42'),
(32, 'Fahreza Dani', 'fahreza2015', '843', 'fahreza', '2022/06/29 00:33:25'),
(33, 'daffa', '12345678', '218', 'daffa', '2022/06/29 09:02:51');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `logging`
--
ALTER TABLE `logging`
  ADD PRIMARY KEY (`No`);

--
-- Indexes for table `log_item`
--
ALTER TABLE `log_item`
  ADD PRIMARY KEY (`id_log_item`);

--
-- Indexes for table `log_mustahik`
--
ALTER TABLE `log_mustahik`
  ADD PRIMARY KEY (`id_log_mustahik`);

--
-- Indexes for table `log_muzaki`
--
ALTER TABLE `log_muzaki`
  ADD PRIMARY KEY (`id_log_muzaki`);

--
-- Indexes for table `mustahik`
--
ALTER TABLE `mustahik`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `muzaki`
--
ALTER TABLE `muzaki`
  ADD PRIMARY KEY (`id_muzaki`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id_pengguna`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `logging`
--
ALTER TABLE `logging`
  MODIFY `No` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=127;

--
-- AUTO_INCREMENT for table `log_item`
--
ALTER TABLE `log_item`
  MODIFY `id_log_item` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `log_mustahik`
--
ALTER TABLE `log_mustahik`
  MODIFY `id_log_mustahik` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `log_muzaki`
--
ALTER TABLE `log_muzaki`
  MODIFY `id_log_muzaki` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `mustahik`
--
ALTER TABLE `mustahik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `muzaki`
--
ALTER TABLE `muzaki`
  MODIFY `id_muzaki` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id_pengguna` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
