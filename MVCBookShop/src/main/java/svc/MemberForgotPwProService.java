package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

/** 회원 비밀번호 찾기 요청을 처리하는 Service 클래스 */
public class MemberForgotPwProService {

	/** 아이디와 이메일로 회원을 가져오는 메서드 */
	public Member getMember(String m_id, String m_email) {
		System.out.println(" M.ForgotPwPro.S : getMember() 호출");
		
		
		/* DB 작업 */
		Connection conn = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(conn);
		Member member = memberDAO.selectMember(m_id, m_email);
		
		System.out.println(" M.ForgotPwPro.S : getMember() 종료");
		return member;
	}

}
