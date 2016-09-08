package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;



public class CustomerDAO {
	private Customer customer = new Customer();
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private Connection dbConnect;

	public ArrayList<Customer> getCustomers(){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		ResultSet erg = null;

		if(this.dbConnect != null){

			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select * from customers");
				while(erg.next()){

					customers.add(new Customer(erg.getInt(1),erg.getString(2),erg.getString(3),erg.getString(4),erg.getString(5),erg.getString(6), erg.getString(7),erg.getString(8)));
				}

			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return customers;

	}

	public int addCustomer(String fname, String lname, String street, String nr, String plz, String city, String telefon) throws SQLException{

		this.dbConnect = (Connection) ConnectDB.createConnection();

		int id = -1;

		if(this.dbConnect != null){

			try{
				Statement anweisung = this.dbConnect.createStatement();
				anweisung.executeUpdate("Insert into customers (fname,lname,street,nr,plz,city,telefon) values ('" + fname + "','" + lname + "','" + street + "','" + nr + "','" + plz + "','" + city + "','" + telefon + "')");
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

	public ArrayList<Customer> changeCustomer(int cnr, String fname, String lname, String street, String nr, String plz, String city, String telefon) throws SQLException{

		this.dbConnect = (Connection) ConnectDB.createConnection();

		int id = -1;

		if(this.dbConnect != null){

			try{
				Statement anweisung = this.dbConnect.createStatement();
				anweisung.executeUpdate("Update customers set fname='"+fname+"',lname='"+lname+"',street='"+street+"',nr='"+nr+"',plz='"+plz+"',city='"+city+"',telefon='"+telefon+"' where cnr=" + cnr);

				anweisung.close();

			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			customers = getCustomers();
		}
		return customers;
	}

	public ArrayList<Customer> getCustomer(String lname){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		ResultSet erg = null;

		if(this.dbConnect != null){

			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select * from customers where lname='" + lname + "'");
				while(erg.next()){
					customers.add(new Customer(erg.getInt(1),erg.getString(2),erg.getString(3),erg.getString(4),erg.getString(5),erg.getString(6), erg.getString(7),erg.getString(8)));
				}

			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return customers;
	}



	public Customer getSingleCustomer(int cnr){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		ResultSet erg = null;

		if(this.dbConnect != null){

			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select * from customers where cnr='" + cnr + "'");
				while(erg.next()){
					customer= new Customer(erg.getInt(1),erg.getString(2),erg.getString(3),erg.getString(4),erg.getString(5),erg.getString(6), erg.getString(7),erg.getString(8));
				}

			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return customer;
	}

	public void setCustomerForOrder(int knr){
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

	public void deleteCustomer(int cnr){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		if(this.dbConnect != null){
			try{
				Statement anweisung2 = this.dbConnect.createStatement();
				anweisung2.executeUpdate("Delete from customers where cnr= " + cnr );
				anweisung2.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
