<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- h t m l:5 지우고 <%%>를 열어두면 자바파일인 것.. j s p만 가지고 전부 다 만들 수 있음! 이걸 Model1이라고 함 --%>

<%
	// 요청 파라미터
	request.setCharacterEncoding("UTF-8");
	String createdDate = request.getParameter("created_date");
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	// 작성자 IP 정보
	String ip = request.getRemoteAddr();
	
	// 파일명
	// 작성자IP_작성자.txt    ip에 콜론이 들어가는데 파일명에 콜론 사용 불가하므로 _로 다 바꿔줘기(replaceAll)
	
	// IPv4
	// 127.0.0.1
	
	// IPv6
	// 0:0:0:0:0:0:0:1
	// 0_0_0_0_0_0_0_1  replaceAll(":", "_")
	String filename = ip.replaceAll(":", "_").replaceAll("\\.", "_")  + "_" + writer + ".txt"; 
	                  // regex: 정규식으로 적어줘야 함. \\ (java에서는 \를 인식시키기 위한 escape용 \추가) or 문자클래스 [] 안에 넣기( [:] )
	
	// 파일 경로
	String realPath = application.getRealPath("storage" + File.separator + createdDate);  // 폴더가 작성한 날짜  
													//  리눅스는 /, 윈도우는 \\ -> 파일구분자를 칭하는 게 다르기 때문에 File.separator로 알아서 갖다 쓰게 하면 됨.
	File dir = new File(realPath);
	if(dir.exists() == false) {
		dir.mkdirs();   // mkdirs 이렇게 해야 폴더 안에 폴더가 들어가는 구조를 만들 수 있다!
	}
	
	// 생성할 파일 객체
	File file = new File(dir, filename);
	
	// 문자 출력 스트림 생성
	BufferedWriter bw = new BufferedWriter(new FileWriter(file));
	bw.write("작성일자 : " + createdDate + "\n");
	bw.write("작성자 : " + writer + "\n");
	bw.write("제목 : " + content + "\n");
	bw.write("내용\n" + content + "\n");
	bw.close();
	
%>

<script>
	// 자바변수는 스크립트에서 사용할 수 있다! (그 반대는 불가)
	var isCreated = <%=file.exists()%>;   // true or false
	if(isCreated) {
		alert('<%=file.getName()%> 파일이 생성되었습니다.');
	} else {
		alert('파일이 생성되지 않았습니다.');
	}
	history.back();   /* alert 창 뜨고 난 후에 작성페이지로 다시 돌아가기 */
</script>