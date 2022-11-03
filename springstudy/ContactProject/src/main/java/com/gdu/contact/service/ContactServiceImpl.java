package com.gdu.contact.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gdu.contact.domain.ContactVO;
import com.gdu.contact.repository.ContactDAO;

public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDAO dao;
	
	
	@Override
	public List<ContactVO> findAllContacts() {
		// TODO Auto-generated method stub
		return dao.findAllContacts();
	}

	@Override
	public ContactVO findContactByNo(int no) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int regiContact(ContactVO contact) {
		// TODO Auto-generated method stub
		return dao.regiContact(contact);
	}

	@Override
	public int editContact(ContactVO contact) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteContact(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
