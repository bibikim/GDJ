package com.gdu.app01.java02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfig {

	@Bean
	public Student stud() {    // 반환타입 학생, stud()메소드   ==>  bean의 id는 stud
		// ★★ 메소드의 이름 == bean의 id
		// ★ <bean id="stud" class="Student">
		
		// List
		List<Integer> scores = new ArrayList<Integer>();
		for(int cnt = 0; cnt < 5; cnt++) {
			// 점수가 1이면 1을 5번 추가.
			// 1에서 100사이의 난수가 만들어지게끔 코드 추가
		scores.add( (int)(Math.random() * 101 + 0) ); // 0부터 101개의 난수가 발생(0~100)
												 // +0을 통해서 시작점 잡아주고 * 101을 통해서 갯수 잡아준다
		}
			
		// Set
		Set<String> awards = new HashSet<String>();
		awards.add("개근상");
		awards.add("장려상");
		awards.add("우수상");
		
		// Map
		Map<String, String> contact = new HashMap<String, String>();
		contact.put("address", "서울");
		contact.put("tel", "010-9997-3332");
		
		/* 컬렉션, 맵 다시 공부하기!!!!!!!!!! 실무는 배열보다 컬렉션프레임워크를 많이 쓴다잉 */
		
		// Bean 생성 및 반환
		Student student = new Student();
		student.setScores(scores);
		student.setAwards(awards);
		student.setContact(contact);
		return student;
	}
	
}
