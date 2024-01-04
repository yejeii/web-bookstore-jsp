package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BookCscenterFaqService;
import vo.ActionForward;
import vo.Faq;
import vo.FaqKeyword;

/** fcode에 따른 페이지 요청을 처리하는 Action 클래스 */ 
public class BookCscenterFaqAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.CscenterFaq.A : execute() 호출");
		
		/* 파라미터 저장 */
		int fcode = 0; 
		if(req.getParameter("fcode") != null) {
			fcode = Integer.parseInt(req.getParameter("fcode"));
		}
		
		/* DB에서 fcode에 따른 데이터 가져오기 */
		BookCscenterFaqService service = new BookCscenterFaqService();
		ArrayList<FaqKeyword> fkList = new ArrayList<>();
		if(fcode != 0) {
			fkList = service.selectFks(fcode);
		} else {
			fkList.add(new FaqKeyword("Best10", fcode));
		}
		
		ArrayList<Faq> faqList = service.selectFaq(fcode);
		
		/* 포워딩할 때 가져갈 정보 저장 
		 * 1. faq 키워드를 속성으로 공유
		 * 2. faq를 속성으로 공유 */
		req.setAttribute("fkList", fkList);
		req.setAttribute("faqList", faqList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/cscenter/faq.jsp");
		forward.setRedirect(false);
		System.out.println(" B.CscenterFaq.A : forward 방식 : " + forward.isRedirect());
		
		System.out.println(" B.CscenterFaq.A : execute() 종료");
		return forward;
	}

}
