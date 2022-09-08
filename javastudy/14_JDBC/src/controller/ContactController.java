package controller;

import java.util.Scanner;

import domain.ContactDTO;
import service.ContactService;
import service.ContactServiceImpl;

public class ContactController {
	/********************* Field **************************/
	
	// 입력을 위한 Scanner 클래스
	// ContactService 호출을 담당
	private Scanner sc;
	private ContactService contactService;  
	
	/******************* Constructor **********************/
	// 인터페이스는 new 방식으로 못 부름    impl은 자식. 업캐스팅~
	public ContactController() {
		sc = new Scanner(System.in);
		contactService = new ContactServiceImpl();
		
	}
	
	/********************* Method **************************/
	
	public void menu() {
		
		System.out.println("1.연락처등록");
		System.out.println("2.연락처수정");
		System.out.println("3.연락처삭제");
		System.out.println("4.연락처조회");
		System.out.println("5.전체연락처");
		System.out.println("0.종료");
		
	}
	                        // ~전달 과정~
	public void play() {    // play가 호출되면 menu(1~5)를 보여주고 선택>> 입력받고, 1이면 3개 정보를 하나로 받아서 contact에 받고 그걸 addContact에 준다
							// 그럼 그 정보가 Impl의 ContactDTO contact에 도착(insertContact에 들어가고). 그 도착한 contact이 다시 출발해서 DAO클래스의 ContactDTO contact로 도착
							// 그렇게 DB에 넣고 0 or 1을 받아서 impl에서 if처리해서 연락처 등록유무 결과가 나온다.
		int contact_no;
		String name;
		String tel;
		String email;
		ContactDTO contact;
		
		
		while(true) {
			menu();
			System.out.println("선택(1~5, 0) >>> ");
			int choice = sc.nextInt();       // 숫자는 choice에 저장
			sc.nextLine();					 // 숫자 입력하고 누른 엔터 처리
			switch(choice) {
			case 1:    // 연락처 정보 입력받기, addContact를 불러주기
				System.out.println("신규 연락처 이름 >>> ");
				name = sc.next();
				System.out.println("신규 연락처 전화번호 >>> ");
				tel = sc.next();
				System.out.println("신규 연락처 이메일 >>> ");
				email = sc.next();
				contact = new ContactDTO();
				contact.setName(name);
				contact.setTel(tel);
				contact.setEmail(email);
				// addContact에 전달
				contactService.addContact(contact);
				break;
			case 2: 
				System.out.println("수정할 연락처 번호 >>> ");
				contact_no = sc.nextInt();
				System.out.println("수정할 연락처 이름 >>> ");
				name = sc.next();
				System.out.println("수정할 연락처 전화번호 >>> ");
				tel = sc.next();
				System.out.println("수정할 연락처 이메일 >>> ");
				email = sc.next();
				contact = new ContactDTO();
				contact.setContact_no(contact_no);
				contact.setTel(tel);
				contact.setName(name);
				contact.setEmail(email);
				// addContact에 전달
				contactService.modifyContact(contact);	
				break;
			case 3: 
				System.out.println("삭제할 연락처 번호");
				contact_no = sc.nextInt();
				contactService.deleteContact(contact_no);
				break;
			case 4: 
				System.out.println("조회할 연락처 번호");
				contact_no = sc.nextInt();
				contactService.findContactByNo(contact_no);
				break;
			case 5: 
				contactService.fildAllConatacts();
				break;
			case 0:
				System.out.println("연락처 프로그램을 종료합니다.");
				return;
			default: System.out.println("다시 선택하세요!");
			
			}
		}
	}
	
	
}
