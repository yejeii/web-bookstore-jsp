package util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** 세션을 확인하는 메서드 */
public class SessionUtil {

	private static HttpSession session;
	
	/** 세션의 로그인 상태를 확인하는 메서드 */
	public static int isLogined(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println(" SessionUtil : isLogined() 호출");
		
		/** 구분 
		 * 로그인 : 1
		 * 비로그인 : 0
		 * 세션 x(로그아웃) : -1
		 */
		int result = -1;
		
		session = req.getSession(false);
		if(session != null) {
			if(session.getAttribute("isLogin") != null 
					&& (boolean)session.getAttribute("isLogin")
					&& session.getAttribute("userId") != null) {
				// isLogin 속성값이 true(로그인 상태)
				result = 1;
			} else {
				result = 0;
			}
		} 
		
		System.out.println(" isLogined - result "+result);
		System.out.println(" SessionUtil : isLogined() 종료");
		return result;
	}
	
	/** 세션 상태에 따라 장바구니 속성을 확인하는 메서드 */
	public static int isCartExist(int loginResult) {
		
		System.out.println(" SessionUtil : isCartExist() 호출");
		
		boolean isCartListExist = false;
		
		// 세션이 존재하는 경우 장바구니 속성 가져오기
		if(session != null) {
			Enumeration<String> AttributeNamesE = session.getAttributeNames();
			
			while (AttributeNamesE.hasMoreElements()) {
				String attributeName = (String) AttributeNamesE.nextElement();
				
				if(attributeName.equals("cartList")) {	
					// .equals() : 파라미터가 null이 아니고 같은 문자열을 지닐 때 true를 반환.
					// 장바구니 속성이 존재하는 경우
					isCartListExist = attributeName.equals("cartList");
				}
			}
		}
		
		/** 경우의 수 
		 * 로그인)  세션 O, isLogin, userId, cartList 존재 		: 1	(세션에 로그인 속성과 장바구니 속성이 존재하는 경우. 사이트 첫 호출 이후 도서를 장바구니에 추가한 후 로그인한 경우)
		 * 로그인)  세션 O, isLogin, userId 존재, cartList 존재 X	: 2	(세션에 로그인 속성은 존재하지만 장바구니 속성이 존재하지 않은 경우. 사이트 첫 호출 이후 바로 로그인한 경우)
		 * 비로그인) 세션 O, isLogin, userId 존재 X, cartList 존재: 3 (세션에 로그인 속성은 존재하지 않지만 장바구니 속성이 존재하는 경우. 사이트 첫 호출 이후 로그인하지 않은 상태에서 장바구니 추가한 경우)
		 * 비로그인) 세션 O, isLogin, userId, cartList 존재 X		: 4 (세션은 존재하지만 아무런 속성이 저장되어 있지 않은 경우. 사이트 첫 호출 이후 로그인도, 장바구니 추가도 하지 않은 경우)
		 * 로그아웃) 세션 X										: 5
		 * */
		switch (loginResult) {
			case 1:
				// 로그인 상태
				if (isCartListExist) {
					// 경우 1
					return 1;
				} else {
					return 2;
				}
			
			case 0:
				// 비로그인 상태
				if (isCartListExist) {
					// 경우 3
					return 3;
				} else {
					return 4;
				}
	
			default:
				// 세션이 존재하지 않는 상태(로그아웃)
				break;
		}
		
		System.out.println(" SessionUtil : isCartExist() 종료");
		
		return 5;
	}
}
