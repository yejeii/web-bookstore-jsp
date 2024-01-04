package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BookCartSearchService;
import vo.ActionForward;
import vo.Cart;

/** 가격으로 장바구니 항목을 검색하는 Action 클래스 */
public class BookCartSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.C.S.A : execute() 호출");
		
		/* 요청 URL에서 전달된 파라미터 값 저장 
		 * startMoney : 검색에 사용될 시작 금액 
		 * endMoney : 검색에 사용될 마지막 금액 */
		int startMoney = Integer.parseInt(req.getParameter("startMoney"));
		int endMoney = Integer.parseInt(req.getParameter("endMoney"));

		/* DB 접근이 필요한 비즈니스 로직 호출 
		 * 시작금액과 마지막 금액 사이에 존재하는 금액을 가지고 있는 상품의 장바구니 항목을 검색하는 메서드 호출 */ 
		BookCartSearchService service = new BookCartSearchService();
		ArrayList<Cart> cartList = service.getCartSearchList(startMoney, endMoney, req);
		
		/* 총금액 계산 */
		int totalMoney = 0;
		int money = 0;
		
		for(int i=0; i<cartList.size(); i++) {
			money = cartList.get(i).getC_b_price() * cartList.get(i).getC_b_qty();
			totalMoney += money;
		}
		
		/* 포워딩할 때 가져갈 정보 저장 
		 * 1. 검색한 장바구니 항목을 request 영역에 속성으로 공유
		 * 2. 검색에 사용된 시작 금액을 request 영역에 속성으로 공유
		 * 3. 검색에 사용된 마지막 금액을 request 영역에 속성으로 공유
		 * 4. 총금액을 request 영역에 속성으로 공유 
		 * .ok -> .jsp : forward
		 * */
		req.setAttribute("cartList", cartList);
		req.setAttribute("startMoney", startMoney);
		req.setAttribute("endMoney", endMoney);
		req.setAttribute("totalMoney", totalMoney);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/book/bookCartList.jsp");
		forward.setRedirect(false);
		
		System.out.println(" B.C.S.A : execute() 종료");
		return forward;
	}

}
