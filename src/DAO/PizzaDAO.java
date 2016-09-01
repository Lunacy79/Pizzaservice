package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Customer;
import model.Pizza;

import java.sql.Connection;
import java.sql.DriverManager;


public class PizzaDAO {

	private Connection dbConnect;
	private Pizza pizza = new Pizza();
	private ArrayList<Pizza> pizzalist = new ArrayList<Pizza>();

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


	public Pizza addPizza(String size) throws SQLException{

		this.dbConnect = (Connection) ConnectDB.createConnection();
		Pizza pizza = new Pizza(size);
		ResultSet erg = null;

		if(this.dbConnect != null){

			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select * from pizza where size='" + size + "'");
				while(erg.next()){
					pizza=new Pizza(erg.getString(1),erg.getDouble(2));
				}



			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pizza;
	}

	public Customer deletePizza(String lname){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		Customer customer = new Customer();
		ResultSet erg = null;

		if(this.dbConnect != null){

			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select * from customers where lname='" + lname + "'");
				while(erg.next()){
					customer=new Customer(erg.getInt(1),erg.getString(2),erg.getString(3),erg.getString(4),erg.getString(5),erg.getString(6), erg.getString(7),erg.getString(8));
				}


			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}


		return customer;
	}
}
