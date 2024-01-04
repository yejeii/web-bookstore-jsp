package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import svc.BookReviewRegistProService;
import util.Util;
import vo.ActionForward;
import vo.Review;

/* 책 하나에 대한 리뷰 등록 요청을 처리하는 Action 클래스 */
public class BookReviewRegistProAction implements AjaxAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.ReviewRegistPro.A : execute() 호출");
		
		/* 세션 확인 */
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user");
		
		/* ajax로 넘어온 데이터를 Review 객체에 저장 */
		int nowPage = Integer.parseInt(req.getParameter("page"));
		int r_buy_opt;
		if(req.getParameter("r_buy_opt") != null){
			r_buy_opt =  Integer.parseInt(req.getParameter("r_buy_opt"));
		} else{
			r_buy_opt = 0;
		}
		// 크로스 사이트 스크립트 공격에 대응
		String r_text = Util.replaceParameter(req.getParameter("r_text"));
		
		Review review = new Review();
		review.setR_b_id(Integer.parseInt(req.getParameter("r_b_id")));
		review.setR_m_id(req.getParameter("r_m_id"));
		review.setR_text(r_text.replaceAll("\r\n", "<br/>"));	// Enter가 있으면 br로 바꿔준다.
		review.setR_buy_opt(r_buy_opt);
		review.setR_star(Integer.parseInt(req.getParameter("r_star")));
		
		System.out.println(" B.ReviewRegistPro.A : 새로 등록되는 리뷰 - "+review.toString());
		
		/* DB에 저장 처리 */
		BookReviewRegistProService service = new BookReviewRegistProService();
		boolean isRegistSuccess = service.insertReview(review);
		
		/* DB 처리에 따른 결과값 처리 */
		if(isRegistSuccess) {
			req.setAttribute("success", isRegistSuccess);
		} else {
			req.setAttribute("success", isRegistSuccess);
		}
		
		System.out.println(" B.ReviewRegistPro.A : execute() 종료");
		return;
	}

}
