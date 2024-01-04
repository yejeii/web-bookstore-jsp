package util;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/** 암호화 및 복호화 클래스 */
public class AesCBCUtil {

	private static AesCBCUtil aesCBCUtil;
	private SecretKeySpec secretKey;
	private IvParameterSpec Iv;
	
	/** 생성자 
	 * 외부 클래스에서 생성자를 사용해 객체를 새로 생성할 수 없도록 생성자의 접근 제한자를 private으로 지정 
	 * 1. 비밀키 설정
	 * 2. 초기 암호화블록 설정 
	 * @throws Exception */
	private AesCBCUtil() throws Exception {
		super();
		
		// 비밀키 생성
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		this.secretKey = (SecretKeySpec) keyGenerator.generateKey();
		
		// 초기 암호화블록(Iv) 초기화
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);		// 사용자가 지정한 바이트수의 난수 바이트를 생성
		this.Iv = new IvParameterSpec(iv);		// 초기화된 벡터(Iv) 리턴
	}
	
	/** 싱글톤 패턴으로 AesCBCUtil 객체를 생성하여 리턴하는 메서드 
	 * @throws Exception */
	public static AesCBCUtil getInstance() throws Exception {
		if(aesCBCUtil == null) {
			aesCBCUtil = new AesCBCUtil();
		}
		return aesCBCUtil;
	}
	
	/** 비밀키 반환 */
	public SecretKeySpec getSecretKey() {
		return secretKey;
	}

	/** 초기 암호화블록(Iv) 반환 */
	public IvParameterSpec getIv() {
		return Iv;
	}

	/** AES CBC PKCS5Padding 암호화(Base64) */
	public String encode(String plainTxt) throws Exception{
		
		/* Cipher 객체 인스턴스화 및 초기화 */
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, Iv);
		
		/* 암호화 */
		byte[] encrpytionByte = cipher.doFinal(plainTxt.getBytes(StandardCharsets.UTF_8));
		
		/* Base64 인코딩 스키마 사용
		 * Base64.getEncoder().encode() : 
		 * 	Encodes all bytes from the specified byte array into a newly-allocatedbyte array using the Base64 encoding scheme.
		 * 	Returns:A newly-allocated byte array containing the resultingencoded bytes.
		 * new String(Byte[] b) : Constructs a new String by decoding the specified array of bytes  */
		return new String(Base64.getEncoder().encode(encrpytionByte));
	}
	
	/** AES CBC PKCS5Padding 복호화(Base64) */
	public String decode(String encodeTxt) throws Exception {
		
		/* Cipher 객체 인스턴스화 및 초기화 */
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, Iv);
		
		/* Base64 디코딩 스키마 사용하여 복호화 
		 * Base64.getDecoder().decode() : 
		 *  Decodes a Base64 encoded String into a newly-allocated byte arrayusing the Base64 encoding scheme. 
		 *  Returns:A newly-allocated byte array containing the decoded bytes. */
		byte[] decodeByte = cipher.doFinal(Base64.getDecoder().decode(encodeTxt));
		
		/* 문자열로 리턴 */
		return new String(decodeByte, StandardCharsets.UTF_8);
	}
	
}
