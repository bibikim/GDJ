package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Student;
import repository.StudentDao;

public class StudentTest {

	/*
	 	순서대로 전체 테스트 돌리기
	 	1. 데이터 삽입
	 		: 테스터, 50, 50, 50
	 	2. 목록
	 	3. 상세
	 	4. 수정
	 		: 테스터2, 60, 60, 60
	 	5. 삭제
	 		: 테스터2 삭제
	*/
	
	// 실행에 실패하면 데이터베이스 초기화(즉 drop테이블이랑 다 해놓고 다시 세팅해서 실행해보기
	// 한번에 모든 테스트를 실행하기 보다는 후에 실행하고 싶은 부분은 주석처리 해가면서 단계적으로 테스트하는 것이 좋겠다~!~!
	/* 평가 때 JUnit 나온다~~~~ */
	
	// @BeforeClass
	// StudentTest 클래스 실행 시 한 번만 마지막으로 실행
	// static 처리 필요
	@BeforeClass
	public static void 삽입테스트() {
		Student student = new Student();
		student.setName("테스터");
		student.setKor(50);
		student.setEng(50);
		student.setMath(50);
		student.setAve(50);
		student.setGrade("F");
		
		int result = StudentDao.getInstance().insertStudent(student);
		assertEquals(1, result);
	}
	// @Before는 @Test 어노테이션이 붙은 메소드 보다 먼저 실행
	// 그냥 before로 하면 before가 test전에 도는거라 밑에 test로 한 애들하고 같이 돌아서 3번 돌게 됨 -> 테스트 실패
	// 따라서 한 번만 돌라고 static 처리하는 것  --> 따라서 @before가 아닌 @beforeClass로 애너테이션!
	// BeforeClass 는 static method에만 사용할 수 있다.
	
	
	
	@Test
	public void 목록테스트() {
		List<Student> students = StudentDao.getInstance().selectAllStudents();
		assertEquals(6, students.size());
		// or
		// assertEquals(6, StudentDao.getInstance().selectAllStudents().size());
		
	}
	
	@Test
	public void 상세테스트() {
		Student student = StudentDao.getInstance().selectStudentByNo(8);
		assertNotNull(student);
		
		// assertNotNull(StudentDao.getInstance().selectStudentByNo(8));
	}
	
	@Test
	public void 수정테스트() {
		Student student = new Student();
		student.setName("테스터2");
		student.setKor(60);
		student.setEng(60);
		student.setMath(60);
		student.setAve(60);
		student.setGrade("D");
		student.setStuNo(8);
		int result = StudentDao.getInstance().updateStudent(student);
		assertEquals(1, result);
		
		// assertEquals(1, StudentDao.getInstance().updateStudent(student))
	}
	
	
	
	// @AfterClass
	// StudentTest 클래스 실행 시 한 번만 마지막으로 실행
	// static 처리 필요
	@AfterClass
	public static void 삭제테스트() {
		int result = StudentDao.getInstance().deleteStudent(8);
		assertEquals(1, result);
		
		// assertEquals(1, StudentDao.getInstance().deleteStudent(8));
	}
	

}
