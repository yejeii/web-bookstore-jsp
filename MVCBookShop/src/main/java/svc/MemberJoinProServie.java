package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MemberDAO;
import vo.Member;

/** 회원 등록 요청을 처리하는 Service 클래스 */
public class MemberJoinProServie {

	/** 회원 등록 처리 메서드 */
	public boolean joinMember(Member member) {
		System.out.println(" M.JoinPro.S : joinMember() 호출");

		/* DB 작업 
		 * 1. 커넥션 풀에서 Connection 객체 얻어오기
		 * 2. 싱글톤 방식으로 MemberDAO 클래스의 인스턴스를 get 
		 * 3. MemberDAO 클래스에서 DB 작업을 수행할 때 사용할 Connection 객체를 주입 
		 * 4. DB에 회원 삽입(INSERT) 메서드 호출
		 * */
		Connection conn = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(conn);
		int insertCount = memberDAO.insertMember(member);
		
		boolean joinSuccess = false;

		if(insertCount > 0) {
			// 성공적으로 삽입된 경우. 트랜잭션 영구반영
			commit(conn);
			joinSuccess = true;
		} else {
			rollback(conn);
		}
		
		close(conn);

		System.out.println(" M.JoinPro.S : joinSuccess - " + joinSuccess);
		System.out.println(" M.JoinPro.S : joinMember() 종료");
		return joinSuccess;
	}

}
