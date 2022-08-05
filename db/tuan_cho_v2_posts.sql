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
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `image` text,
  `title` varchar(500) DEFAULT NULL,
  `slug` longtext,
  `short_content` text,
  `content` longtext,
  `view` int DEFAULT '0',
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,1,NULL,'Hướng dẫn nạp tiền','huong-dan-nap-tien',NULL,'<p>&nbsp;</p>\n\n<p>Hiện tại server nhận giao dịch qua phương thức MOMO hoặc thẻ điện thoại<br />\nC&aacute;c bạn c&oacute; thể truy cập v&agrave; nạp tiền tự động&nbsp; MOMO&nbsp;&nbsp;<a href=\"https://nsohoiuc.site/chuc-nang/nap-tien\">TẠI Đ&Acirc;Y</a>&nbsp; - Thẻ điện thoại&nbsp;<a href=\"https://nsohoiuc.site/chuc-nang/nap-the-dien-thoai\">TẠI Đ&Acirc;Y</a></p>\n\n<p>-------------------------------------------------------------------------------------------------------------------------------------</p>\n\n<p><a href=\"https://nsohoiuc.site/chuc-nang/nap-tien\">NẠP TỰ ĐỘNG MOMO</a></p>\n\n<p><span style=\"color:#e74c3c\">Lưu &yacute; khi nạp tiền qua momo</span>:</p>\n\n<p>- Thời gian xử l&yacute; từ 1-2 ph&uacute;t</p>\n\n<p><em>(*) Cần ghi đ&uacute;ng nội dung chuyển khoản (to&agrave;n bộ l&agrave; chữ viết thường)</em></p>\n\n<p><em>(**) Kiểm tra số điện thoại nhận tiền v&agrave; chủ t&agrave;i khoản</em></p>\n\n<p><em>(***) C&aacute;c trường hợp chuyển khoản sai ch&uacute;ng t&ocirc;i sẽ kh&ocirc;ng chịu tr&aacute;ch nhiệm v&agrave; kh&ocirc;ng bồi thường.</em></p>\n\n<p>&nbsp;</p>\n\n<p>BẢNG GI&Aacute; QUY ĐỔI COIN</p>\n\n<p><img alt=\"\" src=\"https://nsohoiuc.site/userfiles/files/banggia.png\" style=\"height:313px; width:426px\" /></p>\n\n<p>-------------------------------------------------------------------------------------------------------------------------------------</p>\n\n<p><a href=\"https://nsohoiuc.site/chuc-nang/nap-the-dien-thoai\">NẠP THẺ C&Agrave;O</a></p>\n\n<p><span style=\"color:#e74c3c\">Lưu &yacute; khi nạp tiền thẻ c&agrave;o</span>:</p>\n\n<p>- Thời gian xử l&yacute; từ 2-5 ph&uacute;t</p>\n\n<p>(*) Chọn đ&uacute;ng gi&aacute; trị thẻ, đ&uacute;ng số seri, m&atilde; thẻ</p>\n\n<p>(**) Những thẻ bị điền sai th&ocirc;ng tin ch&uacute;ng t&ocirc;i sẽ kh&ocirc;ng chịu tr&aacute;ch nhiệm v&agrave; kh&ocirc;ng bồi thường.</p>\n\n<p>&nbsp;</p>\n\n<p>BẢNG GI&Aacute; CHIẾT KHẨU</p>\n\n<p><img alt=\"\" src=\"https://nsohoiuc.site/userfiles/files/chietkhau.png\" style=\"height:371px; width:423px\" /></p>\n\n<p>&nbsp;</p>\n\n<p>-------------------------------------------------------------------------------------------------------</p>\n\n<p>Mọi thắc mắc xin li&ecirc;n hệ qua QTV&nbsp;<a href=\"https://www.facebook.com/groups/386056276488774/\">GROUP FACEBOOK</a>&nbsp;hoặc trong&nbsp;<a href=\"https://zalo.me/g/kmaieh025\">BOX ZALO</a></p>\n\n<p>&nbsp;</p>',0,NULL,'2021-12-03 15:37:37'),(7,1,'upload/file/oHbrYJjPty_Small30035.png','Danh Sách GiftCode Còn Sử Dụng Được ( Bấm vào để xem chi tiết )','danh-sach-giftcode-con-su-dung-duoc-bam-vao-de-xem-chi-tiet','Danh Sách GiftCode Còn Sử Dụng Được','<p><span style=\"color:#000000\"><span style=\"font-size:16px\">Danh S&aacute;ch GiftCode C&ograve;n Sử Dụng Được:</span></span></p>\r\n\r\n<p><span style=\"color:#000000\"><strong><span style=\"font-size:16px\">baotri3<br />\r\nopen<br />\r\nquatanthu<br />\r\n\r\n\r\n<p><span style=\"color:#000000\"><strong><span style=\"font-size:16px\">Nhập code tại npc Rakki Trường Okaza</span></strong></span></p>',608,'2021-11-01 10:31:03','2022-05-11 10:24:55'),(8,4565,'upload/file/ybKmo4vFvy_Small3019.png','tuần này đua tốp gì (bấm vào để xem chi tiết)','tuan-nay-dua-top-gi-bam-vao-de-xem-chi-tiet','đua tôp','<p><strong>-&nbsp; &nbsp;TOP NẠP&nbsp;</strong></p>\r\n\r\n<p><strong>top 1 : qu&agrave; g&igrave; con l&acirc;u mới n&oacute;i</strong></p>\r\n\r\n<p><strong>top 2 :qu&agrave; g&igrave; con l&acirc;u mới n&oacute;i</strong></p>\r\n\r\n<p><strong>top 3 :qu&agrave; g&igrave; con l&acirc;u mới n&oacute;i</strong></p>\r\n\r\n<p><strong>-&nbsp; &nbsp;TOP CẮN ĐAN</strong></p>\r\n\r\n<p><strong>top 1: 200k coin</strong></p>\r\n\r\n<p><strong>top 2 :150k coin</strong></p>\r\n\r\n<p><strong>top 3: 100k coin</strong></p>\r\n\r\n<p><strong><em>lưu &yacute; top nạp c&oacute; thể tăng phần qu&agrave; dựa tr&ecirc;n tổng nạp của mn&nbsp;</em></strong></p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>',0,'2022-05-12 05:53:21','2022-05-12 06:03:06'),(9,4565,'upload/file/KSFa38Yejv_Small35.png','cách thức nhận coin miễn phí (bấm vào để xem chi tiết)','cach-thuc-nhan-coin-mien-phi-bam-vao-de-xem-chi-tiet','free coin','<p><strong>c&aacute;ch 1 : nhớ đọc c&aacute;ch 2 trc khi đọc c&aacute;ch 1&nbsp;</strong></p>\r\n\r\n<p><strong>c&aacute;ch 2 : nhớ đọc c&aacute;ch 3 rồi h&atilde;y đọc c&aacute;ch 2</strong></p>\r\n\r\n<p><strong>c&aacute;ch 3 : đọc c&aacute;ch 4 đi nha</strong></p>\r\n\r\n<p><strong>c&aacute;ch 4 : ad mu&ocirc;n đời bịp :))</strong></p>',0,'2022-05-12 05:59:24','2022-05-12 06:00:57');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-05 16:15:44
