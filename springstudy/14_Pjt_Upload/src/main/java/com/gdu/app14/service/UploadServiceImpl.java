package com.gdu.app14.service;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.domain.AttachDTO;
import com.gdu.app14.domain.UploadDTO;
import com.gdu.app14.mapper.UploadMapper;
import com.gdu.app14.util.MyFileUtil;

@Service
public class UploadServiceImpl implements UploadService {

	@Autowired
	private UploadMapper uploadMapper;
	
	@Autowired
	private MyFileUtil myFileUtil;
	
	@Override
	public List<UploadDTO> getUploadList() {	
		return uploadMapper.selectUploadList();   // 목록 페이징처리 하자 - 11장 참고
	}
	
	@Transactional  // insert가 두번 (upload와 attach 모두 insert가 있으므로)
	@Override
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {
		// 파라미터
		String title = multipartRequest.getParameter("title");
		String content = multipartRequest.getParameter("content");
		
		// DB로 보낼 UploadDTO
		UploadDTO upload = UploadDTO.builder()
				.title(title)
				.content(content)
				.build();
		
		// System.out.println(upload);  // uploadNo 없음
		// uploadNo = 0
		
		// DB에 UploadDTO 저장
		int uploadResult = uploadMapper.insertUpload(upload);  // <selectKey> 에 의해서 전달해준 UploadDTO upload에 uploadNo도 들어가 있는 상태(upload에 uuploadNo 값이 저장된다.)

		// System.out.println(upload);   // insert 하고 나면 uploadNo 있음
		// 첨부하고 나면, nextVal값을 uploadNo가 쓰고 있음
		
		
		// └--- 첨부랑 노상관 부분 ----┘
		// upload
		// 1번 게시글에 첨부 3개되는 상황
		// 1/제목/내용/1121/1121
		
		// attach
		// 1/storage/사과/apple/0/1
		// 2/storage/바나나/banana/0/1
		// 3/storage/사과/apple/0/1
		//   -> 서비스에서는 제일 끝에 seq값 몇번을 사용했는 지 알 수 없음(uploadNo는 db에서 만드는 거니까).
		// uploadNo를 service로 넘겨주는 방법
		
		
		/* ATTACH 테이블에 저장하기 */
		// files 리스트
		// files.get(0) -> List에서 첫번째 요소 가져오기    ([0] 아님)
		// files.get(0).getSize() = 첫번째 요소의 사이즈 가져오기 (== 0)
		// files.get(0).getOriaginalFilename().isEmpty()
		
		
		// 첨부된 파일 목록
		List<MultipartFile> files = multipartRequest.getFiles("files");   // write.jsp에 <input type="file" name="files">
				
		// 첨부 결과
		// 첨부를 안 하고 글을 쓸 때
		// files.size() == 1  파일이 없다는 정보로 1개가 들어가 있음..
		// attachResult는 0인데 files.size()는 1이 뜸
		int attachResult;
		if(files.get(0).getSize() == 0) {  // 첨부가 없는 경우 (files 리스트에 [MultipartFile[field="files", filename=, contentType=application/octet-stream, size=0]] 이렇게 저장되어 있어서 files.size()가 1이다.
			attachResult = 1;
		} else {
			attachResult = 0;
		}
		
		
		// 첨부된 파일 목록 순회(하나씩 저장)
		for(MultipartFile multipartFile : files) {     // 파일첨부 갯수만큼 반복문이 돈다. 
			
			try {
				
				// 첨부가 있는지 점검
				if(multipartFile != null && multipartFile.isEmpty() == false) {   // 둘다 필요
					
					// 원래 이름   - 다운로드 시 원래 원래이름으로 다운로드 되게 원래이름도 db에 넣어준다
					String origin = multipartFile.getOriginalFilename();
					origin = origin.substring(origin.lastIndexOf("\\.") + 1);  // IE는 오리지널네임에 전체 경로가 붙어서 파일명만 사용해야 함. -> 앞에 경로는 버리고 마지막 것만 쓰겠다
					
					// 저장할 이름
					String filesystem = myFileUtil.getFilename(origin);  // origin = 앞에 다떼고 파일이름만 따옴 -> myFileUtil를 통해 만든 uuid값이 붙은 파일명으로 저장
					
					// 저장할 경로
					String path = myFileUtil.getTodayPath();
					
					// 저장할 경로 만들기
					File dir = new File(path);
					if(dir.exists() == false) {
						dir.mkdirs();   // s 필수~
					}
					
					// 첨부할 File 객체
					File file = new File(dir, filesystem);
					
					// 첨부파일 서버에 저장(업로드 진행)
					multipartFile.transferTo(file);   // 필요한 복사 작업을 해줌
					
					// AttachDTO 생성
					AttachDTO attach = AttachDTO.builder()
							.path(path)
							.origin(origin)                   // 원래 파일명
							.filesystem(filesystem)           // 저장 파일명
							.uploadNo(upload.getUploadNo())   // insert 후 upload에서 uploadNo를 가져오기
							.build();
					
					
					
					// DB에 AttachDTO 저장
					attachResult += uploadMapper.insertAttach(attach);  // 받기만 하면 세개 다 성공했는지 실패했는지 알 수 없음 -> 누적을 시켜주면 세개 첨부시 1이 3개 = 3
																		// ==> 첨부된 갯수하고 attachResult가 같은지 비교
				}
				
			} catch(Exception e) {
				
			}
			
		} // for
		
		// 응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			if(uploadResult > 0 && attachResult == files.size()) {   // files = 첨부된 모든 파일의 목록(List).의 size와 같으면 업로드 성공
				out.println("<script>");
				out.println("alert('업로드 되었습니다');");
				out.println("location.href='" + multipartRequest.getContextPath() + "/upload/list'");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('업로드가 실패했습니다');");
				out.println("history.back();");
				out.println("</script>");
			}
			out.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// sts를 실행했기 때문에 서버경로가 sts-bundle로 잡힘.
		// 배포하면 배포되는 루트에 storage에 생김
		
		// 첨부를 안 하고 글을 쓸 때
		// files.size() == 1  파일이 없다는 정보로 1개가 들어가 있음..먼말이고
		// attachResult는 0인데 files.size()는 1이 뜸
		
		
	}
	
	@Override  // 상세보기
	public void getUploadByNo(int uploadNo, Model model) {
		// select 두번은 트랜잭션 노필요 
		model.addAttribute("upload", uploadMapper.selectUploadByNo(uploadNo));
		model.addAttribute("attachList", uploadMapper.selectAttachList(uploadNo));	
				// 조회수 넣으려면 이 곳에서 조회수 작업 필요
	}
	
	@Override
	// ResponseEntity => 페이지 변화 없음 -> 값만 반환하겠다 (ajax)     // HttpEntity를 상속받으면서 HttpStatus, HttpHeader값 등을 가짐
	// 다운로드 자체가 ajax가 필요한 건 아니지만 다운로드 시 페이지가 바뀌지 않고(다운한다고 페이지 바뀌지 않으니깐) 값만 받아오는 ajax같은 상황임. (ajax를 사용하진 않아)
	public ResponseEntity<Resource> download(String userAgent, int attachNo) {
		
		// 다운로드 할 첨부 파일의 정보(경로, 이름)
		AttachDTO attach = uploadMapper.selectAttachByNo(attachNo);  // 요기에 담겨 있씀당
		File file = new File(attach.getPath(), attach.getFilesystem());   // (경로, 파일명)    원래 이름(origin)은 DB에만 있는 거. 다운로드할 파일명은 filesystem이다
		
		// 반환할 Resource
		Resource resource = new FileSystemResource(file); // file 전달
		
		// Resource가 없으면 종료(다운로드 할 파일이 없음)
		if(resource.exists() == false) {
			return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);  // HttpStatus 못 찾겠따~
		}
		
		// 다운로드 횟수 증가
		uploadMapper.updateDownloadCnt(attachNo);
		
		// 다운로드 되는 파일명(브라우저마다 다르게 세팅)
		String origin = attach.getOrigin();
		try {
			
			// IE (userAgent에 "Trident"가 포함되어 있음) - 브라우저 종류가 많자나여.. 브라우저마다 header에 포함되어 있음. 그럼 어떤 브라우저로 접속했늕 알 수 있음. 그게 userAgent임둥
			if(userAgent.contains("Trident")) {
				origin = URLEncoder.encode(origin, "UTF-8").replaceAll("\\+", " ");   // IE는 공백대신 +가 생긴다. 
			}
			// Edge (userAgent에 "Edg"가 포함되어 있음
			else if(userAgent.contains("Edg")) {
				origin = URLEncoder.encode(origin, "UTF-8");
			}
			// 나머지
			else {
				origin = new String(origin.getBytes("UTF-8"), "ISO-8859-1");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 다운로드 헤더 만들기
		// 실제로 반환할 리소스, 헤더, 상태값
		HttpHeaders header = new HttpHeaders(); // 스프링프레임워크
		header.add("Content-Disposition", "attachment; filename=" + origin);  // filename=" + origin -> 다운로드 받을 때 다운로드 받을 이름 정해주기
		header.add("Content-Length", file.length() + "");

		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
		// 리소스를 만들면 다른 귀찮은거 안해도 댐 그게 머엿더랑~
	}
	
	
	@Override
	public void removeAttachByAttachNo(int attchNo) {
	
		// 삭제할 Attach 정보 가져오기
		AttachDTO attach = uploadMapper.selectAttachByNo(attchNo);  // 삭제 전에 정보를 가져와서 DB에서 attach 정보 삭제하고, 화면에 첨부파일 지우기
		
		// DB에서 삭제
		int result = uploadMapper.deleteAttachByAttachNo(attchNo);
		
		// 첨부파일 삭제
		if(result > 0) {
			
			// 첨부 파일을 File 객체로 만듬
			File file = new File(attach.getPath(), attach.getFilesystem());
			
			// 삭제
			if(file.exists()) {     // file이 존재하면
				file.delete();       // 삭제
			}
		}
		
	}
	
	
	
	
	
	
	
}
