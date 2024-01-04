package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberDeleteProService;
import vo.ActionForward;

/** 회원 탈퇴 요청을 처리하는 Service 클래스 */
public class MemberDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.DeletePro.A : execute() 호출");
		
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
					// 비밀번호 확인
					String deleteId = req.getParameter("m_id");
					String checkPw = req.getParameter("checkPw");
					boolean deleteResult = false;
					
					MemberDeleteProService service = new MemberDeleteProService();
					boolean isWrightPw = service.selectPw(deleteId, checkPw);
					if(isWrightPw) {
						// 비번이 정확한 경우(삭제 처리)
						deleteResult = service.deleteMember(deleteId, checkPw);
					}
					
					if(deleteResult) {
						// DB에서 삭제 성공한 경우
						session.invalidate();
						
						resp.setContentType("text/html;charset=utf-8");
						PrintWriter out = resp.getWriter();
						out.println("<script>");
						out.println("alert('탈퇴되었습니다.');");
						out.println("location.href='/bookShopMain.ok';");
						out.println("</script>");
					} else {
						resp.setContentType("text/html;charset=utf-8");
						PrintWriter out = resp.getWriter();
						out.println("<script>");
						out.println("alert('회원정보 삭제에 실패했습니다.');");
						out.println("history.back(-1);");
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
		
		System.out.println(" M.DeletePro.A : execute() 종료");
		return forward;
	}

}
