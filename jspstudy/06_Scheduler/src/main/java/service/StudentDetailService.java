package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDao;

public class StudentDetailService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("stuNo"));
		int stuNo = Integer.parseInt(opt.orElse("0"));
		
		// stuNo에 해당하는 Student를 request에 저장하기
		request.setAttribute("student", StudentDao.getInstance().selectStudentByNo(stuNo));  // 학생 한명이니까 student, DAO에서 가져오기
		
		// student/datail.jsp로 포워딩
		return new ActionForward("/student/detail.jsp", false);    // "뷰이름", 포워드
		
	}

}
