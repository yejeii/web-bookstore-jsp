package util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import jakarta.activation.CommandMap;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.activation.MailcapCommandMap;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.internet.MimeUtility;
import vo.Mail;

public final class NaverMailSend {

	private final String host = "smtp.naver.com";			// SMTP 서버명
	private final String port = "465";						// SMTP 포트
	private final String user = "hyoyongge2@naver.com"; 	// 발신자의 이메일 계정
	private final String password = "WKGBJYLCR3BV";         // 발신자의 SMTP 패스워드(2단계 인증으로 생성한 비밀번호)
	
	
	public String getUser() {
		return user;
	}

	/** sendEmail(String recipient_to) */
	public String sendEmail(String to) throws Exception {
		System.out.println(" NaverMailSend : sendEmail() 호출");

		String authenCode = null;
		
		/* Property 객체에 SMTP 서버 정보 설정
		 * Properties : commonly used to store application settings or parameters 
		 * 				that can be changed without modifying the code.
		 * In the context of email sending using javax.mail.Message, 
		 * Properties is used to configure the email session properties 
		 * such as the SMTP server host, port, and authentication settings.*/
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
		/* SMTP 서버 정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스를 생성*/
		Session session = Session.getDefaultInstance(props, new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(user, password);
		    }
		});
		
		/* Message 객체에 수신자와 내용, 제목의 메시지를 작성 */
		try {
			// 인증번호 생성
			authenCode = makeAuthenticationCode();
			
		    Message message = new MimeMessage(session);
		    
		    // 발신자 설정
		    message.setFrom(new InternetAddress(user, "MVC Book SHOP"));

		    // 수신자 메일주소 설정
		    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		    // 메일제목 설정
		    message.setSubject("MVCBookSHOP :: 임시 비밀번호 메일입니다.");

		    // 메일 내용 설정
		    message.setText("비밀번호 변경 인증번호는 [ "+authenCode+ " ] 입니다.");

		    // Send the message
		    Transport.send(message);

		    System.out.println(" NaverMailSend : Email sent successfully.");
		} catch (MessagingException e) {
		    e.printStackTrace();
		}
		
		System.out.println(" NaverMailSend : sendEmail() 종료");
		return authenCode;
	}
	
	/** 이메일 전송(feat. attachment) */
	public boolean sendEmail(Mail mail, String filePath) throws Exception {
	
		/* 순서
		 * 1. Get a Session
		 * 2. Create a default MimeMessage object and set From, To, Subject in the message.
		 * 3. Set the actual message as below: 
		 * 		messageBodyPart.setText("This is message body");
		 * 4. Create a MimeMultipart object. Add the above messageBodyPart with actual message set in it, to this multipart object.
		 * 5. Next add the attachment by creating a Datahandler as follows:
		 * 		messageBodyPart = new MimeBodyPart();
		 * 		String filename = "/home/manisha/file.txt";
		 * 		DataSource source = new FileDataSource(filename);
		 * 		messageBodyPart.setDataHandler(new DataHandler(source));
		 * 		messageBodyPart.setFileName(filename);
		 * 		multipart.addBodyPart(messageBodyPart);
		 * 6. Next set the multipart in the message as follows:
		 * 		message.setContent(multipart);
		 * 7. Send the message using the Transport object.
		 * */
		
		boolean sendSuccess = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");
		String sysdate = formatter.format(new Date());
		
		// Set SMTP properties
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
	    //MailAuth auth = new MailAuth(); 익명으로
	    //Session session = Session.getInstance(props, auth); 익명으로
		
		/* 세션 객체 생성 */
		Session session = Session.getDefaultInstance(props, new Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(user, password);
		    }
		});
	    
		/* Message 객체에 메세지에 필요한 내용 설정(수신자, 발신자, 제목, 내용, 첨부파일 등) */
		try {
			
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);
			
		    message.setFrom(new InternetAddress(user, "MVC Book SHOP"));	// 발신자
			message.setSubject(mail.getMl_title());    	// 메일 제목
		    message.setSentDate(new Date());  	// 시간
			
		    // 수신자 메일주소 설정
			InternetAddress[] addressTo = new InternetAddress[mail.getMl_recipient().length];
			for (int i = 0; i < mail.getMl_recipient().length; i++) {
				addressTo[i] = new InternetAddress(mail.getMl_recipient()[i]);
			}
			
			message.setRecipients(Message.RecipientType.TO, addressTo);
			
			// 메일 콘텐츠 설정을 위한 multipart & bodyPart 생성
		    Multipart multipart = new MimeMultipart();
		    MimeBodyPart mTextPart = new MimeBodyPart();
		 
		    // 메일 콘텐츠 - 내용
		    /**********************************************
		     * 본문 처리
		     **********************************************/
		    mTextPart.setText(mail.getMl_text(), "UTF-8", "html"); 
		    multipart.addBodyPart(mTextPart);
		    
		    
		    /**********************************************
		     * 파일 처리. 첨부수대로 처리
		     **********************************************/
		    int cnt = mail.getMl_attachment().size();
		    
		    for(int i=0; i < cnt ;i++) {
		    	
		    	MimeBodyPart attachPart = new MimeBodyPart();
		    	String attachmentFilePath = filePath+"/"+mail.getMl_attachment().get(i);
		    	
		    	DataSource source = new FileDataSource(attachmentFilePath);
			    attachPart.setDataHandler(new DataHandler(source));
			    
			    //파일명칭이 깨지지 않도록 조치
			    try {
					attachPart.setFileName(MimeUtility.encodeText(source.getName(), "euc-kr","B"));
					multipart.addBodyPart(attachPart);	  
					
				} catch (UnsupportedEncodingException e) {
					System.out.println("파일 endcode 에러 발생");
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			    
		    }
	
		    /**********************************************
		     * multipart 본문, 파일 모두 보낸다  ~~~~
		     **********************************************/
		    message.setContent(multipart);
	 
		     // MIME 타입 설정
		     MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		     MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		     MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		     MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		     MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		     MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		     CommandMap.setDefaultCommandMap(MailcapCmdMap);
		 
		     // 메일 발송
		     Transport.send( message );
		     sendSuccess = true;
		     System.out.println("메일을 발송 합니다 : "+sysdate);
		} catch (MessagingException e) {
			 System.err.println("Error sending email: " + e.getMessage());
		}
	    
		return sendSuccess;
	}
	
	/** 인증코드 생성 메서드 */
	private String makeAuthenticationCode() throws Exception {
		
		int pwdLength = 8;
		final char[] pwdTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
                'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
                'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*',
                '(', ')', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		
		// System.currentTimeMillis(): 중복 방지 처리
		Random ran = new Random(System.currentTimeMillis());
		
		StringBuffer bf = new StringBuffer();
		for(int i=0; i<pwdLength; i++) {
			bf.append(pwdTable[ran.nextInt(pwdTable.length)]);
			
		}
		
		return bf.toString();
	}
	
}
