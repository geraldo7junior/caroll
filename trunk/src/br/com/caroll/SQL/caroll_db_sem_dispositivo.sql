SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `caroll_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `caroll_db` ;

-- -----------------------------------------------------
-- Table `caroll_db`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroll_db`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `nomeUsuario` VARCHAR(45) NOT NULL,
  `loginUsuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caroll_db`.`Sentimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroll_db`.`Sentimento` (
  `idSentimento` INT NOT NULL,
  `valorSentimento` VARCHAR(45) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idSentimento`))
ENGINE = InnoDB;


INSERT INTO `caroll_db`.`Sentimento` (`idSentimento`, `valorSentimento`) VALUES (1, 'Neutro');
INSERT INTO `caroll_db`.`Sentimento` (`idSentimento`, `valorSentimento`) VALUES (2, 'Negativo');
INSERT INTO `caroll_db`.`Sentimento` (`idSentimento`, `valorSentimento`) VALUES (3, 'Positivo');

-- -----------------------------------------------------
-- Table `caroll_db`.`Tweet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroll_db`.`Tweet` (
  `idTweet` INT NOT NULL AUTO_INCREMENT,
  `postTweet` VARCHAR(140) NOT NULL,
  `dataTweet` VARCHAR (20) NOT NULL,
  `tipoTweet` VARCHAR(45) NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  `Sentimento_idSentimento` INT NOT NULL,
  PRIMARY KEY (`idTweet`, `Usuario_idUsuario`, `Sentimento_idSentimento`),
 
  CONSTRAINT `fk_Tweet_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `caroll_db`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,

   CONSTRAINT `fk_Tweet_Sentimento1`
    FOREIGN KEY (`Sentimento_idSentimento`)
    REFERENCES `caroll_db`.`Sentimento` (`idSentimento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caroll_db`.`Local`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroll_db`.`Local` (
  `idLocal` INT NOT NULL AUTO_INCREMENT,
  `latitudeLocal` DOUBLE,
  `longitudeLocal` DOUBLE,
  `cidadeLocal` VARCHAR(45),
  `estadoLocal` VARCHAR(45),
  `paisLocal` VARCHAR(45),
  `Tweet_idTweet` INT NOT NULL,
  PRIMARY KEY (`idLocal`, `Tweet_idTweet`),
  INDEX `fk_Local_Tweet1_idx` (`Tweet_idTweet` ASC),
  CONSTRAINT `fk_Local_Tweet1`
    FOREIGN KEY (`Tweet_idTweet`)
    REFERENCES `caroll_db`.`Tweet` (`idTweet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caroll_db`.`Hashtag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroll_db`.`Hashtag` (
  `idHashtag` INT NOT NULL AUTO_INCREMENT,
  `dadoHashtag` VARCHAR(140) NOT NULL,
  PRIMARY KEY (`idHashtag`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caroll_db`.`Favorito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroll_db`.`Favorito` (
  `idFavorito` INT NOT NULL AUTO_INCREMENT,
  `Tweet_idTweet` INT NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idFavorito`, `Tweet_idTweet`, `Usuario_idUsuario`),
  INDEX `fk_Favorito_Tweet1_idx` (`Tweet_idTweet` ASC),
  INDEX `fk_Favorito_Usuario1_idx` (`Usuario_idUsuario` ASC),
  CONSTRAINT `fk_Favorito_Tweet1`
    FOREIGN KEY (`Tweet_idTweet`)
    REFERENCES `caroll_db`.`Tweet` (`idTweet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Favorito_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `caroll_db`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `caroll_db`.`Hashtag_has_Tweet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroll_db`.`Hashtag_has_Tweet` (
  `Hashtag_idHashtag` INT NULL,
  `Tweet_idTweet` INT NOT NULL,
  PRIMARY KEY (`Hashtag_idHashtag`, `Tweet_idTweet`),
  INDEX `fk_Hashtag_has_Tweet_Tweet1_idx` (`Tweet_idTweet` ASC),
  INDEX `fk_Hashtag_has_Tweet_Hashtag1_idx` (`Hashtag_idHashtag` ASC),
  CONSTRAINT `fk_Hashtag_has_Tweet_Hashtag1`
    FOREIGN KEY (`Hashtag_idHashtag`)
    REFERENCES `caroll_db`.`Hashtag` (`idHashtag`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Hashtag_has_Tweet_Tweet1`
    FOREIGN KEY (`Tweet_idTweet`)
    REFERENCES `caroll_db`.`Tweet` (`idTweet`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
