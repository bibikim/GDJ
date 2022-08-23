package ex03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class Main {

   public static void main(String[] args) {
try {
               
                String apiURL="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1154551000"; // 주소자체에 파라미터가 붙어있음 "?"
                  
                URL url =new URL(apiURL);
                HttpURLConnection con=(HttpURLConnection)url.openConnection();
                  
                  
                  // 안적어도 지장 없음
                  // con.setRequestMethod("GET");
                  // con.setRequsetProperty("Content-Type","application/xml; charset=UTF-8");
                  
                StringBuilder sb= new StringBuilder();
                BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line=null;
                
                
                while((line=br.readLine()) != null) {
                   sb.append(line);
                  
                }
                
                // 파싱 결과를 저장할 파일
                File file=new File("test.txt");   // javastudy Re03 프젝 폴더 안에 파일 생긴다.
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));   // writer를 쓰면 없는 파일도 만들어주기 때문에 생긴다
                
                // StringBuilder에 저장된 응답(XML) 데이터를 JSON으로 변경하기
                JSONObject obj = XML.toJSONObject(sb.toString());
                JSONObject rss = obj.getJSONObject("rss");
                JSONObject channel = rss.getJSONObject("channel");
                
                // 위에 3줄 코드를 1줄로!
                // JSONObject channel = XML.toJSONObject(sb.toString()).getJSONObject("rss").getJSONObject("channel");
                
             
                String link = channel.getString("link");
                String description = channel.getString("description");
                String generator = channel.getString("generator");
                String language = channel.getString("language");
                String title = channel.getString("title");
                String pubDate = channel.getString("pubDate");           // channel로 뽑아낼 수 있는 데이터 전체 파싱
                bw.write(pubDate + "\n");
                
                JSONObject item = channel.getJSONObject("item");
                String author = item.getString("author");
                String link2 = item.getString("link");
                String category = item.getString("category");
                bw.write(category + "\n");
                String title2 = item.getString("title");
                
                JSONObject description2 = item.getJSONObject("description");
                JSONObject body = description2.getJSONObject("body");
                JSONArray dataList = body.getJSONArray("data");
                for(int i=0; i < dataList.length(); i++) {
                	JSONObject data = dataList.getJSONObject(i);
                	
                	int sky = data.getInt("sky");
                	int temp = data.getInt("temp");
                	String wfKor = data.getString("wfKor");
                	int wd = data.getInt("wd");
                	int hour = data.getInt("hour");
                	
                	bw.write(hour + "시, " + temp + "도, " + wfKor + "\n");
                	
                	// 					 JSONObject a = obj.getJSONObject("a")
                	//                              b = a.getJSONObject("b")
                	//                              c = a.getJSONObject("c")
                	//                              d = a.getJSONObject("d")
                	//------------------------------------------------------
                	//                   JSONObject d = obj.getJSONObject("a")
                	//                						.getJSONObject("b")
                	//                    					.getJSONObject("c")
                	//                  					.getJSONObject("d");   메소드 체이닝을 잘 이용하자
                	
                }
                // try 블록 끝나기 전에 close()
                bw.close();
                br.close();
                con.disconnect();
         
            }catch(Exception e) {
               e.printStackTrace();
            }
         }
      }
      