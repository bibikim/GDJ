package quiz07_song;

public class Producer {
	
	private String name;

	//public Producer(String name) {
	//	super();
	//	this.name = name;
	//}
	
	public void produce(Singer singer, Song song) {
		singer.addSong(song);	// 가수한테 노래를 준다. singer는 addSong을 가지겠구나 배열에 넣어주면 되겠구나
												// 노래를 가져와서 singer와 songs에 저장하기
	}
	
	
	
}
