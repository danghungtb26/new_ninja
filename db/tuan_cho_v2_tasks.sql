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
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tasks` varchar(500) NOT NULL DEFAULT '[]',
  `maptasks` varchar(500) NOT NULL DEFAULT '[]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (1,'[12,3,4,6,24,5,7,12]','[22,22,22,22,22,22,22,22]'),(2,'[12,4,4,4,4,4,12]','[22,22,22,22,22,22,22]'),(3,'[12,-2,-2,12]','[22,-2,22,22]'),(4,'[12,4,-2,-2,-2,12]','[22,22,-2,23,23,22]'),(5,'[12,-2,-2,-2,12]','[22,-2,21,23,22]'),(6,'[3,-2,-2,3]','[22,-2,6,22]'),(7,'[7,-2,-2,-2,-2,7]','[22,-2,2,71,26,22]'),(8,'[12,-2,4,4,4,4,4,5,5,5,5,5,6,6,6,6,6,12]','[22,-2,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22,22]'),(9,'[12,-2,9,10,11,12]','[22,-2,1,72,27,22]'),(10,'[-1,-2,-2,-2,-1]','[-1,-2,-2,-2,-1]'),(11,'[-1,-2,-2,-2,-1]','[-1,28,4,46,-1]'),(12,'[-1,-2,-2,-1]','[-1,-2,-2,-1]'),(13,'[-1,-2,-2,-2,-2,-1]','[-1,-2,-2,-2,-2,-1]'),(14,'[0,-2,10,11,9,0]','[-1,-2,72,27,1,-1]'),(15,'[2,-2,-2,-2,2]','[-1,-2,29,40,-1]'),(16,'[1,-2,14,15,16,1]','[-1,-2,31,8,65,-1]'),(17,'[16,-2,-2,-2,16]','[65,-2,63,47,65]'),(18,'[18,-2,17,18]','[32,-2,33,32]'),(19,'[18,-2,-2,-2,18]','[32,-2,50,11,32]'),(20,'[18,-2,-2,18]','[32,-2,63,32]'),(21,'[18,-2,15,18]','[32,-2,8,32]'),(22,'[23,-2,-2,-2,23]','[48,-2,12,34,48]'),(23,'[23,-2,-2,23]','[48,-2,35,48]'),(24,'[23,-2,-2,23]','[48,-2,35,48]'),(25,'[23,-2,-2,23]','[48,-2,-2,48]'),(26,'[19,-2,-2,-2,19]','[10,-2,13,52,10]'),(27,'[19,-2,-2,19]','[10,-2,64,10]'),(28,'[19,-2,-2,19]','[10,-2,14,10]'),(29,'[20,-2,-2,-2,20]','[38,-2,14,15,38]'),(30,'[20,-2,-2,20]','[38,-2,15,38]'),(31,'[20,-2,-2,-2,20]','[38,-2,-2,-2,38]'),(32,'[20,-2,-2,-2,20]','[38,-2,16,68,38]'),(33,'[20,-2,-2,20]','[38,-2,38,38]'),(34,'[22,-2,-2,-2,22]','[17,-2,41,41,17]'),(35,'[22,-2,-2,22]','[17,-2,42,17]'),(36,'[22,-2,-2,22]','[17,-2,24,17]'),(37,'[22,-2,-2,22]','[17,-2,62,17]'),(38,'[22,-2,-2,-2,22]','[17,-2,44,44,17]'),(39,'[22,-2,-2,-2,22]','[17,-2,18,18,17]'),(40,'[22,-2,-2,-2,-2,22]','[17,-2,-2,-2,-2,17]'),(41,'[22,-2,-2,-2,-2,22]','[17,-2,-2,-2,-2,17]'),(42,'[22,-2,-2,-2,22]','[17,-2,-2,-2,17]'),(43,'[22,-2,-2,22]','[17,-2,-2,17]');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-05 16:15:45
