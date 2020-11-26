-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-11-2020 a las 03:24:16
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `aivon`
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
(1, 16, 17, 2, 780, 980, 4),
(2, 16, 9, 2, 320, 640, 4),
(3, 16, 1, 1, 1674, 2790, 5),
(8, 17, 17, 1, 390, 490, 2),
(9, 17, 7, 1, 731, 975, 2),
(10, 17, 6, 1, 1500, 2300, 4);

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

--
-- Volcado de datos para la tabla `campaña`
--

INSERT INTO `campaña` (`id_campaña`, `nombre`, `fecha_inicio`, `fecha_fin`, `monto_min`, `monto_max`, `activa`) VALUES
(1, 'NOV-DIC-2020', '2020-11-20', '2020-12-15', 2500, 3000, 1),
(2, 'DIC-ENE-2021', '2020-12-16', '2021-01-10', 2500, 3200, 0),
(3, 'ENE-FEB-2021', '2021-01-11', '2021-02-05', 2900, 3400, 0);

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
  `ganancia` double NOT NULL DEFAULT 0,
  `nivel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(11) NOT NULL,
  `id_revendedor` int(11) NOT NULL,
  `id_campaña` int(11) NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `fecha_entrega` date DEFAULT NULL,
  `fecha_pago` date DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `id_revendedor`, `id_campaña`, `fecha_ingreso`, `fecha_entrega`, `fecha_pago`, `activo`) VALUES
(16, 3, 1, '2020-11-25', '2020-11-25', '2020-11-26', 1),
(17, 1, 1, '2020-11-21', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `uso` varchar(30) NOT NULL,
  `tamaño_cm3` int(11) NOT NULL,
  `costo` double NOT NULL,
  `costo_publico` double NOT NULL,
  `estrellas` int(11) NOT NULL DEFAULT 0,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `nombre`, `uso`, `tamaño_cm3`, `costo`, `costo_publico`, `estrellas`, `activo`) VALUES
(1, 'SERUM NOCTURNO', 'ROSTRO', 210, 1674, 2790, 5, 1),
(2, 'HIDRATANTE PARA MANOS', 'MANOS', 90, 400, 515, 2, 1),
(3, 'JABON DE CEREZA', 'MANOS', 30, 283, 435, 1, 1),
(4, 'DESODORANTE ROLL ON', 'CORPORAL', 160, 186, 310, 2, 1),
(5, 'CREMA MANOS MARACUYA', 'MANOS', 75, 361, 515, 2, 1),
(6, 'KIT POST SOLAR', 'CORPORAL', 120, 1500, 2300, 4, 1),
(7, 'LOCION FACIAL', 'ROSTRO', 120, 731, 975, 2, 1),
(8, 'LOCION PARA BEBE', 'CORPORAL', 120, 945, 1260, 2, 1),
(9, 'SHAMPOO LIMPIEZA PROFUNDA', 'CABELLO', 230, 160, 320, 2, 1),
(10, 'CLASICO FEMENINA', 'CORPORAL', 120, 2000, 2560, 3, 1),
(11, 'LOCIO FRESCOR CASTAÑA', 'CORPORAL', 130, 1000, 1815, 2, 1),
(12, 'LOCION HOMBRE ESSENCE', 'CORPORAL', 150, 2334, 3335, 2, 1),
(13, 'LOCION HOMBRE DESPERTE', 'CORPORAL', 140, 1745, 2685, 2, 1),
(14, 'LOCION HOMBRE QUIMICA HUMO', 'CORPORAL', 150, 2000, 2335, 2, 1),
(15, 'LOCION HOMBRE PAZ E HUMOR', 'CORPORAL', 140, 1800, 2205, 2, 1),
(16, 'LOCION HOMBRE POTENCE', 'CORPORAL', 140, 1800, 2545, 2, 0),
(17, 'SHAMPOO PARAUA', 'CABELLO', 120, 390, 490, 2, 1),
(18, 'SHAMPOO ANTICASPA', 'CABELLO', 230, 380, 425, 2, 1),
(19, 'CREMA DE MANOS DE PAPAYA', 'CORPORAL', 120, 100, 210, 1, 0);

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
(1, 'Ezequiel', 'Albornoz', '36227970', '2664234522', 'eze@gmail.com', 1, 1),
(2, 'Genaro', 'Farias', '26525567', '2664342342', 'genaro@gmail.com', 1, 1),
(3, 'Raul', 'Avaca', '30377673', '2664123312', 'mario@gmail.com', 1, 0);

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
  MODIFY `id_caja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `campaña`
--
ALTER TABLE `campaña`
  MODIFY `id_campaña` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `historico`
--
ALTER TABLE `historico`
  MODIFY `id_historico` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `revendedor`
--
ALTER TABLE `revendedor`
  MODIFY `id_revendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
