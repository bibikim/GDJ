package com.gdu.app11.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmpService {
	public void findAllEmployees(HttpServletRequest request, Model model);   // 페이징처리를 위해서는 파라미터가 필요하다. -> 페이지 번호가 파라미터로 넘어가야 함! (페이지 파라미터가 안 넘어오면 1페이지로 넘어옴)
		// 파라미터 받아오는 방법 3가지 request, requestParam, 객체
		// 페이지 정보를 넘겨받기 위해 파라미터가 필요하므로 파라미터를 받아오는 request!
		// 서비스에서 리스트를 반환한다 -> 컨트롤러가 해야 되는 일 : 포워딩하기 위해서 model에 싣고(model.addAtribute) 실어서 jsp로 가고 view로 간다.
		// void 처리 해놨기 때문에 서비스와 컨트롤러가 주고받는게 없음 -> 넘겨지지 않음 -> service인 내가 할게! (= model.addAttribute)
		// 따라서 두번째 파라미터로 model이 필요하다.
		
		
		
		
		// 요청/응답/세션/모델 : 최초로 선언할 수 있는 곳 = Controller
		// 선언은 컨트롤러, 받아 쓰는 곳은 Service. 서비스단에서 쓰고 싶으면 컨트롤러한테 받아와야 함.
		// 따라서 HttpServletRequest request, Model model도 컨트롤러가 주는 거 받아오는 것이다.
	
}
