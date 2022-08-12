package prac2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

public class Main {
	
	public static void q1() {
		
		// eclipse-jee-2021-03-R-win32-x86_64.zip 복사하기
		// 복사할 파일명은 eclipse.zip
		
		File org = new File("c:\\GDJ\\installer", "eclipse-jee-2021-03-R-win32-x86_64.zip");
		File copy = new File("c:\\GDJ\\installer", "eclipse.zip");
		
		//BufferedInputStream bis = null;
		//BufferedOutputStream bos = null;
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			
			fis = new FileInputStream(org);
			fos = new FileOutputStream(copy);
			
			byte[] b = new byte[1024];  // 1KB
			int readByte = 0;
			
			while((readByte = fis.read(b)) != -1) {
				fos.write(b, 0, readByte);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null)
					fis.close();
				if(fos != null)
					fos.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// org ----- bis(fis) : read(b); ----▶ □ b(1024바이트만큼 읽어옴)□ ----- bos(fos) : bos.write(b, 0, readByte); ----▶ copy
	
	
	public static void pq1() {
		
		// buffered 끼우기
		
		File file1 = new File("C:\\GDJ\\javaNote", "HashCode.txt");
		File file2 = new File("D:", "hashcodecpy.txt");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			
			bis = new BufferedInputStream(new FileInputStream(file1));
			bos = new BufferedOutputStream(new FileOutputStream(file2));
			
			byte[] b = new byte[100];
			int readByte = 0;
			
			while((readByte = bis.read(b)) != -1) {
				bos.write(b, 0, readByte);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(bis != null) bis.close();
				if(bos != null) bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	

	
	public static void q2() {
		
		// eclipse.zip 파일을 c:\storage로 이동시키기(복사하고 지우면 된다!)
		
		File dir = new File("c:\\storage");	 // 목적지
		
		File origin = new File("c:\\GDJ\\installer", "eclipse.zip");
		File dest = new File(dir, origin.getName());	//origin.getName() = origin이랑 같은 이름쓰겠다
		if(dir.exists() == false) {	
			dir.mkdirs();	// 폴더가 없으면 만들게용
		}
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos= null;
		
		try {
			
			bis = new BufferedInputStream(new FileInputStream(origin));
			bos = new BufferedOutputStream(new FileOutputStream(dest));
			
			byte[] b = new byte[1024];
			int readByte = 0;
			
			while((readByte = bis.read(b)) != -1) {
					bos.write(b, 0, readByte);
			}
			
			bos.close();
			bis.close();
			
			// 원본과 복사본의 크기가 동일하면 원본을 제거
			if(origin.length() == dest.length()) {
				origin.deleteOnExit();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// finally 필요 없다 위에서 닫아줬기 때문!!
	}
	
	
	public static void pq2() {
		
		// hashcodecpy.txt 파일을 c:로 이동시키기
		
		File dir = new File("d:\\test");
		
		File src = new File("d:", "hashcodecpy.txt");
		File dst = new File(dir, src.getName());
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			
			bis = new BufferedInputStream(new FileInputStream(src));
			bos = new BufferedOutputStream(new FileOutputStream(dst));
			
			byte[] b = new byte[50];
			int readByte = 0;
			while((readByte = bis.read(b)) != -1) {
				bos.write(b, 0, readByte);
			}
			
			bis.close();
			bos.close();
			
			if(src.length() == dst.length()) {
				src.deleteOnExit();
			}
	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static void main(String[] args) {
		pq2();

	}

}
