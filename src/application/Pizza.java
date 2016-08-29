package application;

import java.util.ArrayList;
import java.util.Iterator;

public class Pizza {

	private Topping topping;
	private ArrayList<Topping> toppings = new ArrayList<Topping>();
	private int size;
	private double price;

	public Pizza(int size){
		this.size = size;
	}

	public Topping getTopping() {
		return topping;
	}

	public void setTopping(Topping topping) {
		this.topping = topping;
	}

	public ArrayList<Topping> getToppings() {
		return toppings;
	}

	public void setToppings(ArrayList<Topping> toppings) {
		this.toppings = toppings;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void addTopping(String number, String name, int priceclass){
		this.toppings.add(new Topping(number,name,priceclass));
	}

	public Topping deleteTopping(String name){
		Iterator<Topping> iter = getToppings().iterator();
		boolean found = false;
		Topping topping = null;
		while(iter.hasNext() && !found){
			Topping top=iter.next();
			if(top.getName() == name){
				topping = top;
				found = true;
			}
		}
		return topping;
	}
}