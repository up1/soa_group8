SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

USE `kmitlresidence` ;

-- -----------------------------------------------------
-- Table `kmitlresidence`.`room_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kmitlresidence`.`room_type` ;

CREATE TABLE IF NOT EXISTS `kmitlresidence`.`room_type` (
  `room_type_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `description` VARCHAR(1200) NOT NULL,
  `maximum_guest` INT NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`room_type_id`),
  UNIQUE INDEX `accommodation_id_UNIQUE` (`room_type_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kmitlresidence`.`room`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kmitlresidence`.`room` ;

CREATE TABLE IF NOT EXISTS `kmitlresidence`.`room` (
  `room_id` INT NOT NULL AUTO_INCREMENT,
  `floor` INT NOT NULL,
  `room_type_id` INT NOT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE INDEX `room_id_UNIQUE` (`room_id` ASC),
  INDEX `idx_room_type_id` (`room_type_id` ASC),
  CONSTRAINT `fk_room_type_id`
    FOREIGN KEY (`room_type_id`)
    REFERENCES `kmitlresidence`.`room_type` (`room_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kmitlresidence`.`notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kmitlresidence`.`notification` ;

CREATE TABLE IF NOT EXISTS `kmitlresidence`.`notification` (
  `notification_id` INT NOT NULL AUTO_INCREMENT,
  `token` VARCHAR(80) NOT NULL,
  `expiry_datetime` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`notification_id`),
  UNIQUE INDEX `notification_id_UNIQUE` (`notification_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kmitlresidence`.`reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kmitlresidence`.`reservation` ;

CREATE TABLE IF NOT EXISTS `kmitlresidence`.`reservation` (
  `reservation_id` INT NOT NULL AUTO_INCREMENT,
  `guest_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `check_in_date` VARCHAR(45) NOT NULL,
  `check_out_date` VARCHAR(45) NOT NULL,
  `status` VARCHAR(45) NOT NULL DEFAULT 'in progress',
  `room_id` INT NOT NULL,
  `notification_id` INT NOT NULL,
  PRIMARY KEY (`reservation_id`),
  UNIQUE INDEX `reservation_id_UNIQUE` (`reservation_id` ASC),
  INDEX `idx_room_id` (`room_id` ASC),
  INDEX `idx_notification_id` (`notification_id` ASC),
  CONSTRAINT `fk_room_id`
    FOREIGN KEY (`room_id`)
    REFERENCES `kmitlresidence`.`room` (`room_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notification_id`
    FOREIGN KEY (`notification_id`)
    REFERENCES `kmitlresidence`.`notification` (`notification_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kmitlresidence`.`registration`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kmitlresidence`.`registration` ;

CREATE TABLE IF NOT EXISTS `kmitlresidence`.`registration` (
  `registration_id` INT NOT NULL AUTO_INCREMENT,
  `check_in_datetime` VARCHAR(45) NOT NULL,
  `check_out_datetime` VARCHAR(45) NULL,
  `reservation_id` INT NOT NULL,
  PRIMARY KEY (`registration_id`),
  UNIQUE INDEX `registration_id_UNIQUE` (`registration_id` ASC),
  INDEX `idx_reservation_id` (`reservation_id` ASC),
  CONSTRAINT `fk_reservation_id`
    FOREIGN KEY (`reservation_id`)
    REFERENCES `kmitlresidence`.`reservation` (`reservation_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `kmitlresidence`.`hotel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kmitlresidence`.`hotel` ;

CREATE TABLE IF NOT EXISTS `kmitlresidence`.`hotel` (
  `hotel_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `description` VARCHAR(1200) NOT NULL,
  `latitude` FLOAT NOT NULL,
  `longitude` FLOAT NOT NULL,
  PRIMARY KEY (`hotel_id`),
  UNIQUE INDEX `hotel_id_UNIQUE` (`hotel_id` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;