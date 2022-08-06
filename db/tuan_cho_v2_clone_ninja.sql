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
-- Table structure for table `clone_ninja`
--

DROP TABLE IF EXISTS `clone_ninja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clone_ninja` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL DEFAULT '',
  `speed` tinyint NOT NULL DEFAULT '4',
  `level` smallint NOT NULL DEFAULT '1',
  `exp` bigint NOT NULL DEFAULT '0',
  `expdown` bigint NOT NULL DEFAULT '0',
  `pk` tinyint NOT NULL DEFAULT '0',
  `ppoint` int NOT NULL DEFAULT '0',
  `potential0` int NOT NULL DEFAULT '15',
  `potential1` int NOT NULL DEFAULT '5',
  `potential2` int NOT NULL DEFAULT '5',
  `potential3` int NOT NULL DEFAULT '5',
  `spoint` int NOT NULL DEFAULT '0',
  `class` tinyint NOT NULL DEFAULT '1',
  `skill` varchar(5000) NOT NULL DEFAULT '[]',
  `KSkill` varchar(100) NOT NULL DEFAULT '[-1,-1,-1]',
  `OSkill` varchar(100) NOT NULL DEFAULT '[-1,-1,-1,-1,-1]',
  `CSkill` smallint NOT NULL DEFAULT '-1',
  `ItemBody` longtext NOT NULL,
  `ItemMounts` longtext NOT NULL,
  `effect` varchar(1000) NOT NULL DEFAULT '[]',
  `info` varchar(1000) NOT NULL DEFAULT '[8,8,10,10,1,1]',
  `ID_HORSE` int NOT NULL DEFAULT '-1',
  `thoi-trang` varchar(500) DEFAULT '[-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clone_ninja`
--

LOCK TABLES `clone_ninja` WRITE;
/*!40000 ALTER TABLE `clone_ninja` DISABLE KEYS */;
INSERT INTO `clone_ninja` VALUES (-10001057,'aaaaaaaa',4,10,80500,0,0,0,15,5,5,5,0,0,'[{\"id\":0,\"point\":0}]','[-1,-1,-1]','[-1,-1,-1,-1,-1]',-1,'[{\"isLock\":true,\"sale\":0,\"quantity\":1,\"upgrade\":0,\"index\":1,\"id\":194,\"sys\":0,\"isExpires\":false,\"option\":[]}]','[]','[]','[8,8,10,10,1,1]',-1,'[-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]'),(-10001056,'cakakaaa',4,10,80500,0,0,0,15,5,5,5,0,0,'[{\"id\":0,\"point\":0}]','[-1,-1,-1]','[-1,-1,-1,-1,-1]',-1,'[{\"isLock\":true,\"sale\":0,\"quantity\":1,\"upgrade\":0,\"index\":1,\"id\":194,\"sys\":0,\"isExpires\":false,\"option\":[]}]','[]','[]','[8,8,10,10,1,1]',-1,'[-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]'),(-10001055,'acascsac',4,10,80500,0,0,0,15,5,5,5,0,0,'[{\"id\":0,\"point\":0}]','[-1,-1,-1]','[-1,-1,-1,-1,-1]',0,'[{\"isLock\":true,\"sale\":0,\"quantity\":1,\"upgrade\":0,\"index\":1,\"id\":194,\"sys\":0,\"isExpires\":false,\"option\":[]}]','[]','[]','[8,8,10,10,1,1]',-1,'[-1,-1,-1,-1,-1,-1,-1,-1,-1,-1]');
/*!40000 ALTER TABLE `clone_ninja` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-06 13:26:09
