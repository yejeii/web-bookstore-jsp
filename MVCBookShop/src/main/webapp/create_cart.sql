CREATE TABLE `cart` (
  `ca_id` int NOT NULL AUTO_INCREMENT COMMENT '장바구니 아이디',
  `ca_m_id` varchar(15) NOT NULL COMMENT '장바구니 회원 아이디',
  `ca_b_id` int NOT NULL COMMENT '장바구니에 담은 도서 아이디',
  `ca_b_qty` int NOT NULL COMMENT '장바구니에 담긴 도서 수량',
  PRIMARY KEY (`ca_id`),
  KEY `fk_c_b_id_idx` (`ca_b_id`),
  KEY `fk_c_m_id_idx` (`ca_m_id`),
  CONSTRAINT `fk_ca_b_id` FOREIGN KEY (`ca_b_id`) REFERENCES `book` (`b_id`),
  CONSTRAINT `fk_ca_m_id` FOREIGN KEY (`ca_m_id`) REFERENCES `member` (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='장바구니 테이블'