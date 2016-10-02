package model;

public class Topping {
	private int tnr;
	private String name;
	private int priceclass;
	private double price;

	public Topping(){

	}
	
	public Topping(int tnr, String name, double price, int priceclass){
		this.tnr=tnr;
		this.name = name;
		this.price=price;
		this.priceclass=priceclass;
	}

	public Topping(String name, int priceclass){
		this.name = name;
		this.priceclass=priceclass;
	}

	public Topping(String name, double price){
		this.name = name;
		this.price = price;
	}

	public int getTnr() {
		return tnr;
	}

	public void setTnr(int tnr) {
		this.tnr = tnr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriceclass() {
		return priceclass;
	}

	public void setPriceclass(int priceclass) {
		this.priceclass = priceclass;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


}
