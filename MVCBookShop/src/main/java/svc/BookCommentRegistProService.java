package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CommentDAO;
import vo.Comment;

/** 코멘트 등록을 처리하는 Service 클래스 */
public class BookCommentRegistProService {

	/** 코멘트(원글)를 등록하는 메서드 */
	public boolean insertComment(Comment comment) {
		System.out.println(" B.CommentRegistPro.S : insertComment() 호출");
		
		boolean isRegistSuccess = false;
		
		/* DB 작업 
		 * 1. 커넥션 풀에서 Connection 객체 얻어오기
		 * 2. 싱글톤 방식으로 CommentDAO 클래스의 인스턴스를 get 
		 * 3. CommentDAO 클래스에서 DB 작업을 수행할 때 사용할 Connection 객체를 주입 
		 * 4. DB에 새로운 리뷰 삽입(INSERT) 메서드 호출
		 * */
		Connection conn = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(conn);
		int insertCount = commentDAO.insertComment(comment);
		
		if(insertCount > 0) {
			// 성공적으로 삽입된 경우. 트랜잭션 영구반영
			commit(conn);
			isRegistSuccess = true;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		System.out.println(" B.CommentRegistPro.S : insertComment() 종료");
		return isRegistSuccess;
	}

	/** 대댓글 코멘트를 등록하는 메섣드 */
	public boolean insertComment(Comment comment, int p_ref, int p_lev, int p_seq) {
		System.out.println(" B.CommentRegistPro.S : insertComment(댓글) 호출");
		
		boolean isRegistSuccess = false;
		
		/* DB 작업 
		 * 1. 커넥션 풀에서 Connection 객체 얻어오기
		 * 2. 싱글톤 방식으로 CommentDAO 클래스의 인스턴스를 get 
		 * 3. CommentDAO 클래스에서 DB 작업을 수행할 때 사용할 Connection 객체를 주입 
		 * 4. DB에 새로운 리뷰 삽입(INSERT) 메서드 호출
		 * */
		Connection conn = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(conn);
		int insertCount = commentDAO.insertComment(comment, p_ref, p_lev, p_seq);
		
		if(insertCount > 0) {
			// 성공적으로 삽입된 경우. 트랜잭션 영구반영
			commit(conn);
			isRegistSuccess = true;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		System.out.println(" B.CommentRegistPro.S : insertComment(댓글) 종료");
		return isRegistSuccess;
	}

}
