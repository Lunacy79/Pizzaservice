package model;

public class Drinks {
	private int dnr;
	private String name;
	private double price;
	
	public Drinks(String name, double price){
		this.name=name;
		this.price=price;
	}
	
	public Drinks(int dnr, String name, double price){
		this.dnr=dnr;
		this.name=name;
		this.price=price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public int getDnr() {
		return dnr;
	}

	public void setDnr(int dnr) {
		this.dnr = dnr;
	}
}
