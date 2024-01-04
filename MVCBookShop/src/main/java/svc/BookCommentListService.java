package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CommentDAO;
import vo.Comment;

/** b_id에 해당하는 코멘트를 가져오는 요청을 처리하는 Service 클래스 */
public class BookCommentListService {

	/** b_id에 해당하는 모든 코멘트 개수 구하는 메서드 */
	public int getCommentCount(int c_b_id) {
		System.out.println(" B.CommentList.S : getCommentCount() 호출");
		
		int commentCount = 0;
		
		/* DB 처리 */
		Connection conn = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(conn);
		commentCount = commentDAO.selectCommentCount(c_b_id);
		close(conn);
		
		System.out.println(" B.CommentList.S : getCommentCount() 종료");
		return commentCount;
	}

	/** b_id에 해당하는 모든 코멘트를 ArrayList객체 타입으로 반환하는 메서드 */
	public ArrayList<Comment> getCommentList(int c_b_id) {
		System.out.println(" B.CommentList.S : getCommentList() 호출");
		
		/* DB 처리 */
		Connection conn = getConnection();
		CommentDAO commentDAO = CommentDAO.getInstance();
		commentDAO.setConnection(conn);
		ArrayList<Comment> commentList = commentDAO.selectCommentList(c_b_id);
		close(conn);
		
		System.out.println(" B.CommentList.S : getCommentList() 종료");
		return commentList;
	}


	
}
