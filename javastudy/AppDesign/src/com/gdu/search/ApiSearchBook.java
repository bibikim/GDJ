package com.gdu.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiSearchBook {

	public static void main(String[] args) {
		
		String clientId= "DJxqiwv2wqFur2KBtCxx";
		String clientSecret= "nq6dLkY86Z";
		Scanner sc = new Scanner(System.in, "UTF-8");
		
		System.out.println("도서를 검색하세요 > ");
		

		try {
		
			// 책관련검색어입력받아보기 (Scanner sc  OR  JOption.showInputDialog)
			// 검색어를 입력받아서 string으로 저장시키기
			// 검색어는 입력받아서 utf-8로 인코딩 (URLEncoder.encode)
			//System.out.println(book);
			String str = sc.next();
			String book = URLEncoder.encode(str, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/book?query=" + book;
			
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			
			BufferedReader br = null;
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;  // 한줄씩 읽어들이기 위함,, readLine
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			sc.close();
			con.disconnect();
			
			// 검색결과 sb에 저장되어 있음  sb => {key:value, ... }  json데이터가 들어있음. -> json라이브러리 추가해서 JSONObject로 바꿔서 값 꺼내보기
			
			JSONObject obj = new JSONObject(sb.toString());   // 스트링으로 넣을 수 있는 걸 괄호 안에. 그래야 분석해서 파싱을 해준다잉
			JSONArray items  = obj.getJSONArray("items"); 
			for(int i = 0; i < items.length(); i++) {
				JSONObject element = items.getJSONObject(i);
				System.out.println(element);
				String title = element.getString("title");
				String link = element.getString("link");
				String image = element.getString("image");
				System.out.println(image);
			
			
			
			//System.out.println(obj);
			
			File dir = new File("C:\\download");   // 경로적어
			if(dir.exists() == false) {
				dir.mkdirs();
			}
			File file = new File(dir, book + ".html"); // 이 파일로 데이터를 보내는게 하고 싶은거임.. writer 사용
			PrintWriter out = new PrintWriter(file);
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"UTF-8\">");
			out.println("<title>도서 검색</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<a href=\"" + link + ">" + title + "</a>");
			out.println("<br>");
			out.println("<img src=\"" + image + "\">");
			out.println("</body>");
			out.println("</html>");
			out.close();
			
			}
			//System.out.println(obj);
			// 제목 + 제목에 a링크 + hr + 반복~~           -> 검색결과 10개 나옴/ 10개의 데이터를 반복문 사용해서 제목/링크/이미지를 화면(<body></body> 내부)에 뿌리기.
			
		} catch(Exception e) {
			
			try {
				File dir = new File("C:\\download\\log");
				if(dir.exists() == false) {
					dir.mkdirs();
				}
				File file = new File(dir, "error_log.txt");
				
				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd a h:mm:ss");
				String errortime = fmt.format(date);
				
				PrintWriter out = new PrintWriter(file);
				out.println("예외메시지     " + e.getMessage());   // 만들라는거 만들고 넣고 해라~
				out.println("예외발생시간   " + errortime);
				out.close();
				
			} catch(Exception e2) {
				e2.printStackTrace();
			}
			
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
