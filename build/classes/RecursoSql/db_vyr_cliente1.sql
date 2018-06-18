-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-08-2017 a las 17:21:27
-- Versión del servidor: 10.1.22-MariaDB
-- Versión de PHP: 7.0.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `vyr_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--
drop database if exists db_vyr_cliente1;
create database db_vyr_cliente1;
use db_vyr_cliente1;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `persona_id` int(11) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `persona_id`, `fecha_creado`, `usuario_id`, `display`) VALUES
(1, 1, '2017-03-11 20:22:41', 1, 1),
(2, 2, '2017-03-13 00:25:02', 1, 1),
(3, 3, '2017-03-17 19:55:46', 1, 1),
(4, 4, '2017-07-01 14:08:27', 1, 1),
(5, 5, '2017-07-01 14:17:36', 1, 1),
(6, 6, '2017-07-01 14:38:14', 1, 1),
(7, 7, '2017-07-01 14:42:12', 1, 1),
(8, 8, '2017-07-01 20:44:13', 1, 1),
(9, 9, '2017-07-09 14:26:57', 1, 1),
(10, 10, '2017-07-13 15:46:31', 1, 1),
(11, 11, '2017-07-13 15:46:31', 1, 1),
(12, 12, '2017-07-13 15:46:31', 1, 1),
(13, 13, '2017-07-13 15:46:31', 1, 1),
(14, 14, '2017-07-13 15:46:31', 1, 1),
(15, 15, '2017-07-13 15:46:31', 1, 1),
(16, 16, '2017-07-13 15:46:31', 1, 1),
(17, 17, '2017-07-13 15:46:31', 1, 1),
(18, 18, '2017-07-13 15:46:31', 1, 1),
(19, 19, '2017-07-13 15:46:31', 1, 1),
(20, 20, '2017-07-13 15:46:31', 1, 1),
(21, 21, '2017-07-13 15:46:31', 1, 1),
(22, 22, '2017-07-13 15:46:31', 1, 1),
(23, 23, '2017-07-13 15:46:31', 1, 1),
(24, 24, '2017-07-13 15:46:31', 1, 1),
(25, 25, '2017-07-13 15:46:31', 1, 1),
(26, 26, '2017-07-13 15:46:31', 1, 1),
(27, 27, '2017-07-13 15:46:31', 1, 1),
(28, 28, '2017-07-13 15:46:31', 1, 1),
(29, 29, '2017-07-13 15:46:31', 1, 1),
(30, 30, '2017-07-13 15:46:31', 1, 1),
(31, 31, '2017-07-13 15:46:31', 1, 1),
(32, 32, '2017-07-13 15:46:31', 1, 1),
(33, 33, '2017-07-13 15:46:31', 1, 1),
(34, 34, '2017-07-13 15:46:31', 1, 1),
(35, 35, '2017-07-13 15:46:31', 1, 1),
(36, 36, '2017-07-13 15:46:31', 1, 1),
(37, 37, '2017-07-13 15:46:31', 1, 1),
(38, 38, '2017-07-13 15:46:31', 1, 1),
(39, 39, '2017-07-13 15:46:31', 1, 1),
(40, 40, '2017-07-13 15:46:31', 1, 1),
(41, 41, '2017-07-13 15:46:31', 1, 1),
(42, 42, '2017-07-13 15:46:31', 1, 1),
(43, 43, '2017-07-13 15:46:31', 1, 1),
(44, 44, '2017-07-13 15:46:31', 1, 1),
(45, 45, '2017-07-13 15:46:31', 1, 1),
(46, 46, '2017-07-13 15:46:31', 1, 1),
(47, 47, '2017-07-13 15:46:31', 1, 1),
(48, 48, '2017-07-13 15:46:31', 1, 1),
(49, 49, '2017-07-13 15:46:31', 1, 1),
(50, 50, '2017-07-13 15:46:31', 1, 1),
(51, 51, '2017-07-13 15:46:31', 1, 1),
(52, 52, '2017-07-13 15:46:31', 1, 1),
(53, 53, '2017-07-13 15:46:31', 1, 1),
(54, 54, '2017-07-13 15:46:31', 1, 1),
(55, 55, '2017-07-13 15:46:31', 1, 1),
(56, 56, '2017-07-13 15:46:31', 1, 1),
(57, 57, '2017-07-13 15:46:31', 1, 1),
(58, 58, '2017-07-13 15:46:31', 1, 1),
(59, 59, '2017-07-13 15:46:31', 1, 1),
(60, 60, '2017-07-13 15:46:31', 1, 1),
(61, 61, '2017-07-13 15:46:31', 1, 1),
(62, 62, '2017-07-13 15:46:31', 1, 1),
(63, 63, '2017-07-13 15:46:31', 1, 1),
(64, 64, '2017-07-13 15:46:31', 1, 1),
(65, 65, '2017-07-13 15:46:31', 1, 1),
(66, 66, '2017-07-13 15:46:31', 1, 1),
(67, 67, '2017-07-13 15:46:31', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cotizacion`
--

CREATE TABLE `cotizacion` (
  `id` int(11) NOT NULL,
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
  `display` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cotizacion`
--

INSERT INTO `cotizacion` (`id`, `usuario_id`, `sub_total`, `itbis`, `tiene_itbis`, `total`, `fecha_creada`, `cliente_id`, `estado`, `nota`, `porciente_itbis`, `usuario_id_anulado`, `comentario_anulado`, `display`) VALUES
(1, 1, '24.00', '0.00', 0, '24.00', '2017-03-11 20:31:01', 1, 'pendiente', NULL, NULL, NULL, NULL, 1),
(2, 1, '69.00', '0.00', 0, '69.00', '2017-03-11 20:33:38', 1, 'pendiente', NULL, NULL, NULL, NULL, 1),
(3, 1, '112.00', '0.00', 0, '112.00', '2017-03-13 13:38:49', 2, 'pendiente', NULL, NULL, NULL, NULL, 1),
(4, 1, '13.00', '0.00', 0, '13.00', '2017-03-23 14:16:13', 1, 'pendiente', NULL, NULL, NULL, NULL, 1),
(5, 1, '250.00', '0.00', 0, '250.00', '2017-03-23 14:30:57', 3, 'pendiente', NULL, NULL, NULL, NULL, 1),
(6, 1, '0.00', '0.00', 0, '0.00', '2017-07-03 17:27:39', 5, 'pendiente', '', NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cotizacion_detalle`
--

CREATE TABLE `cotizacion_detalle` (
  `id` int(11) NOT NULL,
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
  `cotizacion_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cotizacion_detalle`
--

INSERT INTO `cotizacion_detalle` (`id`, `usuario_id`, `nombre`, `precio`, `cantidad`, `total`, `fecha_creada`, `producto_inventariado_id`, `cliente_id`, `estado`, `usuario_id_anulado`, `comentario_anulado`, `display`, `cotizacion_id`) VALUES
(4, 1, 'as', '33.00', 23, '759.00', '2017-03-11 20:31:16', NULL, 1, 'pendiente', NULL, NULL, 1, 1),
(5, 1, 'cxz', '12.00', 2, '24.00', '2017-03-11 20:31:16', NULL, 1, 'pendiente', NULL, NULL, 1, 1),
(6, 1, 'sd', '43.00', 2, '86.00', '2017-03-11 20:33:38', NULL, 1, 'pendiente', NULL, NULL, 1, 2),
(7, 1, 'cds', '23.00', 3, '69.00', '2017-03-11 20:33:38', NULL, 1, 'pendiente', NULL, NULL, 1, 2),
(8, 1, 'producto1', '13.00', 1, '13.00', '2017-03-13 13:38:49', 2, 2, 'pendiente', NULL, NULL, 1, 3),
(9, 1, 'zapatos', '112.00', 1, '112.00', '2017-03-13 13:38:49', 1, 2, 'pendiente', NULL, NULL, 1, 3),
(10, 1, 'zapatos', '112.00', 1, '112.00', '2017-03-23 14:16:13', 1, 1, 'pendiente', NULL, NULL, 1, 4),
(11, 1, 'zapatos', '112.00', 1, '112.00', '2017-03-23 14:16:13', 1, 1, 'pendiente', NULL, NULL, 1, 4),
(12, 1, 'zapatos', '112.00', 1, '112.00', '2017-03-23 14:16:13', 1, 1, 'pendiente', NULL, NULL, 1, 4),
(13, 1, 'producto1', '13.00', 1, '13.00', '2017-03-23 14:16:13', 2, 1, 'pendiente', NULL, NULL, 1, 4),
(14, 1, 'producto1', '13.00', 1, '13.00', '2017-03-23 14:16:13', 2, 1, 'pendiente', NULL, NULL, 1, 4),
(15, 1, 'producto1', '13.00', 1, '13.00', '2017-03-23 14:30:57', 2, 3, 'pendiente', NULL, NULL, 1, 5),
(16, 1, 'zapatos', '112.00', 1, '112.00', '2017-03-23 14:30:58', 1, 3, 'pendiente', NULL, NULL, 1, 5),
(17, 1, 'producto1', '13.00', 1, '13.00', '2017-03-23 14:30:58', 2, 3, 'pendiente', NULL, NULL, 1, 5),
(18, 1, 'zapatos', '112.00', 1, '112.00', '2017-03-23 14:30:58', 1, 3, 'pendiente', NULL, NULL, 1, 5),
(19, 1, 'producto1(2)', '13.00', 1, '13.00', '2017-07-03 17:27:39', 2, 5, 'pendiente', NULL, NULL, 1, 6),
(20, 1, 'reparacion 3(5)', '2000.00', 1, '2000.00', '2017-07-03 17:27:39', 5, 5, 'pendiente', NULL, NULL, 1, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuadre`
--

CREATE TABLE `cuadre` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `fecha_desde` date DEFAULT NULL,
  `fecha_hasta` date DEFAULT NULL,
  `monto_facturado` decimal(20,2) DEFAULT NULL,
  `monto_en_caja` decimal(20,2) DEFAULT NULL,
  `display` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuadre`
--

INSERT INTO `cuadre` (`id`, `usuario_id`, `fecha_creado`, `fecha_desde`, `fecha_hasta`, `monto_facturado`, `monto_en_caja`, `display`) VALUES
(1, 1, '2017-04-05 12:11:27', '2017-03-21', '2017-03-21', '125.00', '5.00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuadre_detalle`
--

CREATE TABLE `cuadre_detalle` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `factura_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  `cuadre_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cuadre_detalle`
--

INSERT INTO `cuadre_detalle` (`id`, `usuario_id`, `fecha_creado`, `factura_id`, `display`, `cuadre_id`) VALUES
(1, 1, '2017-04-05 12:11:28', 3, 1, 1),
(2, 1, '2017-04-05 12:11:28', 5, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `direccion`
--

CREATE TABLE `direccion` (
  `id` int(11) NOT NULL,
  `sector` varchar(50) DEFAULT NULL,
  `provincia` varchar(50) DEFAULT NULL,
  `localidad` text,
  `fecha_creado` datetime DEFAULT NULL,
  `persona_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `direccion`
--

INSERT INTO `direccion` (`id`, `sector`, `provincia`, `localidad`, `fecha_creado`, `persona_id`, `usuario_id`, `display`) VALUES
(1, 'aaaaaa', 'aaaaa', 'aaaaaaa', '2017-03-13 00:13:38', 2, 1, 1),
(2, 'aaaaaa', 'aaaaa', 'aaaaaaa', '2017-03-13 00:13:40', 3, 1, 1),
(3, 'aaaaaa', 'aaaaa', 'aaaaaaa', '2017-03-13 00:13:42', 4, 1, 1),
(4, 'aaaaaa', 'aaaaa', 'aaaaaaa', '2017-03-13 00:13:45', 5, 1, 1),
(5, 'aaaaaa', 'aaaaa', 'aaaaaaa', '2017-03-13 00:13:46', 6, 1, 1),
(6, 'aaaaaa', 'aaaaa', 'aaaaaaa', '2017-03-13 00:13:48', 7, 1, 1),
(7, 'aaaaaa', 'aaaaa', 'aaaaaaa', '2017-03-13 00:13:50', 8, 1, 1),
(8, 'aaaaaa', 'aaaaa', 'aaaaaaa', '2017-03-13 00:13:52', 9, 1, 1),
(9, 'aaaaaa', 'aaaaa', 'aaaaaaa', '2017-03-13 00:13:54', 10, 1, 1),
(10, 'aaaaaa', 'aaaaa', 'aaaaaaa', '2017-03-13 00:13:56', 11, 1, 1),
(11, 'aaaaaa', 'aaaaa', 'aaaaaaa', '2017-03-13 00:13:57', 12, 1, 1),
(12, 'aaaaaa', 'aaaaa', 'aaaaaaa', '2017-03-13 00:13:58', 13, 1, 1),
(13, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:18', 14, 1, 1),
(14, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:20', 15, 1, 1),
(15, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:22', 16, 1, 1),
(16, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:23', 17, 1, 1),
(17, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:25', 18, 1, 1),
(18, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:25', 19, 1, 1),
(19, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:26', 20, 1, 1),
(20, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:28', 21, 1, 1),
(21, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:29', 22, 1, 1),
(22, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:30', 23, 1, 1),
(23, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:31', 24, 1, 1),
(24, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:32', 25, 1, 1),
(25, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:33', 26, 1, 1),
(26, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:34', 27, 1, 1),
(27, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:35', 28, 1, 1),
(28, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:37', 29, 1, 1),
(29, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:38', 30, 1, 1),
(30, 'aaaaa', 'aaaaa', 'aaaaaa', '2017-03-13 00:17:39', 31, 1, 1),
(31, 'aaaaa', 'aaaa', 'aaaaaa', '2017-03-13 00:25:02', 32, 1, 1),
(32, 'bbbbb', 'bbbb', 'bbbb', '2017-03-17 19:55:46', 33, 1, 1),
(33, 'la canita', 'sanjuan', 'k. prueba', '2017-07-01 14:08:27', 34, 1, 1),
(34, 'sector', 'provincia', 'direccion x', '2017-07-01 14:17:36', 35, 1, 1),
(35, 'sector', 'provincia', 'direccion cliente', '2017-07-01 14:38:14', 36, 1, 1),
(36, 'sector1', 'provincia1', 'direccion1', '2017-07-01 14:42:12', 37, 1, 1),
(37, 'sector', 'provincia', 'direccion', '2017-07-01 20:44:13', 38, 1, 1),
(38, 'zz', 'zz', 'zzz', '2017-07-09 14:26:57', 39, 1, 1),
(39, 'hh', 'hh', 'hh', '2017-07-13 15:46:31', 40, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `email`
--

CREATE TABLE `email` (
  `id` int(11) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `persona_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `email`
--

INSERT INTO `email` (`id`, `email`, `fecha_creado`, `persona_id`, `usuario_id`, `display`) VALUES
(1, 'eudy@gmail.com', '2017-03-12 19:37:21', 1, 1, 1),
(2, 'eudy1990@gmail.com', '2017-03-12 19:37:35', 1, 1, 1),
(3, 'eudy90@gmail.com', '2017-03-12 19:37:48', 1, 1, 1),
(4, 'aaaaaa', '2017-03-13 00:17:18', 14, 1, 1),
(5, 'aaaaaa', '2017-03-13 00:17:20', 15, 1, 1),
(6, 'aaaaaa', '2017-03-13 00:17:22', 16, 1, 1),
(7, 'aaaaaa', '2017-03-13 00:17:23', 17, 1, 1),
(8, 'aaaaaa', '2017-03-13 00:17:24', 18, 1, 1),
(9, 'aaaaaa', '2017-03-13 00:17:25', 19, 1, 1),
(10, 'aaaaaa', '2017-03-13 00:17:26', 20, 1, 1),
(11, 'aaaaaa', '2017-03-13 00:17:28', 21, 1, 1),
(12, 'aaaaaa', '2017-03-13 00:17:29', 22, 1, 1),
(13, 'aaaaaa', '2017-03-13 00:17:30', 23, 1, 1),
(14, 'aaaaaa', '2017-03-13 00:17:31', 24, 1, 1),
(15, 'aaaaaa', '2017-03-13 00:17:32', 25, 1, 1),
(16, 'aaaaaa', '2017-03-13 00:17:33', 26, 1, 1),
(17, 'aaaaaa', '2017-03-13 00:17:34', 27, 1, 1),
(18, 'aaaaaa', '2017-03-13 00:17:35', 28, 1, 1),
(19, 'aaaaaa', '2017-03-13 00:17:36', 29, 1, 1),
(20, 'aaaaaa', '2017-03-13 00:17:38', 30, 1, 1),
(21, 'aaaaaa', '2017-03-13 00:17:39', 31, 1, 1),
(22, 'aaaaaaa', '2017-03-13 00:25:02', 32, 1, 1),
(23, 'bbbb', '2017-03-17 19:55:46', 33, 1, 1),
(24, 'eudy@gmail.com', '2017-07-01 14:08:26', 34, 1, 1),
(25, 'rafaul@gmail.com', '2017-07-01 14:17:36', 35, 1, 1),
(26, 'ramirez@gmail.com', '2017-07-01 14:38:14', 36, 1, 1),
(27, 'amauris@gmail.com', '2017-07-01 14:42:12', 37, 1, 1),
(28, 'miguel@gmail.com', '2017-07-01 20:44:13', 38, 1, 1),
(29, 'zz', '2017-07-09 14:26:57', 39, 1, 1),
(30, 'ghh@jh.com', '2017-07-13 15:46:31', 40, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` int(11) NOT NULL,
  `persona_id` int(11) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `usuario_empleado_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `persona_id`, `fecha_creado`, `usuario_id`, `usuario_empleado_id`, `display`) VALUES
(1, 34, '2017-04-03 14:34:04', 1, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `id` int(11) NOT NULL,
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
  `usuario_id_cuadre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `usuario_id`, `cuadrada`, `sub_total`, `itbis`, `tiene_itbis`, `total`, `numero_comprovante_fiscal`, `fecha_creada`, `cliente_id`, `estado`, `nota`, `porciente_itbis`, `tiene_comprovante_fiscal`, `balance_deuda`, `monto_pagado`, `cambio_efectivo`, `usuario_id_anulado`, `comentario_anulado`, `display`, `fecha_cuadre`, `usuario_id_cuadre`) VALUES
(1, 1, 0, '112.00', '0.00', 0, '112.00', '', '2017-03-21 12:01:17', 1, 'pendiente', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
(2, 1, 0, '13.00', '0.00', 0, '13.00', '', '2017-03-21 12:03:27', 1, 'pendiente', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
(3, 1, 1, '112.00', '0.00', 0, '112.00', '', '2017-03-21 12:06:45', 3, 'saldada', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 1, '2017-04-05 12:11:28', 1),
(4, 1, 0, '13.00', '0.00', 0, '13.00', '', '2017-03-21 12:08:27', 3, 'pendiente', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL),
(5, 1, 1, '13.00', '0.00', 0, '13.00', '', '2017-06-01 13:07:36', 1, 'saldada', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, 1, '2017-04-05 12:11:28', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura_detalle`
--

CREATE TABLE `factura_detalle` (
  `id` int(11) NOT NULL,
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
  `factura_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `factura_detalle`
--

INSERT INTO `factura_detalle` (`id`, `usuario_id`, `nombre`, `precio`, `cantidad`, `total`, `fecha_creada`, `producto_inventariado_id`, `cliente_id`, `estado`, `usuario_id_anulado`, `comentario_anulado`, `display`, `factura_id`) VALUES
(1, 1, 'producto1', '13.00', 1, '13.00', '2017-03-21 12:06:12', 2, 1, 'pendiente', NULL, NULL, 1, 2),
(2, 1, 'producto1', '13.00', 1, '13.00', '2017-03-21 12:06:45', 2, 3, 'pendiente', NULL, NULL, 1, 3),
(3, 1, 'zapatos', '112.00', 1, '112.00', '2017-03-21 12:06:45', 1, 3, 'pendiente', NULL, NULL, 1, 3),
(4, 1, 'zapatos', '112.00', 1, '112.00', '2017-03-21 12:08:28', 1, 3, 'pendiente', NULL, NULL, 1, 4),
(5, 1, 'producto1', '13.00', 1, '13.00', '2017-03-21 12:08:28', 2, 3, 'pendiente', NULL, NULL, 1, 4),
(6, 1, 'producto1', '13.00', 1, '13.00', '2017-03-21 13:07:36', 2, 1, 'pendiente', NULL, NULL, 1, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario_reparaciones`
--

CREATE TABLE `inventario_reparaciones` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `nombre` varchar(250) DEFAULT NULL,
  `cantidad_compada` int(11) DEFAULT NULL,
  `precio_compra` decimal(20,2) DEFAULT NULL,
  `precio_venta` decimal(20,2) DEFAULT NULL,
  `display` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nota_cliente`
--

CREATE TABLE `nota_cliente` (
  `id` int(11) NOT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `monto_pagado` decimal(20,2) DEFAULT NULL,
  `balance_anterior` decimal(20,2) DEFAULT NULL,
  `balance_despues_del_pago` decimal(20,2) DEFAULT NULL,
  `cambio` decimal(20,2) DEFAULT NULL,
  `tipo_pago` enum('efectivo','cheque','tarjeta','transferencia') DEFAULT NULL,
  `factura_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago_nomina_empleado`
--

CREATE TABLE `pago_nomina_empleado` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `monto_pagar_al_empleado` decimal(20,2) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  `empleado_id` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `mes` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pago_nomina_empleado`
--

INSERT INTO `pago_nomina_empleado` (`id`, `usuario_id`, `fecha_creado`, `monto_pagar_al_empleado`, `display`, `empleado_id`, `fecha`, `mes`) VALUES
(1, 1, '2017-04-04 14:30:16', '32.00', 1, 1, '2017-04-04', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT ' ',
  `apellido` varchar(100) DEFAULT ' ',
  `cedula` varchar(30) DEFAULT ' ',
  `fecha_nacimiento` date DEFAULT NULL,
  `sexo` enum('masculino','femenino') DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1',
  `rnc` varchar(100) DEFAULT ' '
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `nombre`, `apellido`, `cedula`, `fecha_nacimiento`, `sexo`, `fecha_creado`, `usuario_id`, `display`, `rnc`)
VALUES 
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_inventariado`
--

CREATE TABLE `producto_inventariado` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `nombre` varchar(250) DEFAULT NULL,
  `cantidad_comprada` int(11) DEFAULT '0',
  `precio_compra` decimal(20,2) DEFAULT '0.00',
  `precio_venta` decimal(20,2) DEFAULT '0.00',
  `display` int(1) DEFAULT '1',
  `fecha_creado` datetime DEFAULT NULL,
  `tipo_producto` enum('venta_producto','repacion_producto') DEFAULT 'venta_producto'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto_inventariado`
--

INSERT INTO `producto_inventariado` (`id`, `usuario_id`, `nombre`, `cantidad_comprada`, `precio_compra`, `precio_venta`, `display`, `fecha_creado`, `tipo_producto`) VALUES
(1, 1, 'zapatos', 0, '0.00', '112.00', 1, '2017-03-13 12:00:16', 'venta_producto'),
(2, 1, 'producto1', 0, '0.00', '13.00', 1, '2017-03-13 12:11:52', 'venta_producto'),
(3, 1, 'reparacion producto', 23, '12.00', '123.00', 1, '2017-03-24 20:39:31', 'repacion_producto'),
(4, 1, 'reparacion 1', 20, '9.00', '10.00', 1, '2017-03-25 11:07:18', 'repacion_producto'),
(5, 1, 'reparacion 3', 90, '10.00', '2000.00', 1, '2017-07-01 18:03:26', 'venta_producto'),
(6, 1, 'reparacion4', 1000, '20.00', '800.00', 1, '2017-07-01 18:31:31', 'repacion_producto');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reparacion`
--

CREATE TABLE `reparacion` (
  `id` int(11) NOT NULL,
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
  `total` decimal(20,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `reparacion`
--

INSERT INTO `reparacion` (`id`, `usuario_id`, `sub_total`, `fecha_creada`, `cliente_id`, `estado`, `nota`, `usuario_id_anulado`, `comentario_anulado`, `display`, `abono`, `itbis`, `tiene_itbis`, `total`) VALUES
(1, 1, '13.00', '2017-03-23 09:50:00', 1, 'pendiente', NULL, NULL, NULL, 1, NULL, NULL, 1, NULL),
(2, 1, '645.00', '2017-03-25 12:23:59', 3, 'completo', 'nota generar', NULL, NULL, 1, '2000.00', NULL, 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reparacion_detalle`
--

CREATE TABLE `reparacion_detalle` (
  `id` int(11) NOT NULL,
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
  `nota_completado` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `reparacion_detalle`
--

INSERT INTO `reparacion_detalle` (`id`, `usuario_id`, `nombre`, `precio`, `precio_completado`, `cantidad`, `total`, `fecha_creada`, `inventario_reparaciones_id`, `cliente_id`, `estado`, `nota_incompleto_por`, `usuario_id_incompleto_por`, `nota`, `usuario_id_completado`, `usuario_id_anulado`, `comentario_anulado`, `display`, `reparacion_id`, `producto_inventariado_id`, `nota_procesando`, `usuario_id_procesando`, `fecha_procesado`, `fecha_anulado`, `fecha_incompleto`, `fecha_completado`, `nota_completado`) VALUES
(1, NULL, 'c', '21.00', '0.00', 4, '456.00', NULL, NULL, NULL, 'pendiente', NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 1, 'reparacion producto', '123.00', NULL, 1, '123.00', '2017-03-25 12:24:00', NULL, 3, 'pendiente', NULL, NULL, '', NULL, NULL, NULL, 1, 2, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 1, 'reparacion 1', '10.00', NULL, 1, '10.00', '2017-03-25 12:24:07', NULL, 3, 'pendiente', NULL, NULL, '', NULL, NULL, NULL, 1, 2, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 1, 'reparacion 1', '10.00', NULL, 1, '10.00', '2017-03-25 12:24:13', NULL, 3, 'pendiente', NULL, NULL, '', NULL, NULL, NULL, 1, 2, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 1, 'reparacion 1', '10.00', NULL, 1, '10.00', '2017-03-25 12:24:22', NULL, 3, 'pendiente', NULL, NULL, 'prueba', NULL, NULL, NULL, 1, 2, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 1, 'reparacion producto', '123.00', '12.00', 4, '492.00', '2017-03-25 12:24:27', NULL, 3, 'completado', 'incompleto', NULL, 'este es un 111', 1, NULL, 'anulado', 1, 2, 3, 'procesando', 1, '2017-04-02 11:29:11', NULL, NULL, '2017-04-02 11:29:32', 'completo11');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telephone`
--

CREATE TABLE `telephone` (
  `id` int(11) NOT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `persona_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `tipo_telefono_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `telephone`
--

INSERT INTO `telephone` (`id`, `telephone`, `fecha_creado`, `persona_id`, `usuario_id`, `tipo_telefono_id`, `display`) VALUES
(1, 'aaaa', '2017-03-13 00:17:18', 14, 1, 1, 1),
(2, 'aaaa', '2017-03-13 00:17:20', 15, 1, 1, 1),
(3, 'aaaa', '2017-03-13 00:17:22', 16, 1, 1, 1),
(4, 'aaaa', '2017-03-13 00:17:23', 17, 1, 1, 1),
(5, 'aaaa', '2017-03-13 00:17:24', 18, 1, 1, 1),
(6, 'aaaa', '2017-03-13 00:17:25', 19, 1, 1, 1),
(7, 'aaaa', '2017-03-13 00:17:26', 20, 1, 1, 1),
(8, 'aaaa', '2017-03-13 00:17:28', 21, 1, 1, 1),
(9, 'aaaa', '2017-03-13 00:17:29', 22, 1, 1, 1),
(10, 'aaaa', '2017-03-13 00:17:30', 23, 1, 1, 1),
(11, 'aaaa', '2017-03-13 00:17:31', 24, 1, 1, 1),
(12, 'aaaa', '2017-03-13 00:17:32', 25, 1, 1, 1),
(13, 'aaaa', '2017-03-13 00:17:33', 26, 1, 1, 1),
(14, 'aaaa', '2017-03-13 00:17:34', 27, 1, 1, 1),
(15, 'aaaa', '2017-03-13 00:17:35', 28, 1, 1, 1),
(16, 'aaaa', '2017-03-13 00:17:37', 29, 1, 1, 1),
(17, 'aaaa', '2017-03-13 00:17:38', 30, 1, 1, 1),
(18, 'aaaa', '2017-03-13 00:17:39', 31, 1, 1, 1),
(19, 'aaaa', '2017-03-13 00:25:02', 32, 1, 1, 1),
(20, 'bbbb', '2017-03-17 19:55:46', 33, 1, 1, 1),
(21, '8099088908', '2017-07-01 14:08:27', 34, 1, 1, 1),
(22, '77777777', '2017-07-01 14:17:36', 35, 1, 1, 1),
(23, '4444444', '2017-07-01 14:38:14', 36, 1, 1, 1),
(24, '11111111', '2017-07-01 14:42:12', 37, 1, 1, 1),
(25, '3333333', '2017-07-01 20:44:13', 38, 1, 1, 1),
(26, 'zz', '2017-07-09 14:26:57', 39, 1, 1, 1),
(27, '57768767', '2017-07-13 15:46:31', 40, 1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_telefono`
--

CREATE TABLE `tipo_telefono` (
  `id` int(11) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_telefono`
--

INSERT INTO `tipo_telefono` (`id`, `nombre`, `fecha_creado`, `usuario_id`, `display`) VALUES
(1, 'casa', '2017-03-01 15:27:13', 1, 1),
(2, 'celular', '2017-03-01 15:27:13', 1, 1),
(3, 'oficina', '2017-03-01 15:27:13', 1, 1),
(4, 'whatsapp', '2017-03-01 15:27:13', 1, 1),
(5, 'otro', '2017-03-01 15:27:13', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre_usuario` varchar(100) DEFAULT NULL,
  `clave_usuario` varchar(150) DEFAULT NULL,
  `fecha_creado` datetime DEFAULT NULL,
  `tipo_usuario` enum('admin','cajero','tecnico','supervisor','versatil') DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `display` int(1) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre_usuario`, `clave_usuario`, `fecha_creado`, `tipo_usuario`, `usuario_id`, `display`) VALUES
(1, 'vyr', 'vyr123456@', '2017-03-01 15:26:42', 'admin', 1, 1),
(2, 'prueba', '123', '2017-04-03 14:34:03', 'cajero', 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `persona_id` (`persona_id`);

--
-- Indices de la tabla `cotizacion`
--
ALTER TABLE `cotizacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `usuario_id_anulado` (`usuario_id_anulado`),
  ADD KEY `cliente_id` (`cliente_id`);

--
-- Indices de la tabla `cotizacion_detalle`
--
ALTER TABLE `cotizacion_detalle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `producto_inventariado_id` (`producto_inventariado_id`),
  ADD KEY `usuario_id_anulado` (`usuario_id_anulado`),
  ADD KEY `cotizacion_id` (`cotizacion_id`);

--
-- Indices de la tabla `cuadre`
--
ALTER TABLE `cuadre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `cuadre_detalle`
--
ALTER TABLE `cuadre_detalle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `factura_id` (`factura_id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `cuadre_id` (`cuadre_id`);

--
-- Indices de la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `persona_id` (`persona_id`);

--
-- Indices de la tabla `email`
--
ALTER TABLE `email`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `persona_id` (`persona_id`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `usuario_empleado_id` (`usuario_empleado_id`),
  ADD KEY `persona_id` (`persona_id`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `usuario_id_anulado` (`usuario_id_anulado`),
  ADD KEY `cliente_id` (`cliente_id`);

--
-- Indices de la tabla `factura_detalle`
--
ALTER TABLE `factura_detalle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `producto_inventariado_id` (`producto_inventariado_id`),
  ADD KEY `usuario_id_anulado` (`usuario_id_anulado`),
  ADD KEY `factura_id` (`factura_id`);

--
-- Indices de la tabla `inventario_reparaciones`
--
ALTER TABLE `inventario_reparaciones`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `nota_cliente`
--
ALTER TABLE `nota_cliente`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `cliente_id` (`cliente_id`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `factura_id` (`factura_id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `pago_nomina_empleado`
--
ALTER TABLE `pago_nomina_empleado`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `empleado_id` (`empleado_id`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `producto_inventariado`
--
ALTER TABLE `producto_inventariado`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `reparacion`
--
ALTER TABLE `reparacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `usuario_id_anulado` (`usuario_id_anulado`),
  ADD KEY `cliente_id` (`cliente_id`);

--
-- Indices de la tabla `reparacion_detalle`
--
ALTER TABLE `reparacion_detalle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `usuario_id_completado` (`usuario_id_completado`),
  ADD KEY `usuario_id_incompleto_por` (`usuario_id_incompleto_por`),
  ADD KEY `inventario_reparaciones_id` (`inventario_reparaciones_id`),
  ADD KEY `reparacion_id` (`reparacion_id`),
  ADD KEY `producto_inventariado_id` (`producto_inventariado_id`),
  ADD KEY `usuario_id_procesando` (`usuario_id_procesando`);

--
-- Indices de la tabla `telephone`
--
ALTER TABLE `telephone`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`),
  ADD KEY `persona_id` (`persona_id`),
  ADD KEY `tipo_telefono_id` (`tipo_telefono_id`);

--
-- Indices de la tabla `tipo_telefono`
--
ALTER TABLE `tipo_telefono`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `cotizacion`
--
ALTER TABLE `cotizacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `cotizacion_detalle`
--
ALTER TABLE `cotizacion_detalle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT de la tabla `cuadre`
--
ALTER TABLE `cuadre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `cuadre_detalle`
--
ALTER TABLE `cuadre_detalle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `direccion`
--
ALTER TABLE `direccion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT de la tabla `email`
--
ALTER TABLE `email`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `factura_detalle`
--
ALTER TABLE `factura_detalle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `inventario_reparaciones`
--
ALTER TABLE `inventario_reparaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `nota_cliente`
--
ALTER TABLE `nota_cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `pago_nomina_empleado`
--
ALTER TABLE `pago_nomina_empleado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;
--
-- AUTO_INCREMENT de la tabla `producto_inventariado`
--
ALTER TABLE `producto_inventariado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `reparacion`
--
ALTER TABLE `reparacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `reparacion_detalle`
--
ALTER TABLE `reparacion_detalle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `telephone`
--
ALTER TABLE `telephone`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT de la tabla `tipo_telefono`
--
ALTER TABLE `tipo_telefono`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `cliente_ibfk_2` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `cotizacion`
--
ALTER TABLE `cotizacion`
  ADD CONSTRAINT `cotizacion_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `cotizacion_ibfk_2` FOREIGN KEY (`usuario_id_anulado`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `cotizacion_ibfk_3` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `cotizacion_detalle`
--
ALTER TABLE `cotizacion_detalle`
  ADD CONSTRAINT `cotizacion_detalle_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `cotizacion_detalle_ibfk_2` FOREIGN KEY (`producto_inventariado_id`) REFERENCES `producto_inventariado` (`id`),
  ADD CONSTRAINT `cotizacion_detalle_ibfk_3` FOREIGN KEY (`usuario_id_anulado`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `cotizacion_detalle_ibfk_4` FOREIGN KEY (`cotizacion_id`) REFERENCES `cotizacion` (`id`);

--
-- Filtros para la tabla `cuadre`
--
ALTER TABLE `cuadre`
  ADD CONSTRAINT `cuadre_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `cuadre_detalle`
--
ALTER TABLE `cuadre_detalle`
  ADD CONSTRAINT `cuadre_detalle_ibfk_1` FOREIGN KEY (`factura_id`) REFERENCES `factura` (`id`),
  ADD CONSTRAINT `cuadre_detalle_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `cuadre_detalle_ibfk_3` FOREIGN KEY (`cuadre_id`) REFERENCES `cuadre` (`id`);

--
-- Filtros para la tabla `direccion`
--
ALTER TABLE `direccion`
  ADD CONSTRAINT `direccion_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `direccion_ibfk_2` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `email`
--
ALTER TABLE `email`
  ADD CONSTRAINT `email_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `email_ibfk_2` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `empleado_ibfk_2` FOREIGN KEY (`usuario_empleado_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `empleado_ibfk_3` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `factura_ibfk_2` FOREIGN KEY (`usuario_id_anulado`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `factura_ibfk_3` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `factura_detalle`
--
ALTER TABLE `factura_detalle`
  ADD CONSTRAINT `factura_detalle_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `factura_detalle_ibfk_2` FOREIGN KEY (`producto_inventariado_id`) REFERENCES `producto_inventariado` (`id`),
  ADD CONSTRAINT `factura_detalle_ibfk_3` FOREIGN KEY (`usuario_id_anulado`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `factura_detalle_ibfk_4` FOREIGN KEY (`factura_id`) REFERENCES `factura` (`id`);

--
-- Filtros para la tabla `inventario_reparaciones`
--
ALTER TABLE `inventario_reparaciones`
  ADD CONSTRAINT `inventario_reparaciones_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `nota_cliente`
--
ALTER TABLE `nota_cliente`
  ADD CONSTRAINT `nota_cliente_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `nota_cliente_ibfk_2` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD CONSTRAINT `pagos_ibfk_1` FOREIGN KEY (`factura_id`) REFERENCES `factura` (`id`),
  ADD CONSTRAINT `pagos_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `pago_nomina_empleado`
--
ALTER TABLE `pago_nomina_empleado`
  ADD CONSTRAINT `pago_nomina_empleado_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `pago_nomina_empleado_ibfk_2` FOREIGN KEY (`empleado_id`) REFERENCES `empleado` (`id`);

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `producto_inventariado`
--
ALTER TABLE `producto_inventariado`
  ADD CONSTRAINT `producto_inventariado_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `reparacion`
--
ALTER TABLE `reparacion`
  ADD CONSTRAINT `reparacion_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `reparacion_ibfk_2` FOREIGN KEY (`usuario_id_anulado`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `reparacion_ibfk_3` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `reparacion_detalle`
--
ALTER TABLE `reparacion_detalle`
  ADD CONSTRAINT `reparacion_detalle_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `reparacion_detalle_ibfk_2` FOREIGN KEY (`usuario_id_completado`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `reparacion_detalle_ibfk_3` FOREIGN KEY (`usuario_id_incompleto_por`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `reparacion_detalle_ibfk_4` FOREIGN KEY (`inventario_reparaciones_id`) REFERENCES `inventario_reparaciones` (`id`),
  ADD CONSTRAINT `reparacion_detalle_ibfk_5` FOREIGN KEY (`reparacion_id`) REFERENCES `reparacion` (`id`),
  ADD CONSTRAINT `reparacion_detalle_ibfk_6` FOREIGN KEY (`producto_inventariado_id`) REFERENCES `producto_inventariado` (`id`),
  ADD CONSTRAINT `reparacion_detalle_ibfk_7` FOREIGN KEY (`usuario_id_procesando`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `telephone`
--
ALTER TABLE `telephone`
  ADD CONSTRAINT `telephone_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `telephone_ibfk_2` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`),
  ADD CONSTRAINT `telephone_ibfk_3` FOREIGN KEY (`tipo_telefono_id`) REFERENCES `tipo_telefono` (`id`);

--
-- Filtros para la tabla `tipo_telefono`
--
ALTER TABLE `tipo_telefono`
  ADD CONSTRAINT `tipo_telefono_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
