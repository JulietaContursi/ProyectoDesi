CREATE DATABASE  IF NOT EXISTS `tp2_desi_2023` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tp2_desi_2023`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: tp2_desi_2023
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `model_vuelo`
--

DROP TABLE IF EXISTS `model_vuelo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model_vuelo` (
  `id_vuelo` bigint NOT NULL AUTO_INCREMENT,
  `estado` enum('CANCELADO','NORMAL','REPROGRAMADO') DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time(6) DEFAULT NULL,
  `precio_vuelo` double NOT NULL,
  `tipo` enum('INTERNACIONAL','NACIONAL') DEFAULT NULL,
  `avion_id_avion` bigint DEFAULT NULL,
  `ciudad_destino_id_ciudad` bigint DEFAULT NULL,
  `ciudad_origen_id_ciudad` bigint DEFAULT NULL,
  `asientos_de_avion` int NOT NULL,
  PRIMARY KEY (`id_vuelo`),
  KEY `FK8fdyefl8h8vgynfuxlh24q3ge` (`avion_id_avion`),
  KEY `FKcfguuts0at48tsyh0wvan1lyg` (`ciudad_destino_id_ciudad`),
  KEY `FK337r0qg8y88gvnkl44suboswp` (`ciudad_origen_id_ciudad`),
  CONSTRAINT `FK337r0qg8y88gvnkl44suboswp` FOREIGN KEY (`ciudad_origen_id_ciudad`) REFERENCES `model_ciudad` (`id_ciudad`),
  CONSTRAINT `FK8fdyefl8h8vgynfuxlh24q3ge` FOREIGN KEY (`avion_id_avion`) REFERENCES `model_avion` (`id_avion`),
  CONSTRAINT `FKcfguuts0at48tsyh0wvan1lyg` FOREIGN KEY (`ciudad_destino_id_ciudad`) REFERENCES `model_ciudad` (`id_ciudad`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_vuelo`
--

LOCK TABLES `model_vuelo` WRITE;
/*!40000 ALTER TABLE `model_vuelo` DISABLE KEYS */;
INSERT INTO `model_vuelo` VALUES (1,'NORMAL','2023-12-05','00:00:00.000000',100000,'NACIONAL',1,2,1,80),(2,'NORMAL','2023-12-20','00:00:00.000000',34000,'NACIONAL',2,5,3,144),(3,'NORMAL','2023-12-14','01:07:00.000000',232323,'NACIONAL',1,1,1,80),(4,'NORMAL','2023-12-01','12:50:00.000000',456433,'NACIONAL',1,1,1,80),(5,'NORMAL','2023-12-09','12:10:00.000000',300000,'INTERNACIONAL',4,8,1,192),(6,'NORMAL','2023-12-09','08:00:00.000000',50000,'NACIONAL',2,4,1,144),(7,'NORMAL','2023-12-09','15:00:00.000000',65656,'NACIONAL',1,4,1,80),(8,'NORMAL','2023-12-27','14:07:00.000000',60000,'NACIONAL',3,3,6,132),(10,'NORMAL','2023-12-29','00:36:00.000000',100000,'INTERNACIONAL',4,8,4,192),(17,'NORMAL','2023-12-16','13:27:00.000000',0,'NACIONAL',1,1,1,80);
/*!40000 ALTER TABLE `model_vuelo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-14 16:02:44
