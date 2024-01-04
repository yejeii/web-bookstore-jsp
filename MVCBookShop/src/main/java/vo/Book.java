package vo;

import java.util.List;

/** 책 하나의 정보를 저장하는 클래스*/
public class Book {

	private int b_id;				// 책 아이디
	private String b_name;			// 책 제목
	private String b_writer;		// 책 저자
	private String b_translator;	// 책 옮긴이
	private String b_publisher;		// 책 출판사
	private int b_bc_code; 			// 책 메인 분류
	private int b_price;			// 책 가격
	private String b_image;			// 책 대표 이미지
	private int b_page;				// 책 쪽수
	private String b_publish_date;	// 책 출판일
	private String b_content;		// 책 설명
	private int b_readcount;		// 책 조회수
	
	private int b_main_catgy;		// 책 대분류
	
	private List<BookCatgyCode> bookSubCatgyList;	// 책 서브 분류를 저장할 VO
	
	public Book(int b_id, String b_name, String b_writer, String b_translator, String b_publisher, int b_bc_code,
			int b_price, String b_image, int b_page, String b_publish_date, String b_content, int b_readcount,
			List<BookCatgyCode> bookSubCatgyList) {
		this.b_id = b_id;
		this.b_name = b_name;
		this.b_writer = b_writer;
		this.b_translator = b_translator;
		this.b_publisher = b_publisher;
		this.b_bc_code = b_bc_code;
		this.b_price = b_price;
		this.b_image = b_image;
		this.b_page = b_page;
		this.b_publish_date = b_publish_date;
		this.b_content = b_content;
		this.b_readcount = b_readcount;
		this.setBookSubCatgyList(bookSubCatgyList);
	}

	public Book(int b_id, String b_name, String b_writer, String b_translator, String b_publisher, int b_bc_code,
			int b_price, String b_image, int b_page, String b_publish_date, String b_content, int b_readcount) {
		this.b_id = b_id;
		this.b_name = b_name;
		this.b_writer = b_writer;
		this.b_translator = b_translator;
		this.b_publisher = b_publisher;
		this.b_bc_code = b_bc_code;
		this.b_price = b_price;
		this.b_image = b_image;
		this.b_page = b_page;
		this.b_publish_date = b_publish_date;
		this.b_content = b_content;
		this.b_readcount = b_readcount;
	}
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	// alt shift s + r
	public int getB_id() {
		return b_id;
	}
	
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		this.b_name = b_name;
	}

	public String getB_writer() {
		return b_writer;
	}

	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}

	public String getB_translator() {
		return b_translator;
	}

	public void setB_translator(String b_translator) {
		this.b_translator = b_translator;
	}

	public String getB_publisher() {
		return b_publisher;
	}

	public void setB_publisher(String b_publisher) {
		this.b_publisher = b_publisher;
	}

	public int getB_bc_code() {
		return b_bc_code;
	}

	public void setB_bc_code(int b_bc_code) {
		this.b_bc_code = b_bc_code;
	}

	public int getB_price() {
		return b_price;
	}

	public void setB_price(int b_price) {
		this.b_price = b_price;
	}

	public String getB_image() {
		return b_image;
	}

	public void setB_image(String b_image) {
		this.b_image = b_image;
	}

	public int getB_page() {
		return b_page;
	}

	public void setB_page(int b_page) {
		this.b_page = b_page;
	}

	public String getB_publish_date() {
		return b_publish_date;
	}

	public void setB_publish_date(String b_publish_date) {
		this.b_publish_date = b_publish_date;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public int getB_readcount() {
		return b_readcount;
	}

	public void setB_readcount(int b_readcount) {
		this.b_readcount = b_readcount;
	}
	
	public int getB_main_catgy() {
		return b_main_catgy;
	}
	
	public void setB_main_catgy(int b_main_catgy) {
		this.b_main_catgy = b_main_catgy;
	}
	
	public List<BookCatgyCode> getBookSubCatgyList() {
		return bookSubCatgyList;
	}

	public void setBookSubCatgyList(List<BookCatgyCode> bookSubCatgyList) {
		this.bookSubCatgyList = bookSubCatgyList;
	}

	// alt shift s + s
	@Override
	public String toString() {
		return "Book [b_id=" + b_id + ", \n b_name=" + b_name + ", \n b_writer=" + b_writer + ", \n b_translator=" + b_translator
				+ ", \n b_publisher=" + b_publisher + ", \n b_bc_code=" + b_bc_code + ", \n b_price=" + b_price + ", \n b_image="
				+ b_image + ", \n b_page=" + b_page + ", \n b_publish_date=" + b_publish_date + ", \n b_content=" + b_content
				+ ", \n b_readcount=" + b_readcount + ", \n b_main_catgy=" + b_main_catgy + ", \n bookSubCatgyList="
				+ bookSubCatgyList + "]";
	}

	

} 
