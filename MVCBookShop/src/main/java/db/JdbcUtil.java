package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/** DB 관련 공통 기능 클래스*/
public class JdbcUtil {
	
	/** 커넥션 풀에서 Connection 객체를 얻어오는 메서드 */
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/MVCBookShopDB");
			conn = ds.getConnection();
			conn.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(Connection conn) {
		
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement statement) {
		
		try {
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		
		try {
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		
		try {
			conn.commit();
			System.out.println(" JdbcUtil : commit success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		
		try {
			conn.rollback();
			System.out.println(" JdbcUtil : rollback success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
