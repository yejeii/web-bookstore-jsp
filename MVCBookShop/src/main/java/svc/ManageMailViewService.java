package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.MailDAO;
import vo.Book;
import vo.Mail;

/** 이메일 상세 정보보기 요청을 처리하는 비즈니스 로직을 구현하는 Service 클래스*/
public class ManageMailViewService {

	/** ml_index에 해당하는 이메일을 리턴하는 메서드 */
	public Mail getMailView(int ml_index) {
		System.out.println(" M.MailView.S : getMailView() 호출");
		
		/* DB 처리
		 * 1. 커넥션 객체 생성
		 * 2. 상세 정보를 요청하는 이메일을 열람으로 설정시키는 메서드 호출 
		 * 3. ml_index에 해당하는 이메일을 리턴하는 메서드 호출
		 * */
		Connection conn = getConnection();
		MailDAO mailDAO = MailDAO.getInstance();
		mailDAO.setConnection(conn);
		
		int readCount = mailDAO.updateRead(ml_index);
		
		if(readCount > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		Mail mail = mailDAO.selectMail(ml_index);
		close(conn);
		
		System.out.println(" M.MailView.S : getMailView() 종료");
		return mail;
	}

}
