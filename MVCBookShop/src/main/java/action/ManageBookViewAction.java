package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import svc.BookViewService;
import vo.ActionForward;
import vo.Book;
import vo.BookCatgyCode;

/** 책 상품 상세보기 요청을 처리하는 Action 클래스 */
public class ManageBookViewAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.BookView.A : execute() 호출");
		
		/* URL로 건너온 파라미터 저장 */		
		int b_id = Integer.parseInt(req.getParameter("b_id"));	// 상품 아이디
		
		/* DB에 접근해 책 상품 상세보기 비즈니스 로직 처리 */
		BookViewService service = new BookViewService();
		Book book = service.getBookView(b_id);
		
		System.out.println(" 도서 정보 : "+book.toString());
		System.out.println(book.getB_name()+"의 이미지 경로 : "+req.getServletContext().getRealPath("/bookImage")+"/"+book.getB_image());
		
		/* JAVA 데이터(서브 분류) -> JSON 형태로 */
		JSONObject singleCatgyObj = null;
		JSONObject totalSubCatgyObj = new JSONObject();
		JSONArray subCatgyArr = null;
		
		if(book.getBookSubCatgyList() != null) {
			subCatgyArr = new JSONArray();
			for(BookCatgyCode catgy : book.getBookSubCatgyList()) {
				singleCatgyObj = new JSONObject();
				singleCatgyObj.put("code", catgy.getCode());
				singleCatgyObj.put("name", catgy.getName());
				singleCatgyObj.put("code_ref_md", catgy.getCode_ref_md());
				singleCatgyObj.put("code_ref_mn", catgy.getCode_ref_mn());
				singleCatgyObj.put("code_ref_md_name", catgy.getCode_ref_md_name());
				singleCatgyObj.put("code_ref_mn_name", catgy.getCode_ref_mn_name());
				
				subCatgyArr.add(singleCatgyObj);
			}
		}
		totalSubCatgyObj.put("subCatgyObj", subCatgyArr);
		
		/* 포워딩할 때 가져갈 정보 저장 
		 * 책 상품 정보를 request 영역에 속성으로 공유 
		 * JSON 타입의 서브분류를 속성으로 공유
		 * .ok -> .jsp : Forward
		 * */
		req.setAttribute("book", book);
		req.setAttribute("subCatgyObj", totalSubCatgyObj);
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/bookManage/bookView.jsp");
		forward.setRedirect(false);
		
		System.out.println(" M.BookView.A : execute() 종료");
		return forward;
	}

}
