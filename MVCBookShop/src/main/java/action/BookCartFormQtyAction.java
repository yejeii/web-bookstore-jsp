package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BookCartFormQtyService;
import util.SessionUtil;
import vo.Cart;

/** 장바구니 항목 수량 증가/감소 요청을 처리하는 Action 클래스*/
public class BookCartFormQtyAction implements AjaxAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.CartFormQty.A : execute() 호출");
		
		/* 요청 URL에서 전달된 파라미터 값 저장 */
		int b_id = Integer.parseInt(req.getParameter("b_id"));
		String how = req.getParameter("how");
		
		/* 해당 도서의 장바구니 수량 증가 
		 * 1. 로그인의 경우 -> DB 장바구니 +1 
		 * 2. 비로그인의 경우 -> 세션 장바구니 +1 */
		int returnResult = -1;
		
		switch (SessionUtil.isLogined(req, resp)) {
			case 1:
				// 로그인
				BookCartFormQtyService service = new BookCartFormQtyService();
				int dbResult = 0;
				
				if(how.equals("up")) {
					// 장바구니 수량 +1
					dbResult = service.upCartQty(b_id, (String) req.getSession().getAttribute("userId"));
				} else {
					// 장바구니 수량 -1
					dbResult = service.downCartQty(b_id, (String) req.getSession().getAttribute("userId"));
				}
				
				returnResult = handleDBResult(dbResult);
				break;
				
			case 0:
				// 비로그인
				ArrayList<Cart> cartList = (ArrayList<Cart>) req.getSession().getAttribute("cartList");
				
				if (how.equals("up")) {
					cartList = updateCartQuantity(cartList, b_id, 1);
		        } else if (how.equals("down")) {
		        	cartList = updateCartQuantity(cartList, b_id, -1);
		        }
				
				// 확인용
				System.out.println("수정된 장바구니 ------");
				for(Cart cart:cartList) {
					System.out.println(cart.toString());
				}
				
				returnResult = 0;
				break;
	
			default:
				break;
		}
		
		/* 결과 응답 처리 
	     * 응답할 결과 형태 : int형 데이터  -> dataType: 'TEXT'
	     * resp.setContentType("text/plain"); 응답의 컨텐츠 유형을 설정
	     * */
		resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.print(returnResult);
		
		System.out.println(" B.CartFormQty.A : execute() 종료");
	}

	/** DB 결과 처리 */
	private int handleDBResult(int dbResult) {
		System.out.println(" B.CartFormQty.A : handleDBResult() 호출");
		
		if(dbResult == 1) {
			// DB에 성공적으로 저장된 경우
			return 1;
		} else {
			// DB에 저장 X (DB 에러)
			return -2;
		}
	}
	
	/** 세션 장바구니 속성 처리 
	 * @return */
	private ArrayList<Cart> updateCartQuantity(ArrayList<Cart> cartList, int b_id, int quantityChange) {
		System.out.println(" B.CartFormQty.A : updateCartQuantity() 호출");
		
		for (Cart cart : cartList) {
	        if (cart.getC_b_id() == b_id) {
	        	switch (quantityChange) {
				case 1:
					// 수량 증가
					cart.setC_b_qty(cart.getC_b_qty() + quantityChange);
					break;

				default:
					// 수량 감소
					// 수량이 1 보다 크고 감소하는 경우에만 처리되도록 설정
					if(cart.getC_b_qty() > 1) cart.setC_b_qty(cart.getC_b_qty() + quantityChange);
					break;
				}
	        }
	    }
		
		return cartList;
		
	}

}
