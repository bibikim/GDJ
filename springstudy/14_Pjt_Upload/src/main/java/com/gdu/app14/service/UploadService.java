package com.gdu.app14.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.domain.UploadDTO;

public interface UploadService {

	public List<UploadDTO> getUploadList();
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	// uploadNo 받아서 model에 실어주자 -> 2개를 한번에 반환하려면 골때리니까 model에 받아오자 (UploadDTO, AttachDTO타입의 List 2가지 반환)
	public void getUploadByNo(int uploadNo, Model model);
	public ResponseEntity<Resource> download(String userAgent, int attachNo);  // userAgent가 머임
	public void removeAttachByAttachNo(int attchNo);
	
}
