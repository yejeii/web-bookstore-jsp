package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

/** 새로운 책 상품 등록 페이지를 보여주는 요청을 처리하는 Action 클래스*/
public class ManageBookRegistFormAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.BookRegistForm.A : execute() 호출");
		
		/* 포워딩할 때 가져갈 정보 저장 
		 * .ok -> .jsp : Forward
		 * */
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/bookManage/bookRegistForm.jsp");
		forward.setRedirect(false);
		
		System.out.println(" M.BookRegistForm.A : execute() 종료");
		return forward;
	}

}
