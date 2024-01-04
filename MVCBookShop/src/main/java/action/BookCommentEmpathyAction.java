package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BookCommentEmpathyService;

/** 코멘트의 공감을 1 증가시키는 요청을 처리하는 Action 클래스 */
public class BookCommentEmpathyAction implements AjaxAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.CommentEmpathy.A : execute() 호출");
		
		/* ajax로 건너온 파라미터 저장 */
		int c_id = Integer.parseInt(req.getParameter("c_id"));
		
		/* DB 접근하여 공감 1 증가 처리 */
		BookCommentEmpathyService service = new BookCommentEmpathyService();
		boolean isUpdateSuccess = service.updateEmpathy(c_id);
		
		/* DB 처리결과에 따른 문자열 리턴 */
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		
		if(isUpdateSuccess) { 
			out.print("success");
		} else {
			out.print("fail");
		}
		out.close();
		
		System.out.println(" B.CommentEmpathy.A : execute() 종료");
	}

}
