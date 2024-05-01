CREATE DATABASE  IF NOT EXISTS `kinetia` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `kinetia`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: kinetia
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `activities`
--

DROP TABLE IF EXISTS `activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` enum('AIRE_LIBRE','ARTE','AVENTURA','BARES','CURSOS_Y_TALLERES','DEPORTE','EXPERIENCIAS','GASTRONOMIA','MUSICA','OCIO','OFERTAS','SALUD_Y_BIENESTAR') NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `date` datetime(6) NOT NULL,
  `description` text NOT NULL,
  `featured` bit(1) NOT NULL,
  `location` varchar(255) NOT NULL,
  `price` float DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `vacancies` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq6cjukylkgxdjkm9npk9va2f2` (`user_id`),
  CONSTRAINT `FKq6cjukylkgxdjkm9npk9va2f2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities`
--

LOCK TABLES `activities` WRITE;
/*!40000 ALTER TABLE `activities` DISABLE KEYS */;
INSERT INTO `activities` VALUES (2,'AVENTURA','2024-05-01 07:13:46.995668','2024-04-29 10:00:00.000000','Descripción de la actividad de ejemplo. Un poco más larga. Y más',_binary '\0','Ubicación de la actividad',20.5,'Actividad Modificada2',10,2),(3,'AVENTURA','2024-04-30 20:23:50.750440','2024-04-29 10:00:00.000000','Descripción de la actividad de ejemplo.',_binary '','Ubicación de la actividad',20.5,'Actividad de ejemplo',10,2),(4,'AVENTURA','2024-05-01 06:27:58.371898','2024-04-29 10:00:00.000000','Descripción de la actividad de ejemplo.',_binary '','Ubicación de la actividad',20.5,'Actividad de ejemplo',10,2),(5,'AVENTURA','2024-05-01 06:28:02.761047','2024-04-29 10:00:00.000000','Descripción de la actividad de ejemplo.',_binary '','Ubicación de la actividad',20.5,'Actividad de ejemplo',10,2);
/*!40000 ALTER TABLE `activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertisements`
--

DROP TABLE IF EXISTS `advertisements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `advertisements` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creation_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj69sc4qf7g4p52q8vl0hqvbmp` (`user_id`),
  CONSTRAINT `FKj69sc4qf7g4p52q8vl0hqvbmp` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisements`
--

LOCK TABLES `advertisements` WRITE;
/*!40000 ALTER TABLE `advertisements` DISABLE KEYS */;
INSERT INTO `advertisements` VALUES (5,'2024-05-01 07:50:41.846189','Modificación Descripción del anuncio de ejemplo.','sevilla','Anuncio de ejemplo Modificado',2),(6,'2024-05-01 07:47:51.158729','Descripción del anuncio de ejemplo.','sevilla','Anuncio de ejemplo',2);
/*!40000 ALTER TABLE `advertisements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_user_activities`
--

DROP TABLE IF EXISTS `favorite_user_activities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite_user_activities` (
  `user_id` bigint NOT NULL,
  `activity_id` bigint NOT NULL,
  KEY `FK8h4fd2fx6olw9016ej7crc8q0` (`activity_id`),
  KEY `FK24w2etlt4jx0x5xv3vlfqskxv` (`user_id`),
  CONSTRAINT `FK24w2etlt4jx0x5xv3vlfqskxv` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK8h4fd2fx6olw9016ej7crc8q0` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_user_activities`
--

LOCK TABLES `favorite_user_activities` WRITE;
/*!40000 ALTER TABLE `favorite_user_activities` DISABLE KEYS */;
INSERT INTO `favorite_user_activities` VALUES (1,2),(1,3),(1,4),(1,5);
/*!40000 ALTER TABLE `favorite_user_activities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `is_read` bit(1) NOT NULL,
  `receiver_has_deleted` bit(1) NOT NULL,
  `sender_has_deleted` bit(1) NOT NULL,
  `sent_at` datetime(6) NOT NULL,
  `receiver_id` bigint NOT NULL,
  `sender_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt05r0b6n0iis8u7dfna4xdh73` (`receiver_id`),
  KEY `FK4ui4nnwntodh6wjvck53dbk9m` (`sender_id`),
  CONSTRAINT `FK4ui4nnwntodh6wjvck53dbk9m` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKt05r0b6n0iis8u7dfna4xdh73` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (4,'Esto es un mesaje de prueba',_binary '',_binary '\0',_binary '\0','2024-05-01 10:30:45.000000',2,1),(6,'Esto es un RESPUESTA de prueba',_binary '\0',_binary '\0',_binary '\0','2024-05-01 10:31:45.000000',1,2);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `activity_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`activity_id`,`user_id`),
  KEY `FKb5g9io5h54iwl2inkno50ppln` (`user_id`),
  CONSTRAINT `FKb5g9io5h54iwl2inkno50ppln` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKtn5l4w8sr2h75kainoe3lh2j` FOREIGN KEY (`activity_id`) REFERENCES `activities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (3,1),(4,1);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rol_type` enum('ADMIN','PROVIDER','CONSUMER') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ax6cm6ryoqrak90f3eu36m5c2` (`rol_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'PROVIDER'),(3,'CONSUMER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `adress` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `cif` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `second_surname` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `rol` enum('ADMIN','PROVIDER','CONSUMER') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_m3e35umwg3ht7rfuy308r4b2d` (`cif`),
  KEY `FKaydmdn6eup19dva9d41jm327` (`rol`),
  CONSTRAINT `FKaydmdn6eup19dva9d41jm327` FOREIGN KEY (`rol`) REFERENCES `roles` (`rol_type`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Calle Mayor 123','1990-05-15','','','juan@correo.es','Juan','juan','profile1.jpg','Lopez','Garcia','CONSUMER'),(2,'Avenida Libertad 45','1985-08-20','B12345678','Empresa XYZ','antonia@correo.es','Antonia','antonia','profile2.jpg','Rodriguez','Martinez','PROVIDER'),(5,'Plaza España 8','1978-12-10',NULL,'','carlos@correo.es','Carlos','carlos','profile3.jpg','','Sanchez','ADMIN'),(6,'Calle Real 67','1992-07-25',NULL,'','laura@correo.es','Laura','laura','profile4.jpg','Jimenez','Gomez','CONSUMER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-01 19:38:01
