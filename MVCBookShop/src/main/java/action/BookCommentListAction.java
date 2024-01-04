package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import svc.BookCommentListService;
import vo.Comment;

/** b_id에 해당하는 모든 코멘트 출력 요청을 처리하는 Action 클래스 */
public class BookCommentListAction implements AjaxAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.CommentList.A : execute() 호출");
		
		/* AJAX로 넘어온 파라미터 받기 */
		String b_id = req.getParameter("c_b_id"); 
		int c_b_id = Integer.parseInt(b_id);
		
		/* DB에 접근해 b_id에 해당하는 모든 코멘트 가져오기 */
		BookCommentListService service = new BookCommentListService();
		int commentCount = service.getCommentCount(c_b_id);
		ArrayList<Comment> commentList = service.getCommentList(c_b_id);
		
		/* 가져온 코멘트 배열을 JSON 형태의 문자열로 보내주기 위한 처리 
		 * 1. DB로부터 가져온 코멘트 배열을 JSONObject에 담은 후, JSONArray에 순서대로 저장한다.
		 * 2. 저장된 JSONArray 객체를 새로운 JSONObject에 저장한다. key는 comments로 한다.
		 * 3. JSONArray 객체가 담긴 JSONObject를 JSONString으로 만들어 json 변수에 저장한다.*/
		String json = "";	// 파싱할 최종 데이터 담아주기 위한 문자열
		JSONArray jsArr = new JSONArray();
		JSONObject comments = new JSONObject();	
		
		if(commentCount == 0) {
			json = "no-comments";
		} else if (commentCount > 0 && commentList != null) {
			for(Comment comment: commentList) {
				JSONObject commentObject = new JSONObject();
				commentObject.put("c_id", comment.getC_id());
				commentObject.put("c_b_id", c_b_id);
				commentObject.put("c_m_id", comment.getC_m_id());
				commentObject.put("c_title", comment.getC_title());
				commentObject.put("c_text", comment.getC_text());
				commentObject.put("c_regdate", comment.getC_regdate().toString());
				commentObject.put("c_empathy", comment.getC_empathy());
				commentObject.put("c_ref", comment.getC_ref());
				commentObject.put("c_lev", comment.getC_lev());
				commentObject.put("c_seq", comment.getC_seq());
				
				// 오브젝트 데이터를 JSONArray에 순서대로 저장
				jsArr.add(commentObject);
			}
		
			// 최종적으로 comments 오브젝트에 JSON배열 저장
			comments.put("comments", jsArr);
			
			// 파싱할 데이터 저장
			json = comments.toJSONString();
		}
		
		/* 파싱할 최종 데이터 보내기 
		 * 1. 단순 데이터(json 변수)를 response에 담아 보낼 것이므로 setCharacterEncoding("utf-8") 설정 */
		
		// dataType : text (bookView.jsp ajax)
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		
		if(json.equals("no-comments")) { 
			out.print("no-comments");
		} else {
			out.print(json);
		}
		out.close();
		
		System.out.println(" B.CommentList.A : execute() 종료");
	}

}
