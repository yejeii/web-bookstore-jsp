package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MailDAO;
import vo.Mail;

/** 메일을 저장하는 비즈니스 로직을 구현하는 Service 클래스*/
public class ManageSendMailProService {

	/** 메일을 등록하는 메서드 */
	public boolean insertMail(Mail mail) {
		System.out.println(" M.SendMailPro.S : insertMail() 호출");
		
		boolean isRegistSuccess = false;
		
		/* DB 작업 
		 * 1. 커넥션 풀에서 Connection 객체 얻어오기
		 * 2. 싱글톤 방식으로 BookDAO 클래스의 인스턴스를 get 
		 * 3. BookDAO 클래스에서 DB 작업을 수행할 때 사용할 Connection 객체를 주입 
		 * 4. DB에 새로운 글 갱신(INSERT) 메서드 호출
		 * */
		Connection conn = getConnection();
		MailDAO mailDAO = MailDAO.getInstance();
		mailDAO.setConnection(conn);
		int insertCount = mailDAO.insertMail(mail);
		
		if(insertCount > 0) {
			// 성공적으로 삽입된 경우. 트랜잭션 영구반영
			commit(conn);
			isRegistSuccess = true;
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		System.out.println(" M.BookRegistPro.S : isRegistSuccess - " + isRegistSuccess);
		System.out.println(" M.SendMailPro.S : insertMail() 종료");
		return isRegistSuccess;
	}

	
}
