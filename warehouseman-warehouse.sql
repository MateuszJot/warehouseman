-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sty 30, 2024 at 07:05 PM
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
-- Database: `warehouseman-warehouse`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `item_cloned`
--

CREATE TABLE `item_cloned` (
  `id` binary(16) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `supplier_code` varchar(255) DEFAULT NULL,
  `supplierid` binary(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `item_cloned`
--

INSERT INTO `item_cloned` (`id`, `code`, `name`, `price`, `supplier_code`, `supplierid`) VALUES
(0x22fb48537e0c4015adc8dc3c214f970b, 'JA1', 'Jabłko', 20, 'JM1', 0xdb01d330056d46eabb79a0046bd79b60),
(0x93f11e6d7efb4f05a45c2dbd8bb499f3, 'BA1', 'Banan', 30, 'JM1', 0xdb01d330056d46eabb79a0046bd79b60),
(0xf1d57e1d88434466a6c088ca2d62d1d4, 'CW3', 'Czekolada', 200, 'NE10', 0x92f395969e0644b4811550c51c8cd477),
(0xf257597e258e414daeb5c79ed9c9429d, 'PS2', 'Płatki Śniadaniowe', 20, 'NE10', 0x92f395969e0644b4811550c51c8cd477);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `stored_item`
--

CREATE TABLE `stored_item` (
  `id` binary(16) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `item_id` binary(16) DEFAULT NULL,
  `warehouse_id` binary(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `stored_item`
--

INSERT INTO `stored_item` (`id`, `quantity`, `item_id`, `warehouse_id`) VALUES
(0x011a01d340684b889009ea8d90cf9128, 1, 0xf257597e258e414daeb5c79ed9c9429d, 0xbd5f55d3f52d4cd9b3186be6bd80e0a0),
(0x6996eed59e60477f91b56a31f4cc9126, 1, 0x93f11e6d7efb4f05a45c2dbd8bb499f3, 0x2cb9e3f58d574502ac4aee08037b884d),
(0xc625996f6a5d47f2a91dbba25944357c, 1, 0xf1d57e1d88434466a6c088ca2d62d1d4, 0xbd5f55d3f52d4cd9b3186be6bd80e0a0),
(0xd3f6dc97876845e791b7ce347eaa5585, 1, 0x22fb48537e0c4015adc8dc3c214f970b, 0x2cb9e3f58d574502ac4aee08037b884d);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `warehouse`
--

CREATE TABLE `warehouse` (
  `id` binary(16) NOT NULL,
  `address1` varchar(255) DEFAULT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `warehouse`
--

INSERT INTO `warehouse` (`id`, `address1`, `address2`, `code`, `country`, `name`) VALUES
(0x2725e1edb2de49dd8a2fe75625433f0d, 'Berlin', 'Berlin', 'WH2', 'Niemcy', 'Magazyn2'),
(0x2cb9e3f58d574502ac4aee08037b884d, 'Warszawa ', 'Mazowieckie', 'WH1', 'Polska', 'Magazyn 1'),
(0xbd5f55d3f52d4cd9b3186be6bd80e0a0, 'Amsterdam', 'Amsterdam', 'WH3', 'Holandia', 'Magazyn3');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `item_cloned`
--
ALTER TABLE `item_cloned`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `stored_item`
--
ALTER TABLE `stored_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKi639rh33uh1ffli80v5qwp8vi` (`item_id`),
  ADD KEY `FK3sbmxf2656wtideu80aa1orpe` (`warehouse_id`);

--
-- Indeksy dla tabeli `warehouse`
--
ALTER TABLE `warehouse`
  ADD PRIMARY KEY (`id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `stored_item`
--
ALTER TABLE `stored_item`
  ADD CONSTRAINT `FK3sbmxf2656wtideu80aa1orpe` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouse` (`id`),
  ADD CONSTRAINT `FKi639rh33uh1ffli80v5qwp8vi` FOREIGN KEY (`item_id`) REFERENCES `item_cloned` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
