package action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import svc.BookCscenterFaqService;
import vo.Faq;
import vo.FaqKeyword;

public class CsCenterFaqAction implements AjaxAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" Cs.Faq.A : execute() 호출");
		
		/* 파라미터 저장 */
		int fcode = Integer.parseInt(req.getParameter("fcode"));
		
		/* DB에서 fcode에 따른 데이터 가져오기 */
		BookCscenterFaqService service = new BookCscenterFaqService();
		ArrayList<FaqKeyword> fkList = new ArrayList<>();
		if(fcode != 0) {
			 fkList = service.selectFks(fcode);
		}
		ArrayList<Faq> faqList = service.selectFaq(fcode);
		
		/* 브라우저 스크립트에서 데이터를 쉽게 사용하기 위한 처리(faq 배열을 JSON 형태의 문자열로 만드는 처리) 
		 * {
		 * 	 "fc_code" : 1,
		 *   "fc_value" : "회원",
		 *   "fks" : 
		 *   	[  {"fk_code" : 1, "fk_value" : "회원가입/탈퇴"},
		 *   	   {"fk_code" : 2, "fk_value" : "본인성인인즈"}
		 *   	],
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
		JSONArray fkssarr = new JSONArray();
		JSONObject faqs = new JSONObject();
		
		if(fkList.size() > 0) {
			
			for(FaqKeyword fk : fkList) {
				JSONObject fkObj = new JSONObject();
				fkObj.put("fk_code", fk.getFk_code());
				fkObj.put("fk_value", fk.getFk_value());
				
				fkssarr.add(fkObj);
			}
			
			// 최종적으로 faqs 오브젝트에 JSON배열 저장
			faqs.put("fks", fkssarr);
			faqs.put("fc_code", fkList.get(0).getFk_fc_code()); // add fc_code and fc_value only once
		    faqs.put("fc_value", fkList.get(0).getFc_value());
			
		} else {
			// Best10 버튼인 경우
			faqs.put("fks", "no-fks");
			faqs.put("fc_code", fcode); 
		    faqs.put("fc_value", "Best10");
		}
		
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
		
		System.out.println(" Cs.Faq.A : execute() 종료");
	}

}
