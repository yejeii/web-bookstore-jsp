package dao;

import static db.JdbcUtil.close;
import static db.JdbcUtil.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import vo.Book;
import vo.BookCatgyCode;

/** MySQL DB로 SQL구문을 전송하는 클래스*/
public class BookDAO {

	Connection conn;
	private static BookDAO bookDAO;
	
	// alt shift s
	// 외부 클래스에서 생성자를 사용해 객체를 새로 생성할 수 없도록 생성자의 접근 제한자를 private으로 지정
	private BookDAO() {
		// TODO Auto-generated constructor stub
	}
	
	/** 싱글톤 패턴으로 BookDAO 객체를 생성하여 리턴하는 메서드 */
	public static BookDAO getInstance() {
		if(bookDAO == null) {
			bookDAO = new BookDAO();
		}
		return bookDAO;
	}
	
	/** BookDAO 객체에 Connection 객체를 주입하는 메서드 
	 * @throws SQLException */
	public void setConnection(Connection conn) throws SQLException {
		this.conn = conn;
		conn.setAutoCommit(false);
	}
	
	/** 전체 책 상품 목록을 반환하는 메서드 */
	public ArrayList<Book> selectBookList() {
		System.out.println(" B.DAO : selectBookList() 호출");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Book> bookList = null;
		
		String book_sql = "select * from jspbookshop.book";

		try {
			pstmt = conn.prepareStatement(book_sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bookList = new ArrayList<>();
				
				do {
					bookList.add(new Book(
							rs.getInt("b_id"),
							rs.getString("b_name"),
							rs.getString("b_writer"),
							rs.getString("b_translator"),
							rs.getString("b_publisher"),
							rs.getInt("b_bc_code"),
							rs.getInt("b_price"),
							rs.getString("b_image"),
							rs.getInt("b_page"),
							rs.getString("b_publish_date"),
							rs.getString("b_content"),
							rs.getInt("b_readcount")));
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println(" B.DAO : selectBookList ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" B.DAO : selectBookList() 종료");
		return bookList;
	}

	public ArrayList<Book> selectBookList(int page, int limit) {
		System.out.println(" B.DAO : selectBookList(2) 호출");

		ArrayList<Book> bookList = null;

		// limit ?, 12 : 읽기 시작할 레코드 인덱스를 ? 부분에 매핑, 조회할 레코드 수를 12개로 설정해 데이터를 조회한다.
		// ORACLE 경우
		// order by BOARD_RE_SEQ offset ? rows
		// fetch first 12 rows only;
		String sql = "SELECT * "
				+ "FROM jspbookshop.book "
				+ "ORDER BY b_publish_date DESC "
				+ "LIMIT ?, ? ";
		int startrow = (page-1)*limit;	// 읽기 시작할 row 번호(해당 페이지에서 출력되어야 하는 시작 레코드의 인덱스 번호)
		System.out.println("sql: "+sql);

		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, limit);
			try(ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					bookList = new ArrayList<>();
					
					do {
						bookList.add(new Book(
								rs.getInt("b_id"),
								rs.getString("b_name"),
								rs.getString("b_writer"),
								rs.getString("b_translator"),
								rs.getString("b_publisher"),
								rs.getInt("b_bc_code"),
								rs.getInt("b_price"),
								rs.getString("b_image"),
								rs.getInt("b_page"),
								rs.getString("b_publish_date"),
								rs.getString("b_content"),
								rs.getInt("b_readcount")));
					} while (rs.next());
				}
			} catch (Exception e) {
				System.out.println(" B.DAO : selectBookList rs ERROR : "+e);
			}
		} catch (Exception e) {
			System.out.println(" B.DAO : selectBookList pstmt ERROR : "+e);
		} 
		
		System.out.println(" B.DAO : selectBookList(2) 종료");
		return bookList;
	}
	
	/** 해당 페이지에 출력될 책 상품 목록을 반환하는 메서드 */
	public ArrayList<Book> selectBookList(int page, String sort, String mnName, int mdCode, int limit) {
		System.out.println(" B.DAO : selectBookList() 호출");

		ArrayList<Book> bookList = null;
		String sql = "";
		int mnCode = 0;
		
		switch (mnName) {
		case "ENG":
			mnCode = 2;
			break;

		default:
			mnCode = 1;
			break;
		}
		
		if(mdCode == 0) {
			sql = "SELECT * "
				+ "FROM jspbookshop.book "
				+ "WHERE LEFT(b_bc_code,1) = ? "
				+ "LIMIT ?, ? ";
		} else {
			switch (sort) {
			case "sel":
				sql = "SELECT * "
						+ "FROM jspbookshop.book "
						+ "WHERE LEFT(b_bc_code,1) = ? "
						+ "AND LEFT(b_bc_code,4) = LEFT(?,4) "
						+ "LIMIT ?, ? ";
				break;
				
			default:
				sql = "SELECT * "
						+ "FROM jspbookshop.book "
						+ "WHERE LEFT(b_bc_code,1) = ? "
						+ "AND LEFT(b_bc_code,4) = LEFT(?,4) "
						+ "ORDER BY b_publish_date DESC "
						+ "LIMIT ?, ? ";
				break;
			}
		}

		System.out.println("sql: "+sql);
		int startrow = (page-1)*limit;	// 읽기 시작할 row 번호(해당 페이지에서 출력되어야 하는 시작 레코드의 인덱스 번호)

		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, mnCode);
			pstmt.setInt(2, mdCode);
			pstmt.setInt(3, startrow);
			pstmt.setInt(4, limit);
			try(ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					bookList = new ArrayList<>();
					
					do {
						bookList.add(new Book(
								rs.getInt("b_id"),
								rs.getString("b_name"),
								rs.getString("b_writer"),
								rs.getString("b_translator"),
								rs.getString("b_publisher"),
								rs.getInt("b_bc_code"),
								rs.getInt("b_price"),
								rs.getString("b_image"),
								rs.getInt("b_page"),
								rs.getString("b_publish_date"),
								rs.getString("b_content"),
								rs.getInt("b_readcount")));
					} while (rs.next());
				}
			} catch (Exception e) {
				System.out.println(" B.DAO : selectBookList  rs ERROR : "+e);
			}
		} catch (Exception e) {
			System.out.println(" B.DAO : selectBookList pstmt ERROR : "+e);
		} 
		
		System.out.println(" B.DAO : selectBookList() 종료");
		return bookList;
	}
	
	public ArrayList<Book> selectBookList(int page, LocalDate mondayOfWeek, LocalDate sundayOfWeek, String sort,
											String mnName, int mdCode, int limit) {
		System.out.println(" B.DAO : selectBookList(7) 호출");

		ArrayList<Book> bookList = null;
		String sql = "";
		int mnCode = 0;
		
		switch (mnName) {
		case "ENG":
			mnCode = 2;
			break;

		default:
			mnCode = 1;
			break;
		}
		
		if(mdCode == 0) {
			switch (sort) {
			case "sel":
				sql += "SELECT * "
						+ "FROM jspbookshop.book "
						+ "WHERE STR_TO_DATE(b_publish_date, '%Y-%m-%d') >= ? "
						+ "AND STR_TO_DATE(b_publish_date, '%Y-%m-%d') <= ? "
						+ "WHERE LEFT(b_bc_code,1) = ? "
						+ "LIMIT ?, ? ";
				break;
				
			default:
				sql = "SELECT * "
						+ "FROM jspbookshop.book "
						+ "WHERE STR_TO_DATE(b_publish_date, '%Y-%m-%d') >= ? "
						+ "AND STR_TO_DATE(b_publish_date, '%Y-%m-%d') <= ? "
						+ "AND LEFT(b_bc_code,1) = ? "
						+ "ORDER BY b_publish_date DESC "
						+ "LIMIT ?, ? ";
				break;
			}
		}
		
		else {
			switch (sort) {
			case "sel":
				sql = "SELECT * "
						+ "FROM jspbookshop.book "
						+ "WHERE STR_TO_DATE(b_publish_date, '%Y-%m-%d') >= ? "
						+ "AND STR_TO_DATE(b_publish_date, '%Y-%m-%d') <= ? "
						+ "WHERE LEFT(b_bc_code,1) = ? "
						+ "AND LEFT(b_bc_code,4) = LEFT(?,4) "
						+ "LIMIT ?, ? ";
				break;
				
			default:
				sql = "SELECT * "
						+ "FROM jspbookshop.book "
						+ "WHERE STR_TO_DATE(b_publish_date, '%Y-%m-%d') >= ? "
						+ "AND STR_TO_DATE(b_publish_date, '%Y-%m-%d') <= ? "
						+ "AND LEFT(b_bc_code,1) = ? "
						+ "AND LEFT(b_bc_code,4) = LEFT(?,4) "
						+ "ORDER BY b_publish_date DESC "
						+ "LIMIT ?, ? ";
				break;
			}
		}

		System.out.println("sql: "+sql);
		int startrow = (page-1)*limit;	// 읽기 시작할 row 번호(해당 페이지에서 출력되어야 하는 시작 레코드의 인덱스 번호)

		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, String.valueOf(mondayOfWeek));
			pstmt.setString(2, String.valueOf(sundayOfWeek));
			pstmt.setInt(3, mnCode);
			if(mdCode == 0) {
				pstmt.setInt(4, startrow);
				pstmt.setInt(5, limit);
			} else {
				pstmt.setInt(4, mdCode);
				pstmt.setInt(5, startrow);
				pstmt.setInt(6, limit);
			}
			try(ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					bookList = new ArrayList<>();
					
					do {
						bookList.add(new Book(
								rs.getInt("b_id"),
								rs.getString("b_name"),
								rs.getString("b_writer"),
								rs.getString("b_translator"),
								rs.getString("b_publisher"),
								rs.getInt("b_bc_code"),
								rs.getInt("b_price"),
								rs.getString("b_image"),
								rs.getInt("b_page"),
								rs.getString("b_publish_date"),
								rs.getString("b_content"),
								rs.getInt("b_readcount")));
					} while (rs.next());
				}
			} catch (Exception e) {
				System.out.println(" B.DAO : selectBookList  rs ERROR : "+e);
			}
		} catch (Exception e) {
			System.out.println(" B.DAO : selectBookList pstmt ERROR : "+e);
		} 
		
		System.out.println(" B.DAO : selectBookList(7) 종료");
		return bookList;
	}
	
	/** B_ID에 따른 도서 정보를 구하는 메서드 */
	public Book selectBook(int b_id) {
		System.out.println(" B.DAO : selectBook() 호출");
		
		Book book = null;
		BookCatgyCode bookCatgy = null;		// 서브코드 하나의 대/중/소분류 코드를 저장하는 VO
		List<Integer> bookSubCatgyList = null;
		List<BookCatgyCode> bookCatgyList = null;
		String sql = "SELECT bsc_bc_code FROM jspbookshop.booksubcatgy WHERE bsc_b_id = ?";
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, b_id);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				book = new Book();
				bookSubCatgyList = new ArrayList<>();
				
				while(rs.next()) {
					// 해당 도서의 서브분류 코드 List형으로 저장(서브분류 코드는 1개 이상이 될 수 있으므로)
					bookSubCatgyList.add(rs.getInt(1));
				}
			} catch (Exception e) {
				System.err.println(" B.DAO : selectBook() ERROR2 rs 처리 도중 에러 : "+e);
			}
			
			if(!bookSubCatgyList.isEmpty()) {
				// 해당 도서의 서브분류 코드가 있는 경우
				
				bookCatgyList = new ArrayList<>();
				sql = "SELECT "
						+ "b.b_id, b.b_name, b.b_writer, b.b_translator, b.b_publisher, b.b_bc_code, "
						+ "b.b_price, b.b_image, b.b_page, b.b_publish_date, b.b_content, b.b_readcount, "
						+ "s.bc_code, TRIM(s.bc_name), "
						+ "md.bc_code, TRIM(md.bc_name), "
						+ "mn.bc_code, TRIM(mn.bc_name) "
					+ "FROM jspbookshop.book b "
					+ "JOIN jspbookshop.booksubcatgy bsc ON b.b_id = bsc.bsc_b_id "
					+ "JOIN jspbookshop.bookcatgycode s ON bsc.bsc_bc_code = s.bc_code "
					+ "JOIN jspbookshop.bookcatgycode md ON s.bc_code_ref_md = md.bc_code "
					+ "JOIN jspbookshop.bookcatgycode mn ON s.bc_code_ref_mn = mn.bc_code "
					+ "WHERE b.b_id = ? "
					+ "AND bsc.bsc_bc_code = ?";
				
				try(PreparedStatement pstmt2 = conn.prepareStatement(sql)) {
					pstmt2.setInt(1, b_id);
					
					for(int sub_code:bookSubCatgyList) {
						pstmt2.setInt(2, sub_code);
						try(ResultSet rs2 = pstmt2.executeQuery()) {
							if(rs2.next()) {
								book.setB_id(rs2.getInt(1));
								book.setB_name(rs2.getString(2));
								book.setB_writer(rs2.getString(3));
								book.setB_translator(rs2.getString(4));
								book.setB_publisher(rs2.getString(5));
								book.setB_bc_code(rs2.getInt(6));
								book.setB_price(rs2.getInt(7));
								book.setB_image(rs2.getString(8));
								book.setB_page(rs2.getInt(9));
								book.setB_publish_date(rs2.getString(10));
								book.setB_content(rs2.getString(11));
								book.setB_readcount(rs2.getInt(12));
								bookCatgy = new BookCatgyCode(rs2.getInt(13), 
															  rs2.getString(14), 
															  rs2.getInt(15), 
															  rs2.getInt(17), 
															  rs2.getString(16),
															  rs2.getString(18));
							}
							bookCatgyList.add(bookCatgy);
						} 
					}
					book.setBookSubCatgyList(bookCatgyList);
				} catch (Exception e) {
					System.err.println(" B.DAO : selectBook() ERROR3 pstmt2 처리 도중 에러 : "+e);
				}
			} else {
				sql = "SELECT * FROM jspbookshop.book WHERE b_id = ? ";
				try(PreparedStatement pstmt2 = conn.prepareStatement(sql)) {
					pstmt2.setInt(1, b_id);
					try(ResultSet rs2 = pstmt2.executeQuery()) {
						if(rs2.next()) {
							book = new Book(rs2.getInt(1), 
									rs2.getString(2), 
									rs2.getString(3), 
									rs2.getString(4), 
									rs2.getString(5), 
									rs2.getInt(6), 
									rs2.getInt(7), 
									rs2.getString(8), 
									rs2.getInt(9), 
									rs2.getString(10), 
									rs2.getString(11), 
									rs2.getInt(12),
									null);
						}
					}
				} catch (Exception e) {
					System.err.println(" B.DAO : selectBook() ERROR3 pstmt2 처리 도중 에러 : "+e);
				}
			} 
		} catch (SQLException e) {
			System.err.println(" B.DAO : selectBook() ERROR1 pstmt 처리 중 에러 : "+e);
		}
		
		System.out.println(" B.DAO : selectBook() 종료");
		return book;

	}
	
	/** 책 한권의 상세정보를 구하는 메서드 */
	public List<Object> selectBookSubCatgy(int b_id) {
		System.out.println(" B.DAO : selectBookSubCatgy() 호출");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Object> bookSubCatgyList = null;
		String sql = "SELECT bsc_bc_code "
					+ "FROM jspbookshop.booksubcatgy "
					+ "WHERE bsc_b_id = " + b_id;	
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bookSubCatgyList = new ArrayList<>();
				bookSubCatgyList.add(rs.getInt(1));
			}
		} catch (Exception e) {
			System.err.println(" B.DAO : selectBookSubCatgy() ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		// 확인용
		if(bookSubCatgyList != null) {
			System.out.print(b_id + "의 서브 분류 : ");
			for(Object data:bookSubCatgyList) {
				System.out.print(data +"\t");
			}
		}
		
		System.out.println(" B.DAO : selectBookSubCatgy() 종료");
		return bookSubCatgyList;
	}
	
	/** 상세보기 요청 시 조회수 증가시키는 메서드 */
	public int updateReadCount(int b_id) {
		System.out.println(" B.DAO : updateReadCount() 호출");
		
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update jspbookshop.book "
				+ "set b_readcount = b_readcount+1 "
				+ "where b_id = "+b_id;
		
		try {
			pstmt = conn.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" B.DAO : updateReadCount() ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" B.DAO : updateReadCount() 종료");
		return updateCount;
	}

	/** 새로운 책 상품 정보를 DB에 추가하는 메서드
	 * try-with-resources 문을 사용하여 PreparedStatement 객체와 subCatgyStmt 객체를 자동으로 닫을 수 있도록 구현 
	 * @param b_sub_catgy */
	public int insertBook(Book book, int[] sub_catgy_arr) {
		System.out.println(" B.DAO : insertBook() 호출");
		
		int insertCount = 0;
		String sql = "INSERT INTO jspbookshop.book values(NULL,?,?,?,?,?,?,?,?,?,?,?)";
		
		System.out.println(" B.DAO : 새로 저정될 책 정보 - "+book.toString());
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)){
			// Book 테이블 INSERT
			pstmt.setString(1, book.getB_name());
			pstmt.setString(2, book.getB_writer());
			pstmt.setString(3, book.getB_translator());
			pstmt.setString(4, book.getB_publisher());
			pstmt.setInt(5, book.getB_bc_code());
			pstmt.setInt(6, book.getB_price());
			pstmt.setString(7, book.getB_image());
			pstmt.setInt(8, book.getB_page());
			pstmt.setString(9, book.getB_publish_date());
			pstmt.setString(10, book.getB_content());
			pstmt.setInt(11, 0);
			
			if(pstmt.executeUpdate() > 0) {
				if(sub_catgy_arr != null) {
					// BookSubCatgy 테이블 INSERT
					sql = "INSERT INTO jspbookshop.booksubcatgy(bsc_b_id, bsc_bc_code) VALUES(?, ?)";
					
					try (PreparedStatement subCatgyStmt = conn.prepareStatement(sql)) {
						int last_insert_id = selectLastInsertedBookId(conn); // 방금 INSERT한 book의 ID 가져오기
						
						// 카테고리 데이터 배열 순회 및 INSERT
						for (int i=0; i<sub_catgy_arr.length; i++) {
							subCatgyStmt.setInt(1, last_insert_id);
							subCatgyStmt.setInt(2, sub_catgy_arr[i]);
							insertCount += subCatgyStmt.executeUpdate();
						}
					} catch (Exception e) {
						System.out.println(" B.DAO : insertBook() ERROR2 : "+e);
					}
				}
				insertCount = 1;
			} 
		} catch (Exception e) {
			System.out.println(" B.DAO : insertBook() ERROR1 pstmt 처리 중 에러: "+e);
		} 
		
		System.out.println(" B.DAO : insertBook() 종료");
		return insertCount;
	}

	/** 가장 최근에 INSERT한 book의 ID를 구하는 메서드 
	 * @throws SQLException */
	private int selectLastInsertedBookId(Connection recentConn) {
		
		System.out.println(" B.DAO : selectLastInsertedBookId() 호출");
		
		int bookId = -1;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Query to retrieve the last inserted ID
            String sql = "SELECT last_insert_id() FROM jspbookshop.book";

            pstmt = recentConn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                bookId = rs.getInt(1);
            }
        } catch (Exception e) {
        	System.out.println(" selectLastInsertedBookId ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
        }

        System.out.println(" SELECT LAST_INSERT_ID() : "+bookId);
        System.out.println(" B.DAO : selectLastInsertedBookId() 종료");
        return bookId;
	}

	/** 파라미터에 따른 전체 책 개수를 구하는 메서드 */
	public int selectListCount() {
		System.out.println(" B.DAO : selectListCount(1) 호출");
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM jspbookshop.book";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(" selectListCount ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" B.DAO : selectListCount(1) 종료");
		return listCount;
	}
	
	public int selectListCount(String mnName, int mdCode) {
		System.out.println(" B.DAO : selectListCount(2) 호출");
		
		int listCount = 0;
		int mnCode = 0;
		switch (mnName) {
		case "ENG":
			mnCode = 2;
			break;

		default:
			mnCode = 1;
			break;
		}
		
		String sql = "SELECT COUNT(*) FROM jspbookshop.book "
					+ "WHERE LEFT(b_bc_code,1) = ? "
					+ "AND LEFT(b_bc_code,4) = LEFT(?,4)"; 
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, mnCode);
			pstmt.setInt(2, mdCode);
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println(" selectListCount rs ERROR : "+e);
			}
		} catch (Exception e) {
			System.out.println(" selectListCount pstmt ERROR : "+e);
		} 
		
		System.out.println(" B.DAO : selectListCount(2) 종료");
		return listCount;
	}
	
	public int selectListCount(LocalDate mondayOfWeek, LocalDate sundayOfWeek, String mnName, int mdCode) {
		System.out.println(" B.DAO : selectListCount(5) 호출");
		
		int listCount = 0;
		int mnCode = 0;
		String sql = "SELECT COUNT(*) FROM jspbookshop.book "
					+ "WHERE STR_TO_DATE(b_publish_date, '%Y-%m-%d') >= ? "
					+ "AND STR_TO_DATE(b_publish_date, '%Y-%m-%d') <= ? "
					+ "AND LEFT(b_bc_code,1) = ?";
		
		switch (mnName) {
		case "ENG":
			mnCode = 2;
			break;

		default:
			mnCode = 1;
			break;
		}
		
		if(mdCode != 0) {
			sql += " AND LEFT(b_bc_code,4) = LEFT(?,4)";
		}

		try(PreparedStatement pstmt = conn.prepareStatement(sql)) { 
			pstmt.setString(1, String.valueOf(mondayOfWeek));
			pstmt.setString(2, String.valueOf(sundayOfWeek));
			pstmt.setInt(3, mnCode);
			if(mdCode != 0) pstmt.setInt(4, mdCode);
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					listCount = rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println(" selectListCount rs ERROR : "+e);
			}
		} catch (Exception e) {
			System.out.println(" selectListCount pstmt ERROR : "+e);
		} 
		
		System.out.println(" B.DAO : selectListCount(5) 종료");
		return listCount;
	}

	/** 책 이미지 정보 가져오는 메서드 */
	public String getBookImage(int b_id) {
		System.out.println(" B.DAO : getBookImage() 호출");
		
		String imageFileName = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select b_image from jspbookshop.book where b_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				imageFileName = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println(" getBookImage ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" B.DAO : getBookImage() 종료");
		return imageFileName;
	}
	
	/** 책 정보 수정 메서드 */
	public int updateBook(Book book, int[] sub_catgy_arr) throws SQLException, Exception {
		System.out.println(" B.DAO : updateBook() 호출");
		
		int updateCount = 0;
		
		/* 1. 도서 정보 UPDATE */
		if(updateBookDetails(book) <= 0) return updateCount;
		
		/* 2. 해당 도서의 B_ID에 따른 서브 분류 데이터 SELECT */
		List<Integer> existingSubcategoriesList = getExistingSubcategories(book.getB_id());
		int existingSubcategoriesDB = existingSubcategoriesList.size();
        int updatedSubcategories = sub_catgy_arr.length;
        System.out.println("updatedSubcategories : "+updatedSubcategories);
        
        /* 위의 UPDATE가 잘 된 경우 실행 */
        if (existingSubcategoriesDB > 0 && updatedSubcategories > 0) {
			// 기존의 서브 분류(DB) 데이터 및 변경할 서브 분류 데이터가 있는 경우 
			System.out.println("기존의 서브 분류(DB) 데이터 및 변경할 서브 분류 데이터가 있습니다.");
			int result = updatedSubcategories-existingSubcategoriesDB;
			int DMLcase = 0;
			if(result > 0) {
				DMLcase = 1;
			} else if (result < 0) {
				DMLcase = -1;
			} 
			
			/* 기존의 서브 분류(DB) 기준으로 DML 처리 */
			switch (DMLcase) {
			case 1:
				/* 변경할 서브 분류 데이터가 더 많은 경우 
				 * 1. 기존의 서브 분류(DB) 개수만큼 수정 
				 * 2. (변경할 서브 분류 데이터 길이 - 기존의 서브 분류(DB) 개수)만큼 INSERT */ 
				System.out.println("변경할 서브 분류 데이터가 더 많은 경우");
				updateCount = updateSubCatgys(existingSubcategoriesList, existingSubcategoriesDB, sub_catgy_arr, book.getB_id());
				updateCount += insertSubCatgys(existingSubcategoriesDB, updatedSubcategories, sub_catgy_arr, book.getB_id());
				break;

			case -1:
				/* 기존의 서브 분류(DB) 데이터가 더 많은 경우
				 * 1. 변경할 서브 분류 데이터 길이만큼 수정
				 * 2. 남은 기존 서브 분류(DB) 데이터 DELETE */
				System.out.println("기존의 서브 분류(DB) 데이터가 더 많은 경우");
				updateCount = updateSubCatgys(existingSubcategoriesList, updatedSubcategories, sub_catgy_arr, book.getB_id());
				updateCount += deleteSubCatgys(existingSubcategoriesList, updatedSubcategories, existingSubcategoriesDB, book.getB_id());
				break;
				
			default:
				/* 데이터 개수가 동일한 경우 */
				System.out.println("데이터 개수가 동일한 경우");
				updateCount = updateSubCatgys(existingSubcategoriesList, updatedSubcategories, sub_catgy_arr, book.getB_id());
				break;
			}
        }
			
        else if(existingSubcategoriesDB > 0 && updatedSubcategories <= 0 ) {
			// 기존의 서브 분류(DB) 데이터가 있지만 변경할 서브 분류 데이터가 없는 경우
			System.out.println("기존의 서브 분류(DB) 데이터가 있지만 변경할 서브 분류 데이터가 없습니다.");
			updateCount = deleteSubCatgys(existingSubcategoriesList, 0, existingSubcategoriesDB, book.getB_id());
        }
		
        else if(existingSubcategoriesDB == 0 && updatedSubcategories > 0) {
			// 기존의 서브 분류(DB) 데이터는 없지만 변경할 서브 분류 데이터는 있는 경우
			System.out.println("기존의 서브 분류(DB) 데이터는 없지만 변경할 서브 분류 데이터가 있습니다.");
			updateCount = insertSubCatgys(0, updatedSubcategories, sub_catgy_arr, book.getB_id());
        }
		
        else {
			System.out.println("기존의 서브 분류(DB) 및 변경할 서브 분류 데이터가 없습니다.");
			updateCount = 1;
        }
		
		System.out.println(" B.DAO : updateBook() 종료");
		return updateCount;
	}

	/** 도서 정보 UPDATE하는 DML 트랜잭션 메서드 */
	public int updateBookDetails(Book book) throws SQLException {
		int updateCount = 0;
		String update_sql = "UPDATE jspbookshop.book "
						+ "SET b_name=?, b_writer=?, b_translator=?, b_publisher=?, b_bc_code=?, "
						+ "b_price=?, b_image=?, b_page=?, b_publish_date=?, b_content=? "
						+ "where b_id=?";
		
		try(PreparedStatement pstmt = conn.prepareStatement(update_sql)) {
			pstmt.setString(1, book.getB_name());
			pstmt.setString(2, book.getB_writer());
			pstmt.setString(3, book.getB_translator());
			pstmt.setString(4, book.getB_publisher());
			pstmt.setInt(5, book.getB_bc_code());
			pstmt.setInt(6, book.getB_price());
			pstmt.setString(7, book.getB_image());
			pstmt.setInt(8, book.getB_page());
			pstmt.setString(9, book.getB_publish_date());
			pstmt.setString(10, book.getB_content());
			pstmt.setInt(11, book.getB_id());
			
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(" updateBookDetails ERROR1 pstmt 처리 도중 에러 : "+e);
		} 
		
		return updateCount;
			
	}
	
	/** 도서 서브 분류 데이터를 SELECT하는 DQL 트랜잭션 메서드 */
	public List<Integer> getExistingSubcategories(int b_id) throws SQLException {
		List<Integer> existingSubcategoriesList = new ArrayList<>();
		String select_sql = "SELECT bsc_id FROM jspbookshop.booksubcatgy WHERE bsc_b_id = ?";
		
		try (PreparedStatement subSelectPstmt = conn.prepareStatement(select_sql)) {
			subSelectPstmt.setInt(1, b_id);
			
			try(ResultSet rs = subSelectPstmt.executeQuery()) {
				while (rs.next()) {
					existingSubcategoriesList.add(rs.getInt(1));
				} 
			} catch (SQLException e) {
				System.out.println(" getExistingSubcategories ERROR2 rs 처리 도중 에러 : "+e);
			}	
		} catch (SQLException e) {
			System.out.println(" getExistingSubcategories ERROR1 subSelectPstmt 처리 도중 에러 : "+e);
		}
		
        System.out.println("existingSubcategoriesDB : "+existingSubcategoriesList.size());
		return existingSubcategoriesList;
	}
	
	/** 도서 서브 분류 데이터를 INSERT하는 DML 트랜잭션 메서드 */
	public int insertSubCatgys(int startCount, int limitCount, int mod_arr[], int b_id) throws SQLException {
		int updateCount = 0;
		String insert_sql = "INSERT INTO jspbookshop.booksubcatgy(bsc_b_id, bsc_bc_code) VALUES(?, ?)";
		
		try(PreparedStatement subInsertPstmt = conn.prepareStatement(insert_sql)) {
			subInsertPstmt.setInt(1, b_id);
			for(int i=startCount; i<limitCount; i++) {
				subInsertPstmt.setInt(2, mod_arr[i]);
				updateCount += subInsertPstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(" insertSubCatgys ERROR1 subInsertPstmt 처리 도중 에러 : "+e);
		}
		
		return updateCount;
	}
	
	/** 도서 서브 분류 데이터를 UPDATE하는 DML 트랜잭션 메서드 */
	public int updateSubCatgys(List<Integer> catgyIdList, int limitCount, int mod_arr[], int b_id) throws SQLException {
		int updateCount = 0;
		String update_sql = "UPDATE jspbookshop.booksubcatgy "
				+ "SET bsc_bc_code = ? "
				+ "WHERE bsc_b_id = ? "
				+ "AND bsc_id = ?";
		
		try(PreparedStatement subUpdatePstmt = conn.prepareStatement(update_sql)) {
			for(int i=0; i<limitCount; i++) {
				subUpdatePstmt.setInt(1, mod_arr[i]);
				subUpdatePstmt.setInt(2, b_id);
				subUpdatePstmt.setInt(3, catgyIdList.get(i));
				updateCount += subUpdatePstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(" updateSubCatgys ERROR1 subUpdatePstmt 처리 도중 에러 : "+e);
		}
		
		return updateCount;
	}
	
	/** 도서 서브 분류 데이터를 DELETE하는 DML 트랜잭션 메서드 */
	public int deleteSubCatgys(List<Integer> catgyIdList, int startCount, int limitCount, int b_id) throws SQLException {
		int updateCount = 0;
		String delete_sql = "DELETE FROM jspbookshop.booksubcatgy "
						+ "WHERE bsc_id = ? "
						+ "AND bsc_b_id = ?";
		
		try(PreparedStatement subDeletePstmt = conn.prepareStatement(delete_sql)) {
			for(int i=startCount; i<limitCount; i++) {
				subDeletePstmt.setInt(1, catgyIdList.get(i));
				subDeletePstmt.setInt(2, b_id);
				updateCount += subDeletePstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(" deleteSubCatgys ERROR1 subDeletePstmt 처리 도중 에러 : "+e);
		}
		
		return updateCount;
	}
	
	/** 게시글 삭제 메서드 */
	public int deleteBook(int b_id) {
		System.out.println(" B.DAO : deleteBook() 호출");
		
		PreparedStatement pstmt = null;
		String board_delete_sql = "delete from jspbookshop.book where b_id=?";
		int deleteCount = 0;
		
		try {
			pstmt = conn.prepareStatement(board_delete_sql);
			pstmt.setInt(1, b_id);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" deleteBook ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" B.DAO : deleteBook() 종료");
		return deleteCount;
	}

}
