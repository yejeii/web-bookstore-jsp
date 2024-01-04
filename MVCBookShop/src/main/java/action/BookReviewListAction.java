package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.events.StartDocument;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import svc.BookReviewListService;
import vo.Review;

/** b_id에 해당하는 모든 리뷰 출력 요청을 처리하는 Action 클래스 */
public class BookReviewListAction implements AjaxAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.ReviewList.A : execute() 호출");
		
		/* AJAX로 넘어온 파라미터 받기 */
		String b_id = req.getParameter("b_id"); 
		String nowPage = req.getParameter("page");
		System.out.println(" B.ReviewList.A : b_id - "+b_id+", page - "+nowPage);
		int r_b_id = Integer.parseInt(b_id);
		
		/* DB에 접근해 b_id에 해당하는 모든 리뷰 가져오기 */
		BookReviewListService service = new BookReviewListService();
		int reviewCount = service.getReviewCount(r_b_id);
		ArrayList<Review> reviewList = service.getReviewList(r_b_id);
		
		/* 만들 JSON Object 
		 * {"univ" :	[
		 * 					{"r_text":"리뷰1", "r_m_id":"yejii", "r_regdate":"2023-02-12", ...},
		 * 					{"r_text":"리뷰2", "r_m_id":"y1234", "r_regdate":"2023-01-11", ...}
		 * 			 	]
		 *  "starCnt" : {"star1":2, "star2":24, "star3":12, "star4":10, "star5":0}
		 *  } 
		 * */
		
		/* 가져온 리뷰를 JSON 형태의 문자열로 보내주기 위한 처리 
		 * 1. DB로부터 가져온 리뷰 리스트를 JSONObject에 담은 후, JSONArray에 순서대로 저장한다.
		 * 2. 저장된 JSONArray 객체를 새로운 JSONObject에 저장한다. key는 univ로 한다.
		 * 3. JSONArray 객체가 담긴 JSONObject를 JSONString으로 만들어 json 변수에 저장한다.*/
		String json = "";	// 파싱할 최종 데이터 담아주기 위한 문자열
		int star1 = 0;		// 별점을 누적할 변수
		int star2 = 0;		// 별점을 누적할 변수
		int star3 = 0;		// 별점을 누적할 변수
		int star4 = 0;		// 별점을 누적할 변수
		int star5 = 0;		// 별점을 누적할 변수
		JSONArray jsArr = new JSONArray();
		JSONObject star = new JSONObject();
		JSONObject univ = new JSONObject();		
		
		if(reviewCount != 0) {
			if(reviewList != null) {
				for(Review review: reviewList) {
					JSONObject reviewObject = new JSONObject();
					reviewObject.put("r_text", review.getR_text());
					reviewObject.put("r_m_id", review.getR_m_id());
					reviewObject.put("r_regdate", review.getR_regdate().toString());	// JSON에 문자열 형태로 넣어주기 위한 처리. Convert this Date Object to String 
					reviewObject.put("r_star", review.getR_star());
					reviewObject.put("r_buy_opt", review.getR_buy_opt());
					
					switch (review.getR_star()) {
					case 1:
						star1 ++;
						break;
					case 2:
						star2 ++;
						break;
					case 3:
						star3 ++;
						break;
					case 4:
						star4 ++;
						break;
					case 5:
						star5 ++;
						break;
					}
					
					// start Object에 프로퍼티 추가
					star.put("star1", star1);
					star.put("star2", star2);
					star.put("star3", star3);
					star.put("star4", star4);
					star.put("star5", star5);
					
					// 오브젝트 데이터를 JSONArray에 순서대로 저장
					jsArr.add(reviewObject);
				}
			}
			
			// 최종적으로 univ오브젝트에 JSON배열 저장
			univ.put("univ", jsArr);
			univ.put("startCnt", star);
			
			// 파싱할 데이터 저장
			json = univ.toJSONString();
		}
				
		/* 파싱할 최종 데이터 보내기 
		 * 1. 단순 데이터(json 변수)를 response에 담아 보낼 것이므로 setCharacterEncoding("utf-8") 설정 */
		
		// dataType : text (bookView.jsp ajax)
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		
		if(reviewCount != 0 && json != null) { 
			out.print(json);
		} else {
			out.print("no-review");
		}
		out.close();

		System.out.println(" B.ReviewList.A : 최종적으로 보낼 JSON 형태의 문자열 - "+json);
		System.out.println(" B.ReviewList.A : execute() 종료");
		return;
	}

}
