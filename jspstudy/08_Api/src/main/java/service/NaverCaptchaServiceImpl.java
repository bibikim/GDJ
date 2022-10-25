package service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class NaverCaptchaServiceImpl implements NaverCaptchaService {
	
	
	// 서비스 구현체들의 이름은 Impl로 끝나는 것이 관례
	
	
	// 필드
	private final String CLIENT_ID = "DJxqiwv2wqFur2KBtCxx";
	private final String CLIENT_SECRET = "nq6dLkY86Z";
	
	
	@Override
	public String getCaptchaKey() {
		
		// code=0 : "키 발급", code=1 : "사용자 입력값 검증" 
		String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=0";  // key발급 요청할 때는 code 파라미터를 0으로 설정
		
		// 반환할 key값(네이버API가 제공하는 캡차키)
		String key = null;
		
		try {
			
			// apiURL 접속
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 요청 메소드(HTTP 메소드)
			con.setRequestMethod("GET");  // 대문자로 작성할 것
			
			// 요청 헤더 : 클라이언트 ID, 클라이언트 SECRET 추가
			con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
			con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
			
			// 입력 스트림 선택 및 생성(네이버 API서버의 정보를 읽기 위함)
			BufferedReader reader = null;
			if(con.getResponseCode() == 200) {    // 응답 코드 값이 200 : HttpURLConnection.HTTP_OK 이면
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			// 네이버 API서버가 보낸 데이터 저장
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line); 							 // sb 안에 있는거 : {"key":"@@@@"}
			}
			
			// 네이버 API서버가 보낸 데이터 확인 및 반환 (sb에 들어있는 key값 꺼내오기)
			JSONObject obj = new JSONObject(sb.toString()); 
			key = obj.getString("key");		// getString으로 key값 꺼내오기!
			
			// 자원 반납
			reader.close();
			con.disconnect();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

		return key;
	}

	@Override
	public Map<String, String> getCaptchaImage(HttpServletRequest request, String key) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		String apiURL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin?key=" + key;   // 키하고 이미지하고 요청url 다름, key를 파라미터로 보내야 함
		
		try {
			
			// apiURL 접속
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 요청 메소드(HTTP 메소드)
			con.setRequestMethod("GET");  // 대문자로 작성할 것
			
			// 요청 헤더 : 클라이언트 ID, 클라이언트 SECRET 추가
			con.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
			con.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);
			
			// 입력 스트림 선택 및 생성(네이버 API서버의 정보를 읽기 위함)
			// 텍스트가 아닌 JPG 확장자의 이미지를 받아와야 함(getChaptchaImage)

			// 응답이 성공하면 이미지(JPG)가 응답
			if(con.getResponseCode() == 200) {    // 응답 코드 값이 200 : HttpURLConnection.HTTP_OK 이면
				// 저장할 캡차이미지 경로.     - 이미지 다운로드 경로(realPath로 저장)
				String dirname = "ncaptcha";
				
				String realPath = request.getServletContext().getRealPath(dirname);   // dirname(=ncapcha)라는 폴더를 만들어서 거기다 저장!
				File dir = new File(realPath);  // 이 경로의 정보를 dir라는 파일 객체를 만든다
				if(dir.exists() == false) {  // 존재하지 않으면
					dir.mkdirs();			 // 만들겠다
				}
				// 저장할 캡차이미지 이름      - 이미지 보관. filename은 timestamp 값을 쓰자
				String filename = System.currentTimeMillis() + ".jpg";
				// 저장할 캡차이미지 객체 생성
				File file = new File(dir, filename);
				// 네이버API로부터 정보를 읽어서(in) 서버경로에 저장(out)
				BufferedInputStream in = new BufferedInputStream(con.getInputStream()); // 이미지같은 것들은 바이트 기반의 스트림으로 받아와야 정상적인 처리 가능. 따라서 BufferedInputStream으로 읽어온다
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));  // 입력input-복사, 출력output-저장
				// 저장(다운로드)
				byte[] b = new byte[1024];
				int readByte = 0;
				while((readByte = in.read(b)) != -1) {   // read 다~ 하면 -1 반환 
					out.write(b, 0, readByte);   // 읽어들인 만큼 보내는 메소드
				}
				// login.jsp로 전달할 데이터(캡차이미지 경로 + 파일명)
				map.put("dirname", dirname);
				map.put("filename", filename);
				
				
				// 자원 반납
				out.close();
				in.close();
			} else {
				// 응답이 실패하면 text형식으로 응답이 옴. 따라서 Reader 사용
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));  // 실패할 때는 이미지가 아니라 실패 메시지가 뜨게끔.
				StringBuilder sb = new StringBuilder();
				String line;
				while((line = reader.readLine()) != null) {
					sb.append(line); 							 // sb 안에 있는거 : {"key":"@@@@"}
				}
				System.out.println("응답 실패 사유");
				System.out.println(" : " + sb.toString());
				// 자원 반납
				reader.close();
			}
			
			
			// 자원 반납
			con.disconnect();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return map;

	}
	
	@Override
	public void refreshCaptcha(HttpServletRequest request, HttpServletResponse response) {
		// 응답 데이터 형식: JSON
		response.setContentType("application/json");
		
		// 응답 데이터
		// 캡차 키 + 캡차 이미지 새로 요청해서 JSON 생성
		/*
		 	{
		 		"dirname": "ncaptcha",
		 		"filename": "1111111.jpg"
		 	}
		*/
		String key = getCaptchaKey();
		Map<String, String> map = getCaptchaImage(request, key);    // 캡차이미지를 request를 통해 불러야함 ==> 즉, 캡차 키/캡차 이미지 새로 요청
		JSONObject obj = new JSONObject(map);    // json 받아올 때 map을 전달히기 가능
		
		// 응답
		try {
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean validateUserInput(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

}
