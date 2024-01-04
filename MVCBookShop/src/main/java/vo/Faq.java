package vo;

import java.util.Date;

public class Faq {

	private int f_id; 
	private int f_fc_code;
	private int f_fk_code;
	private String f_title;
	private String f_text;
	private int f_use;
	private Date f_regdate;
	private int f_readcnt;
	
	// faqcode, faqkeyword 테이블 칼럼을 가져오기 위한 설정(외부 칼럼)
	private String fc_value;
	private String fk_value;
	
	public Faq() {
		super();
	}
	
	
	public Faq(int f_id, int f_fk_code, String f_title, String f_text, String fk_value) {
		super();
		this.f_id = f_id;
		this.f_fk_code = f_fk_code;
		this.f_title = f_title;
		this.f_text = f_text;
		this.fk_value = fk_value;
	}

	// alt shift s r
	public int getF_id() {
		return f_id;
	}
	public void setF_id(int f_id) {
		this.f_id = f_id;
	}
	public int getF_fk_code() {
		return f_fk_code;
	}
	public void setF_fk_code(int f_fk_code) {
		this.f_fk_code = f_fk_code;
	}
	public int getF_fc_code() {
		return f_fc_code;
	}
	public void setF_fc_code(int f_fc_code) {
		this.f_fc_code = f_fc_code;
	}
	public String getF_title() {
		return f_title;
	}
	public void setF_title(String f_title) {
		this.f_title = f_title;
	}
	public String getF_text() {
		return f_text;
	}
	public void setF_text(String f_text) {
		this.f_text = f_text;
	}
	public int getF_use() {
		return f_use;
	}
	public void setF_use(int f_use) {
		this.f_use = f_use;
	}
	public Date getF_regdate() {
		return f_regdate;
	}
	public void setF_regdate(Date f_regdate) {
		this.f_regdate = f_regdate;
	}

	public String getFc_value() {
		return fc_value;
	}
	public void setFc_value(String fc_value) {
		this.fc_value = fc_value;
	}
	public String getFk_value() {
		return fk_value;
	}
	public void setFk_value(String fk_value) {
		this.fk_value = fk_value;
	}
	public int getF_readcnt() {
		return f_readcnt;
	}
	public void setF_readcnt(int f_readcnt) {
		this.f_readcnt = f_readcnt;
	}


	// alt shift s s
	@Override
	public String toString() {
		return "Faq [f_id=" + f_id + ", f_fc_code=" + f_fc_code + ", f_fk_code=" + f_fk_code + ", f_title=" + f_title
				+ ", f_text=" + f_text + ", f_use=" + f_use + ", f_regdate=" + f_regdate + ", f_readcnt=" + f_readcnt
				+ ", fc_value=" + fc_value + ", fk_value=" + fk_value + "]";
	}

	
	
	
	

	
}
