package com.gdu.app05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.service.MovieService;

@Controller      // 컨트롤러는 심플하게 주는 것이 좋다! 제일 자주 호출 되니까.
public class MyController3 {

	@GetMapping("movie")
	public String welcome() {
		return "movie";
	}
	
	// field
	// movieServiceImple을 
	@Autowired // Container(root-context.xml)에 저장된 bean을 가져오시오
	private MovieService movieService;
	
	// 요청 처리 메소드 만들고!
	
	@ResponseBody
	@GetMapping(value="movie/boxOfficeList")  // == ("/movie/boxOfficeList")
	public String getBoxOffice(String targetDt) {    // jsp으로 넘겨줄 데이터!!
		return movieService.getBoxOffice(targetDt);
	}
	
	/*
	@GetMapping("movie/boxOfficeList")     // 받는게 string이 아닌 json 따라서 produces=MediaType.Application_Json_Value
	public String getBoxOffice(@RequestParam String targetDt) {
		return movieService.getBoxOffice(targetDt);
	}
	*/

}
