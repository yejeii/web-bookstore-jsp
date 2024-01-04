package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Server;
import org.apache.jasper.tagplugins.jstl.core.If;

import svc.BookCartAddService;
import svc.BookCartListService;
import util.SessionUtil;
import vo.ActionForward;
import vo.Cart;

/** 장바구니 목록보기 요청을 처리하는 Action 클래스 */
public class BookCartListAction implements Action {

	private String s_userId;	 // 로그인 아이디 
	ArrayList<Cart> cartList;	 // 장바구니를 저장할 객체

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.CartList.A : execute() 호출");
		
		req.setCharacterEncoding("UTF-8");
		
		/* 로그인 상태 확인 */
		cartList = checkSession(req, resp);
		
		System.out.println(" 장바구니 소유자 아이디 : "+s_userId);
		
		/* 로그인한 경우의 장바구니 처리
		 * 1. DB에서 기존 장바구니 가져오기 
		 * 2. 세션에 장바구니 속성이 저장된 경우, 장바구니와 비교해 DB 테이블 설정하기 */
		if(s_userId != null) { 
			
			int[] cartBookIds = getCartBookIds(s_userId);
			ArrayList<Integer> idList = new ArrayList<>();
			
			// 세션 장바구니를 DB에 저장(비로그인 상태에서 저장한 장바구니)
			// 기존 장바구니에 있는 도서 or 새 도서인지 확인
			// 기존 장바구니에 있는 도서인 경우 수량 1 증가
			if(cartBookIds != null && cartList != null) {
				for (Cart cart : cartList) {
					boolean isNewBook = true;
					int c_b_id = cart.getC_b_id();
					
				    for (int id : cartBookIds) {
				    	if (c_b_id == id) {
				    		System.out.println(c_b_id+" : 기존 장바구니에 존재하는 아이디. 수량 1 증가");
				            
				        	BookCartAddService service = new BookCartAddService();
				        	service.updateCartQty(c_b_id, s_userId);
				        	
				        	isNewBook = false;
				            break;
				        } 
				    }
				    
				    if(isNewBook) {
				    	System.out.println(c_b_id+" : 새로 추가될 도서 아이디 ");
				    	idList.add(c_b_id);
				    }
				}
			} else if(cartList != null) {
				for (Cart cart : cartList) {
					idList.add(cart.getC_b_id());
				}
			}
			
			// 새 도서 아이디 리스트를 DB에 추가
			System.out.println(" 새로 저장될 도서아이디 개수 - "+idList.size());
			addCart(idList, s_userId);
			
			// DB에 저장된 총 장바구니 가져오기
			cartList = getCartList(s_userId);
		} 
		
		/* 총금액 계산 */
		int totalMoney = 0; // 지불해야 하는 총금액
		int money = 0; // 장바구니 항목 하나에 대한 지불 금액

		if (cartList != null) {
			for (int i = 0; i < cartList.size(); i++) {
				money = cartList.get(i).getC_b_price() * cartList.get(i).getC_b_qty();
				totalMoney += money;
			}
		}

		/*
		 * 포워딩할 때 가져갈 정보 저장 
		 * 1. 총금액을 request 영역에 속성으로 공유
		 * 2. DB에서 가져온 장바구니를 request 영역에 속성으로 공유
		 * .ok -> .jsp : forward
		 */
		req.setAttribute("totalMoney", totalMoney);
		req.setAttribute("cartList", cartList);

		ActionForward forward = new ActionForward();
		forward.setPath("/book/bookCartList.jsp");
		forward.setRedirect(false);

		System.out.println(" B.CartList.A : execute() 종료");
		return forward;
	}

	/** 로그인 세션을 확인하는 메서드 
	 * @param resp 
	 * @throws IOException */
	@SuppressWarnings("unchecked")
	private ArrayList<Cart> checkSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println(" B.CartList.A : checkSession() 호출");
		
		/* 전역변수 초기화 */
		cartList = null;
		s_userId = null;
		
		int result = SessionUtil.isLogined(req, resp);
		result = SessionUtil.isCartExist(result);
		
		System.out.println(" B.CartList.A - result : "+result);
		
		switch (result) {
		case 1:
			// 세션에 로그인 속성과 장바구니 속성이 존재하는 경우. 
			// 사이트 첫 호출 이후 도서를 장바구니에 추가한 후 로그인한 경우
			s_userId = (String)req.getSession().getAttribute("userId");
			
			// 확인용
			if(req.getSession().getAttribute("cartList") != null) {
			  
				System.out.println(" 로그인된 상태에서의 장바구니 세션 --- "); 
				for(Cart cart:(ArrayList<Cart>)req.getSession().getAttribute("cartList")) {
					
					System.out.println(cart.toString()); 
				}
		  
			} 
			
			cartList = (ArrayList<Cart>)req.getSession().getAttribute("cartList");
			break;
		
		case 2:
			// 세션에 로그인 속성은 존재하지만 장바구니 속성이 존재하지 않은 경우. 
			// 사이트 첫 호출 이후 바로 로그인한 경우
			s_userId = (String)req.getSession().getAttribute("userId");
			break;
		
		case 3:
			// 세션에 로그인 속성은 존재하지 않지만 장바구니 속성이 존재하는 경우. 
			// 사이트 첫 호출 이후 로그인하지 않은 상태에서 장바구니 추가한 경우
			
			// 확인용
			if(req.getSession().getAttribute("cartList") != null) {
			  
				System.out.println(" 로그인되지 않은 상태에서의 장바구니 세션 --- "); 
				for(Cart cart:(ArrayList<Cart>)req.getSession().getAttribute("cartList")) {
					
					System.out.println(cart.toString()); 
				}
		  
			}
						
			cartList = (ArrayList<Cart>)req.getSession().getAttribute("cartList");
			break;
			
		case 4:
			// 세션은 존재하지만 아무런 속성이 저장되어 있지 않은 경우. 
			// 사이트 첫 호출 이후 로그인도, 장바구니 추가도 하지 않은 경우
			break;
			
		default:
			// 세션 X의 경우(로그아웃 이후)
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("<script>");
			out.print("alert('잘못된 접근입니다.');");
			out.print("location.href='/bookShopMain.ok';");
			out.print("</script>");
			out.close();
		}
		
		System.out.println(" B.CartList.A : checkSession() 종료");
		return cartList;
	}
	
	/** 로그인 아이디에 해당하는 도서 아이디를 가져오는 메서드 */
	private int[] getCartBookIds(String s_userId) {
		
		System.out.println(" B.CartList.A : getCartBookIds() 호출");
		
		BookCartListService service = new BookCartListService();
		int[] cartBookIds = service.getCartBookIds(s_userId);

		System.out.println(" B.CartList.A : getCartBookIds() 종료");
		return cartBookIds;
	}
	
	/** 로그인 상태에서 DB에 장바구니를 저장하는 메서드 
	 * @throws SQLException */
	private void addCart(ArrayList<Integer> idList, String s_userId2) throws SQLException {
		
		System.out.println(" B.CartList.A : addCart(DB) 호출");
		
		BookCartAddService service = new BookCartAddService();
		service.addCart(idList, s_userId);
		
		System.out.println(" B.CartAdd.A : addCart(DB) 종료");
	}

	/** 로그인 상태일 때 DB에서 장바구니를 가져오는 메서드 */
	private ArrayList<Cart> getCartList(String s_userId) {
		
		System.out.println(" B.CartList.A : getCartList() 호출");
		
		BookCartListService service = new BookCartListService();
		ArrayList<Cart> carts = service.getCartList(s_userId);
		
		System.out.println(" B.CartList.A : getCartList() 종료");
		return carts;
	}

	

}
