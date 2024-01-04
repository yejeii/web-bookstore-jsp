package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.FileDownloadAction;
import action.ManageBookRegistProAction;
import action.ManageBookDeleteProAction;
import action.ManageBookListAction;
import action.ManageBookModifyProAction;
import action.ManageBookRegistFormAction;
import action.ManageBookViewAction;
import action.ManageMailListAction;
import action.ManageMailTempListAction;
import action.ManageMailViewAction;
import action.ManageMemberListAction;
import action.ManageSendMailAction;
import action.ManageSendMailProAction;
import vo.ActionForward;

/** 모든 클라이언트의 요청을 받아서 제어하는 컨트롤러 클래스
 * extends HttpServlet : HttpServlet 클래스를 상속할 때 request, response를 가져온다.
 * Servlet은 http 처리를 담당하기 때문.
 *  */
@WebServlet("*.ma")
public class ManagerFrontController extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		
		System.out.println("ManagerFrontController_doProcess() 호출!");
		
		req.setCharacterEncoding("UTF-8");
		
		/* 0. 관리자가 아니면 접근 불가 */
		
		HttpSession session = req.getSession(false); 
		boolean s_isLogin = (boolean)session.getAttribute("isLogin"); 
		String s_userId = (String)session.getAttribute("userId"); 
		
		if(s_isLogin != true ||	!s_userId.equals("manager")) {
			resp.setContentType("text/html;charset=UTF-8"); 
			PrintWriter out = resp.getWriter(); 
			out.println("<script>");
			out.println("alert('접근 불가한 계정입니다.')");
			out.println("location.href='/bookShopMain.ok';"); 
			out.println("</script>"); 
		}
		 
		System.out.println(" s_userId :"+s_userId);
		 
		
		/* 1. 요청 주소 파악 
		 * request.getRequestURL() : http://localhost:8090/manager/bookManage/bookList.ma
		 * request.getRequestURI() : /manager/bookManage/bookList.ma
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
		
		// 관리자 dashboard 페이지 요청
		if(command.equals("/manager/dashboard.ma")) {
			System.out.println(" M.Front.C : /manager/dashboard.ma 주소 호출");
			try {
				forward = new ActionForward();
				forward.setPath("/manager/dashboard.jsp");
				forward.setRedirect(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 관리자 도서 목록 페이지 요청
		else if(command.equals("/manager/bookManage/bookList.ma")) {
			System.out.println(" M.Front.C : /manager/bookManage/bookList.ma 주소 호출");
			action = new ManageBookListAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 관리자 도서 상세 페이지 요청
		else if (command.equals("/manager/bookManage/bookView.ma")) {
			System.out.println(" M.Front.C : /manager/bookManage/bookView.ma 주소 호출");
			action = new ManageBookViewAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 관리자 도서 등록 페이지 요청
		else if (command.equals("/manager/bookManage/bookRegistForm.ma")) {
			System.out.println(" M.Front.C : /manager/bookManage/bookRegistForm.ma 주소 호출");
			action = new ManageBookRegistFormAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 관리자 도서 등록 처리 요청
		else if(command.equals("/manager/bookManage/bookRegistPro.ma")) {
			System.out.println(" M.Front.C : /manager/bookManage/bookRegistPro.ma 주소 호출");
			action = new ManageBookRegistProAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 관리자 도서 정보 수정 처리 요청
		else if (command.equals("/manager/bookManage/bookModifyPro.ma")) {
			System.out.println(" M.Front.C : /manager/bookManage/bookModifyPro.ma 주소 호출");
			action = new ManageBookModifyProAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 관리자 도서 정보 삭제 처리 요청
		else if (command.equals("/manager/bookManage/bookDeletePro.ma")) {
			System.out.println(" M.Front.C : /manager/bookManage/bookDeletePro.ma 주소 호출");
			action = new ManageBookDeleteProAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 관리자 회원 목록 페이지 요청
		else if (command.equals("/manager/memberManage/memberList.ma")) {
			System.out.println(" M.Front.C : /manager/memberManage/memberList.ma 주소 호출");
			action = new ManageMemberListAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 관리자 메일 전체 리스트 페이지 요청
		else if (command.equals("/manager/emailManage/list.ma")) {
			System.out.println(" M.Front.C : /manager/emailManage/list.ma 주소 호출");
			action = new ManageMailListAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 관리자 메일 작성 페이지 요청
		else if (command.equals("/manager/emailManage/sendMail.ma")) {
			System.out.println(" M.Front.C : /manager/emailManage/sendMail.ma 주소 호출");
			action = new ManageSendMailAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 관리자 메일 전송 처리 요청
		else if (command.equals("/manager/emailManage/sendMailPro.ma")) {
			System.out.println(" M.Front.C : /manager/emailManage/sendMailPro.ma 주소 호출");
			action = new ManageSendMailProAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 관리자 메일 상세보기 페이지 요청
		else if (command.equals("/manager/emailManage/mailView.ma")) {
			System.out.println(" M.Front.C : /manager/emailManage/mailView.ma 주소 호출");
			action = new ManageMailViewAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 다운로드 박스 출력 처리 요청
		else if (command.equals("/manager/fileDownload.ma")) {
			System.out.println(" M.Front.C : /manager/fileDownload.ma 주소 호출");
			action = new FileDownloadAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 관리자 임시 이메일 보관함 페이지 요청
		else if (command.equals("/manager/emailManage/templist.ma")) {
			System.out.println(" M.Front.C : /manager/emailManage/templist.ma 주소 호출");
			action = new ManageMailTempListAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(" M.Front.C : 2. 요청 주소 매칭 끝");
		
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
		System.out.println("ManagerFrontController_doGet() 호출!");
		doProcess(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		System.out.println("ManagerFrontController_doPost() 호출!");
		doProcess(req, resp);
	}

}
