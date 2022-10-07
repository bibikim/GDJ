package ex01_file;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class Main {

	public static void m1() {
		
		// File 클래스
		// 1. 패키지 : java.io
		// 2. 파일 및 디렉터리 관리 (파일도 폴더도 file클래스가 관리한단 뜻)
		// 3. 생성 방법
		//   1) new File(경로, 파일)  >> 분리해서 등록
		//   2) new File(파일)만 지정 >> 해당 경로로 가 있는 경우
		// 4. Window의 경로 구분 방법 : 백슬래시(\)
		// 5. 리눅스의 경로 구분 방법 : 슬래시(/)
		
		// 폴더(디렉터리) 만들기
		File dir = new File("C:\\storage");	// file import 해줭 확장자 없으니까 file아니고 폴더!
					
		// 존재하지 않으면 만들겠다.
		if(dir.exists() == false) {		// if(!dir.exists())   = 존재하지 않으면(false)
			dir.mkdirs();				
		}
		// 존재하면 삭제하겠다.
		else {
		//	dir.delete();	// 지금 지운다.
			dir.deleteOnExit();		// JVM이 종료되면 지운다.
		}
		
	}
	
	public static void m2() {
		
		File file = new File("C:\\storage", "my.txt");
		
		try {
			if(file.exists() == false) {
				file.createNewFile();		// IOException -> checked exception이라 try-catch를 해야 실행해준다.
			} 
			else {
				file.delete();
			}
		} catch(IOException e) {
			// 개발할 때 넣는 catch 블록 코드
			e.printStackTrace();	// 에러를 콘솔에 찍어라. 문제가 발생하면 에러코드를 찍어줌
		}
	}
	
	public static void m3() {
		
		File file = new File("C:\\storage", "my.txt");
		
		System.out.println("파일명 " + file.getName());
		System.out.println("경로 " + file.getParent());	// 폴더 - 부모, 파일 - 자식 계층관계로 보기 때문에 parent
		System.out.println("전체경로(경로+파일명) " + file.getPath());
		
		System.out.println("디렉터리인가? " + file.isDirectory());	// 대상은 my.txt니까 false
		System.out.println("파일인가? " + file.isFile());	// true
		
		long lastModifiedDate = file.lastModified();	// 날짜 시간을 왜 long타입?
		System.out.println("수정한 날짜 " + lastModifiedDate);	// timeStamp 값으로 반환하기 때문에!
		
		//Date date = new Date();
		String lastModified = new SimpleDateFormat("a hh:mm yyyy-MM-dd").format(lastModifiedDate);
		    												// 객체 생성, 메소드 호출 한번에!		
		System.out.println("수정한 날짜 " + lastModified);
		
		long size = file.length();	// 바이트 단위
		System.out.println("파일 크기 " + size + "byte");	// 영문 한 글자는 1byte, 한글은 2~3byte
	}
		
	public static void m4() {
		
		File dir = new File("C:\\GDJ");	// GDJ 안에 폴더와 파일 하나하나가 다 File 객체 -> File[] 배열 필요
		
		File[] list = dir.listFiles();	// 디렉터리 내부에 있는 모든 폴더와 파일을 File 객체로 가져옴
		for(int i = 0; i < list.length; i++) {		// 배열의 이름(list) + 길이(.length) 
			System.out.println(list[i].getName());	// lit[i] = 파일 하나. getName = 파일 이름 가져오기
		}
	}
	
	public static void m5() {
		
		// 플랫폼마다 다른 경로 구분자 지원
		File file = new File("C:" + File.separator + "storage" + File.separator + "my.txt");
		System.out.println(file.getName());
		
		
	}
	
	
	public static void q1() {
		
		 File dir = new File("C:\\GDJ");
		 File[] list = dir.listFiles();
		 
		 int dirCnt = 0;
		 int fileCnt = 0;
		 long totalSize = 0;
		 
		 for(File file : list) {
			 if(file.isHidden()) {
				 continue;				// 숨김파일이면 아무것도 안하고 계속 진행할게여
			 }
			 String lastModified = new SimpleDateFormat("yyyy-MM-dd  a hh:mm").format(file.lastModified());
			 String directory = "";
			 String size = "";
			 if(file.isDirectory()) {
				 directory = "<DIR>";
				 size = "     ";
				 dirCnt++;
			 } else if(file.isFile()) {
				 directory = "     ";
				 size = new DecimalFormat("#,##0").format(file.length()) + "";	// decimalformat으로 ,가 들어갔기 때문에 파스롱에서 ,를 빈문자열로 바꿔줘야함
				 fileCnt++;
				 totalSize += Long.parseLong(size.replace(",", "")); // ,를 찾아서 빈 문자열로 바꿔줌. replace!!
				 // String size를 long타입으로 변환해서 +=
			 }
			 // String size = file.isFile() ? file.length() + "" : "     ";
			 //String size = file.isFile() ? new DecimalFormat("#,##0").format(file.length()) + "" : "     ";
			 String name = file.getName();
			 System.out.println(lastModified + " " + directory + " " + size + " " + name);
		 }
		 System.out.println(dirCnt + "개 디렉터리 ");
		 System.out.println(fileCnt + "개 파일 " + new DecimalFormat("#,##0").format(totalSize));

			 
		
		
		
		
	}
	
	public static void q2() {
		
		// C:\storage 디렉터리 삭제하기
		// 디렉터리가 비어있어야 삭제할 수 있으므로 내부 파일을 먼저 삭제
		
		String sep = File.separator;
		
		File file = new File("c:" + sep + "storage", "my.txt");
		if(file.exists()) {
			file.delete();
		}
		
		File dir = new File("C:" + sep + "storage");
		if(dir.exists()) {
			dir.delete();
		} 
		// 안에 파일부터 먼저 제거 후에 디렉터리(폴더) 삭제 가능
		// 내부 파일이 여러 개일 경우에는 배열을 써서 for문 안에서 delete를 하면 된다.
	}
	
	
	public static void main(String[] args) {
		q2();

	}

}
