package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ManageMailViewService;
import vo.ActionForward;
import vo.Mail;

/** 관리자 메일 상세보기 페이지 요청을 처리하는 Action 클래스 */
public class ManageMailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.MailView.A : execute() 호출");
		
		/* 파라미터 저장 */
		int ml_index = Integer.parseInt(req.getParameter("ml"));
		
		/* DB 처리 */
		ManageMailViewService service = new ManageMailViewService();
		Mail mail = service.getMailView(ml_index);
		System.out.println(" 가져온 메일 : "+mail.toString());
		
		/* 포워딩 처리 */
		req.setAttribute("mail", mail);
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/emailManage/mailView.jsp");
		forward.setRedirect(false);
		
		System.out.println(" M.MailView.A : execute() 종료");
		return forward;
	}

}
