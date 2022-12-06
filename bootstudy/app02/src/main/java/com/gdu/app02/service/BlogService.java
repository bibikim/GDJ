package com.gdu.app02.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app02.domain.BlogDTO;

public interface BlogService {
	// 모델을 받아와서 필요한거 실어줄겡~
	public void getBlogList(Model model);  // 컨패도 쓰고 파라미터도 써얗니 request,  request를 매개변수로 안 보냈는데 보낼 수 있음. 여기서 해볼거임
	public void saveBlog(HttpServletRequest request, HttpServletResponse response); // ip는 request에서 꺼내쓰기 대문에 파라미터들을 리퀘스트로 보내는게 편함
	public Map<String, Object> saveSummernoteImage(MultipartHttpServletRequest multipartRequest);  // 반환타입 Map, 매개변수 mul~
	public int increaseBlogHit(int blogNo); 
	public BlogDTO getBlogByNo(int blogNo); 
	public void modifyBlog(HttpServletRequest request, HttpServletResponse response);
	public void removeBlog(HttpServletRequest request, HttpServletResponse response);
	
}
