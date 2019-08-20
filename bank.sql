CREATE DATABASE  IF NOT EXISTS `bank` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bank`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: bank
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `id_client` bigint(100) NOT NULL,
  `identificationNumber` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `creationDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_client_idx` (`id_client`),
  CONSTRAINT `id_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,'RO8V0QRLMZ5ZSYRRN0EX5TK4','Saving Account',123,'2019-03-25 00:00:00'),(2,1,'ROG4E8W0IKYZ560HZW3MH295','Saving Account',20,'2019-03-25 00:00:00'),(3,2,'ROTSA25N5L8GX21ID0E4W35V','Saving Account',104,'2019-03-25 00:00:00'),(6,2,'ROW5HQG0KXETE0Q3YIA6TBGL','Spending Account',7999,'2019-03-25 00:00:00'),(7,3,'ROTESFQTZBPUD0WR5S59T1OS','Spending Account',1311,'2019-03-26 00:00:00'),(8,3,'ROXK9BHA2FGNV00AU1YQVRPK','Spending Account',544,'2019-03-26 00:00:00');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bill` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `id_account` bigint(100) NOT NULL,
  `id_utility` bigint(100) NOT NULL,
  `id_user` bigint(100) NOT NULL,
  `amount` double NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_account_idx` (`id_account`),
  KEY `id_utility_idx` (`id_utility`),
  KEY `id_user_idx` (`id_user`),
  CONSTRAINT `id_account` FOREIGN KEY (`id_account`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_utility` FOREIGN KEY (`id_utility`) REFERENCES `utility` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,6,2,3,300,'2019-03-25 00:00:00'),(2,1,4,3,20,'2019-03-25 00:00:00'),(3,6,5,8,1000,'2019-03-26 00:00:00'),(4,8,6,3,256,'2019-03-26 00:00:00'),(5,8,6,3,300,'2019-03-26 00:00:00'),(6,6,5,3,123,'2019-03-26 00:00:00');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `identityCardNumber` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `cnp` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Neamt Mihai',123,'Pasteur 35','1242355034243'),(2,'Andrei Vlad',12343,'Zorilor 45','12'),(3,'Sfarlea Alex',21311,'Donath 34','114432');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer`
--

DROP TABLE IF EXISTS `transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transfer` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `id_sursa` bigint(100) NOT NULL,
  `id_destinatie` bigint(100) NOT NULL,
  `id_user` bigint(100) NOT NULL,
  `amount` double NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_sursa_idx` (`id_sursa`),
  KEY `id_destinatie_idx` (`id_destinatie`),
  KEY `id_user_idx` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer`
--

LOCK TABLES `transfer` WRITE;
/*!40000 ALTER TABLE `transfer` DISABLE KEYS */;
INSERT INTO `transfer` VALUES (1,1,2,3,40,'2019-03-25 00:00:00'),(3,2,3,3,4,'2019-03-25 00:00:00'),(4,2,1,3,3,'2019-03-25 00:00:00'),(5,2,1,3,5,'2019-03-25 00:00:00'),(6,2,3,3,6,'2019-03-25 00:00:00'),(7,1,3,4,12,'2019-03-26 00:00:00'),(8,1,3,4,12,'2019-03-26 00:00:00'),(9,6,1,4,111,'2019-03-26 00:00:00'),(10,1,3,8,12,'2019-03-26 00:00:00'),(11,6,3,8,100,'2019-03-26 00:00:00'),(12,6,7,3,50,'2019-03-26 00:00:00'),(13,6,7,3,50,'2019-03-26 00:00:00'),(14,7,8,3,100,'2019-03-26 00:00:00'),(15,2,3,3,2,'2019-03-26 00:00:00'),(16,3,7,3,44,'2019-03-26 00:00:00'),(17,6,7,3,666,'2019-03-26 00:00:00'),(18,6,7,3,1,'2019-03-26 00:00:00');
/*!40000 ALTER TABLE `transfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `admin` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Coman','Vasile','vasile','coman',''),(2,'Andrei','Corujan','andrei','andrei',''),(3,'Todea','Daniel','todea','daniel','\0'),(4,'Petre','Iuliana','iuliana','iuliana','\0'),(8,'Petre','Mariana','mari','mari','\0'),(9,'Rad','Alex','rad','rad','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utility`
--

DROP TABLE IF EXISTS `utility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utility` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utility`
--

LOCK TABLES `utility` WRITE;
/*!40000 ALTER TABLE `utility` DISABLE KEYS */;
INSERT INTO `utility` VALUES (1,'EON',100),(2,'Electrica',0),(3,'Apa',150),(4,'Gaz',20),(5,'Digi',1123),(6,'Orange',556);
/*!40000 ALTER TABLE `utility` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-26 11:55:24
