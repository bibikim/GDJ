package com.gdu.app12.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app12.mapper.BbsMapper;
import com.gdu.app12.util.PageUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor  // 생성자를 이용한 자동주입!
@Service   // bean으로 등록하기 위해 @service 애너테이션
public class BbsServiceImpl implements BbsService {
	
	//@Autowired  // service는 Mapper(DAO역할)를 사용
	// 필드가 여러 개(여긴 2개)이면, Autowired를 여러개 쓰는 것 보다는, 생성자를 이용해서 자동주입 하는 것이 좋다.
	private BbsMapper bbsMapper;
	private PageUtil pageUtil;

	@Override
	public void findAllBbsList(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub

	}

	@Override
	public int addBbs(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addReply(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeBbs(int bbsNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
