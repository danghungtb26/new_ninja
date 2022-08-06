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
-- Table structure for table `gift_code`
--

DROP TABLE IF EXISTS `gift_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gift_code` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `code` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `item_id` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `item_quantity` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `item_isLock` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `item_expires` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `isPlayer` tinyint DEFAULT '0',
  `player` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `isTime` tinyint DEFAULT '0',
  `time` datetime DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='Mã quà tặng';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gift_code`
--

LOCK TABLES `gift_code` WRITE;
/*!40000 ALTER TABLE `gift_code` DISABLE KEYS */;
INSERT INTO `gift_code` VALUES (3,'full','[\"733\",\"734\",\"735\",\"736\",\"737\",\"738\",\"739\",\"740\",\"741\",\"788\",\"789\",\"775\"]','[\"20000\",\"20000\",\"20000\",\"20000\",\"20000\",\"20000\",\"20000\",\"20000\",\"20000\",\"20000\",\"20000\",\"20000\"]','[\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\"]','[\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\"]',1,NULL,0,NULL,'2022-02-20 05:56:20','2022-02-20 05:56:20'),(4,'nlsktrungthu','[\"292\",\"293\",\"294\",\"295\",\"296\",\"297\",\"302\",\"303\"]','[\"20000\",\"20000\",\"20000\",\"20000\",\"20000\",\"20000\",\"20000\",\"20000\"]','[\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\"]','[\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\",\"-1\"]',1,NULL,0,NULL,'2022-02-20 05:56:20','2022-02-20 05:56:20'),(5,'tanthu','[\"-1\",\"-2\", \"-3\",\"385\"]','[\"20000000\",\"1000000000\",\"1000000000\",\"100\"]','[\"-1\",\"-1\",\"-1\",\"0\"]','[\"-1\",\"-1\",\"-1\",\"-1\"]',0,NULL,0,NULL,'2022-02-19 22:56:20','2022-02-19 22:56:20'),(6,'ruongmq','[\"647\"]','[\"50000\"]','[\"0\"]','[\"-1\"]',0,NULL,0,NULL,'2022-02-19 22:56:20','2022-02-19 22:56:20'),(7,'gt','[\"436\",\"437\",\"438\"]','[\"50000\",\"50000\",\"50000\"]','[\"0\",\"0\",\"0\"]','[\"-1\",\"-1\",\"-1\"]',0,NULL,0,NULL,'2022-02-19 22:56:20','2022-02-19 22:56:20');
/*!40000 ALTER TABLE `gift_code` ENABLE KEYS */;
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
