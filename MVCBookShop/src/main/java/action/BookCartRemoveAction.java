package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BookCartRemoveService;
import vo.ActionForward;

/** 장바구니 항목 삭제 요청을 처리하는 Action 클래스*/
public class BookCartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.C.R.A : execute() 호출");
		
		/* 요청 URL에서 전달된 파라미터 값 저장 
		 * 동시에 여러 개의 장바구니 항목을 삭제할 수 있으므로 삭제할 장바구니 항목의 id 파라미터 값을 배열 형태로 받는다
		 * getParameterValues()는 파라미터의 모든 값을 문자열의 배열 형태로 리턴하기 떄문에 따로 int형 배열로 바꿔준다.
		 */
		String[] idArray = req.getParameterValues("remove");
		
		int[] intIdArray = null;
		if(idArray != null) {
			intIdArray = new int[idArray.length];
			for(int i=0; i<idArray.length; i++) {
				intIdArray[i] = Integer.parseInt(idArray[i]);
			}
		}
		
		/* DB 접근이 필요한 비즈니스 로직 호출 
		 * 장바구니 항목 삭제 요청을 처리하는 메서드 호출 */
		BookCartRemoveService service = new BookCartRemoveService();
		service.cartRemove(req, intIdArray);
		
		/* 포워딩할 때 가져갈 정보 저장 
		 * .ok -> .ok : redirect
		 * */
		ActionForward forward = new ActionForward();
		forward.setPath("/bookCartList.ok");
		forward.setRedirect(true);
		
		System.out.println(" B.C.R.A : execute() 종료");
		return forward;
	}

}
