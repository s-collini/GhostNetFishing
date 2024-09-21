-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: ghostnetfishing
-- ------------------------------------------------------
-- Server version	11.2.2-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `form`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form`
--

LOCK TABLES `form` WRITE;
/*!40000 ALTER TABLE `form` DISABLE KEYS */;
INSERT INTO `form` VALUES (1,'Marianne','+43676859485','Rauter'),(2,'Sara','+43676859461','Feier'),(3,'Sara','+43676859461','Feier'),(4,'Judith','+43664512658','Marte'),(5,'','',''),(6,'Markus','+43680154678','Hausleitner');
/*!40000 ALTER TABLE `form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `form_ghostnets`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `form_ghostnets` (
  `Form_id` bigint(20) NOT NULL,
  `ghostNets_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_j0p2xj8bvi1jspgcy44dlngpp` (`ghostNets_id`),
  KEY `FK60ksr00rk4s0qs6kx360v3ics` (`Form_id`),
  CONSTRAINT `FK60ksr00rk4s0qs6kx360v3ics` FOREIGN KEY (`Form_id`) REFERENCES `form` (`id`),
  CONSTRAINT `FKe0pjnoe9wey4h011qne7ufmla` FOREIGN KEY (`ghostNets_id`) REFERENCES `ghostnets` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `form_ghostnets`
--

LOCK TABLES `form_ghostnets` WRITE;
/*!40000 ALTER TABLE `form_ghostnets` DISABLE KEYS */;
/*!40000 ALTER TABLE `form_ghostnets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ghostnets`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ghostnets` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `length` double DEFAULT NULL,
  `width` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `reported_by_id` bigint(20) DEFAULT NULL,
  `rescued_by_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rescued_by` (`rescued_by_id`),
  KEY `fk_reported_by` (`reported_by_id`),
  CONSTRAINT `fk_reported_by` FOREIGN KEY (`reported_by_id`) REFERENCES `form` (`id`),
  CONSTRAINT `fk_rescued_by` FOREIGN KEY (`rescued_by_id`) REFERENCES `form` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ghostnets`
--

LOCK TABLES `ghostnets` WRITE;
/*!40000 ALTER TABLE `ghostnets` DISABLE KEYS */;
INSERT INTO `ghostnets` VALUES (1,2.1103,41.3275,1.2,1.5,'gemeldet',NULL,NULL),(2,7.4664,43.0731,0.8,1.4,'Bergung bevorstehend',NULL,NULL),(3,25.2797,54.6872,1.5,1.6,'gemeldet',NULL,NULL),(4,2.3522,48.8566,1,1.4,'Bergung bevorstehend',NULL,NULL),(5,12.4964,41.9028,1.1,1.6,'Bergung bevorstehend',NULL,NULL),(6,6.444979,42.453905,1,2,'verschollen',NULL,NULL),(7,12.026034,41.605219,2.5,0.5,'geborgen',NULL,NULL),(8,7.991838,43.459216,1,0.5,'verschollen',NULL,NULL),(9,2.657853,41.303848,1,0.8,'Bergung bevorstehend',NULL,NULL),(10,13.439791,44.870035,2.5,3,'geborgen',NULL,NULL),(11,31.2357,30.0444,1.2,1.5,'gemeldet',NULL,NULL),(12,151.2093,-33.8688,1,1.4,'Bergung bevorstehend',NULL,NULL),(13,139.4916,-17.7134,1.5,1.6,'verschollen',NULL,NULL),(14,139.6917,35.6895,1.3,1.8,'geborgen',NULL,NULL),(15,1.970494,39.302714,3,4.5,'Bergung bevorstehend',NULL,NULL),(16,51.1485,11.57994,0.5,1,'geborgen',1,3),(17,33.15789,21.14578,5,4.5,'verschollen',4,NULL),(18,41.21458,11.245896,1,2,'Bergung bevorstehend',5,6);
/*!40000 ALTER TABLE `ghostnets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'ghostnetfishing'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-12 18:56:39
