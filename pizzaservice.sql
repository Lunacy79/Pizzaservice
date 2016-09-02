-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 02. Sep 2016 um 11:52
-- Server-Version: 10.1.9-MariaDB
-- PHP-Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `pizzaservice`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `customers`
--

CREATE TABLE `customers` (
  `cnr` int(10) NOT NULL,
  `fname` varchar(30) NOT NULL,
  `lname` varchar(30) NOT NULL,
  `street` varchar(30) NOT NULL,
  `nr` varchar(10) NOT NULL,
  `plz` varchar(5) NOT NULL,
  `city` varchar(30) NOT NULL,
  `telefon` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `customers`
--

INSERT INTO `customers` (`cnr`, `fname`, `lname`, `street`, `nr`, `plz`, `city`, `telefon`) VALUES
(1, 'Peter', 'Schuldt', 'hauptstr', '33', '33333', 'HH', ''),
(2, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(3, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(4, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(5, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(6, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(7, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(8, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(9, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(10, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(11, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(12, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(13, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(14, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(15, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(16, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(17, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(18, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(19, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(20, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(21, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(22, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(23, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(24, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(25, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(26, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(27, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(28, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(29, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(30, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(31, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(32, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(33, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(34, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(35, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(36, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', ''),
(37, 'Peter', 'Schulz', 'Dorfstr', '23', '24433', 'Hamburg', '');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `orderedpizza`
--

CREATE TABLE `orderedpizza` (
  `pnr` int(11) NOT NULL,
  `onr` int(11) NOT NULL,
  `size` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `orderedtopping`
--

CREATE TABLE `orderedtopping` (
  `tnr` int(10) NOT NULL,
  `pnr` int(10) NOT NULL,
  `topping` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `orders`
--

CREATE TABLE `orders` (
  `onr` int(10) NOT NULL,
  `cnr` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `orders`
--

INSERT INTO `orders` (`onr`, `cnr`) VALUES
(1, 10),
(2, 10),
(3, 13),
(4, 16),
(5, 14),
(6, 10),
(7, 18),
(8, 17),
(9, 12),
(10, 12),
(11, 8),
(12, 15),
(13, 16),
(14, 10);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pizza`
--

CREATE TABLE `pizza` (
  `size` varchar(30) NOT NULL,
  `price` double NOT NULL,
  `topping1` double NOT NULL,
  `topping2` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `pizza`
--

INSERT INTO `pizza` (`size`, `price`, `topping1`, `topping2`) VALUES
('Pizza big', 7.99, 2, 2.5),
('Pizza medium', 6.99, 1.5, 2),
('Pizza small', 5.99, 1, 1.5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `topping`
--

CREATE TABLE `topping` (
  `topping` varchar(30) NOT NULL,
  `priceclass` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `topping`
--

INSERT INTO `topping` (`topping`, `priceclass`) VALUES
('Brokkoli', 1),
('Hack', 2),
('Paprika', 1),
('Salami', 2),
('Tomaten', 1);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`cnr`);

--
-- Indizes für die Tabelle `orderedpizza`
--
ALTER TABLE `orderedpizza`
  ADD PRIMARY KEY (`pnr`);

--
-- Indizes für die Tabelle `orderedtopping`
--
ALTER TABLE `orderedtopping`
  ADD PRIMARY KEY (`tnr`);

--
-- Indizes für die Tabelle `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`onr`);

--
-- Indizes für die Tabelle `pizza`
--
ALTER TABLE `pizza`
  ADD PRIMARY KEY (`size`);

--
-- Indizes für die Tabelle `topping`
--
ALTER TABLE `topping`
  ADD PRIMARY KEY (`topping`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `customers`
--
ALTER TABLE `customers`
  MODIFY `cnr` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT für Tabelle `orderedpizza`
--
ALTER TABLE `orderedpizza`
  MODIFY `pnr` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `orderedtopping`
--
ALTER TABLE `orderedtopping`
  MODIFY `tnr` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `orders`
--
ALTER TABLE `orders`
  MODIFY `onr` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
