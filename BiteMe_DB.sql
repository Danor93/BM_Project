-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: bitemedb
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
-- Table structure for table `buss_client`
--

DROP TABLE IF EXISTS `buss_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buss_client` (
  `ID` varchar(45) NOT NULL,
  `companyName` varchar(45) NOT NULL,
  `budget` varchar(45) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `id_fk1_idx` (`ID`),
  CONSTRAINT `id_fk1` FOREIGN KEY (`ID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buss_client`
--

LOCK TABLES `buss_client` WRITE;
/*!40000 ALTER TABLE `buss_client` DISABLE KEYS */;
/*!40000 ALTER TABLE `buss_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `client_id` varchar(45) NOT NULL,
  `w4c_private` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `CreditCardNumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  KEY `userId` (`client_id`),
  CONSTRAINT `userId_fk` FOREIGN KEY (`client_id`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES ('111','6878','Active',NULL),('123','777','Freeze',NULL),('134','888','Active',NULL);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
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
  `companyStatus` varchar(45) NOT NULL,
  PRIMARY KEY (`w4cBusiness`,`companyName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES ('111','Microsoft','Approved'),('222','Amazon','Not approved'),('22233','Elbit','Approved'),('333','Intel','Approved'),('444','Google','Approved'),('555','Facebook','Approved'),('666','Refael','Waiting'),('777','Nivdia','Not approved');
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
  `restId1` varchar(45) NOT NULL,
  `supplierName` varchar(45) NOT NULL,
  `price` float NOT NULL,
  `choiceFactor` varchar(45) DEFAULT NULL,
  `choiceDetails` varchar(45) DEFAULT NULL,
  `ingredients` varchar(200) DEFAULT NULL,
  `extra` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`dishName`,`restId1`),
  KEY `restId` (`restId1`),
  CONSTRAINT `restID_fkk` FOREIGN KEY (`restId1`) REFERENCES `supplier` (`restId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishes`
--

LOCK TABLES `dishes` WRITE;
/*!40000 ALTER TABLE `dishes` DISABLE KEYS */;
INSERT INTO `dishes` VALUES ('cheese cake','Dessert','1','vivino',6,'with','Strawberries / blueberries','cheese,aggs,cramble','no cramble'),('greek salad','Salad','1','vivino',3.5,'size','S/M/L','tomato,cucamber,onion','no onion/ no pinuts/extra cheese'),('home salad','Salad','1','vivino',7.5,'','','tomato,cucamber,onion','');
/*!40000 ALTER TABLE `dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dishesinorder`
--

DROP TABLE IF EXISTS `dishesinorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dishesinorder` (
  `dishId` int NOT NULL AUTO_INCREMENT,
  `orderNum` int NOT NULL,
  `dishName` varchar(45) NOT NULL,
  `restID` varchar(45) NOT NULL,
  `dishType` varchar(45) NOT NULL,
  `choiceFactor` varchar(45) DEFAULT NULL,
  `choiceDetails` varchar(45) DEFAULT NULL,
  `extra` varchar(45) DEFAULT NULL,
  `quentity` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`dishId`,`orderNum`,`dishName`),
  KEY `orderNumber` (`orderNum`),
  CONSTRAINT `orderNumber_fk` FOREIGN KEY (`orderNum`) REFERENCES `order` (`orderNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishesinorder`
--

LOCK TABLES `dishesinorder` WRITE;
/*!40000 ALTER TABLE `dishesinorder` DISABLE KEYS */;
INSERT INTO `dishesinorder` VALUES (3,15,'home salad','1','Salad','','','',2),(4,15,'cheese cake','1','Dessert','with','Strawberries ','',2),(5,16,'home salad','1','Salad','','','',2),(6,17,'home salad','1','Salad','','','',2),(7,17,'cheese cake','1','Dessert','with',' blueberries','',1),(8,18,'greek salad','1','Salad','size','S','',1),(9,18,'home salad','1','Salad','','','',1),(10,19,'greek salad','1','Salad','size','S','',1),(11,19,'home salad','1','Salad','','','',1),(12,20,'greek salad','1','Salad','size','S','',1),(13,20,'home salad','1','Salad','','','',1);
/*!40000 ALTER TABLE `dishesinorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `import_users`
--

DROP TABLE IF EXISTS `import_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `import_users` (
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `id` varchar(45) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `import_users`
--

LOCK TABLES `import_users` WRITE;
/*!40000 ALTER TABLE `import_users` DISABLE KEYS */;
INSERT INTO `import_users` VALUES ('ds','ds','danor','sinai','3111','d@gmail.com','050266',''),('ag','ag','aviel','gabay','312987','a@gmail.com','053456',''),('so','so','sahar','oz','31456','s@gmail.com','052222','');
/*!40000 ALTER TABLE `import_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `orderNumber` int NOT NULL AUTO_INCREMENT,
  `orderType` varchar(45) DEFAULT NULL,
  `restName` varchar(45) DEFAULT NULL,
  `rstID` varchar(45) NOT NULL,
  `totalPrice` varchar(45) DEFAULT NULL,
  `timeOfOrder` varchar(45) DEFAULT NULL,
  `dateOfOrder` varchar(45) DEFAULT NULL,
  `orderStatus` varchar(45) DEFAULT NULL,
  `costumerID` varchar(45) NOT NULL,
  `usedRefund` varchar(45) NOT NULL DEFAULT '0',
  `usedBudget` int NOT NULL DEFAULT '0',
  `EarlyOrder` varchar(45) NOT NULL,
  PRIMARY KEY (`orderNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (3,'Take Away','vivino','1','7.5','12:00','2021-12-23','Done','134','0',0,''),(4,'Take Away','vivino','1','6.75','14:00','2021-12-23','Waiting for approval','123','0',0,''),(5,'Take Away','vivino','1','7.5','12:00','2021-12-23','Sended','134','0',0,''),(6,'Take Away','vivino','1','6.75','13:00','2021-12-23','Waiting for approval','123','0',0,''),(7,'Take Away','vivino','1','7.5','11:00','2021-12-23','Waiting for approval','134','0',0,''),(8,'Take Away','vivino','1','6.75','11:00','2021-12-23','Done','123','0',0,''),(9,'Take Away','vivino','1','6.75','11:00','2021-12-23','Waiting for approval','123','0',0,''),(10,'Take Away','vivino','1','7.5','11:00','2021-12-23','Waiting for approval','134','0',0,''),(11,'Take Away','vivino','1','7.5','11:00','2021-12-23','Waiting for approval','134','0',0,''),(12,'Take Away','vivino','1','12.15','11:00','2021-12-23','Done','123','0',0,''),(13,'Take Away','vivino','1','13.5','12:00','2021-12-23','Waiting for approval','134','0',0,''),(14,'Take Away','vivino','1','12.15','12:00','2021-12-23','Waiting for approval','3111','0',0,''),(15,'Take Away','vivino','1','13.5','11:00','2021-12-23','Done','134','0',0,''),(16,'Take Away','vivino','1','13.5','11:00','2021-12-23','Waiting for approval','123','0',0,''),(17,'Take Away','vivino','1','21.0','11:00','2021-12-23','Waiting for approval','134','0',0,''),(18,'Take Away','vivino','1','11.0','14:00','2021-12-24','Waiting for approval','123','0',0,'yes'),(19,'Take Away','vivino','1','6.8999996','14:00','2021-12-24','Waiting for approval','123','3',0,'yes'),(20,'Take Away','vivino','1','6.8999996','14:00','2021-12-24','Done','123','3',0,'yes'),(21,'Take Away','refaelo','2','10.5','12:30','2021-12-24','Waiting for approval','123','0',0,'yes'),(22,'Take Away','refaelo','2','17.5','13:45','2021-12-24','Waiting for approval','123','0',0,'yes');
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
  `restId` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`,`restId`),
  CONSTRAINT `id_fk` FOREIGN KEY (`ID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund`
--

LOCK TABLES `refund` WRITE;
/*!40000 ALTER TABLE `refund` DISABLE KEYS */;
INSERT INTO `refund` VALUES ('123','3','1');
/*!40000 ALTER TABLE `refund` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `quarter` varchar(50) NOT NULL,
  `year` varchar(45) NOT NULL,
  `date_added` datetime NOT NULL,
  `file_name` varchar(50) NOT NULL,
  `upload_file` longblob NOT NULL,
  `homebranch` varchar(45) NOT NULL,
  PRIMARY KEY (`quarter`,`date_added`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `restId` varchar(45) NOT NULL,
  `supplierName` varchar(45) NOT NULL,
  `openingTime` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `supplierStatus` varchar(45) DEFAULT NULL,
  `homeBranch` varchar(45) DEFAULT NULL,
  `Certified_Employee` varchar(45) NOT NULL,
  `Confirm_Employee` varchar(45) NOT NULL,
  PRIMARY KEY (`restId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('1','vivino','10:00-20:00','haifa','haifa','Approved','north','',''),('2','refaelo','11:00-20:00','haifa','haifa','Approved','north','',''),('3','mcdonalds','11:00-23:00','kiryat haim','ahi eilat','Approved','north','',''),('4','japanika','12:00-00:00','kiryat haim','big center','Not approved','north','',''),('5','suso&suns','12:00-00:00','tel aviv','dizingof','Not approved','center','','');
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
  `homeBranch` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `homeBranch` (`homeBranch`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('supp','supp','Supplier','ron','abu','1','asa','234',0,'center'),('adi','a1','Customer','tali','or','111','as@as.com','050344',0,'north'),('h','h','HR Intel','avi','sofer','1211','h@h.co.il','052121',0,'north'),('a','a','Customer','adi','sasson','123','***','***',0,'north'),('b','b','Customer','talia','blum','134','111','111',0,'center'),('supp2','supp2','Supplier','adi','blum','2','222','222',0,'north'),('ag','ag','Customer','aviel','gabay','312987','a@gmail.com','053456',0,'North'),('c','c','BranchManager','sahar','oz','456','b@b.co.il','054678',0,'north'),('e','e','CEO','lior','shauli','689','c@c.co.il','054789',0,'');
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

-- Dump completed on 2021-12-27  8:13:22
