package util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.ValueRange;
import java.time.temporal.WeekFields;
import java.util.ArrayList;

/** 특정 연도와 월, 주의 월요일과 일요일을 구하는 메서드 */
public class GetDateByWeekUtil {

	private TemporalField isoField = WeekFields.ISO.weekOfMonth();
	private int year;
	private int month;
	private LocalDate date_1; 	// year년 month월의 1일 날짜를 저장할 객체
	private int weekOfDate_1;	// 1일의 주차를 저장
	private int week;			// isoField에 따라 0~5의 범위를 가짐
    
    /** 인스턴스가 생성되는 시점의 날짜로 세팅 */
    public GetDateByWeekUtil(int year, int month, int week) {
		this.year = year;
		this.month = month;
		this.date_1 = LocalDate.of(this.year, this.month, 1);
		this.weekOfDate_1 = this.date_1.get(isoField);
		this.week = checkWeek(week);
	}
    
    /** 사용자가 원하는 날짜로 세팅 */
	/*
	 * public void setYearMonthWeek(int year, int month, int week) { this.year =
	 * year; this.month = month; this.date_1 = LocalDate.of(this.year, this.month,
	 * 1); this.weekOfDate_1 = this.date_1.get(isoField); this.week =
	 * checkWeek(week); }
	 */

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getWeek() {
		return week;
	}

	public LocalDate getDate_1() {
		return date_1;
	}

	public int getWeekOfDate_1() {
		return weekOfDate_1;
	}
	
	/** week의 유효성 체크 */
	public int checkWeek(int week) {
	    System.out.println(" GetDateByWeekUtil.checkWeek() 호출");
	    
	    // 주 최소 최대 구하기
	    ValueRange range = date_1.range(isoField);
	    long min = range.getMinimum();
	    long max = range.getMaximum();
	
	    System.out.println("  "+month + "월의 최소 주 : " + min);
	    System.out.println("  "+month + "월의 최대 주 : " + max);
	    System.out.println("  "+month + "월 1일이 속한 주 : " +weekOfDate_1);
	    
	    if(!range.isValidValue(week)) {
	    	System.err.println("  주의 범위가 벗어납니다.");
	    	return (int)max;
	    }
	    
	    System.out.println(" GetDateByWeekUtil.checkWeek() 종료");
	    return week;	// 조정 안한 경우 그대로 리턴
    }
  
	/* 특정 주의 첫 번째 날짜 및 마지막 날짜 구하기 */
	public ArrayList<LocalDate> calMondaySunday() {
		System.out.println(" GetDateByWeekUtil.calMondaySunday() 호출");
		
		ArrayList<LocalDate> dateList = new ArrayList<>();
		LocalDate monday = null;
		LocalDate sunday = null;
		    
		if(weekOfDate_1 == 1) {
			// 1일이 있는 주가 첫 주인경우
			monday = date_1.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)).plusWeeks(week-1);
		}
		else monday = date_1.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).plusWeeks(week-1);
		
		// 특정 주의 마지막 날짜를 계산합니다.
		sunday = monday.plusDays(6);
		
		dateList.add(monday);
		dateList.add(sunday);
		
		System.out.println("  "+week +"주의 월요일 : " + monday);
		System.out.println("  "+week +"주의 일요일 : " + sunday);
		System.out.println(" GetDateByWeekUtil.calMondaySunday() 종료");
		
		return dateList;
	}

	@Override
	public String toString() {
		return "  GetDateByWeekUtil [isoField=" + isoField + ", year=" + year + ", month=" + month + ", date_1=" + date_1
				+ ", weekOfDate_1=" + weekOfDate_1 + ", week=" + week + "]";
	}
}
