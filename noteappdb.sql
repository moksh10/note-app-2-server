-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: noteapp
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `note` (
  `note_id` int NOT NULL AUTO_INCREMENT,
  `note_title` varchar(100) DEFAULT '',
  `note_body` varchar(3500) DEFAULT '',
  `user_id` int NOT NULL,
  PRIMARY KEY (`note_id`),
  KEY `note_ibfk_1` (`user_id`),
  CONSTRAINT `note_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `note_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (3,'tobe note3sad','tobe note3',1),(5,'Second note','Second note',5),(6,'Third note','Third note..........',5),(8,'sda','sadasd',5),(9,'Yo OY oy','client.origin',6),(11,'eiyadljnkm,x','adscsd ',6),(13,'guevhbAJLKm:VFGJK','sd',6),(14,'adca','adsfae',6);
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note_user`
--

DROP TABLE IF EXISTS `note_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `note_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(200) NOT NULL,
  `password` varchar(64) NOT NULL,
  `user_name` varchar(200) DEFAULT '',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note_user`
--

LOCK TABLES `note_user` WRITE;
/*!40000 ALTER TABLE `note_user` DISABLE KEYS */;
INSERT INTO `note_user` VALUES (1,'tobeteng@gmail.com','$2a$10$eR.TFY7WkL8uTu8p5pSWSuiAR6hTRLlh5csAiW7gSwFmhOM.5wJX6','Tobe69'),(2,'teng1@gmail.com','$2a$10$JWYFhRsMk/jHelrXbK13nu5LinwRPDX9stOfQeyv6jsZ3q5LivoRO','Teng'),(3,'mokshteng122@gmail.com','$2a$10$O9BwRMdavCs9MGxL5.f1MOW8SDf2e7gmEkcAM5sOcsZT7j1ZlJVGW','Moksh Teng'),(4,'mokshteng6969@gmail.com','$2a$10$kmUxv3LQOMGkbuBTdsrwxefrncfI5W9M5B.sF5/s81pM4bxRYscNG','mokshteng10'),(5,'mokshteng10@gmail.com','$2a$10$0hhBW/tJG20naZxFRJXU4O8HjzkhudpU4Db1QeYkZAPdpBKkqK/tq','Moksh Teng'),(6,'mokshteng@gmail.com','$2a$10$SsEJsoBwDIwPsG3EDmelUultcHOVeY3UFxhUYzoxeMtpJA6JPW2qe','Moksh Teng');
/*!40000 ALTER TABLE `note_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-10 16:10:31
