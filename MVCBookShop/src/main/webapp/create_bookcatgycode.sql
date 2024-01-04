CREATE TABLE `bookcatgycode` (
  `bc_code` int NOT NULL COMMENT '카테고리 코드',
  `bc_name` varchar(45) NOT NULL COMMENT '카테고리 코드명',
  `bc_code_ref_md` int DEFAULT NULL COMMENT '해당 카테고리의 중분류(부모) 코드',
  `bc_code_ref_mn` int DEFAULT NULL COMMENT '해당 카테고리의 대분류 코드',
  PRIMARY KEY (`bc_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='도서 카테고리 테이블'