package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewDAO;
import vo.Review;

/** 리뷰 등록을 처리하는 Service 클래스 */
public class BookReviewRegistProService {

	/** 리뷰를 등록하는 메서드 */
	public boolean insertReview(Review review) {
		System.out.println(" B.ReviewRegistPro.S : insertReview() 호출");
		
		boolean isRegistSuccess = false;
		
		/* DB 작업 
		 * 1. 커넥션 풀에서 Connection 객체 얻어오기
		 * 2. 싱글톤 방식으로 ReviewDAO 클래스의 인스턴스를 get 
		 * 3. ReviewDAO 클래스에서 DB 작업을 수행할 때 사용할 Connection 객체를 주입 
		 * 4. DB에 새로운 리뷰 삽입(INSERT) 메서드 호출
		 * */
		Connection conn = getConnection();
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		reviewDAO.setConnection(conn);
		int insertCount = reviewDAO.insertReview(review);
		
		if(insertCount > 0) {
			// 성공적으로 삽입된 경우. 트랜잭션 영구반영
			commit(conn);
			isRegistSuccess = true;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		System.out.println(" B.ReviewRegistPro.S : isRegistSuccess - " + isRegistSuccess);
		System.out.println(" B.ReviewRegistPro.S : insertReview() 종료");
		return isRegistSuccess;
	}

	
}
