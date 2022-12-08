package com.gdu.rest.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.gdu.rest.domain.MemberDTO;
import com.gdu.rest.mapper.MemberMapper;
import com.gdu.rest.util.PageUtil;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PageUtil pageUtil;
	
	@Override
	public Map<String, Object> register(MemberDTO member, HttpServletResponse response) {
		
		
		try {
			
			// 반환시킬 맵을 만들어서 반환시키자!
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("insertResult", memberMapper.insertMember(member));  // 성공하면 insertResult를 보냄
										// └> 여기서 예외가 발생한다고 하면 이 곳임 => 1. unique한 데이터가 들어오지 않았다  2. not null인데 null이 들어왔다
			return result;
		} catch(DuplicateKeyException e) {
			// 이렇게 오류 하나하나 확인해보면서 catch로 막고 하면 좋다아~~~
			// 1. 오류시 뜨는 예외클래스 : DuplicateKeyException    id가 중복일 때 뜨는 오류!  이렇게 백단에서도 막고, 프론트단에서도 막고 하면 좋다!
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				response.setStatus(501); // 응답 코드 501
				out.println("이미 사용 중인 아이디입니다.");  // 응답 메시지    // handle.jsp의 error로 오는 응답~
				out.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
			
		} catch(DataIntegrityViolationException e) {
			//e.printStackTrace();
			System.out.println(e.getClass().getName());  
			// e.getClass().getName() -> 예외클래스의 이름을 띄워줌. 클래스 이름 확인 가능.
		// java.sql.SQLIntegrityConstraintViolationException -> 스프링은 duplicatekey를 뿜지만 ... ? -> 이 오류 찾아보기ㅠ_ㅠ
	
			// DataIntegrityViolationException  : 필수요소입력을 안 한 상황에 뜨는 예외 (not null인데 null값 들어온 경우)
			// UncategorizedSQLException        : 가능한 byte 수 초과했을 때 뜨는 예외
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				response.setStatus(502); // 응답 코드 501
				out.println("필수 정보가 누락되었습니다.");  // 응답 메시지    // handle.jsp의 error로 오는 응답~
				out.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
			
			
		} catch(Exception e) {
			
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				response.setStatus(503); // 응답 코드 501
				out.println("입력 정보를 확인하세요.");  // 응답 메시지    // handle.jsp의 error로 오는 응답~
				out.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return null;
	}
	
	@Override
	public Map<String, Object> getMemberList(int page) {
		
		int totalRecord = memberMapper.selectMemberCount();
		pageUtil.setPageUtil(page, totalRecord);
		
		// map에 begin, end 값 담기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		// 결과 반환할 map 만들기
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("memberList", memberMapper.selectMemberListByMap(map));
		result.put("pageUtil", pageUtil);
		
		return result;
	}
	
	@Override
	public Map<String, Object> getMemberByNo(int memberNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("member", memberMapper.selectMemberByNo(memberNo));
		return result;
	}
	
	
}
