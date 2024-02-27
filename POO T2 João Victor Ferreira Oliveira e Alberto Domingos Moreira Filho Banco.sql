CREATE DATABASE  IF NOT EXISTS `poot2joaovictorferreiraoliveiraealbertodomingosmoreirafilho` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `poot2joaovictorferreiraoliveiraealbertodomingosmoreirafilho`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: poot2joaovictorferreiraoliveiraealbertodomingosmoreirafilho
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
-- Table structure for table `alimento`
--

DROP TABLE IF EXISTS `alimento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alimento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `carboidratos` double NOT NULL,
  `proteinas` double NOT NULL,
  `gorduras` double NOT NULL,
  `calorias` double NOT NULL,
  `porcao` double NOT NULL,
  `dataCriacao` timestamp NOT NULL,
  `dataModificacao` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alimento`
--

LOCK TABLES `alimento` WRITE;
/*!40000 ALTER TABLE `alimento` DISABLE KEYS */;
INSERT INTO `alimento` VALUES (1,'Arroz Branco',28.18,2.5,0.23,124.78999999999999,100,'2023-11-27 03:00:00','2023-11-27 03:00:00'),(2,'Feijao Carioca',13.61,5.04,0.85,82.25,100,'2023-12-02 03:00:00','2023-12-02 03:00:00'),(3,'cafe',30,30,30,510,100,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(4,'costela de frango',0,27.91,14.57,242.76999999999998,100,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(5,'alface',1.63,0.5,0.08,9.24,100,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(6,'salada de batata',11.17,2.68,8.2,129.2,100,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(7,'arroz com legumes',19.14,2.54,1.37,99.05,100,'2023-12-07 03:00:00','2023-12-07 03:00:00');
/*!40000 ALTER TABLE `alimento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alimentorefeicoes`
--

DROP TABLE IF EXISTS `alimentorefeicoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alimentorefeicoes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `refeicao` int NOT NULL,
  `alimento` int NOT NULL,
  `porcao` double NOT NULL,
  `proteinas` double DEFAULT NULL,
  `gorduras` double DEFAULT NULL,
  `calorias` double NOT NULL,
  `dataCriacao` timestamp NOT NULL,
  `dataModificacao` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_refeicao` (`refeicao`),
  KEY `fk_alimento` (`alimento`),
  CONSTRAINT `fk_alimento` FOREIGN KEY (`alimento`) REFERENCES `alimento` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_refeicao` FOREIGN KEY (`refeicao`) REFERENCES `refeicoes` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alimentorefeicoes`
--

LOCK TABLES `alimentorefeicoes` WRITE;
/*!40000 ALTER TABLE `alimentorefeicoes` DISABLE KEYS */;
INSERT INTO `alimentorefeicoes` VALUES (5,1,1,100,2.5,0.23,124.78999999999999,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(6,1,2,100,5.04,0.85,82.25,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(7,1,3,100,30,30,510,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(8,1,5,100,0.5,0.08,9.24,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(9,3,3,100,30,30,510,'2023-12-07 03:00:00','2023-12-07 03:00:00');
/*!40000 ALTER TABLE `alimentorefeicoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avaliacaofisica`
--

DROP TABLE IF EXISTS `avaliacaofisica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `avaliacaofisica` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pessoa` int NOT NULL,
  `peso` double NOT NULL,
  `altura` double NOT NULL,
  `idade` int NOT NULL,
  `pescoco` double NOT NULL,
  `cintura` double NOT NULL,
  `quadril` double NOT NULL,
  `IMC` double NOT NULL,
  `TMB` double NOT NULL,
  `BF` double NOT NULL,
  `massaGorda` double NOT NULL,
  `massaMagra` double NOT NULL,
  `dataCriacao` timestamp NOT NULL,
  `dataModificacao` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pessoa` (`pessoa`),
  CONSTRAINT `fk_pessoa` FOREIGN KEY (`pessoa`) REFERENCES `pessoa` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avaliacaofisica`
--

LOCK TABLES `avaliacaofisica` WRITE;
/*!40000 ALTER TABLE `avaliacaofisica` DISABLE KEYS */;
INSERT INTO `avaliacaofisica` VALUES (1,1,80,180,21,38,80,95,21.604938271604937,1795.4,14.555925925925921,10.189148148148146,89.81085185185185,'2023-11-28 03:00:00','2023-12-02 20:39:49'),(3,3,100,190,25,42,85,105,27.70083102493075,0,22.790997229916897,22.790997229916897,77.2090027700831,'2023-12-02 03:00:00','2023-12-02 03:00:00'),(4,7,86,185,40,40,87,94,25.12783053323594,1945.0500000000002,23.153396639883127,19.91192111029949,80.08807888970051,'2023-12-02 03:00:00','2023-12-02 03:00:00'),(5,2,85,175,20,40,83,93,27.755102040816325,2017.35,21.706122448979585,18.450204081632645,81.54979591836735,'2023-12-07 03:00:00','2023-12-07 03:00:00');
/*!40000 ALTER TABLE `avaliacaofisica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dieta`
--

DROP TABLE IF EXISTS `dieta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dieta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pessoa` int NOT NULL,
  `avaliacaofisica` int NOT NULL,
  `tipodedieta` int NOT NULL,
  `objetivo` varchar(30) NOT NULL,
  `calorias` double NOT NULL,
  `nroRefeicoes` int NOT NULL,
  `dataCriacao` timestamp NOT NULL,
  `dataModificacao` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pessoa_dieta` (`pessoa`),
  KEY `fk_avaliacaofisica` (`avaliacaofisica`),
  KEY `fk_tipodedieta` (`tipodedieta`),
  CONSTRAINT `fk_avaliacaofisica` FOREIGN KEY (`avaliacaofisica`) REFERENCES `avaliacaofisica` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_pessoa_dieta` FOREIGN KEY (`pessoa`) REFERENCES `pessoa` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_tipodedieta` FOREIGN KEY (`tipodedieta`) REFERENCES `tipodedieta` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dieta`
--

LOCK TABLES `dieta` WRITE;
/*!40000 ALTER TABLE `dieta` DISABLE KEYS */;
INSERT INTO `dieta` VALUES (1,1,1,1,'Diminuir o peso',1495.4,2,'2023-11-30 03:00:00','2023-11-30 03:00:00'),(3,2,5,1,'Diminuir o peso',1717.35,3,'2023-12-07 03:00:00','2023-12-07 03:00:00');
/*!40000 ALTER TABLE `dieta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensagem`
--

DROP TABLE IF EXISTS `mensagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensagem` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pessoaOrigem` int NOT NULL,
  `pessoaDestino` int NOT NULL,
  `mensagem` varchar(300) NOT NULL,
  `dataCriacao` timestamp NOT NULL,
  `dataModificacao` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mensagem_pessoa` (`pessoaOrigem`),
  CONSTRAINT `fk_mensagem_pessoa` FOREIGN KEY (`pessoaOrigem`) REFERENCES `pessoa` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensagem`
--

LOCK TABLES `mensagem` WRITE;
/*!40000 ALTER TABLE `mensagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `mensagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pessoa` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `sexo` varchar(20) NOT NULL,
  `nascimento` date NOT NULL,
  `login` varchar(50) NOT NULL,
  `senha` varchar(50) NOT NULL,
  `tipousuario` varchar(50) NOT NULL,
  `dataCriacao` timestamp NOT NULL,
  `dataModificacao` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'Joao','Masculino','2002-04-30','jv','jv123','sedentario','2023-11-25 03:00:00','2023-12-02 20:22:08'),(2,'Alberto','Masculino','2002-05-20','ab','ab123','muito ativo','2023-11-25 03:00:00','2023-12-02 20:19:27'),(3,'Pedro','Masculino','2000-07-08','pa','pa123','levemente ativo','2023-11-25 03:00:00','2023-12-02 20:20:45'),(4,'joao pedro','masculino','2002-06-30','jp123','jp123','sedentario','2023-11-25 03:00:00','2023-11-25 03:00:00'),(5,'marcos','masculino','2005-01-20','mv','mv123','sedentario','2023-11-25 03:00:00','2023-11-25 03:00:00'),(6,'ana','feminino','2005-06-30','ana','ana123','atleta','2023-11-25 03:00:00','2023-11-25 03:00:00'),(7,'Eduardo','Masculino','1980-07-01','leo','leo123','muito ativo','2023-12-02 03:00:00','2023-12-02 03:00:00');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pessoa` int NOT NULL,
  `conteudo` varchar(300) NOT NULL,
  `dataCriacao` timestamp NOT NULL,
  `dataModificacao` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_post_pessoa` (`pessoa`),
  CONSTRAINT `fk_post_pessoa` FOREIGN KEY (`pessoa`) REFERENCES `pessoa` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferencias`
--

DROP TABLE IF EXISTS `preferencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preferencias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pessoa` int NOT NULL,
  `alimento` int NOT NULL,
  `dataCriacao` timestamp NOT NULL,
  `dataModificacao` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_prferencia_pessoa` (`pessoa`),
  KEY `fk_prferencia_alimento` (`alimento`),
  CONSTRAINT `fk_prferencia_alimento` FOREIGN KEY (`alimento`) REFERENCES `alimento` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_prferencia_pessoa` FOREIGN KEY (`pessoa`) REFERENCES `pessoa` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferencias`
--

LOCK TABLES `preferencias` WRITE;
/*!40000 ALTER TABLE `preferencias` DISABLE KEYS */;
INSERT INTO `preferencias` VALUES (1,1,1,'2023-12-02 03:00:00','2023-12-02 23:16:18'),(2,1,2,'2023-12-02 03:00:00','2023-12-02 03:00:00'),(3,2,3,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(4,2,4,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(5,2,5,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(6,2,6,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(7,2,7,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(8,1,3,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(9,1,5,'2023-12-07 03:00:00','2023-12-07 03:00:00'),(10,1,6,'2023-12-07 03:00:00','2023-12-07 03:00:00');
/*!40000 ALTER TABLE `preferencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refeicoes`
--

DROP TABLE IF EXISTS `refeicoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refeicoes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dieta` int NOT NULL,
  `carboidrato` double NOT NULL,
  `proteina` double NOT NULL,
  `gordura` double NOT NULL,
  `calorias` double NOT NULL,
  `nomedarefeicao` varchar(100) DEFAULT NULL,
  `dataCriacao` timestamp NOT NULL,
  `dataModificacao` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_refeicoes` (`dieta`),
  CONSTRAINT `fk_refeicoes` FOREIGN KEY (`dieta`) REFERENCES `dieta` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refeicoes`
--

LOCK TABLES `refeicoes` WRITE;
/*!40000 ALTER TABLE `refeicoes` DISABLE KEYS */;
INSERT INTO `refeicoes` VALUES (1,1,50,30,30,747.7,'Cafe da Manha','2023-11-30 03:00:00','2023-11-30 03:00:00'),(2,3,50,30,30,572.4499999999999,'Cafe da Manha','2023-12-07 03:00:00','2023-12-07 03:00:00'),(3,3,50,30,30,572.4499999999999,'Almoco','2023-12-07 03:00:00','2023-12-07 03:00:00');
/*!40000 ALTER TABLE `refeicoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seguir`
--

DROP TABLE IF EXISTS `seguir`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seguir` (
  `id` int NOT NULL AUTO_INCREMENT,
  `pessoaOrigem` int NOT NULL,
  `pessoaSeguindo` int NOT NULL,
  `seguindo` tinyint(1) NOT NULL,
  `dataCriacao` timestamp NOT NULL,
  `dataModificacao` timestamp NOT NULL,
  PRIMARY KEY (`pessoaOrigem`,`pessoaSeguindo`),
  UNIQUE KEY `id` (`id`),
  CONSTRAINT `fk_seguir_pessoa` FOREIGN KEY (`pessoaOrigem`) REFERENCES `pessoa` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seguir`
--

LOCK TABLES `seguir` WRITE;
/*!40000 ALTER TABLE `seguir` DISABLE KEYS */;
INSERT INTO `seguir` VALUES (12,1,2,1,'2023-12-08 03:00:00','2023-12-08 19:38:07');
/*!40000 ALTER TABLE `seguir` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodedieta`
--

DROP TABLE IF EXISTS `tipodedieta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipodedieta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `carboidrato` double NOT NULL,
  `proteina` double NOT NULL,
  `gordura` double NOT NULL,
  `dataCriacao` timestamp NOT NULL,
  `dataModificacao` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodedieta`
--

LOCK TABLES `tipodedieta` WRITE;
/*!40000 ALTER TABLE `tipodedieta` DISABLE KEYS */;
INSERT INTO `tipodedieta` VALUES (1,'Equilibrada',50,30,30,'2023-11-28 03:00:00','2023-12-02 21:48:43');
/*!40000 ALTER TABLE `tipodedieta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-13 15:08:33
