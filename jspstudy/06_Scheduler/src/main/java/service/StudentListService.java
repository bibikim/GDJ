package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDao;

public class StudentListService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		StudentDao dao = StudentDao.getInstance();
		
		// request에 필요한 정보 저장하기
		request.setAttribute("students", dao.selectAllStudents());
		request.setAttribute("count", dao.selectAllStudentsCount());
		request.setAttribute("average", dao.selectAllStudentsAverage());
		
		// student/list.jsp로 포워딩
		// 이전처럼 ActionForward 따로 선언 안하고 바로 반환해벌이기
		return new ActionForward("/student/list.jsp", false);    // view, 포워드
	}

}
