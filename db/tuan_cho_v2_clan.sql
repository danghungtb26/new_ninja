-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tuan_cho_v2
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `clan`
--

DROP TABLE IF EXISTS `clan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL DEFAULT '',
  `exp` int NOT NULL DEFAULT '0',
  `level` int NOT NULL DEFAULT '1',
  `itemLevel` int NOT NULL DEFAULT '0',
  `coin` int NOT NULL DEFAULT '1000000',
  `reg_date` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL DEFAULT '28/05/2003 16:05:22',
  `log` varchar(5000) CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL,
  `alert` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL DEFAULT '',
  `use_card` tinyint NOT NULL DEFAULT '4',
  `openDun` tinyint NOT NULL DEFAULT '3',
  `debt` tinyint NOT NULL DEFAULT '0',
  `members` longtext CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL,
  `items` varchar(5000) CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL DEFAULT '[]',
  `week` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL DEFAULT '2003-05-28 22:22:1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clan`
--

LOCK TABLES `clan` WRITE;
/*!40000 ALTER TABLE `clan` DISABLE KEYS */;
INSERT INTO `clan` VALUES (73,'admin',1497663,30,5,353400000,'2022-08-06 11:56:45','aaaaaaaa,2,500000000,2022-08-06 12:05:43\naaaaaaaa,5,6100000,2022-08-06 12:03:22\naaaaaaaa,5,5800000,2022-08-06 12:02:50\naaaaaaaa,5,5500000,2022-08-06 12:02:49\naaaaaaaa,5,5200000,2022-08-06 12:02:22\naaaaaaaa,5,4900000,2022-08-06 12:02:20\naaaaaaaa,5,4600000,2022-08-06 12:01:52\naaaaaaaa,5,4300000,2022-08-06 12:01:32\naaaaaaaa,5,4000000,2022-08-06 12:01:30\naaaaaaaa,5,3700000,2022-08-06 12:01:29\naaaaaaaa,5,3400000,2022-08-06 12:01:28\n','',4,3,0,'[[1057,\"aaaaaaaa\",\"admin\",4,146,1,6411613,6411613]]','[{\"isLock\":false,\"expires\":604800000,\"sale\":0,\"quantity\":1,\"upgrade\":0,\"index\":0,\"id\":601,\"sys\":0,\"isExpires\":true,\"option\":[]}]','2022-08-06 11:56:45');
/*!40000 ALTER TABLE `clan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-06 13:26:10
