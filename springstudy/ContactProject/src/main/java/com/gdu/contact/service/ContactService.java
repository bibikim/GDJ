package com.gdu.contact.service;

import java.util.List;

import com.gdu.contact.domain.ContactVO;

public interface ContactService {

	
	public List<ContactVO> findAllContacts();
	public ContactVO findContactByNo(int no);
	public int regiContact(ContactVO contact);
	public int editContact(ContactVO contact);
	public int deleteContact(int no);
	
	
}
