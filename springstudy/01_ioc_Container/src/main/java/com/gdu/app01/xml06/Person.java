package com.gdu.app01.xml06;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Person {

	// field
	private List<String> hobbies;
	private Set<String> contacts;   // 순서가 없어서 인덱스 사용x, 향상for문만 가능
	private Map<String, String> friends;
	
	
	// Setter Injection
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	public Set<String> getContacts() {
		return contacts;
	}
	public void setContacts(Set<String> contacts) {
		this.contacts = contacts;
	}
	public Map<String, String> getFriends() {
		return friends;
	}
	public void setFriends(Map<String, String> friends) {
		this.friends = friends;
	}
	
	
	// info() 메소드
	public void info() {
		// List
		for(int i = 0; i < hobbies.size(); i++) {
			System.out.println((i+1) + "번째 취미 :" + hobbies.get(i));   // 인덱스는 get()메소드로 지정
		}
		
		// Set
		for(String contact : contacts) {
			System.out.println(contact);
		}
		
		// Map
		for(Map.Entry<String, String> entry : friends.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
}
