package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Customer;
import model.Pizza;
import model.Topping;

import java.sql.Connection;


public class PizzaDAO {
	private Connection dbConnect;
	private ArrayList<Pizza> pizzalist = new ArrayList<Pizza>();
	private ToppingDAO topping = new ToppingDAO();

	public ArrayList<Pizza> getPizzas(){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select * from pizza");
				while(erg.next()){
					pizzalist.add(new Pizza(erg.getString(1),erg.getDouble(2),erg.getDouble(3),erg.getDouble(4)));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pizzalist;
	}

	public void setPizza(int onr, String size, double price){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		if(this.dbConnect != null){
			try{
				Statement anweisung2 = this.dbConnect.createStatement();
				anweisung2.executeUpdate("Insert into orderedpizza (onr,size, price) values (" + onr + ", '" + size + "', " + price + ")");
				anweisung2.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Pizza> getPizzas(int onr){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		ArrayList<Pizza> pizzas = new ArrayList<>();
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select size,price,pnr from orderedpizza where onr=" + onr);
				while(erg.next()){
					int pnr = erg.getInt(3);
					ArrayList<Topping> tops = topping.getToppings(pnr);
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
