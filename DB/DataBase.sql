
CREATE SCHEMA IF NOT EXISTS `SSAFIT` DEFAULT CHARACTER SET utf8mb3 ;
USE `SSAFIT` ;

-- -----------------------------------------------------
-- Table `SSAFIT`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SSAFIT`.`User` (
  `user_num` INT NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(20) NOT NULL,
  `user_id` VARCHAR(20) NOT NULL,
  `nick_name` VARCHAR(20) NOT NULL,
  `email_adress` VARCHAR(45) NOT NULL,
  `img` VARCHAR(45) NULL,
  `height` INT NULL,
  `weight` INT NULL,
  PRIMARY KEY (`user_num`),
  UNIQUE INDEX `id_UNIQUE` (`user_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO user (password,user_id,nick_name,email_adress)
values ("qwer","qwer","admin","john0513@naver.com");



-- -----------------------------------------------------
-- Table `SSAFIT`.`Schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SSAFIT`.`Schedule` (
  `schedule_num` INT NOT NULL AUTO_INCREMENT,
  `user_num` INT NOT NULL,
  `video_id` VARCHAR(45) NOT NULL,
  `day` DATETIME NOT NULL,
  PRIMARY KEY (`schedule_num`),
  INDEX `schedule_from_user_idx` (`user_num` ASC),
  CONSTRAINT `schedule_from_user`
    FOREIGN KEY (`user_num`)
    REFERENCES `SSAFIT`.`User` (`user_num`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `SSAFIT`.`Review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SSAFIT`.`Review` (
  `review_num` INT NOT NULL AUTO_INCREMENT,
  `video_id` VARCHAR(45) NOT NULL,
  `user_num` INT NOT NULL,
  `content` VARCHAR(200) NOT NULL,
  `write_time` DATETIME NOT NULL,
  `rate` INT NOT NULL,
  PRIMARY KEY (`review_num`),
  INDEX `review_from_user_idx` (`user_num` ASC),
  CONSTRAINT `review_from_user`
    FOREIGN KEY (`user_num`)
    REFERENCES `SSAFIT`.`User` (`user_num`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

insert into Review values (0,"test",1,"test",now(),5);

-----------------------------------------------------
-- Table `SSAFIT`.`ZzimList`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SSAFIT`.`ZzimList` (
  `zzim_num` VARCHAR(45) NOT NULL auto_increment,
  `user_num` INT NOT NULL,
  `video_id` VARCHAR(45) NOT NULL,
  `video_title` VARCHAR(45) NOT NULL,
  `channel_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`zzim_num`),
  INDEX `zzim_from_user_idx` (`user_num` ASC),
  CONSTRAINT `zzim_from_user`
    FOREIGN KEY (`user_num`)
    REFERENCES `SSAFIT`.`User` (`user_num`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `SSAFIT`.`food`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SSAFIT`.`food` (
  `food_name` VARCHAR(45) NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`food_name`))
ENGINE = InnoDB;

