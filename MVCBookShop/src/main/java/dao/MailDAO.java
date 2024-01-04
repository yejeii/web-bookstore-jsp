package dao;

import static db.JdbcUtil.close;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import vo.Book;
import vo.Mail;


public class MailDAO {

	Connection conn;
	private static MailDAO mailDAO;
	
	// alt shift s
	// 외부 클래스에서 생성자를 사용해 객체를 새로 생성할 수 없도록 생성자의 접근 제한자를 private으로 지정
	private MailDAO() {
		// TODO Auto-generated constructor stub
	}
	
	/** 싱글톤 패턴으로 MailDAO 객체를 생성하여 리턴하는 메서드 */
	public static MailDAO getInstance() {
		if(mailDAO == null) {
			mailDAO = new MailDAO();
		}
		return mailDAO;
	}
	
	/** MailDAO 객체에 Connection 객체를 주입하는 메서드 */
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	/** 전체 이메일 개수 구하는 메서드 */
	public int selectListCount() {
		System.out.println(" Mail.DAO : selectListCount() 호출");
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select count(*) from jspbookshop.mail");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(" Mail.DAO selectListCount ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" Mail.DAO : selectListCount() 종료");
		return listCount;
	}
	
	/** 보내거나 받은 메일 개수를 구하는 메서드 */
	public int selectListCount(String string) {
		System.out.println(" Mail.DAO : selectListCount(String string) 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;
		
		String sql = "SELECT count(*) FROM jspbookshop.mail "
	            + "WHERE ml_sender LIKE ? OR ml_recipient LIKE ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + string + "%");
			pstmt.setString(2, "%" + string + "%");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(" Mail.DAO selectListCount ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" Mail.DAO : selectListCount(String string) 종료");
		return listCount;
	}
	
	/** 중요 메일 개수 또는 미열람 메일 개수를 구하는 메서드 */
	public int selectListCount(int i) {
		System.out.println(" Mail.DAO : selectListCount(int i) 호출");
		
		/* i == 1 : 중요 메일 개수 count
		 * i == 0 : 미열람 메일 개수 count */
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int listCount = 0;
		
		String sql;
		if(i == 0) sql = "SELECT COUNT(*) FROM jspbookshop.mail WHERE ml_read = ?";
		else sql= "SELECT count(*) FROM jspbookshop.mail WHERE ml_importance = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(" Mail.DAO selectListCount ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" Mail.DAO : selectListCount(int i) 종료");
		return listCount;
	}
	
	/** 전체 메일 목록을 반환하는 메서드 */
	public ArrayList<Mail> selectMailList() {
		System.out.println(" Mail.DAO : selectMailList() 호출");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Mail> mailList = null;
		
		String sql = "SELECT * FROM jspbookshop.mail order by ml_sendTime desc";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				mailList = new ArrayList<>();
				
				do {
					mailList.add(new Mail(rs.getInt(1), 
											rs.getString(2).split(","), 
											rs.getString(3), 
											rs.getString(4), 
											rs.getString(5),
											rs.getString(6),
											rs.getInt(7),
											rs.getInt(8),
											rs.getTimestamp(9)));
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(" Mail.DAO : selectMailList ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" Mail.DAO : selectMailList() 종료");
		return mailList;
	}
	
	/** 보내거나 받은 메일 목록을 반환하는 메서드 */
	public ArrayList<Mail> selectMailList(String string) {
		System.out.println(" Mail.DAO : selectMailList(String string) 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Mail> mailList = null;
		
		String sql = "SELECT * FROM jspbookshop.mail "
	            + "WHERE ml_sender LIKE ? OR ml_recipient LIKE ? "
	            + "ORDER BY ml_sendTime DESC";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + string + "%");
			pstmt.setString(2, "%" + string + "%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				mailList = new ArrayList<>();
				
				do {
					mailList.add(new Mail(rs.getInt(1), 
											rs.getString(2).split(","), 
											rs.getString(3), 
											rs.getString(4), 
											rs.getString(5),
											rs.getString(6),
											rs.getInt(7),
											rs.getInt(8),
											rs.getTimestamp(9)));
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(" Mail.DAO : selectMailList ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" Mail.DAO : selectMailList(String string) 종료");
		return mailList;
	}

	/** 중요 메일만 구하는 메서드 */
	public ArrayList<Mail> selectMailList(int i) {
		System.out.println(" Mail.DAO : selectMailList(int i) 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Mail> mailList = null;
		
		String sql = "SELECT * FROM jspbookshop.mail where ml_importance = ? order by ml_sendTime desc";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, i);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				mailList = new ArrayList<>();
				
				do {
					mailList.add(new Mail(rs.getInt(1), 
											rs.getString(2).split(","), 
											rs.getString(3), 
											rs.getString(4), 
											rs.getString(5),
											rs.getString(6),
											rs.getInt(7),
											rs.getInt(8),
											rs.getTimestamp(9)));
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(" Mail.DAO : selectMailList ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" Mail.DAO : selectMailList(int i) 종료");	
		return mailList;
	}
	
	/** 메일의 상세정보를 구하는 메서드 */
	public Mail selectMail(int ml_index) {
		System.out.println(" Mail.DAO : selectMail() 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Mail mail = null;
		
		try {
			pstmt = conn.prepareStatement("select * from jspbookshop.mail where ml_index=?");
			pstmt.setInt(1, ml_index);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mail = new Mail(rs.getInt(1), 
						rs.getString(2).split(","), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getInt(8),
						rs.getTimestamp(9));
			}
		} catch (Exception e) {
			System.err.println(" Mail.DAO : selectMail() ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" Mail.DAO : selectMail() 종료");
		return mail;
	}
	
	/** 새로운 이메일을 DB에 추가하는 메서드 */
	public int insertMail(Mail mail) {
		System.out.println(" Mail.DAO : insertMail() 호출");
		
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		System.out.println(" Mail.DAO : 새로 저정될 책 정보 - "+mail.toString());
		
		try {
			String sql = "insert into jspbookshop.mail values(NULL,?,?,?,?,?,default,default,now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Arrays.toString(mail.getMl_recipient()).substring(1, Arrays.toString(mail.getMl_recipient()).length()-1));
			pstmt.setString(2, mail.getMl_sender());
			pstmt.setString(3, mail.getMl_attachment().toString().substring(1, mail.getMl_attachment().toString().length()-1));
			pstmt.setString(4, mail.getMl_title());
			pstmt.setString(5, mail.getMl_text());
			
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" Mail.DAO : insertMail() ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" Mail.DAO : insertMail() 종료");
		return insertCount;
	}
	
	/** 상세보기 요청 시 열람처리하는 메서드 */
	@SuppressWarnings("resource")
	public int updateRead(int ml_index) {
		System.out.println(" Mail.DAO : updateRead() 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int updateReadCount = 0;
		String sql = "select ml_read from jspbookshop.mail where ml_index="+ml_index;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			// 미열람일때만 열람되도록 설정
			if(rs.next() && rs.getInt(1) == 0) {
				String update_sql = "update jspbookshop.mail "
								+ "set ml_read = 1 "
								+ "where ml_index = "+ml_index;
				
				
				pstmt = conn.prepareStatement(update_sql);
				updateReadCount = pstmt.executeUpdate();
			} 
			
		} catch (Exception e) {
			System.out.println(" Mail.DAO : updateRead() ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" Mail.DAO : updateRead() 종료");
		return updateReadCount;
	}

	/** 이메일 중요도를 처리하는 메서드 */
	public int updateStar(int ml_index, int star) {
		System.out.println(" Mail.DAO : updateStar() 호출");
		
		PreparedStatement pstmt = null;
		int updateStarCount = 0;
		String sql = "update jspbookshop.mail set ml_importance = ? where ml_index= ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, star);
			pstmt.setInt(2, ml_index);
			
			updateStarCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" Mail.DAO : updateStar() ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" Mail.DAO : updateStar() 종료");
		return updateStarCount;
	}


}
