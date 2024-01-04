package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Review;

public class ReviewDAO {

	Connection conn;
	private static ReviewDAO reviewDAO;
	
	// alt shift s
	// 외부 클래스에서 생성자를 사용해 객체를 새로 생성할 수 없도록 생성자의 접근 제한자를 private으로 지정
	private ReviewDAO() {
	}
	
	/** 싱글톤 패턴으로 ReviewDAO 객체를 생성하여 리턴하는 메서드 */
	public static ReviewDAO getInstance() {
		if(reviewDAO == null) {
			reviewDAO = new ReviewDAO();
		}
		return reviewDAO;
	}
	
	/** ReviewDAO 객체에 Connection 객체를 주입하는 메서드 */
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	/** 리뷰 등록하는 메서드 */
	public int insertReview(Review review) {
		System.out.println(" R.DAO : insertBook() 호출");
		
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		System.out.println(" R.DAO : 새로 저정될 리뷰  - "+review.toString());
		
		try {
			String sql = "insert into jspbookshop.review values(NULL,?,?,?,?,?,now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review.getR_b_id());
			pstmt.setString(2, review.getR_m_id());
			pstmt.setString(3, review.getR_text());
			pstmt.setInt(4, review.getR_buy_opt());
			pstmt.setInt(5, review.getR_star());
			
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" R.DAO : insertReview() ERROR : "+e);
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	/** b_id에 해당하는 모든 리뷰 개수를 구하는 메서드 */
	public int selectReviewCount(int b_id) {
		System.out.println(" R.DAO : selectReviewCount() 호출");
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement("select count(*) from jspbookshop.review where r_b_id=?");
			pstmt.setInt(1, b_id);
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
		
		System.out.println(" R.DAO : selectReviewCount() 종료");
		return listCount;
	}

	/** b_id에 해당하는 모든 리뷰를 구하는 메서드 */
	public ArrayList<Review> selectReviewList(int b_id) {
		System.out.println(" R.DAO : selectReviewList() 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Review> reviewList = null;
		
		String review_sql = "select * from jspbookshop.review where r_b_id="+b_id+" order by r_regdate desc";
		
		try {
			pstmt = conn.prepareStatement(review_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reviewList = new ArrayList<>();
				
				do {
					
					Review review = new Review();
					review.setR_id(rs.getInt("r_id"));
					review.setR_b_id(b_id);
					review.setR_m_id(rs.getString("r_m_id"));
					review.setR_buy_opt(rs.getInt("r_buy_opt"));
					review.setR_star(rs.getInt("r_star"));
					review.setR_text(rs.getString("r_text"));
					review.setR_regdate(rs.getDate("r_regdate"));
					
					reviewList.add(review);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(" R.DAO : selectReviewList ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" R.DAO : selectReviewList() 종료");
		return reviewList;
	}
	
	
}
