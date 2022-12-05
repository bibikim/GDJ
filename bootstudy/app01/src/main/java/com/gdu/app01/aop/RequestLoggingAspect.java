package com.gdu.app01.aop;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component    // RequestLoggingAspect 클래스를 Bean으로 만들어 두시오.
@Aspect		  // 안녕. 난 Aspect야. AOP 동작하려면 내가 필요해.
@EnableAspectJAutoProxy
public class RequestLoggingAspect {

	// 로거    // 실제 내부에서 동작하는건 logback이지만 개발자가 사용하는건 slf4j임
	private static final Logger LOG = LoggerFactory.getLogger(RequestLoggingAspect.class);   // 대문자인 이유: static final이라서.  // slf4j 인터페이스 Logger
		// aop에 한번만 만들어 두고 자동반영하게 하려는 목적!
	
	 
	// controller 패키지 아래 모든 패키지(..)의 모든 클래스(*)
	@Pointcut("within(com.gdu.app01.controller..*)") 	// -▶ 컨트롤러의 모든 메소드(조인포인트)를 포인트컷으로 지정하겠다. -> 모든 조인포인트를 포인트컷으로!
														// 컨트롤러의 모든 메소드에서 어드바이스(콘설에 로그 찍기)가 동작한다.
	 public void setPointCut() {	// 오직 포인트컷 대상을 결정하기 위한 메소드(이름 : 아무거나, 본문 : 없음)
				// 포인트 컷만 여기서 달아놓고 밑에서(@Around) 지정함
	}
	
	
	
	// 어드바이스 설정
	// 어드바이스 실행 시점
	// @Before(메소드 호출 전), @After(메소드 호출 후), @AfterReturing(메소드가 정상 반환하면), @AfterThrowing(메소드가 오류 뿜을 떄), @Around(메소드 호출 전후 -> 가장 많이 씀)
	@Around("com.gdu.app01.aop.RequestLoggingAspect.setPointCut()")		// setPointCut() 메소드에 설정된 포인트컷에서 동작하는 어드바이스
	// 옆에 빨간 아이콘 : exexuteLogging 코드를 전부다 controller의 아이콘 찍힌 메소드에 다 넣어주겠단 의미!
	public Object executeLogging(ProceedingJoinPoint joinPoint) throws Throwable {	// @Around는 반드시 ProceedingJoinPoint joinPoint 선언해야 함	// 실제로 동작할 어드바이스
	
		// HttpServletRequest를 사용하는 방법
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();  // 현재 모든 요청 관련된 객체들 중에서 request를 가져와라

		// HttpServletRequest를 Map으로 바꾸기  -> 맵으로 바꿔서 key와 value
		// 파라미터는 Map의 key가 되고, 값은 Map의 value가 된다.
		Map<String, String[]> map = request.getParameterMap();  // map으로 바꿔주는 메소드
		String params = "";
		if(map.isEmpty()) {  // 파라미터가 없으면(map이 비어있으면)
			params += "[No Parameter]";
		} else {		// 파라미터가 있으면 for문 돌려서 key와 value값 빼내기
			for(Map.Entry<String, String[]> entry : map.entrySet()) {
				params += "[" + entry.getKey() + "=" + String.format("%s", (Object[])entry.getValue());   // "%s" 문자열 출력하겠다는 약속된 포맷코드!  // 타입맞춰주기 위해 (Object[])캐스팅
 			}		
		}
		
		// 어드바이스는 proceed() 메소드 실행 결과를 반환
		Object result = null;
		try {
			result = joinPoint.proceed(joinPoint.getArgs());
		} catch(Exception e) {
			throw e;
		} finally {
			// 무조건 실행되는 영역(여기서 로그를 찍는다.)
			// 치환문자 : {}
			LOG.info("{} {} {} > {}", request.getMethod(), request.getRequestURI(), params, request.getRemoteHost());  // get방식인지 post방식인지 찍어줌(GET) // uri 찍어줌(/app08/) // 파라미터 // 주소찍어줌
			// 작업마다 찍을 수 있게(INFO 레벨) 코드 짜봤음! -> 기능 사용할 때마다 콘솔에 찍힌다. 만약 title에 null이 뜨면 title 파라미터에 문제가 있는거니까 오류 찾아보면 된다.
		}
		
		return result;
	}
	
	
	
}
