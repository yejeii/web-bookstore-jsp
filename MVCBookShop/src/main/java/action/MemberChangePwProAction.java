package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberChangePwProService;
import vo.ActionForward;

/** 인증코드를 받아 새 비밀번호로 변경하는 요청을 처리하는 Action 클래스 */
public class MemberChangePwProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.ChangePwPro.A : execute() 호출");
		
		ActionForward forward = null;
		
		
		/* 브라우저에서 건너온 세션 확인 
		 * 로그인 상태에선 접근 못하게 설정 */
		HttpSession session = req.getSession(false);
		if(session != null) {
			// 세션이 생성되어 있는 경우
			if((String)session.getAttribute("isLogin") != null) {
				// 로그인된 상태
				System.out.println(" isLogin : "+(String)session.getAttribute("isLogin") );
				session.invalidate();
				
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>");
				out.println("alert('접근 권한이 없습니다.');");
				out.println("location.href='/bookShopMain.ok';");
				out.println("</script>");
			} else if((String) session.getAttribute("authenticationCode") != null){
				// 비로그인 상태 & 인증코드를 받은 경우
				
				String authenCode = req.getParameter("authenCode");
				if(((String) session.getAttribute("authenticationCode")).equals(authenCode)) {
					// 세션에 저장된 인증코드와 <input>값으로 넘어온 인증코드가 일치하는 경우
					
					String m_id = req.getParameter("m_id");
					String newPw = req.getParameter("newPw");
					
					System.out.println("m_id:"+m_id+", newPw:"+newPw);
					boolean updateResult = false;
					
					/* DB 작업 */
					MemberChangePwProService service = new MemberChangePwProService();
					updateResult = service.updatePw(m_id, newPw);
					if(updateResult) {
						// 수정 성공
						resp.setContentType("text/html;charset=utf-8");
						PrintWriter out = resp.getWriter();
						out.println("<script>");
						out.println("alert('정상적으로 수정되었습니다. 로그인 페이지로 돌아갑니다.');");
						out.println("location.href='/memberLogin.me';");
						out.println("</script>");
					} else {
						resp.setContentType("text/html;charset=utf-8");
						PrintWriter out = resp.getWriter();
						out.println("<script>");
						out.println("alert('비밀번호 수정에 실패했습니다.');");
						out.println("history.back(-1);");
						out.println("</script>");
					}
				} 
			} else {
				// 인증코드가 없는 경우
				resp.setContentType("text/html;charset=utf-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>");
				out.println("alert('접근 권한이 없습니다.');");
				out.println("location.href='/bookShopMain.ok';");
				out.println("</script>");
			}
		} else {
			// 세션 생성 X 
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('접근 권한이 없습니다.');");
			out.println("location.href='/bookShopMain.ok';");
			out.println("</script>");
		}
		
		System.out.println(" M.ChangePwPro.A : execute() 종료");
		return forward;
	}

}
