CREATE TABLE `member` (
  `m_id` varchar(15) NOT NULL COMMENT '회원 아이디',
  `m_pw` varchar(16) NOT NULL COMMENT '회원 비번',
  `m_name` varchar(15) NOT NULL COMMENT '회원 이름',
  `m_age` int NOT NULL COMMENT '회원 나이',
  `m_gender` varchar(2) NOT NULL COMMENT '회원 성별',
  `m_email` varchar(40) NOT NULL COMMENT '회원 이메일',
  `m_phone1` varchar(5) NOT NULL COMMENT '회원 연락처(010 다음 4자리)',
  `m_phone2` varchar(5) NOT NULL COMMENT '회원 연락처(끝 4자리)',
  `m_postcode` varchar(10) DEFAULT NULL COMMENT '우편번호',
  `m_addr1` varchar(100) DEFAULT NULL COMMENT '도로명 주소',
  `m_addr2` varchar(50) DEFAULT NULL COMMENT '상세 주소',
  `m_addr3` varchar(10) DEFAULT NULL COMMENT '참고항목',
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='회원 테이블'