CREATE DATABASE  IF NOT EXISTS `spring-quiz` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `spring-quiz`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: spring-quiz
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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `id` bigint NOT NULL,
  `answer` int DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `question_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_answer_question1_idx` (`question_id`),
  CONSTRAINT `fk_answer_question1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (10,1,'параллелограмма',4),(11,0,'прямоугольника',4),(12,0,'квадрата',4),(13,0,'трапеции',4),(14,0,'7',5),(15,0,'49',5),(16,1,'14',5),(17,0,'21',5),(18,0,'30',6),(19,0,'45',6),(20,1,'60',6),(21,0,'70',6),(22,0,'230',7),(23,1,'324',7),(24,0,'170',7),(25,0,'160',7),(26,0,'echo(\"Hello World\");',8),(27,1,'System.out.println(\"Hello World\");',8),(28,0,'print (\"Hello World\");',8),(29,0,'Console.WriteLine(\"Hello World\");',8),(30,1,'False',9),(31,0,'True',9),(32,0,'# This is a comment',10),(33,1,'// This is a comment',10),(34,0,'/* This is a comment',10),(35,1,'String',11),(36,0,'myString',11),(37,0,'Txt',11),(38,0,'string',11),(39,1,'int x = 5;',12),(40,0,'float x = 5;',12),(41,0,'x = 5;',12),(42,0,'num x = 5',12),(90,0,'1',89),(91,1,'5',89),(92,0,'10',89),(94,1,'8',93),(95,0,'5',93),(96,0,'10',93),(98,0,'8',97),(99,0,'5',97),(100,1,'10',97),(132,1,'123',131),(150,1,'1234Aa',149),(151,0,'2345',149),(152,0,'234',149),(154,1,'ads',153);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (155);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `quiz_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_quiz1_idx` (`quiz_id`),
  CONSTRAINT `fk_question_quiz1` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (4,'Ромб обладает всеми свойствами...',2),(5,'В квадрате ABCD диагонали пересекаются в точке O. AO=7см чему равна диагональ BD?',2),(6,'Величина одного из углов прямоугольной трапеции равна 120º. Найдите острый угол этой трапеции.',2),(7,'В равнобедренной трапеции диагонали взаимно перпендикулярны, высота трапеции равна 18 см. Найдите площадь трапеции.',2),(8,'What is a correct syntax to output \"Hello World\" in Java?',1),(9,'Java is short for \"JavaScript\".',1),(10,'How do you insert COMMENTS in Java code?',1),(11,'Which data type is used to create a variable that should store text?',1),(12,'How do you create a variable with the numeric value 5?',1),(47,'12',51),(48,'13',51),(49,'14',51),(51,'15',51),(89,'2+3=?',3),(93,'5+3=?',3),(97,'5+5=?',3),(131,'1324',130),(149,'qwqw',134),(153,'asd',134);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quiz`
--

DROP TABLE IF EXISTS `quiz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quiz` (
  `id` bigint NOT NULL,
  `description` text,
  `topic_id` bigint NOT NULL,
  `archived` tinyint NOT NULL DEFAULT '0',
  `create_date` date DEFAULT NULL,
  `difficult` varchar(255) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  `header` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `quiz_header_uindex` (`header`),
  KEY `fk_quiz_topic1_idx` (`topic_id`),
  CONSTRAINT `fk_quiz_topic1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quiz`
--

LOCK TABLES `quiz` WRITE;
/*!40000 ALTER TABLE `quiz` DISABLE KEYS */;
INSERT INTO `quiz` VALUES (1,'Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let application developers write once, run anywhere (WORA), meaning that compiled Java code can run on all platforms that support Java without the need for recompilation. Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but has fewer low-level facilities than either of them. The Java runtime provides dynamic capabilities (such as reflection and runtime code modification) that are typically not available in traditional compiled languages. As of 2019, Java was one of the most popular programming languages in use according to GitHub, particularly for client-server web applications, with a reported 9 million developers.',2,0,'2021-09-01','hard',10,'Java'),(2,'Geometry, the branch of mathematics concerned with the shape of individual objects, spatial relationships among various objects, and the properties of surrounding space. It is one of the oldest branches of mathematics, having arisen in response to such practical problems as those found in surveying, and its name is derived from Greek words meaning “Earth measurement.” Eventually it was realized that geometry need not be limited to the study of flat surfaces (plane geometry) and rigid three-dimensional objects (solid geometry) but that even the most abstract thoughts and images might be represented and developed in geometric terms.\n\n',3,0,'2021-09-02','medium',10,'Geometry'),(3,'Algebra is a branch of mathematics dealing with symbols and the rules for manipulating those symbols. In elementary algebra, those symbols (today written as Latin and Greek letters) represent quantities without fixed values, known as variables. Just as sentences describe relationships between specific words, in algebra, equations describe relationships between variables.',3,0,'2021-09-03','easy',16,'Algebra'),(51,'qwertewerty',1,1,'2021-09-28','easy',214,'adsafsafs'),(130,'                qew',1,1,'2021-10-04','easy',123,'eqwwq'),(134,'       123        12345123465',1,0,'2021-10-04','easy',123,'afsfas');
/*!40000 ALTER TABLE `quiz` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `id` bigint NOT NULL,
  `passed_date` datetime(6) DEFAULT NULL,
  `score` int DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `quiz_id` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_result_user1_idx` (`user_id`),
  KEY `fk_result_quiz1_idx` (`quiz_id`),
  CONSTRAINT `FKpjjrrf0483ih2cvyfmx70a16b` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `result_quiz_id_fk` FOREIGN KEY (`quiz_id`) REFERENCES `quiz` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (57,'2021-09-28 19:00:06.047000',0,20,3),(87,'2021-09-29 23:11:21.120000',0,20,51),(88,'2021-09-30 00:42:38.755000',0,20,3),(101,'2021-09-30 00:43:35.844000',100,20,3),(103,'2021-10-02 01:47:37.885000',0,20,2),(104,'2021-10-02 02:21:28.626000',40,20,1),(105,'2021-10-02 02:31:20.399000',20,20,1),(106,'2021-10-02 02:32:13.227000',20,20,1),(107,'2021-10-02 02:35:23.194000',20,20,1),(108,'2021-10-02 02:46:13.756000',40,20,1),(109,'2021-10-02 02:47:31.236000',40,20,1),(110,'2021-10-02 02:50:55.811000',0,20,1),(111,'2021-10-02 02:51:54.603000',0,20,1),(112,'2021-10-02 03:02:01.241000',0,20,1),(113,'2021-10-02 03:02:18.030000',80,20,1),(133,'2021-10-04 02:16:54.876000',100,20,130);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `topic` (
  `id` bigint NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `archived` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` VALUES (1,'English',0),(2,'Programming',0),(3,'Math',0);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `block` int DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`),
  CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (7,0,'2021-09-05','user5@gmail.com','Sasha','27879e858b39bc13845d30ac6d9e51fe6455f8ba93453d34f8165d4365c885b5dc3c97c00bbd0b86','Kovalchuk',2),(8,0,'2021-09-06','user6@gmail.com','Sasha','27879e858b39bc13845d30ac6d9e51fe6455f8ba93453d34f8165d4365c885b5dc3c97c00bbd0b86','Kovalchuk',2),(9,0,'2021-09-07','user7@gmail.com','Sasha','27879e858b39bc13845d30ac6d9e51fe6455f8ba93453d34f8165d4365c885b5dc3c97c00bbd0b86','Kovalchuk',2),(10,0,'2021-09-08','user8@gmail.com','Sasha','27879e858b39bc13845d30ac6d9e51fe6455f8ba93453d34f8165d4365c885b5dc3c97c00bbd0b86','Kovalchuk',2),(11,0,'2021-09-09','user9@gmail.com','Sasha','27879e858b39bc13845d30ac6d9e51fe6455f8ba93453d34f8165d4365c885b5dc3c97c00bbd0b86','Kovalchuk',2),(12,0,'2021-09-10','user10@gmail.com','Sasha','27879e858b39bc13845d30ac6d9e51fe6455f8ba93453d34f8165d4365c885b5dc3c97c00bbd0b86','Kovalchuk',2),(14,0,'2021-09-12','user12@gmail.com','Sasha','27879e858b39bc13845d30ac6d9e51fe6455f8ba93453d34f8165d4365c885b5dc3c97c00bbd0b86','Kovalchuk',2),(15,0,'2021-09-13','user13@gmail.com','Sasha','27879e858b39bc13845d30ac6d9e51fe6455f8ba93453d34f8165d4365c885b5dc3c97c00bbd0b86','Kovalchuk',2),(16,0,'2021-09-14','user14@gmail.com','Sasha','27879e858b39bc13845d30ac6d9e51fe6455f8ba93453d34f8165d4365c885b5dc3c97c00bbd0b86','Kovalchuk',2),(17,0,'2021-09-15','user15@gmail.com','Sasha','27879e858b39bc13845d30ac6d9e51fe6455f8ba93453d34f8165d4365c885b5dc3c97c00bbd0b86','Kovalchuk',2),(18,0,'2021-09-16','user16@gmail.com','Sasha','27879e858b39bc13845d30ac6d9e51fe6455f8ba93453d34f8165d4365c885b5dc3c97c00bbd0b86','Kovalchuk',2),(19,0,'2021-09-17','user17@gmail.com','Sasha','27879e858b39bc13845d30ac6d9e51fe6455f8ba93453d34f8165d4365c885b5dc3c97c00bbd0b86','Kovalchuk',2),(20,0,'2021-09-18','admin@mail.com','Sasha','27879e858b39bc13845d30ac6d9e51fe6455f8ba93453d34f8165d4365c885b5dc3c97c00bbd0b86','Kovalchuk',1),(21,0,'2021-09-28','owner@mail.com','Sasha','a4dd61f21aaca884a7379453c36c72b2ceb868a2e86e43d35a8a117820f99c70e9bde5128d082c96','Kovalchuk',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-04  4:49:03
