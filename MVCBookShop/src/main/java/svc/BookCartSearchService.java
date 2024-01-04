package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

/** 장바구니 항목 검색 요청을 처리하는 비즈니스 로직을 구현한 Service 클래스*/
public class BookCartSearchService {

	/** 시작금액과 마지막 금액 사이에 존재하는 금액을 가지고 있는 상품의 장바구니 항목을 리턴하는 메서드 */
	public ArrayList<Cart> getCartSearchList(int startMoney, int endMoney, HttpServletRequest req) {
		System.out.println(" B.C.S.S : getCartSearchList() 호출");
		
		/* 현재 세션 영역에 저장되어 있는 장바구니 목록을 얻어온다 */
		HttpSession session = req.getSession();
		ArrayList<Cart> oldCartList = (ArrayList<Cart>) session.getAttribute("cartList");
		ArrayList<Cart> cartList = new ArrayList<>();
		
		/* 장바구니 목록 중 검색 가격에 해당하는 장바구니 항목을 찾아 새로 생성한 장바구니 목록 객체(cartList)에 추가 */
		for(int i=0; i<oldCartList.size(); i++) {
			if(oldCartList.get(i).getC_b_price() >= startMoney &&
					oldCartList.get(i).getC_b_price() <= endMoney) {
				cartList.add(oldCartList.get(i));
			}
		}
		
		System.out.println(" B.C.S.S : getCartSearchList() 종료");
		return cartList;
	}

}
