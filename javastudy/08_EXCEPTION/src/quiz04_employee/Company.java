package quiz04_employee;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Company {

	
	private Employee[] employees;
	private int idx;
	private Scanner sc;
	
	public Company() {
		employees = new Employee[5];   // 직원은 5명
		sc = new Scanner(System.in);
	}
	
	public void addEmployee() throws EmployeeException { 		// full, 1   	regular/temporary ? 둘은 다르게 입력 받을 것
		if(idx == employees.length) {
			throw new EmployeeException("Full", 1);
		}
		
		System.out.println("사번 >>> ");
		int empNo = sc.nextInt();
		System.out.println("이름 >>> ");
		String name = sc.next();
		
		Employee employee = new Employee(empNo, name);
		employees[idx++] = employee;
		System.out.println("직원 등록이 완료되었습니다. ^*^");
		
	}
	
	public void dropEmployee() throws EmployeeException {		// empty, 2		empNo 일치하면 삭제
		if(idx == 0) {
			throw new EmployeeException("Empty", 2);
		}
		System.out.println("등록 취소할 사번 입력 >>> ");
		int empNo = sc.nextInt();
		for(int i = 0; i < idx; i++) {
			if(empNo == employees[i].getEmpNo()) {
				
				
				System.out.println("사원 번호가 " +  empNo +"인 직원을 등록 취소하였습니다.");
				return;
			}
		}
		
	}
	
	public void findEmployee() throws EmployeeException {		// empty, 2		empNo 입력받아 검색
		if(idx == 0) {
			throw new EmployeeException("Empty", 2);
		}
		System.out.println("♥ 직원 조회 ♥");
		int empNo = sc.nextInt();
		for(int i = 0; i < idx; i++) {
			if(empNo == employees[i].getEmpNo()) {
				System.out.println(employees[i]);
				return;
			}
		}
	}
	
	public void printAllEmployees() throws EmployeeException {	// empty, 2
		if(idx == 0) {
			throw new EmployeeException("Empty", 2);
		}
		System.out.println("※ 전체 직원 목록 ※");
		for(int i = 0; i < idx; i++) {
			System.out.println(employees[i]);
		}
	}
	
	
	public void manage() {
		
		while(true) {
			
			try {
				System.out.println("1.직원등록 2.삭제 3.직원조회 4.전체 목록 0.종료");
				int choice = sc.nextInt();
				switch(choice) {
				case 1: addEmployee(); break;
				case 2: dropEmployee(); break;
				case 3: findEmployee(); break;
				case 4: printAllEmployees(); break;
				case 0: return;
				default : throw new EmployeeException("Bad request", 3);
				}	
			} catch(InputMismatchException e) {
				sc.next();
			} catch(RuntimeException e) {
				System.out.println(e.getMessage());
			} catch(EmployeeException e) {
				System.out.println(e.getMessage() + ", " + e.getErrorCode());
			}
			
		}
	}
}




// pay 정리를 위해 Employee는 abstract 메소드로 바꿈
// getPay();
// salary, temporary에 오버라이드 getPay()
