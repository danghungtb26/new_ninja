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
-- Table structure for table `optionitem`
--

DROP TABLE IF EXISTS `optionitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `optionitem` (
  `id` int NOT NULL,
  `name` varchar(500) NOT NULL,
  `type` smallint NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `optionitem`
--

LOCK TABLES `optionitem` WRITE;
/*!40000 ALTER TABLE `optionitem` DISABLE KEYS */;
INSERT INTO `optionitem` VALUES (0,'Tấn công ngoại: +#',0),(1,'Tấn công nội: +#',0),(2,'Kháng hỏa:  +#',0),(3,'Kháng băng: +#',0),(4,'Kháng phong:  +#',0),(5,'Né đòn: +#',0),(6,'HP tối đa: +#',1),(7,'MP tối đa: +#',1),(8,'Vật công ngoại: +#%',1),(9,'Vật công nội: +#%',1),(10,'Chính xác: +#',1),(11,'Kháng hỏa: +#',2),(12,'Kháng băng: +#',2),(13,'Kháng phong: +#',2),(14,'Chí mạng: #',2),(15,'Phản đòn cận chiến: +#',2),(16,'Tốc độ di chuyển: +#%',2),(17,'Né đòn: #',2),(18,'Chính xác: #',2),(19,'MP tối đa: #',2),(20,'Kháng tất cả: #',2),(21,'Hỏa công ngoại: +#',2),(22,'Hỏa công nội: +#',2),(23,'Băng sát ngoại: +#',2),(24,'Băng sát nội: +#',2),(25,'Phong lôi ngoại: +#',2),(26,'Phong lôi nội: +#',2),(27,'(+4) Mỗi 5 giây phục hồi MP: #',3),(28,'(+8) Tỉ lệ MP tối đa: +#%',4),(29,'(+12) MP tối đa: +#',5),(30,'(+4) Mỗi 5 giây phục hồi HP: #',3),(31,'(+8) Tỉ lệ HP tối đa: +#%',4),(32,'(+12) HP tối đa: +#',5),(33,'(+14) Kháng hỏa: +#',6),(34,'(+14) Kháng băng: +#',6),(35,'(+14) Kháng phong: +#',6),(36,'(+14) Kháng tất cả: +#',6),(37,'(+8) Chí mạng: +#',4),(38,'(+12) Tấn công cơ bản của vũ khí: +#',5),(39,'(+14) Tấn công khi đánh chí mạng: +#%',6),(40,'(+16) Giảm trừ thời gian bị bỏng: -#',7),(41,'(+16) Giảm trừ thời gian bị đóng băng: -#',7),(42,'(+16) Giảm trừ thời gian bị choáng: -#',7),(43,'(+16) Thời gian bị bỏng: +#',7),(44,'(+16) Thời gian bị đóng băng: +#',7),(45,'(+16) Thời gian bị choáng: +#',7),(46,'(+16) Chịu sát thương khi bị chí mạng: -#%',7),(47,'Giảm trừ sát thương: +#',0),(48,'(+16) Giảm trừ sát thương bởi hỏa hệ: +#',7),(49,'(+16) Giảm trừ sát thương bởi băng hệ: +#',7),(50,'(+16) Giảm trừ sát thương bởi phong hệ: +#',7),(51,'(+16) Tăng sát thương bởi hỏa hệ: +#',7),(52,'(+16) Tăng sát thương bởi băng hệ: +#',7),(53,'(+16) Tăng sát thương bởi phong hệ: +#',7),(54,'(+16) Sát thương đánh hệ hỏa: +#%',7),(55,'(+16) Sát thương đánh hệ băng: +#%',7),(56,'(+16) Sát thương đánh hệ phong: +#%',7),(57,'+# điểm tiềm năng cho tất cả',1),(58,'Cộng thêm tiềm năng: +#%',0),(59,'Cho phép tăng thêm điểm kỹ năng 6x: +# điểm',0),(60,'(+4) Tỉ lệ MP tối đa: +#%',3),(61,'(+8) Tỉ lệ HP tối đa: +#%',4),(62,'(+12) Né đòn: +#',5),(63,'Giảm sát thương bởi người chơi khác: +#%',1),(64,'Chưa xác định',0),(65,'Kinh nghiệm: #/1000',0),(66,'Sinh lực: #/1000',0),(67,'Tấn công khi đánh chí mạng: #%',1),(68,'Né đòn: +#',1),(69,'Chí mạng: +#',1),(70,'Kháng hỏa:  +#',1),(71,'Kháng băng:  +#',1),(72,'Kháng phong:  +#',1),(73,'Tấn công: #',1),(74,'Chịu sát thương cho chủ: +#',1),(75,'Tăng chính xác cho chủ: +#',8),(76,'Tăng tấn công cho chủ: +#',8),(77,'Tăng max HP cho chủ: +#',8),(78,'Tăng né tránh cho chủ: +#',8),(79,'Kháng sát thương chí mạng: #%',8),(80,'Giảm trừ sát thương: #',8),(81,'Kháng tất cả: +#',8),(82,'HP tối đa: #',8),(83,'MP tối đa: +#',8),(84,'Né đòn: +#',8),(85,'Độ tinh luyện: #',8),(86,'Chính xác: +#',8),(87,'Tấn công: +#',8),(88,'Hỏa công: +#',8),(89,'Băng công: +#',8),(90,'Phong lôi: +#',8),(91,'Phản đòn: #',8),(92,'Chí mạng: +#',8),(93,'Tốc độ di chuyển: +#',8),(94,'Tấn công: +#%',8),(95,'Kháng băng:  +#',8),(96,'Kháng hỏa:  +#',8),(97,'Kháng phong:  +#',8),(98,'Miễn giảm sát thương: #%',8),(99,'Mỗi nửa giây hồi phục # Hp và Mp',8),(100,'Tăng #% kinh nghiệm khi đánh quái',8),(101,'#% Bỏ qua kháng đối phương',8),(102,'St lên quái: #',1),(103,'St lên người: #',1),(104,'Exp: #',0),(105,'Sát thương chí mạng: #',1),(106,'Vũ khí',0),(107,'Trang bị',0),(108,'Trang sức',0),(109,'Huyền Tinh Ngọc',0),(110,'Huyết Ngọc',0),(111,'Lam Tinh Ngọc',0),(112,'Lục Ngọc',0),(113,'Sát thương chuẩn: +#',0),(114,'Chí mạng: #',1),(115,'Né đòn: #',1),(116,'Chính xác: #',1),(117,'MP tối đa: #',1),(118,'Kháng tất cả: #',1),(119,'Mỗi 5 giây phục hồi MP: #',1),(120,'Mỗi 5 giây phục hồi HP: #',1),(121,'Kháng sát thương chí mạng: #%',1),(122,'Yên tháo ngọc: #',8),(123,'Giá khảm: #',8),(124,'Giảm trừ sát thương: #',1),(125,'HP tối đa: #',1),(126,'Phản đòn: #',1),(127,'Giảm st hệ hoả: #%',1),(128,'Giảm st hệ băng: #%',1),(129,'Giảm st hệ phong: #%',1),(130,'Kỹ năng Mưa Băng:-Mưa băng lớn gây đóng băng quái, tỉ lệ 30% trong: #giây',0),(131,'Kỹ năng Vụ Nổ Băng Giá:- Bắn chùm lông cực mạnh gây sát thương lên quái : #',0),(132,'Giảm nửa thương -Tỷ lệ xh: #%',0),(133,'Nội phòng: #%',0),(134,'Ngoại phòng: #%',0);
/*!40000 ALTER TABLE `optionitem` ENABLE KEYS */;
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
