package com.gdu.app14.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
		//System.out.println(files);
		
		
		
		
		// 첨부 결과
		int attachResult = 0;
		if(files.get(0).getSize() == 0) {
			
		}
		
		// 첨부된 파일 목록 순회(하나씩 저장)
		for(MultipartFile multipartFile : files) {     // 파일첨부 갯수만큼 반복문이 돈다. 
			
			try {
				
				// 첨부가 있는지 점검
				if(multipartFile != null && multipartFile.isEmpty() == false) {   // 둘다 필요
					
					// 원래 이름   - 다운로드 시 원래 원래이름으로 다운로드 되게 원래이름도 db에 넣어준다
					String origin = multipartFile.getOriginalFilename();
					origin = origin.substring(origin.lastIndexOf("\\") + 1);  // IE는 오리지널네임에 전체 경로가 붙어서 파일명만 사용해야 함. -> 앞에 경로는 버리고 마지막 것만 쓰겠다
					
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
				out.println("location.href='" + multipartRequest.getContextPath() + "/upload/list");
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
	
}
