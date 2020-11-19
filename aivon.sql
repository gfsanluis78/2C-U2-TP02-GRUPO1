-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-11-2020 a las 16:24:44
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
(3, 5, 2, 5, 1500, 1750, 25),
(4, 5, 5, 3, 750, 900, 3),
(5, 5, 7, 5, 500, 750, 5),
(11, 12, 7, 5, 500, 1000, 25),
(12, 13, 3, 5, 350, 500, 10),
(13, 13, 4, 2, 100, 140, 6),
(14, 14, 2, 5, 1500, 2000, 50),
(15, 14, 5, 3, 750, 1050, 15),
(16, 14, 7, 5, 500, 1000, 25),
(17, 15, 4, 5, 250, 350, 15),
(18, 15, 6, 3, 600, 750, 3),
(19, 15, 7, 5, 500, 1000, 25);

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
(1, 'Primera', '2020-01-01', '2020-01-26', 1500, 2000, 0),
(2, 'Segunda', '2020-01-27', '2020-02-21', 1500, 2000, 0),
(3, 'Tercera', '2020-02-22', '2020-03-18', 1500, 2000, 0),
(4, 'Cuarta', '2020-03-19', '2020-04-13', 1500, 2000, 0),
(5, 'Quinta', '2020-04-14', '2020-05-09', 1500, 2000, 0),
(6, 'Sexta', '2020-05-10', '2020-06-04', 1500, 2000, 0),
(7, 'Septima', '2020-06-05', '2020-06-30', 1500, 2000, 0),
(8, 'Octava', '2020-07-01', '2020-07-26', 1500, 2000, 0),
(9, 'Novena', '2020-07-27', '2020-08-22', 1500, 2000, 0),
(10, 'Decima', '2020-08-23', '2020-09-17', 1500, 2000, 0),
(11, 'DECIMAPRIMERA', '2020-09-18', '2020-10-13', 1500, 2000, 0),
(12, 'DECIMASEGUNDA', '2020-10-14', '2020-11-08', 1500, 2000, 0),
(13, 'DECIMATERCERA', '2020-11-09', '2020-12-04', 1500, 2000, 1);

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

--
-- Volcado de datos para la tabla `historico`
--

INSERT INTO `historico` (`id_historico`, `id_revendedor`, `id_campaña`, `monto_min`, `monto_max`, `estrellas_campaña_revendedor`, `ganancia`, `nivel`) VALUES
(4, 1, 12, 1500, 2000, 33, 650, 1),
(5, 3, 12, 1500, 2000, 25, 500, 1),
(6, 4, 12, 1500, 2000, 16, 190, 1);

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
(5, 1, 12, '2020-11-06', '2020-11-08', '2020-11-18', 1),
(12, 3, 12, '2020-11-05', '2020-11-06', '2020-11-07', 1),
(13, 4, 12, '2020-10-28', '2020-11-01', '2020-11-03', 1),
(14, 1, 13, '2020-11-15', NULL, NULL, 1),
(15, 3, 13, '2020-11-15', NULL, NULL, 1);

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
(1, 'CREMA MIVEA', 'CREMA CORPORAL', 100, 200, 230, 2, 1),
(2, 'DESODORANTE ROXANA', 'DESODORANTE CORPORAL', 240, 300, 400, 10, 1),
(3, 'ESMALTE UÑAS', 'MANOS', 10, 70, 100, 2, 1),
(4, 'ESPONJA VEGETAL', 'CORPORAL', 30, 50, 70, 3, 1),
(5, 'Jabon liquido Frutilla', 'Cabello', 120, 250, 350, 5, 1),
(6, 'Shampoo ', 'Cabello', 120, 200, 250, 1, 1),
(7, 'SHAMPOO PARA BARBA ', 'Cabello', 120, 100, 200, 5, 1),
(8, 'SUERO REDUCTOR DE ARRUGAS', 'PIEL', 130, 1002, 1305, 5, 1),
(9, 'DESODORANTE CORPORAL', 'PERSONAL', 130, 270, 330, 4, 1),
(10, 'DESODORANTE ANTIMANCHAS', 'PERSONAL', 130, 180, 225, 4, 1),
(11, 'ANTISEÑALES', 'PIEL', 130, 960, 870, 4, 1),
(12, 'JABONES EN BARRA LIMON', 'PIEL', 130, 336, 280, 4, 1),
(13, 'ACEITE CORPORAL ALMENDRAS', 'PIEL', 130, 913, 800, 4, 1),
(14, 'BALSAMO POST BARBA', 'BARBA', 130, 344, 250, 4, 1),
(15, 'MASCARILLA EXFOLIANTE', 'CARA', 130, 590, 389, 4, 1);

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
(1, 'Ezequiel', 'Albornoz', '36227970', '1123917575', 'franco.ezequielq@outlook.com', 1, 1),
(3, 'Mario', 'Avaca', '30377673', '2664222979', 'marioraulavaca@gmail.com', 1, 1),
(4, 'Genaro', 'Farias', '26525567', '2664692950', 'gfsanluis78@gmail.com', 1, 1);

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
  MODIFY `id_caja` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `campaña`
--
ALTER TABLE `campaña`
  MODIFY `id_campaña` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `historico`
--
ALTER TABLE `historico`
  MODIFY `id_historico` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `revendedor`
--
ALTER TABLE `revendedor`
  MODIFY `id_revendedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
