package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import svc.BookCatgyService;
import vo.ActionForward;
import vo.BookCatgyCode;

/** 메인 페이지 Action 클래스 */
public class MainAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" Main.A : execute() 호출");

		/* 도서 분류 데이터 세팅 */
		BookCatgyService service = new BookCatgyService();
		ArrayList<BookCatgyCode> catgyList = service.getCatgyList();
		
		/* JAVA 데이터 -> JSON 형태로 */
		JSONObject singleCatgyObj = null;
		JSONObject totalObj = new JSONObject();
		JSONArray catgyArr = new JSONArray();
		
		for(BookCatgyCode catgy : catgyList) {
			singleCatgyObj = new JSONObject();
			singleCatgyObj.put("code", catgy.getCode());
			singleCatgyObj.put("name", catgy.getName());
			singleCatgyObj.put("code_ref_md", catgy.getCode_ref_md());
			singleCatgyObj.put("code_ref_mn", catgy.getCode_ref_mn());
			
			catgyArr.add(singleCatgyObj);
		}
		
		totalObj.put("catgys", catgyArr);
		
		/* 애플리케이션의 ServletContext의 속성으로 설정 -> JSP에서 application 내장 객체로 사용가능 
		 * req.getServletContext() : returns the servlet context to which this ServletRequest was last dispatched */
		req.getServletContext().setAttribute("catgys", totalObj);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/main.jsp");
		forward.setRedirect(false);
		
		System.out.println(" Main.A : execute() 종료");
		return forward;
	}

}
