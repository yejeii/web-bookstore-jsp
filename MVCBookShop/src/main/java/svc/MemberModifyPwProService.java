package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;

/** 회원 정보 수정 요청을 처리하는 Service 클래스 */
public class MemberModifyPwProService {

	/** 회원 비밀번호를 확인하는 메서드 */
	public boolean selectPw(String m_id, String oldPw) {
		System.out.println(" M.ModifyPwPro.S : selectPw() 호출");
		
		/* DB 작업 */
		Connection conn = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(conn);
		int selectCount = memberDAO.selectPw(m_id, oldPw);
		
		boolean isWrightPw = false;
		if(selectCount == 1) {
			isWrightPw = true;
		}
		close(conn);
		
		System.out.println(" M.ModifyPwPro.S : selectPw() 종료");
		return isWrightPw;
	}

	/** 회원 비밀번호을 수정하는 메서드 */
	public boolean modifyPw(String m_id, String oldPw, String newPw) {
		System.out.println(" M.ModifyPwPro.S : modifyPw() 호출");
		
		/* DB 작업 */
		Connection conn = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(conn);
		int updateCount = memberDAO.updatePw(m_id, oldPw, newPw);
		
		boolean updateResult = false;
		
		if(updateCount > 0) {
			commit(conn);
			updateResult = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		System.out.println(" M.ModifyPwPro.S : modifyPw() 종료");
		return updateResult;
	}

	
}
