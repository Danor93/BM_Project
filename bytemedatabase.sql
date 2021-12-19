-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bytemedatabase
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `busclient`
--

DROP TABLE IF EXISTS `busclient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `busclient` (
  `ID` varchar(45) NOT NULL,
  `companyName` varchar(45) NOT NULL,
  `w4cBusiness` varchar(45) NOT NULL,
  `budget` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `id_fk1` FOREIGN KEY (`ID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `busclient`
--

LOCK TABLES `busclient` WRITE;
/*!40000 ALTER TABLE `busclient` DISABLE KEYS */;
/*!40000 ALTER TABLE `busclient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `w4cBusiness` varchar(45) NOT NULL,
  `companyName` varchar(45) NOT NULL,
  `companyStatus` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`w4cBusiness`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES ('123','intel','approved'),('258','nvidia','approved'),('321','refael','approved'),('456','elbit','approved'),('654','apple','approved'),('789','microsoft','approved'),('854','amdocs','approved'),('987','google','approved');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery` (
  `orderNum` varchar(45) NOT NULL,
  `deliveryType` varchar(45) DEFAULT NULL,
  `participantsNum` varchar(45) NOT NULL DEFAULT '1',
  `address` varchar(45) DEFAULT NULL,
  `phone` varchar(45) NOT NULL,
  `recipient` varchar(45) NOT NULL,
  `deliPrice` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dishes`
--

DROP TABLE IF EXISTS `dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dishes` (
  `dishName` varchar(45) NOT NULL,
  `dishType` varchar(45) NOT NULL,
  `restId1` int NOT NULL,
  `supplierName` varchar(45) NOT NULL,
  `inventory` int DEFAULT NULL,
  `price` float NOT NULL,
  `size` varchar(45) DEFAULT NULL,
  `cookLevel` varchar(45) DEFAULT NULL,
  `extra` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`dishName`,`restId1`),
  KEY `restId` (`restId1`),
  KEY `restId1` (`restId1`) /*!80000 INVISIBLE */,
  CONSTRAINT `restId_fk1` FOREIGN KEY (`restId1`) REFERENCES `supplier` (`restId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishes`
--

LOCK TABLES `dishes` WRITE;
/*!40000 ALTER TABLE `dishes` DISABLE KEYS */;
INSERT INTO `dishes` VALUES ('greek','Salad',3,'aviel',20,30,NULL,NULL,NULL),('greek salad','Salad',1,'vivino',3,3.5,'*','*','*'),('halomi','Salad',3,'aviel',50,20,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dishesinorder`
--

DROP TABLE IF EXISTS `dishesinorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dishesinorder` (
  `dishName` varchar(45) NOT NULL,
  `orderNum` varchar(45) NOT NULL,
  `size` varchar(45) DEFAULT NULL,
  `cookLevel` varchar(45) DEFAULT NULL,
  `extra` varchar(45) DEFAULT NULL,
  `restID` int NOT NULL,
  PRIMARY KEY (`dishName`,`orderNum`,`restID`),
  KEY `restID` (`restID`),
  CONSTRAINT `restID_fk` FOREIGN KEY (`restID`) REFERENCES `supplier` (`restId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishesinorder`
--

LOCK TABLES `dishesinorder` WRITE;
/*!40000 ALTER TABLE `dishesinorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `dishesinorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `orderNumber` varchar(45) NOT NULL,
  `orderType` varchar(45) DEFAULT NULL,
  `restName` varchar(45) DEFAULT NULL,
  `totalPrice` varchar(45) DEFAULT NULL,
  `timeOfOrder` varchar(45) DEFAULT NULL,
  `dateOfOrder` varchar(45) DEFAULT NULL,
  `orderStatus` varchar(45) DEFAULT NULL,
  `costumerID` varchar(45) NOT NULL,
  `rstID` int NOT NULL,
  `restId` int DEFAULT NULL,
  PRIMARY KEY (`orderNumber`),
  KEY `costumerID` (`costumerID`),
  KEY `restId` (`restId`),
  KEY `rstID` (`rstID`),
  CONSTRAINT `costumerID_fk` FOREIGN KEY (`costumerID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rstID_fk` FOREIGN KEY (`rstID`) REFERENCES `supplier` (`restId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refund`
--

DROP TABLE IF EXISTS `refund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refund` (
  `ID` varchar(45) NOT NULL,
  `ammount` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `id_fk` FOREIGN KEY (`ID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund`
--

LOCK TABLES `refund` WRITE;
/*!40000 ALTER TABLE `refund` DISABLE KEYS */;
/*!40000 ALTER TABLE `refund` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `restId` int NOT NULL AUTO_INCREMENT,
  `supplierName` varchar(45) NOT NULL,
  `openingTime` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `supplierStatus` varchar(45) DEFAULT NULL,
  `homeBranch` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`restId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'vivino','1','haifa','haifa','approved','north'),(2,'refaelo','2','haifa','haifa','approved','east'),(3,'aviel','3','Telaviv','telaviv','approved','center'),(4,'Mcdonalds','10:00-23:00','Kiryat Haim','Ahi Eilat','not approved','north'),(5,'Japanika','11:00-24:00','Kiryat Ata','Big center','approved','north'),(6,'Limozin','12:00-23:00','Ramat Ishay','center','approved','north');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `Role` varchar(45) DEFAULT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `ID` varchar(45) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `isLoggedIn` int DEFAULT '0',
  `w4cPrivate` varchar(45) DEFAULT NULL,
  `homeBranch` varchar(45) NOT NULL,
  `userStatus` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `homeBranch` (`homeBranch`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('a','a','Customer','adi','sasson','123','***','***',0,'***','north','active'),('b','b','Customer','talia','blum','134','111','111',0,'222','east','active'),('d','d','Supplier','aviel','gabay','3','d@d.co.il','052123',0,'0','0','active'),('e','e','Coustomer','danor','sinai','426','e@e.co.il','052897',0,'0','0','active'),('c','c','BranchManager','sahar','oz','456','c@c.co.il','054879',1,'0','0','active');
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

-- Dump completed on 2021-12-15 19:59:44
