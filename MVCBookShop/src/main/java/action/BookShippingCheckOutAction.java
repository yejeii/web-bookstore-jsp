package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar;

import svc.BookCartListService;
import util.SessionUtil;
import vo.ActionForward;
import vo.Cart;

/** 장바구니 상품을 주문하는 요청을 처리하는 Action 클래스 */
public class BookShippingCheckOutAction implements Action {

	private String s_userId;	 // 로그인 아이디 
	
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.ShippingCheckOut.A : excute() 호출");
		
		req.setCharacterEncoding("UTF-8");
		
		/* URL로 넘어온 파라미터 저장 */
		//String c_m_id = req.getParameter("c_m_id");
		//String c_session = req.getParameter("c_session");
		//System.out.println(" B.ShippingCheckOut.A : 장바구니 사용자 - "+c_m_id);
		
		/* 요청 URL request Headers에서 쿠키 받아오기 */
		/*
		 * String JSESSIONID = null;
		 * 
		 * System.out.println(" B.ShippingCheckOut.A : 생성된 쿠키 --------"); for(Cookie
		 * cookie: req.getCookies()) { System.out.println(cookie.getName() + " - "+
		 * cookie.getValue()); if(cookie.getName().toString().equals("JSESSIONID")) {
		 * JSESSIONID = cookie.getValue(); } }
		 * System.out.println(" B.ShippingCheckOut.A : 세션Id(JSESSIONID) - "+JSESSIONID);
		 */
		ArrayList<Cart> cartList = null;
		
		/* 로그인 상태에 따른 장바구니 가져오기
		 * 1. 로그인 상태 - DB에서,
		 * 2. 비로그인 상태 - 세션에서 */
		checkSession(req, resp);
		System.out.println(" 장바구니 소유자 아이디 : "+s_userId);
		
		if(s_userId != null) {
			cartList = getCartList(s_userId);
		} else {
			cartList = (ArrayList<Cart>) req.getSession().getAttribute("cartList");
		}
		
		/** 장바구니 총 금액 계산 */
		int totalMoney = 0;		// 지불해야 하는 총금액
		int money = 0;			// 장바구니 항목 하나에 대한 지불 금액

		if(cartList != null) {
			System.out.println(" B.ShippingCheckOut.A : cartList - "+cartList.size());
			
			for(Cart cart :cartList) {
				money = cart.getC_b_price() * cart.getC_b_qty();
				totalMoney += money;
			}
		}
		
		/* 포워딩할 때 가져갈 정보 저장 
		 * 총금액을 request 영역에 속성으로 공유
		 * 전체 장바구니 목록을 request 영역에 속성으로 공유
		 * .ok -> .jsp : forward
		 * */
		req.setAttribute("totalMoney", totalMoney);
		req.setAttribute("cartList", cartList);
		//req.setAttribute("c_session", c_session);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/book/bookShippingCheckOut.jsp");
		forward.setRedirect(false);
		
		System.out.println(" B.ShippingCheckOut.A : excute() 종료");
		return forward;
	}

	/** 로그인 세션을 확인하는 메서드 
	 * @throws IOException */
	private void checkSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println(" B.ShippingCheckOut.A : checkSession() 호출");

		s_userId = null;
		int result = SessionUtil.isLogined(req, resp);
		
		switch (result) {
		case 1:
			// 로그인된 경우
			s_userId = (String)req.getSession().getAttribute("userId");
			break;
		case 0:
			// 로그인 X 경우
			break;
		default:
			// 세션 X의 경우
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("<script>");
			out.print("alert('잘못된 접근입니다.');");
			out.print("location.href='/bookShopMain.ok';");
			out.print("</script>");
			out.close();
		}
		
		System.out.println(" B.ShippingCheckOut.A : checkSession() 종료");
	}

	/** 로그인 상태일 때 DB에서 장바구니를 가져오는 메서드 */
	private ArrayList<Cart> getCartList(String s_userId) {
		System.out.println(" B.ShippingCheckOut.A : getCartList() 호출");
		
		BookCartListService service = new BookCartListService();
		ArrayList<Cart> carts = service.getCartList(s_userId);
		
		System.out.println(" B.ShippingCheckOut.A : getCartList() 종료");
		return carts;
	}
	
}
