package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Customer;
import model.Drinks;
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
//
//	public ArrayList<Order> getOrder(int onr){
//		ArrayList<Order> order = new ArrayList<Order>();
//		ResultSet erg = null;
//		ResultSet erg2 = null;
//		if(this.dbConnect != null){
//			try{
//				Statement anweisung = this.dbConnect.createStatement();
//				erg = anweisung.executeQuery("Select size, price from orderedpizzas where onr = " + onr);
//				while(erg.next()){
//					order.add(new Order(erg.getString(1), erg.getDouble(2)));
//				}
//				Statement anweisung2 = this.dbConnect.createStatement();
//				erg2 = anweisung2.executeQuery("Select topping, price from orderedpizzas where onr = " + onr);
//				while(erg2.next()){
//					order.add(new Order(erg.getString(1), erg.getDouble(2)));
//				}
//			}
//			catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return order;
//	}

	public ArrayList<Order> getOrders(){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select onr, orders.cnr, lname, fname, closed from orders,customers where orders.cnr=customers.cnr");
				while(erg.next()){
					orders.add(new Order(erg.getInt(1),erg.getInt(2),erg.getString(3), erg.getString(4), erg.getInt(5)));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return orders;
	}

	public void closeOrder(int onr){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		if(this.dbConnect != null){
			try{
				Statement anweisung2 = this.dbConnect.createStatement();
				anweisung2.executeUpdate("Update orders set closed=1 where onr = " + onr);
				anweisung2.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void openOrder(int onr){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		if(this.dbConnect != null){
			try{
				Statement anweisung2 = this.dbConnect.createStatement();
				anweisung2.executeUpdate("Update orders set closed=0 where onr = " + onr);
				anweisung2.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getClosedOrder(int onr){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		int closed = 0;
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select closed from orders where onr = " + onr);
				while(erg.next()){
					closed = erg.getInt(1);
				}

			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return closed;
	}
}
