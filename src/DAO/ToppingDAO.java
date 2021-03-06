package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Topping;


public class ToppingDAO {

	private Connection dbConnect;
	private ArrayList<Topping> toppings1 = new ArrayList<Topping>();
	private ArrayList<Topping> toppings2 = new ArrayList<Topping>();
	private ArrayList<Topping> toppinglist1 = new ArrayList<Topping>();
	private ArrayList<Topping> toppinglist2 = new ArrayList<Topping>();
	private ArrayList<Topping> toppinglist = new ArrayList<Topping>();

	public ArrayList<Topping> getTopping1(String size){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select topping,topping1 from topping,pizza where size='"+size+"' and priceclass = 1");
				while(erg.next()){
					toppings1.add(new Topping(erg.getString(1),erg.getDouble(2)));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return toppings1;
	}

	public ArrayList<Topping> getTopping2(String size){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select topping,topping2 from topping,pizza where size='"+size+"' and priceclass = 2");
				while(erg.next()){
					toppings2.add(new Topping(erg.getString(1),erg.getDouble(2)));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return toppings2;
	}

	public ArrayList<Topping> getToppings1(){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select topping,priceclass from topping where priceclass = 1");
				while(erg.next()){
					toppinglist1.add(new Topping(erg.getString(1),erg.getInt(2)));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return toppinglist1;
	}

	public ArrayList<Topping> getToppings2(){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select topping,priceclass from topping where priceclass = 2");
				while(erg.next()){
					toppinglist2.add(new Topping(erg.getString(1),erg.getInt(2)));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return toppinglist2;
	}

	public ArrayList<Topping> getToppings(){
		toppinglist.addAll(getToppings1());
		toppinglist.addAll(getToppings2());
		return toppinglist;
	}

	public void setToppings(int pnr, ArrayList<Topping> toppings){
		this.dbConnect = (Connection) ConnectDB.createConnection();

		if(this.dbConnect != null){
			try{
				for(int i=0;i<toppings.size(); i++){
				Statement anweisung2 = this.dbConnect.createStatement();
				anweisung2.executeUpdate("Insert into orderedtopping (pnr,topping, price) values (" + pnr + ", '" + toppings.get(i).getName() + "', " + toppings.get(i).getPrice() +")");
				anweisung2.close();
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Topping> getToppings(int pnr){
		this.dbConnect = (Connection) ConnectDB.createConnection();
		ResultSet erg = null;
		ArrayList<Topping> toppings = new ArrayList<Topping>();
		if(this.dbConnect != null){
			try{
				Statement anweisung = this.dbConnect.createStatement();
				erg = anweisung.executeQuery("Select topping,price from orderedtopping where pnr=" + pnr);
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
}
