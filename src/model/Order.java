package model;

import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Order {

	private int ordernumber;
	private int customernumber;
	private Customer customer;
	private Pizza pizza;
	private ArrayList<Topping> toppings1 = new ArrayList<Topping>();
	private Drinks drink;
	private ArrayList<Drinks> drinks = new ArrayList<Drinks>();
	private SimpleStringProperty item;
	private SimpleDoubleProperty price;

	public Order(String item,Double price){
		this.item = new SimpleStringProperty(item);
		this.price = new SimpleDoubleProperty(price);
	}

	public Order(int ordernumber, int customernumber){
		this.ordernumber = ordernumber;
		this.setCustomernumber(customernumber);
	}

	public SimpleStringProperty itemProperty() {
        if (item == null) {
            item = new SimpleStringProperty(this, "item");
        }
        return item;
	}

	public SimpleDoubleProperty priceProperty() {
        if (price == null) {
            price = new SimpleDoubleProperty(this, "price");
        }
        return price;
	}

	public String getItem() {
		return item.get();
	}

	public void setItem(String aitem) {
		item.set(aitem);
	}

	public void setPrice(Double aprice) {
		price.set(aprice);
	}

	public Double getPrice() {
		return price.get();
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
//	public ArrayList<Pizza> getPizzalist() {
//		return pizzalist;
//	}
//	public void setPizzalist(ArrayList<Pizza> pizzalist) {
//		this.pizzalist = pizzalist;
//	}
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

	public void setItem(SimpleStringProperty item) {
		this.item = item;
	}

	public void setPrice(SimpleDoubleProperty price) {
		this.price = price;
	}

	public int getCustomernumber() {
		return customernumber;
	}

	public void setCustomernumber(int customernumber) {
		this.customernumber = customernumber;
	}



}
