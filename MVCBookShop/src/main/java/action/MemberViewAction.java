package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberViewService;
import vo.ActionForward;
import vo.Member;

/** 회원 한 명의 상세 정보를 보여주는 요청을 처리하는 Action 클래스 */
public class MemberViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.View.A : execute() 호출");
		
		/* 로그인 상태 확인을 위한 session 객체 얻어오기 
		 * 1. req.getSession(false) : 이미 세션이 존재하면 세션을 반환하고, 없으면 null 반환 
		 * 2. 로그인 상태를 통한 포워딩 처리 */
		boolean s_isLogin = false;
		String s_userId = null;
		
		ActionForward forward = null;
		
		HttpSession session = req.getSession(false);
		if(session != null) {
			// 세션이 생성되어 있는 경우
			s_isLogin = (boolean)session.getAttribute("isLogin");
			if(s_isLogin) {
				// isLogin 속성값이 true(로그인 상태)
				s_userId = (String)session.getAttribute("userId");
				if(!s_userId.equals("manager")) {
					// 일반 회원인 경우
					forward = new ActionForward();
					String isLogin = req.getParameter("isLogin"); 
					String userId = req.getParameter("userId"); 
					MemberViewService service = new MemberViewService();
					Member member = service.getMember(userId);
					System.out.println(member.toString());
					req.setAttribute("member", member);
					forward.setPath("/member/memberInfo.jsp");
					forward.setRedirect(false);
				}
			} else {
				// 비로그인 상태
				session.invalidate();	// 세션 삭제
				
				forward = new ActionForward();
				forward.setPath("/bookShopMain.ok");
				forward.setRedirect(true);
			}
		} else {
			// 세션이 생성되지 않은 경우
			forward = new ActionForward();
			forward.setPath("/bookShopMain.ok");
			forward.setRedirect(true);
		}
		
		System.out.println(" M.View.A : execute() 종료");
		return forward;
	}

}
