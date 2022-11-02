package com.gdu.app05.service;

import java.io.File;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;

public class GalleryServiceImpl implements GalleryService {

	// 절대경로에 있는 이미지의 표시를 위한 작업들!!!!
	
	@Override
	public ResponseEntity<byte[]> imageDisplay(String path, String filename) {
		// 경로하고 이름 알면 자바는 제일 먼저 file 객체를 만들 수가 있다
		
		File file = new File(path, filename);
		
		ResponseEntity<byte[]> entity = null;  // 바이트 배열이 저장된 entity는 null
		
		try {
			
			String contentType = Files.probeContentType(file.toPath());	// 파일객체(file)가 있기 때문에 파일객체를 이용해서 컨텐트타입을 알아낼 수 있다.  -> probeContentType() : Content-Type을 알아서 가져옴!
			System.out.println(contentType);
			
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", contentType);    // 컨텐트 타입은 뫄뫄다, 알려주는 거~. 바이트배열은 image야 라고 알려주는거야  // image의 mime-type은 이미지 확장자
														// image/jpeg
			
			
			entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK); // 파일객체가 바이트배열로 바뀐거!
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return entity;
	}

}
