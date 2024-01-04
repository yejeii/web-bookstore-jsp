package vo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Mail {

	private int ml_index;					// PK
	private String[] ml_recipient;			// 수신자
	private String ml_sender;				// 발신자 
	private List<String> ml_attachment; 	// 첨부파일
	private String ml_title;				// 제목
	private String ml_text;					// 내용
	private int ml_read;					// 열람여부(0: 미열람)
	private int ml_importance;				// 중요도(default 0 : 중요X)
	private Date ml_sendTime;				// 수신시간(데이터베이스의 "TIMESTAMP" 컬럼 유형을 사용할 예정)
	
	public Mail() {
		super();
	}
	
	public Mail(int ml_index, String[] ml_recipient, String ml_sender, String ml_attachment, String ml_title,
			String ml_text, int ml_read, int ml_importance, Date ml_sendTime) {
		if(ml_attachment != null) this.ml_attachment = Arrays.asList(ml_attachment.split(","));
		
		this.ml_index = ml_index;
		this.ml_recipient = ml_recipient;
		this.ml_sender = ml_sender;
		this.ml_title = ml_title;
		this.ml_text = ml_text;
		this.ml_read = ml_read;
		this.ml_importance = ml_importance;
		this.ml_sendTime = ml_sendTime;
	}
	
	public int getMl_index() {
		return ml_index;
	}
	public void setMl_index(int ml_index) {
		this.ml_index = ml_index;
	}
	public String[] getMl_recipient() {
		return ml_recipient;
	}
	public void setMl_recipient(String[] ml_recipient) {
		this.ml_recipient = ml_recipient;
	}
	public String getMl_sender() {
		return ml_sender;
	}
	public void setMl_sender(String ml_sender) {
		this.ml_sender = ml_sender;
	}
	public List<String> getMl_attachment() {
		return ml_attachment;
	}
	public void setMl_attachment(List<String> ml_attachment) {
		this.ml_attachment = ml_attachment;
	}
	public String getMl_title() {
		return ml_title;
	}
	public void setMl_title(String ml_title) {
		this.ml_title = ml_title;
	}
	public String getMl_text() {
		return ml_text;
	}
	public void setMl_text(String ml_text) {
		this.ml_text = ml_text;
	}
	public int getMl_read() {
		return ml_read;
	}
	public void setMl_read(int ml_read) {
		this.ml_read = ml_read;
	}
	public int getMl_importance() {
		return ml_importance;
	}
	public void setMl_importance(int ml_importance) {
		this.ml_importance = ml_importance;
	}
	public Date getMl_sendTime() {
		return ml_sendTime;
	}
	public void setMl_sendTime(Date ml_sendTime) {
		this.ml_sendTime = ml_sendTime;
	}

	/** parse the mail string to Mail type */
	
	public Mail convertToMail(String mailStr) {
  
	  Mail mail = new Mail();
	  String[] spilted = mailStr.split("ml_"); 

	  for(String colstr: spilted) {
		
		/* 뒷 공백 및 ',' 제거 */
		colstr = colstr.trim().substring(0, colstr.trim().length()-1);
		System.out.println(colstr);
		
		/* 각 컬럼을 전역변수에 저장 */
		
		// 수신자
		// 수신자는 String[] 타입으로 저장되므로 수신자가 없어도 []로 저장된다
		// 수신자 형식 : [] 
		if(colstr.startsWith("recipient=")) {
			if(colstr.length() > 14) {	// 데이터 존재	
				colstr = colstr.substring(colstr.indexOf("[")+1, colstr.indexOf("]"));	
				
				System.out.println(" 수신자 명단 : "+colstr);
				mail.setMl_recipient(colstr.split(", "));
			}
		}
		
		// 첨부파일
		// 첨부파일은 List 타입으로 저장되므로 만약 첨부파일이 없다면 []이 저장된다. 
		else if(colstr.startsWith("attachment=")) {
			if(colstr.length() > 13) {
				colstr = colstr.substring(colstr.indexOf("[")+1, colstr.indexOf("]"));
				mail.setMl_attachment(Arrays.asList(colstr.split(", ")));
			}
		}
		
		// 제목 
		else if(colstr.startsWith("title=") && (colstr.length() > 6)) {
			mail.setMl_title(colstr.substring(colstr.indexOf("=")+1));
		}
		
		// 내용
		else if(colstr.startsWith("text=") && (colstr.length() > 5)) {
			mail.setMl_text(colstr.substring(colstr.indexOf("=")+1));
		}
		
		// 중요도
		mail.setMl_importance(0);
		
		// 저장시간
		mail.setMl_sendTime(new Date());
		
	  }

	  System.out.println(" convertToMail 결과: "+mail.toString());
	  return mail; 
	}
	
	// alt shift s s
	@Override
	public String toString() {
		return "Mail [ml_index=" + ml_index + ", ml_recipient=" + Arrays.toString(ml_recipient) + ", ml_sender="
				+ ml_sender + ", ml_attachment=" + ml_attachment + ", ml_title=" + ml_title + ", ml_text=" + ml_text
				+ ", ml_read=" + ml_read + ", ml_importance=" + ml_importance + ", ml_sendTime=" + ml_sendTime + "]";
	}				
	
}
