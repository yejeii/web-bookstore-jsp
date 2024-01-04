package vo;

/** 도서 분류 VO */
public class BookCatgyCode {

	private int code;			// 해당 카테고리 코드(소분류 코드)
	private String name;		// 해당 카테고리 코드명
	private int code_ref_md;	// 해당 카테고리 중분류 코드
	private int code_ref_mn;	// 해당 카테고리 대분류 코드
	
	private String code_ref_md_name; // 해당 카테고리 중분류 코드명
	private String code_ref_mn_name; // 해당 카테고리 대분류 코드명
	
	
	public BookCatgyCode() {
		super();
	}
	
	public BookCatgyCode(int code, String name, int code_ref_md, int code_ref_mn, String code_ref_md_name,
			String code_ref_mn_name) {
		super();
		this.code = code;
		this.name = name;
		this.code_ref_md = code_ref_md;
		this.code_ref_mn = code_ref_mn;
		this.code_ref_md_name = code_ref_md_name;
		this.code_ref_mn_name = code_ref_mn_name;
	}
	
	// alt shift s r
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCode_ref_md() {
		return code_ref_md;
	}
	public void setCode_ref_md(int code_ref_md) {
		this.code_ref_md = code_ref_md;
	}
	public int getCode_ref_mn() {
		return code_ref_mn;
	}
	public void setCode_ref_mn(int code_ref_mn) {
		this.code_ref_mn = code_ref_mn;
	}
	public String getCode_ref_md_name() {
		return code_ref_md_name;
	}
	public void setCode_ref_md_name(String code_ref_md_name) {
		this.code_ref_md_name = code_ref_md_name;
	}
	public String getCode_ref_mn_name() {
		return code_ref_mn_name;
	}
	public void setCode_ref_mn_name(String code_ref_mn_name) {
		this.code_ref_mn_name = code_ref_mn_name;
	}

	// alt shift s s
	@Override
	public String toString() {
		return "BookCatgyCode [code=" + code + ", \n name=" + name + ", \n code_ref_md=" + code_ref_md + ", \n code_ref_mn="
				+ code_ref_mn + ", \n code_ref_md_name=" + code_ref_md_name + ", \n code_ref_mn_name=" + code_ref_mn_name
				+ "]";
	}
	
}
