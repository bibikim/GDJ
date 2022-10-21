package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Student;
import repository.StudentDao;

public class StudentModifyService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// edit + modify 한번에 만들거라 editservice 안 만듦
		
		// 요청 파라미터
		int stuNo = Integer.parseInt(request.getParameter("stuNo"));
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		
		// 파생값
		double ave = (kor + eng + math) / 3.0;  // 연산에 double타입이 있으면 알아서 나눔 그냥3이면 나누기 안됨! 소숫점 다 떼고 나옴
		String grade;
		switch((int)(ave / 10)) {
		case 10: case 9: grade = "A"; break;
		case 8: grade = "B"; break;
		case 7: grade = "C"; break;
		case 6: grade = "D"; break;
		default: grade= "F";
		}
		
		
		// DB로 보낼 Student student 생성
		Student student = Student.builder()
				.stuNo(stuNo)
				.name(name)
				.kor(kor)
				.eng(eng)
				.math(math)
				.ave(ave)
				.grade(grade)
				.build();
		
		// DB로 Student student 보내기(수정한 후의 데이터)
		int result = StudentDao.getInstance().updateStudent(student);
				
				// 삽입 성공 / 실패
				PrintWriter out = response.getWriter();
				if(result > 0) {
					out.println("<script>");
					out.println("alert('학생 정보가 수정되었습니다.')");
					out.println("location.href='" + request.getContextPath() + "/student/detail.do?stuNo=" + stuNo + "'");   // 상세보기에서 항상 필요한건 PK전달
					out.println("</script>");
				} else {
					out.println("<script>");
					out.println("alert('신규 학생 등록이 실패하였습니다.')");
					out.println("history.back()");
					out.println("</script>");
				}
				out.close();
				return null;  // Service를 통해서 직접 응답했기 때문에 컨트롤러로 null을 반환
							  // 컨트롤러가 null을 반환받으면 리다리엑트/포워드 모두 수행하지 않는다.

	}

}
