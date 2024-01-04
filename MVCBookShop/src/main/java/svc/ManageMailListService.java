package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.MailDAO;
import vo.Mail;

/** 메일 목록 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스*/
public class ManageMailListService {

	/*
	 * li값에 따라 다르게 DB 처리 
	 * li == a : 전부 
	 * li == g : 받은 메일함만 
	 * li == s : 보낸 메일함만 
	 * li == i : 중요 메일함만
	 */
	
	/** DB에 저장된 총 메일 개수(count(*))를 구하는 메서드 */
	public int getListCount(String li) {
		System.out.println(" M.MailList.S : getListCount() 호출");
		System.out.println(" li:"+li);
		
		int listCount = 0;
		
		/* DB 처리 */
		MailDAO mailDAO = MailDAO.getInstance();
		Connection conn = getConnection();
		mailDAO.setConnection(conn);

		switch (li) {
		case "g":
			listCount = mailDAO.selectListCount("hyoyongge2");
			break;
		case "s":
			listCount = mailDAO.selectListCount("hyoyongge2");
			break;
		case "i":
			listCount = mailDAO.selectListCount(1);
			break;
		default:
			listCount = mailDAO.selectListCount();
			break;
		}
		
		close(conn);
		
		System.out.println(" listCount : "+listCount);
		System.out.println(" M.MailList.S : getListCount() 종료");
		return listCount;
	}

	/** 모든 메일 목록을 ArrayList 객체 타입으로 반환하는 메서드 */
	public ArrayList<Mail> getMailList(String li) {
		System.out.println(" M.MailList.S : getMailList() 호출");
		System.out.println(" li:"+li);
		
		/* DB 처리 */
		MailDAO mailDAO = MailDAO.getInstance();
		Connection conn = getConnection();
		mailDAO.setConnection(conn);
		ArrayList<Mail> mailList = null;
		
		switch (li) {
		case "g":
			mailList = mailDAO.selectMailList("hyoyongge2");
			break;
		case "s":
			mailList = mailDAO.selectMailList("hyoyongge2");
			break;
		case "i":
			mailList = mailDAO.selectMailList(1);
			break;
		default:
			mailList = mailDAO.selectMailList();
			break;
		}
		
		close(conn);
		
		System.out.println(" M.MailList.S : getMailList() 종료");
		return mailList;
	}

	/** 미열람 이메일 개수를 가져오는 메서드 */
	public int getListCount(int i) {
		System.out.println(" M.MailList.S : getListCount(int i) 호출");
		
		int listCount = 0;
		
		/* DB 처리 */
		MailDAO mailDAO = MailDAO.getInstance();
		Connection conn = getConnection();
		mailDAO.setConnection(conn);
		listCount = mailDAO.selectListCount(i);
		
		System.out.println(" listCount : "+listCount);
		System.out.println(" M.MailList.S : getListCount(int i) 종료");
		return listCount;
	}

}
