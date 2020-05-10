-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Usuario` (
  `idUsuario` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `endereco` VARCHAR(255) NOT NULL,
  `dataNascimento` DATE NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idUsuario` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Servidor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Servidor` (
  `idServidor` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `matriculaSIAPE` INT UNSIGNED NOT NULL,
  `fkUsuario` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idServidor`),
  UNIQUE INDEX `idServidor_UNIQUE` (`idServidor` ASC) VISIBLE,
  UNIQUE INDEX `matriculaSIAPE_UNIQUE` (`matriculaSIAPE` ASC) VISIBLE,
  INDEX `fk_Servidor_Usuario1_idx` (`fkUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Servidor_Usuario1`
    FOREIGN KEY (`fkUsuario`)
    REFERENCES `mydb`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Aluno`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Aluno` (
  `idAluno` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ra` INT UNSIGNED NOT NULL,
  `fkUsuario` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idAluno`),
  UNIQUE INDEX `idAluno_UNIQUE` (`idAluno` ASC) VISIBLE,
  UNIQUE INDEX `ra_UNIQUE` (`ra` ASC) VISIBLE,
  INDEX `fk_Aluno_Usuario1_idx` (`fkUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Aluno_Usuario1`
    FOREIGN KEY (`fkUsuario`)
    REFERENCES `mydb`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`UsuarioFone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`UsuarioFone` (
  `idUsuarioFone` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `telefone` VARCHAR(45) NOT NULL,
  `fkUsuario` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idUsuarioFone`),
  UNIQUE INDEX `idUsuarioFone_UNIQUE` (`idUsuarioFone` ASC) VISIBLE,
  INDEX `fk_UsuarioFone_Usuario1_idx` (`fkUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_UsuarioFone_Usuario1`
    FOREIGN KEY (`fkUsuario`)
    REFERENCES `mydb`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Autor` (
  `idAutor` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nomeAutor` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idAutor`),
  UNIQUE INDEX `idAutor_UNIQUE` (`idAutor` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TipoObra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TipoObra` (
  `idTipoObra` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descricaoTipoObra` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idTipoObra`),
  UNIQUE INDEX `idTipoObra_UNIQUE` (`idTipoObra` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Obra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Obra` (
  `idObra` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(255) NOT NULL,
  `anoPublicacao` INT NOT NULL,
  `fkTipoObra` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idObra`),
  UNIQUE INDEX `idObra_UNIQUE` (`idObra` ASC) VISIBLE,
  INDEX `fk_Obra_TipoObra1_idx` (`fkTipoObra` ASC) VISIBLE,
  CONSTRAINT `fk_Obra_TipoObra1`
    FOREIGN KEY (`fkTipoObra`)
    REFERENCES `mydb`.`TipoObra` (`idTipoObra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`autor_obra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`autor_obra` (
  `idAutorObra` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fkAutor` INT UNSIGNED NOT NULL,
  `fkObra` INT UNSIGNED NOT NULL,
  INDEX `fk_Autor_has_Obra_Obra1_idx` (`fkObra` ASC) VISIBLE,
  INDEX `fk_Autor_has_Obra_Autor1_idx` (`fkAutor` ASC) VISIBLE,
  PRIMARY KEY (`idAutorObra`),
  UNIQUE INDEX `idAutorObra_UNIQUE` (`idAutorObra` ASC) VISIBLE,
  CONSTRAINT `fk_Autor_has_Obra_Autor1`
    FOREIGN KEY (`fkAutor`)
    REFERENCES `mydb`.`Autor` (`idAutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Autor_has_Obra_Obra1`
    FOREIGN KEY (`fkObra`)
    REFERENCES `mydb`.`Obra` (`idObra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Situacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Situacao` (
  `idSituacao` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `descricaoSituacao` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idSituacao`),
  UNIQUE INDEX `idSituacao_UNIQUE` (`idSituacao` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Exemplar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Exemplar` (
  `idExemplar` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `dataAquisicao` DATE NOT NULL,
  `fkObra` INT UNSIGNED NOT NULL,
  `fkSituacao` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idExemplar`),
  UNIQUE INDEX `idExemplar_UNIQUE` (`idExemplar` ASC) VISIBLE,
  INDEX `fk_Exemplar_Obra1_idx` (`fkObra` ASC) VISIBLE,
  INDEX `fk_Exemplar_Situacao1_idx` (`fkSituacao` ASC) VISIBLE,
  CONSTRAINT `fk_Exemplar_Obra1`
    FOREIGN KEY (`fkObra`)
    REFERENCES `mydb`.`Obra` (`idObra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Exemplar_Situacao1`
    FOREIGN KEY (`fkSituacao`)
    REFERENCES `mydb`.`Situacao` (`idSituacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Emprestimo` (
  `idEmprestimo` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `dataEmprestimo` DATE NOT NULL,
  `dataPrevistaDevolucao` DATE NOT NULL,
  `fkUsuario` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idEmprestimo`),
  UNIQUE INDEX `idEmprestimo_UNIQUE` (`idEmprestimo` ASC) VISIBLE,
  INDEX `fk_Emprestimo_Usuario1_idx` (`fkUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Emprestimo_Usuario1`
    FOREIGN KEY (`fkUsuario`)
    REFERENCES `mydb`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Devolucao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Devolucao` (
  `idDevolucao` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `dataDevolucao` DATE NOT NULL,
  `fkEmprestimo` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idDevolucao`),
  UNIQUE INDEX `idDevolucao_UNIQUE` (`idDevolucao` ASC) VISIBLE,
  INDEX `fk_Devolucao_Emprestimo1_idx` (`fkEmprestimo` ASC) VISIBLE,
  CONSTRAINT `fk_Devolucao_Emprestimo1`
    FOREIGN KEY (`fkEmprestimo`)
    REFERENCES `mydb`.`Emprestimo` (`idEmprestimo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Exemplar_Emprestimo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Exemplar_Emprestimo` (
  `idExemplarEmprestimo` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fkExemplar` INT UNSIGNED NOT NULL,
  `fkEmprestimo` INT UNSIGNED NOT NULL,
  INDEX `fk_Exemplar_has_Emprestimo_Emprestimo1_idx` (`fkEmprestimo` ASC) VISIBLE,
  INDEX `fk_Exemplar_has_Emprestimo_Exemplar1_idx` (`fkExemplar` ASC) VISIBLE,
  PRIMARY KEY (`idExemplarEmprestimo`),
  UNIQUE INDEX `idExemplarEmprestimo_UNIQUE` (`idExemplarEmprestimo` ASC) VISIBLE,
  CONSTRAINT `fk_Exemplar_has_Emprestimo_Exemplar1`
    FOREIGN KEY (`fkExemplar`)
    REFERENCES `mydb`.`Exemplar` (`idExemplar`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Exemplar_has_Emprestimo_Emprestimo1`
    FOREIGN KEY (`fkEmprestimo`)
    REFERENCES `mydb`.`Emprestimo` (`idEmprestimo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
