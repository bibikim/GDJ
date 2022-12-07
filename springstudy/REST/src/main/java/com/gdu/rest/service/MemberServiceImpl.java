package com.gdu.rest.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.gdu.rest.domain.MemberDTO;
import com.gdu.rest.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public Map<String, Object> register(MemberDTO member, HttpServletResponse response) {
		
		
		try {
			
			// 반환시킬 맵을 만들어서 반환시키자!
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("insertResult", memberMapper.insertMember(member));  
										// └> 여기서 예외가 발생한다고 하면 이 곳임 => 1. unique한 데이터가 들어오지 않았다  2. not null인데 null이 들어왔다
			return result;
		} catch(DuplicateKeyException e) {
			// 이렇게 오류 하나하나 확인해보면서 catch로 막고 하면 좋다아~~~
			// 1. 오류시 뜨는 예외클래스 : DuplicateKeyException    id가 중복일 때 뜨는 오류!  이렇게 백단에서도 막고, 프론트단에서도 막고 하면 좋다!
			
			
			
		} catch(Exception e) {
			//e.printStackTrace();
			System.out.println(e.getClass().getName());  
			// e.getClass().getName() -> 예외클래스의 이름을 띄워줌. 클래스 이름 확인 가능.
		// java.sql.SQLIntegrityConstraintViolationException -> 스프링은 duplicatekey를 뿜지만 ... ? -> 이 오류 찾아보기ㅠ_ㅠ
		}
		
		return null;
	}
}
