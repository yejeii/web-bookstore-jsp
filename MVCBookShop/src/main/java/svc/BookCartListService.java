package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CartDAO;
import db.JdbcUtil;
import vo.Cart;

public class BookCartListService {

	/** 로그인 아이디에 따른 장바구니를 가져오는 메서드 */
	public ArrayList<Cart> getCartList(String s_userId) {
		System.out.println(" B.CartList.S : getCartList() 호출");
		
		Connection conn = getConnection();
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(conn);
		ArrayList<Cart> carts = cartDAO.selectCartList(s_userId);
		JdbcUtil.close(conn);
		
		System.out.println(" B.CartList.S : getCartList() 종료");
		return carts;
	}

	/** 로그인 아이디에 따른 도서 아이디를 가져오는 메서드 */
	public int[] getCartBookIds(String s_userId) {
		
		System.out.println(" B.CartList.S : getCartBookIds() 호출");
		
		Connection conn = getConnection();
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(conn);
		
		int[] cartBookIds = cartDAO.selectCartBookIds(s_userId);
		JdbcUtil.close(conn);
		
		System.out.println(" B.CartList.S : getCartBookIds() 종료");
		return cartBookIds;
	}

}
