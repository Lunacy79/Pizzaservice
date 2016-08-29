package application;

import java.util.ArrayList;

public class Customer {

	private int cnr;
	private String fname;
	private String lname;
	private String street;
	private String nr;
	private String plz;
	private String city;
	private ArrayList<Telefon> telefonliste = new ArrayList<Telefon>();


	public Customer(String fname, String lname, String street, String nr, String plz, String city){
		this.fname=fname;
		this.lname=lname;
		this.street=street;
		this.nr=nr;
		this.plz=plz;
		this.city=city;
	}

	public Customer(int cnr, String fname, String lname, String street, String nr, String plz, String city){
		this.cnr=cnr;
		this.fname=fname;
		this.lname=lname;
		this.street=street;
		this.nr=nr;
		this.plz=plz;
		this.city=city;
	}

	public void addTelefon(String number){
		this.telefonliste.add(new Telefon(number));
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


	public ArrayList<Telefon> getTelefonliste() {
		return telefonliste;
	}

	public void setTelefonliste(ArrayList<Telefon> telefonliste) {
		this.telefonliste = telefonliste;
	}

	@Override
	public String toString() {
		return "Customer [cnr=" + cnr + ", fname=" + fname + ", lname=" + lname + ", street=" + street + ", nr=" + nr
				+ ", plz=" + plz + ", city=" + city + ", telefonliste=" + telefonliste + "]";
	}



}
