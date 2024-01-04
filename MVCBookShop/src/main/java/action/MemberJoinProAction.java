package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.MemberJoinProServie;
import vo.ActionForward;
import vo.Member;

/** 회원 가입 요청을 처리하는 Action 클래스 */
public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.JoinPro.Action : execute() 호출");
		
		boolean joinResult = false;
		
		/* Form 정보 저장 */
		Member member = new Member();
		member.setM_id(req.getParameter("m_id"));
		member.setM_pw(req.getParameter("m_pw"));
		member.setM_name(req.getParameter("m_name"));
		member.setM_age(Integer.parseInt(req.getParameter("m_age")));
		member.setM_gender(req.getParameter("m_gender"));
		member.setM_email(req.getParameter("m_email"));
		member.setM_phone1(req.getParameter("m_phone1"));
		member.setM_phone2(req.getParameter("m_phone2"));
		
		/* DB에 접근해 회원가입 비즈니스 로직을 처리 */
		MemberJoinProServie service = new MemberJoinProServie();
		joinResult = service.joinMember(member);
		
		/* DB 처리 결과에 따른 페이지 이동 처리 
		 * 성공시 /bookShopMain.ok로 이동 
		 * .me -> .ok : redirect */
		ActionForward forward = null;
		if(joinResult == false) {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('회원가입에 실패했습니다.')");
			out.println("history.back(-1)");			
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("/bookShopMain.ok");
			forward.setRedirect(true);
		}
		
		System.out.println(" M.JoinPro.Action : execute() 종료");
		return forward;
	}

}
