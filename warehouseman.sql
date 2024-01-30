-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sty 30, 2024 at 07:04 PM
-- Wersja serwera: 10.4.28-MariaDB
-- Wersja PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `warehouseman`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `item`
--

CREATE TABLE `item` (
  `id` binary(16) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `supplier_id` binary(16) DEFAULT NULL,
  `warehouseable` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`id`, `code`, `description`, `name`, `price`, `supplier_id`, `warehouseable`) VALUES
(0x22fb48537e0c4015adc8dc3c214f970b, 'JA1', 'Jabłko LUZ', 'Jabłko', 20, 0xdb01d330056d46eabb79a0046bd79b60, b'1'),
(0x2466520ac888473b9da6931b8bf7d1f9, 'PO1', 'Pomidor LUZ', 'Pomidor', 10, 0xdb01d330056d46eabb79a0046bd79b60, b'1'),
(0x5d4704e36e9e488198b6afc1abecce3e, 'RY2', 'Pstrąg łososiowy', 'Ryba', 200, 0x9b8b615753a4432c885af316545c2bbb, b'1'),
(0x93f11e6d7efb4f05a45c2dbd8bb499f3, 'BA1', 'Banan LUZ', 'Banan', 30, 0xdb01d330056d46eabb79a0046bd79b60, b'1'),
(0xf1d57e1d88434466a6c088ca2d62d1d4, 'CW3', 'Czekolada Wedel', 'Czekolada', 200, 0x92f395969e0644b4811550c51c8cd477, b'1'),
(0xf257597e258e414daeb5c79ed9c9429d, 'PS2', 'Płatki śniadaniowe', 'Płatki Śniadaniowe', 20, 0x92f395969e0644b4811550c51c8cd477, b'1');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `supplier`
--

CREATE TABLE `supplier` (
  `id` binary(16) NOT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id`, `address1`, `address2`, `code`, `country`, `name`) VALUES
(0x92f395969e0644b4811550c51c8cd477, 'Lucerna 10', 'a212dtfyge5', 'NE10', 'Szwajcaria', 'Nestle'),
(0x9b8b615753a4432c885af316545c2bbb, 'Luton 23', '123', 'TO2', 'Wielka Brytania', 'Tesco'),
(0xdb01d330056d46eabb79a0046bd79b60, 'XYZ1', 'XYZ2', 'JM1', 'Portugalia', 'Jeronimo Martins'),
(0xf7a992e701cf4175838055f44781348f, 'Monachium 2', 'asdasdasd', 'WE2', 'Niemcy', 'Wedel');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcjes46ncuefgrkgt6ib0oo2bb` (`supplier_id`);

--
-- Indeksy dla tabeli `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `FKcjes46ncuefgrkgt6ib0oo2bb` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
