package action;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.BookFrontController;
import util.AesCBCUtil;
import vo.ActionForward;
import vo.JsonMail;
import vo.Mail;

/** 임시 보관함 페이지 요청을 처리하는 Action 클래스 */
public class ManageMailTempListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		System.out.println(" M.MailTempList.A : execute() 호출");
		
		ArrayList<Mail> mails = new ArrayList<>();

		/* 읽어와야 할 임시 파일 및 경로 설정 
		 * System.getProperty("java.io.tmpdir") : 시스템에 저장된 임시 파일이 저장되는 경로를 반환 */
		System.out.println(" Temp file path: " + System.getProperty("java.io.tmpdir"));
				
		File dir = new File(System.getProperty("java.io.tmpdir"));
		File[] files = dir.listFiles((dir1, name) -> name.startsWith("MVC_"));
		
		String decodeData = new String();	
		
		if(files != null) {
			
			FileReader reader = null;
			
			for (File file : files) {
			    System.out.println(file.getName()+"--------------------");
			    String output = new String();	// 초기화
			    
			    /* 임시 파일 읽어오기 */
			    // BufferedReader reader = new BufferedReader(new FileReader(file));
			    reader = new FileReader(file);
			    
			    char[] buffer = new char[1024]; // buffer size of 1024 bytes
			    int bytesRead;
			    while ((bytesRead = reader.read(buffer, 0, buffer.length)) != -1) {
			    	System.out.print(new String(buffer, 0, bytesRead)+" ");
			    	output += new String(buffer, 0, bytesRead);	// char 배열을 인덱스 0부터 배열길이만큼 String형으로 생성
			    }
			    System.out.println("");
			    
			    /* 데이터 디코딩 */
			    AesCBCUtil aesUtil = AesCBCUtil.getInstance();
			    decodeData += aesUtil.decode(output) + "\n\n";
			}
			
			reader.close();
	
			System.out.println("---------디코딩된 데이터---------");
			System.out.println(decodeData);
			
			/* 데이터를 List<Mail> 타입의 객체로 변환 */
			String[] datas = decodeData.trim().split("\n\n");	// 앞뒤 공백 제거 후 \n\n으로 split
			
	        for(String data:datas) {
	        	Mail mail = new Mail();
	        	mails.add(mail.convertToMail(data));
	        }
		}
		
		HttpSession session = req.getSession(false);
		if(session != null) session.setAttribute("mailList", mails);
		
		req.setAttribute("listCount", mails.size());
		ActionForward forward = new ActionForward();
		forward.setPath("/manager/emailManage/tempList.jsp");
		forward.setRedirect(false);
		
		System.out.println(" M.MailTempList.A : execute() 종료");
		return forward;
	}

}
