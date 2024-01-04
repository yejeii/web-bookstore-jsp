package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.SQLException;

import dao.BookDAO;

/** 책 삭제하기 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스*/
public class ManageBookDeleteProService {

	/** 삭제 처리 메서드 
	 * @throws SQLException */
	public boolean deleteBook(int b_id) throws SQLException {
		System.out.println(" M.BookDeletePro.S : deleteBook() 호출");
		
		boolean isRemoveSuccess = false;
		
		/* DB 처리 */
		Connection conn = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(conn);
		
		int deleteCount = bookDAO.deleteBook(b_id);
		
		if(deleteCount > 0) {
			commit(conn);
			isRemoveSuccess = true;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		System.out.println(" M.BookDeletePro.S : deleteBook() 종료");
		return isRemoveSuccess;
	}

}
