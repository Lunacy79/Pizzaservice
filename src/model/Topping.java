package model;

public class Topping {

	private String number;
	private String name;
	private int priceclass;

	public Topping(String number,String name, int priceclass){
		this.number=number;
		this.name = name;
		this.priceclass=priceclass;

	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
