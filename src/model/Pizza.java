package model;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pizza {

	private Topping topping;
	private ArrayList<Topping> toppings = new ArrayList<Topping>();
	private SimpleStringProperty name;
	private SimpleStringProperty size;
	private SimpleDoubleProperty price;
	private double topping1;
	private double topping2;

	public Pizza(String size, double price){
		this.size = new SimpleStringProperty(size);
		this.price = new SimpleDoubleProperty(price);
	}
	
	public Pizza(String size, double price, ArrayList<Topping> toppings){
		this.size = new SimpleStringProperty(size);
		this.price = new SimpleDoubleProperty(price);
		this.toppings = toppings;
	}

	public Pizza(){

	}

	public Pizza(String size, double price, double topping1, double topping2){
		this.size = new SimpleStringProperty(size);
		this.price = new SimpleDoubleProperty(price);
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

	

	public String getSize() {
		return size.get();
	}

	public void setSize(String size) {
		this.size.set(size);
	}

	public double getPrice() {
		return price.get();
	}

	public void setPrice(double price) {
		this.price.set(price);
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