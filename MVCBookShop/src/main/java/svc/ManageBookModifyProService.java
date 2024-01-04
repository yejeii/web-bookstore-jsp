package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.SQLException;

import dao.BookDAO;
import vo.Book;

/** 책 정보 수정하기 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스*/
public class ManageBookModifyProService {
	
	/** 책 이미지 정보 가져오는 메서드 
	 * @throws SQLException */
	public String getBookImage(int b_id) throws SQLException {
		System.out.println(" M.BookModifyPro.S : getBookImage() 호출");
		
		String imageFileName = null;
		
		/* DB 처리 */
		BookDAO bookDAO = BookDAO.getInstance();
		Connection conn = getConnection();
		bookDAO.setConnection(conn);
		imageFileName = bookDAO.getBookImage(b_id);
		close(conn);
		
		System.out.println(" M.BookModifyPro.S : 이미지 파일명 - "+imageFileName);
		System.out.println(" M.BookModifyPro.S : getBookImage() 종료");
		return imageFileName;
	}
	
	/** 수정 처리 메서드 
	 * @throws SQLException */
	public boolean modifyBook(Book book, int[] sub_catgy_arr) {
		System.out.println(" M.BookModifyPro.S : modifyBook() 호출");
		
		boolean isModifySuccess = false;
		
		/* DB 처리 */
		BookDAO bookDAO = BookDAO.getInstance();
		Connection conn = getConnection();
		int updateCount = 0;
		try {
			bookDAO.setConnection(conn);
			updateCount = bookDAO.updateBook(book, sub_catgy_arr);
			
			// 예외 발생은 안했지만 updateCount이 0보다 작을 때를 위해 한 번 더 처리
			if(updateCount > 0) {
				commit(conn);
				isModifySuccess = true;
			} else {
				rollback(conn);
			}
		} catch (SQLException e) {
			System.out.println(" SQLException 예외 발생 ");
			rollback(conn);
		} catch (Exception e) {
			System.out.println(" Exception 예외 발생 ");
			System.out.println(e);
			rollback(conn);
		} finally {
			close(conn);
		}
		
		System.out.println(" M.BookModifyPro.S updateCount : " +updateCount);
		System.out.println(" M.BookModifyPro.S : modifyBook() 종료");
		return isModifySuccess;
	}

	
}
