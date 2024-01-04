package svc;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MemberDAO;
import vo.Member;

/** 관리자가 회원 목록을 가져오는 Service 클래스 */
public class ManageMemberListService {

	/** 모든 회원을 가져오는 메서드 */
	public ArrayList<Member> getMembers() {
		System.out.println(" M.MemberList.S : getMembers() 호출");
		
		ArrayList<Member> members = null;
		
		/* DB 작업 */
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection conn = getConnection();
		memberDAO.setConnection(conn);
		members = memberDAO.selectMembers();
		
		System.out.println(" M.MemberList.S : getMembers() 종료");
		return members;
	}

}
