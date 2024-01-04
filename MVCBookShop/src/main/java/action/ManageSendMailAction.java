package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

/** 관리자가 회원에게 메일 전송하기 위한 페이지 요청을 처리하는 Action 클래스 */
public class ManageSendMailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.SendMail.A : execute() 호출");
		
		/* 수신자가 있는 경우와 없는 경우 구분 */
		if(req.getParameterValues("emails[]") != null) {
			//String m_id = req.getParameter("m_id");
			String[] emails = req.getParameterValues("emails[]");
			for(String email:emails) {
				System.out.print(email+",");
			}
			
			// req.setAttribute("m_id", m_id);
			req.setAttribute("emails", emails);
		}
		
		/* 포워딩 처리 */
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/emailManage/mailForm.jsp");
		forward.setRedirect(false);
		
		System.out.println(" M.SendMail.A : execute() 종료");
		return forward;
	}

}
