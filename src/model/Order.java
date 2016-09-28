package model;

import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order {

	private int ordernumber;
	private Customer customer;
	private int cnr;
	private String lname;
	private String fname;
	private int closed;
	private Pizza pizza;
	private Drinks drink;
	private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
	private ArrayList<Drinks> drinks = new ArrayList<Drinks>();
	private SimpleStringProperty item;
	private SimpleDoubleProperty price;

	public Order(){

	}

	public Order(String item,Double price){
		this.item = new SimpleStringProperty(item);
		this.price = new SimpleDoubleProperty(price);
	}

	public Order(int ordernumber, int cnr, String lname, String fname, int closed){
		this.ordernumber = ordernumber;
		customer = new Customer(cnr,lname,fname);
		this.cnr = customer.getCnr();
		this.lname = customer.getLname();
		this.fname = customer.getFname();
		this.closed = closed;
	}

	public Order(int ordernumber, int cnr, ArrayList<Pizza> pizzas, ArrayList<Drinks> drinks, int closed){
		this.ordernumber = ordernumber;
		this.cnr = cnr;
		this.pizzas = pizzas;
		this.drinks = drinks;
		this.closed = closed;
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

	public int getCnr() {
		return cnr;
	}

	public void setCnr(int cnr) {
		this.cnr = cnr;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public int getClosed() {
		return closed;
	}

	public void setClosed(int closed) {
		this.closed = closed;
	}

	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
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

	public void setItem(SimpleStringProperty item) {
		this.item = item;
	}

	public void setPrice(SimpleDoubleProperty price) {
		this.price = price;
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

	public ArrayList<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(ArrayList<Pizza> pizzas) {
		this.pizzas = pizzas;
	}



}
