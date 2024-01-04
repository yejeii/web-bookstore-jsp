package vo;

/** 등록한 도서의 서브 분류 VO */
public class BookSubCatgy {

	private int bsc_id;			// PK
	private int bsc_b_id;		// 해당 도서 아이디
	private int bsc_bc_code;	// 해당 도서의 서브 카테고리 코드
	
	public BookSubCatgy() {
		super();
	}

	public BookSubCatgy(int bsc_bc_code) {
		this.bsc_bc_code = bsc_bc_code;
	}
	
	private int getBsc_id() {
		return bsc_id;
	}
	private void setBsc_id(int bsc_id) {
		this.bsc_id = bsc_id;
	}
	private int getBsc_b_id() {
		return bsc_b_id;
	}
	private void setBsc_b_id(int bsc_b_id) {
		this.bsc_b_id = bsc_b_id;
	}
	private int getBsc_bc_code() {
		return bsc_bc_code;
	}
	private void setBsc_bc_code(int bsc_bc_code) {
		this.bsc_bc_code = bsc_bc_code;
	}
	
	@Override
	public String toString() {
		return "BookSubCatgy [bsc_id=" + bsc_id + ", bsc_b_id=" + bsc_b_id + ", bsc_bc_code=" + bsc_bc_code + "]";
	}
	
	
}
