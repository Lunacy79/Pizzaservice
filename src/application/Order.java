package application;

import java.util.ArrayList;

public class Order {

	private int ordernumber;
	private Customer customer;
	private Pizza pizza;
	private ArrayList<Pizza> pizzalist = new ArrayList<Pizza>();
	private Drinks drink;
	private ArrayList<Drinks> drinks = new ArrayList<Drinks>();
	private double price;

	public void addPizza(ArrayList<Topping> toppings, int size, double price){
		this.pizzalist.add(new Pizza(toppings, size, price));
	}

	public int getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	public ArrayList<Pizza> getPizzalist() {
		return pizzalist;
	}
	public void setPizzalist(ArrayList<Pizza> pizzalist) {
		this.pizzalist = pizzalist;
	}
	public Drinks getDrink() {
		return drink;
	}
	public void setDrink(Drinks drink) {
		this.drink = drink;
	}
	public ArrayList<Drinks> getDrinks() {
		return drinks;
	}
	public void setDrinks(ArrayList<Drinks> drinks) {
		this.drinks = drinks;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


}
