-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-11-2020 a las 02:38:59
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `aivon2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `caja_pedido`
--

CREATE TABLE `caja_pedido` (
  `id_caja` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad_producto` int(11) NOT NULL,
  `costo_caja` double NOT NULL,
  `costo_caja_publico` double NOT NULL,
  `estrellas_caja` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `caja_pedido`
--

INSERT INTO `caja_pedido` (`id_caja`, `id_pedido`, `id_producto`, `cantidad_producto`, `costo_caja`, `costo_caja_publico`, `estrellas_caja`) VALUES
(1, 1, 1, 5, 1000, 1400, 10),
(2, 1, 2, 2, 650, 750, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campaña`
--

CREATE TABLE `campaña` (
  `id_campaña` int(11) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `activa` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `campaña`
--

INSERT INTO `campaña` (`id_campaña`, `fecha_inicio`, `fecha_fin`, `activa`) VALUES
(1, '2020-11-01', '2020-11-25', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historico`
--

CREATE TABLE `historico` (
  `id_historico` int(11) NOT NULL,
  `id_revendedor` int(11) NOT NULL,
  `id_campaña` int(11) NOT NULL,
  `monto_min` double NOT NULL,
  `monto_max` double NOT NULL,
  `estrellas_campaña_revendedor` int(11) NOT NULL,
  `estado_campaña_revendedor` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `id_revendedor` int(11) NOT NULL,
  `id_campaña` int(11) NOT NULL,
  `fecha_ingreso` datetime NOT NULL,
  `fecha_entrega` date DEFAULT NULL,
  `fecha_pago` date DEFAULT NULL,
  `cantidad_cajas` int(11) DEFAULT NULL,
  `estrellas_pedido` int(11) DEFAULT NULL,
  `pago` tinyint(1) NOT NULL DEFAULT 0,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `id_revendedor`, `id_campaña`, `fecha_ingreso`, `fecha_entrega`, `fecha_pago`, `cantidad_cajas`, `estrellas_pedido`, `pago`, `activo`) VALUES
(1, 1, 0, '2020-11-05 00:00:00', NULL, NULL, NULL, NULL, 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `uso` varchar(30) NOT NULL,
  `tamaño_cm3` int(11) NOT NULL,
  `costo_publico` double NOT NULL,
  `costo` double NOT NULL,
  `estrellas` int(11) NOT NULL DEFAULT 0,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `uso`, `tamaño_cm3`, `costo_publico`, `costo`, `estrellas`, `activo`) VALUES
(1, 'CREMA MIVEA', 'CREMA CORPORAL', 100, 230, 200, 2, 1),
(2, 'DESODORANTE ROXANA', 'DESODORANTE CORPORAL', 240, 350, 300, 5, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `revendedor`
--

CREATE TABLE `revendedor` (
  `id_revendedor` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `email` varchar(40) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `revendedor`
--

INSERT INTO `revendedor` (`id_revendedor`, `nombre`, `apellido`, `dni`, `tel`, `email`, `activo`) VALUES
(1, 'Mario', 'Avaca', '30377673', '2664222979', 'marioraulavaca@gmail.com', 0),
(2, 'Ezequiel', 'Albornoz', '36227970', '1123917575', 'franco.ezequiel@outlook.com', 0),
(3, 'Genaro', 'Farias', '26525567', '2664692950', 'gfsanluis78@gmail.com', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `caja_pedido`
--
ALTER TABLE `caja_pedido`
  ADD PRIMARY KEY (`id_caja`),
  ADD KEY `id_pedido` (`id_pedido`,`id_producto`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `campaña`
--
ALTER TABLE `campaña`
  ADD PRIMARY KEY (`id_campaña`);

--
-- Indices de la tabla `historico`
--
ALTER TABLE `historico`
  ADD PRIMARY KEY (`id_historico`),
  ADD UNIQUE KEY `id_revendedor` (`id_revendedor`,`id_campaña`) USING BTREE,
  ADD KEY `id_campaña` (`id_campaña`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `id_pedido` (`id_pedido`),
  ADD KEY `id_revendedor` (`id_revendedor`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `revendedor`
--
ALTER TABLE `revendedor`
  ADD PRIMARY KEY (`id_revendedor`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `caja_pedido`
--
ALTER TABLE `caja_pedido`
  MODIFY `id_caja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `campaña`
--
ALTER TABLE `campaña`
  MODIFY `id_campaña` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `historico`
--
ALTER TABLE `historico`
  MODIFY `id_historico` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `revendedor`
--
ALTER TABLE `revendedor`
  MODIFY `id_revendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `caja_pedido`
--
ALTER TABLE `caja_pedido`
  ADD CONSTRAINT `caja_pedido_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `caja_pedido_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `historico`
--
ALTER TABLE `historico`
  ADD CONSTRAINT `historico_ibfk_1` FOREIGN KEY (`id_revendedor`) REFERENCES `revendedor` (`id_revendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `historico_ibfk_2` FOREIGN KEY (`id_campaña`) REFERENCES `campaña` (`id_campaña`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_revendedor`) REFERENCES `revendedor` (`id_revendedor`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
