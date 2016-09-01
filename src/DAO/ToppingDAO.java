package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Pizza;
import model.Topping;

public class ToppingDAO {

	private Connection dbConnect;
	private Topping topping = new Topping();
	private ArrayList<String> toppinglist1 = new ArrayList<String>();
	private ArrayList<String> toppinglist2 = new ArrayList<String>();

	public ArrayList<String> getToppings1(){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		ResultSet erg = null;

		if(this.dbConnect != null){

			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select topping from topping where priceclass = 1");
				while(erg.next()){

					toppinglist1.add(erg.getString(1));
				}

			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}


		return toppinglist1;
	}

	public ArrayList<String> getToppings2(){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		ResultSet erg = null;

		if(this.dbConnect != null){

			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select topping from topping where priceclass = 2");
				while(erg.next()){

					toppinglist2.add(erg.getString(1));
				}


			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return toppinglist2;
	}
}
