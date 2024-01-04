package vo;


/** 페이징 처리 관련 정보를 저장하는 클래스 */
public class PageInfo {

	private int page;		// 해당 목록 화면의 페이지 번호
	private int maxPage;	// 도서 개수와 한 화면에 보여질 도서 개수(Limit)에 따라 필요한 총 페이지 수
	private int startPage;	// 한 섹션의 첫 번째 페이지 번호(한 섹션 : 1-10, 11-20 ...)
	private int endPage;	// 한 섹션의 마지막 페이지 번호(10,20,30 ...)
	private int listCount;	// DB에 저장된 도서 총 개수

	// alt+shift+s+r
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	
	// alt shift s + s
	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", listCount=" + listCount + "]";
	}
	
	
}
