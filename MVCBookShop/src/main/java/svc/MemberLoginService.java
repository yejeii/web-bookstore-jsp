package svc;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;
import static db.JdbcUtil.*;

/** 로그인 요청을 처리하는 Service 클래스 */
public class MemberLoginService {

	public boolean login(Member member) {
		System.out.println(" M.Login.S : login() 호출");
		
		boolean loginResult = false;

		/* DB 작업 
		 * 1. 커넥션 풀에서 Connection 객체 얻어오기
		 * 2. 싱글톤 방식으로 MemberDAO 클래스의 인스턴스를 get 
		 * 3. MemberDAO 클래스에서 DB 작업을 수행할 때 사용할 Connection 객체를 주입 
		 * 4. DB에 회원 조회(SELECT) 메서드 호출
		 * */
		Connection conn = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(conn);
		
		String loginId = memberDAO.selectLoginId(member);
		if(loginId != null) {
			loginResult = true;
		}
		
		close(conn);
		System.out.println(" M.Login.S : login() 종료");
		return loginResult;
	}
}
