package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import db.JdbcUtil;

import dao.MailDAO;

/** 이메일 중요도를 설정하는 비즈니스 로직이 있는 Service 클래스 */
public class EmailUpdateStarProService {

	/** 이메일 중요도 재설정하는 메서드 */
	public boolean updateStar(int ml_index, int star) {
		System.out.println(" EmailUpdateStarPro.S : updateStar() 호출");
		
		boolean updateResult = false;
		
		/* DB 작업 
		 * 1. 커넥션 풀에서 Connection 객체 얻어오기
		 * 2. 싱글톤 방식으로 MailDAO 클래스의 인스턴스를 get 
		 * 3. MailDAO 클래스에서 DB 작업을 수행할 때 사용할 Connection 객체를 주입 
		 * 4. DB에 이메일 star 갱신(UPDATE) 메서드 호출
		 * */
		Connection conn = JdbcUtil.getConnection();
		MailDAO mailDAO = MailDAO.getInstance();
		mailDAO.setConnection(conn);
		int updateCount = mailDAO.updateStar(ml_index, star);
		
		if(updateCount > 0) {
			commit(conn);
			updateResult = true;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		System.out.println(" EmailUpdateStarPro.S : updateStar() 종료");
		return updateResult;
	}

}
