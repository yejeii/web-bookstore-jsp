package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BookCartListService;
import util.SessionUtil;
import vo.ActionForward;
import vo.Cart;

/** 주문 완료 후 페이지 요청을 처리하는 Action 클래스 */
public class BookShippingConfirmAction implements Action {

	private String s_userId;	 // 로그인 아이디 
	
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.ShippingConfirm.A : execute() 호출");
		
		req.setCharacterEncoding("UTF-8");
		
		/* 파라미터 가져오기 */
		/*
		 * String c_session = req.getParameter("c_session");
		 * System.out.println(" B.ShippingConfirm.A : 세션Id(c_session) - "+c_session);
		 */

		/* 요청 URL request Headers에서 쿠키 받아오기 */
//		String JSESSIONID = null;
//		
//		System.out.println(" B.ShippingCheckOut.A : 생성된 쿠키 --------");
//		for(Cookie cookie: req.getCookies()) {
//			System.out.println(cookie.getName() + " - "+ cookie.getValue());
//			if(cookie.getName().toString().equals("JSESSIONID")) {
//				JSESSIONID = cookie.getValue();
//			}
//		}
//		System.out.println(" B.ShippingCheckOut.A : 세션Id(JSESSIONID) - "+JSESSIONID);

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
			System.out.println(" B.ShippingConfirm.A : cartList - "+cartList.size());
			
			for(Cart cart :cartList) {
				money = cart.getC_b_price() * cart.getC_b_qty();
				totalMoney += money;
			}
		}
		
		/* 폼 페이지에서 전송된 파라미터를 쿠키로 생성 
		 * 생성한 쿠키의 유효 기간 : 1m(60*1)
		 * response 내장 객체의 addCookie()로 쿠키 등록(생성은 서버에서, 저장은 클라쪽에) */
		Cookie m_name = new Cookie("shipping_name", URLEncoder.encode(req.getParameter("m_name"), "UTF-8")); 
		Cookie m_phone1 =  new Cookie("shipping_phone1", URLEncoder.encode(req.getParameter("m_phone1"), "UTF-8")); 
		Cookie m_phone2 =  new Cookie("shipping_phone2", URLEncoder.encode(req.getParameter("m_phone2"), "UTF-8")); 
		Cookie m_email =  new Cookie("shipping_email", URLEncoder.encode(req.getParameter("m_email"), "UTF-8")); 
		Cookie m_country =  new Cookie("shipping_country", URLEncoder.encode(req.getParameter("m_country"), "UTF-8")); 
		Cookie m_postcode =  new Cookie("shipping_postcode", URLEncoder.encode(req.getParameter("m_postcode"), "UTF-8")); 
		Cookie m_addr1 =  new Cookie("shipping_addr1", URLEncoder.encode(req.getParameter("m_addr1"), "UTF-8")); 
		
		m_name.setMaxAge(60*1);
		m_phone1.setMaxAge(60*1);
		m_phone2.setMaxAge(60*1);
		m_email.setMaxAge(60*1);
		m_country.setMaxAge(60*1);
		m_postcode.setMaxAge(60*1);
		m_addr1.setMaxAge(60*1);

		resp.addCookie(m_name);
		resp.addCookie(m_phone1);
		resp.addCookie(m_phone2);
		resp.addCookie(m_email);
		resp.addCookie(m_country);
		resp.addCookie(m_postcode);
		resp.addCookie(m_addr1);
		
		
		/* 포워딩할 때 가져갈 정보 저장 
		 * 총금액을 request 영역에 속성으로 공유
		 * 전체 장바구니 목록을 request 영역에 속성으로 공유
		 * 결제한 시간을 request 영역에 속성으로 공유
		 * .ok -> .jsp : forward
		 * */
		req.setAttribute("totalMoney", totalMoney);
		req.setAttribute("cartList", cartList);
		req.setAttribute("shippingDate", DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.SHORT, req.getLocale()).format(new Date()));
		
		ActionForward forward = new ActionForward();
		forward.setPath("/book/bookShippingConfirm.jsp");
		forward.setRedirect(false);
		
		System.out.println(" B.ShippingConfirm.A : execute() 종료");
		return forward;
	}

	/** 로그인 세션을 확인하는 메서드 
	 * @throws IOException */
	private void checkSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println(" B.ShippingConfirm.A : checkSession() 호출");
		
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
		
		System.out.println(" B.ShippingConfirm.A : checkSession() 종료");
	}
	
	/** 로그인 상태일 때 DB에서 장바구니를 가져오는 메서드 */
	private ArrayList<Cart> getCartList(String s_userId2) {
		System.out.println(" B.ShippingConfirm.A : getCartList() 호출");
		
		BookCartListService service = new BookCartListService();
		ArrayList<Cart> carts = service.getCartList(s_userId);
		
		System.out.println(" B.ShippingConfirm.A : getCartList() 종료");
		return carts;
	}

}
