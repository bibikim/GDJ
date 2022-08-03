package quiz07_song;

public class Singer {

	private String name;
	private Song[] songs;
	private int idx;
	
	public Singer(String name, int cnt) {		// cnt 노래 갯수
		super();
		this.name = name;
		songs = new Song[cnt];
	}
	
	public void addSong(Song song) {	// producer에서 addSong에 song을 받아오니까 addSong메소드
		if(idx == songs.length) {		// 가득 차면 return
			return;
		}
		songs[idx++] = song;			// 아니면 노래 목록에 추가
	}


	public void info() {
		System.out.println("가수 이름: " + name);
		System.out.println("대표곡");
		for(int i = 0; i < idx; i++) {
			System.out.println(songs[i]);
		}
	}
	
	
	
	
}
