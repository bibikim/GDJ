package com.gdu.rest.service;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
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
	
	
	@Override
	public Map<String, Object> modifyMember(Map<String, Object> map, HttpServletResponse response) {
		
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("updateResult", memberMapper.updateMember(map));
			return result;
		} catch(DataIntegrityViolationException e) {
			try {   // 요기 트라이 블럭이 코드가 넘 길어서 나온게 ResponseEntity임!! 응답 전용 객체 new ResopnseEntity 로 하면 코드 한줄이면 쌉가넝
					// new ResponseEntity<>(null);   -> body(응답할 값), header, status(예외) 다 받아와서~
				response.setContentType("text/plain; charset=UTF-8");
				PrintWriter out = response.getWriter();   // response.getWriter(); 얘가 반환하는게 printwriter라서 프.라 쓰는거
				response.setStatus(501);  // 임의의 응답코드 501
				out.println("필수 정보가 누락되었습니다.(null왔다)");
				out.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
			
		}  catch(Exception e) {
			
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
	
	
	// 삭제
	@Override
	public Map<String, Object> removeMemberList(String memberNoList) {
		List<String> list = Arrays.asList(memberNoList.split("\\,")); // 어레이리스트 초기화할때 쓰는거
												 // "3,1" 과 같이 하나의 문자열을 {"3", "1"}과 같이 쪼개서 배열로 바꾸고 싶을 떄 split("정규식")
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("deletResult", memberMapper.deleteMemberList(list));  // 3, 1 두개를 전달해서 지웠다 치면 deleteResult의 값 2가 나오는 것!(실제로 삭제된 데이터의 갯수 반환)
		return result;
	}
	
}
