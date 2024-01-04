package dao;

import static db.JdbcUtil.close;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JdbcUtil;
import vo.Cart;

public class CartDAO {

	Connection conn;
	private static CartDAO cartDAO;

	// alt shift s
	// 외부 클래스에서 생성자를 사용해 객체를 새로 생성할 수 없도록 생성자의 접근 제한자를 private으로 지정
	private CartDAO() {
	}
	
	/** 싱글톤 패턴으로 CartDAO 객체를 생성하여 리턴하는 메서드 */
	public static CartDAO getInstance() {
		if(cartDAO == null) {
			cartDAO = new CartDAO();
		}
		return cartDAO;
	}
	
	/** CartDAO 객체에 Connection 객체를 주입하는 메서드 */
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	/** 도서 한권의 장바구니 수량을 가져오는 메서드 */
	public int selectCartQty(int b_id, String m_id) {
		System.out.println(" Ca.DAO : selectCartQty() 호출");
		
		int cartQty = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT ca_b_qty FROM jspbookshop.cart "
					+ "WHERE ca_b_id = ? "
					+ "AND ca_m_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_id);
			pstmt.setString(2, m_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) cartQty = rs.getInt(1);
		} catch (Exception e) {
			System.out.println(" Ca.DAO : selectCartQty() ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" Ca.DAO : selectCartQty() 종료");
		return cartQty;
	}
	
	/** 도서 아이디와 로그인 아이디에 따른 장바구니 도서 정보를 가져오는 메서드 */
	public Cart selectCart(int b_id, String s_userId) {
		System.out.println(" Ca.DAO : selectCart() 호출");
		
		Cart cart = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT ca.ca_id, ca.ca_b_id, b.b_name, b.b_image, b.b_sub_catgy, b.b_price, ca.ca_b_qty, ca.ca_m_id "
					+ "FROM jspbookshop.cart ca JOIN jspbookshop.book b "
					+ "ON ca.ca_b_id = b.b_id "
					+ "WHERE ca.ca_m_id = ? "
					+ "AND ca.ca_b_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_userId);
			pstmt.setInt(2, b_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cart = new Cart();
				cart.setC_id(rs.getInt(1));
				cart.setC_b_id(b_id);
				cart.setC_b_name(rs.getString(3));
				cart.setC_b_image(rs.getString(4));
				cart.setC_b_sub_catgy(rs.getInt(5));
				cart.setC_b_price(rs.getInt(6));
				cart.setC_b_qty(rs.getInt(7));
				cart.setC_m_id(s_userId);
			}
		} catch (Exception e) {
			System.out.println(" Ca.DAO : selectCart() ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" Ca.DAO : selectCart() 종료");
		return cart;
	}
	
	/** 아이디에 따른 장바구니 도서아이디를 가져오는 메서드 */
	public int[] selectCartBookIds(String s_userId) {
		System.out.println(" Ca.DAO : selectCartBookIds() 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Integer> list = null;
		int[] cartBookIds = null;
		
		try {
			String sql = "SELECT ca_b_id FROM jspbookshop.cart "
					+ "WHERE ca_m_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_userId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				list = new ArrayList<Integer>();
				
				do {
					int data = rs.getInt(1); // 첫 번째 열의 데이터 가져오기
					list.add(data); // 데이터를 ArrayList에 추가
				} while (rs.next());
			}
			
		} catch (Exception e) {
			System.out.println(" Ca.DAO : selectCartBookIds() ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		// ArrayList를 int[] 배열로 변환
		if(list != null) {
			cartBookIds = new int[list.size()];

			for (int i = 0; i < list.size(); i++) {
				cartBookIds[i] = list.get(i);
			}
		}
		
		System.out.println(" Ca.DAO : selectCartBookIds() 종료");
		return cartBookIds;
	}
	
	/** 아이디에 따른 장바구니를 가져오는 메서드 */
	@SuppressWarnings("resource")
	public ArrayList<Cart> selectCartList(String s_userId) {
		System.out.println(" Ca.DAO : selectCartList() 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Cart> carts = null;
		int listCount = 0;
		
		try {
			String sql = "SELECT count(*) FROM jspbookshop.cart "
						+ "WHERE ca_m_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_userId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) listCount = rs.getInt(1);
			if(listCount > 0) {
				sql = "SELECT ca.ca_id, ca.ca_b_id, b.b_name, b.b_image, b.b_sub_catgy, b.b_price, ca.ca_b_qty, ca.ca_m_id "
					+ "FROM jspbookshop.cart ca JOIN jspbookshop.book b "
					+ "ON ca.ca_b_id = b.b_id "
					+ "WHERE ca.ca_m_id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, s_userId);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					carts = new ArrayList<>();
					
					do {
						carts.add(new Cart(rs.getInt(1), 
										rs.getInt(2), 
										rs.getString(3), 
										rs.getString(4), 
										rs.getInt(5), 
										rs.getInt(6), 
										rs.getInt(7), 
										s_userId));
					} while (rs.next());
				}
			}
			
		} catch (Exception e) {
			System.out.println(" Ca.DAO : selectCartList() ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" Ca.DAO : selectCartList() 종료");
		return carts;
	}
	
	/** 도서 아이디(배열)와 로그인 아이디를 DB에 추가하는 메서드 
	 * @throws SQLException */
	public int insertCart(ArrayList<Integer> idList, String s_userId) throws SQLException {
		System.out.println(" Ca.DAO : insertCart(idList) 호출");
		
		int insertCount = 0;
		String sql = "INSERT INTO jspbookshop.cart " 
			 	   + "VALUES(NULL,?,?,?)";
		
		for (int id : idList) {
		    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setString(1, s_userId);
		        pstmt.setInt(2, id);
		        pstmt.setInt(3, 1);

		        insertCount += pstmt.executeUpdate();
		    } catch (Exception e) {
		        System.out.println(" Ca.DAO: insertCart(idList) ERROR: " + e);
		    }
		}

		System.out.println(" Ca.DAO : insertCart(idList) 종료");
		return insertCount;
	}
	
	/** 도서 아이디와 로그인 아이디를 DB에 추가하는 메서드 
	 * @throws SQLException */
	public int insertCart(int b_id, String s_userId) {
		System.out.println(" Ca.DAO : insertCart(b_id) 호출");
		
		PreparedStatement pstmt = null;
		int insertCount = 0;
		
		try {
			String sql = "INSERT INTO jspbookshop.cart " 
					+ "VALUES(NULL,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, s_userId);
			pstmt.setInt(2, b_id);
			pstmt.setInt(3, 1);
			
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" Ca.DAO: insertCart(b_id) ERROR: " + e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" Ca.DAO : insertCart(b_id) 종료");
		return insertCount;
	}

	/** 도서 수량을 1 증가시키는 메서드 
	 * @param status */
	public int updateQty(int b_id, String m_id, String status) {
		System.out.println(" Ca.DAO : updateQty() 호출");
		
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "";
		if(status.equals("up")) {
			sql = "UPDATE jspbookshop.cart "
					+ "SET ca_b_qty = ca_b_qty + 1 "
					+ "WHERE ca_b_id = ? "
					+ "AND ca_m_id = ?";
		} else {
			sql = "UPDATE jspbookshop.cart "
					+ "SET ca_b_qty = ca_b_qty - 1 "
					+ "WHERE ca_b_id = ? "
					+ "AND ca_m_id = ?";
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_id);
			pstmt.setString(2, m_id);
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" Ca.DAO : updateQty() ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" Ca.DAO : updateQty() 종료");
		return updateCount;
	}









	
	
}
