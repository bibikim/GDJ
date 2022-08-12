package prac1;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Main {

	
	public static void m1() {
	
		// c:\\storage\\prac1.txt 파일을
		// 1글자씩 읽어서
		// c:\\storage\\prac1_copy.txt 파일로 보내기
		// 걸린 시간 출력하기
		
		// FileWriter, FileReader 사용 (copy => 둘 다 쓰게 하는겨)
		// int read() 메소드 사용
		
		// prac1. txt  ---fr.read()--> int c; ---fw.write(c);--> prac1_copy.txt
		// while((c=fr.read()) != -1) {       \ read한걸 c에 저장
		//        fw.write(c);				  \	c를 write
		// }
		
		
		File src = new File("c:\\storage", "prac1.txt");
		File cpy = new File("c:\\storage", "prac1_copy.txt");
		
		FileReader fr = null;
		FileWriter fw = null;
		
		try {
			fr = new FileReader(src);
			fw = new FileWriter(cpy);
			int c;
			
			long begin = System.currentTimeMillis();
			while((c = fr.read()) != -1) {	// 글자가 없을 때까지 read한 다음에
				fw.write(c);	// 읽은 c값을 write 해준다
			}
			long end = System.currentTimeMillis();
			
			System.out.println("복사시간 : " + (end - begin) * 0.001 + "초");
	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) fr.close(); 
				if(fw != null) fw.close();
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

	
	}

	public static void m2() {
		// m1() 메소드 다시 풀기
		
		// 100글자씩 읽어서 보내기
		// BufferedReader, BufferedWriter 사용
		
		File src = new File("c:\\storage", "prac1.txt");
		File cpy = new File("c:\\storage", "prac1_copy.txt");
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try {
			
			br = new BufferedReader(new FileReader(src));
			bw = new BufferedWriter(new FileWriter(cpy));
			
			char[] cbuf = new char[100];
			int readCnt = 0;
			
			long begin = System.currentTimeMillis();
			while((readCnt = br.read(cbuf)) != -1) {
				bw.write(cbuf, 0, readCnt);
			}
			long end = System.currentTimeMillis();
			
			System.out.println("복사시간 : " + (end - begin) * 0.001 + "초");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) bw.close();
				if(br != null) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	
	public static void main(String[] args) {
			m2();
	}
	}
