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
-- Table structure for table `effect`
--

DROP TABLE IF EXISTS `effect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `effect` (
  `id` int NOT NULL,
  `type` smallint NOT NULL,
  `name` varchar(500) NOT NULL,
  `iconId` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `effect`
--

LOCK TABLES `effect` WRITE;
/*!40000 ALTER TABLE `effect` DISABLE KEYS */;
INSERT INTO `effect` VALUES (0,0,'Hiệu quả thức ăn Cơm nắm',953),(1,0,'Hiệu quả thức ăn Tempura',954),(2,0,'Hiệu quả thức ăn Katsudon',955),(3,0,'Hiệu quả thức ăn Sashimi',956),(4,0,'Hiệu quả thức ăn Gà Quay',957),(5,1,'Hiệu ứng bỏng',1120),(6,2,'Hiệu ứng đóng băng',1006),(7,3,'Hiệu ứng choáng',1009),(8,4,'Mỗi nữa giây phục hồi HP',617),(9,5,'Lửa vô hình',622),(10,6,'Hiệu ứng Mana Shield',614),(11,7,'Hiệu ứng tăng né tránh',619),(12,8,'Hiệu ứng tăng kinh nghiệm',592),(13,9,'Tăng tỉ lệ Hp từ đồng đội',592),(14,10,'Trạng thái phi chiến đấu',1008),(15,11,'Trạng thái tàng hình',603),(16,12,'Trạng thái ẩn thân',623),(17,13,'Giảm trừ máu và tăng tấn công',615),(18,14,'Trạng thái bị trói',607),(19,15,'Tăng kháng cho mình và đồng đội',618),(20,16,'Giảm trừ thời gian cho mình và đồng đội',594),(21,17,'Hiệu ứng bom máu',1200),(22,18,'Hiệu ứng nhân 2 kinh nghiệm',1004),(23,19,'Hiệu ứng bí dược',1225),(24,20,'Hiệu ứng tăng chính xác',1245),(25,21,'Hiệu ứng tăng sức mạnh',1248),(26,22,'Hiệu ứng tăng kháng',1247),(27,23,'Hiệu ứng tăng máu',1246),(28,0,'Hiệu ứng thức ăn 5x',1249),(29,24,'Hiệu ứng sinh mệnh đan',1246),(30,0,'Hiệu ứng thức ăn 6x',1606),(31,0,'Hiệu ứng thức ăn 7x',1607),(32,18,'Hiệu ứng nhân 3 kinh nghiệm',2203),(33,18,'Hiệu ứng nhân 4 kinh nghiệm',2204),(34,25,'Thí luyện',2260),(35,0,'Hiệu ứng thức ăn 9x',2263),(36,0,'Hiệu ứng lồng đèn cá chép, nửa giây hồi phục 300 hp mp',2402),(37,25,'Hiệu ứng lồng đèn ngôi sao, miễn giảm sát thương 50%',2403),(38,25,'Hiệu ứng lồng đèn tròn, thêm 30% kinh nghiệm khi đánh quái',2404),(39,25,'Hiệu ứng lồng đèn ông trăng, 20% bỏ qua kháng của đối phương',2405),(40,25,'Thiên nhãn phù',2092),(41,25,'Khai nhãn phù',2093),(42,0,'Hiệu ứng pet Bí rễ hành, nửa giây hồi phục 400 hp mp',3018),(43,0,'Hiệu ứng đình chiến',1008);
/*!40000 ALTER TABLE `effect` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-06 13:26:12
