package action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import svc.ManageBookRegistProService;
import vo.ActionForward;
import vo.Book;

/* 새로운 책 상품 정보를 등록하는 Action 클래스 */
public class ManageBookRegistProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" M.BookRegistPro.A : execute() 호출");
		
		// 파일이 업로드될 서버 상의 실제 경로 저장
		String realFolder = "";
		// 파일을 업로드할 디렉토리명을 지정
		String saveFolder = "/bookImage";
		// 한번에 업로드할 수 있는 파일 크기(5Mbyte)
		int fileSize = 5*1024*1024;
		
		/* 파일이 업로드될 서버 상의 물리적인 경로 얻어오기 */
		ServletContext context = req.getServletContext();
		realFolder = context.getRealPath(saveFolder);	// 파라미터로 지정한 디렉토리의 서버 상의 실제 경로를 get
		System.out.println(" M.BookRegistPro.A : 서버 상에서 업로드될 물리적인 경로 - " + realFolder );
	
		/* 폴더 생성 처리 */
		File upDirFile = new File(realFolder);
		if(!upDirFile.exists()) {
			upDirFile.mkdir();
		}
		
		/* 웹 브라우저에서 전송된 파라미터 데이터를 새로 등록할 책 객체에 저장 처리
		 * MultipartRequest 클래스 생성자를 사용하여 객체를 생성하는 순간 바로 서버 상에 파일이 업로드 된다.*/
		MultipartRequest multi = 
				new MultipartRequest(req, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		
		/* 카테고리 데이터 저장(서브 분류 데이터만 저장할 것)
		 * [0]의 값은 메인 분류 데이터 값이다! 
		 * 1. String형 배열을 int형 배열로 전환 - int형 배열, 서브 분류가 있을때에만 그만큼의 길이를 가지도록 한다!
		 * 2. 배열의 값들을 순회하며 처리  */
        String[] catgy_arr = multi.getParameterValues("b_sub_catgy");
        int[] sub_catgy_arr = null;
        
        System.out.println("catgy_arr.length: "+catgy_arr.length);
        if(catgy_arr.length > 1) sub_catgy_arr = new int[catgy_arr.length-1];
        
        // 배열의 값들을 순회하며 처리
        if(sub_catgy_arr != null) {
	        for (int i = 0; i < sub_catgy_arr.length; i++) {
	        	sub_catgy_arr[i] = Integer.parseInt(catgy_arr[i+1]);
	        	System.out.print(sub_catgy_arr[i] + " "); // 각 값 처리
	        }
        }
		
		Book book = new Book();
		book.setB_name(multi.getParameter("b_name"));
		book.setB_writer(multi.getParameter("b_writer"));
		book.setB_translator(multi.getParameter("b_translator"));
		book.setB_publisher(multi.getParameter("b_publisher"));
		book.setB_main_catgy(Integer.parseInt(multi.getParameter("b_main_catgy")));
		book.setB_bc_code(Integer.parseInt(catgy_arr[0]));
		book.setB_price(Integer.parseInt(multi.getParameter("b_price")));
		book.setB_image(multi.getFilesystemName("b_image"));
		book.setB_page(Integer.parseInt(multi.getParameter("b_page")));
		book.setB_publish_date(multi.getParameter("b_publish_date"));
		book.setB_content(multi.getParameter("b_content"));
		
		System.out.println(" M.BookRegistPro.A : 새로 저장될 책 정보 - "+book.toString());
		
		/* DB 처리
		 * 1. 도서 상품 등록 
		 * 2. 도서 서브 카테고리 등록 */
		ManageBookRegistProService service = new ManageBookRegistProService();
		boolean isRegistSuccess = service.insertBook(book, sub_catgy_arr);
		
		/** DB 처리 결과에 따라 forward를 처리하는 메서드 
		 * 성공시 /manager/bookManage/bookList.ma로 이동 
		 * .ma -> .ma : redirect */
		ActionForward forward = null;
		
		if(!isRegistSuccess) {
			// isRegistSuccess가 false일 때(DB에 도서 및 도서서브분류 저장 실패)
			
			// 서버에 저장한 이미지 파일 삭제
			File deleteFile = new File(realFolder, book.getB_image());
			if(deleteFile.exists()) {
				deleteFile.delete();
	    		System.out.println(" M.BookRegistPro.A : 파일 삭제 완료");
			}
			
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('등록되지 못했습니다.')");
			out.println("history.back(-1)");			
			out.println("</script>");
		} else {
		
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/manager/bookManage/bookList.ma");
		}

		System.out.println(" M.BookRegistPro.A : execute() 종료");
		return forward;
	}

}
