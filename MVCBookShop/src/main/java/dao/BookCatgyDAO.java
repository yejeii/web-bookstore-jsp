package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.exceptions.RSAException;

import vo.BookCatgyCode;

public class BookCatgyDAO {

	Connection conn;
	private static BookCatgyDAO catgyDAO;
	
	// alt shift s
	// 외부 클래스에서 생성자를 사용해 객체를 새로 생성할 수 없도록 생성자의 접근 제한자를 private으로 지정
	private BookCatgyDAO() {
		// TODO Auto-generated constructor stub
	}
	
	/** 싱글톤 패턴으로 catgyDAO 객체를 생성하여 리턴하는 메서드 */
	public static BookCatgyDAO getInstance() {
		if(catgyDAO == null) {
			catgyDAO = new BookCatgyDAO();
		}
		return catgyDAO;
	}
	
	/** catgyDAO 객체에 Connection 객체를 주입하는 메서드 */
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	/** 카테고리를 조회하는 메서드 */
	public ArrayList<BookCatgyCode> selectCatgyList() {
		System.out.println(" BC.DAO : selectCatgyList() 호출");

		ResultSet rs = null;
		ArrayList<BookCatgyCode> catgyList = null;
		
		String sql = "SELECT bc_code, TRIM(bc_name), bc_code_ref_md, bc_code_ref_mn FROM jspbookshop.bookcatgycode";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				catgyList = new ArrayList<>();
				do {
					BookCatgyCode catgy = new BookCatgyCode();
					catgy.setCode(rs.getInt(1));
					catgy.setName(rs.getString(2));
					catgy.setCode_ref_md(rs.getInt(3));
					catgy.setCode_ref_mn(rs.getInt(4));
					
					catgyList.add(catgy);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(" BC.DAO : selectCatgyList ERROR : "+e);
		} finally {
			close(rs);
		}
		
		System.out.println(" BC.DAO : selectCatgyList() 종료");
		return catgyList;
	}
	
	
}
