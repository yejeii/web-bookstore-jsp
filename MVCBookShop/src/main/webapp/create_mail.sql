CREATE TABLE `jspbookshop`.`mail` (
  `ml_index` INT NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `ml_recipient` VARCHAR(1000) NOT NULL COMMENT '수신자',
  `ml_sender` VARCHAR(100) NOT NULL COMMENT '발신자',
  `ml_attachment` VARCHAR(500) NULL COMMENT '첨부파일',
  `ml_title` VARCHAR(100) NOT NULL COMMENT '제목',
  `ml_text` LONGTEXT NOT NULL COMMENT '내용',
  `ml_read` INT NOT NULL DEFAULT 0 COMMENT '열람여부( default 0 : 미열람)',
  `ml_importance` INT NOT NULL DEFAULT 0 COMMENT '중요도(default 0 : 중요X)',
  `ml_sendTime` TIMESTAMP NOT NULL COMMENT '수신시간',
  PRIMARY KEY (`ml_index`))
COMMENT = '네이버 SMTP을 이용한 이메일 테이블';