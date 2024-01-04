package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

/** 회원 정보 수정을 처리하는 Service 클래스 */
public class MemberModifyProService {

	/** 회원 정보를 수정하는 메서드 */
	public boolean updateMember(Member member) {
		System.out.println(" M.ModifyPro.S : updateMember() 호출");
		
		boolean updateResult = false;
		
		/* DB 작업 
		 * 1. 커넥션 풀에서 Connection 객체 얻어오기
		 * 2. 싱글톤 방식으로 MemberDAO 클래스의 인스턴스를 get 
		 * 3. MemberDAO 클래스에서 DB 작업을 수행할 때 사용할 Connection 객체를 주입 
		 * 4. DB에 회원 갱신(UPDATE) 메서드 호출
		 * */
		Connection conn = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(conn);
		
		int updateCount = memberDAO.updateMember(member);
		
		if(updateCount > 0) {
			commit(conn);
			updateResult = true;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		System.out.println(" M.ModifyPro.S : updateMember() 종료");
		return updateResult;
	}

}
