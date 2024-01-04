package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import svc.BookCscenterFaqService;
import vo.Faq;

/** fk_code에 따른 FAQ 요청을 처리하는 Action 클래스 */
public class CsCenterFkcodeFaqAction implements AjaxAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" Cs.FkcodeFaq.A : execute() 호출");
		
		/* 파라미터 저장 */
		int fk_code = Integer.parseInt(req.getParameter("fk_code"));
		int fc_code = Integer.parseInt(req.getParameter("fc_code"));
		
		/* DB에서 fk_code에 따른 데이터 가져오기 */
		BookCscenterFaqService service = new BookCscenterFaqService();
		ArrayList<Faq> faqList = service.selectFaq(fc_code, fk_code);
		
		/* 브라우저 스크립트에서 데이터를 쉽게 사용하기 위한 처리(faq 배열을 JSON 형태의 문자열로 만드는 처리) 
		 * {
		 *   "faqs" :
		 *   	[
		 *     	  {"fk_code" : 1, "fk_value" : "회원가입/탈퇴", "f_id" : 1, "f_title" : "제목", "f_text" : "내용"},
		 *        {"fk_code" : 1, "fk_value" : "회원가입/탈퇴", "f_id" : 2, "f_title" : "제목2", "f_text" : "내용2"},
		 *        {"fk_code" : 2, "fk_value" : "본인성인인증", "f_id" : 3, "f_title" : "제목3", "f_text" : "내용3"}
		 *   	]
		 * }
		 * */
		String faqStr = null;
		JSONArray faqsarr = new JSONArray();
		JSONObject faqs = new JSONObject();
		
		if(faqList != null) {
			for(Faq faq : faqList) {
				JSONObject faqObj = new JSONObject();
				faqObj.put("fk_code", faq.getF_fk_code());
				faqObj.put("fk_value", faq.getFk_value());
				faqObj.put("f_id", faq.getF_id());
				faqObj.put("f_title", faq.getF_title());
				faqObj.put("f_text", faq.getF_text());

				// 오브젝트 데이터를 JSONArray에 순서대로 저장
				faqsarr.add(faqObj);
			}
			
			// 최종적으로 faqs 오브젝트에 JSON배열 저장
			faqs.put("faqs", faqsarr);
		} else {
			faqs.put("faqs", "no-faqs");
		}
		
		// 파싱할 데이터 저장
		faqStr = faqs.toJSONString();
		
		/* 파싱할 최종 데이터 보내기 
		 * 1. 단순 데이터(json 변수)를 response에 담아 보낼 것이므로 setCharacterEncoding("utf-8") 설정 */
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		
		out.print(faqStr);
		out.close();
		
		System.out.println(" Cs.FkcodeFaq.A : execute() 종료");
	}

}
