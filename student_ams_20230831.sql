-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: student_ams
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` varchar(20) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin','123456');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `semester` varchar(20) NOT NULL,
  `time1` int NOT NULL,
  `time2` int NOT NULL,
  `credit` varchar(11) NOT NULL,
  `belong` varchar(100) NOT NULL,
  `place` varchar(30) NOT NULL,
  `amount` int NOT NULL,
  `detail` varchar(200) NOT NULL,
  `selected` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('123ijk','遊戲程式設計','四',1,4,'3','林平之','資工館222',30,'',1),('F233967','SQL資料庫設計','二',2,3,'2','公孫光','資工館510',30,'',1),('kk989','java','一',1,4,'3','呂騰空','資工館',1,'w',0),('LOP2','資料結構','三',3,4,'3','陸仲遠','資工館401',30,'學習',1),('MX5566','基本電學','六',4,4,'3','凌天崖','電子館',12,'學習基本電學',0),('S123','電子電路','四',1,2,'2','燕南天','電子館',50,'hahaha',0),('S1234','LinFeng','一',1,2,'3','t t','電子館',45,'w',0),('S456','計算機概論','六',5,8,'1','胡車兒','電子館',30,'ww',0),('S789','python程式設計','五',2,4,'3','汪遠圖','資工館F300',50,'學習python',0),('TTBN2','大秦帝國的興衰','三',1,1,'1','杜天籌','教學樓301',50,'歷史',0),('Y76321','數位邏輯設計','一',5,8,'3','李繼遷','電子館404',45,'',1);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(20) NOT NULL,
  `sex` varchar(2) NOT NULL,
  `year` varchar(4) NOT NULL,
  `major` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Student_UK1` (`email`),
  UNIQUE KEY `Student_UK2` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('1855914125','ty35ae8@gmail.com','林仕風','男','一','電子工程系'),('4502369992','ty35ae87@gmail.com','江小魚','男','一','資訊工程系');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study`
--

DROP TABLE IF EXISTS `study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study` (
  `id` int NOT NULL AUTO_INCREMENT,
  `s_id` varchar(20) NOT NULL,
  `s_name` varchar(20) NOT NULL,
  `s_major` varchar(100) NOT NULL,
  `c_id` varchar(20) NOT NULL,
  `c_name` varchar(100) NOT NULL,
  `c_belong` varchar(100) NOT NULL,
  `c_credit` varchar(11) NOT NULL,
  `c_semester` varchar(20) NOT NULL,
  `time1` int NOT NULL,
  `time2` int NOT NULL,
  `eventId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
INSERT INTO `study` VALUES (20,'1855914125','林仕風','電子工程系','F233967','SQL資料庫設計','公孫光','2','二',2,3,'ro78vplu4g119thjv1319b43m0'),(23,'1855914125','林仕風','電子工程系','kk989','java','阿古打','3','一',1,4,'bd29dk05evmag6fn5ln88abhk4'),(26,'1855914125','林仕風','電子工程系','Y76321','數位邏輯設計','李繼遷','3','一',5,8,'3urr8aviefigkvnsgm00ls720c'),(27,'4502369992','江小魚','資訊工程系','123ijk','遊戲程式設計','林平之','3','四',1,4,'hd6p0rl0q16rcjg1di05shimbc');
/*!40000 ALTER TABLE `study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userconnection`
--

DROP TABLE IF EXISTS `userconnection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userconnection` (
  `USERID` varchar(255) NOT NULL,
  `PROVIDERID` varchar(255) NOT NULL,
  `PROVIDERUSERID` varchar(255) NOT NULL,
  `RANK` int NOT NULL,
  `DISPLAYNAME` varchar(255) DEFAULT NULL,
  `PROFILEURL` varchar(512) DEFAULT NULL,
  `IMAGEURL` varchar(512) DEFAULT NULL,
  `ACCESSTOKEN` varchar(255) NOT NULL,
  `SECRET` varchar(255) DEFAULT NULL,
  `REFRESHTOKEN` varchar(255) DEFAULT NULL,
  `EXPIRETIME` bigint DEFAULT NULL,
  PRIMARY KEY (`USERID`,`PROVIDERID`,`PROVIDERUSERID`),
  UNIQUE KEY `USERCONNECTIONRANK` (`USERID`,`PROVIDERID`,`RANK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userconnection`
--

LOCK TABLES `userconnection` WRITE;
/*!40000 ALTER TABLE `userconnection` DISABLE KEYS */;
/*!40000 ALTER TABLE `userconnection` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-01 15:25:45
