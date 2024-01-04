package action;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.MemberDeleteProService;
import svc.MemberModifyProService;
import svc.MemberViewService;
import vo.ActionForward;
import vo.Member;

/** 회원 정보를 수정하는 요청을 처리하는 Action 클래스 */
public class MemberModifyProAction implements AjaxAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.ModifyPro.A : execute() 호출");
		
		boolean s_isLogin = false;
		String s_userId = null;
		
		PrintWriter out = null;
		resp.setCharacterEncoding("utf-8");
		
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
					out = resp.getWriter();
					out.print("not-logined");
					out.close();
				} else {
					/* Form 정보 저장 */
					// String formData = req.getParameter("formData");
					Member member = new Member();
					Map<String, String[]> formData = req.getParameterMap();
					
					for(Map.Entry<String, String[]> entry : formData.entrySet()) {
					    String key = entry.getKey();
					    String[] values = entry.getValue();
					    System.out.println(key.toString());
					    if(key.toString().equals("m_id")) member.setM_id(values[0]);
					    else if(key.toString().equals("m_name")) member.setM_name(values[0]);
					    else if(key.toString().equals("m_pw")) member.setM_pw(values[0]);
					    else if(key.toString().equals("m_age")) member.setM_age(Integer.parseInt(values[0]));
					    else if(key.toString().equals("m_email")) member.setM_email(values[0]);
					    else if(key.toString().equals("m_phone1")) member.setM_phone1(values[0]);
					    else if(key.toString().equals("m_phone2")) member.setM_phone2(values[0]);
					    else if(key.toString().equals("m_gender")) member.setM_gender(values[0]);
					    else if(key.toString().equals("m_postcode")) member.setM_postcode(values[0]);
					    else if(key.toString().equals("m_addr1")) member.setM_addr1(values[0]);
					    else if(key.toString().equals("m_addr2")) member.setM_addr2(values[0]);
					    else if(key.toString().equals("m_addr3")) member.setM_addr3(values[0]);
					}
					
					System.out.println(" 수정될 회원 정보 : "+member.toString());
					
					/* DB에 접근해 회원가입 비즈니스 로직을 처리 */
					MemberModifyProService service = new MemberModifyProService();
					boolean updateResult = service.updateMember(member);
					
					if(updateResult) {
						// DB에서 수정 성공한 경우
						MemberViewService viewservice = new MemberViewService();
						Member modMember = viewservice.getMember(req.getParameter("m_id"));
						
						/* 파싱할 최종 데이터 보내기 
						 * 1. 단순 데이터(json 변수)를 response에 담아 보낼 것이므로 setCharacterEncoding("utf-8") 설정 */
						out = resp.getWriter();
						
						out.print("commit");
						out.close();
					} else {
						out = resp.getWriter();
						out.print("rollback");
						out.close();
					}
				}
			} else {
				// 비로그인 상태
				session.invalidate();	// 세션 삭제
				
				out = resp.getWriter();
				out.print("not-logined");
				out.close();
			}
		} else {
			// 세션이 생성되지 않은 경우
			out = resp.getWriter();
			out.print("not-logined");
			out.close();
		}
		
		System.out.println(" M.ModifyPro.A : execute() 종료");
	}

}
