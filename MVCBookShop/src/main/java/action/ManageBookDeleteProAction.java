package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ManageBookDeleteProService;
import vo.ActionForward;

/** 책 정보 삭제 요청을 처리하는 Action 클래스 */
public class ManageBookDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.BookDeletePro.A : execute() 호출");
		
		ActionForward forward = null;
		
		/* 파라미터 저장 */
		int b_id = Integer.parseInt(req.getParameter("b_id"));  
		
		/* 삭제 처리 */
		ManageBookDeleteProService service = new ManageBookDeleteProService();
		boolean isDeleteSuccess = service.deleteBook(b_id);
		
		if(!isDeleteSuccess) {
			// 삭제 실패
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('삭제 실패')");
			out.println("history.back(-1)");
			out.println("</script>");
		} else {
			/* 페이지 이동 처리
			 * .bo -> .bo : sendRedirect
			 * */
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/manager/bookManage/bookList.ma");
		}
		
		System.out.println(" M.BookDeletePro.A : execute() 종료");
		return forward;
	}

}
