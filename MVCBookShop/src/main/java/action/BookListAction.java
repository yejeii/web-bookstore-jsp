package action;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.BookListService;
import util.GetDateByWeekUtil;
import vo.ActionForward;
import vo.Book;
import vo.Filter;
import vo.PageInfo;

/** 책 상품 목록보기 요청을 처리하는 Action 클래스*/
public class BookListAction implements Action {

	private GetDateByWeekUtil dateByWeekUtil;
	private ArrayList<String> todayImageList;	// 오늘 본 도서 이미지 배열
	private int page;							// 목록 화면의 페이지 번호(기본값 1)
	private int limit;							// 한 화면에 출력될 도서 개수(기본값 9)
	private String mnName;						// 대분류 값 
	private int mdCode;							// 중분류 값
	private String sort;						// 출력 순서(default: new)
	private int year;							// 요청한 년도(default: 현재)
	private int month;							// 요청한 달(default: 현재)
	private int week;							// 요청한 주(default: 현재 요일의 주)
	private LocalDate monday;					// 요청한 주의 월요일
	private LocalDate sunday;					// 요청한 주의 일요일
	private ArrayList<Book> bookList;			// 도서 목록
	private int listCount;						// 도서 개수
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		System.out.println(" B.List.A : execute() 호출 ");
		
		/* URL 형식 : 
		 *  http://localhost:8090/bookList.ok?page=1&mnName=KOR&mdCode=1037&year=2023&month=06&week=01&sort=new&limit=9 
		 *  전역변수 초기화 */
		todayImageList = new ArrayList<>();
		/*
		 * page = 1; limit = 9; mnName = "KOR"; mdCode = 0; sort = "new"; year =
		 * LocalDate.now().getYear(); month = LocalDate.now().getMonthValue(); week =
		 * LocalDate.now().get(WeekFields.ISO.weekOfMonth());
		 */
		
		/* URL 파라미터 설정 */		
		if(req.getQueryString() != null) {
			System.out.println("  QueryString : " + req.getQueryString());
			setParameter(req);
			dateByWeekUtil = new GetDateByWeekUtil(year, month, week);
			//dateByWeekUtil.setYearMonthWeek(year, month, week);
			
			System.out.println(dateByWeekUtil.toString());
		} else {
			System.err.println("  QueryString : 쿼리 문자열이 없습니다!! Exception");
		}
		
		// Filter 객체에 전역변수 저장(나중에 request내장 객체에 담아 보낼 것)
		Filter filter = new Filter(sort, year, month, week);
		
		/* 주어진 년, 월, 주차를 사용하여 해당 주의 월요일과 일요일 날짜를 가져오기 
		 * 1. 사용자가 지정한 지의 경우에 따라 year, month, week로 재조정 
		 * 2. 해당 주의 월요일과 일요일 날짜를 가져오기 */
		ArrayList<LocalDate> dateList = dateByWeekUtil.calMondaySunday();
		monday = dateList.get(0);
		sunday = dateList.get(1);
		System.out.println("  전역변수 상태 : "+toString());
		
		/* 도서 이미지로 쿠키 설정 */
		setImageToCookie(req);
		
		/* DB 처리 */
		Map<String, Object> dbResult = contactDB(req);
		bookList = (ArrayList<Book>) dbResult.get("bookList"); 
		listCount = (int) dbResult.get("listCount"); 
		System.out.println("  전역변수 상태 : "+toString());
		
		/* 페이징 처리 */
		PageInfo pageInfo = paging();
		
		/* 포워딩할 때 가져갈 정보 저장 
		 * 1. 책 상품 목록 정보를 속성으로 공유 
		 * 2. 오늘 본 책 상품 이미지 목록 정보를 속성으로 공유 
		 * 3. pageInfo 객체를 request 영역에 속성 값으로 공유
		 * 4. 도서 분류 값을 속성으로 공유
		 * 5. limit 값을 속성으로 공유
		 * .ok -> .jsp : Forward
		 * */
		req.setAttribute("bookList", bookList);
		req.setAttribute("todayImageList", todayImageList);
		req.setAttribute("pageInfo", pageInfo);
		req.setAttribute("mnName", mnName);
		req.setAttribute("mdCode", mdCode);
		req.setAttribute("limit", limit);
		req.setAttribute("filter", filter);
		
		String q = "page="+page+"&mnName="+mnName+"&mdCode="+mdCode+"&year="+year+"&month="+month+"&week="+week+"&sort="+sort+"&limit="+limit;
		String path = "/book/bookList.jsp?"+q;
		System.out.println("  path : "+path);
		
		ActionForward forward = new ActionForward();
		forward.setPath(path);
		forward.setRedirect(false);
		System.out.println(" B.List.A : forward 방식 : " + forward.isRedirect());
		
		System.out.println(" B.List.A : execute() 종료 ");
		return forward;
	}
	
	/** 요청 URL의 파라미터 설정하는 메서드 */
	private void setParameter(HttpServletRequest req) throws Exception {
		System.out.println(" B.List.A : setParameter() 호출 ");
		
		if(req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		if(req.getParameter("sort") != null) {
			sort = req.getParameter("sort");
		}
		
		if(req.getParameter("year") != null) {
			year = Integer.parseInt(req.getParameter("year"));
		}
		
		if(req.getParameter("month") != null) {
			month = Integer.parseInt(req.getParameter("month"));
		}
		
		if(req.getParameter("week") != null) {
			week = Integer.parseInt(req.getParameter("week"));
		}
		
		if(req.getParameter("mnName") != null) {
			mnName = req.getParameter("mnName");
		} 
		
		if(req.getParameter("mdCode") != null) {
			switch (req.getParameter("mdCode")) {
			case "":
				mdCode = 0;
				break;

			default:
				mdCode = Integer.parseInt(req.getParameter("mdCode"));
			}
		} 
		
		if(req.getParameter("limit") != null) {
			limit = Integer.parseInt(req.getParameter("limit"));
		}
		
		System.out.println(" B.List.A : setParameter() 종료 ");
	}

	/** 사용자가 본 도서 이미지를 쿠키로 설정하는 메서드 */
	private void setImageToCookie(HttpServletRequest req) throws Exception {
		System.out.println(" B.List.A : setImageToCookie() 호출 ");
		
		// 웹 브라우저에서 넘어온 Cookie 객체를 배열 형태로 반환
		Cookie[] cookieArray = req.getCookies();
		
		/* 요청에 넘어온 쿠키 객체 중 오늘 본 상품 이미지 이름을 저장하고 있는 쿠키 객체를 찾아서
		 * todayImageList ArrayList 객체에 쿠키 객체의 값, 즉 이미지 이름을 요소로 추가.
		 * 특정 상품의 자세한 내용을 볼 때 BookViewAction 클래스에서 내용을 본 상품의 이미지를
		 * today문자열 뒤에 해당 상품의 아이디를 붙인 이름으로 쿠키 객체에 저장
		 */
		if(cookieArray != null) {
			for(int i=0; i<cookieArray.length; i++) {
				if(cookieArray[i].getName().startsWith("today")) {
					todayImageList.add(cookieArray[i].getValue());
				}
			}
		}
		
		System.out.println(" B.List.A : setImageToCookie() 종료 ");
	}

	/** DB 처리하는 메서드 
	 * @throws SQLException */
	private Map<String, Object> contactDB(HttpServletRequest req) throws SQLException {
		System.out.println(" B.List.A : contactDB() 호출 ");
		
		/* 1. DB에 저장된 도서 총 개수 GET
		 * 2. 지정한 페이지에 출력될 책 목록을 GET */
		ArrayList<Book> bookList = null;
		int listCount = 0; 
		BookListService service = new BookListService();
		
		listCount = service.getListCount(monday, sunday, mnName, mdCode);	
		bookList = service.getBookList(page, monday, sunday, sort, mnName, mdCode, limit);
		
		Map<String, Object> result = new HashMap<>();
	    result.put("bookList", bookList);
	    result.put("listCount", listCount);
	    
		System.out.println(" B.List.A : contactDB() 종료 ");
		return result;
	}

	/** 페이징을 처리하는 메서드 */
	private PageInfo paging() {
		System.out.println(" B.List.A : paging() 호출 ");
		
		/* 1. 총 페이지 수(나머지가 있는 경우에만 올림처리) 계산
		 * 2. 페이징 부분에 출력되는 페이지 번호 중 첫 번째 페이지 번호(1, 11, 21 등) 계산
		 * 3. 페이징 부분에 출력되는 페이지 번호 중 마지막 페이지 번호(10, 20, 30 등) 계산 */
		int maxPage = listCount / limit + (listCount % limit == 0 ? 0 : 1);
		int startPage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		
		// 계산된 endPage 값을 존재하는 페이지의 마지막 페이지 번호(maxPage)로 지정
		if(endPage > maxPage) endPage = maxPage;
		
		System.out.println("  PageInfo.page: "+page);
		System.out.println("  PageInfo.listCount: "+listCount);
		System.out.println("  PageInfo.maxPage: "+maxPage);
		System.out.println("  PageInfo.startPage: "+startPage);
		System.out.println("  PageInfo.endPage: "+endPage);
		
		/* 페이징에 관한 정보를 저장할 PageInfo 객체 생성 */		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		
		System.out.println(" B.List.A : paging() 종료 ");
		return pageInfo;
	}

	@Override
	public String toString() {
		return "BookListAction [dateByWeekUtil=" + dateByWeekUtil + ", todayImageList=" + todayImageList + ", page="
				+ page + ", limit=" + limit + ", mnName=" + mnName + ", mdCode=" + mdCode + ", sort=" + sort + ", year="
				+ year + ", month=" + month + ", week=" + week + ", monday=" + monday + ", sunday=" + sunday
				+ ", bookList=" + bookList + ", listCount=" + listCount + "]";
	}
	
}
