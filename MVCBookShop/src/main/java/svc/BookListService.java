package svc;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dao.BookDAO;
import vo.Book;

import static db.JdbcUtil.*;

/** 책 상품 목록보기 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스*/
public class BookListService {

	/** DB에 저장된 총 책 개수(count(*))를 구하는 메서드 
	 * @throws SQLException */
	public int getListCount() throws SQLException {
		System.out.println(" B.List.S : getListCount(1) 호출");
		
		int listCount = 0;
		
		/* DB 처리 */
		Connection conn = getConnection();
		BookDAO boardDAO = BookDAO.getInstance();
		boardDAO.setConnection(conn);
		listCount = boardDAO.selectListCount();
		close(conn);
		
		System.out.println(" B.List.S : getListCount(1) 종료");
		return listCount;
	}
	
	public int getListCount(String mnName, int mdCode) throws SQLException {
		System.out.println(" B.List.S : getListCount(2) 호출");
		
		int listCount = 0;
		
		/* DB 처리 */
		Connection conn = getConnection();
		BookDAO boardDAO = BookDAO.getInstance();
		boardDAO.setConnection(conn);
		listCount = boardDAO.selectListCount(mnName, mdCode);
		close(conn);
		
		System.out.println(" B.List.S : getListCount(2) 종료");
		return listCount;
	}
	
	public int getListCount(LocalDate mondayOfWeek, LocalDate sundayOfWeek, String mnName, int mdCode) throws SQLException {
		System.out.println(" B.List.S : getListCount(4) 호출");
		
		int listCount = 0;
		
		/* DB 처리 */
		Connection conn = getConnection();
		BookDAO boardDAO = BookDAO.getInstance();
		boardDAO.setConnection(conn);
		listCount = boardDAO.selectListCount(mondayOfWeek, sundayOfWeek, mnName, mdCode);
		close(conn);
		
		System.out.println(" B.List.S : getListCount(4) 종료");
		return listCount;
	}
	
	/** 모든 책 상품 목록을 ArrayList 객체 타입으로 반환하는 메서드 
	 * 관리자 책 관리 페이지에서 전체 목록을 가져와 웹 브라우저에서 처리할 것 */
	public ArrayList<Book> getBookList() throws SQLException {
		System.out.println(" B.List.S : getBookList() 호출");

		/* DB 처리 */
		BookDAO bookDAO = BookDAO.getInstance();
		Connection conn = getConnection();
		bookDAO.setConnection(conn);
		ArrayList<Book> bookList = bookDAO.selectBookList();
		System.out.println(" B.List.S bookList.get(0) : " + bookList.get(0).toString());
		close(conn);
		
		System.out.println(" B.List.S : getBookList() 종료");
		return bookList;
	}
	
	public ArrayList<Book> getBookList(int page, int limit) throws SQLException {
		System.out.println(" B.List.S : getBookList(2) 호출");

		/* DB 처리 */
		BookDAO bookDAO = BookDAO.getInstance();
		Connection conn = getConnection();
		bookDAO.setConnection(conn);
		ArrayList<Book> bookList = bookDAO.selectBookList(page, limit);
		System.out.println(" B.List.S bookList.get(0) : " + bookList.get(0).toString());
		close(conn);
		
		System.out.println(" B.List.S : getBookList(2) 종료");
		return bookList;
	}

	
	/** 파라미터로 넘어온 페이지에서 출력될 책 상품 목록을 ArrayList 객체 타입으로 반환하는 메서드 */
	public ArrayList<Book> getBookList(int page, String sort, String mnName, int mdCode, int limit) throws SQLException {
		System.out.println(" B.List.S : getBookList(5) 호출");

		/* DB 처리 */
		BookDAO bookDAO = BookDAO.getInstance();
		Connection conn = getConnection();
		bookDAO.setConnection(conn);
		ArrayList<Book> bookList = bookDAO.selectBookList(page, sort, mnName, mdCode, limit);
		close(conn);
		
		System.out.println(" B.List.S : getBookList(5) 종료");
		return bookList;
	}
	
	public ArrayList<Book> getBookList(int page, LocalDate mondayOfWeek, LocalDate sundayOfWeek, String sort, 
										String mnName, int mdCode, int limit) throws SQLException {
		System.out.println(" B.List.S : getBookList(7) 호출");

		/* DB 처리 */
		BookDAO bookDAO = BookDAO.getInstance();
		Connection conn = getConnection();
		bookDAO.setConnection(conn);
		ArrayList<Book> bookList = bookDAO.selectBookList(page, mondayOfWeek, sundayOfWeek, sort, mnName, mdCode, limit);
		close(conn);
		
		System.out.println(" B.List.S : getBookList(7) 종료");
		return bookList;
	}
	
}
