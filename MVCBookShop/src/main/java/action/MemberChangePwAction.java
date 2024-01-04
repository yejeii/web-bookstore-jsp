package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;

/** 임시코드를 발급받은 후, 새 비밀번호로 변경하는 페이지 요청을 처리하는 클래스 */
public class MemberChangePwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.ChangePw.A : execute() 호출");
		
		ActionForward forward = null;
		
		/* 브라우저에서 건너온 세션 확인 
		 * 로그인 상태에선 접근 못하게 설정 */
		HttpSession session = req.getSession(false);
		if(session != null) {
			// 세션이 생성되어 있는 경우
			if((String)session.getAttribute("isLogin") != null) {
				System.out.println(" isLogin : "+(String)session.getAttribute("isLogin") );
				// 로그인된 상태
				session.invalidate();
				
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>");
				out.println("alert('접근 권한이 없습니다.');");
				out.println("location.href='/bookShopMain.ok';");
				out.println("</script>");
			} else {
				// 비로그인 상태
				String authenticationCode = (String) session.getAttribute("authenticationCode");
				if(authenticationCode != null) {
					// 인증코드가 저장된 속성이 있는 경우
					forward = new ActionForward();
					forward.setPath("/member/changePwForm.jsp");
					forward.setRedirect(false);
				} else {
					resp.setContentType("text/html;charset=utf-8");
					PrintWriter out = resp.getWriter();
					out.println("<script>");
					out.println("alert('잘못된 접근 권한입니다.');");
					out.println("location.href='/bookShopMain.ok';");
					out.println("</script>");
				}
			}
		} else {
			// 세션이 생성되지 않은 경우
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("location.href='/bookShopMain.ok';");
			out.println("</script>");
		}
		
		System.out.println(" M.ChangePw.A : execute() 종료");
		return forward;
	}

}
