-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `administrativniucet`
--

DROP TABLE IF EXISTS `administrativniucet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrativniucet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `jmenoUctu` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `jmenoUctu_UNIQUE` (`jmenoUctu`),
  CONSTRAINT `AdminAccount` FOREIGN KEY (`jmenoUctu`) REFERENCES `ucet` (`jmenoUctu`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrativniucet`
--

LOCK TABLES `administrativniucet` WRITE;
/*!40000 ALTER TABLE `administrativniucet` DISABLE KEYS */;
INSERT INTO `administrativniucet` VALUES (2,'admin1');
/*!40000 ALTER TABLE `administrativniucet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beznyucet`
--

DROP TABLE IF EXISTS `beznyucet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `beznyucet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `jmenoUctu` varchar(45) NOT NULL,
  `zustatek` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `jmenoUctu_UNIQUE` (`jmenoUctu`),
  CONSTRAINT `RegularAccount` FOREIGN KEY (`jmenoUctu`) REFERENCES `ucet` (`jmenoUctu`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beznyucet`
--

LOCK TABLES `beznyucet` WRITE;
/*!40000 ALTER TABLE `beznyucet` DISABLE KEYS */;
INSERT INTO `beznyucet` VALUES (7,'regular1',0),(8,'regular2',100);
/*!40000 ALTER TABLE `beznyucet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mince`
--

DROP TABLE IF EXISTS `mince`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mince` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cena` double DEFAULT NULL,
  `rok` int DEFAULT NULL,
  `nazev` varchar(100) DEFAULT NULL,
  `kov` varchar(45) DEFAULT NULL,
  `zeme` varchar(45) DEFAULT NULL,
  `nazevKolekce` varchar(100) DEFAULT NULL,
  `obrazekCesta` varchar(150) DEFAULT NULL,
  `jmenoProdavajiciho` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `prodavane_mince_idx` (`jmenoProdavajiciho`),
  CONSTRAINT `ProdavajiciJmeno` FOREIGN KEY (`jmenoProdavajiciho`) REFERENCES `beznyucet` (`jmenoUctu`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mince`
--

LOCK TABLES `mince` WRITE;
/*!40000 ALTER TABLE `mince` DISABLE KEYS */;
INSERT INTO `mince` VALUES (19,250,1936,'Silver Reichsmark','Silver','Germany',NULL,'1936-1939 Silver 2 Reichsmark.jpg','regular2');
/*!40000 ALTER TABLE `mince` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `podporaucet`
--

DROP TABLE IF EXISTS `podporaucet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `podporaucet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `jmenoUctu` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `jmenoUctu_UNIQUE` (`jmenoUctu`),
  CONSTRAINT `SupportAccount` FOREIGN KEY (`jmenoUctu`) REFERENCES `ucet` (`jmenoUctu`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `podporaucet`
--

LOCK TABLES `podporaucet` WRITE;
/*!40000 ALTER TABLE `podporaucet` DISABLE KEYS */;
INSERT INTO `podporaucet` VALUES (2,'support1'),(3,'support2');
/*!40000 ALTER TABLE `podporaucet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transakce`
--

DROP TABLE IF EXISTS `transakce`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transakce` (
  `idTransakce` int NOT NULL AUTO_INCREMENT,
  `nazevMince` varchar(45) NOT NULL,
  `cena` double NOT NULL,
  `prodavajici` varchar(45) NOT NULL,
  `kupujici` varchar(45) NOT NULL,
  PRIMARY KEY (`idTransakce`),
  UNIQUE KEY `idtransaction_UNIQUE` (`idTransakce`),
  KEY `Prodavajici_idx` (`prodavajici`),
  KEY `Kupujici_idx` (`kupujici`),
  CONSTRAINT `Kupujici` FOREIGN KEY (`kupujici`) REFERENCES `beznyucet` (`jmenoUctu`),
  CONSTRAINT `Prodavajici` FOREIGN KEY (`prodavajici`) REFERENCES `beznyucet` (`jmenoUctu`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transakce`
--

LOCK TABLES `transakce` WRITE;
/*!40000 ALTER TABLE `transakce` DISABLE KEYS */;
/*!40000 ALTER TABLE `transakce` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ucet`
--

DROP TABLE IF EXISTS `ucet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ucet` (
  `jmenoUctu` varchar(45) NOT NULL,
  `heslo` varchar(45) NOT NULL,
  `jmeno` varchar(45) NOT NULL,
  `prijmeni` varchar(45) NOT NULL,
  `typUctu` int NOT NULL,
  PRIMARY KEY (`jmenoUctu`),
  UNIQUE KEY `jmenoUctu_UNIQUE` (`jmenoUctu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ucet`
--

LOCK TABLES `ucet` WRITE;
/*!40000 ALTER TABLE `ucet` DISABLE KEYS */;
INSERT INTO `ucet` VALUES ('admin1','123','Vyacheslav','Novak',2),('regular1','123','Jan','Novak',0),('regular2','123','Jana','Novakova',0),('support1','123','John','Smith',1),('support2','123','Mary','Poppins',1);
/*!40000 ALTER TABLE `ucet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zprava`
--

DROP TABLE IF EXISTS `zprava`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zprava` (
  `id` int NOT NULL AUTO_INCREMENT,
  `druhZpravy` int DEFAULT NULL,
  `text` varchar(500) NOT NULL,
  `predmet` varchar(120) DEFAULT NULL,
  `odesilatel` varchar(45) NOT NULL,
  `prijemce` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zprava`
--

LOCK TABLES `zprava` WRITE;
/*!40000 ALTER TABLE `zprava` DISABLE KEYS */;
/*!40000 ALTER TABLE `zprava` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-25 22:09:37
