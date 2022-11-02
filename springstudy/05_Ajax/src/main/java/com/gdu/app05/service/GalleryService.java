package com.gdu.app05.service;

import org.springframework.http.ResponseEntity;

public interface GalleryService {
	public ResponseEntity<byte[]> imageDisplay(String path, String filename);   // 이미지 경로하고 이름을 주면 바이트배열로 저장하겠다
}
