package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BookListService;
import vo.ActionForward;
import vo.Book;
import vo.PageInfo;

/** 책 상품 목록보기 요청을 처리하는 Action 클래스 */
public class ManageBookListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.BookList.A : execute() 호출");
		
//		int page = 1;		// 목록 페이지에서 출력될 페이지의 기본값을 1로 설정하는 부분
		// int limit = 10;		// 한 페이지당 출력될 책 개수를 10개로 설정
		
		/* URL로 건너온 페이지 변수 파라미터(http://localhost:8088/manager/bookManage/bookList.ma?page=n)를 현재
		 * page값으로 설정
		 */		
//		if(req.getParameter("page") != null) {
//			page = Integer.parseInt(req.getParameter("page"));
//		}
		
		/* DB에 접근해 책 상품 목록보기 비즈니스 로직 처리 
		 * 1. 페이지 리스트 처리
		 * 2. 지정한 페이지에 출력될 책 목록 저장 */
		BookListService service = new BookListService();
		// DB에 저장된 총 책 개수 가져오기
//		int listCount = service.getListCount(); 
		// 지정한 페이지에 출력될 책 목록을 저장
		ArrayList<Book> bookList = service.getBookList();
		// 총 페이지 수(0.95를 더해서 올림처리)
//		int maxPage = (int)((double)(listCount / limit + 0.95));	
		// 페이징 부분에 출력되는 페이지 번호 중 첫 번째 페이지 번호(1, 11, 21 등) 계산
//		int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		// 페이징 부분에 출력되는 페이지 번호 중 마지막 페이지 번호(10, 20, 30 등) 계산
//		int endPage = startPage + 10 - 1;
		
		// 계산된 endPage 값을 존재하는 페이지의 마지막 페이지 번호(maxPage)로 지정
//		if(endPage > maxPage) endPage = maxPage;
		
		/* 페이징에 관한 정보를 저장할 PageInfo 객체 생성 */		
//		PageInfo pageInfo = new PageInfo();
//		pageInfo.setEndPage(endPage);
//		pageInfo.setListCount(listCount);
//		pageInfo.setMaxPage(maxPage);
//		pageInfo.setPage(page);
//		pageInfo.setStartPage(startPage);
		
		/* 포워딩할 때 가져갈 정보 저장 
		 * 1. 전체 책 상품 목록 정보를 속성으로 공유 
		 * .ok -> .jsp : Forward
		 * */
		req.setAttribute("bookList", bookList);
//		req.setAttribute("bookListSize", bookList.size());
//		req.setAttribute("pageInfo", pageInfo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/bookManage/bookList.jsp");
		forward.setRedirect(false);
		
		System.out.println(" M.BookList.A : execute() 종료");
		return forward;
	}

}
