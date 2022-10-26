package com.gdu.app01.xml02;

public class Engine {

	// field
	private String fuel;   		// 연료(디젤, 가솔린)
	private double efficiency;  // 연비(12.5)
	private int cc;				// 배기량(1998)
	
	// method(getter/setter)
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public double getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
	
	
	
}
