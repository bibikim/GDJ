package com.gdu.app05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.service.MovieService;

@Controller
public class MyController3 {

	@GetMapping("movie")
	public String welcome() {
		return "movie";
	}
	
	@Autowired
	private MovieService movieService;
	
	// 요청 처리 메소드 만들고!
	@ResponseBody
	@GetMapping(value="movie/boxOfficeList")
	public String getBoxOffice(String targetDt) {
		return movieService.getBoxOffice(targetDt);
	}
}
