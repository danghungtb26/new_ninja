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
-- Table structure for table `optionskill`
--

DROP TABLE IF EXISTS `optionskill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `optionskill` (
  `name` varchar(500) NOT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `optionskill`
--

LOCK TABLES `optionskill` WRITE;
/*!40000 ALTER TABLE `optionskill` DISABLE KEYS */;
INSERT INTO `optionskill` VALUES ('Tấn công ngoại: +#%',0),('Tấn công nội: +#%',1),('Hỏa công: +#',2),('Băng sát: +#',3),('Phong lôi: +#',4),('Hỏa công ngoại: +#',5),('Băng sát ngoại: +#',6),('Phong lôi ngoại: +#',7),('Hỏa công nội: +#',8),('Băng sát nội: +#',9),('Phong lôi nội: +#',10),('Tấn công cơ bản: +#%',11),('Chính xác: +#',12),('Né đòn: +#',13),('Chí mạng: +#',14),('Phản đòn cận chiến: +#',15),('Tốc độ di chuyển: +#',16),('Tỉ lệ HP tối đa: +#%',17),('Tỉ lệ MP tối đa: +#%',18),('Kháng tất cả: +#',19),('Kháng hỏa: +#',20),('Kháng băng: +#',21),('Kháng phong: +#',22),('Kháng phản đòn: +#',23),('Xác xuất gây bỏng: +#% duy trì 2 giây',24),('Xác xuất đóng băng: +#% duy trì 1.5 giây',25),('Xác xuất gây choáng: +#% duy trì 1 giây',26),('Nữa giây phục hồi HP: +# duy trì 5 giây',27),('Hồi phục cho đồng đội, sau khi hồi phục sẽ giúp đồng đội tăng khả năng né tránh: +# duy trì 5 giây',28),('Tăng % kinh nghiệm khi đánh quái: +#%',29),('Khi Mp lớn hơn 10%, dùng MP hút sát thương: +#% duy trì 90 giây',30),('Né tránh hoàn toàn tấn công: +#%',31),('Hạ khắc: +#',32),('Tương khắc: +#',33),('Xác suất gây bỏng: +#% duy trì 4 giây',34),('Xác suất đóng băng: +#% duy trì 3 giây',35),('Xác suất gây choáng: +#% duy trì 2 giây',36),('Giảm thời gian bị bỏng: -0.# giây',37),('Giảm thời gian bị đóng băng: -0.# giây',38),('Giảm thời gian bị choáng: -0.# giây',39),('Giảm 3 giây thời gian bị bỏng: duy trì # giây',40),('Giảm 2 giây thời gian bị đóng băng: duy trì # giây',41),('Giảm 1 giây thời gian bị choáng: duy trì # giây',42),('Nữa giây phục hồi HP cho đồng đội: +# duy trì 5 giây',43),('Tăng tỉ lệ Hp cho đồng đội: +#%',44),('Tăng kháng tất cả cho mình và đồng đội: +# duy trì 90 giây',45),('Tăng tấn công cơ bản cho mình và đồng đội: +#% duy trì 90 giây',46),('Giảm 2 giây thời gian bị bỏng cho đồng đội: duy trì # giây',47),('Vô hiệu hóa tấn công của quái vật: duy trì +# giây',48),('Tạo vật thế thân: duy trì +# giây',49),('Biến quái thành thức ăn: tăng +#HP',50),('Đốt Hp của quái vật dựa trên tấn công hiện tại: +#% duy trì 30 giây',51),('Ẩn thân mỗi nữa giây phục hồi Mp, Hp: +# duy trì 5 giây',52),('Tàng hình: duy trì # giây',53),('Giảm 1 giây thời gian bị đóng băng cho đồng đội: duy trì # giây',54),('Làm cho đối phương không thể di chuyển: duy trì +# giây',55),('Mỗi nữa giây giảm trừ 3% Hp đồng thời tăng tấn công cơ bản: +#% duy trì 5 giây',56),('Giảm 0.5 giây thời gian bị choáng cho đồng đội: duy trì # giây',57),('Hỗ trợ tấn công chiêu cấp 30: +#%',58),('Hỗ trợ tấn công chiêu cấp 40: +#%',59),('Hỗ trợ tấn công chiêu cấp 50: +#%',60),('Tăng #% tấn công của nhát đánh đầu tiên của chiêu cấp 35',61),('Tăng # giây giữ chặt đối phương của chiêu cấp 35',62),('Phục hồi # Hp mỗi khi bị bỏng',63),('Giảm trừ tất cả các sát thương: duy trì # giây',64),('Tăng tấn công khi đánh chí mạng: +#%',65),('Tăng #% hiệu của của chiêu cấp 15, cấp 35, cấp 40',66),('Trừ kháng tất cả: -# của đối phương khi có hiệu ứng băng',67),('Phân thân tồn tại trong: # phút ',68),('Tỉ lệ #% đóng băng mục tiêu khi bị tấn công',69),('Đóng băng mục tiêu trong: # giây',70),('Sức đánh phân thân: #% ',71);
/*!40000 ALTER TABLE `optionskill` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-05 16:15:47
