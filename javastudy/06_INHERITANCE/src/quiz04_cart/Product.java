package quiz04_cart;

public class Product {

	private String name;
	private int price;
	
	// 생성자 만들기
	public Product(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	
	// 외부에서 이름과 가격 정보 확인 가능하도록 게터세터 생성.
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
// 모든 제품은 product 타입으로 저장시킬 수 있다.
	

	
	
	
	
	
	
}
