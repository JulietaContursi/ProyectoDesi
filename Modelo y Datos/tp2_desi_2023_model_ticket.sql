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
-- Table structure for table `model_ticket`
--

DROP TABLE IF EXISTS `model_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model_ticket` (
  `id_ticket` bigint NOT NULL AUTO_INCREMENT,
  `asiento_fila` int NOT NULL,
  `asiento_letra` char(1) NOT NULL,
  `fecha_ticket` datetime(6) DEFAULT NULL,
  `fecha_vuelo` datetime(6) DEFAULT NULL,
  `precio` double NOT NULL,
  `cliente_id_cliente` bigint DEFAULT NULL,
  `vuelo_id_vuelo` bigint DEFAULT NULL,
  `avion_id_avion` bigint DEFAULT NULL,
  `id_asiento` bigint DEFAULT NULL,
  PRIMARY KEY (`id_ticket`),
  UNIQUE KEY `UK_hx5qnggg2o7pbv01uppg19n62` (`id_asiento`),
  KEY `FK6l5hqrso7ts7kl886fideem4k` (`cliente_id_cliente`),
  KEY `FKiu54acjyt3mbd2apuredvulma` (`vuelo_id_vuelo`),
  KEY `FK1nn2og3th65obss02jks6xo9c` (`avion_id_avion`),
  CONSTRAINT `FK1nn2og3th65obss02jks6xo9c` FOREIGN KEY (`avion_id_avion`) REFERENCES `model_avion` (`id_avion`),
  CONSTRAINT `FK6l5hqrso7ts7kl886fideem4k` FOREIGN KEY (`cliente_id_cliente`) REFERENCES `model_cliente` (`id_cliente`),
  CONSTRAINT `FKgeic95g7imma99r45wi8e1w2n` FOREIGN KEY (`id_asiento`) REFERENCES `asientos_vendidos` (`id`),
  CONSTRAINT `FKiu54acjyt3mbd2apuredvulma` FOREIGN KEY (`vuelo_id_vuelo`) REFERENCES `model_vuelo` (`id_vuelo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_ticket`
--

LOCK TABLES `model_ticket` WRITE;
/*!40000 ALTER TABLE `model_ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `model_ticket` ENABLE KEYS */;
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
