
CREATE SCHEMA IF NOT EXISTS `SSAFIT` DEFAULT CHARACTER SET utf8mb4;
USE `SSAFIT` ;

-- -----------------------------------------------------
-- Table `SSAFIT`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SSAFIT`.`User` (
  `user_num` INT NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(100) NOT NULL,
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
  `zzim_num` INT NOT NULL auto_increment,
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
  `food_num` INT NOT NULL AUTO_INCREMENT,
  `food_name` VARCHAR(45) NOT NULL,
  `category` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`food_num`))
ENGINE = InnoDB;


insert into food (category, food_name )values 
("탄수화물", "잡곡밥") ,("탄수화물", "현미밥") ,("탄수화물", "귀리밥")   
,("탄수화물", "퀴노아" ),("탄수화물", "통밀파스타"),("탄수화물", "호밀빵") 
,("탄수화물", "통밀빵") ,("탄수화물", "보리밥") ,("탄수화물", "오트밀") 
,("탄수화물", "통곡물씨리얼") ,("탄수화물", "고구마") ,("탄수화물", "곤약") 
,("탄수화물", "단호박") ,("탄수화물", "바나나") ,("탄수화물", "병아리콩")
,("탄수화물", "아몬드") ,("탄수화물", "아마씨") 
,("단백질", "닭가슴살") ,("단백질", "소고기") ,("단백질", "돼지고기")  
,("단백질", "고등어") ,("단백질", "오징어") ,("단백질", "꽁치")  
,("단백질", "연어") ,("단백질", "멸치") ,("단백질", "참치")  
,("단백질", "달걀") ,("단백질", "두부") ,("단백질", "대두콩")  
,("단백질", "강낭콩") ,("단백질", "완두콩") ,("단백질", "렌틸콩"),("단백질","치즈")
,("기본채소", "양상추") ,("기본채소", "양배추") ,("기본채소", "적양배추") ,("기본채소", "상추")   
,("보충채소", "케일"),("보충채소", "치커리")  ,("보충채소", "새싹채소")  ,("보충채소", "브로콜리")  
,("보충채소", "토마토"),("보충채소", "콜라비")  ,("보충채소", "버섯")  ,("보충채소", "오이")  
,("보충채소", "미역"),("보충채소", "마")  ,("보충채소", "자색당근")  ,("보충채소", "연근")
,("보충채소", "우엉"),("보충채소", "비트")  ,("보충채소", "샐러리")
,("과일", "사과"),("과일", "바나나")  ,("과일", "블루베리")  ,("과일", "라즈베리")    
,("과일", "아보카도"),("과일", "파프리카")  ,("과일", "단감")  ,("과일", "포도")   
,("과일", "자몽"),("과일", "오렌지")  ,("과일", "귤")  ,("과일", "망고")
,("간식", "견과류"),("간식", "과일")  ,("간식", "플레인요거트")  ,("간식", "방울토마토") 
,("간식", "바나나"),("간식", "군밤")  ,("간식", "참치캔샐러드")  ,("간식", "연어샐러드")    
,("간식", "닭가슴살샐러드");
