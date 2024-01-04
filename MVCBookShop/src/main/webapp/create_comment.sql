CREATE TABLE `jspbookshop`.`comment` (
  `c_id` INT NOT NULL AUTO_INCREMENT COMMENT '코멘트 아이디',
  `c_b_id` INT NOT NULL COMMENT '도서 아이디',
  `c_m_id` VARCHAR(15) NOT NULL COMMENT '코멘트를 작성하는 회원 아이디',
  `c_title` VARCHAR(45) NOT NULL COMMENT '코멘트 제목',
  `c_text` VARCHAR(2000) NOT NULL COMMENT '코멘트 내용',
  `c_regdate` DATE NOT NULL COMMENT '코멘트 등록 날짜',
  `c_empathy` INT NULL COMMENT '공감 수',
  `c_ref` INT NULL COMMENT '그룹번호',
  `c_lev` INT NULL COMMENT '깊이',
  `c_seq` INT NULL COMMENT '관련글 중 출력 순서',
  PRIMARY KEY (`c_id`));

ALTER TABLE `jspbookshop`.`comment` 
ADD INDEX `fk_c_b_id_idx` (`c_b_id` ASC) VISIBLE,
ADD INDEX `fk_c_m_id_idx` (`c_m_id` ASC) VISIBLE;
;

ALTER TABLE `jspbookshop`.`comment` 
	ADD CONSTRAINT `fk_c_b_id`
	FOREIGN KEY (`c_b_id`)
	REFERENCES `jspbookshop`.`book` (`b_id`)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_c_m_id`
  FOREIGN KEY (`c_m_id`)
  REFERENCES `jspbookshop`.`member` (`m_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
