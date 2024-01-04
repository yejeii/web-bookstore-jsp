package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CommentDAO;

/** 코멘트의 공감을 1 증가시키는 비즈니스 로직을 처리하는 Service 클래스 */
public class BookCommentEmpathyService {

	/** 공감을 1 증가시키는 메서드 */
	public boolean updateEmpathy(int c_id) {
		System.out.println(" B.CommentEmpathy.S : updateEmpathy() 호출");
		
		boolean isUpdateSuccess = false;
		
		/* DB 작업 */
		Connection conn = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(conn);
		int updateCount = commentDAO.updateEmpathy(c_id);
		
		if(updateCount > 0) {
			// 성공적으로 삽입된 경우. 트랜잭션 영구반영
			commit(conn);
			isUpdateSuccess = true;
		} else rollback(conn);
		
		close(conn);
		
		System.out.println(" B.CommentEmpathy.S  isUpdateSuccess " + isUpdateSuccess);
		System.out.println(" B.CommentEmpathy.S : updateEmpathy() 종료");
		return isUpdateSuccess;
	}
	

}
