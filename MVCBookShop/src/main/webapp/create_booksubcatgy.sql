CREATE TABLE `booksubcatgy` (
  `bsc_id` int NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `bsc_b_id` int NOT NULL COMMENT '해당 도서 아이디',
  `bsc_bc_code` int NOT NULL COMMENT '해당 도서의 서브 카테고리 코드',
  PRIMARY KEY (`bsc_id`),
  KEY `fk_bsc_bc_code_idx` (`bsc_bc_code`),
  KEY `fk_bsc_b_id_idx` (`bsc_b_id`),
  CONSTRAINT `fk_bsc_b_id` FOREIGN KEY (`bsc_b_id`) REFERENCES `book` (`b_id`),
  CONSTRAINT `fk_bsc_bc_code` FOREIGN KEY (`bsc_bc_code`) REFERENCES `bookcatgycode` (`bc_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='등록된 도서 한 권의 서브 카테고리(메인이 아닌) 정보를 담는 테이블'