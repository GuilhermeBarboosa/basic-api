-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 10-Nov-2023 às 14:03
-- Versão do servidor: 8.0.29
-- versão do PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `basic_database`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `role` varchar(30) NOT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `roles`
--

INSERT INTO `roles` (`id`, `role`) VALUES
    (1, 'ADMIN');

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `name` varchar(80) NOT NULL,
    `cpf` varchar(15) NOT NULL,
    `email` varchar(80) NOT NULL,
    `password` varchar(80) NOT NULL,
    `role` int NOT NULL,
    `actived` tinyint(1) NOT NULL,
    `created` timestamp NOT NULL,
    `updated` timestamp NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `role` (`role`)
    ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`id`, `name`, `cpf`, `email`, `password`, `role`, `actived`, `created`, `updated`) VALUES
    (1, 'Guilherme', '019.733.946-85', 'guilherme@gmail.com', '$2a$10$CC1gMizOXge0iV4fLlaX5eePX/iuH5aoQsoBoOUU.dV4gKzKJUsay', 1, 1, '2023-11-10 14:00:10', '2023-11-10 14:01:22');

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `users`
--
ALTER TABLE `users`
    ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role`) REFERENCES `roles` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
