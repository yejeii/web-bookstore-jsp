package svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import vo.Cart;

/** 장바구니 항목 삭제 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스*/
public class BookCartRemoveService {

	/** 장바구니 항목 삭제 요청을 처리하는 메서드 */
	public void cartRemove(HttpServletRequest req, int[] intIdArray) {
		System.out.println(" B.C.R.S : cartRemove() 호출");
		
		/* 현재 세션 영역에 저장되어 있는 장바구니 목록을 얻어온다 */
		HttpSession session = req.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		
		/* 웹 브라우저가 삭제할 대상으로 선택한 항목을 장바구니 목록에서 제거
		 * 삭제할 항목의 id 값과 동일한 id 값을 가진 장바구니 항목을 찾아 삭제 */
		for(int i=0; i<intIdArray.length; i++) {
			for(int j=0; j<cartList.size(); j++) {
				if(cartList.get(j).getC_b_id() == intIdArray[i]) {
					cartList.remove(cartList.get(j));
				}
			}
		}
		
		System.out.println(" B.C.R.S : cartRemove() 종료");
	}

}
