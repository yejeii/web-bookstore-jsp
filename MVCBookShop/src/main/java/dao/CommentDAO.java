package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Comment;

public class CommentDAO {

	Connection conn;
	private static CommentDAO commentDAO;

	// 외부 클래스에서 생성자를 사용해 객체를 새로 생성할 수 없도록 생성자의 접근 제한자를 private으로 지정
	private CommentDAO() {
	}
	
	/** 싱글톤 패턴으로 CommentDAO 객체를 생성하여 리턴하는 메서드 */
	public static CommentDAO getInstance() {
		if(commentDAO == null) {
			commentDAO = new CommentDAO();
		}
		return commentDAO;
	}
	
	/** CommentDAO 객체에 Connection 객체를 주입하는 메서드 */
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	/** c_b_id에 해당하는 모든 코멘트 개수를 구하는 메서드 */
	public int selectCommentCount(int c_b_id) {
		System.out.println(" C.DAO : selectCommentCount() 호출");
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select count(*) from jspbookshop.comment where c_b_id=?");
			pstmt.setInt(1, c_b_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			else {
				listCount = 0;
			}
		} catch (Exception e) {
			System.out.println(" R.DAO : selectReviewCount() ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" C.DAO : selectCommentCount() 종료");
		return listCount;
	}
	
	/** 가장 최근에 등록된 c_id 구하는 메서드 */
	public int selectMaxPk() {
		System.out.println(" C.DAO : selectMaxPk() 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select max(c_id) from jspbookshop.comment";
		int maxPk = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) maxPk = rs.getInt(1);
			else maxPk = 1;
		} catch (Exception e) {
			System.out.println(" C.DAO : selectMaxPk() ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" C.DAO : selectMaxPk() 종료");
		return maxPk;
	}
	
	/** 원글 코멘트를 등록하는 메서드 */
	public int insertComment(Comment comment) {
		System.out.println(" C.DAO : insertComment() 호출");
		
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		System.out.println(" C.DAO : 새로 저정될 리뷰  - "+comment.toString());
		
		try {
			// 가장 최근에 등록된 c_id 구하기
			int maxPk = selectMaxPk();
			
			String sql = "insert into jspbookshop.comment values(NULL,?,?,?,?,now(),0,?,0,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getC_b_id());
			pstmt.setString(2, comment.getC_m_id());
			pstmt.setString(3, comment.getC_title());
			pstmt.setString(4, comment.getC_text());
			pstmt.setInt(5, maxPk+1);
			
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" C.DAO : insertComment() ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" C.DAO : insertComment() 종료");
		return insertCount;
	}
	
	/** 대댓글 코멘트를 등록하는 메서드 */
	@SuppressWarnings("resource")
	public int insertComment(Comment comment, int p_ref, int p_lev, int p_seq) {
		System.out.println(" C.DAO : insertComment(댓글) 호출");
		
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		System.out.println(" C.DAO : 새로 저장될 코멘트  - "+comment.toString());
		System.out.println(" C.DAO : 새로 저장될 코멘트의 부모ref, lev, seq  - "+p_ref+" "+p_lev+" "+p_seq);

		/* 대댓글 처리 방법(ref, lev, seq) 
		 * 1. 같은 그룹(ref)의 코멘트들의 출력순서(seq)를 1씩 증가시킨다.
		 * 2. 새로 등록할 대댓글 코멘트의 출력순서(seq)와 깊이(lev)를 1씩 증가시켜 insert한다.
		 * */
		String sql = "update jspbookshop.comment "
				+ "set c_seq = c_seq+1 "
				+ "where c_ref=? and c_seq>?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_ref);
			pstmt.setInt(2, p_seq);
			int updateCount = pstmt.executeUpdate();
			
			if(updateCount > 0) {
				commit(conn);
			}
			
			int c_seq = p_seq + 1;
			int c_lev = p_lev + 1;
			sql = "insert into jspbookshop.comment values(NULL,?,?,?,?,now(),?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getC_b_id());
			pstmt.setString(2, comment.getC_m_id());
			pstmt.setString(3, comment.getC_title());
			pstmt.setString(4, comment.getC_text());
			pstmt.setInt(5, comment.getC_empathy());
			pstmt.setInt(6, p_ref);
			pstmt.setInt(7, c_lev);
			pstmt.setInt(8, c_seq);
			
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" C.DAO : insertComment() ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" C.DAO : insertComment(댓글) 종료");
		return insertCount;
	}

	/** b_id에 해당하는 모든 코멘트를 구하는 메서드 */
	public ArrayList<Comment> selectCommentList(int c_b_id) {
		System.out.println(" C.DAO : selectCommentList() 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Comment> commestList = null;
		
		String sql = "select * from jspbookshop.comment where c_b_id="+c_b_id+" order by c_ref desc, c_seq asc";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				commestList = new ArrayList<>();
				
				do {
					Comment comment = new Comment();
					comment.setC_id(rs.getInt("c_id"));
					comment.setC_b_id(c_b_id);
					comment.setC_m_id(rs.getString("c_m_id"));
					comment.setC_title(rs.getString("c_title"));
					comment.setC_text(rs.getString("c_text"));
					comment.setC_regdate(rs.getDate("c_regdate"));
					comment.setC_empathy(rs.getInt("c_empathy"));
					comment.setC_ref(rs.getInt("c_ref"));
					comment.setC_lev(rs.getInt("c_lev"));
					comment.setC_seq(rs.getInt("c_seq"));
					
					commestList.add(comment);
				} while(rs.next());
			}
		} catch (Exception e) {
			System.out.println(" C.DAO : selectCommentList ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" C.DAO : selectCommentList() 종료");
		return commestList;
	}

	/** 코멘트 공감을 1 증가시키는 메서드 */
	public int updateEmpathy(int c_id) {
		System.out.println(" C.DAO : updateEmpathy() 호출");
		
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update jspbookshop.comment set c_empathy = c_empathy+1 where c_id = "+c_id;
		
		try {
			pstmt = conn.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" C.DAO : updateEmpathy ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" C.DAO : updateEmpathy() 종료");
		return updateCount;
	}



	
	
	
	
}
