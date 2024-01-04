package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.EmbeddedServletOptions;

import svc.MemberForgotPwProService;
import util.NaverMailSend;
import vo.ActionForward;
import vo.Member;

/** 회원 비밀번호 찾기 요청을 처리하는 Action 클래스 */
public class MemberForgotPwProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.ForgotPwPro.A : execute() 호출");
		
		ActionForward forward = null;
		
		/* 브라우저에서 건너온 세션 확인 
		 * 로그인 상태에선 접근 못하게 설정 
		 * req.getSession(true) : 브라우저에서 보낸 request에 최근 만들어진 세션 객체가 있으면 리턴하고, 
		 * 						  없으면 새로운 세션객체를 만들어 리턴한다. */
		HttpSession session = req.getSession(true);
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
		} 
		
		/* DB 처리 */
		String m_email = req.getParameter("m_email");
		String m_id = req.getParameter("m_id");
			
		MemberForgotPwProService service = new MemberForgotPwProService();
		Member member = service.getMember(m_id, m_email);
		if(member == null || !member.getM_email().equals(m_email)) {
			// 회원 정보가 일치하지 않은 경우
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('회원 정보가 존재하지 않습니다.');");
			out.println("history.back(-1);");
			out.println("</script>");
		} else {	
			/* 메일 전송 */
			NaverMailSend mailSend = new NaverMailSend();
			String authenticationCode = mailSend.sendEmail(m_email);
			
			/* 포워딩 처리 
			 * 인증키를 세션값에 넣는 이유는 request로는 보관이 용이하지 않기 때문*/
			session.setAttribute("authenticationCode", authenticationCode);
			session.setAttribute("m_id", m_id);

			forward = new ActionForward();
			forward.setPath("/memberChangePw.me");
			forward.setRedirect(true);
			
		}
			
		System.out.println(" M.ForgotPwPro.A : execute() 종료");
		return forward;
	}

}
