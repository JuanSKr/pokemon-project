-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-05-2023 a las 02:05:50
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pokemon`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `capturado`
--

CREATE TABLE `capturado` (
  `id_capturado` int(11) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `mote` varchar(15) DEFAULT NULL,
  `equipo` int(11) NOT NULL,
  `vitalidad` int(11) DEFAULT NULL,
  `fertilidad` int(11) DEFAULT NULL,
  `velocidad` int(11) DEFAULT NULL,
  `estamina` int(11) DEFAULT NULL,
  `ataque` int(11) DEFAULT NULL,
  `defensa` int(11) DEFAULT NULL,
  `ataque_especial` int(11) DEFAULT NULL,
  `defensa_especial` int(11) DEFAULT NULL,
  `tipo1` varchar(15) NOT NULL,
  `tipo2` varchar(15) DEFAULT NULL,
  `movimiento1` int(11) DEFAULT NULL,
  `id_entrenador` int(11) DEFAULT NULL,
  `id_pokedex` int(11) DEFAULT NULL,
  `nivel` int(11) DEFAULT NULL,
  `xp` int(11) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `movimiento2` int(11) DEFAULT NULL,
  `movimiento3` int(11) DEFAULT NULL,
  `movimiento4` int(11) DEFAULT NULL,
  `foto_espalda` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `capturado`
--

INSERT INTO `capturado` (`id_capturado`, `nombre`, `mote`, `equipo`, `vitalidad`, `fertilidad`, `velocidad`, `estamina`, `ataque`, `defensa`, `ataque_especial`, `defensa_especial`, `tipo1`, `tipo2`, `movimiento1`, `id_entrenador`, `id_pokedex`, `nivel`, `xp`, `foto`, `movimiento2`, `movimiento3`, `movimiento4`, `foto_espalda`) VALUES
(1234, 'Venonat', '', 1, 130, 5, 45, 90, 55, 50, 40, 55, 'BICHO', 'VENENO', 47, 6, 48, 1, 0, '/img/Gif/venonat.gif', 51, 54, 71, '/img/Gif/venonatespalda.gif'),
(2674, 'Vaporeon', '', 1, 130, 5, 65, 260, 65, 60, 110, 95, 'AGUA', 'null', 23, 6, 134, 1, 0, 'img/Gif/vaporeon.gif', NULL, NULL, NULL, '/img/Gif/vaporeonespalda.gif'),
(7015, 'Starmie', '', 1, 100, 5, 115, 100, 70, 85, 85, 85, 'AGUA', 'PSIQUICO', 47, 6, 121, 1, 0, '/img/Gif/starmie.gif', NULL, NULL, NULL, '/img/Gif/starmieespalda.gif'),
(8310, 'Kabuto', '', 1, 100, 5, 80, 60, 80, 90, 55, 45, 'ROCA', 'AGUA', 12, 6, 140, 1, 0, '/img/Gif/kabuto.gif', NULL, NULL, NULL, '/img/Gif/kabutoespalda.gif'),
(8939, 'Hypno', '', 1, 170, 5, 67, 140, 73, 70, 73, 115, 'PSIQUICO', 'null', 67, 6, 97, 1, 0, 'img/Gif/hypno.gif', NULL, NULL, NULL, '/img/Gif/hypnoespalda.gif'),
(9579, 'Hitmonchan', '', 1, 100, 5, 75, 100, 105, 79, 35, 110, 'LUCHA', 'null', 64, 6, 107, 1, 0, '/img/Gif/hitmonchan.gif', NULL, NULL, NULL, '/img/Gif/hitmonchanespalda.gif');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrenador`
--

CREATE TABLE `entrenador` (
  `id_entrenador` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `dinero` decimal(20,2) NOT NULL,
  `id_mochila` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `entrenador`
--

INSERT INTO `entrenador` (`id_entrenador`, `nombre`, `pass`, `dinero`, `id_mochila`) VALUES
(1, 'Test', 'asd', 500.00, NULL),
(2, '1212', '1212', 500.00, NULL),
(3, 'asda', 'asd', 500.00, NULL),
(4, 'asd', 'asd', 500.00, NULL),
(5, 'ggwg', 'gewgwe', 500.00, NULL),
(6, 'El pepe', '1234', 500.00, NULL),
(7, 'Juan', '1234', 500.00, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mochila`
--

CREATE TABLE `mochila` (
  `id_mochila` int(11) NOT NULL,
  `id_objeto` int(11) DEFAULT NULL,
  `id_entrenador` int(11) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mochila`
--

INSERT INTO `mochila` (`id_mochila`, `id_objeto`, `id_entrenador`, `cantidad`) VALUES
(1, NULL, 1, NULL),
(2, NULL, 2, NULL),
(3, NULL, 3, NULL),
(4, NULL, 4, NULL),
(5, NULL, 5, NULL),
(6, NULL, 6, NULL),
(7, NULL, 7, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimiento`
--

CREATE TABLE `movimiento` (
  `id_movimiento` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `potencia` int(11) DEFAULT NULL,
  `tipo_movimiento` varchar(20) DEFAULT NULL,
  `tipo_ataque` varchar(20) DEFAULT NULL,
  `estado` varchar(20) DEFAULT NULL,
  `turno` int(11) DEFAULT NULL,
  `ataque` int(11) DEFAULT NULL,
  `defensa` int(11) DEFAULT NULL,
  `ataque_especial` int(11) DEFAULT NULL,
  `defensa_especial` int(11) DEFAULT NULL,
  `vitalidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movimiento`
--

INSERT INTO `movimiento` (`id_movimiento`, `nombre`, `potencia`, `tipo_movimiento`, `tipo_ataque`, `estado`, `turno`, `ataque`, `defensa`, `ataque_especial`, `defensa_especial`, `vitalidad`) VALUES
(1, 'pistola agua', 40, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'hidrobomba', 110, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, 'burbuja', 20, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, 'rayo burbuja', 65, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(5, 'hidropulso', 60, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, 'chorro de agua', 80, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(7, 'cascada', 80, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(8, 'surf', 90, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(9, 'hidroariete', 80, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(10, 'pulso dragon', 85, 'ataque', 'dragon', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(11, 'acua jet', 40, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(12, 'acua cola', 90, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(13, 'buceo', 80, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(14, 'hidrocañón', 150, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(15, 'bomba lodo', 90, 'ataque', 'veneno', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(16, 'lanzallamas', 90, 'ataque', 'fuego', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(17, 'hoja afilada', 70, 'ataque', 'planta', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(18, 'ventisca', 110, 'ataque', 'hielo', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(19, 'doble golpe', 35, 'ataque', 'normal', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(20, 'vendetta', 60, 'ataque', 'siniestro', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(21, 'golpe bajo', 65, 'ataque', 'siniestro', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(22, 'onda certera', 60, 'ataque', 'lucha', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(23, 'roca afilada', 100, 'ataque', 'roca', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(24, 'onda trueno', 90, 'ataque', 'electrico', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(25, 'espada santa', 75, 'ataque', 'psiquico', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(26, 'patada salto', 85, 'ataque', 'lucha', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(27, 'hidro bomba', 110, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(28, 'caída libre', 60, 'ataque', 'volador', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(29, 'patada salto', 70, 'ataque', 'normal', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(30, 'avalancha', 75, 'ataque', 'roca', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(31, 'latigazo', 60, 'ataque', 'normal', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(32, 'bola sombra', 80, 'ataque', 'fantasma', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(33, 'ataque aereo', 80, 'ataque', 'volador', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(34, 'carga', 50, 'ataque', 'normal', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(35, 'rayo hielo', 90, 'ataque', 'hielo', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(36, 'poder pasado', 60, 'ataque', 'normal', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(37, 'puño dinámico', 100, 'ataque', 'lucha', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(38, 'pulso umbrío', 80, 'ataque', 'siniestro', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(39, 'psicoataque', 65, 'ataque', 'psiquico', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(40, 'fuego cruzado', 80, 'ataque', 'fuego', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(41, 'chorro de agua', 80, 'ataque', 'agua', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(42, 'hoja afilada', 70, 'ataque', 'planta', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(43, 'puño hielo', 75, 'ataque', 'hielo', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(44, 'terremoto', 100, 'ataque', 'tierra', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(45, 'bomba lodo', 90, 'ataque', 'tierra', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(46, 'foco resplandor', 80, 'ataque', 'fuego', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(47, 'ráfaga', 60, 'ataque', 'normal', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(48, 'ventisca', 110, 'ataque', 'hielo', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(49, 'puño fuego', 75, 'ataque', 'fuego', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(50, 'ráfaga', 60, 'ataque', 'viento', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(51, 'paz mental', NULL, 'mejora', 'psiquico', NULL, 3, 0, 0, 15, 0, NULL),
(52, 'doble equipo', NULL, 'mejora', 'normal', NULL, 1, 0, 10, 0, 0, NULL),
(53, 'recuperación', NULL, 'mejora', 'normal', NULL, 2, 0, 0, 0, 0, 15),
(54, 'protección', NULL, 'mejora', 'normal', NULL, 2, 0, 20, 0, 0, NULL),
(55, 'defensa férrea', NULL, 'mejora', 'acero', NULL, 3, 0, 25, 0, NULL, NULL),
(56, 'respiro', NULL, 'mejora', 'dragon', NULL, 1, 0, 0, 0, 0, 20),
(57, 'foco energético', NULL, 'mejora', 'electrico', NULL, 2, 0, 0, 35, 0, NULL),
(58, 'barrera', NULL, 'mejora', 'psiquico', NULL, 1, 0, 10, 0, 0, NULL),
(59, 'rayo de luz', NULL, 'mejora', 'normal', NULL, 3, 0, 0, 15, 0, NULL),
(60, 'supervivencia', NULL, 'mejora', 'normal', NULL, 2, 0, 0, 0, 0, 10),
(63, 'gruñido', 0, 'estado', 'normal', 'confuso', 2, NULL, NULL, NULL, NULL, NULL),
(64, 'telépata', NULL, 'estado', 'psiquico', 'confuso', 1, NULL, NULL, NULL, NULL, NULL),
(67, 'niebla', NULL, 'estado', 'hada', 'confuso', 2, NULL, NULL, NULL, NULL, NULL),
(69, 'golpe bajo', NULL, 'estado', 'siniestro', 'confuso', 2, NULL, NULL, NULL, NULL, NULL),
(71, 'envenenamiento', NULL, 'estado', 'veneno', 'envenenado', 2, NULL, NULL, NULL, NULL, NULL),
(72, 'curseador', NULL, 'estado', 'fantasma', 'maldito', 2, NULL, NULL, NULL, NULL, NULL),
(77, 'confusion', NULL, 'estado', 'psiquico', 'confuso', 2, NULL, NULL, NULL, NULL, NULL),
(79, 'envenenamiento', NULL, 'estado', 'veneno', 'envenenado', 2, NULL, NULL, NULL, NULL, NULL),
(80, 'envenenamiento grave', NULL, 'estado', 'veneno', 'envenenado', 2, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `objeto`
--

CREATE TABLE `objeto` (
  `id_objeto` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `precio` decimal(5,2) NOT NULL,
  `id_capturado` int(11) DEFAULT NULL,
  `id_entrenador` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pokedex`
--

CREATE TABLE `pokedex` (
  `id_pokedex` int(11) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `vitalidad` int(11) DEFAULT NULL,
  `velocidad` int(11) DEFAULT NULL,
  `estamina` int(11) DEFAULT NULL,
  `ataque` int(11) NOT NULL,
  `defensa` int(11) DEFAULT NULL,
  `ataque_especial` int(11) DEFAULT NULL,
  `defensa_especial` int(11) DEFAULT NULL,
  `tipo1` varchar(15) NOT NULL,
  `tipo2` varchar(15) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `foto_espalda` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pokedex`
--

INSERT INTO `pokedex` (`id_pokedex`, `nombre`, `vitalidad`, `velocidad`, `estamina`, `ataque`, `defensa`, `ataque_especial`, `defensa_especial`, `tipo1`, `tipo2`, `foto`, `foto_espalda`) VALUES
(1, 'Bulbasaur', 100, 110, 95, 70, 70, 85, 85, 'Planta', 'Veneno', '/img/Gif/bulbasaur.gif', '/img/Gif/bulbasaurespalda.gif'),
(2, 'Ivysaur', 100, 90, 165, 80, 80, 100, 100, 'Planta', 'Veneno', '/img/Gif/ivysaur.gif', '/img/Gif/ivysaurespalda.gif'),
(3, 'Venusaur', 100, 70, 185, 100, 100, 120, 120, 'Planta', 'Veneno', '/img/Gif/venusaur.gif', '/img/Gif/venusaurespalda.gif'),
(4, 'Charmander', 100, 140, 135, 85, 70, 80, 80, 'Fuego', NULL, '/img/Gif/charmander.gif', '/img/Gif/charmanderespalda.gif'),
(5, 'Charmeleon', 100, 110, 160, 105, 85, 100, 100, 'Fuego', NULL, '/img/Gif/charmeleon.gif', '/img/Gif/charmeleonespalda.gif'),
(6, 'Charizard', 100, 80, 195, 130, 100, 125, 125, 'Fuego', 'Volador', '/img/Gif/charizard.gif', '/img/Gif/charizardespalda.gif'),
(7, 'Squirtle', 100, 70, 140, 70, 115, 80, 80, 'Agua', NULL, '/img/Gif/squirtle.gif', '/img/Gif/squirtleespalda.gif'),
(8, 'Wartortle', 100, 90, 200, 75, 80, 70, 80, 'Agua', NULL, '/img/Gif/wartortle.gif', '/img/Gif/wartortleespalda.gif'),
(9, 'Blastoise', 100, 80, 125, 95, 100, 85, 105, 'Agua', NULL, '/img/Gif/blastoise.gif', '/img/Gif/blastoiseespalda.gif'),
(10, 'Caterpie', 100, 45, 120, 30, 35, 20, 20, 'Bicho', NULL, '/img/Gif/caterpie.gif', '/img/Gif/caterpieespalda.gif'),
(11, 'Metapod', 100, 30, 120, 20, 55, 25, 25, 'Bicho', NULL, '/img/Gif/metapod.gif', '/img/Gif/metapodespalda.gif'),
(12, 'Butterfree', 100, 70, 120, 45, 50, 90, 80, 'Bicho', 'Volador', '/img/Gif/butterfree.gif', '/img/Gif/butterfreeespalda.gif'),
(13, 'Weedle', 100, 50, 120, 35, 30, 20, 20, 'Bicho', 'Veneno', '/img/Gif/weedle.gif', '/img/Gif/weedleespalda.gif'),
(14, 'Kakuna', 100, 35, 95, 25, 50, 25, 25, 'Bicho', 'Veneno', '/img/Gif/kakuna.gif', '/img/Gif/kakunaespalda.gif'),
(15, 'Beedrill', 100, 75, 125, 80, 40, 45, 80, 'Bicho', 'Veneno', '/img/Gif/beedrill.gif', '/img/Gif/beedrillespalda.gif'),
(16, 'Pidgey', 100, 56, 90, 45, 40, 35, 35, 'Normal', 'Volador', '/img/Gif/pidgey.gif', '/img/Gif/pidgeyespalda.gif'),
(17, 'Pidgeotto', 100, 71, 110, 60, 55, 50, 50, 'Normal', 'Volador', '/img/Gif/pidgeotto.gif', '/img/Gif/pidgeottoespalda.gif'),
(18, 'Pidgeot', 100, 101, 140, 80, 75, 70, 70, 'Normal', 'Volador', '/img/Gif/pidgeot.gif', '/img/Gif/pidgeotespalda.gif'),
(19, 'Rattata', 100, 72, 120, 56, 35, 25, 35, 'Normal', NULL, '/img/Gif/rattata.gif', '/img/Gif/rattataespalda.gif'),
(20, 'Raticate', 100, 97, 120, 81, 60, 50, 70, 'Normal', NULL, '/img/Gif/raticate.gif', '/img/Gif/raticateespalda.gif'),
(21, 'Spearow', 100, 70, 95, 60, 30, 31, 31, 'Normal', 'Volador', '/img/Gif/spearow.gif', '/img/Gif/spearowespalda.gif'),
(22, 'Fearow', 100, 100, 130, 90, 65, 61, 61, 'Normal', 'Volador', '/img/Gif/fearow.gif', '/img/Gif/fearowespalda.gif'),
(23, 'Ekans', 100, 85, 125, 80, 75, 70, 70, 'Veneno', NULL, '/img/Gif/ekans.gif', '/img/Gif/ekansespalda.gif'),
(24, 'Arbok', 100, 100, 135, 95, 100, 80, 80, 'Veneno', NULL, '/img/Gif/arbok.gif', '/img/Gif/arbokespalda.gif'),
(25, 'Pikachu', 100, 120, 125, 75, 70, 100, 70, 'Electrico', NULL, '/img/Gif/pikachu.gif', '/img/Gif/pikachuespalda.gif'),
(26, 'Raichu', 100, 130, 145, 90, 80, 110, 80, 'Electrico', NULL, '/img/Gif/raichu.gif', '/img/Gif/raichuespalda.gif'),
(27, 'Sandshrew', 100, 70, 155, 75, 85, 30, 30, 'Tierra', NULL, '/img/Gif/sandshrew.gif', '/img/Gif/sandshrewespalda.gif'),
(28, 'Sandslash', 100, 95, 120, 100, 110, 55, 55, 'Tierra', NULL, '/img/Gif/sandslash.gif', '/img/Gif/sandslashespalda.gif'),
(29, 'Nidoran♀', 100, 55, 90, 47, 52, 40, 40, 'Veneno', NULL, '/img/Gif/nidoranf.gif', '/img/Gif/nidoranfespalda.gif'),
(30, 'Nidorina', 100, 70, 125, 62, 67, 55, 55, 'Veneno', NULL, '/img/Gif/nidorina.gif', '/img/Gif/nidorinaespalda.gif'),
(31, 'Nidoqueen', 100, 85, 110, 102, 92, 82, 87, 'Veneno', 'Tierra', '/img/Gif/nidoqueen.gif', '/img/Gif/nidoqueenespalda.gif'),
(32, 'Nidoran♂', 100, 50, 95, 57, 40, 40, 40, 'Veneno', NULL, '/img/Gif/nidoran-male.gif', '/img/Gif/nidoran-maleespalda.gif'),
(33, 'Nidorino', 100, 75, 145, 72, 57, 55, 55, 'Veneno', NULL, '/img/Gif/nidorino.gif', '/img/Gif/nidorinoespalda.gif'),
(34, 'Nidoking', 100, 85, 110, 102, 77, 85, 75, 'Veneno', 'Tierra', '/img/Gif/nidoking.gif', '/img/Gif/nidokingespalda.gif'),
(35, 'Clefairy', 100, 60, 135, 45, 48, 60, 65, 'Hada', NULL, '/img/Gif/clefairy.gif', '/img/Gif/clefairyespalda.gif'),
(36, 'Clefable', 100, 85, 140, 70, 73, 95, 90, 'Hada', NULL, '/img/Gif/clefable.gif', '/img/Gif/clefableespalda.gif'),
(37, 'Vulpix', 100, 65, 125, 41, 40, 50, 65, 'Fuego', NULL, '/img/Gif/vulpix.gif', '/img/Gif/vulpixespalda.gif'),
(38, 'Ninetales', 100, 100, 100, 76, 75, 81, 100, 'Fuego', NULL, '/img/Gif/ninetales.gif', '/img/Gif/ninetalesespalda.gif'),
(39, 'Jigglypuff', 100, 20, 120, 45, 20, 25, 25, 'Normal', 'Hada', '/img/Gif/jigglypuff.gif', '/img/Gif/jigglypuffespalda.gif'),
(40, 'Wigglytuff', 100, 45, 190, 70, 45, 85, 50, 'Normal', 'Hada', '/img/Gif/wigglytuff.gif', '/img/Gif/wigglytuffespalda.gif'),
(41, 'Zubat', 100, 85, 100, 45, 35, 30, 40, 'Veneno', 'Volador', '/img/Gif/zubat.gif', '/img/Gif/zubatespalda.gif'),
(42, 'Golbat', 100, 90, 100, 80, 70, 65, 75, 'Veneno', 'Volador', '/img/Gif/golbat.gif', '/img/Gif/golbatespalda.gif'),
(43, 'Oddish', 100, 30, 100, 50, 55, 75, 65, 'Planta', 'Veneno', '/img/Gif/oddish.gif', '/img/Gif/oddishespalda.gif'),
(44, 'Gloom', 100, 40, 100, 65, 70, 85, 75, 'Planta', 'Veneno', '/img/Gif/gloom.gif', '/img/Gif/gloomespalda.gif'),
(45, 'Vileplume', 100, 50, 120, 80, 85, 110, 90, 'Planta', 'Veneno', '/img/Gif/vileplume.gif', '/img/Gif/vileplumeespalda.gif'),
(46, 'Paras', 100, 30, 95, 60, 60, 40, 80, 'Bicho', 'Planta', '/img/Gif/paras.gif', '/img/Gif/parasespalda.gif'),
(47, 'Parasect', 100, 50, 100, 95, 95, 65, 120, 'Bicho', 'Planta', '/img/Gif/parasect.gif', '/img/Gif/parasectespalda.gif'),
(48, 'Venonat', 100, 45, 155, 55, 50, 40, 55, 'Bicho', 'Veneno', '/img/Gif/venonat.gif', '/img/Gif/venonatespalda.gif'),
(49, 'Venomoth', 100, 90, 100, 65, 60, 90, 75, 'Bicho', 'Veneno', '/img/Gif/venomoth.gif', '/img/Gif/venomothespalda.gif'),
(50, 'Diglett', 100, 95, 120, 55, 25, 35, 45, 'Tierra', NULL, '/img/Gif/diglett.gif', '/img/Gif/diglettespalda.gif'),
(51, 'Dugtrio', 100, 120, 150, 100, 50, 50, 70, 'Tierra', NULL, '/img/Gif/dugtrio.gif', '/img/Gif/dugtrioespalda.gif'),
(52, 'Meowth', 100, 90, 125, 45, 35, 40, 40, 'Normal', NULL, '/img/Gif/meowth.gif', '/img/Gif/meowthespalda.gif'),
(53, 'Persian', 100, 115, 100, 70, 60, 65, 65, 'Normal', NULL, '/img/Gif/persian.gif', '/img/Gif/persianespalda.gif'),
(54, 'Psyduck', 100, 65, 120, 70, 70, 75, 75, 'Agua', NULL, '/img/Gif/psyduck.gif', '/img/Gif/psyduckespalda.gif'),
(55, 'Golduck', 100, 85, 165, 85, 85, 95, 95, 'Agua', NULL, '/img/Gif/golduck.gif', '/img/Gif/golduckespalda.gif'),
(56, 'Mankey', 100, 70, 125, 75, 45, 35, 35, 'Lucha', NULL, '/img/Gif/mankey.gif', '/img/Gif/mankeyespalda.gif'),
(57, 'Primeape', 100, 95, 100, 105, 60, 60, 60, 'Lucha', NULL, '/img/Gif/primeape.gif', '/img/Gif/primeapeespalda.gif'),
(58, 'Growlithe', 100, 60, 140, 70, 50, 70, 70, 'Fuego', NULL, '/img/Gif/growlithe.gif', '/img/Gif/growlitheespalda.gif'),
(59, 'Arcanine', 100, 95, 100, 110, 80, 100, 100, 'Fuego', NULL, '/img/Gif/arcanine.gif', '/img/Gif/arcanineespalda.gif'),
(60, 'Poliwag', 100, 50, 120, 40, 40, 40, 40, 'Agua', NULL, '/img/Gif/poliwag.gif', '/img/Gif/poliwagespalda.gif'),
(61, 'Poliwhirl', 100, 70, 125, 65, 65, 65, 65, 'Agua', NULL, '/img/Gif/poliwhirl.gif', '/img/Gif/poliwhirlespalda.gif'),
(62, 'Poliwrath', 100, 90, 145, 85, 95, 70, 70, 'Agua', 'Lucha', '/img/Gif/poliwrath.gif', '/img/Gif/poliwrathespalda.gif'),
(63, 'Abra', 100, 90, 120, 20, 15, 105, 105, 'Psiquico', NULL, '/img/Gif/abra.gif', '/img/Gif/abraespalda.gif'),
(64, 'Kadabra', 100, 105, 135, 50, 45, 120, 120, 'Psiquico', NULL, '/img/Gif/kadabra.gif', '/img/Gif/kadabraespalda.gif'),
(65, 'Alakazam', 100, 120, 95, 95, 55, 135, 135, 'Psiquico', NULL, '/img/Gif/alakazam.gif', '/img/Gif/alakazamespalda.gif'),
(66, 'Machop', 100, 35, 145, 80, 50, 35, 35, 'Lucha', NULL, '/img/Gif/machop.gif', '/img/Gif/machopespalda.gif'),
(67, 'Machoke', 100, 60, 185, 100, 70, 50, 50, 'Lucha', NULL, '/img/Gif/machoke.gif', '/img/Gif/machokeespalda.gif'),
(68, 'Machamp', 100, 85, 200, 130, 80, 65, 65, 'Lucha', NULL, '/img/Gif/machamp.gif', '/img/Gif/machampespalda.gif'),
(69, 'Bellsprout', 100, 60, 155, 75, 35, 70, 35, 'Planta', 'Veneno', '/img/Gif/bellsprout.gif', '/img/Gif/bellsproutespalda.gif'),
(70, 'Weepinbell', 100, 85, 145, 90, 50, 85, 50, 'Planta', 'Veneno', '/img/Gif/weepinbell.gif', '/img/Gif/weepinbellespalda.gif'),
(71, 'Victreebel', 100, 100, 140, 105, 65, 100, 70, 'Planta', 'Veneno', '/img/Gif/victreebel.gif', '/img/Gif/victreebelespalda.gif'),
(72, 'Tentacool', 100, 70, 135, 40, 35, 70, 100, 'Agua', 'Veneno', '/img/Gif/tentacool.gif', '/img/Gif/tentacoolespalda.gif'),
(73, 'Tentacruel', 100, 100, 95, 70, 65, 120, 150, 'Agua', 'Veneno', '/img/Gif/tentacruel.gif', '/img/Gif/tentacruelespalda.gif'),
(74, 'Geodude', 100, 20, 185, 80, 100, 30, 30, 'Roca', 'Tierra', '/img/Gif/geodude.gif', '/img/Gif/geodudeespalda.gif'),
(75, 'Graveler', 100, 45, 130, 95, 115, 45, 45, 'Roca', 'Tierra', '/img/Gif/graveler.gif', '/img/Gif/gravelerespalda.gif'),
(76, 'Golem', 100, 65, 160, 120, 130, 55, 65, 'Roca', 'Tierra', '/img/Gif/golem.gif', '/img/Gif/golemespalda.gif'),
(77, 'Ponyta', 100, 90, 155, 85, 55, 65, 65, 'Fuego', NULL, '/img/Gif/ponyta.gif', '/img/Gif/ponytaespalda.gif'),
(78, 'Rapidash', 100, 105, 100, 100, 70, 80, 80, 'Fuego', NULL, '/img/Gif/rapidash.gif', '/img/Gif/rapidashespalda.gif'),
(79, 'Slowpoke', 100, 15, 120, 65, 65, 40, 40, 'Agua', 'Psiquico', '/img/Gif/slowpoke.gif', '/img/Gif/slowpokeespalda.gif'),
(80, 'Slowbro', 100, 30, 120, 75, 110, 80, 80, 'Agua', 'Psiquico', '/img/Gif/slowbro.gif', '/img/Gif/slowbroespalda.gif'),
(81, 'Magnemite', 100, 45, 110, 35, 70, 95, 55, 'Electrico', 'Acero', '/img/Gif/magnemite.gif', '/img/Gif/magnemiteespalda.gif'),
(82, 'Magneton', 100, 70, 165, 60, 95, 120, 70, 'Electrico', 'Acero', '/img/Gif/magneton.gif', '/img/Gif/magnetonespalda.gif'),
(83, 'Farfetchd', 100, 60, 100, 65, 55, 58, 62, 'Normal', 'Volador', '/img/Gif/farfetchd.gif', '/img/Gif/farfetchdespalda.gif'),
(84, 'Doduo', 100, 100, 120, 85, 45, 35, 35, 'Normal', 'Volador', '/img/Gif/doduo.gif', '/img/Gif/doduoespalda.gif'),
(85, 'Dodrio', 100, 140, 165, 110, 70, 60, 60, 'Normal', 'Volador', '/img/Gif/dodrio.gif', '/img/Gif/dodrioespalda.gif'),
(86, 'Seel', 100, 70, 145, 45, 55, 45, 70, 'Agua', NULL, '/img/Gif/seel.gif', '/img/Gif/seelespalda.gif'),
(87, 'Dewgong', 100, 90, 120, 70, 80, 70, 95, 'Agua', 'Hielo', '/img/Gif/dewgong.gif', '/img/Gif/dewgongespalda.gif'),
(88, 'Grimer', 100, 25, 90, 80, 50, 40, 40, 'Veneno', NULL, '/img/Gif/grimer.gif', '/img/Gif/grimerespalda.gif'),
(89, 'Muk', 100, 20, 120, 105, 75, 65, 100, 'Veneno', NULL, '/img/Gif/muk.gif', '/img/Gif/mukespaldaespalda.gif'),
(90, 'Shellder', 100, 40, 120, 65, 100, 45, 25, 'Agua', NULL, '/img/Gif/shellder.gif', '/img/Gif/shellderespalda.gif'),
(91, 'Cloyster', 100, 70, 100, 95, 180, 85, 45, 'Agua', 'Hielo', '/img/Gif/cloyster.gif', '/img/Gif/cloysterespalda.gif'),
(92, 'Gastly', 100, 80, 140, 35, 30, 100, 100, 'Fantasma', 'Veneno', '/img/Gif/gastly.gif', '/img/Gif/gastlyespalda.gif'),
(93, 'Haunter', 100, 95, 165, 50, 45, 115, 115, 'Fantasma', 'Veneno', '/img/Gif/haunter.gif', '/img/Gif/haunterespalda.gif'),
(94, 'Gengar', 100, 110, 120, 65, 60, 130, 130, 'Fantasma', 'Veneno', '/img/Gif/gengar.gif', '/img/Gif/gengarespalda.gif'),
(95, 'Onix', 100, 70, 165, 45, 160, 30, 45, 'Roca', 'Tierra', '/img/Gif/onix.gif', '/img/Gif/onixespalda.gif'),
(96, 'Drowzee', 100, 35, 125, 48, 45, 43, 90, 'Psiquico', NULL, '/img/Gif/drowzee.gif', '/img/Gif/drowzeeespalda.gif'),
(97, 'Hypno', 100, 67, 140, 73, 70, 73, 115, 'Psiquico', NULL, '/img/Gif/hypno.gif', '/img/Gif/hypnoespalda.gif'),
(98, 'Krabby', 100, 50, 95, 105, 90, 25, 25, 'Agua', NULL, '/img/Gif/krabby.gif', '/img/Gif/krabbyespalda.gif'),
(99, 'Kingler', 100, 75, 175, 130, 115, 50, 50, 'Agua', NULL, '/img/Gif/kingler.gif', '/img/Gif/kinglerespalda.gif'),
(100, 'Voltorb', 100, 100, 110, 80, 80, 80, 80, 'Electrico', NULL, '/img/Gif/voltorb.gif', '/img/Gif/voltorbespalda.gif'),
(101, 'Electrode', 100, 130, 135, 100, 100, 100, 100, 'Electrico', NULL, '/img/Gif/electrode.gif', '/img/Gif/electrodeespalda.gif'),
(102, 'Exeggcute', 100, 55, 120, 40, 60, 60, 60, 'Planta', 'Psiquico', '/img/Gif/exeggcute.gif', '/img/Gif/exeggcuteespalda.gif'),
(103, 'Exeggutor', 100, 75, 155, 95, 85, 125, 75, 'Planta', 'Psiquico', '/img/Gif/exeggutor.gif', '/img/Gif/exeggutorespalda.gif'),
(104, 'Cubone', 100, 35, 100, 50, 95, 40, 50, 'Tierra', NULL, '/img/Gif/cubone.gif', '/img/Gif/cuboneespalda.gif'),
(105, 'Marowak', 100, 45, 130, 80, 110, 50, 80, 'Tierra', NULL, '/img/Gif/marowak.gif', '/img/Gif/marowakespalda.gif'),
(106, 'Hitmonlee', 100, 85, 100, 120, 53, 35, 110, 'Lucha', NULL, '/img/Gif/hitmonlee.gif', '/img/Gif/hitmonleeespalda.gif'),
(107, 'Hitmonchan', 100, 75, 100, 105, 79, 35, 110, 'Lucha', NULL, '/img/Gif/hitmonchan.gif', '/img/Gif/hitmonchanespalda.gif'),
(108, 'Lickitung', 100, 30, 180, 60, 75, 60, 75, 'Normal', NULL, '/img/Gif/lickitung.gif', '/img/Gif/lickitungespalda.gif'),
(109, 'Koffing', 100, 35, 120, 60, 45, 60, 45, 'Veneno', NULL, '/img/Gif/koffing.gif', '/img/Gif/koffingespalda.gif'),
(110, 'Weezing', 100, 60, 130, 90, 120, 85, 70, 'Veneno', NULL, '/img/Gif/weezing.gif', '/img/Gif/weezingespalda.gif'),
(111, 'Rhyhorn', 100, 35, 160, 85, 95, 30, 30, 'Tierra', 'Roca', '/img/Gif/rhyhorn.gif', '/img/Gif/rhyhornespalda.gif'),
(112, 'Rhydon', 100, 40, 210, 130, 120, 45, 45, 'Tierra', 'Roca', '/img/Gif/rhydon.gif', '/img/Gif/rhydonespalda.gif'),
(113, 'Chansey', 100, 50, 250, 5, 5, 35, 105, 'Normal', NULL, '/img/Gif/chansey.gif', '/img/Gif/chanseyespalda.gif'),
(114, 'Tangela', 100, 60, 100, 55, 115, 100, 40, 'Planta', NULL, '/img/Gif/tangela.gif', '/img/Gif/tangelaespalda.gif'),
(115, 'Kangaskhan', 100, 90, 100, 95, 80, 40, 80, 'Normal', NULL, '/img/Gif/kangaskhan.gif', '/img/Gif/kangaskhanespalda.gif'),
(116, 'Horsea', 100, 60, 120, 65, 70, 80, 70, 'Agua', NULL, '/img/Gif/horsea.gif', '/img/Gif/horseaespalda.gif'),
(117, 'Seadra', 100, 85, 100, 95, 95, 95, 95, 'Agua', NULL, '/img/Gif/seadra.gif', '/img/Gif/seadraespalda.gif'),
(118, 'Goldeen', 100, 80, 90, 67, 60, 35, 50, 'Agua', NULL, '/img/Gif/goldeen.gif', '/img/Gif/goldeenespalda.gif'),
(119, 'Seaking', 100, 85, 100, 92, 65, 65, 80, 'Agua', NULL, '/img/Gif/seaking.gif', '/img/Gif/seakingespalda.gif'),
(120, 'Staryu', 100, 85, 120, 45, 55, 70, 55, 'Agua', NULL, '/img/Gif/staryu.gif', '/img/Gif/staryuespalda.gif'),
(121, 'Starmie', 100, 115, 100, 70, 85, 85, 85, 'Agua', 'Psiquico', '/img/Gif/starmie.gif', '/img/Gif/starmieespalda.gif'),
(122, 'Mr. Mime', 100, 90, 145, 45, 65, 100, 120, 'Psiquico', 'Hada', '/img/Gif/mrmime.gif', '/img/Gif/mrmimeespalda.gif'),
(123, 'Scyther', 100, 120, 125, 110, 80, 55, 80, 'Bicho', 'Volador', '/img/Gif/scyther.gif', '/img/Gif/scytherespalda.gif'),
(124, 'Jynx', 100, 95, 130, 50, 35, 95, 95, 'Hielo', 'Psiquico', '/img/Gif/jynx.gif', '/img/Gif/jynxespalda.gif'),
(125, 'Electabuzz', 100, 105, 165, 123, 75, 85, 85, 'Electrico', NULL, '/img/Gif/electabuzz.gif', '/img/Gif/electabuzzespalda.gif'),
(126, 'Magmar', 100, 93, 155, 95, 100, 85, 85, 'Fuego', NULL, '/img/Gif/magmar.gif', '/img/Gif/magmarespalda.gif'),
(127, 'Pinsir', 100, 85, 100, 125, 100, 55, 70, 'Bicho', NULL, '/img/Gif/pinsir.gif', '/img/Gif/pinsirespalda.gif'),
(128, 'Tauros', 100, 110, 180, 100, 95, 40, 70, 'Normal', NULL, '/img/Gif/tauros.gif', '/img/Gif/taurosespalda.gif'),
(129, 'Magikarp', 100, 80, 120, 30, 20, 10, 20, 'Agua', NULL, '/img/Gif/magikarp.gif', '/img/Gif/magikarpespalda.gif'),
(130, 'Gyarados', 100, 100, 220, 125, 79, 81, 100, 'Agua', 'Volador', '/img/Gif/gyarados.gif', '/img/Gif/gyaradosespalda.gif'),
(131, 'Lapras', 100, 60, 260, 85, 80, 85, 95, 'Agua', 'Hielo', '/img/Gif/lapras.gif', '/img/Gif/laprasespalda.gif'),
(132, 'Ditto', 100, 48, 140, 48, 48, 48, 48, 'Normal', NULL, '/img/Gif/ditto.gif', '/img/Gif/dittoespalda.gif'),
(133, 'Eevee', 100, 55, 135, 55, 50, 65, 65, 'Normal', NULL, '/img/Gif/eevee.gif', '/img/Gif/eeveeespalda.gif'),
(134, 'Vaporeon', 100, 65, 260, 65, 60, 110, 95, 'Agua', NULL, '/img/Gif/vaporeon.gif', '/img/Gif/vaporeonespalda.gif'),
(135, 'Jolteon', 100, 130, 130, 65, 60, 110, 95, 'Electrico', NULL, '/img/Gif/jolteon.gif', '/img/Gif/jolteonespalda.gif'),
(136, 'Flareon', 100, 65, 130, 130, 60, 95, 110, 'Fuego', NULL, '/img/Gif/flareon.gif', '/img/Gif/flareonespalda.gif'),
(137, 'Porygon', 100, 60, 130, 60, 70, 85, 75, 'Normal', NULL, '/img/Gif/porygon.gif', '/img/Gif/porygonespalda.gif'),
(138, 'Omanyte', 100, 35, 120, 40, 100, 90, 55, 'Roca', 'Agua', '/img/Gif/omanyte.gif', '/img/Gif/omanyteespalda.gif'),
(139, 'Omastar', 100, 60, 140, 60, 125, 115, 70, 'Roca', 'Agua', '/img/Gif/omastar.gif', '/img/Gif/omastarespalda.gif'),
(140, 'Kabuto', 100, 80, 120, 80, 90, 55, 45, 'Roca', 'Agua', '/img/Gif/kabuto.gif', '/img/Gif/kabutoespalda.gif'),
(141, 'Kabutops', 100, 115, 120, 115, 105, 70, 80, 'Roca', 'Agua', '/img/Gif/kabutops.gif', '/img/Gif/kabutopsespalda.gif'),
(142, 'Aerodactyl', 100, 130, 160, 105, 65, 60, 75, 'Roca', 'Volador', '/img/Gif/aerodactyl.gif', '/img/Gif/aerodactylespalda.gif'),
(143, 'Snorlax', 100, 30, 320, 110, 65, 65, 110, 'Normal', NULL, '/img/Gif/snorlax.gif', '/img/Gif/snorlaxespalda.gif'),
(144, 'Articuno', 100, 85, 180, 85, 100, 95, 125, 'Hielo', 'Volador', '/img/Gif/articuno.gif', '/img/Gif/articunoespalda.gif'),
(145, 'Zapdos', 100, 100, 180, 90, 85, 125, 90, 'Electrico', 'Volador', '/img/Gif/zapdos.gif', '/img/Gif/zapdosespalda.gif'),
(146, 'Moltres', 100, 90, 180, 100, 90, 125, 85, 'Fuego', 'Volador', '/img/Gif/moltres.gif', '/img/Gif/moltresespalda.gif'),
(147, 'Dratini', 100, 50, 125, 64, 45, 50, 50, 'Dragon', NULL, '/img/Gif/dratini.gif', '/img/Gif/dratiniespalda.gif'),
(148, 'Mew', 100, 100, 200, 100, 100, 100, 100, 'Psiquico', NULL, '/img/Gif/mew.gif', '/img/Gif/mewespalda.gif'),
(149, 'Mewtwo', 100, 200, 250, 200, 150, 200, 180, 'Psiquico', NULL, '/img/Gif/mewtwo.gif', '/img/Gif/mewtwoespalda.gif');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `capturado`
--
ALTER TABLE `capturado`
  ADD PRIMARY KEY (`id_capturado`),
  ADD KEY `movimiento_fk` (`movimiento1`),
  ADD KEY `entrenador_fk` (`id_entrenador`),
  ADD KEY `pokedex_fk` (`id_pokedex`),
  ADD KEY `movimiento2_fk` (`movimiento2`),
  ADD KEY `movimiento3_fk` (`movimiento3`),
  ADD KEY `movimiento4_fk` (`movimiento4`);

--
-- Indices de la tabla `entrenador`
--
ALTER TABLE `entrenador`
  ADD PRIMARY KEY (`id_entrenador`),
  ADD UNIQUE KEY `id_entrenador` (`id_entrenador`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `mochila_fk` (`id_mochila`);

--
-- Indices de la tabla `mochila`
--
ALTER TABLE `mochila`
  ADD PRIMARY KEY (`id_mochila`),
  ADD UNIQUE KEY `id_mochila` (`id_mochila`),
  ADD KEY `objeto_pk` (`id_objeto`),
  ADD KEY `entrenador_pk` (`id_entrenador`);

--
-- Indices de la tabla `movimiento`
--
ALTER TABLE `movimiento`
  ADD PRIMARY KEY (`id_movimiento`),
  ADD UNIQUE KEY `id_movimiento` (`id_movimiento`);

--
-- Indices de la tabla `objeto`
--
ALTER TABLE `objeto`
  ADD PRIMARY KEY (`id_objeto`),
  ADD UNIQUE KEY `id_objeto` (`id_objeto`),
  ADD KEY `capturado_fk` (`id_capturado`);

--
-- Indices de la tabla `pokedex`
--
ALTER TABLE `pokedex`
  ADD PRIMARY KEY (`id_pokedex`),
  ADD UNIQUE KEY `id_pokedex` (`id_pokedex`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `capturado`
--
ALTER TABLE `capturado`
  ADD CONSTRAINT `entrenador_fk` FOREIGN KEY (`id_entrenador`) REFERENCES `entrenador` (`id_entrenador`),
  ADD CONSTRAINT `movimiento2_fk` FOREIGN KEY (`movimiento2`) REFERENCES `movimiento` (`id_movimiento`),
  ADD CONSTRAINT `movimiento3_fk` FOREIGN KEY (`movimiento3`) REFERENCES `movimiento` (`id_movimiento`),
  ADD CONSTRAINT `movimiento4_fk` FOREIGN KEY (`movimiento4`) REFERENCES `movimiento` (`id_movimiento`),
  ADD CONSTRAINT `movimiento_fk` FOREIGN KEY (`movimiento1`) REFERENCES `movimiento` (`id_movimiento`),
  ADD CONSTRAINT `pokedex_fk` FOREIGN KEY (`id_pokedex`) REFERENCES `pokedex` (`id_pokedex`);

--
-- Filtros para la tabla `entrenador`
--
ALTER TABLE `entrenador`
  ADD CONSTRAINT `mochila_fk` FOREIGN KEY (`id_mochila`) REFERENCES `mochila` (`id_mochila`);

--
-- Filtros para la tabla `mochila`
--
ALTER TABLE `mochila`
  ADD CONSTRAINT `entrenador_pk` FOREIGN KEY (`id_entrenador`) REFERENCES `entrenador` (`id_entrenador`),
  ADD CONSTRAINT `objeto1_fk` FOREIGN KEY (`id_objeto`) REFERENCES `objeto` (`id_objeto`),
  ADD CONSTRAINT `objeto_pk` FOREIGN KEY (`id_objeto`) REFERENCES `objeto` (`id_objeto`);

--
-- Filtros para la tabla `objeto`
--
ALTER TABLE `objeto`
  ADD CONSTRAINT `capturado_fk` FOREIGN KEY (`id_capturado`) REFERENCES `capturado` (`id_capturado`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
