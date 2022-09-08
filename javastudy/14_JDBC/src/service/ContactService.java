package service;

import domain.ContactDTO;

public interface ContactService {
	// 어떤 메소드가 추상 메소드(본문이 없는) 형태로 들어가는 것이 특징인 인터페이스
	// 따라서 ;으로 닫아준다
	// 인터페이스는 작업 지시서같은 역할을 함
	
	// 추가,수정할 때는 정보들을 contactDTO에 담아와야 함
	public void addContact(ContactDTO contact);  // 추가할 정보를 가지고 있는 것 - 이름, 연락처, 이메일을 다 받아와야 하기 때문에 그 여러개를 받아오는 걸 ContactDTO로 하는거
	public void modifyContact(ContactDTO contact);	// 삭제할 정보를 갖고 있는 것 - 마찬가지
	public void deleteContact(int contact_no);  // CONTACT_NO로 삭제할거라 연락처만 받아와도 되니까 int처리
	public void findContactByNo(int contact_no); 
	public void fildAllConatacts(); // 전체 조회할거니까 매개변수 딱히 필요없음.

	public void createContactFile();
	
	// 실제 내용은 인터페이스를 구현하는 ContactImpl을 클래스로 만들면 자동으로 내가 채워야하는 본문이 만들어진다
	
	// Controller play()
	//  선택에 따른 ContactService의 메소드 호출
	// ContactController에서 1~5를 선택하면 ContactServie의 1~5가 불리는 것
	
	// ContactService
	// ContactDAO의 메소드 호출
	// 컨트롤러 -> 서비스(1~5) 부르고 -> (DB로 직접 접근하는게 아니라 DAO한테) 원하는 DAO를 부르고 -> DAO가 DB에 처리
	
	// Controller -> Service -> DAO -> DB
	// 컨트롤러, 서비스, DAO 전부 5개로 나눠짐
	// 컨트롤러에서 부르는건 서비스, 서비스는 DAO를 불러서 DAO가 DB에 접근함
	
}
