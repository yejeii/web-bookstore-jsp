package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.catalina.startup.AddPortOffsetRule;

import vo.Faq;

public class FaqDAO {

	Connection conn;
	private static FaqDAO faqDAO;
	
	// 외부 클래스에서 생성자를 사용해 객체를 새로 생성할 수 없도록 생성자의 접근 제한자를 private으로 지정
	private FaqDAO() {
	}

	/** 싱글톤 패턴으로 FaqDAO 객체를 생성하여 리턴하는 메서드 */
	public static FaqDAO getInstance() {
		if(faqDAO == null) {
			faqDAO = new FaqDAO();
		}
		return faqDAO;
	}

	/** FaqDAO 객체에 Connection 객체를 주입하는 메서드 */
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	/** fcode에 따른 faq 목록을 가져오는 메서드 */
	public ArrayList<Faq> selectFaqList(int fcode) {
		System.out.println(" F.DAO : selectFaqList(fcode) 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Faq> faqList = null;
		
		// fcode == 0(Best10)인 경우
		String sql = "select f.f_id, f.f_title, f.f_text, fk.fk_code, fk.fk_value "
					+ "from jspbookshop.faqkeyword fk join jspbookshop.faq f "
					+ "on f.f_fk_code = fk.fk_code "
					+ "and f.f_use = 1 "
					+ "order by f.f_readcnt desc, fk.fk_fc_code asc "
					+ "limit 10";
		
		if(fcode != 0) {
			sql = "select f.f_id, f.f_title, f.f_text, fk.fk_code, fk.fk_value "
					+ "from jspbookshop.faqkeyword fk join jspbookshop.faq f "
					+ "on f.f_fk_code = fk.fk_code "
					+ "and fk.fk_fc_code = "+fcode+" "
					+ "and f.f_use = 1 "
					+ "order by f.f_readcnt desc, fk.fk_fc_code asc";
		}

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				faqList = new ArrayList<Faq>();
				
				do {
					faqList.add(new Faq(rs.getInt(1), rs.getInt(4), rs.getString(2), rs.getString(3), rs.getString(5)));
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(" F.DAO : selectFaqList ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" F.DAO : selectFaqList(fcode) 종료");
		return faqList;
	}

	/** fc_code, fk_code에 따른 FAQ 데이터를 ArrayList 객체 타입으로 반환하는 메서드 */
	public ArrayList<Faq> selectFaqList(int fc_code, int fk_code) {
		System.out.println(" F.DAO : selectFaqList(fc_code, fk_code) 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Faq> faqList = null;
		
		String sql  = "select f.f_id, f.f_title, f.f_text, fk.fk_code, fk.fk_value "
					+ "from faqkeyword fk join faq f "
					+ "on f.f_fk_code = fk.fk_code ";		
		
		if(fk_code == 0) {
			// 전체
			sql += "and fk.fk_fc_code = " + fc_code + " "
				 + "and f.f_use = 1 "
				 + "order by f.f_id asc";
		} else {
			sql += "and fk.fk_fc_code = " + fc_code + " "
				 + "and fk.fk_code = " + fk_code + " "
				 + "and f.f_use = 1 "
				 + "order by f.f_id asc";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				faqList = new ArrayList<Faq>();
				
				do {
					faqList.add(new Faq(rs.getInt(1), 
										rs.getInt(4), 
										rs.getString(2), 
										rs.getString(3), 
										rs.getString(5)));
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(" F.DAO : selectFaqList(fc_code, fk_code) ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" F.DAO : selectFaqList(fc_code, fk_code) 종료");
		return faqList;
	}

	
	
}
