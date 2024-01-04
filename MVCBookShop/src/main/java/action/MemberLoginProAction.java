package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberLoginService;
import vo.ActionForward;
import vo.Member;

/** 로그인 요청을 처리하는 Action 클래스 */
public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.Login.A : execute() 호출");
		
		req.setCharacterEncoding("UTF-8");
		
		Member member = new Member();
		
		member.setM_id(req.getParameter("m_id"));
		member.setM_pw(req.getParameter("m_pw"));
		
		MemberLoginService service = new MemberLoginService();
		boolean loginResult = service.login(member);
		
		ActionForward forward = null;
		if(loginResult) {
			// 데이터가 존재하는 경우
			
			/* 브라우저로부터 Session 객체 가져와서 필요한 정보 저장 */
			HttpSession session = req.getSession();	
			session.setAttribute("isLogin", true);
			session.setAttribute("userId", member.getM_id());
			// 세션 유지시간 설정
			session.setMaxInactiveInterval(60*60); // 3600초(60분)간 아이디 유지
			
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/bookShopMain.ok");
		} else {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('아이디 혹은 비밀번호가 틀립니다.')");
			out.println("history.back(-1);");
			out.println("</script>");
		}
		
		System.out.println(" M.Login.A : execute() 종료");
		return forward;
	}

}
