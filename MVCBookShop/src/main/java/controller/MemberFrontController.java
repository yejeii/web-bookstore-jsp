package controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.MemberChangePwAction;
import action.MemberChangePwProAction;
import action.MemberDeleteProAction;
import action.MemberForgotPwAction;
import action.MemberForgotPwProAction;
import action.MemberJoinProAction;
import action.MemberLoginProAction;
import action.MemberModifyPwAction;
import action.MemberModifyPwProAction;
import action.MemberViewAction;
import vo.ActionForward;

/** 모든 클라이언트의 요청을 받아서 제어하는 컨트롤러 클래스
 * extends HttpServlet : HttpServlet 클래스를 상속할 때 request, response를 가져온다.
 * Servlet은 http 처리를 담당하기 때문.
 *  */
@WebServlet("*.me")
public class MemberFrontController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		
		System.out.println("MemberFrontController_doProcess() 호출");
		
		req.setCharacterEncoding("UTF-8");
		
		/* 1. 요청 주소 파악 
		 * request.getRequestURL() : http://localhost:8090/MVCBookShop/bookList.ok 
		 * request.getRequestURI() : /MVCBookShop/bookList.ok 
		 * request.getRequestURL()에서 프로젝트명까지 제외한 나머지 주소를 가져온다.
		 * 이때, 계산된 주소에는 반드시 '/'가 포함되어야 한다!
		 */		
		String requestURI = req.getRequestURI();
		System.out.println(" M.Front.C : requestURI - "+requestURI);
		
		String contextPath = req.getContextPath();
		System.out.println(" M.Front.C : contextPath - "+contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println(" M.Front.C : command - "+command);
		System.out.println(" M.Front.C : 1. 요청 주소 계산 완료");
		
		/* 2. 각 요청 주소의 매핑 처리 */
		ActionForward forward = null;
		Action action = null;
		
		// 로그인 페이지 요청
		if(command.equals("/memberLogin.me")) {
			forward = new ActionForward();
			forward.setPath("/member/loginForm.jsp");
			forward.setRedirect(false);
		} 
		
		// 로그인 처리 요청
		else if(command.equals("/memberLoginPro.me")) {
			action = new MemberLoginProAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 로그아웃 처리 요청
		else if(command.equals("/memberLogOutPro.me")) {
			
			/* 로그아웃을 요청한 사용자의 session 객체 얻어와서 제거 */
			HttpSession session = req.getSession();
			session.invalidate();
			
			/* 포워딩 처리 */
			try {
				forward = new ActionForward();
				forward.setPath("/bookShopMain.ok");
				forward.setRedirect(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 회원가입 페이지 요청
		else if (command.equals("/memberJoin.me")) {
			try {
				forward = new ActionForward();
				forward.setPath("/member/joinForm.jsp");
				forward.setRedirect(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 회원가입 처리 요청
		else if (command.equals("/memberJoinPro.me")) {
			action = new MemberJoinProAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 회원 정보 페이지 요청
		else if (command.equals("/memberView.me")) {
			action = new MemberViewAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 회원 비밀번호 변경 페이지 요청
		else if (command.equals("/memberModifyPw.me")) {
			action = new MemberModifyPwAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 회원 비밀번호 변경 처리 요청
		else if (command.equals("/memberModifyPwPro.me")) {
			action = new MemberModifyPwProAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 회원 비밀번호 찾기 페이지 요청
		else if (command.equals("/memberForgotPw.me")) {
			action = new MemberForgotPwAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 회원 비밀번호 찾기 처리 요청
		else if (command.equals("/memberForgotPwPro.me")) {
			action = new MemberForgotPwProAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 인증코드 이용, 새 비밀번호 변경 페이지 요청
		else if (command.equals("/memberChangePw.me")) {
			action = new MemberChangePwAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 인증코드 이용, 새 비밀번호 변경 요청
		else if (command.equals("/memberChangePwPro.me")) {
			action = new MemberChangePwProAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 회원 탈퇴 처리 요청
		else if (command.equals("/memberDeletePro.me")) {
			action = new MemberDeleteProAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/* 3. 포워딩 처리 */
		if(forward != null) {
			if(forward.isRedirect()) {
				// redirect 방식
				resp.sendRedirect(forward.getPath());
				System.out.println(" M.Front.C : 페이지 이동 (sendRedirect)");
				System.out.println(" M.Front.C : 페이지 주소 - "+forward.getPath());
			} else {
				// forward 방식
				RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, resp);
				System.out.println(" M.Front.C : 페이지 이동 (forward)");
				System.out.println(" M.Front.C : 페이지 주소 - "+forward.getPath());
			}
		}
		
		System.out.println(" M.Front.C : 3. 페이지 이동 완료  \n\n\n");
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		System.out.println("MemberFrontController_doGet() 호출!");
		doProcess(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		System.out.println("MemberFrontController_doPost() 호출!");
		doProcess(req, resp);
	}

}
