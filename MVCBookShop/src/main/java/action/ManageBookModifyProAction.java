package action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ManageBookModifyProService;
import vo.ActionForward;
import vo.Book;
import vo.BookCatgyCode;

/** 책 정보 수정 요청을 처리하는 Action 클래스 */
public class ManageBookModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.BookModifyPro.A : execute() 호출");
		
		// 파일이 업로드될 서버 상의 실제 경로 저장
		String realFolder = "";
		// 파일을 업로드할 디렉토리명을 지정
		String saveFolder = "/bookImage";
		// 한번에 업로드할 수 있는 파일 크기(5Mbyte)
		int fileSize = 5*1024*1024;
		
		/* 파일이 업로드될 서버 상의 물리적인 경로 얻어오기 */
		ServletContext context = req.getServletContext();
		realFolder = context.getRealPath(saveFolder);	// 파라미터로 지정한 디렉토리의 서버 상의 실제 경로를 get
		System.out.println(" M.BookModifyPro.A : 서버 상에서 업로드될 물리적인 경로 - " + realFolder );

		/* 웹 브라우저에서 전송된 파라미터 데이터를 새로 등록할 책 객체에 저장 처리
		 * MultipartRequest 클래스 생성자를 사용하여 객체를 생성하는 순간 바로 서버 상에 파일이 업로드 된다.*/
		MultipartRequest multi = 
				new MultipartRequest(req, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		/* 카테고리 데이터 저장(서브 분류 데이터만 저장할 것)
		 * [0]의 값은 메인 분류 데이터 값이다! 
		 * 1. String형 배열을 int형 배열로 전환
		 * 2. 배열의 값들을 순회하며 처리  */
        String[] catgy_arr = multi.getParameterValues("b_sub_catgy");
        int[] sub_catgy_arr = new int[catgy_arr.length-1];	// 서브 분류 데이터만 넣을 새로운 배열 생성
        
        for (int i = 0; i < sub_catgy_arr.length; i++) {
        	sub_catgy_arr[i] = Integer.parseInt(catgy_arr[i+1]);	// catgy_arr[1]부터 저장되도록 처리
        	System.out.print(sub_catgy_arr[i] + " "); // 각 값 처리
        }
        
		Book book = new Book();
		book.setB_id(Integer.parseInt(multi.getParameter("b_id")));
		book.setB_name(multi.getParameter("b_name"));
		book.setB_writer(multi.getParameter("b_writer"));
		book.setB_translator(multi.getParameter("b_translator"));
		book.setB_publisher(multi.getParameter("b_publisher"));
		book.setB_bc_code(Integer.parseInt(catgy_arr[0]));
		book.setB_price(Integer.parseInt(multi.getParameter("b_price")));
		book.setB_image(multi.getFilesystemName("b_image"));
		book.setB_page(Integer.parseInt(multi.getParameter("b_page")));
		book.setB_publish_date(multi.getParameter("b_publish_date"));
		book.setB_content(multi.getParameter("b_content"));		
		
		System.out.println(" M.BookModifyPro.A : 새로 저장될 책 정보 - "+book.toString());
		
		/* DB 처리
		 * 1. 이미지 처리를 위한 이미지 정보를 가져오는 메서드 호출
		 *    1. 사진이 변경되지 않은 경우 DB에 저장되어 있는 파일명을 book 객체의 b_image값으로 세팅
		 * 2. 변경할 도서 정보 저장하는 메서드 호출
		 */
		ManageBookModifyProService service = new ManageBookModifyProService();
		String oldFileName = service.getBookImage(book.getB_id());
		boolean isSameFile = oldFileName.equals(book.getB_image());	// 이전 파일명과 변경 파일명이 동일한지 비교
		
        if(book.getB_image() == null) {
        	// 사진이 변경되지 않은 경우, multi.getFilesystemName("b_image")의 값은 null이다.
        	book.setB_image(oldFileName);
        } 

        // 2. 
		boolean isModifySuccess = service.modifyBook(book, sub_catgy_arr);
		
		/* DB 처리 결과에 따른 페이지 이동 처리 
		 * 성공시 /bookList.ok로 이동 
		 * .ok -> .ok : redirect */
		ActionForward forward = null;
		
		if(!isModifySuccess) {
			// isRegistSuccess가 false일 때(도서 수정 실패)
			
			// 이전 파일명과 새로운 파일명이 다른 경우 서버에 새로운 파일명 삭제 처리
			if(!isSameFile) {
				deleteFile(realFolder, book.getB_image());
			}
			
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('수정 실패')");
			out.println("history.back(-1)");			
			out.println("</script>");
		} else {
			// 도서 수정 성공 
			
			// 이전 파일명과 새로운 파일명이 다른 경우 서버에 저장된 이전 파일명 삭제 처리
			if(!isSameFile) {
				deleteFile(realFolder, oldFileName);
			}
        	
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/manager/bookManage/bookView.ma?b_id="+book.getB_id());
		}
		
		System.out.println(" M.BookModifyPro.A : execute() 종료");
		return forward;
	}

	private void deleteFile(String path, String fileName) {
		File deleteFile = new File(path+"/"+fileName);
		if(deleteFile.exists()) {    
			deleteFile.delete();
    		System.out.println(" M.BookModifyPro.A : 파일 삭제 완료");
    	}
	}
}
