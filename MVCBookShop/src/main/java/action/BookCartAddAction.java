package action;

import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BookCartAddService;
import vo.ActionForward;
import vo.Book;
import vo.Cart;

/** 장바구니 담기 요청을 처리하는 Action 클래스*/
public class BookCartAddAction implements Action {

	private HttpSession session;	// 해당 클래스 전역에 session이 공유되도록 설정
	private String s_userId;		// 로그인 아이디
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.CartAdd.A : execute() 호출");

		req.setCharacterEncoding("UTF-8");
		
		/* 장바구니 항목으로 추가될 책 상품의 아이디와 마지막 페이지를 파라미터 값으로 받아온다 */
		int b_id = Integer.parseInt(req.getParameter("b_id"));
		int nowPage = Integer.parseInt(req.getParameter("page"));
		String sort = req.getParameter("sort");						// 전체/국내/외국 분류
		int limit = Integer.parseInt(req.getParameter("limit"));	// bookList.ok 페이지에서 한 페이지에 보여질 도서 수
		
		/* DB 접근이 필요한 비즈니스 로직 호출
		 * 1. 장바구니 항목으로 추가될 책 상품 정보를 얻어오는 메서드 호출
		 * */
		BookCartAddService service = new BookCartAddService();
		Book cartBook = service.getBookCart(b_id);
		
		/* 로그인 확인 */
		checkSession(req);
		
		/* 책 상품을 장바구니 항목으로 추가하는 메서드 호출 
		 * 세션 영역 객체에 장바구니 항목을 추가해야 하므로 파라미터 값으로 request 객체를 던진다. */
		if(s_userId != null) {
			addCart(b_id, s_userId);		
		} else {
			addCart(req, cartBook);
		}
		
		/* 세션ID 쿠키로 만들어 브라우저에 넘겨주기 
		 * 생성한 쿠키의 유효 기간 : 초 단위로 지정(이 값은 쿠키가 만료될 최대 나이를 의미합니다. 따라서 이 값은 쿠키가 생성된 후 경과한 시간이 아니라, 쿠키가 만료되는 시간을 나타냅니다.)
		 * 	음수 값 - 쿠키를 영구적으로 저장하지 않고, 웹 브라우저가 종료될 때 삭제된다는 것을 의미
		 * 	0 - 쿠키 삭제 */
//		Cookie c_session = new Cookie("c_session", URLEncoder.encode(session.getId(), "UTF-8")); 
//		c_session.setMaxAge(60*60*24);		// 24H(60*60*24)
//		resp.addCookie(c_session);
		
		/* 포워딩할 때 가져갈 정보 저장 
		 * .ok -> .ok : redirect
		 * */
		ActionForward forward = new ActionForward();
		forward.setPath("/bookView.ok?page="+nowPage+"&b_id="+b_id+"&sort="+sort+"&limit="+limit);
		forward.setRedirect(true);
		
		System.out.println(" B.CartAdd.A : execute() 종료");
		return forward;
	}

	/** 로그인 세션을 확인하는 메서드 */
	private void checkSession(HttpServletRequest req) {
		System.out.println(" B.CartAdd.A : checkSession() 호출");
		
		s_userId = null;
		
		/* req.getSession(true) : 
		 *  이 요청과 관련된 현재 HttpSession이 있는 경우 가장 최근의 세션을 반환한다. 
		 * 	만약 현재 세션이 없고 create가 true인 경우 새 세션을 반환한다. */
		session = req.getSession(true);
		if(session.getAttribute("isLogin") != null && (boolean)session.getAttribute("isLogin")) {
			// isLogin 속성값이 true(로그인 상태)
			s_userId = (String)session.getAttribute("userId");
		}
		
		System.out.println(" B.CartAdd.A : checkSession() 종료");
	}

	/** 로그인인 경우 DB에 장바구니를 저장하는 메서드 */
	private void addCart(int b_id, String s_userId) {
		System.out.println(" B.CartAdd.A : addCart(DB) 호출");
		
		BookCartAddService service = new BookCartAddService();
		
		/* 기존 DB에서 같은 b_id인 경우 수량만 1 증가 */
		Cart cart = service.getBookCart(b_id, s_userId);
		if(cart != null) {
			service.updateCartQty(b_id, s_userId);
		} else {
			service.addCart(b_id, s_userId);
		}
		
		System.out.println(" B.CartAdd.A : addCart(DB) 종료");
	}
	
	/** 비로그인인 경우 세션에 장바구니를 저장하는 메서드 */
	@SuppressWarnings("unchecked")
	public void addCart(HttpServletRequest req, Book book) {
		System.out.println(" B.CartAdd.A : addCart(세션) 호출");
		
		 /* 세션에 저장되어 있는 장바구니 목록 get 
		  * 세션에 장바구니 속성이 존재치 않으면(장바구니 요청을 처음 실행하는 경우) 
		  * 장바구니 항목을 요소로 추가할 ArrayList 객체를 생성해 해당 객체를 세션 영역의 속성으로 공유한다. */
		ArrayList<Cart> s_cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		
		if(s_cartList == null ) {
			s_cartList = new ArrayList<>();
			//session.setAttribute("cartList", s_cartList);
		}
		
		// 지금 장바구니에 담는 항목이 새로 추가되는 항목인지를 판단하는 변수
		// 기본값 true로 하여 요청에서 지정한 항목이 처음으로 추가되는 장바구니 항목으로 지정되게 한다.
		boolean isNewCart = true;	
		Cart cart = null;
		
		/* 새로 추가하는 장바구니 항목인지에 따른 처리
		 * 새로 추가할 장바구니 항목이 기존 장바구니 항목 목록에 존재하는 경우 isNewCart = false
		 * */
		for(Cart c : s_cartList) {
			if(book.getB_id() == c.getC_b_id()) {
				isNewCart = false;
				cart = c;
				break;
			}
		}
		
		/* 새로 추가하는 장바구니 항목인지에 따른 처리
		 * 새로운 장바구니인 경우 장바구니 항목을 저장하는 cartList에 도서 추가
		 * */
		if(isNewCart) {
			cart = new Cart();
			cart.setC_id(0);
			cart.setC_b_id(book.getB_id());
			cart.setC_b_name(book.getB_name());
			cart.setC_b_image(book.getB_image());
			cart.setC_b_sub_catgy(book.getB_bc_code());
			cart.setC_b_price(book.getB_price());
			cart.setC_b_qty(1);
			cart.setC_m_id(s_userId);	// 로그인되어 있으면 아이디가, 안되어 있으면 NULL이 저장
		} else {
			// 기존 장바구니에 존재하는 도서의 경우
			// 장바구니 세션에 수량 증가
			cart.setC_b_qty(cart.getC_b_qty()+1);
		}
		
		s_cartList.add(cart);
		session.setAttribute("cartList", s_cartList);
		
		System.out.println(" B.CartAdd.A : addCart(세션) 종료");
	}

}
