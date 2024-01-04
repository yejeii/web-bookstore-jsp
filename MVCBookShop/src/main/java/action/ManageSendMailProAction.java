package action;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ManageSendMailProService;
import util.NaverMailSend;
import vo.ActionForward;
import vo.Mail;

/** 관리자가 회원에게 메일을 전송하는 요청을 처리하는 Action 클래스 */
public class ManageSendMailProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.SendMailPro.A : execute() 호출");
		
		/* 파일 설정 
		 * 1. 파일이 업로드될 서버 상의 실제 경로 저장
		 *    : E:\Eclipse_jsp_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVCBookShop
		 * 2. 파일을 업로드할 디렉토리명을 지정
		 * 3. 한번에 업로드할 수 있는 파일 크기(10Mbyte)
		 * 4.
		 * */
		String realFolder = "";	
		String saveFolder = "/sendMail";
		int fileSize = 10*1024*1024;
		
		/* 파일이 업로드될 서버 상의 물리적인 경로 얻어오기 */
		ServletContext context = req.getServletContext();
		realFolder = context.getRealPath(saveFolder);	// 파라미터로 지정한 디렉토리의 서버 상의 실제 경로를 get
		System.out.println(" M.SendMailPro.A : 서버 상에서 업로드될 물리적인 경로 - " + realFolder );
	
		/* 폴더 생성 처리 */
		File upDirFile = new File(realFolder);
		if(!upDirFile.exists()) {
			upDirFile.mkdir();
		}
		
		/* form 저장 및 서버에 첨부파일 저장 */
		MultipartRequest multi = 
				new MultipartRequest(req, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());

		List<String> fileNames = null;

		System.out.println(" 수신자 : "+ multi.getParameterValues("recipient_to[]"));
		System.out.println(" 수신자 : "+ multi.getParameterValues("recipient_to[]").toString());
		
		Mail mail = new Mail();
		mail.setMl_recipient(multi.getParameterValues("recipient_to[]"));
		mail.setMl_title(multi.getParameter("title"));
		mail.setMl_text(multi.getParameter("text"));
		
		@SuppressWarnings("unchecked")
		Enumeration<String> e = multi.getFileNames();
		fileNames = new ArrayList<>();
		
		while (e.hasMoreElements()) {
		    String fileName = multi.getFilesystemName((String) e.nextElement());	// 서버에 저장된 파일명(중복처리)

		    if (fileName != null) {
		    	fileNames.add(fileName);
		    }
		}
		mail.setMl_attachment(fileNames);
		
		System.out.println(" 전송될 메일 : "+mail.toString());
		
		/* 파일첨부 이메일 전송 작업 */
		NaverMailSend mailSend = new NaverMailSend();
		boolean sendSuccess = mailSend.sendEmail(mail, realFolder);
		
		if(sendSuccess) {
			// 성공 시 
			mail.setMl_sender(mailSend.getUser());	// 발신자 저장
			
			/* DB 작업 */
			ManageSendMailProService service = new ManageSendMailProService();
			boolean isRegistSuccess = service.insertMail(mail);
			
			if(isRegistSuccess) {
				resp.setContentType("text/html;charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>");
				out.println("alert('이메일이 전송되었습니다.');");
				out.println("location.href='/manager/memberManage/memberList.ma';");
				out.println("</script>");
			} else {
				resp.setContentType("text/html;charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.println("<script>");
				out.println("alert('DB 저장에 실패했습니다.');");
				out.println("history.back(-1);");
				out.println("</script>");
			}
		} else {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('이메일이 전송에 실패했습니다.');");
			out.println("history.back(-1);");
			out.println("</script>");
		}
		
		System.out.println(" M.SendMailPro.A : execute() 종료");
		return null;
	}

}
