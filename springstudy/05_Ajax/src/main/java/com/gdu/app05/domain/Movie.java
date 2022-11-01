package com.gdu.app05.domain;

public class Movie {

	private int rank;  // 순위
	private String movieNm;  // 영화명
	private String openDt;   // 개봉일
	private int audiCnt;	 // 해당 날짜의 관객수
	private int audiAcc;     // 누적 관객수

	public Movie() {
		// TODO Auto-generated constructor stub       왜 디폴트생성자만 필요한걸까? ㅇㅅㅇ
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getMovieNm() {
		return movieNm;
	}

	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}

	public String getOpenDt() {
		return openDt;
	}

	public void setOpenDt(String openDt) {
		this.openDt = openDt;
	}

	public int getAudiCnt() {
		return audiCnt;
	}

	public void setAudiCnt(int audiCnt) {
		this.audiCnt = audiCnt;
	}

	public int getAudiAcc() {
		return audiAcc;
	}

	public void setAudiAcc(int audiAcc) {
		this.audiAcc = audiAcc;
	}
	
	
	
}
