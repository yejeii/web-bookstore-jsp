package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BookCommentRegistProService;
import util.Util;
import vo.Comment;

/** b_id에 따른 코멘트를 처리하는 Action 클래스 */
public class BookCommentRegistProAction implements AjaxAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.CommentRegistPro.A : execute() 호출");
		
		/* 세션 확인 */
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user");
		
		// 부모 댓글의 ref, lev, seq를 저장하는 변수
		int p_ref = -1;
		int p_lev = -1;
		int p_seq = -1;
		
		/* 댓글의 경우 부모의 c_ref, c_lev, c_seq를 가져온다. */
		if(req.getParameter("c_lev") != null) {
			p_lev = Integer.parseInt(req.getParameter("c_lev"));
			p_ref = Integer.parseInt(req.getParameter("c_ref"));
			p_seq = Integer.parseInt(req.getParameter("c_seq"));
		}
		
		System.out.println(" B.CommentRegistPro.A : "+p_ref+p_lev+p_seq);
		
		/* ajax로 넘어온 데이터를 Comment 객체에 저장*/
		// 크로스 사이트 스크립트 공격에 대응
		String c_text = Util.replaceParameter(req.getParameter("c_text"));
		
		Comment comment = new Comment();
		comment.setC_b_id(Integer.parseInt(req.getParameter("c_b_id")));
		comment.setC_m_id(req.getParameter("c_m_id"));
		comment.setC_title(req.getParameter("c_title"));
		comment.setC_text(c_text);
		
		System.out.println(" B.CommentRegistPro.A : 새로 등록되는 코멘트 - " + comment.toString());
		
		/* DB에 접근해 데이터 등록 
		 * 원글일 경우와 댓글일 경우 분리하여 메서드 호출 */
		BookCommentRegistProService service = new BookCommentRegistProService();
		boolean isRegistSuccess;
		if(p_lev == -1) {
			// 원글일 경우
			isRegistSuccess = service.insertComment(comment);
		} else {
			// 댓글일 경우
			isRegistSuccess = service.insertComment(comment, p_ref, p_lev, p_seq);
		}
		

		/* DB 처리에 따른 결과값 리턴 */
		PrintWriter out = resp.getWriter();
		if(isRegistSuccess) {
			out.print("success");
		} else {
			out.print("failed");
		}
		
		System.out.println(" B.CommentRegistPro.A : execute() 종료");
	}

}
