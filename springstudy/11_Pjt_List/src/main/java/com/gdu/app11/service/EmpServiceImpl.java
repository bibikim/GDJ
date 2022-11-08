package com.gdu.app11.service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public class EmpServiceImpl implements EmpService {

	@Override
	public void findAllEmployees(HttpServletRequest request, Model model) {
		// 파라미터가 있으므로 request를 받아온다 -> 페이지라는 파라미터를 받아오기 위해! (페이지 파라미터 없으면 1페이지로 돌아온다)
		// model은 결과 명단 저장하려고 가져옴!
		
		// request에서 page 파라미터 꺼내기
		// page 파라미터가 전달되지 않는 경우 page = 1 로 처리한다.
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("0"));

	}

}
