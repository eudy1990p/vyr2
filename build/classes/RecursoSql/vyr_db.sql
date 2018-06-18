-- MySQL dump 10.16  Distrib 10.1.22-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: vyr_db
-- ------------------------------------------------------
-- Server version	10.1.22-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `persona_id` int(11) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `persona_id` (`persona_id`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `cliente_ibfk_2` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES 
(1,1,'2017-03-11 20:22:41',1,1),
(2,2,'2017-03-13 00:25:02',1,1),
(3,3,'2017-03-17 19:55:46',1,1),
(4,4,'2017-07-01 14:08:27',1,1),
(5,5,'2017-07-01 14:17:36',1,1),
(6,6,'2017-07-01 14:38:14',1,1),
(7,7,'2017-07-01 14:42:12',1,1),
(8,8,'2017-07-01 20:44:13',1,1),
(9,9,'2017-07-09 14:26:57',1,1),
(10,10,'2017-07-09 14:26:57',1,1),
(11,11,'2017-07-09 14:26:57',1,1),
(12,12,'2017-07-09 14:26:57',1,1),
(13,13,'2017-07-09 14:26:57',1,1),
(14,14,'2017-07-09 14:26:57',1,1),
(15,15,'2017-07-09 14:26:57',1,1),
(16,16,'2017-07-09 14:26:57',1,1),
(17,17,'2017-07-09 14:26:57',1,1),
(18,18,'2017-07-09 14:26:57',1,1),
(19,19,'2017-07-09 14:26:57',1,1),
(20,20,'2017-07-09 14:26:57',1,1),
(21,21,'2017-07-09 14:26:57',1,1),
(22,22,'2017-07-09 14:26:57',1,1),
(23,23,'2017-07-09 14:26:57',1,1),
(24,24,'2017-07-09 14:26:57',1,1),
(25,25,'2017-07-09 14:26:57',1,1),
(26,26,'2017-07-09 14:26:57',1,1),
(27,27,'2017-07-09 14:26:57',1,1),
(28,28,'2017-07-09 14:26:57',1,1),
(29,29,'2017-07-09 14:26:57',1,1),
(30,30,'2017-07-09 14:26:57',1,1),
(31,31,'2017-07-09 14:26:57',1,1),
(32,32,'2017-07-09 14:26:57',1,1),
(33,33,'2017-07-09 14:26:57',1,1),
(34,34,'2017-07-09 14:26:57',1,1),
(35,35,'2017-07-09 14:26:57',1,1),
(36,36,'2017-07-09 14:26:57',1,1),
(37,37,'2017-07-09 14:26:57',1,1),
(38,38,'2017-07-09 14:26:57',1,1),
(39,39,'2017-07-09 14:26:57',1,1),
(40,40,'2017-07-09 14:26:57',1,1),
(41,41,'2017-07-09 14:26:57',1,1),
(42,42,'2017-07-09 14:26:57',1,1),
(43,43,'2017-07-09 14:26:57',1,1),
(44,44,'2017-07-09 14:26:57',1,1),
(45,45,'2017-07-09 14:26:57',1,1),
(46,46,'2017-07-09 14:26:57',1,1),
(47,47,'2017-07-09 14:26:57',1,1),
(48,48,'2017-07-09 14:26:57',1,1),
(49,49,'2017-07-09 14:26:57',1,1),
(50,50,'2017-07-09 14:26:57',1,1),
(51,51,'2017-07-09 14:26:57',1,1),
(52,52,'2017-07-09 14:26:57',1,1),
(53,53,'2017-07-09 14:26:57',1,1),
(54,54,'2017-07-09 14:26:57',1,1),
(55,55,'2017-07-09 14:26:57',1,1),
(56,56,'2017-07-09 14:26:57',1,1),
(57,57,'2017-07-09 14:26:57',1,1),
(58,58,'2017-07-09 14:26:57',1,1),
(59,59,'2017-07-09 14:26:57',1,1),
(60,60,'2017-07-09 14:26:57',1,1),
(61,61,'2017-07-09 14:26:57',1,1),
(62,62,'2017-07-09 14:26:57',1,1),
(63,63,'2017-07-09 14:26:57',1,1),
(64,64,'2017-07-09 14:26:57',1,1),
(65,65,'2017-07-09 14:26:57',1,1),
(66,66,'2017-07-09 14:26:57',1,1),
(67,67,'2017-07-09 14:26:57',1,1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cotizacion`
--

DROP TABLE IF EXISTS `cotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cotizacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `sub_total` decimal(20,2) DEFAULT NULL,
  `itbis` decimal(20,2) DEFAULT NULL,
  `tiene_itbis` int(1) DEFAULT '1',
  `total` decimal(20,2) DEFAULT NULL,
  `fecha_creada` datetime DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `estado` enum('anulada','facturada','pendiente') DEFAULT 'pendiente',
  `nota` text,
  `porciente_itbis` int(11) DEFAULT NULL,
  `usuario_id_anulado` int(11) DEFAULT NULL,
  `comentario_anulado` text,
  `display` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `usuario_id_anulado` (`usuario_id_anulado`),
  KEY `cliente_id` (`cliente_id`),
  CONSTRAINT `cotizacion_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `cotizacion_ibfk_2` FOREIGN KEY (`usuario_id_anulado`) REFERENCES `usuario` (`id`),
  CONSTRAINT `cotizacion_ibfk_3` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizacion`
--

--LOCK TABLES `cotizacion` WRITE;
/*!40000 ALTER TABLE `cotizacion` DISABLE KEYS */;
--INSERT INTO `cotizacion` VALUES (1,1,24.00,0.00,0,24.00,'2017-03-11 20:31:01',1,'pendiente',NULL,NULL,NULL,NULL,1),(2,1,69.00,0.00,0,69.00,'2017-03-11 20:33:38',1,'pendiente',NULL,NULL,NULL,NULL,1),(3,1,112.00,0.00,0,112.00,'2017-03-13 13:38:49',2,'pendiente',NULL,NULL,NULL,NULL,1),(4,1,13.00,0.00,0,13.00,'2017-03-23 14:16:13',1,'pendiente',NULL,NULL,NULL,NULL,1),(5,1,250.00,0.00,0,250.00,'2017-03-23 14:30:57',3,'pendiente',NULL,NULL,NULL,NULL,1),(6,1,0.00,0.00,0,0.00,'2017-07-03 17:27:39',5,'pendiente','',NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `cotizacion` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `cotizacion_detalle`
--

DROP TABLE IF EXISTS `cotizacion_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cotizacion_detalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `precio` decimal(20,2) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `total` decimal(20,2) DEFAULT NULL,
  `fecha_creada` datetime DEFAULT NULL,
  `producto_inventariado_id` int(11) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `estado` enum('anulada','pendiente') DEFAULT 'pendiente',
  `usuario_id_anulado` int(11) DEFAULT NULL,
  `comentario_anulado` text,
  `display` int(1) DEFAULT '1',
  `cotizacion_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `producto_inventariado_id` (`producto_inventariado_id`),
  KEY `usuario_id_anulado` (`usuario_id_anulado`),
  KEY `cotizacion_id` (`cotizacion_id`),
  CONSTRAINT `cotizacion_detalle_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `cotizacion_detalle_ibfk_2` FOREIGN KEY (`producto_inventariado_id`) REFERENCES `producto_inventariado` (`id`),
  CONSTRAINT `cotizacion_detalle_ibfk_3` FOREIGN KEY (`usuario_id_anulado`) REFERENCES `usuario` (`id`),
  CONSTRAINT `cotizacion_detalle_ibfk_4` FOREIGN KEY (`cotizacion_id`) REFERENCES `cotizacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizacion_detalle`
--

--LOCK TABLES `cotizacion_detalle` WRITE;
/*!40000 ALTER TABLE `cotizacion_detalle` DISABLE KEYS */;
--INSERT INTO `cotizacion_detalle` VALUES (4,1,'as',33.00,23,759.00,'2017-03-11 20:31:16',NULL,1,'pendiente',NULL,NULL,1,1),(5,1,'cxz',12.00,2,24.00,'2017-03-11 20:31:16',NULL,1,'pendiente',NULL,NULL,1,1),(6,1,'sd',43.00,2,86.00,'2017-03-11 20:33:38',NULL,1,'pendiente',NULL,NULL,1,2),(7,1,'cds',23.00,3,69.00,'2017-03-11 20:33:38',NULL,1,'pendiente',NULL,NULL,1,2),(8,1,'producto1',13.00,1,13.00,'2017-03-13 13:38:49',2,2,'pendiente',NULL,NULL,1,3),(9,1,'zapatos',112.00,1,112.00,'2017-03-13 13:38:49',1,2,'pendiente',NULL,NULL,1,3),(10,1,'zapatos',112.00,1,112.00,'2017-03-23 14:16:13',1,1,'pendiente',NULL,NULL,1,4),(11,1,'zapatos',112.00,1,112.00,'2017-03-23 14:16:13',1,1,'pendiente',NULL,NULL,1,4),(12,1,'zapatos',112.00,1,112.00,'2017-03-23 14:16:13',1,1,'pendiente',NULL,NULL,1,4),(13,1,'producto1',13.00,1,13.00,'2017-03-23 14:16:13',2,1,'pendiente',NULL,NULL,1,4),(14,1,'producto1',13.00,1,13.00,'2017-03-23 14:16:13',2,1,'pendiente',NULL,NULL,1,4),(15,1,'producto1',13.00,1,13.00,'2017-03-23 14:30:57',2,3,'pendiente',NULL,NULL,1,5),(16,1,'zapatos',112.00,1,112.00,'2017-03-23 14:30:58',1,3,'pendiente',NULL,NULL,1,5),(17,1,'producto1',13.00,1,13.00,'2017-03-23 14:30:58',2,3,'pendiente',NULL,NULL,1,5),(18,1,'zapatos',112.00,1,112.00,'2017-03-23 14:30:58',1,3,'pendiente',NULL,NULL,1,5),(19,1,'producto1(2)',13.00,1,13.00,'2017-07-03 17:27:39',2,5,'pendiente',NULL,NULL,1,6),(20,1,'reparacion 3(5)',2000.00,1,2000.00,'2017-07-03 17:27:39',5,5,'pendiente',NULL,NULL,1,6);
/*!40000 ALTER TABLE `cotizacion_detalle` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `cuadre`
--

DROP TABLE IF EXISTS `cuadre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuadre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `fecha_desde` date DEFAULT NULL,
  `fecha_hasta` date DEFAULT NULL,
  `monto_facturado` decimal(20,2) DEFAULT NULL,
  `monto_en_caja` decimal(20,2) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `cuadre_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuadre`
--

--LOCK TABLES `cuadre` WRITE;
/*!40000 ALTER TABLE `cuadre` DISABLE KEYS */;
--INSERT INTO `cuadre` VALUES (1,1,'2017-04-05 12:11:27','2017-03-21','2017-03-21',125.00,5.00,1);
/*!40000 ALTER TABLE `cuadre` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `cuadre_detalle`
--

DROP TABLE IF EXISTS `cuadre_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuadre_detalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `factura_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  `cuadre_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `factura_id` (`factura_id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `cuadre_id` (`cuadre_id`),
  CONSTRAINT `cuadre_detalle_ibfk_1` FOREIGN KEY (`factura_id`) REFERENCES `factura` (`id`),
  CONSTRAINT `cuadre_detalle_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `cuadre_detalle_ibfk_3` FOREIGN KEY (`cuadre_id`) REFERENCES `cuadre` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuadre_detalle`
--

--LOCK TABLES `cuadre_detalle` WRITE;
/*!40000 ALTER TABLE `cuadre_detalle` DISABLE KEYS */;
--INSERT INTO `cuadre_detalle` VALUES (1,1,'2017-04-05 12:11:28',3,1,1),(2,1,'2017-04-05 12:11:28',5,1,1);
/*!40000 ALTER TABLE `cuadre_detalle` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sector` varchar(50) DEFAULT NULL,
  `provincia` varchar(50) DEFAULT NULL,
  `localidad` text,
  `fecha_creado` datetime DEFAULT NULL,
  `persona_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `persona_id` (`persona_id`),
  CONSTRAINT `direccion_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `direccion_ibfk_2` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

--LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
--INSERT INTO `direccion` VALUES (1,'aaaaaa','aaaaa','aaaaaaa','2017-03-13 00:13:38',2,1,1),(2,'aaaaaa','aaaaa','aaaaaaa','2017-03-13 00:13:40',3,1,1),(3,'aaaaaa','aaaaa','aaaaaaa','2017-03-13 00:13:42',4,1,1),(4,'aaaaaa','aaaaa','aaaaaaa','2017-03-13 00:13:45',5,1,1),(5,'aaaaaa','aaaaa','aaaaaaa','2017-03-13 00:13:46',6,1,1),(6,'aaaaaa','aaaaa','aaaaaaa','2017-03-13 00:13:48',7,1,1),(7,'aaaaaa','aaaaa','aaaaaaa','2017-03-13 00:13:50',8,1,1),(8,'aaaaaa','aaaaa','aaaaaaa','2017-03-13 00:13:52',9,1,1),(9,'aaaaaa','aaaaa','aaaaaaa','2017-03-13 00:13:54',10,1,1),(10,'aaaaaa','aaaaa','aaaaaaa','2017-03-13 00:13:56',11,1,1),(11,'aaaaaa','aaaaa','aaaaaaa','2017-03-13 00:13:57',12,1,1),(12,'aaaaaa','aaaaa','aaaaaaa','2017-03-13 00:13:58',13,1,1),(13,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:18',14,1,1),(14,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:20',15,1,1),(15,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:22',16,1,1),(16,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:23',17,1,1),(17,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:25',18,1,1),(18,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:25',19,1,1),(19,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:26',20,1,1),(20,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:28',21,1,1),(21,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:29',22,1,1),(22,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:30',23,1,1),(23,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:31',24,1,1),(24,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:32',25,1,1),(25,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:33',26,1,1),(26,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:34',27,1,1),(27,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:35',28,1,1),(28,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:37',29,1,1),(29,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:38',30,1,1),(30,'aaaaa','aaaaa','aaaaaa','2017-03-13 00:17:39',31,1,1),(31,'aaaaa','aaaa','aaaaaa','2017-03-13 00:25:02',32,1,1),(32,'bbbbb','bbbb','bbbb','2017-03-17 19:55:46',33,1,1),(33,'la canita','sanjuan','k. prueba','2017-07-01 14:08:27',34,1,1),(34,'sector','provincia','direccion x','2017-07-01 14:17:36',35,1,1),(35,'sector','provincia','direccion cliente','2017-07-01 14:38:14',36,1,1),(36,'sector1','provincia1','direccion1','2017-07-01 14:42:12',37,1,1),(37,'sector','provincia','direccion','2017-07-01 20:44:13',38,1,1),(38,'zz','zz','zzz','2017-07-09 14:26:57',39,1,1);
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `email`
--

DROP TABLE IF EXISTS `email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(20) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `persona_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `persona_id` (`persona_id`),
  CONSTRAINT `email_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `email_ibfk_2` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email`
--

--LOCK TABLES `email` WRITE;
/*!40000 ALTER TABLE `email` DISABLE KEYS */;
--INSERT INTO `email` VALUES (1,'eudy@gmail.com','2017-03-12 19:37:21',1,1,1),(2,'eudy1990@gmail.com','2017-03-12 19:37:35',1,1,1),(3,'eudy90@gmail.com','2017-03-12 19:37:48',1,1,1),(4,'aaaaaa','2017-03-13 00:17:18',14,1,1),(5,'aaaaaa','2017-03-13 00:17:20',15,1,1),(6,'aaaaaa','2017-03-13 00:17:22',16,1,1),(7,'aaaaaa','2017-03-13 00:17:23',17,1,1),(8,'aaaaaa','2017-03-13 00:17:24',18,1,1),(9,'aaaaaa','2017-03-13 00:17:25',19,1,1),(10,'aaaaaa','2017-03-13 00:17:26',20,1,1),(11,'aaaaaa','2017-03-13 00:17:28',21,1,1),(12,'aaaaaa','2017-03-13 00:17:29',22,1,1),(13,'aaaaaa','2017-03-13 00:17:30',23,1,1),(14,'aaaaaa','2017-03-13 00:17:31',24,1,1),(15,'aaaaaa','2017-03-13 00:17:32',25,1,1),(16,'aaaaaa','2017-03-13 00:17:33',26,1,1),(17,'aaaaaa','2017-03-13 00:17:34',27,1,1),(18,'aaaaaa','2017-03-13 00:17:35',28,1,1),(19,'aaaaaa','2017-03-13 00:17:36',29,1,1),(20,'aaaaaa','2017-03-13 00:17:38',30,1,1),(21,'aaaaaa','2017-03-13 00:17:39',31,1,1),(22,'aaaaaaa','2017-03-13 00:25:02',32,1,1),(23,'bbbb','2017-03-17 19:55:46',33,1,1),(24,'eudy@gmail.com','2017-07-01 14:08:26',34,1,1),(25,'rafaul@gmail.com','2017-07-01 14:17:36',35,1,1),(26,'ramirez@gmail.com','2017-07-01 14:38:14',36,1,1),(27,'amauris@gmail.com','2017-07-01 14:42:12',37,1,1),(28,'miguel@gmail.com','2017-07-01 20:44:13',38,1,1),(29,'zz','2017-07-09 14:26:57',39,1,1);
/*!40000 ALTER TABLE `email` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `persona_id` int(11) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `usuario_empleado_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `usuario_empleado_id` (`usuario_empleado_id`),
  KEY `persona_id` (`persona_id`),
  CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `empleado_ibfk_2` FOREIGN KEY (`usuario_empleado_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `empleado_ibfk_3` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

--LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
--INSERT INTO `empleado` VALUES (1,34,'2017-04-03 14:34:04',1,2,1);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `cuadrada` int(1) DEFAULT '0',
  `sub_total` decimal(20,2) DEFAULT NULL,
  `itbis` decimal(20,2) DEFAULT NULL,
  `tiene_itbis` int(1) DEFAULT '1',
  `total` decimal(20,2) DEFAULT NULL,
  `numero_comprovante_fiscal` varchar(50) DEFAULT NULL,
  `fecha_creada` datetime DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `estado` enum('anulada','saldada','pendiente') DEFAULT 'pendiente',
  `nota` text,
  `porciente_itbis` int(11) DEFAULT NULL,
  `tiene_comprovante_fiscal` int(1) DEFAULT '0',
  `balance_deuda` decimal(20,2) DEFAULT '0.00',
  `monto_pagado` decimal(20,2) DEFAULT '0.00',
  `cambio_efectivo` decimal(20,2) DEFAULT '0.00',
  `usuario_id_anulado` int(11) DEFAULT NULL,
  `comentario_anulado` text,
  `display` int(1) DEFAULT '1',
  `fecha_cuadre` datetime DEFAULT NULL,
  `usuario_id_cuadre` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `usuario_id_anulado` (`usuario_id_anulado`),
  KEY `cliente_id` (`cliente_id`),
  CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `factura_ibfk_2` FOREIGN KEY (`usuario_id_anulado`) REFERENCES `usuario` (`id`),
  CONSTRAINT `factura_ibfk_3` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

--LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
--INSERT INTO `factura` VALUES (1,1,0,112.00,0.00,0,112.00,'','2017-03-21 12:01:17',1,'pendiente',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(2,1,0,13.00,0.00,0,13.00,'','2017-03-21 12:03:27',1,'pendiente',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(3,1,1,112.00,0.00,0,112.00,'','2017-03-21 12:06:45',3,'saldada',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,1,'2017-04-05 12:11:28',1),(4,1,0,13.00,0.00,0,13.00,'','2017-03-21 12:08:27',3,'pendiente',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL),(5,1,1,13.00,0.00,0,13.00,'','2017-06-01 13:07:36',1,'saldada',NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,1,'2017-04-05 12:11:28',1);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `factura_detalle`
--

DROP TABLE IF EXISTS `factura_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura_detalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `precio` decimal(20,2) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `total` decimal(20,2) DEFAULT NULL,
  `fecha_creada` datetime DEFAULT NULL,
  `producto_inventariado_id` int(11) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `estado` enum('anulada','pendiente') DEFAULT 'pendiente',
  `usuario_id_anulado` int(11) DEFAULT NULL,
  `comentario_anulado` text,
  `display` int(1) DEFAULT '1',
  `factura_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `producto_inventariado_id` (`producto_inventariado_id`),
  KEY `usuario_id_anulado` (`usuario_id_anulado`),
  KEY `factura_id` (`factura_id`),
  CONSTRAINT `factura_detalle_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `factura_detalle_ibfk_2` FOREIGN KEY (`producto_inventariado_id`) REFERENCES `producto_inventariado` (`id`),
  CONSTRAINT `factura_detalle_ibfk_3` FOREIGN KEY (`usuario_id_anulado`) REFERENCES `usuario` (`id`),
  CONSTRAINT `factura_detalle_ibfk_4` FOREIGN KEY (`factura_id`) REFERENCES `factura` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura_detalle`
--

--LOCK TABLES `factura_detalle` WRITE;
/*!40000 ALTER TABLE `factura_detalle` DISABLE KEYS */;
--INSERT INTO `factura_detalle` VALUES (1,1,'producto1',13.00,1,13.00,'2017-03-21 12:06:12',2,1,'pendiente',NULL,NULL,1,2),(2,1,'producto1',13.00,1,13.00,'2017-03-21 12:06:45',2,3,'pendiente',NULL,NULL,1,3),(3,1,'zapatos',112.00,1,112.00,'2017-03-21 12:06:45',1,3,'pendiente',NULL,NULL,1,3),(4,1,'zapatos',112.00,1,112.00,'2017-03-21 12:08:28',1,3,'pendiente',NULL,NULL,1,4),(5,1,'producto1',13.00,1,13.00,'2017-03-21 12:08:28',2,3,'pendiente',NULL,NULL,1,4),(6,1,'producto1',13.00,1,13.00,'2017-03-21 13:07:36',2,1,'pendiente',NULL,NULL,1,5);
/*!40000 ALTER TABLE `factura_detalle` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `inventario_reparaciones`
--

DROP TABLE IF EXISTS `inventario_reparaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventario_reparaciones` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `nombre` varchar(250) DEFAULT NULL,
  `cantidad_compada` int(11) DEFAULT NULL,
  `precio_compra` decimal(20,2) DEFAULT NULL,
  `precio_venta` decimal(20,2) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `inventario_reparaciones_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventario_reparaciones`
--

--LOCK TABLES `inventario_reparaciones` WRITE;
/*!40000 ALTER TABLE `inventario_reparaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventario_reparaciones` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `nota_cliente`
--

DROP TABLE IF EXISTS `nota_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nota_cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `cliente_id` (`cliente_id`),
  CONSTRAINT `nota_cliente_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `nota_cliente_ibfk_2` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nota_cliente`
--

--LOCK TABLES `nota_cliente` WRITE;
/*!40000 ALTER TABLE `nota_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `nota_cliente` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `pago_nomina_empleado`
--

DROP TABLE IF EXISTS `pago_nomina_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pago_nomina_empleado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `monto_pagar_al_empleado` decimal(20,2) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  `empleado_id` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `mes` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `empleado_id` (`empleado_id`),
  CONSTRAINT `pago_nomina_empleado_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `pago_nomina_empleado_ibfk_2` FOREIGN KEY (`empleado_id`) REFERENCES `empleado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago_nomina_empleado`
--

--LOCK TABLES `pago_nomina_empleado` WRITE;
/*!40000 ALTER TABLE `pago_nomina_empleado` DISABLE KEYS */;
--INSERT INTO `pago_nomina_empleado` VALUES (1,1,'2017-04-04 14:30:16',32.00,1,1,'2017-04-04',1);
/*!40000 ALTER TABLE `pago_nomina_empleado` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `monto_pagado` decimal(20,2) DEFAULT NULL,
  `balance_anterior` decimal(20,2) DEFAULT NULL,
  `balance_despues_del_pago` decimal(20,2) DEFAULT NULL,
  `cambio` decimal(20,2) DEFAULT NULL,
  `tipo_pago` enum('efectivo','cheque','tarjeta','transferencia') DEFAULT NULL,
  `factura_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `factura_id` (`factura_id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `pagos_ibfk_1` FOREIGN KEY (`factura_id`) REFERENCES `factura` (`id`),
  CONSTRAINT `pagos_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

--LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT ' ',
  `apellido` varchar(100) DEFAULT ' ',
  `cedula` varchar(30) DEFAULT ' ',
  `fecha_nacimiento` date DEFAULT NULL,
  `sexo` enum('masculino','femenino') DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  `rnc` varchar(100) DEFAULT ' ',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cedula` (`cedula`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES 
(1,'ALAMEDA','','','2017-03-11','masculino','2017-03-11 15:36:29',1,1,''),
(2,'ALEJANDRO VIDRIO Y MAS','','','2017-03-11','masculino','2017-03-13 00:13:24',1,1,''),
(3,'ALTEMIO','','','','masculino','2017-03-13 00:13:39',1,1,''),
(4,'ARCANGEL JONAS BAUTISTA','','','','masculino','2017-03-13 00:13:41',1,1,''),
(5,'ARISTIDES DEL CARMEN','','','','masculino','2017-03-13 00:13:43',1,1,''),
(6,'ARMANDO','','','','masculino','2017-03-13 00:13:45',1,1,''),
(7,'BELTRE','','','','masculino','2017-03-13 00:13:45',1,1,''),
(8,'BETANCES','','','','masculino','2017-03-13 00:13:45',1,1,''),
(9,'BIMCA ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(10,'CARLOS MARTE  (EL CHINO)','','','','masculino','2017-03-13 00:13:45',1,1,''),
(11,'CARLOS MARTINEZ ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(12,'CARLOS NATALIO(CARLITOS) ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(13,'CELESTE  ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(14,'CONTRUCTORA SUED','','','','masculino','2017-03-13 00:13:45',1,1,''),
(15,'CRISTIAN MINYETI','','','','masculino','2017-03-13 00:13:45',1,1,''),
(16,'DARWIN MIESES','','','','masculino','2017-03-13 00:13:45',1,1,''),
(17,'DIONICIO DE LA ROSA ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(18,'DOMINGO ANTONIO SANTOS GARCIA ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(19,'EDGAR LEONARDO SANTOS ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(20,'EDWIN FELIZ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(21,'ELIAS SOTO','','','','masculino','2017-03-13 00:13:45',1,1,''),
(22,'ENMANUEL MUNOZ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(23,'FRANCISCO FIBRA','','','','masculino','2017-03-13 00:13:45',1,1,''),
(24,'FRANCKILN ROBLES ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(25,'FRANKLIN     ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(26,'FRANKLIN DE LOS SANTOS','','','','masculino','2017-03-13 00:13:45',1,1,''),
(27,'GENEDY MIGUEL PENA ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(28,'GREGORIO MARTINEZ  ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(29,'JERIBEL PENA','','','','masculino','2017-03-13 00:13:45',1,1,''),
(30,'JESUS CONDE','','','','masculino','2017-03-13 00:13:45',1,1,''),
(31,'JESUS PUERTAS Y VENTANAS','','','','masculino','2017-03-13 00:13:45',1,1,''),
(32,'JORGE ALBERTO FEBRILLET ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(33,'JORGE LORENZO','','','','masculino','2017-03-13 00:13:45',1,1,''),
(34,'JOSE BALDERA','','','','masculino','2017-03-13 00:13:45',1,1,''),
(35,'JOSELO FAMEJAO ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(36,'JUAN ALEXIS ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(37,'JUAN ALMONTE ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(38,'JUAN FERRERAS REYES ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(39,'JUAN GONZALEZ ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(40,'JULIO PANTOJA  ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(41,'JUNIOR CACERES     ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(42,'KENNEDY   ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(43,'LACHAPELLE   ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(44,'LUCAS FELIZ  ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(45,'MARCELINO ANTONIO VENTURA(TONITO) ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(46,'MIGUEL ANTONIO ALCANTARA      ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(47,'NELSON NUNEZ ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(48,'NICOLAS RAMOS  ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(49,'NICOLAS TIBURCIO  ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(50,'ODELTO CABRERA  ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(51,'PALMAREJO ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(52,'PEDRO PEREZ     ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(53,'PEDRO VALDEZ ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(54,'RAFAEL DAVID MENA  ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(55,'RAYMOND    ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(56,'RICARDO GUTIERREZ  ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(57,'ROMEL HERASMO   ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(58,'RUBEN ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(59,'SANTO DOMINGO MATEO BATISTA  ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(60,'SEVERIANO HERRERA   ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(61,'SIXTO ACOSTA','','','','masculino','2017-03-13 00:13:45',1,1,''),
(62,'SKENE  ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(63,'SPC ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(64,'TALLERES RIVERA  ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(65,'THOMAS DE MORA ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(66,'TONY ','','','','masculino','2017-03-13 00:13:45',1,1,''),
(67,'VICTOR MONTERO','','','','masculino','2017-03-13 00:13:45',1,1,'');
--JUANSITO DE OLEO ALCANTARA   
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `producto_inventariado`
--

DROP TABLE IF EXISTS `producto_inventariado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto_inventariado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `nombre` varchar(250) DEFAULT NULL,
  `cantidad_comprada` int(11) DEFAULT '0',
  `precio_compra` decimal(20,2) DEFAULT '0.00',
  `precio_venta` decimal(20,2) DEFAULT '0.00',
  `display` int(1) DEFAULT '1',
  `fecha_creado` datetime DEFAULT NULL,
  `tipo_producto` enum('venta_producto','repacion_producto') DEFAULT 'venta_producto',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `producto_inventariado_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_inventariado`
--

--LOCK TABLES `producto_inventariado` WRITE;
/*!40000 ALTER TABLE `producto_inventariado` DISABLE KEYS */;
--INSERT INTO `producto_inventariado` VALUES (1,1,'zapatos',0,0.00,112.00,1,'2017-03-13 12:00:16','venta_producto'),(2,1,'producto1',0,0.00,13.00,1,'2017-03-13 12:11:52','venta_producto'),(3,1,'reparacion producto',23,12.00,123.00,1,'2017-03-24 20:39:31','repacion_producto'),(4,1,'reparacion 1',20,9.00,10.00,1,'2017-03-25 11:07:18','repacion_producto'),(5,1,'reparacion 3',90,10.00,2000.00,1,'2017-07-01 18:03:26','venta_producto'),(6,1,'reparacion4',1000,20.00,800.00,1,'2017-07-01 18:31:31','repacion_producto');
/*!40000 ALTER TABLE `producto_inventariado` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `reparacion`
--

DROP TABLE IF EXISTS `reparacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reparacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `sub_total` decimal(20,2) DEFAULT NULL,
  `fecha_creada` datetime DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `estado` enum('anulada','procesando','pendiente','completo') DEFAULT 'pendiente',
  `nota` text,
  `usuario_id_anulado` int(11) DEFAULT NULL,
  `comentario_anulado` text,
  `display` int(1) DEFAULT '1',
  `abono` decimal(20,2) DEFAULT NULL,
  `itbis` decimal(20,2) DEFAULT NULL,
  `tiene_itbis` int(1) DEFAULT '1',
  `total` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `usuario_id_anulado` (`usuario_id_anulado`),
  KEY `cliente_id` (`cliente_id`),
  CONSTRAINT `reparacion_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `reparacion_ibfk_2` FOREIGN KEY (`usuario_id_anulado`) REFERENCES `usuario` (`id`),
  CONSTRAINT `reparacion_ibfk_3` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reparacion`
--

--LOCK TABLES `reparacion` WRITE;
/*!40000 ALTER TABLE `reparacion` DISABLE KEYS */;
--INSERT INTO `reparacion` VALUES (1,1,13.00,'2017-03-23 09:50:00',1,'pendiente',NULL,NULL,NULL,1,NULL,NULL,1,NULL),(2,1,645.00,'2017-03-25 12:23:59',3,'completo','nota generar',NULL,NULL,1,2000.00,NULL,1,NULL);
/*!40000 ALTER TABLE `reparacion` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `reparacion_detalle`
--

DROP TABLE IF EXISTS `reparacion_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reparacion_detalle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_id` int(11) DEFAULT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `precio` decimal(20,2) DEFAULT NULL,
  `precio_completado` decimal(20,2) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `total` decimal(20,2) DEFAULT NULL,
  `fecha_creada` datetime DEFAULT NULL,
  `inventario_reparaciones_id` int(11) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `estado` enum('anulada','pendiente','completado','procesando','incompleto') DEFAULT 'pendiente',
  `nota_incompleto_por` text,
  `usuario_id_incompleto_por` int(11) DEFAULT NULL,
  `nota` text,
  `usuario_id_completado` int(11) DEFAULT NULL,
  `usuario_id_anulado` int(11) DEFAULT NULL,
  `comentario_anulado` text,
  `display` int(1) DEFAULT '1',
  `reparacion_id` int(11) DEFAULT NULL,
  `producto_inventariado_id` int(11) DEFAULT NULL,
  `nota_procesando` text,
  `usuario_id_procesando` int(11) DEFAULT NULL,
  `fecha_procesado` datetime DEFAULT NULL,
  `fecha_anulado` datetime DEFAULT NULL,
  `fecha_incompleto` datetime DEFAULT NULL,
  `fecha_completado` datetime DEFAULT NULL,
  `nota_completado` text,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `usuario_id_completado` (`usuario_id_completado`),
  KEY `usuario_id_incompleto_por` (`usuario_id_incompleto_por`),
  KEY `inventario_reparaciones_id` (`inventario_reparaciones_id`),
  KEY `reparacion_id` (`reparacion_id`),
  KEY `producto_inventariado_id` (`producto_inventariado_id`),
  KEY `usuario_id_procesando` (`usuario_id_procesando`),
  CONSTRAINT `reparacion_detalle_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `reparacion_detalle_ibfk_2` FOREIGN KEY (`usuario_id_completado`) REFERENCES `usuario` (`id`),
  CONSTRAINT `reparacion_detalle_ibfk_3` FOREIGN KEY (`usuario_id_incompleto_por`) REFERENCES `usuario` (`id`),
  CONSTRAINT `reparacion_detalle_ibfk_4` FOREIGN KEY (`inventario_reparaciones_id`) REFERENCES `inventario_reparaciones` (`id`),
  CONSTRAINT `reparacion_detalle_ibfk_5` FOREIGN KEY (`reparacion_id`) REFERENCES `reparacion` (`id`),
  CONSTRAINT `reparacion_detalle_ibfk_6` FOREIGN KEY (`producto_inventariado_id`) REFERENCES `producto_inventariado` (`id`),
  CONSTRAINT `reparacion_detalle_ibfk_7` FOREIGN KEY (`usuario_id_procesando`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reparacion_detalle`
--

--LOCK TABLES `reparacion_detalle` WRITE;
/*!40000 ALTER TABLE `reparacion_detalle` DISABLE KEYS */;
--INSERT INTO `reparacion_detalle` VALUES (1,NULL,'c',21.00,0.00,4,456.00,NULL,NULL,NULL,'pendiente',NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,1,'reparacion producto',123.00,NULL,1,123.00,'2017-03-25 12:24:00',NULL,3,'pendiente',NULL,NULL,'',NULL,NULL,NULL,1,2,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,1,'reparacion 1',10.00,NULL,1,10.00,'2017-03-25 12:24:07',NULL,3,'pendiente',NULL,NULL,'',NULL,NULL,NULL,1,2,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,1,'reparacion 1',10.00,NULL,1,10.00,'2017-03-25 12:24:13',NULL,3,'pendiente',NULL,NULL,'',NULL,NULL,NULL,1,2,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,1,'reparacion 1',10.00,NULL,1,10.00,'2017-03-25 12:24:22',NULL,3,'pendiente',NULL,NULL,'prueba',NULL,NULL,NULL,1,2,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,1,'reparacion producto',123.00,12.00,4,492.00,'2017-03-25 12:24:27',NULL,3,'completado','incompleto',NULL,'este es un 111',1,NULL,'anulado',1,2,3,'procesando',1,'2017-04-02 11:29:11',NULL,NULL,'2017-04-02 11:29:32','completo11');
/*!40000 ALTER TABLE `reparacion_detalle` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `telephone`
--

DROP TABLE IF EXISTS `telephone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telephone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `telephone` varchar(20) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `persona_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `tipo_telefono_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  KEY `persona_id` (`persona_id`),
  KEY `tipo_telefono_id` (`tipo_telefono_id`),
  CONSTRAINT `telephone_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `telephone_ibfk_2` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `telephone_ibfk_3` FOREIGN KEY (`tipo_telefono_id`) REFERENCES `tipo_telefono` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telephone`
--

--LOCK TABLES `telephone` WRITE;
/*!40000 ALTER TABLE `telephone` DISABLE KEYS */;
--INSERT INTO `telephone` VALUES (1,'aaaa','2017-03-13 00:17:18',14,1,1,1),(2,'aaaa','2017-03-13 00:17:20',15,1,1,1),(3,'aaaa','2017-03-13 00:17:22',16,1,1,1),(4,'aaaa','2017-03-13 00:17:23',17,1,1,1),(5,'aaaa','2017-03-13 00:17:24',18,1,1,1),(6,'aaaa','2017-03-13 00:17:25',19,1,1,1),(7,'aaaa','2017-03-13 00:17:26',20,1,1,1),(8,'aaaa','2017-03-13 00:17:28',21,1,1,1),(9,'aaaa','2017-03-13 00:17:29',22,1,1,1),(10,'aaaa','2017-03-13 00:17:30',23,1,1,1),(11,'aaaa','2017-03-13 00:17:31',24,1,1,1),(12,'aaaa','2017-03-13 00:17:32',25,1,1,1),(13,'aaaa','2017-03-13 00:17:33',26,1,1,1),(14,'aaaa','2017-03-13 00:17:34',27,1,1,1),(15,'aaaa','2017-03-13 00:17:35',28,1,1,1),(16,'aaaa','2017-03-13 00:17:37',29,1,1,1),(17,'aaaa','2017-03-13 00:17:38',30,1,1,1),(18,'aaaa','2017-03-13 00:17:39',31,1,1,1),(19,'aaaa','2017-03-13 00:25:02',32,1,1,1),(20,'bbbb','2017-03-17 19:55:46',33,1,1,1),(21,'8099088908','2017-07-01 14:08:27',34,1,1,1),(22,'77777777','2017-07-01 14:17:36',35,1,1,1),(23,'4444444','2017-07-01 14:38:14',36,1,1,1),(24,'11111111','2017-07-01 14:42:12',37,1,1,1),(25,'3333333','2017-07-01 20:44:13',38,1,1,1),(26,'zz','2017-07-09 14:26:57',39,1,1,1);
/*!40000 ALTER TABLE `telephone` ENABLE KEYS */;
--UNLOCK TABLES;

--
-- Table structure for table `tipo_telefono`
--

DROP TABLE IF EXISTS `tipo_telefono`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_telefono` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `tipo_telefono_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(100) DEFAULT NULL,
  `clave_usuario` varchar(150) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `tipo_usuario` enum('admin','cajero','tecnico','supervisor','versatil') DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'vyr','vyr123456@','2017-03-01 15:26:42','admin',1,1),(2,'prueba','123','2017-04-03 14:34:03','cajero',1,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

