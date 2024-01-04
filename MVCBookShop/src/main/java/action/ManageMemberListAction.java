package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.ManageMemberListService;
import vo.ActionForward;
import vo.Member;

/** 회원 목록 페이지 요청을 처리하는 Action 클래스 */
public class ManageMemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.MemberList.A : execute() 호출");
		
		/* DB 작업 */
		ManageMemberListService service = new ManageMemberListService();
		ArrayList<Member> members = service.getMembers();
		
		/* 포워딩 처리 */
		req.setAttribute("members", members);
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/memberManage/memberList.jsp");
		forward.setRedirect(false);
		
		System.out.println(" M.MemberList.A : execute() 종료");
		return forward;
	}

}
