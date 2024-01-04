package vo;

public class Member {

	private String m_id;
	private String m_pw;
	private String m_name;
	private int m_age;
	private String m_gender;
	private String m_email;
	private String m_phone1;
	private String m_phone2;
	private String m_postcode;
	private String m_addr1;
	private String m_addr2;
	private String m_addr3;
	
	// 생성자
	public Member() {
		super();
	}

	public Member(String m_id, String m_pw, String m_name, int m_age, String m_gender, String m_email, String m_phone1,
			String m_phone2, String m_postcode, String m_addr1, String m_addr2, String m_addr3) {
		super();
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_name = m_name;
		this.m_age = m_age;
		this.m_gender = m_gender;
		this.m_email = m_email;
		this.m_phone1 = m_phone1;
		this.m_phone2 = m_phone2;
		this.m_postcode = m_postcode;
		this.m_addr1 = m_addr1;
		this.m_addr2 = m_addr2;
		this.m_addr3 = m_addr3;
	}
	
	// alt shift s + r
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public int getM_age() {
		return m_age;
	}
	public void setM_age(int m_age) {
		this.m_age = m_age;
	}
	public String getM_gender() {
		return m_gender;
	}
	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_phone1() {
		return m_phone1;
	}
	public void setM_phone1(String m_phone1) {
		this.m_phone1 = m_phone1;
	}
	public String getM_phone2() {
		return m_phone2;
	}
	public void setM_phone2(String m_phone2) {
		this.m_phone2 = m_phone2;
	}
	public String getM_postcode() {
		return m_postcode;
	}
	public void setM_postcode(String m_postcode) {
		this.m_postcode = m_postcode;
	}
	public String getM_addr1() {
		return m_addr1;
	}
	public void setM_addr1(String m_addr1) {
		this.m_addr1 = m_addr1;
	}
	public String getM_addr2() {
		return m_addr2;
	}
	public void setM_addr2(String m_addr2) {
		this.m_addr2 = m_addr2;
	}
	public String getM_addr3() {
		return m_addr3;
	}
	public void setM_addr3(String m_addr3) {
		this.m_addr3 = m_addr3;
	}

	// alt shift s + s
	@Override
	public String toString() {
		return "Member [m_id=" + m_id + ", m_pw=" + m_pw + ", m_name=" + m_name + ", m_age=" + m_age + ", m_gender="
				+ m_gender + ", m_email=" + m_email + ", m_phone1=" + m_phone1 + ", m_phone2=" + m_phone2
				+ ", m_postcode=" + m_postcode + ", m_addr1=" + m_addr1 + ", m_addr2=" + m_addr2 + ", m_addr3="
				+ m_addr3 + "]";
	}
	
	
	
}
