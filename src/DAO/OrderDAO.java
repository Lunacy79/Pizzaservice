package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Pizza;

public class OrderDAO {

	private Connection dbConnect;
	
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
	
	public void setToppings(int pnr, ArrayList<String> toppings){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		if(this.dbConnect != null){
			try{
				for(int i=0;i<toppings.size(); i++){
				Statement anweisung2 = this.dbConnect.createStatement();
				anweisung2.executeUpdate("Insert into orderedtopping (pnr,topping) values (" + pnr + ", '" + toppings.get(i) + "')");
				anweisung2.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
