package vo;

import java.util.Date;

/** 코멘트 하나를 저장하는 클래스 */
public class Comment {

	private int c_id;
	private int c_b_id;
	private String c_m_id;
	private String c_title;
	private String c_text;
	private Date c_regdate;
	private int c_empathy;
	private int c_ref;
	private int c_lev;
	private int c_seq;
	
	// alf shift s r
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getC_b_id() {
		return c_b_id;
	}
	public void setC_b_id(int c_b_id) {
		this.c_b_id = c_b_id;
	}
	public String getC_m_id() {
		return c_m_id;
	}
	public void setC_m_id(String c_m_id) {
		this.c_m_id = c_m_id;
	}
	public String getC_title() {
		return c_title;
	}
	public void setC_title(String c_title) {
		this.c_title = c_title;
	}
	public String getC_text() {
		return c_text;
	}
	public void setC_text(String c_text) {
		this.c_text = c_text;
	}
	public Date getC_regdate() {
		return c_regdate;
	}
	public void setC_regdate(Date c_regdate) {
		this.c_regdate = c_regdate;
	}
	public int getC_empathy() {
		return c_empathy;
	}
	public void setC_empathy(int c_empathy) {
		this.c_empathy = c_empathy;
	}
	public int getC_ref() {
		return c_ref;
	}
	public void setC_ref(int c_ref) {
		this.c_ref = c_ref;
	}
	public int getC_lev() {
		return c_lev;
	}
	public void setC_lev(int c_lev) {
		this.c_lev = c_lev;
	}
	public int getC_seq() {
		return c_seq;
	}
	public void setC_seq(int c_seq) {
		this.c_seq = c_seq;
	}

	// alt shift s s
	@Override
	public String toString() {
		return "Comment [c_id=" + c_id + ", c_b_id=" + c_b_id + ", c_m_id=" + c_m_id + ", c_title=" + c_title
				+ ", c_text=" + c_text + ", c_regdate=" + c_regdate + ", c_empathy=" + c_empathy + ", c_ref=" + c_ref
				+ ", c_lev=" + c_lev + ", c_seq=" + c_seq + "]";
	}

	
	
	
}
