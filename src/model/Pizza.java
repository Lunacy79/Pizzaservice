package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Pizza {

	private Topping topping;
	private ArrayList<Topping> toppings = new ArrayList<Topping>();
	private String name;
	private String size;
	private double price;
	private double topping1;
	private double topping2;

	public Pizza(String size, double price){
		this.size = size;
	}

	public Pizza(){

	}

	public Pizza(String size, double price, double topping1, double topping2){
		this.size = size;
		this.price = price;
		this.topping1 = topping1;
		this.topping2 = topping2;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTopping1() {
		return topping1;
	}

	public void setTopping1(double topping1) {
		this.topping1 = topping1;
	}

	public double getTopping2() {
		return topping2;
	}

	public void setTopping2(double topping2) {
		this.topping2 = topping2;
	}

	public void addTopping(String number, String name, int priceclass){
		this.toppings.add(new Topping(name,priceclass));
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