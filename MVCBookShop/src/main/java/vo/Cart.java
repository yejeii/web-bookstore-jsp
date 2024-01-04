package vo;

/** 장바구니 도서 하나의 정보를 저장하는 클래스*/
public class Cart {
	
	private int c_id;			// 장바구니 아이디
	private int c_b_id;			// 책 아이디
	private String c_b_name;	// 책 제목
	private String c_b_image;	// 책 대표 이미지
	private int c_b_sub_catgy;	// 책 소분류
	private int c_b_price;		// 책 가격
	private int c_b_qty;		// 책 수량
	
	private String c_m_id;		// 회원 아이디 
	
	public Cart(int c_id, int c_b_id, String c_b_name, String c_b_image, int c_b_sub_catgy, int c_b_price, int c_b_qty,
			String c_m_id) {
		super();
		this.c_id = c_id;
		this.c_b_id = c_b_id;
		this.c_b_name = c_b_name;
		this.c_b_image = c_b_image;
		this.c_b_sub_catgy = c_b_sub_catgy;
		this.c_b_price = c_b_price;
		this.c_b_qty = c_b_qty;
		this.c_m_id = c_m_id;
	}

	public Cart() {
		// TODO Auto-generated constructor stub
	}

	// alt shift s + r
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

	public String getC_b_name() {
		return c_b_name;
	}

	public void setC_b_name(String c_b_name) {
		this.c_b_name = c_b_name;
	}

	public String getC_b_image() {
		return c_b_image;
	}

	public void setC_b_image(String c_b_image) {
		this.c_b_image = c_b_image;
	}

	public int getC_b_sub_catgy() {
		return c_b_sub_catgy;
	}

	public void setC_b_sub_catgy(int c_b_sub_catgy) {
		this.c_b_sub_catgy = c_b_sub_catgy;
	}

	public int getC_b_price() {
		return c_b_price;
	}

	public void setC_b_price(int c_b_price) {
		this.c_b_price = c_b_price;
	}

	public int getC_b_qty() {
		return c_b_qty;
	}

	public void setC_b_qty(int c_b_qty) {
		this.c_b_qty = c_b_qty;
	}

	public String getC_m_id() {
		return c_m_id;
	}

	public void setC_m_id(String c_m_id) {
		this.c_m_id = c_m_id;
	}

	// alt shift s + s
	@Override
	public String toString() {
		return "Cart [c_id=" + c_id + ", c_b_id=" + c_b_id + ", c_b_name=" + c_b_name + ", c_b_image=" + c_b_image
				+ ", c_b_sub_catgy=" + c_b_sub_catgy + ", c_b_price=" + c_b_price + ", c_b_qty=" + c_b_qty + ", c_m_id="
				+ c_m_id + "]";
	}

}
