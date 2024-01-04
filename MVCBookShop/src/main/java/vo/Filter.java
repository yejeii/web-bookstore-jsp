package vo;

/** 도서 목록 필터링 대상 */
public class Filter {

	private String sort;	// 출력 순서
	private int year;		// 출력 년도
	private int month;		// 출력 달
	private int week;		// 출력 주
	
	/* 필터링 Default 설정 */
	public Filter(String sort, int year, int month, int week) {
		this.sort = sort;
		this.year = year;
		this.month = month;
		this.week = week;
	}

	// alt shift s r
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	
	@Override
	public String toString() {
		return "Filter [sort=" + sort + ", year=" + year + ", month=" + month + ", week=" + week + "]";
	}
	
	
}
