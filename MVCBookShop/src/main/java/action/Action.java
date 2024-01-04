package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.ActionForward;


/** Action 클래스들의 규격을 정의한 Action 인터페이스.
 * Model(DB 처리동작)동작이 필요할 때 사용하는 객체.
 * Interface로 만들어 처리하는 동작의 형태(틀)을 강제로 부여.
 * 한 컵에 물을 담으면 물컵이 되고, 꽃을 심으면 화분이 된다. 
 * 쓰임은 달라지지지만, 틀-컵의 역할이 무언가를 담은 거에 있음은 변함이 없다. 이 컵이 바로 인터페이스의 역할!
 * */
public interface Action {
	
	/** 각 요청을 처리하는 Action 클래스들이 공통적으로 구현해야 하는 메서드.
	 * 웹 요청을 처리하고 응답하기 위해 HttpServlet에서 각각 request, response를 전달받아 처리한 후 ActionForward를 리턴.
	 * */
	ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
	
}
