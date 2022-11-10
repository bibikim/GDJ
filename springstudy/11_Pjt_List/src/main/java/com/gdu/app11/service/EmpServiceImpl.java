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
		int page = Integer.parseInt(opt.orElse("1"));   // 파라미터가 없으면 1페이지로 가게 한다. page는 내가 클릭해서 현재 있는 페이지!

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
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/emp/list"));   // 경로 보내주기 => request.getContextPath() + "/emp/list"
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());  // 1페이지는 107-(1-1) * 10  -> 1페이지는 107부터 시작!
		//model.addAttribute("pageUtil", pageUtil);  -> view/list.jsp에서 페이징처리할 때 썼던 코드
		//System.out.println(employees);
		
	}
	
	
	@Override  // 추상메소드 오버라이드
	public void findEmployees(HttpServletRequest request, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));      // 검색후 뜨는 페이징의 페이지도 파라미터로 전달되어야 하므로 ~~~
		
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		String start =  request.getParameter("start");
		String stop = request.getParameter("stop");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("column", column);   // list.jsp에서 column이라는 파라미터를 받아와서 column으로 저장. 이걸 employee.xml에서 받아서 씀!
		map.put("query", query);
		map.put("start", start);
		map.put("stop", stop);
	
		int totalRecord = empMapper.selectFindEmployeesCount(map);  // 전체 검색 개수
		
		pageUtil.setPageUtil(page, totalRecord);
		
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		List<EmpDTO> employees = empMapper.selectFindEmployees(map);  // map 전달
		
		model.addAttribute("employees", employees);
		model.addAttribute("beginNo", totalRecord - (page - 1 ) * pageUtil.getRecordPerPage());
		//model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/emp/search"));
									// └> 여기서 파라미터 정보를 넘겨주지 않고 있어서 검색후 페이징이 넘어가면 초기화 되는것이 문제
									// 페이징처리할 때도 주소창으로 파라미터를 보내줘야 한다.
		
		// 새로 path 잡기!
		// 페이징에도 검색했을 때 붙는 파라미터를 적어줘야 한다.
		String path = null;
		switch(column) {
		case "EMPLOYEE_ID" :
		case "E.DEPARTMENT_ID":
		case "LAST_NAME":
		case "FIRST_NAME":
		case "PHONE_NUMBER":
			// └---- 조회 시 사용하는 파라미터 : query  사용
			path = request.getContextPath() + "/emp/search?column=" + column + "&query=" + query;
			break;
		case "HIRE_DATE":
		case "SALARY":
			// └---- 조회 시 사용하는 파라미터 : start, stop 사용
			path = request.getContextPath() + "/emp/search?column=" + column + "&start=" + start + "&stop=" + stop;
			break;
		}
		model.addAttribute("paging", pageUtil.getPaging(path));
		/* /app11/emp/search?column=EMPLOYEE_ID&query=150 --> 이 주소가 getPaging(path)의 path로 들어감
		   -> 그럼 이 path는 pageUtil의 <a>링크 안에 path로 들어간다. 
		    * <a href=\"" + ^/app11/emp/search?column=EMPLOYEE_ID&query=150^ + "?page=" + p + "\">"
		    주소창 뒤에 파라미터가 시작될 때 들어가는 ? 가 두개가 됨. 잘못된 주소!!
		    
		    전달받은 패스에 ?가 있따는건 검색을 했다는 것인데, 검색한 상태를 유지하면서 page 파라미터를 &page로 처리할 수 있게끔 만들어줘야 한다.
				-> pageUtil에서 <a>링크 이전에 if(path.contains)으로 처리한다!
		*/
	}
	
	// empService에서 메소드 만들고 나면 여기서 ctrl+space로 오버라이드 자동완성 하면 됨
	@Override
	public Map<String, Object> findAutoCompleteList(HttpServletRequest request) {
		
		String target = request.getParameter("target");
		String param = request.getParameter("param");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("param", param);
		
		List<EmpDTO> list = empMapper.selectAutoCompleteList(map);
		
		Map<String, Object> result = new HashMap<String, Object>();
		if(list.size() == 0) {  // list에 검색결과가 없으면
			result.put("status", 400);
			result.put("list", null);
		} else {	// list에 검색결과가 있으면
			result.put("status", 200);
			result.put("list", list);
		}
		
		switch(target) {
		case "FIRST_NAME" : result.put("target", "firstName"); break;
		case "LAST_NAME" : result.put("target", "lastName"); break;
		case "EMAIL" : result.put("target", "email"); break;
		}
	
		
		return result;
		
		/*
			Map<> result가 jackson에 의해서 아래 JSON으로 자동 변환된다.
				result에 들어있는 프로퍼티가 3개~! status와 list, target
			result = {
				"status" : 200							=> status property 꺼내기 :  result.status  /  result["status"]
				"list" : [          
					
						// {}하나하나의 타입은 EmpDTO
					{
					 	// select에서 email만 가져오고 나머지는 안 갖고 왔기 때문에 null 
					  "employeeId": null,
					  "firstName": null,
					  ...
					  "email": "MHARTSTE"  				=> list property 꺼내기 :   result.list[0].email
					},
					{
						...
					},
					...
				],
				"target" : "email"						=> result.target
			}
		
		*/
		
		
	}

}
