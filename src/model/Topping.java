package model;

public class Topping {

	private String name;
	private int priceclass;
	private double price;

	public Topping(String name, int priceclass){

		this.name = name;
		this.priceclass=priceclass;

	}

	public Topping(){

	}

	public Topping(String name, double price){
		this.name = name;
		this.price = price;
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


}
