package controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.BookCartAddAction;
import action.BookCartListAction;
import action.BookCartRemoveAction;
import action.BookCartSearchAction;
import action.BookCscenterFaqAction;
import action.BookListAction;
import action.BookRegistFormAction;
import action.BookShippingCheckOutAction;
import action.BookShippingConfirmAction;
import action.BookViewAction;
import action.MainAction;
import vo.ActionForward;

/** 모든 클라이언트의 요청을 받아서 제어하는 컨트롤러 클래스
 * extends HttpServlet : HttpServlet 클래스를 상속할 때 request, response를 가져온다.
 * Servlet은 http 처리를 담당하기 때문.
 *  */
@WebServlet("*.ok")
public class BookFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		
		System.out.println("BookFrontController_doProcess() 호출!");
		
		req.setCharacterEncoding("UTF-8");
		
		/* 1. 요청 주소 파악 
		 * request.getRequestURL() : http://localhost:8090/MVCBookShop/bookList.ok 
		 * request.getRequestURI() : /MVCBookShop/bookList.ok 
		 * request.getRequestURL()에서 프로젝트명까지 제외한 나머지 주소를 가져온다.
		 * 이때, 계산된 주소에는 반드시 '/'가 포함되어야 한다!
		 */		
		String requestURI = req.getRequestURI();
		System.out.println(" B.Front.C : requestURI - "+requestURI);
		
		String contextPath = req.getContextPath();
		System.out.println(" B.Front.C : contextPath - "+contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println(" B.Front.C : command - "+command);
		System.out.println(" B.Front.C : 1. 요청 주소 계산 완료");
		
		/* 2. 각 요청 주소의 매핑 처리 */
		ActionForward forward = null;
		Action action = null;
		
		// main 페이지 요청
		if(command.equals("/bookShopMain.ok")) {
			System.out.println(" B.Front.C : /bookShopMain.ok 주소 호출");
			action = new MainAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 책 목록 페이지 요청
		else if(command.equals("/bookList.ok")) {
			System.out.println(" B.Front.C : /bookList.ok 주소 호출");
			action = new BookListAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 책 상세 페이지 요청
		else if (command.equals("/bookView.ok")) {
			System.out.println(" B.Front.C : /bookView.ok 주소 호출");
			action = new BookViewAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 책 상품 장바구니 추가 요청
		else if (command.equals("/bookCartAdd.ok")) {
			System.out.println(" B.Front.C : /bookCartAdd.ok 주소 호출");
			action = new BookCartAddAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 장바구니 목록 페이지 요청
		else if (command.equals("/bookCartList.ok")) {
			System.out.println(" B.Front.C : /bookCartList.ok 주소 호출");
			action = new BookCartListAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 장바구니 검색 요청
		else if (command.equals("/bookCartSearch.ok")) {
			System.out.println(" B.Front.C : /bookCartSearch.ok 주소 호출");
			action = new BookCartSearchAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 장바구니 삭제 처리 요청
		else if (command.equals("/bookCartRemove.ok")) {
			System.out.println(" B.Front.C : /bookCartRemove.ok 주소 호출");
			action = new BookCartRemoveAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 주문하기 페이지 요청
		else if (command.equals("/bookShippingCheckOut.ok")) {
			System.out.println(" B.Front.C : /bookShippingCheckOut.ok 주소 호출");
			action = new BookShippingCheckOutAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 주문 완료 페이지
		else if(command.equals("/bookShippingConfirm.ok")) {
			System.out.println(" B.Front.C : /bookShippingConfirm.ok 주소 호출");
			action = new BookShippingConfirmAction();
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 고객센터 페이지 요청
		else if (command.equals("/cscenter.ok")) {
			System.out.println(" B.Front.C : /cscenter.ok 주소 호출");
			try {
				forward = new ActionForward();
				forward.setPath("/cscenter/index.jsp");
				forward.setRedirect(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 고객센터 faq 페이지 요청
		else if (command.equals("/cscenter/faq.ok")) {
			System.out.println(" B.Front.C : /cscenter/faq.ok 주소 호출");
			try {
				action = new BookCscenterFaqAction();
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 도서 등록 페이지 요청
		else if (command.equals("/regist.ok")) {
			System.out.println(" B.Front.C : /regist.ok 주소 호출");
			try {
				action = new BookRegistFormAction();
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println(" B.Front.C : 2. 요청 주소 매칭 끝");
		
		/* 3. 포워딩 처리 */
		
		if(forward != null) {
			if(forward.isRedirect()) {
				// redirect 방식
				resp.sendRedirect(forward.getPath());
				System.out.println(" B.Front.C : 페이지 이동 (sendRedirect)");
				System.out.println(" B.Front.C : 페이지 주소 - "+forward.getPath());
			} else {
				// forward 방식
				RequestDispatcher dispatcher = req.getRequestDispatcher(forward.getPath());
				dispatcher.forward(req, resp);
				System.out.println(" B.Front.C : 페이지 이동 (forward)");
				System.out.println(" B.Front.C : 페이지 주소 - "+forward.getPath());
			}
		}
		
		System.out.println(" B.Front.C : 3. 페이지 이동 완료  \n\n\n");
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		System.out.println("BookFrontController_doGet() 호출!");
		doProcess(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		System.out.println("BookFrontController_doPost() 호출!");
		doProcess(req, resp);
	}
}
