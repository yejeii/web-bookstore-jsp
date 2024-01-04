package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberModifyPwProService;
import vo.ActionForward;

/** 회원 비밀번호 변경 처리 요청을 처리하는 Action 클래스 */
public class MemberModifyPwProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.ModifyPwPro.A : execute() 호출");
		
		boolean s_isLogin = false;
		String s_userId = null;
		
		ActionForward forward = null;
		
		/* 로그인 상태를 통한 DB 처리 및 포워딩 처리
		 * 1. 로그인 상태 확인을 위한 session 객체 얻어오기 */
		HttpSession session = req.getSession(false);
		if(session != null) {
			// 세션이 생성되어 있는 경우
			s_isLogin = (boolean)session.getAttribute("isLogin");
			if(s_isLogin) {
				// isLogin 속성값이 true(로그인 상태)
				s_userId = (String)session.getAttribute("userId");
				if(s_userId == null) {
					// userId 속성에 회원 아이디가 저장되지 않은 경우
					resp.setContentType("text/html;charset=utf-8");
					PrintWriter out = resp.getWriter();
					out.println("<script>");
					out.println("alert('접근 권한이 없습니다.');");
					out.println("location.href='/bookShopMain.ok';");
					out.println("</script>");
				} else {
					if(s_userId.equals(req.getParameter("m_id"))){
						// 세션에 저장된 아이디와 파라미터로 넘어온 아이디가 동일한 경우
						
						/* DB 처리 
						 * 1. 비밀번호 확인
						 * 2. 비밀번호 변경*/
						String m_id = req.getParameter("m_id");
						String oldPw = req.getParameter("oldPw");
						String newPw = req.getParameter("newPw");
						boolean updateResult = false;
						
						MemberModifyPwProService service = new MemberModifyPwProService();
						boolean isWrightPw = service.selectPw(m_id, oldPw);
						if(isWrightPw) {
							// 비번이 정확한 경우(수정 처리)
							updateResult = service.modifyPw(m_id, oldPw, newPw);
						}
						
						if(updateResult) {
							// DB에서 수정 성공한 경우
							resp.setContentType("text/html;charset=utf-8");
							PrintWriter out = resp.getWriter();
							out.println("<script>");
							out.println("alert('정상적으로 수정되었습니다.');");
							out.println("location.href='/memberModifyPw.me?isLogin=true&userId=" + m_id + "';");
							out.println("</script>");
						} else {
							resp.setContentType("text/html;charset=utf-8");
							PrintWriter out = resp.getWriter();
							out.println("<script>");
							out.println("alert('비밀번호 수정에 실패했습니다.');");
							out.println("history.back(-1);");
							out.println("</script>");
						}
					} else {
						session.invalidate();
						resp.setContentType("text/html;charset=utf-8");
						PrintWriter out = resp.getWriter();
						out.println("<script>");
						out.println("alert('접근 권한이 없습니다.');");
						out.println("location.href='/bookShopMain.ok';");
						out.println("</script>");
					}
				}
			} else {
				// 비로그인 상태
				session.invalidate();	// 세션 삭제
				
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>");
				out.println("alert('접근 권한이 없습니다.');");
				out.println("location.href='/bookShopMain.ok';");
				out.println("</script>");
			}
		} else {
			// 세션이 생성되지 않은 경우
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('접근 권한이 없습니다.');");
			out.println("location.href='/bookShopMain.ok';");
			out.println("</script>");
		}
		
		System.out.println(" M.ModifyPwPro.A : execute() 종료");
		return forward;
	}

}
