package vo;

/** 포워딩 정보를 저장하는 클래스.
 * 1) 페이지 이동정보(주소) 
 * 2) 페이지 이동방식
 * 컨트롤러 역할을 하는 서블릿에서 클라의 각 요청을 받아서 처리한 후 최종적으로 뷰 페이지로 포워딩 처리 시
 * 이동할 뷰 페이지의 url와 포워딩 방식(디스패치나 리다이렉트)이 필요.
 */
public class ActionForward {

	
	private String path;		// 1)
	private boolean redirect; 	// 2) 
	// true - Redirect방식
	// false - RequestDispatcher방식
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean isRedirect() {
		return redirect;
	}
	
	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
}
