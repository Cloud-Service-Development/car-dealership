-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Εξυπηρετητής: localhost
-- Χρόνος δημιουργίας: 03 Ιαν 2025 στις 18:40:36
-- Έκδοση διακομιστή: 10.4.28-MariaDB
-- Έκδοση PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Βάση δεδομένων: `car_dealership`
--

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `appointment`
--

CREATE TABLE `appointment` (
  `id` bigint(20) NOT NULL,
  `appointment_date` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `citizen_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `appointment`
--

INSERT INTO `appointment` (`id`, `appointment_date`, `status`, `citizen_id`) VALUES
(1, '2025-01-10 10:00:00', 'PENDING', 1),
(2, '2025-01-11 14:30:00', 'CONFIRMED', 1),
(3, '2025-01-12 09:15:00', 'PENDING', 2);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `car`
--

CREATE TABLE `car` (
  `id` bigint(20) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `dealership_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `car`
--

INSERT INTO `car` (`id`, `brand`, `model`, `price`, `dealership_id`) VALUES
(1, 'Toyota', 'Corolla', 18000, 3),
(2, 'Honda', 'Civic', 20000, 3),
(3, 'Ford', 'Focus', 17000, 4);

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `dtype` varchar(31) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `contacting` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Άδειασμα δεδομένων του πίνακα `users`
--

INSERT INTO `users` (`id`, `dtype`, `email`, `password`, `username`, `name`, `location`, `contacting`) VALUES
(1, 'Citizen', 'john@citizen.com', 'pass123', 'johnUser', 'John', 'Patras', '6931111111'),
(2, 'Citizen', 'mary@citizen.com', 'pass456', 'maryUser', 'Mary', 'Athens', '6942222222'),
(3, 'DealerShip', 'carKing@deal.com', 'dkpass', 'dealerOne', 'CarKing Co', 'Thessaloniki', '2310333333'),
(4, 'DealerShip', 'autoHouse@deal.com', 'autopass', 'dealerTwo', 'AutoHouse Inc', 'Crete', '2810123456');

--
-- Ευρετήρια για άχρηστους πίνακες
--

--
-- Ευρετήρια για πίνακα `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_appointment_citizen` (`citizen_id`);

--
-- Ευρετήρια για πίνακα `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_car_dealership` (`dealership_id`);

--
-- Ευρετήρια για πίνακα `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT για άχρηστους πίνακες
--

--
-- AUTO_INCREMENT για πίνακα `appointment`
--
ALTER TABLE `appointment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT για πίνακα `car`
--
ALTER TABLE `car`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT για πίνακα `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `fk_appointment_citizen` FOREIGN KEY (`citizen_id`) REFERENCES `users` (`id`);

--
-- Περιορισμοί για πίνακα `car`
--
ALTER TABLE `car`
  ADD CONSTRAINT `fk_car_dealership` FOREIGN KEY (`dealership_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
