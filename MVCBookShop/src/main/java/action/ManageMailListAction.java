package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ManageMailListService;
import vo.ActionForward;
import vo.Mail;

public class ManageMailListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.MailList.A : execute() 호출");
		
		/* 파라미터 저장 
		 * li값에 따라 다르게 DB 처리
		 * li == a : 전부
		 * li == g : 받은 메일함만
		 * li == s : 보낸 메일함만 
		 * li == i : 중요 메일함만 */
		
		String li = req.getParameter("li");
		
		ManageMailListService service = new ManageMailListService();
		int	listCount = service.getListCount(li); 
		ArrayList<Mail>	mailList = service.getMailList(li);
		
		/* applicationScope 영역에 저장해 공유할 데이터 가져오기
		 * 전체 메일함 URL로 요청될 때 최초로 설정, 그 이후엔 X
		 * 이메일 미열람 수, 중요 메일 수 */
		if(li.equals("a")) {
			int appStarListCount = 0;		// 중요 메일 변수
			
			int appNotReadListCount = service.getListCount(0);	// 미열람 메일 개수를 가져온다
			if(li=="i") appStarListCount = listCount;
			else appStarListCount = service.getListCount(1);	// 중요 메일 개수를 가져온다
			
			
			/* 포워딩 처리 및 기본 객체에 속성 저장 */
			req.getServletContext().setAttribute("appStarListCount", appStarListCount);
			req.getServletContext().setAttribute("appNotReadListCount", appNotReadListCount);
		}
		
		req.setAttribute("mailList", mailList);
		req.setAttribute("listCount", listCount);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/emailManage/list.jsp");
		forward.setRedirect(false);
		
		System.out.println(" M.MailList.A : execute() 종료");
		return forward;
	}

}
