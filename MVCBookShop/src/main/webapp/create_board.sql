CREATE TABLE `jspbookshop`.`book` (
  `b_id` INT NOT NULL AUTO_INCREMENT COMMENT '책 아이디',
  `b_name` VARCHAR(25) NOT NULL COMMENT '책 제목',
  `b_writer` VARCHAR(20) NOT NULL COMMENT '책 저자',
  `b_translator` VARCHAR(12) NULL COMMENT '책 옮긴이',
  `b_publisher` VARCHAR(15) NOT NULL COMMENT '책 출판사',
  `b_catgy` VARCHAR(12) NOT NULL COMMENT '책 분류',
  `b_price` INT NOT NULL COMMENT '책 가격',
  `b_image` VARCHAR(45) NOT NULL COMMENT '책 대표 이미지',
  `b_page` BIGINT NOT NULL COMMENT '책 쪽수',
  `b_publish_date` VARCHAR(15) NOT NULL COMMENT '책 출판일',
  `b_content` VARCHAR(1000) NULL COMMENT '책 설명',
  `b_readcount` INT NULL COMMENT '조회수',
  PRIMARY KEY (`b_id`));