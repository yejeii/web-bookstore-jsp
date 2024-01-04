package svc;

import dao.BookDAO;
import dao.CartDAO;
import vo.Book;
import vo.Cart;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookCartAddService {

	/** b_id에 따른 도서 정보를 가져오는 메서드 
	 * @throws SQLException */
	public Book getBookCart(int b_id) throws SQLException {
		System.out.println(" B.CartAdd.S : getBookCart() 호출");
		
		/* DB 처리
		 * 1. 커넥션 객체 생성
		 * 2. 글 번호에 해당하는 글의 정보를 리턴하는 메서드 호출
		 * */
		Connection conn = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(conn);
		
		Book book = bookDAO.selectBook(b_id);
		
		close(conn);
		
		System.out.println(" B.CartAdd.S : getBookCart() 종료");
		return book;
	}

	/** 도서 아이디와 로그인 아이디에 따른 장바구니 도서 정보를 가져오는 메서드 */
	public Cart getBookCart(int b_id, String s_userId) {

		System.out.println(" B.CartAdd.S : getBookCart() 호출");
		
		Connection conn = getConnection();
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(conn);
		
		Cart cart = cartDAO.selectCart(b_id, s_userId);
		
		System.out.println(" B.CartAdd.S : getBookCart() 종료");
		return cart;
	}
	
	/** 도서 아이디(배열)와 로그인 아이디를 장바구니 테이블에 저장하는 메서드 
	 * @throws SQLException */
	public boolean addCart(ArrayList<Integer> idList, String s_userId) throws SQLException {
		System.out.println(" B.CartAdd.S : addCart(idList) 호출");
		
		boolean isRegistSuccess = false;
		
		Connection conn = getConnection();
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(conn);
		
		int insertCount = cartDAO.insertCart(idList, s_userId);
		
		if(insertCount > 0) {
			// 성공적으로 삽입된 경우. 트랜잭션 영구반영
			commit(conn);
			isRegistSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		System.out.println(" B.CartAdd.S - insertCount : "+insertCount);
		System.out.println(" B.CartAdd.S - isRegistSuccess" + isRegistSuccess);
		System.out.println(" B.CartAdd.S : addCart(idList) 종료");
		return isRegistSuccess;
	}
	
	/** 도서 아이디와 로그인 아이디를 장바구니 테이블에 저장하는 메서드*/
	public boolean addCart(int b_id, String s_userId) {
		System.out.println(" B.CartAdd.S : addCart(b_id) 호출");
		
		boolean isRegistSuccess = false;
		
		Connection conn = getConnection();
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(conn);
		
		int insertCount = cartDAO.insertCart(b_id, s_userId);
		
		if(insertCount > 0) {
			// 성공적으로 삽입된 경우. 트랜잭션 영구반영
			commit(conn);
			isRegistSuccess = true;
		} else {
			rollback(conn);
		}
		close(conn);
		
		System.out.println(" B.CartAdd.S - isRegistSuccess" + isRegistSuccess);
		System.out.println(" B.CartAdd.S : addCart(b_id) 종료");
		return isRegistSuccess;
	}

	/** 도서 상품 수량을 1 증가시키는 메서드 */
	public void updateCartQty(int b_id, String m_id) {
		System.out.println(" B.CartAdd.S : updateCartQty() 호출");
		
		/* DB 처리
		 * 1. 커넥션 객체 생성
		 * 2. 글 번호에 해당하는 글의 정보를 리턴하는 메서드 호출
		 * */
		Connection conn = getConnection();
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(conn);
		
		int updateCount = cartDAO.updateQty(b_id, m_id, "up");
		
		if(updateCount > 0) {
			// 성공적으로 삽입된 경우. 트랜잭션 영구반영
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		System.out.println(" B.CartAdd.S : updateCartQty() 종료");
	}


}
