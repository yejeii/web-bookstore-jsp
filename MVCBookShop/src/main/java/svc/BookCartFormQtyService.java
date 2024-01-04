package svc;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CartDAO;
import db.JdbcUtil;

/** 장바구니 항목 수량 증가 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스 */
public class BookCartFormQtyService {

	/** 장바구니 항목의 수량을 증가시키는 메서드 
	 * @throws SQLException */
	public int upCartQty(int b_id, String m_id) throws SQLException {
		System.out.println(" B.CartQtyUp.S : upCartQty() 호출");
		
		/* DB 작업 */
		Connection conn = JdbcUtil.getConnection();
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(conn);
		int updateCount = cartDAO.updateQty(b_id, m_id, "up");
		int isUpdateSuccess = -1;
		
		if((updateCount) > 0) {
			conn.commit();
			isUpdateSuccess = 1;
		}
		else conn.rollback();
		
		conn.close();

		System.out.println(" B.CartQtyUp.S isUpdateSuccess - "+isUpdateSuccess);
		System.out.println(" B.CartQtyUp.S : upCartQty() 종료");
		
		return isUpdateSuccess;
	}

	/** 장바구니 항목 수량을 -1하는 메서드 
	 * @throws SQLException */
	public int downCartQty(int b_id, String m_id) throws SQLException {
		System.out.println(" B.Cart.QtyUp.S : downCartQty() 호출");
		
				
		/* DB 작업 */
		Connection conn = JdbcUtil.getConnection();
		CartDAO cartDAO = CartDAO.getInstance();
		cartDAO.setConnection(conn);
		
		int isUpdateSuccess = -1;

		int updateCount = cartDAO.updateQty(b_id, m_id, "down");
		
		if((updateCount) > 0) {
			// DB 수정 성공
			conn.commit();
			isUpdateSuccess = 1;
		}
		else conn.rollback();
		
		conn.close();
		
		System.out.println(" B.Cart.QtyUp.S isUpdateSuccess - "+isUpdateSuccess);
		System.out.println(" B.Cart.QtyUp.S : downCartQty() 종료");
		return isUpdateSuccess;
	}

}
