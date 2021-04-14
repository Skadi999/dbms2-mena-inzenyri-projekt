-- MySQL Script generated by MySQL Workbench
-- Tue Apr 13 19:31:48 2021
-- Model: New Model    Version: 1.0
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
-- Table `mydb`.`Mince`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Mince` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cena` DOUBLE NULL,
  `rok` INT NULL,
  `nazev` VARCHAR(100) NULL,
  `kov` VARCHAR(45) NULL,
  `zeme` VARCHAR(45) NULL,
  `nazevKolekce` VARCHAR(100) NULL,
  `obrazekCesta` VARCHAR(150) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`KolekceMinci`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`KolekceMinci` (
  `mince` VARCHAR(45) NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Transakce`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Transakce` (
  `celkovaCena` DOUBLE NULL,
  `cisloTransakce` INT NOT NULL AUTO_INCREMENT,
  `prodaneMince` VARCHAR(45) NULL,
  PRIMARY KEY (`cisloTransakce`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`BeznyUcet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`BeznyUcet` (
  `jmeno` VARCHAR(45) NULL,
  `prijmeni` VARCHAR(45) NULL,
  `idPolozky` INT NULL,
  `transakce` INT NULL,
  `jmenoUctu` VARCHAR(45) NOT NULL,
  `heslo` VARCHAR(45) NULL,
  PRIMARY KEY (`jmenoUctu`),
  UNIQUE INDEX `jmenoUctu_UNIQUE` (`jmenoUctu` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Programator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Programator` (
  `idUctu` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `jmeno` VARCHAR(45) NULL,
  `prijmeni` VARCHAR(45) NULL,
  PRIMARY KEY (`idUctu`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`AdministrativniUcet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`AdministrativniUcet` (
  `email` VARCHAR(45) NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `jmeno` VARCHAR(45) NULL,
  `prijmeni` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PracovnikPodpory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`PracovnikPodpory` (
  `idUctu` INT NOT NULL AUTO_INCREMENT,
  `jmeno` VARCHAR(45) NULL,
  `prijmeni` VARCHAR(45) NULL,
  PRIMARY KEY (`idUctu`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Zprava`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Zprava` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `druhZpravy` INT NULL,
  `text` VARCHAR(500) NULL,
  `odesilatel` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`SeznamMinci`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`SeznamMinci` (
  `id` INT NOT NULL,
  `idMince` INT NULL,
  `ucetKlienta` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `Odkaz na Mince_idx` (`idMince` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `Prodavajici_idx` (`ucetKlienta` ASC) VISIBLE,
  CONSTRAINT `Odkaz na Mince`
    FOREIGN KEY (`idMince`)
    REFERENCES `mydb`.`Mince` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Prodavajici`
    FOREIGN KEY (`ucetKlienta`)
    REFERENCES `mydb`.`BeznyUcet` (`jmenoUctu`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;