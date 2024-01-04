package svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.FaqDAO;
import dao.FaqKeyDAO;
import vo.Faq;
import vo.FaqKeyword;

/** fcode에 따른 비즈니스 로직을 구현하는 Service 클래스*/
public class BookCscenterFaqService {

	/** fcode에 따른 fkeyword를 가져오는 메서드 */
	public ArrayList<FaqKeyword> selectFks(int fcode) {
		System.out.println(" B.CscenterFaq.S : selectFks() 호출");
		
		/* DB 처리 */
		FaqKeyDAO faqkeyDAO = FaqKeyDAO.getInstance();
		Connection conn = getConnection();
		faqkeyDAO.setConnection(conn);
		ArrayList<FaqKeyword> fkList = faqkeyDAO.selectFkList(fcode);
		close(conn);
		
		System.out.println(" B.CscenterFaq.S : selectFks() 종료");
		return fkList;
	}

	/** fcode에 따른 FAQ 데이터를 ArrayList 객체 타입으로 반환하는 메서드 */
	public ArrayList<Faq> selectFaq(int fcode) {
		System.out.println(" B.CscenterFaq.S : selectFaq() 호출");
		
		/* DB 처리 */
		FaqDAO faqDAO = FaqDAO.getInstance();
		Connection conn = getConnection();
		faqDAO.setConnection(conn);
		ArrayList<Faq> faqList = faqDAO.selectFaqList(fcode);
		close(conn);
		
		System.out.println(" B.CscenterFaq.S : selectFaq() 종료");
		return faqList;
	}
	
	/** fc_code, fk_code에 따른 FAQ 데이터를 ArrayList 객체 타입으로 반환하는 메서드 */
	public ArrayList<Faq> selectFaq(int fc_code, int fk_code) {
		System.out.println(" B.CscenterFaq.S : selectFaq(fc_code, fk_code) 호출");
		
		/* DB 처리 */
		FaqDAO faqDAO = FaqDAO.getInstance();
		Connection conn = getConnection();
		faqDAO.setConnection(conn);
		ArrayList<Faq> faqList = faqDAO.selectFaqList(fc_code, fk_code);
		close(conn);
		
		System.out.println(" B.CscenterFaq.S : selectFaq(fc_code, fk_code) 종료");
		return faqList;
	}
	
	

}
