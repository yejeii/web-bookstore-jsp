package util;

import java.util.Random;

public class RandomUtil {

	/** 영문+숫자 랜덤코드 생성 메서드 */
	public static String createRanAlNum(int cnt) {
		
		Random ran = new Random();
		String ranNum = "";
		
		for(int i=0; i<cnt; i++) {
			if(ran.nextBoolean()) {
				ranNum += (char)((int)(ran.nextInt(26))+97);
			} else {
				ranNum += ran.nextInt(10);
			}
		}
		
		return ranNum;
	}
}
