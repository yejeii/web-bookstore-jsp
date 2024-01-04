package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReviewDAO;
import vo.Review;

/** b_id에 해당하는 리뷰를 가져오는 요청을 처리하는 Service 클래스 */
public class BookReviewListService {

	/** b_id에 해당하는 모든 리뷰 수를 구하는 메서드 */
	public int getReviewCount(int b_id) {
		System.out.println(" B.ReviewList.S : getReviewCount() 호출");
		
		int reviewCount = 0;
		
		/* DB 처리 */
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		reviewCount = reviewDAO.selectReviewCount(b_id);
		close(conn);
		
		System.out.println(" B.ReviewList.S reviewCount : "+reviewCount);
		System.out.println(" B.ReviewList.S : getReviewCount() 종료");
		return reviewCount;
	}

	/** b_id에 해당하는 모든 리뷰를 ArrayList객체 타입으로 반환하는 메서드 */
	public ArrayList<Review> getReviewList(int b_id) {
		System.out.println(" B.ReviewList.S : getReviewList() 호출");
		
		/* DB 처리 */
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		ArrayList<Review> reviewList = reviewDAO.selectReviewList(b_id);
		close(conn);
		
		System.out.println(" B.ReviewList.S : getReviewList() 종료");
		return reviewList;
	}

	
}
