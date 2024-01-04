CREATE TABLE `book` (
  `b_id` int NOT NULL AUTO_INCREMENT COMMENT '책 아이디',
  `b_name` varchar(100) NOT NULL COMMENT '책 제목',
  `b_writer` varchar(100) NOT NULL COMMENT '책 저자',
  `b_translator` varchar(100) DEFAULT NULL COMMENT '책 옮긴이',
  `b_publisher` varchar(100) NOT NULL COMMENT '책 출판사',
  `b_bc_code` int NOT NULL COMMENT '책 카테고리 코드(메인)',
  `b_price` int NOT NULL COMMENT '책 가격',
  `b_image` varchar(45) NOT NULL COMMENT '책 대표 이미지',
  `b_page` bigint NOT NULL COMMENT '책 쪽수',
  `b_publish_date` varchar(15) NOT NULL COMMENT '책 출판일',
  `b_content` text COMMENT '책 설명',
  `b_readcount` int DEFAULT NULL COMMENT '조회수',
  PRIMARY KEY (`b_id`),
  KEY `fk_b_bs_code_idx` (`b_bc_code`),
  CONSTRAINT `fk_b_bc_code` FOREIGN KEY (`b_bc_code`) REFERENCES `bookcatgycode` (`bc_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='	'