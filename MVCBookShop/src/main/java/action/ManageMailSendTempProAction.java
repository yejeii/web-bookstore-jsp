package action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.compiler.NewlineReductionServletWriter;
import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import util.AesCBCUtil;
import util.RandomUtil;
import vo.Mail;

/** 이메일 임시저장 요청을 처리하는 Action 클래스 */
public class ManageMailSendTempProAction implements AjaxAction {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.MailSendTempPro.A : execute() 호출");
		
		/* 파일 설정 
		 * 1. 파일이 업로드될 서버 상의 실제 경로 저장
		 *    : E:\Eclipse_jsp_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVCBookShop
		 * 2. 파일을 업로드할 디렉토리명을 지정
		 * 3. 한번에 업로드할 수 있는 파일 크기(10Mbyte)
		 * 4.
		 * */
		String realFolder = "";	
		String saveFolder = "/tempMail";
		int fileSize = 10*1024*1024;
		
		/* 파일이 업로드될 서버 상의 물리적인 경로 얻어오기 */
		ServletContext context = req.getServletContext();
		realFolder = context.getRealPath(saveFolder);	// 파라미터로 지정한 디렉토리의 서버 상의 실제 경로를 get
		System.out.println(" M.MailSendTempPro.A : 서버 상에서 업로드될 물리적인 경로 - " + realFolder );
	
		/* 폴더 생성 처리 */
		File tempFile = new File(realFolder);
		if(!tempFile.exists()) {
			tempFile.mkdir();
		}
		
		/* 서버에 첨부파일 저장 및 form 파라미터 Mail 클래스타입으로 생성 */
		MultipartRequest multi = 
				new MultipartRequest(req, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

		List<String> fileNames = null;
		Mail mail = new Mail();
		
		// System.out.println(" 수신자 수 : "+ multi.getParameterValues("recipient_to[]").length);
		
		// 수신자 
		if(multi.getParameterValues("recipient_to[]") != null) {
			System.out.print(" 수신자 : ");
			mail.setMl_recipient(multi.getParameterValues("recipient_to[]"));
			
			// 수신자 확인용
			for(String recipient : multi.getParameterValues("recipient_to[]")) {
				System.out.print(recipient+", ");
			}
			System.out.println("");
		}
		
		// 제목, 내용
		if(multi.getParameter("title") != null) mail.setMl_title(multi.getParameter("title"));
		else mail.setMl_title(null);
		if(multi.getParameter("text") != null) mail.setMl_text(multi.getParameter("text"));
		else mail.setMl_text(null);
		
		// 첨부 파일
		Enumeration<String> e = multi.getFileNames();
		fileNames = new ArrayList<>();
		
		while (e.hasMoreElements()) {
		    String fileName = multi.getFilesystemName((String) e.nextElement());	// 서버에 저장된 파일명(중복처리)

		    if (fileName != null) {
		    	fileNames.add(fileName);
		    }
		}
		mail.setMl_attachment(fileNames);
		
		// Mail 클래스타입의 데이터를 String형으로 convert(암호화 하기 위함)
		String toString = mail.toString();
		System.out.println(" 저장될 데이터 : "+toString);
		
		/* 데이터 암호화 처리 */
		AesCBCUtil aesUtil = AesCBCUtil.getInstance();	// AesCBCUtil 인스턴스 생성(비밀키 및 Iv 설정)
		String encodeData = aesUtil.encode(toString);
		System.out.println(" 암호화된 데이터 : " + encodeData);
		System.out.println(" 암호키: " + aesUtil.getSecretKey().toString());
		System.out.println(" 암호화블록: " + aesUtil.getIv().toString());
		
		/* 임시 파일에 저장
		 * File.createTempFile("파일명", "확장자") :
		 *  Creates an empty file in the default temporary-file directory
		 *  저장경로 : C:\Users\f\AppData\Local\Temp */
		String formattedDate = new SimpleDateFormat("YYMMdd").format(new Date());
		String filename = "MVC_" + RandomUtil.createRanAlNum(10) + "_" + formattedDate;
		System.out.println(" 임시파일 이름 : "+filename);
		
		File temp = File.createTempFile(filename, ".txt");
		boolean saveTemp = false;
		
		if(temp != null) {
			
			// write
			BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
			writer.write(encodeData);
			writer.close();
	
			saveTemp = true;
			System.out.println(" 임시파일에 데이터 저장 완료");
		}
		
		/* 브라우저 이동 처리 */
		PrintWriter outWriter = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");
		if(saveTemp) {
			outWriter.println("<script>");
			outWriter.println("alert('임시 메일이 저장되었습니다.');");
			outWriter.println("location.href='/manager/emailManage/list.ma?li=a'");
			outWriter.println("</script>");
		} else {
			outWriter.println("<script>");
			outWriter.println("alert('임시 메일이 저장되지 못했습니다.');");
			outWriter.println("history.back(-1);");
			outWriter.println("</script>");
		}
		
		System.out.println(" M.MailSendTempPro.A : execute() 종료");
	}

}
