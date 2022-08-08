package quiz02_rsp;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		String[] rsp = {"가위","바위","보"};
		
		// rsp 배열에서 임의의 값을 선택하여 HashSet에 저장한다.
		// rsp[0] == "가위"
		// rsp[1] == "바위"
		// rsp[2] == "보"
		
		// 몇 번만에 HashSet에 모두 넣을 수 있는지 확인한다.
		// 임의의 값이기 때문에 언제 어떤게 Set에 들어갈지는 잘 모른다
		
		Set<String> set = new HashSet<String>();     
		int cnt = 0; 	// 시도 횟수
		
		// 몇번 수행할지 모르니까 반복문 while 사용
		while(set.size() < 3) {		// 조건은 "세트에 저장된게 3개가 아니면"
			int i = (int)(Math.random() * 3);	// 0,1,2 중 하나
			set.add(rsp[i]);
			cnt++;					// 중복을 배제시킬 땐 Set만한게 없당
		}
		System.out.println(set);
		System.out.println(cnt);
		
	}
	
	
	
	

}
