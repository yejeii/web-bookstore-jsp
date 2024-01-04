package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

/** 회원 정보 탈퇴 요청을 처리하는 Service 클래스 */
public class MemberDeleteProService {

	/** 비밀번호를 확인하는 메서드 */
	public boolean selectPw(String deleteId, String checkPw) {
		System.out.println(" M.DeletePro.S : selectPw() 호출");
		
		/* DB 작업 */
		Connection conn = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(conn);
		int selectCount = memberDAO.selectPw(deleteId, checkPw);
		
		boolean isWrightPw = false;
		if(selectCount == 1) {
			isWrightPw = true;
		}
		close(conn);
		
		System.out.println(" M.DeletePro.S : selectPw() 종료");
		return isWrightPw;
	}

	/** 회원 정보를 삭제하는 메서드 */
	public boolean deleteMember(String deleteId, String checkPw) {
		System.out.println(" M.DeletePro.S : deleteMember() 호출");
		
		/* DB 작업 
		 * 1. 커넥션 풀에서 Connection 객체 얻어오기
		 * 2. 싱글톤 방식으로 MemberDAO 클래스의 인스턴스를 get 
		 * 3. MemberDAO 클래스에서 DB 작업을 수행할 때 사용할 Connection 객체를 주입 
		 * 4. DB에 회원 삭제(DELETE) 메서드 호출
		 * */
		Connection conn = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(conn);
		int deleteCount = memberDAO.deleteMember(deleteId, checkPw);
		
		boolean deleteResult = false;
		
		if(deleteCount > 0) {
			commit(conn);
			deleteResult = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		System.out.println(" M.DeletePro.S : deleteMember 결과 -"+deleteResult);
		System.out.println(" M.DeletePro.S : deleteMember() 종료");
		return deleteResult;
	}


}
