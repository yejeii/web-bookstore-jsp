package svc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BookDAO;
import vo.Book;
import static db.JdbcUtil.*;

/** 책 상품 상세 정보보기 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스*/
public class BookViewService {

	/** B_ID에 해당하는 책 정보를 리턴하는 메서드 
	 * @throws SQLException */
	public Book getBookView(int b_id) throws SQLException {
		System.out.println(" B.View.S : getBookView() 호출");
		
		/* DB 처리
		 * 1. 커넥션 객체 생성
		 * 2. 상세 정보를 요청하는 책 상품을 조회수를 증가시키는 메서드 호출 
		 * 3. B_ID에 해당하는 책 정보를 리턴하는 메서드 호출
		 * */
		Connection conn = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(conn);
		
		int updateCount = bookDAO.updateReadCount(b_id);
		
		if(updateCount > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		Book book = bookDAO.selectBook(b_id);
		close(conn);
		
		System.out.println(" B.View.S : getBookView() 종료");
		return book;
	}

	/** B_ID에 해당하는 도서 서브 분류를 가져오는 메서드 
	 * @throws SQLException */
	public List<Object> getBookSubCatgy(int b_id) throws SQLException {
		System.out.println(" B.View.S : getBookSubCatgy() 호출");
		
		/* DB 처리
		 * 1. 커넥션 객체 생성
		 * 2. 상세 정보를 요청하는 책 상품을 조회수를 증가시키는 메서드 호출 
		 * 3. B_ID에 해당하는 책 정보를 리턴하는 메서드 호출
		 * */
		Connection conn = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(conn);
		
		// null 가능
		List<Object> bookSubCatgyList = bookDAO.selectBookSubCatgy(b_id);
		
		System.out.println(" B.View.S : getBookSubCatgy() 종료");
		return bookSubCatgyList;
	}

}
