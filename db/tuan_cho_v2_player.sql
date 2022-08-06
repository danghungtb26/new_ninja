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
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` char(15) CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL,
  `password` char(30) CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL,
  `invite` varchar(150) DEFAULT NULL,
  `lock` tinyint NOT NULL DEFAULT '0',
  `ban` tinyint DEFAULT '0',
  `email` varchar(999) DEFAULT NULL,
  `otp` int DEFAULT NULL,
  `luong` bigint NOT NULL DEFAULT '0',
  `ninja` varchar(500) NOT NULL DEFAULT '[]',
  `coin` bigint NOT NULL DEFAULT '0',
  `status` int NOT NULL DEFAULT '-1',
  `role` int DEFAULT '0',
  `online` tinyint DEFAULT '0',
  `vip` int NOT NULL DEFAULT '0',
  `coinnap` int NOT NULL DEFAULT '0',
  `vongxoayluong` int NOT NULL DEFAULT '0',
  `mocnap` int NOT NULL DEFAULT '0',
  `tiennap` int NOT NULL DEFAULT '0',
  `remember_token` varchar(150) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `id` (`id`,`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20263 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (1,'admin','1',NULL,0,0,NULL,NULL,59764816,'[\"aaaaaaaa\",\"acascsac\"]',0,0,0,0,0,0,0,0,0,NULL,'2022-05-16 13:25:16','2022-05-16 13:25:16'),(2,'hungdv','1',NULL,0,0,NULL,NULL,19865716,'[\"cakakaaa\"]',0,0,0,0,0,0,0,0,0,NULL,'2022-05-16 13:25:16','2022-05-16 13:25:16');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-06 13:26:11
