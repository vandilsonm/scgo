-- phpMyAdmin SQL Dump
-- version 3.4.9
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tempo de Geração: 20/03/2012 às 00h03min
-- Versão do Servidor: 5.5.20
-- Versão do PHP: 5.3.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Banco de Dados: `sgc_online`
--
CREATE DATABASE `sgc_online` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `sgc_online`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_arbitragem_arb`
--

CREATE TABLE IF NOT EXISTS `sgc_arbitragem_arb` (
  `ARB_CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `ARB_NOME` varchar(70) NOT NULL,
  `ARB_TIPO` char(2) NOT NULL,
  PRIMARY KEY (`ARB_CODIGO`),
  UNIQUE KEY `ARB_CODIGO` (`ARB_CODIGO`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Extraindo dados da tabela `sgc_arbitragem_arb`
--

INSERT INTO `sgc_arbitragem_arb` (`ARB_CODIGO`, `ARB_NOME`, `ARB_TIPO`) VALUES
(2, 'Jose', 'BD'),
(3, 'Marcos', 'JR'),
(4, 'Jose Pedro', 'BD'),
(5, 'Andre', 'JZ');

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_campeonato_cam`
--

CREATE TABLE IF NOT EXISTS `sgc_campeonato_cam` (
  `CAM_CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `CAM_NOME` varchar(70) NOT NULL,
  `CAM_DESCRICAO` varchar(300) NOT NULL,
  `USU_CODIGO` int(11) NOT NULL,
  `CAM_TIPO` char(1) NOT NULL,
  `CAM_TABELA` char(1) NOT NULL,
  PRIMARY KEY (`CAM_CODIGO`),
  UNIQUE KEY `CAM_CODIGO` (`CAM_CODIGO`),
  KEY `FK_CAM_USU` (`USU_CODIGO`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Extraindo dados da tabela `sgc_campeonato_cam`
--

INSERT INTO `sgc_campeonato_cam` (`CAM_CODIGO`, `CAM_NOME`, `CAM_DESCRICAO`, `USU_CODIGO`, `CAM_TIPO`, `CAM_TABELA`) VALUES
(1, 'Campeonato Japa', 'Descricao', 1, 'P', 'M'),
(2, 'Campeonato da Una Barreiro', 'UNA', 2, 'P', 'M'),
(3, 'Campeonato JAPA mundial', 'Campeonato com todos os times do mundo', 1, 'P', 'M'),
(4, 'Tópicos Especiais', 'Campeonato teste para a matéria de TE', 1, 'P', 'M');

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_cartoes_crt`
--

CREATE TABLE IF NOT EXISTS `sgc_cartoes_crt` (
  `CRT_CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `TIM_CODIGO` int(11) NOT NULL,
  `JGS_CODIGO` int(11) NOT NULL,
  `JOG_CODIGO` int(11) NOT NULL,
  `CRT_TIPO` char(1) NOT NULL,
  PRIMARY KEY (`CRT_CODIGO`),
  UNIQUE KEY `CRT_CODIGO` (`CRT_CODIGO`),
  KEY `FK_CRT_JGS` (`JGS_CODIGO`),
  KEY `FK_CRT_JOG` (`JOG_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_confirmacaojogo_cfj`
--

CREATE TABLE IF NOT EXISTS `sgc_confirmacaojogo_cfj` (
  `JGS_CODIGO` int(11) NOT NULL,
  `JOG_CODIGO` int(11) NOT NULL,
  `CFJ_CONFIRMACAO` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`JGS_CODIGO`,`JOG_CODIGO`),
  KEY `FK_PLC_JOG_CFJ` (`JOG_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_estadio_est`
--

CREATE TABLE IF NOT EXISTS `sgc_estadio_est` (
  `EST_CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `EST_NOME` varchar(50) NOT NULL,
  `EST_END_LOGRADOURO` varchar(70) NOT NULL,
  `EST_END_NUMERO` varchar(10) NOT NULL,
  `EST_END_COMPLEMENTO` varchar(40) DEFAULT NULL,
  `EST_END_BAIRRO` varchar(70) NOT NULL,
  `EST_END_CIDADE` varchar(70) NOT NULL,
  `EST_END_ESTADO` char(2) NOT NULL,
  PRIMARY KEY (`EST_CODIGO`),
  UNIQUE KEY `EST_CODIGO` (`EST_CODIGO`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `sgc_estadio_est`
--

INSERT INTO `sgc_estadio_est` (`EST_CODIGO`, `EST_NOME`, `EST_END_LOGRADOURO`, `EST_END_NUMERO`, `EST_END_COMPLEMENTO`, `EST_END_BAIRRO`, `EST_END_CIDADE`, `EST_END_ESTADO`) VALUES
(1, 'Mineirao', 'adfja', 'lkadkfj', 'kljafj', 'kjadf', 'kjadf', 'MG');

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_jogador`
--

CREATE TABLE IF NOT EXISTS `sgc_jogador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `celular` varchar(14) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_jogador_jog`
--

CREATE TABLE IF NOT EXISTS `sgc_jogador_jog` (
  `JOG_CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `JOG_NOME` varchar(100) NOT NULL,
  `JOG_POSICAO` varchar(40) NOT NULL,
  `JOG_STATUS` char(1) NOT NULL,
  `JOG_TIPO` char(1) NOT NULL,
  `TIM_CODIGO` int(11) NOT NULL,
  `JOG_CELULAR` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`JOG_CODIGO`),
  UNIQUE KEY `JOG_CODIGO` (`JOG_CODIGO`),
  KEY `FK_JOG_TIM` (`TIM_CODIGO`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=35 ;

--
-- Extraindo dados da tabela `sgc_jogador_jog`
--

INSERT INTO `sgc_jogador_jog` (`JOG_CODIGO`, `JOG_NOME`, `JOG_POSICAO`, `JOG_STATUS`, `JOG_TIPO`, `TIM_CODIGO`, `JOG_CELULAR`) VALUES
(1, 'tioitçpsd', 'çsldkfjçslkdfj', 'A', 'T', 2, NULL),
(2, 'japa marta', 'atacante japonesa', 'A', 'T', 3, NULL),
(3, 'JANAINA LOUBACK MAGALHAES', 'Atacante', 'I', 'T', 5, NULL),
(4, 'Filipe Miranda', 'atacante', 'I', 'T', 5, NULL),
(5, 'Diego Marlon', 'Goleiro', 'I', 'T', 7, NULL),
(6, 'Sinésio', 'Lateral', 'I', 'T', 7, NULL),
(7, 'Tiago Carmo', 'Zagueiro', 'I', 'T', 6, NULL),
(8, 'Heberth', 'Goleiro', 'I', 'R', 6, NULL),
(9, 'Andre', 'Atacante', 'I', 'T', 5, NULL),
(10, '1', 'df', 'I', 'T', 8, NULL),
(11, '2', 'daaf', 'I', 'T', 8, NULL),
(12, 'flamengo1', 'adfa', 'I', 'T', 8, NULL),
(13, 'teste', 'dfadf', 'I', 'T', 8, NULL),
(14, 'teste', 'tete', 'I', 'T', 5, NULL),
(15, 'Japa', 'Atacante', 'I', 'T', 5, NULL),
(16, 'rtrtrt', 'trtr', 'I', 'T', 8, NULL),
(17, 'afa', 'dfadfad', 'I', 'T', 8, '77875546256'),
(18, 'JANAINA LOUBACK MAGALHAES', 'Atacante', 'I', 'T', 8, '098598284352'),
(19, 'Teste', 'Teste', 'I', 'T', 8, '1'),
(20, 'faj', 'aljdf', 'I', 'T', 8, '(22) 2222-2222'),
(21, 'JANAINA LOUBACK MAGALHAES', 'akdjfakj', 'I', 'T', 8, '(31) 4444-4441'),
(22, 'Andre', 'Atacante', 'A', 'T', 8, '(31) 9987-6482'),
(23, 'Heberth', 'Atacante', 'A', 'T', 8, '(31) 8990-0091'),
(24, 'Diego', 'Goleiro', 'A', 'T', 5, '(31) 6677-8999'),
(25, 'Ichi', 'Artilheiro', 'A', 'T', 8, '(31) 4546-4564'),
(26, 'tiago carmo', 'gandulaa', 'I', 'T', 8, '(99) 9999-9999'),
(27, 'Sinésio''', 'Atacante', 'I', 'T', 5, '(31) 6789-5958'),
(28, 'Sinésio', 'Atacante', 'A', 'T', 9, '(31) 8888-8888'),
(29, 'Ichi', 'Artilheiro', 'A', 'T', 9, '(31) 7874-8578'),
(30, 'Diego', 'Atacante', 'I', 'T', 10, '(31) 6464-6464'),
(31, 'André', 'Zagueiro', 'A', 'T', 10, '(31) 4664-3331'),
(32, 'Rodrigo', 'Atacante', 'A', 'T', 9, '(31) 9977-6861'),
(33, 'Rodrigo #''', 'eagf', 'I', 'T', 10, '(22) 2222-2222'),
(34, 'Leia', 'zagueira', 'A', 'T', 9, '(31) 8765-5899');

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_jogadorpelada`
--

CREATE TABLE IF NOT EXISTS `sgc_jogadorpelada` (
  `idJogador` int(11) NOT NULL,
  `idPelada` int(11) NOT NULL,
  `confirmacao` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idJogador`,`idPelada`),
  KEY `fk_idPelada` (`idPelada`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_jogos_jgs`
--

CREATE TABLE IF NOT EXISTS `sgc_jogos_jgs` (
  `JGS_CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `CAM_CODIGO` int(11) NOT NULL,
  `EST_CODIGO` int(11) NOT NULL,
  `TIM_CODIGO_MANDANTE` int(11) NOT NULL,
  `TIM_CODIGO_VISITANTE` int(11) NOT NULL,
  `JGS_DATA_HORA` date NOT NULL,
  `JGS_QTDE_GOLS_MANDANTE` int(11) DEFAULT NULL,
  `JGS_QTDE_GOLS_VISITANTE` int(11) DEFAULT NULL,
  `ARB_CODIGO_JUIZ` int(11) DEFAULT NULL,
  `ARB_CODIGO_JUIZ_RESV` int(11) DEFAULT NULL,
  `ARB_CODIGO_BANDEIRINHA1` int(11) DEFAULT NULL,
  `ARB_CODIGO_BANDEIRINHA2` int(11) DEFAULT NULL,
  PRIMARY KEY (`JGS_CODIGO`),
  UNIQUE KEY `JGS_CODIGO` (`JGS_CODIGO`),
  KEY `FK_JGS_TIM_001` (`TIM_CODIGO_MANDANTE`),
  KEY `FK_JGS_TIM_002` (`TIM_CODIGO_VISITANTE`),
  KEY `FK_JGS_EST` (`EST_CODIGO`),
  KEY `FK_JGS_ARB_001` (`ARB_CODIGO_JUIZ`),
  KEY `FK_JGS_ARB_002` (`ARB_CODIGO_JUIZ_RESV`),
  KEY `FK_JGS_ARB_003` (`ARB_CODIGO_BANDEIRINHA1`),
  KEY `FK_JGS_ARB_004` (`ARB_CODIGO_BANDEIRINHA2`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `sgc_jogos_jgs`
--

INSERT INTO `sgc_jogos_jgs` (`JGS_CODIGO`, `CAM_CODIGO`, `EST_CODIGO`, `TIM_CODIGO_MANDANTE`, `TIM_CODIGO_VISITANTE`, `JGS_DATA_HORA`, `JGS_QTDE_GOLS_MANDANTE`, `JGS_QTDE_GOLS_VISITANTE`, `ARB_CODIGO_JUIZ`, `ARB_CODIGO_JUIZ_RESV`, `ARB_CODIGO_BANDEIRINHA1`, `ARB_CODIGO_BANDEIRINHA2`) VALUES
(1, 3, 1, 5, 8, '2012-03-12', 0, 0, 5, 3, 2, 4),
(2, 4, 1, 10, 9, '2012-03-12', 0, 0, 5, 3, 4, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_local`
--

CREATE TABLE IF NOT EXISTS `sgc_local` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idPelada` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `endereco` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_msgemail_msg`
--

CREATE TABLE IF NOT EXISTS `sgc_msgemail_msg` (
  `MSG_CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `MSG_DESCRICAO` varchar(250) NOT NULL,
  PRIMARY KEY (`MSG_CODIGO`),
  UNIQUE KEY `MSG_CODIGO` (`MSG_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_pelada`
--

CREATE TABLE IF NOT EXISTS `sgc_pelada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `descricao` varchar(350) NOT NULL,
  `horario` time NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_placar_plc`
--

CREATE TABLE IF NOT EXISTS `sgc_placar_plc` (
  `JGS_CODIGO` int(11) NOT NULL,
  `JOG_CODIGO` int(11) NOT NULL,
  `PLC_QTDE_GOLS` int(11) NOT NULL,
  `tim_codigo` int(11) NOT NULL,
  PRIMARY KEY (`JGS_CODIGO`,`JOG_CODIGO`),
  KEY `FK_PLC_JOG` (`JOG_CODIGO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `sgc_placar_plc`
--

INSERT INTO `sgc_placar_plc` (`JGS_CODIGO`, `JOG_CODIGO`, `PLC_QTDE_GOLS`, `tim_codigo`) VALUES
(1, 24, 1, 5),
(1, 25, 10, 8);

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_substituicao_sub`
--

CREATE TABLE IF NOT EXISTS `sgc_substituicao_sub` (
  `JGS_CODIGO` int(11) NOT NULL,
  `tim_codigo` int(11) NOT NULL,
  `JOG_CODIGO_ENTROU` int(11) NOT NULL,
  `JOG_CODIGO_SAIU` int(11) NOT NULL,
  PRIMARY KEY (`JGS_CODIGO`,`JOG_CODIGO_ENTROU`,`JOG_CODIGO_SAIU`),
  KEY `FK_SUB_JOG_001` (`JOG_CODIGO_ENTROU`),
  KEY `FK_SUB_JOG_002` (`JOG_CODIGO_SAIU`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_time_tim`
--

CREATE TABLE IF NOT EXISTS `sgc_time_tim` (
  `TIM_CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `CAM_CODIGO` int(11) NOT NULL,
  `TIM_NOME` varchar(50) NOT NULL,
  `TIM_TECNICO` varchar(100) DEFAULT NULL,
  `TIM_QTDE_JOGADORES` int(11) NOT NULL,
  `TIM_ESCUDO` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`TIM_CODIGO`),
  UNIQUE KEY `TIM_CODIGO` (`TIM_CODIGO`),
  KEY `FK_TIM_CAM` (`CAM_CODIGO`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Extraindo dados da tabela `sgc_time_tim`
--

INSERT INTO `sgc_time_tim` (`TIM_CODIGO`, `CAM_CODIGO`, `TIM_NOME`, `TIM_TECNICO`, `TIM_QTDE_JOGADORES`, `TIM_ESCUDO`) VALUES
(1, 1, 'fsdf', 'fsdfd', 11, ''),
(2, 1, 'filipr', 'filipe', 11, ''),
(3, 1, 'japa girl nome', 'japa girl tecnica', 11, ''),
(4, 1, 'teste', 'testes', 11, ''),
(5, 3, 'Cruzeiro', 'Não sei', 11, ''),
(6, 3, 'Cruzeiro mirim', 'Piorou', 11, ''),
(7, 3, 'Cruzeiro 3', 'testes', 11, ''),
(8, 3, 'America', 'teste', 11, ''),
(9, 4, 'Cruzeiro', 'Rodrigo', 11, ''),
(10, 4, 'América', 'Rodrigo 2', 11, '');

-- --------------------------------------------------------

--
-- Estrutura da tabela `sgc_usuario_usu`
--

CREATE TABLE IF NOT EXISTS `sgc_usuario_usu` (
  `USU_CODIGO` int(11) NOT NULL AUTO_INCREMENT,
  `USU_NOME` varchar(70) NOT NULL,
  `USU_STATUS` char(1) NOT NULL,
  `USU_EMAIL` varchar(100) NOT NULL,
  `USU_LOGIN` varchar(100) DEFAULT NULL,
  `USU_SENHA` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`USU_CODIGO`),
  UNIQUE KEY `USU_CODIGO` (`USU_CODIGO`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Extraindo dados da tabela `sgc_usuario_usu`
--

INSERT INTO `sgc_usuario_usu` (`USU_CODIGO`, `USU_NOME`, `USU_STATUS`, `USU_EMAIL`, `USU_LOGIN`, `USU_SENHA`) VALUES
(1, 'japa', 'A', 'japa', 'japa', '44b09e4df13c101ae575a4f4f6a938c2'),
(2, 'Cleidison', 'A', 'cleidisoncanuto@gmail.com', 'cleidison', 'e10adc3949ba59abbe56e057f20f883e');

--
-- Restrições para as tabelas dumpadas
--

--
-- Restrições para a tabela `sgc_campeonato_cam`
--
ALTER TABLE `sgc_campeonato_cam`
  ADD CONSTRAINT `FK_CAM_USU` FOREIGN KEY (`USU_CODIGO`) REFERENCES `sgc_usuario_usu` (`USU_CODIGO`);

--
-- Restrições para a tabela `sgc_cartoes_crt`
--
ALTER TABLE `sgc_cartoes_crt`
  ADD CONSTRAINT `FK_CRT_JGS` FOREIGN KEY (`JGS_CODIGO`) REFERENCES `sgc_jogos_jgs` (`JGS_CODIGO`),
  ADD CONSTRAINT `FK_CRT_JOG` FOREIGN KEY (`JOG_CODIGO`) REFERENCES `sgc_jogador_jog` (`JOG_CODIGO`);

--
-- Restrições para a tabela `sgc_confirmacaojogo_cfj`
--
ALTER TABLE `sgc_confirmacaojogo_cfj`
  ADD CONSTRAINT `FK_PLC_JGS_CFJ` FOREIGN KEY (`JGS_CODIGO`) REFERENCES `sgc_jogos_jgs` (`JGS_CODIGO`),
  ADD CONSTRAINT `FK_PLC_JOG_CFJ` FOREIGN KEY (`JOG_CODIGO`) REFERENCES `sgc_jogador_jog` (`JOG_CODIGO`);

--
-- Restrições para a tabela `sgc_jogador_jog`
--
ALTER TABLE `sgc_jogador_jog`
  ADD CONSTRAINT `FK_JOG_TIM` FOREIGN KEY (`TIM_CODIGO`) REFERENCES `sgc_time_tim` (`TIM_CODIGO`);

--
-- Restrições para a tabela `sgc_jogadorpelada`
--
ALTER TABLE `sgc_jogadorpelada`
  ADD CONSTRAINT `fk_idJogador` FOREIGN KEY (`idJogador`) REFERENCES `sgc_jogador` (`id`),
  ADD CONSTRAINT `fk_idPelada` FOREIGN KEY (`idPelada`) REFERENCES `sgc_pelada` (`id`);

--
-- Restrições para a tabela `sgc_jogos_jgs`
--
ALTER TABLE `sgc_jogos_jgs`
  ADD CONSTRAINT `FK_JGS_ARB_001` FOREIGN KEY (`ARB_CODIGO_JUIZ`) REFERENCES `sgc_arbitragem_arb` (`ARB_CODIGO`),
  ADD CONSTRAINT `FK_JGS_ARB_002` FOREIGN KEY (`ARB_CODIGO_JUIZ_RESV`) REFERENCES `sgc_arbitragem_arb` (`ARB_CODIGO`),
  ADD CONSTRAINT `FK_JGS_ARB_003` FOREIGN KEY (`ARB_CODIGO_BANDEIRINHA1`) REFERENCES `sgc_arbitragem_arb` (`ARB_CODIGO`),
  ADD CONSTRAINT `FK_JGS_ARB_004` FOREIGN KEY (`ARB_CODIGO_BANDEIRINHA2`) REFERENCES `sgc_arbitragem_arb` (`ARB_CODIGO`),
  ADD CONSTRAINT `FK_JGS_EST` FOREIGN KEY (`EST_CODIGO`) REFERENCES `sgc_estadio_est` (`EST_CODIGO`),
  ADD CONSTRAINT `FK_JGS_TIM_001` FOREIGN KEY (`TIM_CODIGO_MANDANTE`) REFERENCES `sgc_time_tim` (`TIM_CODIGO`),
  ADD CONSTRAINT `FK_JGS_TIM_002` FOREIGN KEY (`TIM_CODIGO_VISITANTE`) REFERENCES `sgc_time_tim` (`TIM_CODIGO`);

--
-- Restrições para a tabela `sgc_placar_plc`
--
ALTER TABLE `sgc_placar_plc`
  ADD CONSTRAINT `FK_PLC_JGS` FOREIGN KEY (`JGS_CODIGO`) REFERENCES `sgc_jogos_jgs` (`JGS_CODIGO`),
  ADD CONSTRAINT `FK_PLC_JOG` FOREIGN KEY (`JOG_CODIGO`) REFERENCES `sgc_jogador_jog` (`JOG_CODIGO`);

--
-- Restrições para a tabela `sgc_substituicao_sub`
--
ALTER TABLE `sgc_substituicao_sub`
  ADD CONSTRAINT `FK_SUB_JGS` FOREIGN KEY (`JGS_CODIGO`) REFERENCES `sgc_jogos_jgs` (`JGS_CODIGO`),
  ADD CONSTRAINT `FK_SUB_JOG_001` FOREIGN KEY (`JOG_CODIGO_ENTROU`) REFERENCES `sgc_jogador_jog` (`JOG_CODIGO`),
  ADD CONSTRAINT `FK_SUB_JOG_002` FOREIGN KEY (`JOG_CODIGO_SAIU`) REFERENCES `sgc_jogador_jog` (`JOG_CODIGO`);

--
-- Restrições para a tabela `sgc_time_tim`
--
ALTER TABLE `sgc_time_tim`
  ADD CONSTRAINT `FK_TIM_CAM` FOREIGN KEY (`CAM_CODIGO`) REFERENCES `sgc_campeonato_cam` (`CAM_CODIGO`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
