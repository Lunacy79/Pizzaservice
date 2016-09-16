package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.ConnectDB;
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
}
