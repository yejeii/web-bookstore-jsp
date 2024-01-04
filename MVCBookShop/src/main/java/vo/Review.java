package vo;

import java.util.Date;

/** 리뷰 하나를 저장하는 클래스*/
public class Review {

	private int r_id;			// 리뷰 아이디
	private int r_b_id;			// 리뷰를 단 책 아이디
	private String r_m_id;		// 리뷰를 쓴 회원 아이디
	private String r_text;		// 리뷰 내용
	private int r_buy_opt;		// 책 구매 여부
	private int r_star;			// 리뷰 별점		
	private Date r_regdate;		// 리뷰 작성일
	
	public Review() {
		// TODO Auto-generated constructor stub
	}
	
	// alt shift s r
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public int getR_b_id() {
		return r_b_id;
	}
	public void setR_b_id(int r_b_id) {
		this.r_b_id = r_b_id;
	}
	public String getR_m_id() {
		return r_m_id;
	}
	public void setR_m_id(String r_m_id) {
		this.r_m_id = r_m_id;
	}
	public String getR_text() {
		return r_text;
	}
	public void setR_text(String r_text) {
		this.r_text = r_text;
	}
	public int getR_buy_opt() {
		return r_buy_opt;
	}
	public void setR_buy_opt(int r_buy_opt) {
		this.r_buy_opt = r_buy_opt;
	}
	public int getR_star() {
		return r_star;
	}
	public void setR_star(int r_star) {
		this.r_star = r_star;
	}
	public Date getR_regdate() {
		return r_regdate;
	}
	public void setR_regdate(Date r_regdate) {
		this.r_regdate = r_regdate;
	}

	// alt shift s s
	@Override
	public String toString() {
		return "Review [r_id=" + r_id + ", r_b_id=" + r_b_id + ", r_m_id=" + r_m_id + ", r_text=" + r_text
				+ ", r_buy_opt=" + r_buy_opt + ", r_star=" + r_star + ", r_regdate=" + r_regdate + "]";
	}

	
	
}
