-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-05-2024 a las 01:22:47
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `casajuan`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contacto`
--

CREATE TABLE `contacto` (
  `id` int(11) NOT NULL,
  `asunto` varchar(255) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `comentario` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eventos`
--

CREATE TABLE `eventos` (
  `id` int(255) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `descripcion` text NOT NULL,
  `fecha_evento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `menu`
--

CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `tipo` enum('primero','segundo','postre','bebidas') NOT NULL,
  `nombre_plato` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` decimal(5,2) NOT NULL,
  `fecha_menu` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `menu`
--

INSERT INTO `menu` (`id`, `tipo`, `nombre_plato`, `descripcion`, `precio`, `fecha_menu`) VALUES
(1, 'primero', 'Ensalada César', 'Lechuga, pollo a la parrilla, crutones, queso parmesano y aderezo César.', 12.50, '2024-05-20'),
(2, 'segundo', 'Lomo Saltado', 'Trozos de carne de res, cebolla, tomate y papas fritas salteadas en salsa de soja.', 15.90, '2024-05-20'),
(3, 'postre', 'Tiramisú', 'Pastel de queso italiano hecho con bizcochos empapados en café y capas de crema de mascarpone.', 8.75, '2024-05-20'),
(4, 'bebidas', 'Pisco Sour', 'Cóctel peruano preparado con pisco, limón, clara de huevo, jarabe de goma y amargo de angostura.', 10.00, '2024-05-20'),
(5, 'primero', 'Ceviche Mixto', 'Pescado y mariscos frescos marinados en jugo de limón, cebolla roja y ají.', 14.50, '2024-05-21'),
(6, 'segundo', 'Ajiaco Bogotano', 'Sopa colombiana tradicional hecha con papas, pollo, maíz, guascas y crema de leche.', 13.75, '2024-05-21'),
(7, 'postre', 'Flan de Coco', 'Flan suave y cremoso con sabor a coco, cubierto con caramelo dorado.', 7.50, '2024-05-21'),
(8, 'bebidas', 'Aguardiente', 'Bebida alcohólica colombiana destilada de la caña de azúcar.', 9.50, '2024-05-21'),
(9, 'primero', 'Gazpacho Andaluz', 'Sopa fría de tomate, pimiento, pepino, cebolla, ajo, aceite de oliva y vinagre.', 10.00, '2024-05-22'),
(10, 'segundo', 'Paella Valenciana', 'Plato de arroz español con pollo, conejo, judías verdes, garrofón, tomate y azafrán.', 17.50, '2024-05-22'),
(11, 'postre', 'Crema Catalana', 'Postre español similar al flan, pero con una capa de azúcar caramelizado en la parte superior.', 9.25, '2024-05-22'),
(12, 'bebidas', 'Sangría', 'Bebida española hecha con vino tinto, frutas picadas, azúcar y un toque de brandy.', 12.00, '2024-05-22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE `reservas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `numero_personas` int(11) NOT NULL,
  `fecha_reserva` varchar(255) NOT NULL,
  `hora_reserva` varchar(255) NOT NULL,
  `preferencia_ubicacion` varchar(255) NOT NULL,
  `restaurante` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `contrasena` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `contacto`
--
ALTER TABLE `contacto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `contacto`
--
ALTER TABLE `contacto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `eventos`
--
ALTER TABLE `eventos`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `reservas`
--
ALTER TABLE `reservas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
