package com.gdu.app11.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app11.domain.EmpDTO;
import com.gdu.app11.mapper.EmpMapper;
import com.gdu.app11.util.PageUtil;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpMapper empMapper;
	
	// PageUtil은 컴포넌트로, 빈으로 만들어져있다(@Component 애너테이션 했으니까!) -> 여기서 PageUtil 빈을 사용하고 싶으면 @Autowired를 이용하자
	@Autowired	
	private PageUtil pageUtil;   // PageUtil이름으로 빈을 가져옴. 왜냐면?!? Autowired는 타입이 일치하면 가지고 오니까!
	
	@Override
	public void findAllEmployees(HttpServletRequest request, Model model) {
		// 파라미터가 있으므로 request를 받아온다 -> 페이지라는 파라미터를 받아오기 위해! (페이지 파라미터 없으면 1페이지로 돌아온다)
		// model은 결과 명단 저장하려고 가져옴!
		
		// request에서 page 파라미터 꺼내기
		// page 파라미터가 전달되지 않는 경우 page = 1 로 처리한다.
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));   // 파라미터가 없으면 1페이지로 가게 한다.

		/*
		 * page는 파라미터로 주소창으로 전달
			sqldeveloper에서 정렬 다음에 그냥 붙인 번호 : Rownum
			
			total 54
			      <rownum 기준>으로 begin과 end를 구하는 것이당!
			page   begin   end   totalRecord   실제end
			1       1      10   <   54			  10
			2       11     20   <   54			  20
			3       21     30   <   54			  30
			4       31     40   <   54			  40
			5       41     50   <   54		  	  50
			6       51     60   >   54			  54
			
			-> rownum에 맞춰서 검색해온다
			- 한 페이지에 몇 개의 목록을 표시할 것인가?
 		 
		*/
		// 전체 레코드(직원) 개수 구하기
		int totalRecord = empMapper.selectAllEmployeesCount();
	
		
		// 입사순으로 정렬한 뒤(a) rownum 불러들이고 11 ~ 20 가져오기
		/*
			select b.*
			  from (select rownum as rn, a.*
					  from (select *
							  from employees
							  order by hire_date desc) a) b            정렬한 테이블을 a라고 붙이고, 정렬한 뒤 rn이라는 rownum 붙이기, 그걸 b라고 부르기
			where b.rn between 11 and 20;
		
			-> begin이 11, end가 20. 즉 매퍼에서는 b.rn between #{param1} and %{[param2}  
		*/
		
		// PageUtil 계산하기
		pageUtil.setPageUtil(page, totalRecord);
		
		// Map 만들기(begin, end)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		// begin-end 목록 가져오기
		List<EmpDTO> employees = empMapper.selectEmployeesByPage(map);
		
		model.addAttribute("employees" ,employees);  // "employees"이름으로 list.jsp로 넘어간다
		model.addAttribute("pageUtil", pageUtil);
		//System.out.println(employees);
		
	}

}
