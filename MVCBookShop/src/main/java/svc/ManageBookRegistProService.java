package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.SQLException;

import dao.BookDAO;
import vo.Book;

/** 책 상품 등록 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스*/
public class ManageBookRegistProService {

	/** 책 상품을 DB에 저장하는 메서드 
	 * @param b_sub_catgy 
	 * @throws SQLException */
	public boolean insertBook(Book book, int[] sub_catgy_arr) throws SQLException {
		System.out.println(" M.BookRegistPro.S : insertBook() 호출");
		
		boolean isRegistSuccess = false;
		
		/* DB 작업 
		 * 1. 커넥션 풀에서 Connection 객체 얻어오기
		 * 2. 싱글톤 방식으로 BookDAO 클래스의 인스턴스를 get 
		 * 3. BookDAO 클래스에서 DB 작업을 수행할 때 사용할 Connection 객체를 주입 
		 * 4. DB에 새로운 글 갱신(INSERT) 메서드 호출
		 * */
		Connection conn = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(conn);
		int insertCount = bookDAO.insertBook(book, sub_catgy_arr);
		
		if(insertCount > 0) {
			// 성공적으로 삽입된 경우(book 테이블 및 booksubcatgy 테이블)
			commit(conn);
			isRegistSuccess = true;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		System.out.println(" M.BookRegistPro.S : isRegistSuccess - " + isRegistSuccess);
		System.out.println(" M.BookRegistPro.S : insertBook() 종료");
		return isRegistSuccess;
	}

}
