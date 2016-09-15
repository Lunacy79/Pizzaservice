package model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {

	private int cnr;
	private String fname;
	private String lname;
	private String street;
	private String nr;
	private String plz;
	private String city;
	private String telefon;


	public Customer(String fname, String lname, String street, String nr, String plz, String city, String telefon){
		this.fname=fname;
		this.lname=lname;
		this.street=street;
		this.nr=nr;
		this.plz=plz;
		this.telefon=telefon;
	}

	public Customer(int cnr, String fname, String lname, String street, String nr, String plz, String city, String telefon){
		this.cnr=cnr;
		this.fname=fname;
		this.lname=lname;
		this.street=street;
		this.nr=nr;
		this.plz=plz;
		this.city=city;
		this.telefon=telefon;
	}

	public Customer(int cnr, String lname, String fname){
		this.cnr=cnr;
		this.lname=lname;
		this.fname=fname;
	}

	public Customer(){

	}

	public int getCnr() {
		return cnr;
	}

	public void setCnr(int cnr) {
		this.cnr = cnr;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	@Override
	public String toString() {
		return "Customer [cnr=" + cnr + ", fname=" + fname + ", lname=" + lname + ", street=" + street + ", nr=" + nr
				+ ", plz=" + plz + ", city=" + city + ", telefon=" + telefon + "]";
	}



}
