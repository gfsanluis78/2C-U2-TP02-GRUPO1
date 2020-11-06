-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-11-2020 a las 02:27:42
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campaña`
--

CREATE TABLE `campaña` (
  `id_campaña` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `monto_min` double NOT NULL,
  `monto_max` double NOT NULL,
  `activa` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `fecha_ingreso` date NOT NULL DEFAULT current_timestamp(),
  `fecha_entrega` date DEFAULT NULL,
  `fecha_pago` date DEFAULT NULL,
  `cantidad_cajas` int(11) DEFAULT NULL,
  `estrellas_pedido` int(11) DEFAULT NULL,
  `pago` tinyint(1) NOT NULL DEFAULT 0,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(2, 'DESODORANTE ROXANA', 'DESODORANTE CORPORAL', 240, 350, 300, 5, 1),
(3, 'ESMALTE UÑAS', 'MANOS', 10, 100, 70, 2, 1),
(4, 'ESPONJA VEGETAL', 'CORPORAL', 30, 70, 50, 3, 1);

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
  `nivel` int(11) NOT NULL DEFAULT 1,
  `activo` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `revendedor`
--

INSERT INTO `revendedor` (`id_revendedor`, `nombre`, `apellido`, `dni`, `tel`, `email`, `nivel`, `activo`) VALUES
(1, 'Mario', 'Avaca', '30377673', '2664222979', 'marioraulavaca@gmail.com', 1, 0),
(2, 'Ezequiel', 'Albornoz', '36227970', '1123917575', 'franco.ezequiel@outlook.com', 1, 0),
(3, 'Genaro', 'Farias', '26525567', '2664692950', 'gfsanluis78@gmail.com', 1, 1);

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
  ADD UNIQUE KEY `id_revendedor` (`id_revendedor`,`id_campaña`) USING BTREE,
  ADD KEY `id_pedido` (`id_pedido`),
  ADD KEY `pedido_campaña_fk` (`id_campaña`);

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
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
  ADD CONSTRAINT `pedido_campaña_fk` FOREIGN KEY (`id_campaña`) REFERENCES `campaña` (`id_campaña`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pedido_revendedor_fk` FOREIGN KEY (`id_revendedor`) REFERENCES `revendedor` (`id_revendedor`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
