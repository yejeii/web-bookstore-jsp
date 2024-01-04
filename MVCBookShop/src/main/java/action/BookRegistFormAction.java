package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import svc.BookCatgyService;
import vo.ActionForward;
import vo.BookCatgyCode;

public class BookRegistFormAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// TODO Auto-generated method stub
		/* DB에서 카테고리 가져오기 */
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
		
		/* request 속성에 공유 */
		req.setAttribute("catgys", totalObj);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/regist.jsp");
		forward.setRedirect(false);
		
		System.out.println(" B.RegistForm.A : execute() 종료");
		return forward;
	}

}
