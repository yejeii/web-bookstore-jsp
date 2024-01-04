package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.catalina.startup.AddPortOffsetRule;

import vo.Faq;
import vo.FaqKeyword;

public class FaqKeyDAO {

	Connection conn;
	private static FaqKeyDAO faqKeyDAO;
	
	private FaqKeyDAO() {
	}
	
	public static FaqKeyDAO getInstance() {
		if(faqKeyDAO == null) {
			faqKeyDAO = new FaqKeyDAO();
		}
		return faqKeyDAO;
	}
	
	/** FaqKeyDAO 객체에 Connection 객체를 주입하는 메서드 */
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	/** fcode에 따른 fkeyword를 가져오는 메서드 */
	public ArrayList<FaqKeyword> selectFkList(int fcode) {
		System.out.println(" Fk.DAO : selectFkList(fcode) 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FaqKeyword> fkList = null;
		
		/* 2개의 테이블 조인하여 fc_code=?인 값에 따른 fcode 정보와 fkeyword 정보를 가져오는 qeury */
		String sql = "select fc.fc_code, fc.fc_value, fk.fk_code, fk.fk_value "
					+ "from faqcode fc join faqkeyword fk "
					+ "on fc.fc_code = fk.fk_fc_code "
					+ "and fc.fc_code = "+fcode;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				fkList = new ArrayList<FaqKeyword>();
				
				do {
					fkList.add(new FaqKeyword(rs.getInt(1), 
												rs.getString(2),
											 	rs.getInt(3),
											 	rs.getString(4)));
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(" Fk.DAO : selectFkList ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" Fk.DAO : selectFkList(fcode) 종료");
		return fkList;
	}
}
