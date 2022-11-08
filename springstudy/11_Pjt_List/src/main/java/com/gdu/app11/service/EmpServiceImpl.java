package com.gdu.app11.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app11.domain.EmpDTO;
import com.gdu.app11.mapper.EmpMapper;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpMapper empMapper;
	
	@Override
	public void findAllEmployees(HttpServletRequest request, Model model) {
		// 파라미터가 있으므로 request를 받아온다 -> 페이지라는 파라미터를 받아오기 위해! (페이지 파라미터 없으면 1페이지로 돌아온다)
		// model은 결과 명단 저장하려고 가져옴!
		
		// request에서 page 파라미터 꺼내기
		// page 파라미터가 전달되지 않는 경우 page = 1 로 처리한다.
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));   // 파라미터가 없으면 1페이지로 가게 한다.

		/*
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
		int totalRecord = empMapper.selectAllEmployeesCount();
		
		int recordPerPage = 10; // 한 페이지당 10개의 record
		int begin = (page - 1) * recordPerPage + 1;
		int end = begin + recordPerPage - 1;  // 마지막 페이지 : totalRecord(54)보다 작은 값을 실제 end로
		if(end > totalRecord) {
			end = totalRecord; // end가 totalRecord보다 크면 end와 totalRecord를 똑같게.
		}
		
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
		List<EmpDTO> employees = empMapper.selectEmployeesByPage(begin, end);
		
		model.addAttribute("employees" ,employees);  // "employees"이름으로 list.jsp로 넘어간다
		//System.out.println(employees);
		
	}

}
