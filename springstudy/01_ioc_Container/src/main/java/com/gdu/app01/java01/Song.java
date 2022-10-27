package com.gdu.app01.java01;

public class Song {

	// field
	private String title;
	private String genre;
	
	// constructor
	public Song() {       // -> crtl+space로 디폴트 생성자
		
	}

	public Song(String title, String genre) {       // -> source - generate~
		super();
		this.title = title;
		this.genre = genre;
	}

	// getter/setter
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
}
