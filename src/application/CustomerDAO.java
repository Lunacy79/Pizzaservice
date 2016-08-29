package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;



public class CustomerDAO {

	private Connection dbConnect;


	public int addCustomer(String fname, String lname, String street, String nr, String plz, String city) throws SQLException{

		this.dbConnect = (Connection) ConnectDB.createConnection();

		int id = -1;

		if(this.dbConnect != null){

			try{
				Statement anweisung = this.dbConnect.createStatement();
				anweisung.executeUpdate("Insert into customers (fname,lname,street,nr,plz,city) values ('" + fname + "','" + lname + "','" + street + "','" + nr + "','" + plz + "','" + city + "')");
				Statement anw = this.dbConnect.createStatement();
				ResultSet erg = anw.executeQuery("select @@IDENTITY");
				while(erg.next()){
					id = erg.getInt(1);
				}
				anweisung.close();

			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return id;
	}

	public Customer getCustomer(String lname){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		Customer customer = new Customer();
		ResultSet erg = null;

		if(this.dbConnect != null){

			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select * from customers where lname='" + lname + "'");
				while(erg.next()){
					customer=new Customer(erg.getInt(1),erg.getString(2),erg.getString(3),erg.getString(4),erg.getString(5),erg.getString(6), erg.getString(7));
				}


			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}


		return customer;
	}
}
