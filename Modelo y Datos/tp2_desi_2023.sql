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
-- Table structure for table `asientos_vendidos`
--

DROP TABLE IF EXISTS `asientos_vendidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asientos_vendidos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `columna` int NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fila` int NOT NULL,
  `id_vuelo` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkqitkygjd60rm8rl675c4iid8` (`id_vuelo`),
  CONSTRAINT `FKkqitkygjd60rm8rl675c4iid8` FOREIGN KEY (`id_vuelo`) REFERENCES `model_vuelo` (`id_vuelo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asientos_vendidos`
--

LOCK TABLES `asientos_vendidos` WRITE;
/*!40000 ALTER TABLE `asientos_vendidos` DISABLE KEYS */;
INSERT INTO `asientos_vendidos` VALUES (1,2,'vendido',10,8),(2,3,'vendido',5,3),(3,4,'vendido',5,3),(4,2,'vendido',2,1),(5,3,'vendido',15,10),(6,4,'vendido',15,10);
/*!40000 ALTER TABLE `asientos_vendidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model_avion`
--

DROP TABLE IF EXISTS `model_avion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model_avion` (
  `id_avion` bigint NOT NULL AUTO_INCREMENT,
  `aerolinea` varchar(255) DEFAULT NULL,
  `asientosxfila` int NOT NULL,
  `filas` int NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_avion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_avion`
--

LOCK TABLES `model_avion` WRITE;
/*!40000 ALTER TABLE `model_avion` DISABLE KEYS */;
INSERT INTO `model_avion` VALUES (1,'FlyBondy',4,20,'McFly 2020'),(2,'Aerolineas Argentinas',6,24,'Fl3030'),(3,'Luftansa',6,22,'RTVB0054'),(4,'Aerolíneas Argentinas',8,24,'Airbus A380'),(5,'Aerolíneas Argentinas',6,24,'Boeing 737-800'),(6,'Aerolíneas Argentinas',8,26,'Boeing 737 MAX 8');
/*!40000 ALTER TABLE `model_avion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model_ciudad`
--

DROP TABLE IF EXISTS `model_ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model_ciudad` (
  `id_ciudad` bigint NOT NULL AUTO_INCREMENT,
  `codigo_postal` int NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_ciudad`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_ciudad`
--

LOCK TABLES `model_ciudad` WRITE;
/*!40000 ALTER TABLE `model_ciudad` DISABLE KEYS */;
INSERT INTO `model_ciudad` VALUES (1,1000,'Buenos Aires'),(2,5000,'Córdoba, Argentina'),(3,5500,'Mendoza, Argentina'),(4,8300,'Neuquén, Argentina'),(5,2000,'Rosario, Argentina'),(6,4400,'Salta, Argentina'),(7,10002,'New York. USA'),(8,1101,'Amsterdam, Netherlands'),(9,10115,'Berlin, Germany');
/*!40000 ALTER TABLE `model_ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model_cliente`
--

DROP TABLE IF EXISTS `model_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `model_cliente` (
  `id_cliente` bigint NOT NULL AUTO_INCREMENT,
  `apellido_nombre` varchar(255) DEFAULT NULL,
  `dni` int NOT NULL,
  `domicilio` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fechanacimiento` date DEFAULT NULL,
  `numero_pasaporte` int NOT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_cliente`
--

LOCK TABLES `model_cliente` WRITE;
/*!40000 ALTER TABLE `model_cliente` DISABLE KEYS */;
INSERT INTO `model_cliente` VALUES (1,'Medina Mario',20256698,'Las Heras 2020','medinamario@gmail.com','1990-01-01',123456),(2,'Pedroni Esteban',24887978,'Fcdo. Zuviría 5050','estebanpedroni@gmail.com','1975-10-15',1245587),(3,'Rodriguez María',23887978,'Brasil 5647 1 Dpto A','mariarodriguez@gmail.com','1972-04-22',1112500);
/*!40000 ALTER TABLE `model_cliente` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model_vuelo`
--

LOCK TABLES `model_vuelo` WRITE;
/*!40000 ALTER TABLE `model_vuelo` DISABLE KEYS */;
INSERT INTO `model_vuelo` VALUES (1,'NORMAL','2023-12-05','00:00:00.000000',100000,'NACIONAL',1,2,1,80),(2,'NORMAL','2023-12-20','00:00:00.000000',34000,'NACIONAL',2,5,3,144),(3,'NORMAL','2023-12-14','01:07:00.000000',232323,'NACIONAL',1,1,1,80),(4,'NORMAL','2023-12-01','12:50:00.000000',456433,'NACIONAL',1,1,1,80),(5,'NORMAL','2023-12-09','12:10:00.000000',300000,'INTERNACIONAL',4,8,1,192),(6,'NORMAL','2023-12-09','08:00:00.000000',50000,'NACIONAL',2,4,1,144),(7,'NORMAL','2023-12-09','15:00:00.000000',65656,'NACIONAL',1,4,1,80),(8,'NORMAL','2023-12-27','14:07:00.000000',60000,'NACIONAL',3,3,6,132),(10,'NORMAL','2023-12-29','00:36:00.000000',100000,'INTERNACIONAL',4,8,4,192),(17,'NORMAL','2023-12-16','13:27:00.000000',0,'NACIONAL',1,1,1,80),(20,'NORMAL','2023-12-29','00:58:00.000000',0,'INTERNACIONAL',5,7,1,144);
/*!40000 ALTER TABLE `model_vuelo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modelcp`
--

DROP TABLE IF EXISTS `modelcp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `modelcp` (
  `id_componente_precio` bigint NOT NULL AUTO_INCREMENT,
  `iva` double NOT NULL,
  `cotizacion` int NOT NULL,
  `nombrecp` varchar(255) DEFAULT NULL,
  `preciocp` double NOT NULL,
  `tasaai` double NOT NULL,
  `tasaan` double NOT NULL,
  PRIMARY KEY (`id_componente_precio`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modelcp`
--

LOCK TABLES `modelcp` WRITE;
/*!40000 ALTER TABLE `modelcp` DISABLE KEYS */;
INSERT INTO `modelcp` VALUES (1,21,800,'Tasa única',45000.04,3.02,2500.07);
/*!40000 ALTER TABLE `modelcp` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-14 19:02:49
