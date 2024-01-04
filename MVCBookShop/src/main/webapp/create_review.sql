CREATE TABLE `jspbookshop`.`review` (
  `r_id` INT NOT NULL AUTO_INCREMENT COMMENT '리뷰 아이디',
  `r_b_id` INT NOT NULL COMMENT '리뷰를 단 책 아이디',
  `r_m_id` VARCHAR(15) NOT NULL COMMENT '리뷰를 쓴 회원 아이디',
  `r_text` VARCHAR(100) NOT NULL COMMENT '리뷰 내용',
  `r_buy_opt` INT NOT NULL COMMENT '책 구매 여부(1 : 구매)',
  `r_star` INT NOT NULL COMMENT '리뷰 별점',
  `r_regdate` DATE NOT NULL COMMENT '리뷰 작성일',
  PRIMARY KEY (`r_id`),
  INDEX `r_b_id_idx` (`r_b_id` ASC) VISIBLE,
  INDEX `fk_r_m_id_idx` (`r_m_id` ASC) VISIBLE,
  CONSTRAINT `fk_r_b_id`
    FOREIGN KEY (`r_b_id`)
    REFERENCES `jspbookshop`.`book` (`b_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_r_m_id`
    FOREIGN KEY (`r_m_id`)
    REFERENCES `jspbookshop`.`member` (`m_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);