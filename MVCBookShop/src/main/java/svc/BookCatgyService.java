package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BookCatgyDAO;
import db.JdbcUtil;
import vo.BookCatgyCode;

/** 도서 카테고리 관련 Service 클래스 */
public class BookCatgyService {

	/** 카테고리를 가져오는 메서드 */
	public ArrayList<BookCatgyCode> getCatgyList() {

		System.out.println(" B.Catgy.S : getCatgyList() 호출");
		
		Connection conn = JdbcUtil.getConnection();
		BookCatgyDAO catgyDAO = BookCatgyDAO.getInstance();
		catgyDAO.setConnection(conn);
		
		ArrayList<BookCatgyCode> catgyList = catgyDAO.selectCatgyList();
		
		for(int i=0; i<3; i++) System.out.println(catgyList.get(i).toString());
		System.out.println(" B.Catgy.S : getCatgyList() 종료");
		return catgyList;
	}

	
}
