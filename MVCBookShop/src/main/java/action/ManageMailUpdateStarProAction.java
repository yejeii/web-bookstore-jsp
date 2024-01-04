package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.EmailUpdateStarProService;

/** 이메일 중요도를 재설정하는 요청을 처리하는 Action 클래스 */
public class ManageMailUpdateStarProAction implements AjaxAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" EmailUpdateStarPro.A : execute() 호출");
		
		PrintWriter out = null;
		int ml_index = Integer.parseInt(req.getParameter("ml_index"));
		int star = Integer.parseInt(req.getParameter("star"));
		
		EmailUpdateStarProService service = new EmailUpdateStarProService();
		boolean updateResult = service.updateStar(ml_index, star);
		
		resp.setCharacterEncoding("UTF-8");
		if(updateResult) {
			// DB에서 수정 성공한 경우
			
			/* 파싱할 최종 데이터 보내기 
			 * 1. 단순 text를 response에 담아 보낼 것이므로 setCharacterEncoding("utf-8") 설정 */
			out = resp.getWriter();
			
			out.print("commit");
			out.close();
		} else {
			out = resp.getWriter();
			out.print("rollback");
			out.close();
		}
		
		System.out.println(" EmailUpdateStarPro.A : execute() 종료");
	}

}
