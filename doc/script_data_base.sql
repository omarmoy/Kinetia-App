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
  `price` decimal(38,2) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `vacancies` int NOT NULL,
  `user_id` bigint DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq6cjukylkgxdjkm9npk9va2f2` (`user_id`),
  CONSTRAINT `FKq6cjukylkgxdjkm9npk9va2f2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities`
--

LOCK TABLES `activities` WRITE;
/*!40000 ALTER TABLE `activities` DISABLE KEYS */;
INSERT INTO `activities` VALUES (24,'AVENTURA','2024-05-21 17:32:23.346000','2024-12-12 16:00:00.000000','Vente a caminar por el desierto mientras te hidratados cada 5 minutos por tu bien. ',_binary '','Taberna, Malaga',20.00,'Paseo Caluroso',30,35,'painting'),(25,'ARTE','2024-05-21 17:34:50.145000','2024-12-01 19:30:00.000000','Entona tus canciones favoritas mientras te hacen los coros nuestros loros. Te lo pasarás genial!! ',_binary '\0','Sevilla',60.00,'Canto con loros',10,35,'crafts'),(26,'OFERTAS','2024-05-21 17:51:07.611000','2024-11-02 22:00:00.000000','Descubre los mayores y mejor guardados secretos de Sevilla en este freetour por sus callejas del casco antiguo. ',_binary '\0','Sevilla centro',0.00,'Sevilla secreta',30,36,'music'),(27,'AVENTURA','2024-06-01 09:41:32.552824','2024-12-12 03:00:00.000000','Descubre Sevilla de una manra nica y espectacular con nuestros paseos en globo aerostatico. Sumergete en una experienca inolvidagle mientras flotas suavemente sobre la hermosa ciudad andaluza, disfrutando de vistas panormicas inigualables. \n\nPara mas informacion contacta con nosotros.',_binary '','Sevilla',20.00,'Paseo en globo',10,37,'drawing'),(28,'CURSOS_Y_TALLERES','2024-06-01 09:45:49.153368','2024-11-30 23:00:00.000000','Te apasiona la fotografia y quieres llevar tus habilidades al siguiente nivel?\n\nUnete a nuestro exclusivo taller de fotografia nocturan y aprende a capturar la belleza de la noche con tecnicas profesionales. este taller esta diseado tanta para princpiantes como para fotrografos con experiencia que deseen explorar el arte de la fotografia nocturna		',_binary '','Sevilla',50.00,'Fotografia Nocturna',30,37,'photography'),(29,'EXPERIENCIAS','2024-06-01 09:49:38.671694','2024-09-23 02:00:00.000000','Te invitamos a participar en nuestras sesiones de ajedres callejero en sevilla. Vive la emocion del juego e un ambiente dinamico y social, mientras disfrutas de la fescura del aire libre y las compania de otros entusiastas de este amado juego.',_binary '','Sevilla',0.00,'Ajedrez Callegero',50,37,'gaming'),(30,'AVENTURA','2024-06-01 09:59:20.802808','2024-12-05 06:30:00.000000','Salta al vacio en el puete Triana y date un chapuzon en el Guadalquivir, amarrado a una cuerda elastica que te hara poner los pies en el suelo, eso si, empapado en agua.\n\nTe lo pasaras genial!!!',_binary '\0','Sevilla',35.00,'Puenting Triana',33,36,'business'),(31,'BARES','2024-06-01 10:05:57.434011','2024-12-05 00:30:00.000000','Aprende apintar con cuadros de Van Gogh mientras te emborrachamos con vino barato.',_binary '\0','Sevilla',51.00,'Winegogh',99,36,'tech');
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertisements`
--

LOCK TABLES `advertisements` WRITE;
/*!40000 ALTER TABLE `advertisements` DISABLE KEYS */;
INSERT INTO `advertisements` VALUES (18,'2024-05-21 17:43:55.348000','Busco entrenador personal para ponerme fuerte los fines de semana. ','Sevilla','Entrenador personal',36),(19,'2024-06-01 09:52:27.549902','Busco bar, o bares, donde poder disfrutas de comida vegana por Sevilla, que esten bien de precio y no haya mucho hipster.','Sevilla','Bar Vegano',37),(20,'2024-06-01 09:53:51.572835','Busco un free tour por el centro de sevilla para el fin de semana que viene que paso por alli','Sevilla','Free Tour Sevilla',37),(21,'2024-06-01 10:08:31.597928','Se compran pilas usadas de todo tipo, la mejor opcion para reciclar es que me las des gratis pero si no se las compro','Sevilla','Compro pilas usadas',36),(22,'2024-06-01 10:12:49.600973','Busco empresa de alquiler de barcos para navegar por la costa andaluza y sentirme como Colon','Andalucia','Navegar',35),(23,'2024-06-01 10:17:16.916786','Se busca profesor particular para clases de ingles	','Sevilla','Clases de ingles',38);
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
INSERT INTO `favorite_user_activities` VALUES (36,24),(38,28),(38,30);
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (32,'Hola',_binary '\0',_binary '\0',_binary '\0','2024-05-21 17:45:11.438000',35,36),(33,'Me gustaría saber que tipo de refrigerio dan? ',_binary '\0',_binary '\0',_binary '\0','2024-05-21 17:45:48.133000',35,36),(34,'hola',_binary '\0',_binary '\0',_binary '\0','2024-06-01 10:13:39.701789',37,35),(35,'Hola',_binary '',_binary '\0',_binary '\0','2024-06-01 11:19:54.466473',35,38),(36,'Que tal',_binary '',_binary '\0',_binary '\0','2024-06-01 11:52:55.142364',38,35),(37,'Que necesitas?',_binary '',_binary '\0',_binary '\0','2024-06-01 11:57:15.791160',38,35);
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
INSERT INTO `reservations` VALUES (24,36),(24,38),(27,38),(31,38);
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
  `role_type` enum('ADMIN','PROVIDER','CONSUMER') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ax6cm6ryoqrak90f3eu36m5c2` (`role_type`)
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
  `address` varchar(255) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `cif` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `second_surname` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','PROVIDER','CONSUMER') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FKaydmdn6eup19dva9d41jm327` (`role`),
  CONSTRAINT `FKaydmdn6eup19dva9d41jm327` FOREIGN KEY (`role`) REFERENCES `roles` (`role_type`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (35,'C/ Cuarto 4','1988-02-04','A1111','MiEmpresa','oscar@correo.es','Oscar','oscar','crash','Moya','Marquez','PROVIDER'),(36,'c/ Melancolia 7, Sevilla','1988-02-04','0001','PabloTech','pablo@correo.es','Pablo','pablo','cortex','Segundo','Rico','PROVIDER'),(37,'C/ Dos 7',NULL,'E00000','EmpresaXYZ','empresa@correo.es','Juan','empresa','crunch','','Gimenez','PROVIDER'),(38,'C/ Teres 7','1999-01-01','','','consumidor@correo.es','Consumidor','consumidor','coco','Sumidor','Con','CONSUMER');
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

-- Dump completed on 2024-06-02 14:38:54
