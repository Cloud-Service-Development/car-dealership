-- MariaDB dump 10.19  Distrib 10.4.28-MariaDB, for osx10.10 (x86_64)
--
-- Host: localhost    Database: car_dealership
-- ------------------------------------------------------
-- Server version	10.4.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appointment_date` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `car_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_appointment_user` (`user_id`),
  KEY `FKn2owx2s2lbw7t170byuxsvqyf` (`car_id`),
  CONSTRAINT `FKn2owx2s2lbw7t170byuxsvqyf` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
  CONSTRAINT `fk_appointment_car` FOREIGN KEY (`id`) REFERENCES `car` (`id`),
  CONSTRAINT `fk_appointment_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (1,'2025-01-10 10:00:00','PENDING',1,NULL),(2,'2025-01-11 14:30:00','CONFIRMED',1,NULL),(3,'2025-01-12 09:15:00','PENDING',2,NULL);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `car` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `dealership_id` int(11) DEFAULT NULL,
  `fuelType` varchar(15) DEFAULT NULL,
  `thesis` int(11) DEFAULT NULL,
  `numberOfSameCars` int(11) DEFAULT NULL,
  `fuel_type` varchar(255) DEFAULT NULL,
  `number_of_same_cars` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_car_dealership` (`dealership_id`) USING BTREE,
  CONSTRAINT `fk_car_dealership` FOREIGN KEY (`dealership_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123456795 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES (1,'Toyota','Corolla',18000,3,'diesel',5,3,NULL,NULL),
  (2,'Honda','Civic',20000,3,'gas',4,9,NULL,NULL),
  (3,'Ford','Focus',17000,4,'gas',5,6,NULL,NULL),
  (4,'Toyota','Corolla',20000,8,'gas',5,10,NULL,NULL),
  (5,'Tesla','Electrical',50000,7,'diesel',7,5,NULL,NULL),
  (6,'Bucati','Electrical',50000,7,'hybrid',7,6,NULL,NULL),
  (7,'Volvo','Hybrid',35000,7,'electical',4,3,NULL,NULL),
  (22222,'Hyundai','i30',18000,7,'diesel',4,9,NULL,NULL),
  (123456789,'Hyundai','i20',15000,8,'diesel',5,10,NULL,NULL),
  (123456790,'Toyota','Aygo',12000,7,NULL,4,NULL,NULL,NULL),
  (123456791,'Toyota','Aygo',12000,7,NULL,4,NULL,NULL,NULL),
  (123456792,'Toyota','Aygo',12000,7,NULL,4,NULL,NULL,NULL),
  (123456793,'BMW','M5',120000,7,NULL,5,NULL,'diesel',5),
  (123456794,'BMW','M5',120000,8,NULL,5,NULL,'diesel',5);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `citizen`
--

DROP TABLE IF EXISTS `citizen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `citizen` (
  `contacting` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citizen`
--

LOCK TABLES `citizen` WRITE;
/*!40000 ALTER TABLE `citizen` DISABLE KEYS */;
INSERT INTO `citizen` VALUES (NULL,NULL,'Jo Boy',10),(NULL,NULL,'Jo Boy',11);
/*!40000 ALTER TABLE `citizen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dealer_ship`
--

DROP TABLE IF EXISTS `dealer_ship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dealer_ship` (
  `contact_info` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dealer_ship`
--

LOCK TABLES `dealer_ship` WRITE;
/*!40000 ALTER TABLE `dealer_ship` DISABLE KEYS */;
INSERT INTO `dealer_ship` VALUES ('123-456-7890','123 Main Street','Best Dealership',7),('123-456-7890','Thessaloniki','Hertz',8),(NULL,'Athens','Jo Boy',12);
/*!40000 ALTER TABLE `dealer_ship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dtype` varchar(31) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `contacting` varchar(255) DEFAULT NULL,
  `afm` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Citizen','john@citizen.com','pass123','johnUser','John','Patras','6931111111',11111),
  (2,'Citizen','mary@citizen.com','pass456','maryUser','Mary','Athens','6942222222',22222),
  (3,'DealerShip','carKing@deal.com','dkpass','dealerOne','CarKing Co','Thessaloniki','2310333333',33333),
  (4,'DealerShip','autoHouse@deal.com','autopass','dealerTwo','AutoHouse Inc','Crete','2810123456',444444),
  (5,'Citizen','john@example.com','password123','johnDoe','John Doe','Athens','2100000000',555555),
  (6,'Citizen','john@example.com','password123','johnDoe','John Doe','Thessaloniki','2311111111',66666),
  (7,'DealerShip','dealership@example.com','securePassword','bestDealership','Hary Tomson','Athens','2111111111',777777),
  (8,'DealerShip','dealership@hertz.com','securePassword','Hertz','Hertz','Herakleion','2354000000',888888),
  (9,'Citizen','flikflok@example.com','password123','flikflok','Flik Flok','Thessaloniki','2313000000',999999),
  (10,'Citizen','citizen@example.com','securePassword','PASS123','Luis Hamilton','USA','231400000',12121212),
  (11,'Citizen','citizen@example.com','securePassword','PASS123','Steve Hopkins','USA','210000032',1313131),
  (12,'DealerShip','dealership@enterprize.com','securePassword','PASS123','Enterprize','Patras','235400000',11414141);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-04 23:03:04
