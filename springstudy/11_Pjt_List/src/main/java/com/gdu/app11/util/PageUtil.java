package com.gdu.app11.util;

import org.springframework.stereotype.Component;

import lombok.Getter;


/* Bean으로 등록하기~~~~~
 * 1. 루트컨텍스트파일에
 * 2. 자바 컨피그에 만들기
 * 3. 애너테이션 컴포넌트 붙이기  - @Component, @Service, @Repository, @
 * 스프링컨테이너가 싱글턴패턴으로 하나 만들어서 페이지유틸을 빈으로 등록!
*/

@Component
@Getter
public class PageUtil {
	
	// page와 totalRecord는 외부에서 값을 받아와야 함.
	private int page;   // 현재 페이지(파라미터로 받아온다.)
	private int totalRecord;   // 전체 레코드 개수(DB에서 구해온다.)
	private int recordPerPage = 10;   // 페이지에 표시할 레코드 개수(임의로 정한다) -> 한 페이지당 10개의 record
	
	// └> 위에 세개로 begin과 end값 구함
	private int begin;	 // 가져올 목록의 시작 번호(계산한다)
	private int end;	 // 가져올 목록의 끝 번호(계산)
	
	private int totalPage;  // 전체 페이지 개수(계산한다)
	private int pagePerBlock = 5;  // 블록에 표시할 페이지 개수(임의로 정한다)
	private int beginPage;	// 블록의 시작 페이지 번호(계산한다)
	private int endPage;	// 블록의 끝 페이지 번호(계산한다)
	
	public void setPageUtil(int page, int totalRecord) { // pageUtil의 정보를 채워주겠다. page와 totalRecord 값은 외부에서 받아오므로 매개변수로.
		
		// page, totalRecord 필드 저장
		this.page = page;
		this.totalRecord = totalRecord;
		
		// begin과 end를 이용한 계산   -> 하나의 객체에 모아서 관리해줄 것!
		begin = (page - 1) * recordPerPage + 1;
		end = begin + recordPerPage - 1;  // 마지막 페이지 : totalRecord(54)보다 작은 값을 실제 end로
		if(end > totalRecord) {
			end = totalRecord; // end가 totalRecord보다 크면 end와 totalRecord를 똑같게.
		}
		
		
		// totalPage 계산 (실제 전체 페이지 개수)
		totalPage = totalRecord / recordPerPage;
		if(totalRecord % recordPerPage != 0) {  // 나누어 떨어지지 않았으면
			totalPage++;		// totalPage를 하나 더 주겠다.
		}
		
		
		// beginPage, endPage 계산  -> 한 블락 안에 몇 페이지(beginPage 1)에서 몇 페이지(endPage 5)까지 
		beginPage = ((page -1) / pagePerBlock) * pagePerBlock + 1;
		endPage = beginPage + pagePerBlock - 1;
		if(endPage > totalPage) {	// 계산된 endPage가 실제 totalPage보다 크면
			endPage = totalPage;    // endPage를 totalPage와 같은 값으로 해라.
		}
		
	}
		
		public String getPaging(String path) {    // ${contextPath}/emp/list 경로는 변수처리(path) -> 그래야 여기저기서 사용 가능
			// └> 이렇게 만들어 두면, getPaging()만 갖다놓고 사용할 수 있으니까!
			
			StringBuilder sb = new StringBuilder();
			
			// 이전블록 : 1block이 아니면 이전블록이 있다. 즉 beginPage가 1이 아니면~
			if(beginPage != 1) {
				/*
				 	<a href="path?page=beginPage-1">◀</a>
				*/
				sb.append("<a href=\"" + path + "?page=" + (beginPage - 1) + "\">◀</a>");     // href의 ""를 사용하기 위해서 \를 꼭 붙여준다!
			}
			
			// 페이지번호 : 현재 페이지는 링크가 없다
			for(int p = beginPage; p <= endPage; p++) {
				if(p == page) {   	// p가 현재 페이지 넘버와 같으면
					sb.append(p);	// 현재 페이지 번호 보여주고 끝!
				} else {
					sb.append("<a href=\"" + path + "?page=" + p + "\">" + p +"</a>");
				}
			}
			// 다음블록 : 마지막 블록이 아니면 다음블록이 있다 (endPage != totalPage -> endPage+1)
			if(endPage != totalPage) {
				
				/*
			 		<a href="path?page=endPage+1">▶</a>
				*/
				
				sb.append("<a href=\"" + path + "?page=" + (endPage + 1) + "\">▶</a>");
			}
	
			return sb.toString();
		}

}
