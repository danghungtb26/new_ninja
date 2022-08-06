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
-- Table structure for table `npc`
--

DROP TABLE IF EXISTS `npc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `npc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `headId` int NOT NULL,
  `bodyId` int NOT NULL,
  `legId` int NOT NULL,
  `menu` varchar(500) NOT NULL DEFAULT '[]',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `npc`
--

LOCK TABLES `npc` WRITE;
/*!40000 ALTER TABLE `npc` DISABLE KEYS */;
INSERT INTO `npc` VALUES (0,'Kanata',56,57,58,'[[\"Binh khí\"],[\"Gia tộc\",\"Thành lập\",\"Lãnh Địa Gia Tộc\",\"Đổi túi quà\",\"Hướng dẫn\"],[\"Hang động sau trường\",\"Nhận thưởng\",\"Cấp 35\",\"Cấp 45\",\"Cấp 55\",\"Cấp 65\",\"Cấp 75\",\"Cấp 95\"],[\"Lôi đài\",\"Thách đấu\",\"Xem thi đấu\",\"Kết quả\",\"Ninja tài năng\",\"Xếp hạng tài năng\"],[\"Nói chuyện\"]]'),(1,'Furoya',77,78,79,'[[\"Y phục\",\"Nón\",\"Áo\",\"Găng tay\",\"Quần\",\"Giày\"],[\"Nói chuyện\"]]'),(2,'Ameji',47,48,49,'[[\"Trang sức\",\"Liên\",\"Nhẫn\",\"Ngọc bội\",\"Phù\"],[\"Nhiệm vụ danh vọng\",\"Nhận\",\"Trả\",\"Huỷ\",\"Nhận Geningan\",\"Nâng cấp\",\"Nâng cấp vip\",\"Hướng dẫn\"],[\"Nói chuyện\"]]'),(3,'Kiriko',74,75,76,'[[\"Dược phẩm (Yên)\"],[\"Dược phẩm (Xu)\"],[\"Nói chuyện\"]]'),(4,'Tabemono',50,51,52,'[[\"Thức ăn (Yên)\"],[\"Thức ăn (Xu)\"],[\"Nói chuyện\"],[\"Thiên Địa bảng\",\"Đăng ký\",\" Chinh phục\",\"Thiên bảng\",\"Địa bảng\",\"Nhận thưởng\",\"Hướng dẫn\"]]'),(5,'Kamakura',80,81,82,'[[\"Mở rương\",\"Rương đồ\",\"Bộ sưu tập\",\"Cải trang\",\"Tháo cải trang\"],[\"Lưu tọa độ\"],[\"Vùng Đất Ma Quỷ\",\"Đi tới\",\"Hướng dẫn\"],[\"Nói chuyện\"]]'),(6,'Kenshinto',68,69,70,'[[\"Nâng cấp\",\"Thường\",\"Cẩn thận\",\"Hướng dẫn\"],[\"Luyện đá\",\"Yên\",\"Xu\"],[\"Tách\"],[\"Chuyển hóa\"],[\"Luyện ngọc\"],[\"Khảm\"],[\"Gọt Ngọc\"],[\"Tháo Ngọc\"],[\"Nói chuyện\"]]'),(7,'Umayaki',62,63,64,'[[\"Nói chuyện\"],[\"Làng Kojin\"],[\"Làng Sanzu\"],[\"Làng Tone\"],[\"Làng chài\"],[\"Làng Chakumi\"],[\"Làng Echigo\"],[\"Làng Oshin\"]]'),(8,'Umayaki',62,63,64,'[[\"Trường Hirosaki\"],[\"Trường Haruna\"],[\"Trường Ookaza\"],[\"Nói chuyện\"]]'),(9,'Cô Toyotomi',44,45,46,'[[\"Bảng xếp hạng\",\"Top đại gia\",\"Top cao thủ\",\"Top Gia Tộc\",\"Top hang động\"],[\"Nhập học\",\"Ninja Kiếm\",\"Ninja Phi Tiêu\"],[\"Tẩy điểm\",\"Tiềm năng\",\"Kỹ năng\"],[\"Nói chuyện\"],[\"Giao chiến\",\"Tham gia\",\"Thành tích\",\"Hướng dẫn\"]]'),(10,'Thầy Ookamesama',53,54,55,'[[\"Bảng xếp hạng\",\"Top đại gia\",\"Top cao thủ\",\"Top Gia Tộc\",\"Top hang động\"],[\"Nhập học\",\"Ninja Kunai\",\"Ninja Cung\"],[\"Tẩy điểm\",\"Tiềm năng\",\"Kỹ năng\"],[\"Nói chuyện\"],[\"Giao chiến\",\"Tham gia\",\"Thành tích\",\"Hướng dẫn\"]]'),(11,'Thầy Kazeto',65,66,67,'[[\"Bảng xếp hạng\",\"Top đại gia\",\"Top cao thủ\",\"Top Gia Tộc\",\"Top hang động\"],[\"Nhập học\",\"Ninja Đao\",\"Ninja Quạt\"],[\"Tẩy điểm\",\"Tiềm năng\",\"Kỹ năng\"],[\"Nói chuyện\"],[\"Giao chiến\",\"Tham gia\",\"Thành tích\",\"Hướng dẫn\"]]'),(12,'Tajima',71,72,73,'[[\"Nói chuyện\"],[\"Hủy vật phẩm và nhiệm vụ\"],[\"Thứ thân\"],[\"Chủ thân\"],[\"Nhận quà top event\"],[\"Cài đặt mời\"]]'),(13,'Khu vực',0,0,0,'[]'),(14,'Hashimoto',59,60,61,'[]'),(15,'Fujiwara',89,90,91,'[]'),(16,'Nao',86,87,88,'[]'),(17,'Jaian',96,94,95,'[]'),(18,'Bà Rei',83,84,85,'[[\"Nói chuyện\"]]'),(19,'Kirin',97,98,99,'[[\"Nói chuyện\"]]'),(20,'Soba',114,115,116,'[[\"Nói chuyện\"]]'),(21,'Sunoo',117,118,119,'[[\"Nói chuyện\"]]'),(22,'Guriin',120,121,122,'[[\"Nói chuyện\"],[\"Ninja đệ nhất\",\"Tham gia\"],[\"Bảng xếp hạng\",\"Kiếm\",\"Tiêu\",\"Kunai\",\"Cung\",\"Đao\",\"Quạt\"]]'),(23,'Matsurugi',100,101,102,'[[\"Nói chuyện\"]]'),(24,'Okanechan',103,104,105,'[[\"Nạp tiền\"],[\"Nhận thưởng thăng cấp\",\"Cấp 10\",\"Cấp 20\",\"Cấp 30\",\"Cấp40\",\"Cấp 50\",\"Cấp 70\",\"Cấp 90\",\"Cấp 110\",\"Cấp 130\"],[\"ĐỔI HÀNG\",\"Đổi 100 lượng lấy 20k xu\",\"Đổi 1000 lượng lấy 200k xu\",\"Đổi 1000 lượng lấy 5m yên\"]]'),(25,'Rikudou',106,107,108,'[[\"Nói chuyện\"],[\"NV mỗi ngày\",\"Nhận\",\"Hủy\",\"Hoàn thành\",\"Đi làm NV\"],[\"NV Truy bắt Tà Thú\",\"Nhận\",\"Hủy\",\"Hoàn Thành\"],[\"Chiến trường\",\"Bạch Giả\",\"Hắc Giả\",\"Tổng kết\",\"Hướng dẫn\"],[\"Thất thú ải\"]]'),(26,'Goosho',109,110,111,'[[\"Cửa hàng\"],[\"Quầy sách\"],[\"Thời trang\"],[\"Gia tộc\"]]'),(27,'Trụ cơ quan',0,0,0,'[[\"Cắm chìa khóa\"]]'),(28,'Shinwa',128,129,130,'[[\"Gian hàng\",\"Đá\",\"Nón\",\"Vũ khí\",\"Áo\",\"Dây chuyền\",\"Găng tay\",\"Nhẫn\",\"Quần\",\"Ngọc bội\",\"Giày\",\"Bùa\",\"Linh tinh\"],[\"Bán vật phẩm\"],[\"Nhận lại vật phẩm\"]]'),(29,'Chị Hằng',131,132,133,'[[\"Làm bánh\",\"Bánh thập cẩm\",\"Bánh dẻo\",\"Bánh đậu xanh\",\"Bánh pía\"],[\"Đóng hộp\",\"Hộp bánh thường\",\"Hộp bánh thượng hạng\"],[\"Hướng dẫn\",\"Cách làm bánh\",\"Tìm Thần Thố\",\"Tìm lồng đèn\"],[\"Nói chuyện\"]]'),(30,'Rakkii',134,135,136,'[[\"Lật hình\"],[\"Mã quà tặng\"],[\"Vòng quay vip\",\"Thông tin\",\"Tham gia\",\"Luật chơi\"],[\"Vòng quay thường\",\"Thông tin\",\"Tham gia\",\"Luật chơi\"]]'),(31,'Lồng đèn',0,0,0,'[[\"Thắp lồng đèn\"]]'),(32,'Kagai',143,144,145,'[[\"Chiến trường kẹo\",\"Tham gia\",\"Hướng dẫn\"],[\"Gia tộc chiến\",\"Thách Đấu\"],[\"Liên Server\",\"Hướng dẫn\"],[\"Đổi điểm hoạt động\",\"Bí kíp 3 ngày\",\"Bí kíp 7 ngày\",\"Bí kíp 15 ngày\"],[\"Tinh luyện\",\"Luyện tinh thạch\",\"Tinh luyện vật phẩm\",\"Dịch chuyển trang bị\",\"Hướng dẫn\"]]'),(33,'Tiên nữ',131,132,133,'[]'),(34,'Cây thông',249,250,251,'[[\"Nhận quà\"],[\"Trang trí\"],[\"Hướng dẫn\"]]'),(35,'Ông già Noel',168,169,170,'[[\"Đổi mặt nạ\"],[\"Hướng dẫn\"]]'),(36,'Vua Hùng',191,192,193,'[[\"Đổi coin sang lượng\"],[\"Lượng sang coin\"],[\"Nói chuyện\"]]'),(37,'Kanata',56,57,58,'[[\"Rời khỏi nơi này\"],[\"Đặt cược\"],[\"Nói chuyện\"]]'),(38,'ADMIN',159,197,198,'[[\"Điểm danh hàng ngày\"],[\"Nhận quà tân thủ\"],[\"Bật/Tắt nhận EXP\"],[\"Hướng dẫn\"]]'),(39,'Rikudou',106,107,108,'[[\"Rời khỏi nơi này\"],[\"Thông tin\"]]'),(40,'Kagai',143,144,145,'[]'),(43,'Cây đào',255,256,257,'[[\"Hái lộc\"],[\"Hướng dẫn\"]]'),(56,'BXH',131,132,133,'[[\"BXH Yên\"],[\"BXH lever\"],[\"BXH gia tộc\"],[\"BXH hang động\"],[\"BXH tiêu lượng\"],[\"BXH chiến lực\"],[\"BXH tiềm năng\"],[\"BXH chuyển sinh\"],[\"BXH nạp\"],[\"BXH câu cá\"],[\"BXH Bợm nhậu\"],[\"BXH Cắn Đan\"],[\"BXH diệt boss ngày\"]]'),(59,'đổi mk khẩu',159,197,198,'[[\"đổi mật khẩu\"]]');
/*!40000 ALTER TABLE `npc` ENABLE KEYS */;
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
