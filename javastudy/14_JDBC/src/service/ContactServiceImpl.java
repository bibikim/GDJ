package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import domain.ContactDTO;
import repository.ContactDAO;

public class ContactServiceImpl implements ContactService {
	
	// 실제 내용은 인터페이스를 구현하는 ContactServiceImpl을 클래스로 만들면 자동으로 내가 채워야하는 본문이 만들어진다
	
	
	/********************* Field **************************/
	
	// DAO에 데이터를 전달하고, DAO로부터 결과를 반환 받기 위해서 필드값으로 DAO를 선언
	// DAO를 선언하고 사용하는게 ServiceImpl
	private ContactDAO dao = ContactDAO.getInstance();   // 싱글턴패턴이기 때문에 getInstance로 불러야 함. 객체생성을 못하기때문에 객체없이 클래스 이름을 부르기 위해 static처리 했던 것
	
	
	/********************* Method **************************/
	
	@Override
	public void addContact(ContactDTO contact) {
		int result = dao.insertContact(contact);   
		// service쪽으로 넘어온 신규 연락처 정보를 DB에 넣기 위해 DAO불러서 insert, 결과(result)를 받아와서 0인지 1인지 확인
		if(result > 0) {
			System.out.println("연락처가 등록되었습니다.");
		} else {
			System.out.println("연락처 등록이 실패했습니다.");
		}

	}

	@Override
	public void modifyContact(ContactDTO contact) {
		int result = dao.updateContact(contact);
		// service쪽으로 넘어온 신규 연락처 정보를 DB에 넣기 위해 DAO불러서 update, 결과(result)를 받아와서 0인지 1인지 확인
		if(result > 0) {
			System.out.println("연락처가 수정되었습니다.");
		} else {
			System.out.println("연락처 수정이 실패했습니다.");
		}

	}

	@Override
	public void deleteContact(int contact_no) {
		int result = dao.deleteContact(contact_no);  
		// service쪽으로 넘어온 신규 연락처 정보를 DB에 넣기 위해 DAO불러서 delete, 결과(result)를 받아와서 0인지 1인지 확인
		if(result > 0) {
			System.out.println("연락처가 삭제되었습니다.");
		} else {
			System.out.println("연락처 삭제가 실패했습니다.");
		}

	}

	@Override
	public void findContactByNo(int contact_no) { 
		ContactDTO contact = dao.selectContactByNo(contact_no);
		if(contact == null) {
			System.out.println("조회된 연락처가 없습니다");
		} else {
			System.out.println(contact);
		}
	}

	@Override
	public void fildAllConatacts() {
		List<ContactDTO> contacts = dao.selectAllContacts();
		if(contacts.isEmpty()) {
			System.out.println("저장된 연락처가 없습니다.");
		} else {
			for(ContactDTO contact : contacts) {
			System.out.println(contact);
			}
		}

	}

	@Override
	public void createContactFile() {
		
		List<ContactDTO> contacts = dao.selectAllContacts();
		
		File file = new File("연락처.csv");
		FileWriter fw = null;
		BufferedWriter bw = null;
		
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			for(ContactDTO contact : contacts) {
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
