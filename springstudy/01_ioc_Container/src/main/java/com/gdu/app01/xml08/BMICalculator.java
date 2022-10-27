package com.gdu.app01.xml08;

public class BMICalculator {

	private Calculator calc;
	private double height;
	private double weight;
	private double bmi;     // 계산 필요한 파생 속성들은 여기서 나와야 함. 외부에서 계산한 값을 받아오는게 아니라.
	private String health;
	
	// contsructor
	public BMICalculator(Calculator calc, double height, double weight/*, double bmi, String health*/) {
																	// 계산이 필요한 파라미터는 지우고 밑에서 계산식을 만든다.
		super();
		this.calc = calc;
		this.height = height;
		this.weight = weight;
		bmi = calc.div(weight, calc.div(calc.mul(height, height), 10000));   // cm로 곱하기 -> m 단위로 바꾸기 위해 나누기 10000. -> 몸무게 곱해주기
		health = (bmi < 20) ? "저체중" : (bmi < 25) ? "정상" : (bmi < 30) ? "과체중" : "비만";
	}
	
	// info()메소드 : 출력 정보 담아두기
	public void info() {
		// bmi랑 heallth는 반드시 여기서 보여줘야 됨
		System.out.println("BMI : " + bmi);
		System.out.println("Health : " + health);
	} 
	
}
