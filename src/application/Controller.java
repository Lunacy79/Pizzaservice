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
	    private AnchorPane anchor;

	    @FXML
	    private TabPane tabpane;

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
	    private Main mainApp;

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

		colcnr.setCellValueFactory(new PropertyValueFactory <Customer,Integer>("cnr"));
		collname.setCellValueFactory(new PropertyValueFactory <Customer,String>("lname"));
		colfname.setCellValueFactory(new PropertyValueFactory <Customer,String>("fname"));
		colstreet.setCellValueFactory(new PropertyValueFactory <Customer,String>("street"));
		colnr.setCellValueFactory(new PropertyValueFactory <Customer,String>("nr"));
		colplz.setCellValueFactory(new PropertyValueFactory <Customer,String>("Postleitzahl"));
		colcity.setCellValueFactory(new PropertyValueFactory <Customer,String>("Ort"));
		colphone.setCellValueFactory(new PropertyValueFactory <Customer,String>("Telefonnummer"));

	}

	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
		customertable.setItems(mainApp.getCustomers());
	}


}
