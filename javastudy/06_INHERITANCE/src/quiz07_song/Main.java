package quiz07_song;

public class Main {

	public static void main(String[] args) {
		
		
		Singer singer = new Singer("아이즈원", 2);	// 앚 노래가 2개 

		Song song1 = new Song("파노라마", 3.5);
		Song song2 = new Song("피에스타", 4.0);
		
		Producer producer = new Producer();
		producer.produce(singer, song1);		// 앚에게 song1, 2를 줬다
		producer.produce(singer, song2);

		singer.info();
		
	}

}
