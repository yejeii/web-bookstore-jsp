package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BookViewService;
import vo.ActionForward;
import vo.Book;

/** 특정 책 상품의 상세 정보보기 요청을 처리하는 Action 클래스*/
public class BookViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.View.A : execute() 호출");

		/* URL로 건너온 파라미터 저장 */		
		int b_id = Integer.parseInt(req.getParameter("b_id"));		// 상품 아이디
		int nowPage = Integer.parseInt(req.getParameter("page"));	// 책 목록 페이지에서 상품이 출력되는 페이지
		int limit = Integer.parseInt(req.getParameter("limit"));	// bookList.ok 페이지에서 한 페이지에 보여질 도서 수

		/* DB 처리 
		 * 1. 해당 도서 정보
		 * 2. 해당 도서 서브 분류 데이터(NULL 가능) */
		BookViewService service = new BookViewService();
		Book book = service.getBookView(b_id);
		
		System.out.println("  도서 정보 : "+book.toString());
		
		/* 오늘 본 상품의 이미지를 쿠키로 저장하여 상품 리스트 페이지에 출력하기 위한 처리 
		 * 책 상품 이미지 이름 문자열을 today 문자열 뒤에 해당 책 상품의 id값과 연결하여 ("today"+id) 쿠키 이름을 지정
		 * 응답에 쿠키 객체를 추가 */
		Cookie todayImageCookie = new Cookie("today"+b_id, (String) book.getB_image());
		todayImageCookie.setMaxAge(60*60*24);	// 클라 시스템에 저장되는 기간 : 24시간
		resp.addCookie(todayImageCookie);
		
		/* 포워딩할 때 가져갈 정보 저장 
		 * 책 상품 아이디를 request 영역에 속성으로 공유 
		 * 책 상품이 출력되는 페이지를 request 영역에 속성으로 공유
		 * .ok -> .jsp : Forward
		 * */
		req.setAttribute("book", book);
		req.setAttribute("page", nowPage);
		req.setAttribute("limit", limit);
		ActionForward forward = new ActionForward();
		forward.setPath("/book/bookView.jsp");
		forward.setRedirect(false);
		
		System.out.println(" B.View.A : execute() 종료");
		return forward;
	}

}
