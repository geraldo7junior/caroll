SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `caroll` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `caroll` ;

-- -----------------------------------------------------
-- Table `caroll`.`Usuario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `caroll`.`Usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT ,
  `nomeUsuario` VARCHAR(45) NOT NULL ,
  `loginUsuario` VARCHAR(45) NOT NULL ,
  `sexoUsuario` VARCHAR(45) NULL ,
  `dataNascimentoUsuario` VARCHAR(45) NULL ,
  PRIMARY KEY (`idUsuario`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caroll`.`Sentimento`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `caroll`.`Sentimento` (
  `idSentimento` INT NOT NULL AUTO_INCREMENT ,
  `valorSentimento` VARCHAR(45) NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`idSentimento`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caroll`.`Tweet`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `caroll`.`Tweet` (
  `idTweet` INT NOT NULL AUTO_INCREMENT ,
  `postTweet` VARCHAR(140) NOT NULL ,
  `dataTweet` VARCHAR(15) NOT NULL ,
  `Usuario_idUsuario` INT NOT NULL ,
  `Sentimento_idSentimento` INT NULL ,
  PRIMARY KEY (`idTweet`) ,
  INDEX `fk_Tweet_Usuario_idx` (`Usuario_idUsuario` ASC) ,
  INDEX `fk_Tweet_Sentimento1_idx` (`Sentimento_idSentimento` ASC) ,
  CONSTRAINT `fk_Tweet_Usuario`
    FOREIGN KEY (`Usuario_idUsuario` )
    REFERENCES `caroll`.`Usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Tweet_Sentimento1`
    FOREIGN KEY (`Sentimento_idSentimento` )
    REFERENCES `caroll`.`Sentimento` (`idSentimento` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caroll`.`Local`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `caroll`.`Local` (
  `idLocal` INT NOT NULL AUTO_INCREMENT ,
  `latidudeLocal` DOUBLE NOT NULL ,
  `longitudeLocal` DOUBLE NOT NULL ,
  `cidadeLocal` VARCHAR(45) NOT NULL ,
  `estadoLocal` VARCHAR(45) NOT NULL ,
  `paisLocal` VARCHAR(45) NOT NULL ,
  `Tweet_idTweet` INT NOT NULL ,
  PRIMARY KEY (`idLocal`) ,
  INDEX `fk_Local_Tweet1_idx` (`Tweet_idTweet` ASC) ,
  UNIQUE INDEX `Tweet_idTweet_UNIQUE` (`Tweet_idTweet` ASC) ,
  CONSTRAINT `fk_Local_Tweet1`
    FOREIGN KEY (`Tweet_idTweet` )
    REFERENCES `caroll`.`Tweet` (`idTweet` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caroll`.`Hashtag`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `caroll`.`Hashtag` (
  `idHashtag` INT NOT NULL AUTO_INCREMENT ,
  `dadoHashtag` VARCHAR(140) NOT NULL ,
  PRIMARY KEY (`idHashtag`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caroll`.`Favorito`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `caroll`.`Favorito` (
  `idFavorito` INT NOT NULL AUTO_INCREMENT ,
  `Usuario_idUsuario` INT NOT NULL ,
  `Tweet_idTweet` INT NOT NULL ,
  PRIMARY KEY (`idFavorito`) ,
  INDEX `fk_Favorito_Usuario1_idx` (`Usuario_idUsuario` ASC) ,
  INDEX `fk_Favorito_Tweet1_idx` (`Tweet_idTweet` ASC) ,
  CONSTRAINT `fk_Favorito_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario` )
    REFERENCES `caroll`.`Usuario` (`idUsuario` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Favorito_Tweet1`
    FOREIGN KEY (`Tweet_idTweet` )
    REFERENCES `caroll`.`Tweet` (`idTweet` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caroll`.`ReTweet`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `caroll`.`ReTweet` (
  `idReTweet` INT NOT NULL AUTO_INCREMENT ,
  `Tweet_idTweet` INT NOT NULL ,
  PRIMARY KEY (`idReTweet`) ,
  INDEX `fk_ReTweet_Tweet1_idx` (`Tweet_idTweet` ASC) ,
  CONSTRAINT `fk_ReTweet_Tweet1`
    FOREIGN KEY (`Tweet_idTweet` )
    REFERENCES `caroll`.`Tweet` (`idTweet` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caroll`.`Resposta`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `caroll`.`Resposta` (
  `idResposta` INT NOT NULL AUTO_INCREMENT ,
  `Tweet_idTweet` INT NOT NULL ,
  PRIMARY KEY (`idResposta`) ,
  INDEX `fk_Resposta_Tweet1_idx` (`Tweet_idTweet` ASC) ,
  CONSTRAINT `fk_Resposta_Tweet1`
    FOREIGN KEY (`Tweet_idTweet` )
    REFERENCES `caroll`.`Tweet` (`idTweet` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `caroll`.`Hashtag_has_Tweet`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `caroll`.`Hashtag_has_Tweet` (
  `Hashtag_idHashtag` INT NOT NULL ,
  `Tweet_idTweet` INT NOT NULL ,
  INDEX `fk_Hashtag_has_Tweet_Tweet1_idx` (`Tweet_idTweet` ASC) ,
  INDEX `fk_Hashtag_has_Tweet_Hashtag1_idx` (`Hashtag_idHashtag` ASC) ,
  CONSTRAINT `fk_Hashtag_has_Tweet_Hashtag1`
    FOREIGN KEY (`Hashtag_idHashtag` )
    REFERENCES `caroll`.`Hashtag` (`idHashtag` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Hashtag_has_Tweet_Tweet1`
    FOREIGN KEY (`Tweet_idTweet` )
    REFERENCES `caroll`.`Tweet` (`idTweet` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
