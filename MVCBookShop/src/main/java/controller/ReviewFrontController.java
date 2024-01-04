package controller;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.AjaxAction;
import action.BookCommentEmpathyAction;
import action.BookCommentListAction;
import action.BookCommentRegistProAction;
import action.BookReviewListAction;
import action.BookReviewRegistProAction;

/** AJAX로 데이터 통신
 * ActionForward 클래스 필요 X */
@WebServlet("*.re")
public class ReviewFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		
		System.out.println("ReviewFrontController_doProcess() 호출!");
		
		req.setCharacterEncoding("UTF-8");
		
		/* 1. 요청 주소 파악 
		 * request.getRequestURL() : http://localhost:8090/MVCBookShop/bookReviewRegistPro.re
		 * request.getRequestURI() : /MVCBookShop/bookReviewRegistPro.ma 
		 * request.getRequestURL()에서 프로젝트명까지 제외한 나머지 주소를 가져온다.
		 * 이때, 계산된 주소에는 반드시 '/'가 포함되어야 한다!
		 */		
		String requestURI = req.getRequestURI();
		System.out.println(" R.Front.C : requestURI - "+requestURI);
		
		String contextPath = req.getContextPath();
		System.out.println(" R.Front.C : contextPath - "+contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println(" R.Front.C : command - "+command);
		System.out.println(" R.Front.C : 1. 요청 주소 계산 완료");

		/* 2. 각 요청 주소의 매핑 처리 */
		AjaxAction action = null;
		
		// 리뷰 등록 처리 요청
		if (command.equals("/bookReviewRegistPro.re")) {
			System.out.println(" R.Front.C : /bookReviewRegistPro.re 주소 호출");
			action = new BookReviewRegistProAction();
			try {
				action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// b_id에 해당하는 모든 리뷰 리스트 처리 요청
		else if (command.equals("/bookReviewList.re")) {
			System.out.println(" R.Front.C : /bookReviewList.re 주소 호출");
			action = new BookReviewListAction();
			try {
				action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 리뷰 삭제 처리 요청
		
		// 코멘트 등록 처리 요청
		else if (command.equals("/bookCommentRegistPro.re")) {
			System.out.println(" R.Front.C : /bookCommentRegistPro.re 주소 호출");
			action = new BookCommentRegistProAction();
			try {
				action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// b_id에 해당하는 모든 코멘트 리스트 처리 요청
		else if (command.equals("/bookCommentList.re")) {
			System.out.println(" R.Front.C : /bookCommentList.re 주소 호출");
			action = new BookCommentListAction();
			try {
				action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// 코멘트의 공감을 1 증가하는 처리 요청
		else if (command.equals("/bookCommentEmpathy.re")) {
			System.out.println(" R.Front.C : /bookCommentEmpathy.re 주소 호출");
			action = new BookCommentEmpathyAction();
			try {
				action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(" R.Front.C : 2. 요청 주소 매칭 끝");
		
		/* 3. 포워딩 처리 */
		System.out.println(" R.Front.C : 3. 페이지 이동  \n\n\n");
			
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		System.out.println("ReviewFrontController_doGet() 호출!");
		doProcess(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServerException, IOException, ServletException {
		System.out.println("ReviewFrontController_doPost() 호출!");
		doProcess(req, resp);
	}
}
