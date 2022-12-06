package com.gdu.app02.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app02.domain.BlogDTO;
import com.gdu.app02.mapper.BlogMapper;
import com.gdu.app02.util.MyFileUtil;
import com.gdu.app02.util.PageUtil;

@Service
public class BlogServiceImpl implements BlogService {

	// @컴포넌트~ 스프링이 뉴 페이지유틸 해서 갖고 있단 의미. 스프링이 페이지유틸 객체를 가지고 있따는 의미
	// 가져다 쓰는게 @오토와이어드  
	private BlogMapper blogMapper;
	private PageUtil pageUtil;
	private MyFileUtil myFileUtil;
	
	@Autowired    // autowired가 적용되는 곳은 아래 매개변수. 매개변수로 주입된다	-> @Autowired를 하나만 쓸 때 이런식으로 작성한다.  
	public void set(BlogMapper blogMapper, PageUtil pageUtil, MyFileUtil myFileUtil) {
		this.blogMapper = blogMapper;
		this.pageUtil = pageUtil;
		this.myFileUtil = myFileUtil;
	}
	
	@Override
	public void getBlogList(Model model) {
		
		// Model에 저장된 request 꺼내기
		Map<String, Object> modelMap = model.asMap();   // model을 map으로 변신
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");  //
		
		// 페이징처리하면 꼭 들어와야하는게 page!!! 
		// 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// 전체 블로그 개수
		int totalRecord = blogMapper.selectBlogListCount();   // 전체 블로그 갯수는 db에서 구해온다.
		
		// 페이징 처리에 필요한 변수 계산
		pageUtil.setPageUtil(page, totalRecord);
		
		// 조회 조건으로 사용할 Map 만들기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin()); // 페이지유틸에 계산 끝났으니까 begin값은 페유틸에 들어있음당
		map.put("end", pageUtil.getEnd());
		
		// 뷰로 전달할 데이터를 model에 저장하기
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("blogList", blogMapper.selectBlogListByMap(map));
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage()); // 순번만들때 필요한거 모델에 실어드릴게
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/blog/list"));    // 완성된 번호 텍스트로 받아서 넘기깅(getPaging()에 경로path만 넘겨주면 다 작성되게 String으로 넘겨줌)
																					// 뭘 누르든 어차피 blogList니까 /blog/list로 가게 한대.. 이해모태모태
	}
	
	
	@Override
	public void saveBlog(HttpServletRequest request, HttpServletResponse response) {
		
		// title, content (파라미터)
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 작성자의 ip
		// 작성된 내용이 어딘가를 경유해서 도착하면 원래 ip가 X-Forwarded-For라는 요청헤더에 저장
		// 출발       경유      도착
		// 1.1.1.1   2.2.2.2    2.2.2.2  : request.getRemoteAddr()
		//                      1.1.1.1  : request.getHeader("X-Forwarded-For")    // 경유지가 없으면 getRemoteAddr();
		
		// 출발       경유      도착
		// 1.1.1.1              1.1.1.1  : request.getRemoteAddr()
		//                      null     : request.getHeader("X-Forwarded-For")    // 경유지가 없으면 getRemoteAddr();
		Optional<String> opt = Optional.ofNullable(request.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(request.getRemoteAddr());  /// header가 null이 나올 수 있어서 optional처리함. 널나오면 널 대신 경유지 없이 ip얻는 getRemoteAddr로
		
		// db로 보낼 blogDTO
		BlogDTO blog = BlogDTO.builder()
				.title(title)
				.content(content)
				.ip(ip)
				.build();
		
		// db에 저장
		int result = blogMapper.insertBlog(blog);
		
		// 응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			if(result > 0) {
				out.println("alert('삽입 성공');");
				out.println("location.href='"+ request.getContextPath() +"/blog/list';");
			} else {
				out.println("alert('삽입 실패');");
				out.println("histroy.back();");
				
			}
			out.println("</script>");
			out.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// img src로 이미지가 들어가면 잡아먹는 용량이 많음..??ㅇ.ㅇ.ㅇ.ㅇ.
		// 첨부처럼 이미지 경로를 잡아주는 것이 좋다. 이미지를 하드에 별도로 저장시키고 저장된 애를 불러와서 경로로 표기해주는 작업을 여름노트가 제공해주고 있음
	}
	
	@Override
	public Map<String, Object> saveSummernoteImage(MultipartHttpServletRequest multipartRequest) {
		// 파라미터 이름 file
		// getParameter는 String 꺼내는거라서 사용불가
		MultipartFile multipartFile = multipartRequest.getFile("file");
		
		// 저장할 파일명
		String filesystem = myFileUtil.getFilename(multipartFile.getOriginalFilename());   // getFilename() 원래이름에서 확장자만 뜯어다쓰고 나머지는 랜덤값으로 바꿔주는 메소드였따
		 
		// 저장 경로  -> c 드라이브 밑에 upload에 저장할궹
		String path = "C:\\upload";  // "C:" + File.separator => 윈도우만 쓰면 경로 이렇게 잡으면 됨.   file.separator경로구분자
		
		// 저장 경로가 없으면 만들기
		File dir = new File(path);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// 저장할 File 객체
		File file = new File(dir, filesystem);    // new File(path, filesystem) : dir 대신 path도 가능
		
		// HDD에 File 객체 저장하기
		try {
			multipartFile.transferTo(file);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 저장된 파일을 확인할 수 있는 매핑을 반환
		Map<String, Object> map = new HashMap<String, Object>();  // jackson을 넣어놨기 때문에 json으로 잘 바꿔주게~
		map.put("src", multipartRequest.getContextPath() + "/load/image/" + filesystem);  // 매핑값이니까,, multipartRequest에서 컨패 가져옴
		return map;
		
		// 저장된 파일이 aaa.jpg라고 가정하면
		//     ${contextPath}/load/image/aaa.jpg 로 넘기겠다요.
		// 여기서의 "src"가 success의 resData에 들어있는 src로 감
	}
	
	@Override
	public int increaseBlogHit(int blogNo) {
		return blogMapper.updateHit(blogNo);
	}
	
	@Override
	public BlogDTO getBlogByNo(int blogNo) {
		
		// 편집하러 갈 때, 상세보기를 함
		
		return blogMapper.selectBlogByNo(blogNo);   // 블넘으로 셀렉블넘 결과를 blog라는 이름으로 model에 실어주기!
		
	}
	
	@Override
	public void modifyBlog(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return ;
	}
	
	@Override
	public void removeBlog(HttpServletRequest request, HttpServletResponse response) {
		int blogNo = Integer.parseInt( request.getParameter("blogNo"));
		int result = blogMapper.deleteBlogByNo(blogNo);
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			if(result > 0) {
				out.println("alert('게시글이 삭제 되었습니다.')");
				out.println("location.href='" + request.getContextPath() +"/blog/list';");
			} else {
				out.println("alert('게시글이 삭제되지 않았습니다.')");
				out.println("history.back();");
			}
			
			out.println("</script>");
			out.close();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
