package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Customer;
import model.Order;
import model.Pizza;
import model.Topping;

public class OrderDAO {

	private Connection dbConnect;
	private ArrayList<Order> orders = new ArrayList<Order>();
	private Order order = new Order();

	public void setOnr(int knr){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		if(this.dbConnect != null){
			try{
				Statement anweisung2 = this.dbConnect.createStatement();
				anweisung2.executeUpdate("Insert into orders (cnr) values (" + knr + ")");
				anweisung2.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getOnr(){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		int onr = 0;
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select max(onr) from orders");
				while(erg.next()){
					onr = erg.getInt(1);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return onr;
	}

	public ArrayList<Order> getOrder(int onr){
		ArrayList<Order> order = new ArrayList<Order>();
		ResultSet erg = null;
		ResultSet erg2 = null;
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select size, price from orderedpizzas where onr = " + onr);
				while(erg.next()){
					order.add(new Order(erg.getString(1), erg.getDouble(2)));
				}
				Statement anweisung2 = this.dbConnect.createStatement();
				erg2 = anweisung2.executeQuery("Select topping, price from orderedpizzas where onr = " + onr);
				while(erg2.next()){
					order.add(new Order(erg.getString(1), erg.getDouble(2)));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return order;
	}

	public ArrayList<Order> getOrders(){
		ResultSet erg = null;
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select onr, cnr from orders");
				while(erg.next()){
					orders.add(new Order(erg.getInt(1),erg.getInt(2)));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orders;
	}

	public String getCustomerForOrder(){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		String customer = "";
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select orders.cnr,onr,lname,fname from customers,orders where customers.cnr = orders.cnr order by onr");
				while(erg.next()){
					customer = erg.getString(3) + ", " + erg.getString(4);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customer;
	}

	public String getCustomerForOrder(int onr){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		String customer = "";
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select orders.cnr,onr,lname,fname from customers,orders where customers.cnr = orders.cnr and onr=" +onr);
				while(erg.next()){
					customer=erg.getString(3) + ", " + erg.getString(4);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customer;
	}

	public void setToppings(int pnr, ArrayList<Topping> toppings){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		if(this.dbConnect != null){
			try{
				for(int i=0;i<toppings.size(); i++){
				Statement anweisung2 = this.dbConnect.createStatement();
				anweisung2.executeUpdate("Insert into orderedtopping (pnr,topping) values (" + pnr + ", '" + toppings.get(i).getName() + "')");
				anweisung2.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Order> getToppingsOrder(int pnr){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		ArrayList<Order> toppings = new ArrayList<Order>();
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select orderedtopping.topping,topping1 from orderedtopping,topping,orderedpizza,pizza where orderedpizza.pnr=" + pnr + " and orderedtopping.topping = topping.topping and orderedpizza.pnr = orderedtopping.pnr and orderedpizza.size = pizza.size and priceclass = 1");
				while(erg.next()){
					toppings.add(new Order(erg.getString(1),erg.getDouble(2)));

				}
				erg = anweisung.executeQuery("Select orderedtopping.topping,topping2 from orderedtopping,topping,orderedpizza,pizza where orderedpizza.pnr=" + pnr + " and orderedtopping.topping = topping.topping and orderedpizza.pnr = orderedtopping.pnr and orderedpizza.size = pizza.size and priceclass = 2");
				while(erg.next()){
					toppings.add(new Order(erg.getString(1),erg.getDouble(2)));

				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return toppings;
	}

	public ArrayList<Topping> getToppings(int pnr){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		ArrayList<Topping> toppings = new ArrayList<Topping>();
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select orderedtopping.topping,topping1 from orderedtopping,topping,orderedpizza,pizza where orderedpizza.pnr=" + pnr + " and orderedtopping.topping = topping.topping and orderedpizza.pnr = orderedtopping.pnr and orderedpizza.size = pizza.size and priceclass = 1");
				while(erg.next()){
					toppings.add(new Topping(erg.getString(1),erg.getDouble(2)));

				}
				erg = anweisung.executeQuery("Select orderedtopping.topping,topping2 from orderedtopping,topping,orderedpizza,pizza where orderedpizza.pnr=" + pnr + " and orderedtopping.topping = topping.topping and orderedpizza.pnr = orderedtopping.pnr and orderedpizza.size = pizza.size and priceclass = 2");
				while(erg.next()){
					toppings.add(new Topping(erg.getString(1),erg.getDouble(2)));

				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return toppings;
	}

	public void setPizza(int onr, String size){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		if(this.dbConnect != null){
			try{
				Statement anweisung2 = this.dbConnect.createStatement();
				anweisung2.executeUpdate("Insert into orderedpizza (onr,size) values (" + onr + ", '" + size + "')");
				anweisung2.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Pizza getPizza(int onr){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		Pizza pizza = new Pizza();
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select size,price from orderedpizza,pizza where onr=" + onr + "and orderedpizza.size = pizza.size");
				while(erg.next()){
					pizza = new Pizza(erg.getString(1),erg.getDouble(2));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pizza;
	}

	public ArrayList<Order> getPizzasOrder(int onr){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		ArrayList<Order> pizzas = new ArrayList<>();
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select orderedpizza.size,price,pnr from orderedpizza,pizza where onr=" + onr);
				while(erg.next()){
					pizzas.add(new Order(erg.getString(1),erg.getDouble(2)));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pizzas;
	}
	public ArrayList<Pizza> getPizzas(int onr){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		ArrayList<Pizza> pizzas = new ArrayList<>();
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select orderedpizza.size,price,pnr from orderedpizza,pizza where orderedpizza.size = pizza.size and onr=" + onr);
				while(erg.next()){
					int pnr = erg.getInt(3);
					ArrayList<Topping> tops = getToppings(pnr);
					pizzas.add(new Pizza(erg.getString(1),erg.getDouble(2),tops));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pizzas;
	}

	public int getPnr(int onr){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		int pnr = 0;
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select pnr from orderedpizza where onr=" + onr);
				while(erg.next()){
					pnr = erg.getInt(1);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pnr;
	}
}
