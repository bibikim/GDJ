package com.gdu.app01.xml07;

public class User {
	
	// field
	private String id;
	private Contact contact;
	
	// contsructor
	public User(String id, Contact contact) {
		super();
		this.id = id;
		this.contact = contact;
	}
	
	// method
	public void info() {
		System.out.println("아이디 : " + id);
		contact.info();  // contact이 가지고 있는 info()를 찍어주면 연락처 나옴
	}
	
}
