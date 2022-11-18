package com.gdu.app13.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.app13.service.UserService;

@EnableScheduling
@Component   // 컴포넌트를 해줘야 빈으로 만들어서 가져간당
public class SleepUserScheduler {

	@Autowired
	private UserService userService;
	
	// 매일 새벽 1시(cron="0 0 1 * * *")
	@Scheduled(cron="0 02 11 * * *")  // 이 메소드가 하루에 한번 동작하게끔 설정학꺼당   <- @Scheduled 쓰려면 @Enable~~ 애너테이션 필요
	public void execute() {
		userService.sleepUserHandle();
	}
	
	
	// interceptor의 prehandle작업으로 로그인할 때 휴면회원인지 확인하는 코드 짜기 가넝
}
