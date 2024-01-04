package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import vo.Member;
import static db.JdbcUtil.*;

public class MemberDAO {

	public static MemberDAO instance;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	private MemberDAO() {
		
	}
	
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	public String selectLoginId(Member member) {
		System.out.println(" M.DAO : selectLoginId() 호출");
		
		String loginId = null;
		String sql = "select m_id, m_pw from jspbookshop.member where m_id=? and m_pw = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getM_id());
			pstmt.setString(2, member.getM_pw());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				loginId = rs.getString(1);
			}
			
		} catch (Exception e) {
			System.out.println(" M.DAO : selectLoginId() ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" M.DAO : selectLoginId() 종료");
		return loginId;
	}

	/** 새로운 회원 정보 하나를 INSERT하는 메서드 */
	public int insertMember(Member member) {
		System.out.println(" M.DAO : insertMember() 호출");
		
		int insertCount = 0;
		String sql = "insert into jspbookshop.member values(?,?,?,?,?,?,?,?,NULL,NULL,NULL,NULL)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getM_id());
			pstmt.setString(2, member.getM_pw());
			pstmt.setString(3, member.getM_name());
			pstmt.setInt(4, member.getM_age());
			pstmt.setString(5, member.getM_gender());
			pstmt.setString(6, member.getM_email());
			pstmt.setString(7, member.getM_phone1());
			pstmt.setString(8, member.getM_phone2());
			
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" M.DAO : insertMember() ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" M.DAO : insertMember() 종료");
		return insertCount;
	}

	/** 모든 회원 정보를 가져오는 메서드 */
	public ArrayList<Member> selectMembers() {
		System.out.println(" M.DAO : selectMembers() 호출");
		
		ArrayList<Member> members = null;
		String sql = "select * from jspbookshop.member";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				members = new ArrayList<>();
				do {
					members.add(new Member(rs.getString("m_id"),
										rs.getString("m_pw"),
										rs.getString("m_name"),
										rs.getInt("m_age"),
										rs.getString("m_gender"),
										rs.getString("m_email"),
										rs.getString("m_phone1"),
										rs.getString("m_phone2"),
										rs.getString("m_postcode"),
										rs.getString("m_addr1"),
										rs.getString("m_addr2"),
										rs.getString("m_addr3")));
				} while (rs.next());
				
			}
		} catch (Exception e) {
			System.out.println(" M.DAO : selectMembers() ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" M.DAO : selectMembers() 종료");
		return members;
	}
	
	/** 아이디로 회원 정보를 가져오는 메서드 */
	public Member selectMember(String userId) {
		System.out.println(" M.DAO : selectMember() 호출");
		
		String sql = "select * from jspbookshop.member where m_id = '"+userId+"'";
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				member.setM_id(userId);
				member.setM_pw(rs.getString("m_pw"));
				member.setM_name(rs.getString("m_name"));
				member.setM_age(rs.getInt("m_age"));
				member.setM_gender(rs.getString("m_gender"));
				member.setM_email(rs.getString("m_email"));
				member.setM_phone1(rs.getString("m_phone1"));
				member.setM_phone2(rs.getString("m_phone2"));
				member.setM_postcode(rs.getString("m_postcode"));
				member.setM_addr1(rs.getNString("m_addr1"));
				member.setM_addr2(rs.getNString("m_addr2"));
				member.setM_addr3(rs.getNString("m_addr3"));
			}
		} catch (Exception e) {
			System.out.println(" M.DAO : selectMember() ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" M.DAO : selectMember() 종료");
		return member;
	}
	
	/** 아이디, 이메일로 회원을 가져오는 메서드 */
	public Member selectMember(String m_id, String m_email) {
		System.out.println(" M.DAO : selectMember(m_id, m_email) 호출");
		
		String sql = "select m_id, m_email from jspbookshop.member where m_id=? and m_email=?";
		Member member = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m_id);
			pstmt.setString(2, m_email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				member.setM_id(m_id);
				member.setM_email(m_email);
			}
		} catch (Exception e) {
			System.out.println(" M.DAO : selectMember(m_id, m_email) ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" M.DAO : selectMember(m_id, m_email) 종료");
		return member;
	}

	/** 비밀번호를 확인하는 메서드 */
	public int selectPw(String m_id, String pw) {
		System.out.println(" M.DAO : selectPw() 호출");
		
		String sql = "select m_id, m_pw from jspbookshop.member where m_id = '"+m_id+"' and m_pw = '"+pw+"'";
		int selectCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next() ) {
				selectCount = rs.getRow();
			}
		} catch (Exception e) {
			System.out.println(" M.DAO : selectPw() ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" M.DAO : selectPw() 종료");
		return selectCount;
	}
	
	/** 회원을 삭제하는 메서드 */
	public int deleteMember(String deleteId, String checkPw) {
		System.out.println(" M.DAO : deleteMember() 호출");
		
		String sql = "delete from jspbookshop.member where m_id=? and m_pw=?";
		int deleteCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deleteId);
			pstmt.setString(2, checkPw);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" M.DAO : deleteMember() ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" M.DAO : deleteMember() 종료");
		return deleteCount;
	}

	/** 회원정보 수정하는 메서드 */
	public int updateMember(Member member) {
		System.out.println(" M.DAO : updateMember() 호출");
		
		int updateCount = 0;
		String sql = "update jspbookshop.member "
				+ "set m_name=?, m_age=?, m_gender=?, m_email=?, m_phone1=?, m_phone2=?, m_postcode=?, m_addr1=?, m_addr2=?, m_addr3=? "
				+ "where m_id=? and m_pw=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getM_name());
			pstmt.setInt(2, member.getM_age());
			pstmt.setString(3, member.getM_gender());
			pstmt.setString(4, member.getM_email());
			pstmt.setString(5, member.getM_phone1());
			pstmt.setString(6, member.getM_phone2());
			pstmt.setString(7, member.getM_postcode());
			pstmt.setString(8, member.getM_addr1());
			pstmt.setString(9, member.getM_addr2());
			pstmt.setString(10, member.getM_addr3());
			pstmt.setString(11, member.getM_id());
			pstmt.setString(12, member.getM_pw());
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" updateMember ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" M.DAO : updateMember() 종료");
		return updateCount;
	}

	/** 회원 비밀번호를 수정하는 메서드 */
	public int updatePw(String m_id, String oldPw, String newPw) {
		System.out.println(" M.DAO : updatePw(m_id, oldPw, newPw) 호출");
		
		int updateCount = 0;
		String sql = "update jspbookshop.member "
				   + "set m_pw=? "
				   + "where m_id=? and m_pw=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setString(2, m_id);
			pstmt.setString(3, oldPw);
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" updatePw(m_id, oldPw, newPw) ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" M.DAO : updatePw(m_id, oldPw, newPw) 종료");
		return updateCount;
	}

	/** 임시코드를 받은 후 회원 비밀번호를 수정하는 메서드 */
	public int updatePw(String m_id, String newPw) {
		System.out.println(" M.DAO : updatePw(m_id, newPw) 호출");
		
		int updateCount = 0;
		String sql = "update jspbookshop.member "
				   + "set m_pw=? "
				   + "where m_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setString(2, m_id);
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(" updatePw(m_id, oldPw, newPw) ERROR : "+e);
		} finally {
			close(pstmt);
		}
		
		System.out.println(" M.DAO : updatePw(m_id, newPw) 호출");
		return updateCount;
	}

	/** 쿼리문을 포함하는 회원 이메일을 찾아 리스트로 반환하는 메서드 */
	public ArrayList<Map<String, String>> getEmailList(String query) {
		System.out.println(" M.DAO : getEmailList() 호출");
		System.out.println(" query : "+query);
		
		ArrayList<Map<String, String>> memberList = null;
		Map<String, String> member = null;
		String sql = "SELECT m_id, m_email FROM jspbookshop.member WHERE m_email LIKE ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, query + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				memberList = new ArrayList<Map<String, String>>();
				member = new HashMap<>();
				do {
					member.put(rs.getString("m_id"), rs.getString("m_email"));	// {"yeji1234" : "yejii1234@naver.com"}
				} while (rs.next());
			}
			memberList.add(member);
		} catch (Exception e) {
			System.out.println(" M.DAO : selectMembers() ERROR : "+e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
		System.out.println(" M.DAO : getEmailList() 종료");
		return memberList;
	}






	
}
