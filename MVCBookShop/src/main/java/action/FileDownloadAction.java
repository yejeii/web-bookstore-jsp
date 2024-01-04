package action;

import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.ActionForward;

/** 브라우저에서 실행되는 파일에 대해 다운로드 박스가 출력되는 요청을 처리하는 Action 클래스 */
public class FileDownloadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" FileDownload.A : execute() 호출");
		
		resp.setCharacterEncoding("utf-8");
		
		/* 파라미터 저장 */
		String ml_attachment = req.getParameter("filename").trim();	// 혹시 모를 앞뒤 공백 제거
		System.out.println(" 다운로드할 파일명 : "+ml_attachment);
		
		/* 서버에 저장된 파일 경로 */
		String savePath = "sendMail";
		String sDownloadPath = req.getServletContext().getRealPath(savePath);
		String sfilePath = sDownloadPath + "\\" + ml_attachment;
		// \\ : \(원 표시. 경로 표시)
		// sDownloadPath: E:\Eclipse_jsp_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MVCBookShop\sendMail
		
		/* 파일을 읽어오는 설정
		 * 1. 한 번에 읽고 출력할 바이트 크기로 배열 생성(2KB씩 읽도록 설정)
		 * 2. 다운로드할 파일의 경로를 인자로 지정하면서 FileInputStream 객체 생성
		 * 3. 다운로드할 파일의 마임 타입 얻어오기 */
		byte b[] = new byte[1024*2];
		FileInputStream in = new FileInputStream(sfilePath);
		String sMimeType = req.getServletContext().getMimeType(sfilePath);
		System.out.println(" 다운로드할 파일의 마임 타입 : "+sMimeType);
		
		if(sMimeType == null) sMimeType = "application/octet-stream";	// 기본 마임 타입 지정
		
		/* 브라우저 설정
		 * 1. 응답할 데이터의 마임타입을 다운로드할 파리의 마임타입으로 지정
		 * 2. 브라우저가 Internet Explorer인지 판단
		 * 3. 브라우저에서 해석되는 확장자의 파일도 다운로드 박스가 실행되게 처리
		 * 	  헤더 정보 설정 시 Content-Disposition 값을 attachment로 설정하면 모든 파일에 대해서 다운로드 박스가 실행됨. */
		resp.setContentType(sMimeType);
		String agent = req.getHeader("User-Agent");
		boolean ieBrowser = (agent.indexOf("MSIE") > -1) || (agent.indexOf("Trident") > -1);
		
		if(ieBrowser) {
			// "+" 문자를 공백문자("%20")으로 변경
			ml_attachment = URLEncoder.encode(ml_attachment, "UTF-8").replaceAll("\\+", "%20");
		} else {
			// 다운로드 시 한글 파일명이 깨지지 않도록 처리
			ml_attachment = new String(ml_attachment.getBytes("UTF-8"), "iso-8859-1");
		}
		
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Content-Disposition", "attachment;filename= "+ml_attachment);
		
		/* 브라우저 PC에 다운로드 처리
		 * 1. 파일 다운로드 역할을 하는 바이트 기반 출력 스트림 객체 생성 
		 * 2. 바이트 배열 객체 단위로 다운로드할 파일 정볼르 읽어서 응답에 출력하여 다운로드 처리 */
		ServletOutputStream sout = resp.getOutputStream();
		int numRead;
	
		// 처음부터 끝까지 읽는다(더 이상 읽을 게 없다 : -1)
		while((numRead = in.read(b,0,b.length)) != -1) {
			sout.write(b,0,numRead);
		}
		
		sout.flush();
		sout.close();
		in.close();
		
		System.out.println(" FileDownload.A : execute() 종료");
		return null;
	}

}
