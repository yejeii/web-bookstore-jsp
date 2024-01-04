package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.compiler.NewlineReductionServletWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import svc.MemberSearchEmailService;

/** 이메일을 검색하는 요청을 처리하는 Action 클래스 */
public class ManageMailSearchProAction implements AjaxAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" EmailSearchPro.A : execute() 호출");
		
		/* 파라미터 저장 */
		String query = req.getParameter("q");
		
		/* DB 처리 */
		MemberSearchEmailService service = new MemberSearchEmailService();
	    ArrayList<Map<String, String>> memberMapList = service.getEmailMapList(query); 

	    /* JSON 형태의 String으로 변형 */
	    JSONObject jObject = new JSONObject();
	    JSONObject emailsObject = null;
	    JSONArray jArray = new JSONArray();
	    
	    if(memberMapList != null) {
	    	// query로 시작하는 이메일 리스트가 저장된 경우
	    	
		    for(Map<String, String> member: memberMapList) {
		    	// [hashmap 객체 선언 및 데이터 삽입]
				HashMap<String, String> hashmap = new HashMap<String, String>(member);
				System.out.println("Map : " + hashmap.toString());
				
				// hashmap key 확인 >> key 데이터로 value 출력
				for(Entry<String, String> elem : hashmap.entrySet()){
					
					// JSONObject 객체에 Map 추가
					emailsObject = new JSONObject();
					emailsObject.put("m_id", elem.getKey());
					emailsObject.put("m_email", elem.getValue());
					System.out.println("key : " + elem.getKey());
					System.out.println("value : " + elem.getValue());
					System.out.println("");
					
					// JSONArray 객체에 JSONObject 추가
					jArray.add(emailsObject);
				}
		    }
	    
		    jObject.put("emails", jArray);
	    } 
	    else {
	    	jObject.put("emails", "none");
	    }
	    
	    /* 결과 응답 처리 
	     * 응답할 결과 형태 : json  -> dataType: 'JSON'
	     * */
	    resp.setContentType("application/json");
	    resp.setCharacterEncoding("UTF-8");
	    resp.getWriter().write(new Gson().toJson(jObject));

	    System.out.println(" 최종 보낼 JSON 형식의 데이터 : "+ jObject.toString());
		System.out.println(" EmailSearchPro.A : execute() 종료");
	}

}
