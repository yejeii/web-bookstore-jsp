package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;

/** 임시코드를 발급받은 후, 새 비밀번호로 변경하는 요청을 처리하는 비즈니스 클래스 */
public class MemberChangePwProService {

	/** 임시코드를 발급받은 후, 새 비밀번호로 변경하는 메서드 */
	public boolean updatePw(String m_id, String newPw) {
		System.out.println(" M.ChangePwPro.S : updatePw() 호출");
		
		int updateCount = 0;
		
		/* DB 작업 */
		Connection conn = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(conn);
		updateCount = memberDAO.updatePw(m_id, newPw);
		
		boolean updateResult = false;
		
		if(updateCount > 0) {
			commit(conn);
			updateResult = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		System.out.println(" M.ChangePwPro.S : updatePw() 종료");
		return updateResult;
	}

}
