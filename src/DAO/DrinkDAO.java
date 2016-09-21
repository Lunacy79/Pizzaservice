package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Drinks;


public class DrinkDAO {

	private Connection dbConnect;
	ArrayList<Drinks> drinks = new ArrayList<>();

	public ArrayList<Drinks> getDrinks(){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select name,price from drinks");
				while(erg.next()){
					drinks.add(new Drinks(erg.getString(1),erg.getDouble(2)));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return drinks;
	}

	public void setDrink(int onr, String name){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		if(this.dbConnect != null){
			try{
				Statement anweisung2 = this.dbConnect.createStatement();
				anweisung2.executeUpdate("Insert into ordereddrinks (onr,name) values (" + onr + ", '" + name + "')");
				anweisung2.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Drinks> getDrinks(int onr){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		ArrayList<Drinks> drinks = new ArrayList<>();
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select drinks.name,drinks.price from ordereddrinks,drinks where onr=" + onr + " and ordereddrinks.name = drinks.name");
				while(erg.next()){
					drinks.add(new Drinks(erg.getString(1),erg.getDouble(2)));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return drinks;
	}
}
