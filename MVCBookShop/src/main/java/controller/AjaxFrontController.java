package controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.AjaxAction;
import action.BookCartFormQtyAction;
import action.BookReviewRegistProAction;
import action.CsCenterFaqAction;
import action.CsCenterFkcodeFaqAction;
import action.ManageMailSearchProAction;
import action.ManageMailSendTempProAction;
import action.ManageMailUpdateStarProAction;
import action.FileDownloadAction;
import action.MemberModifyProAction;

/** AJAX로 데이터 통신
 * ActionForward 클래스 필요 X */
@WebServlet("*.ax")
public class AjaxFrontController extends HttpServlet {

	private static final long serialVersionUID = -3610741079136094028L;
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		
		System.out.println("AjaxFrontController() 호출!");
		
		req.setCharacterEncoding("UTF-8");
		
		/* 1. 요청 주소 파악 
		 * request.getRequestURL() : http://localhost:8090/MVCBookShop/bookReviewRegistPro.re
		 * request.getRequestURI() : /MVCBookShop/bookReviewRegistPro.ma 
		 * request.getRequestURL()에서 프로젝트명까지 제외한 나머지 주소를 가져온다.
		 * 이때, 계산된 주소에는 반드시 '/'가 포함되어야 한다!
		 */		
		String requestURI = req.getRequestURI();
		System.out.println(" Ax.Front.C : requestURI - "+requestURI);
		
		String contextPath = req.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println(" Ax.Front.C : command - "+command);
		System.out.println(" Ax.Front.C : 1. 요청 주소 계산 완료");

		/* 2. 각 요청 주소의 매핑 처리 */
		AjaxAction action = null;
		
		// 전체 Faq 처리 요청
		if (command.equals("/cscenter/faq.ax")) {
			System.out.println(" Ax.Front.C : /cscenter/faq.ax 주소 호출");
			action = new CsCenterFaqAction();
			try {
				action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// fk_code에 따른 Faq 처리 요청
		else if (command.equals("/cscenter/fkcode/faq.ax")) {
			System.out.println(" Ax.Front.C : /cscenter/fkcode/faq.ax 주소 호출");
			action = new CsCenterFkcodeFaqAction();
			try {
				action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 회원 정보 수정 처리 요청
		else if (command.equals("/memberModifyPro.ax")) {
			System.out.println(" Ax.Front.C : /memberModifyPro.ax 주소 호출");
			action = new MemberModifyProAction();
			try {
				action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 이메일 중요도를 0으로 처리 요청
		else if (command.equals("/manager/emailManage/updateStar.ax")) {
			System.out.println(" Ax.Front.C : /manager/emailManage/updateStar.ax 주소 호출");
			action = new ManageMailUpdateStarProAction();
			try {
				action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 이메일 임시저장 처리 요청
		else if (command.equals("/manager/emailManage/saveTempMailPro.ax")) {
			System.out.println(" Ax.Front.C : /manager/emailManage/saveTempMailPro.ax 주소 호출");
			action = new ManageMailSendTempProAction();
			try {
				action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 이메일 검색기능 처리 요청
		else if (command.equals("/manager/emailManage/search.ax")) {
			System.out.println(" Ax.Front.C : /manager/emailManage/search.ax 주소 호출");
			action = new ManageMailSearchProAction();
			try {
				action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 장바구니 수량 증가/감소 요청
		else if (command.equals("/bookCartFormQty.ax")) {
			System.out.println(" Ax.Front.C : /bookCartFormQty.ax 주소 호출");
			action = new BookCartFormQtyAction();
			try {
				action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(" Ax.Front.C : 2. 요청 주소 매칭 끝");
		
		System.out.println(" Ax.Front.C : 3. 페이지 이동  \n\n\n");
			
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		System.out.println("AjaxFrontController_doGet() 호출!");
		doProcess(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		System.out.println("AjaxFrontController_doPost() 호출!");
		doProcess(req, resp);
	}

}
