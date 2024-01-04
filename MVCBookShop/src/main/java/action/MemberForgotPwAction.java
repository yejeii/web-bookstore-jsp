package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;

/** 회원 비밀번호 찾기 페이지 요청을 처리하는 Action 클래스 */
public class MemberForgotPwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.ForgotPw.A : execute() 호출");
		
		ActionForward forward = null;
		
		/* 브라우저에서 건너온 세션 확인 
		 * 로그인 상태에선 접근 못하게 설정 */
		HttpSession session = req.getSession(false);
		if(session != null) {
			// 세션이 생성되어 있는 경우
			if((String)session.getAttribute("isLogin") != null || (String)session.getAttribute("isLogin") != "") {
				// 로그인된 상태
				session.invalidate();
				
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>");
				out.println("alert('접근 권한이 없습니다.');");
				out.println("location.href='/bookShopMain.ok';");
				out.println("</script>");
			}
		} 
		
		// 세션이 생성되어있든 없든
		forward = new ActionForward();
		forward.setPath("/member/forgotPwForm.jsp");
		forward.setRedirect(false);
		
		System.out.println(" M.ForgotPw.A : execute() 종료");
		return forward;
	}

}
