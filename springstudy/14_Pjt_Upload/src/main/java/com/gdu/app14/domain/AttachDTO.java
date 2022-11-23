package com.gdu.app14.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AttachDTO {
	private int attachNo;
	private String path;
	private String origin;
	private String filesystem;
	private int downloadCnt;
	private int uploadNo;
	
	
	/*
	 	첨부파일 확장자 제한 걸기 - 실행파일은 못 올리게 막아주는 것이 좋다 exe, bat
	 	모두 다운로드 - 한번에 다운로드 => 압축해서 다운로드. 매핑 downloadAll 보면 됨.
	 	첨부파일이 서버에 많이 쌓이니까 스케줄러 넣음 -> 모두 다운로드(temp폴더)  사용자는 ㄷ ㅏ가져간 파일인데 서버에 남아있는 것임 -> batch로 지우기(deleteTemplateFiles)
		해당경로의 파일들 가져오기 -> 디렉터리 지정해주면 해당 디렉터리의 모든ㄷ 파일을 가져오는 listFiles -> for문 돌려서 하나씩 지우기
		
		타겟 ==> 어제 업로드 된 애들. deleteWrongFils 배치 - 하루에 한번씩 돌리는 거라 어제거만 정리해줌
		경로  sysdate-1(어제)
		     cancat 'storage/2022/11/어제일'  -> 어제 업로드 된 애들다 가져와서 list로 가져옴(파일목록) , 
		     dbList는 디비에 업로드 됏다고 기록된 애들
		     실제로 파일을 해당경로와 연결해서  올라온 애들을 비교해서 데이터 읽어들임
		     
		     잘못들어가있는 파일(디비에 없는 파일들) 걸러내기-
		     FilenameFilter를 익명 구현체로 직접 넣어서 만듦
		     return ! 디렉터리에 들어가있는 이름, 실제로 드러가있는 파일이     포함되어있지 않은 것.
	*/
	
	
	/*
	 * 
	 * 14장 - 상세보기와 리스트 모두 모델에 담아서 상세보기로 가져갔었음
	 * 
	 * 15장 - 상세보기는 forward, 댓글목록은 ajax
	 * 
	 * reapath
	 * */
	
}
