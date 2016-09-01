package model;

public class Topping {

	private String name;
	private int priceclass;

	public Topping(String name, int priceclass){

		this.name = name;
		this.priceclass=priceclass;

	}

	public Topping(){

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
