package svc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import dao.MemberDAO;
import db.JdbcUtil;

/** 이메일을 검색하는 요청을 처리하는 비즈니스 클래스 */
public class MemberSearchEmailService {

	/** 검색 쿼리문을 포함하는 회원 이메일을 찾아 반환하는 메서드 */
	public ArrayList<Map<String, String>> getEmailMapList(String query) {
		System.out.println(" M.SearchEmail.S : getEmailMapList() 호출");
		
		
		/* DB 작업 */
		Connection conn = JdbcUtil.getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(conn);
		ArrayList<Map<String, String>> memberList = memberDAO.getEmailList(query);
		JdbcUtil.close(conn);
		
		System.out.println(" M.SearchEmail.S : getEmailMapList() 종료");
		return memberList;
	}

}
