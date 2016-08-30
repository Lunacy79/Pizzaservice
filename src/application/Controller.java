package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.CustomerDAO;
import model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;


import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



public class Controller implements Initializable {


	public Controller(){

	}

	  @FXML
	    private TableColumn<Customer,String> colplz;

	    @FXML
	    private TableColumn<Customer,String> colphone;

	    @FXML
	    private Button custdel;

	    @FXML
	    private TableColumn<Customer,String> colnr;

	    @FXML
	    private TableView<Customer> customertable;

	    @FXML
	    private TextField searchfield;

	    @FXML
	    private TableColumn<Customer,String> colstreet;

	    @FXML
	    private TableColumn<Customer,String> collname;

	    @FXML
	    private Tab menutab;

	    @FXML
	    private Button custadd;

	    @FXML
	    private Tab ordertab;

	    @FXML
	    private TableColumn<Customer,String> colfname;

	    @FXML
	    private Tab custtab;

	    @FXML
	    private TableColumn<Customer,String> colcity;

	    @FXML
	    private TableColumn<Customer,Integer> colcnr;

	    @FXML
	    private Button custchange;

	    @FXML
	    void addCustomer(ActionEvent event) {

	    }

	    @FXML
	    void deleteCustomer(ActionEvent event) {

	    }

	    @FXML
	    void changeCustomer(ActionEvent event) {

	    }

	    @FXML
	    void searchCustomer(ActionEvent event) {

	    }
	@Override
	public void initialize(final URL location, final ResourceBundle resources){

		ArrayList<Customer> customers = new ArrayList<Customer>();
		  CustomerDAO customer = new CustomerDAO();
		  customers = customer.getCustomers();
		  ObservableList<Customer> customerlist = FXCollections.observableArrayList(customers);

		  TableView tableView = new TableView();
		  tableView.getColumns().addAll(colcnr,collname,colfname,colstreet,colnr,colplz,colcity,colphone);
		  tableView.setItems(customerlist);

		colcnr.setCellValueFactory(new PropertyValueFactory <Customer,Integer>("Kundennummer"));
		  collname.setCellValueFactory(new PropertyValueFactory <Customer,String>("Nachname"));
		  colfname.setCellValueFactory(new PropertyValueFactory <Customer,String>("Vorname"));
		  colstreet.setCellValueFactory(new PropertyValueFactory <Customer,String>("Straﬂe"));
		  colnr.setCellValueFactory(new PropertyValueFactory <Customer,String>("Hausnr"));
		  colplz.setCellValueFactory(new PropertyValueFactory <Customer,String>("Postleitzahl"));
		  colcity.setCellValueFactory(new PropertyValueFactory <Customer,String>("Ort"));
		  colphone.setCellValueFactory(new PropertyValueFactory <Customer,String>("Telefonnummer"));

	}


}
