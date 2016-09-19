package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.CustomerDAO;
import DAO.PizzaDAO;
import application.Main;
import model.Customer;
import model.Order;
import model.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

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
    private TabPane tabpane;

	@FXML
    private Tab bestellungstab;

	@FXML
    public TableView<Order> ordertable;
	public ObservableList<Order> orderlist = FXCollections.observableArrayList();

	@FXML
    private TableColumn<Order, Integer> orderonr;

	@FXML
	private TableColumn<Order, Integer> ordercnr;

	@FXML
	private TableColumn<Order, String> orderlname;

	@FXML
    private TableColumn<Order, String> orderfname;

	@FXML
    private TableColumn<Customer,String> colplz;

    @FXML
    private TableColumn<Customer,String> colphone;

    @FXML
    private Button custdel;

    @FXML
    private Button custlistorder;

    @FXML
    private AnchorPane anchor;

    @FXML
    private TableColumn<Customer,String> colnr;

    @FXML
    public TableView<Customer> customertable;
    public ObservableList<Customer> customerlist = FXCollections.observableArrayList();

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
    private Button searchbtn;

    @FXML
    private TableColumn<Customer,String> colcity;

    @FXML
    private TableColumn<Customer,Integer> colcnr;
    public Main mainApp;

    @FXML
    private Button custchange;
    private ObservableList<Pizza> pizzalist = FXCollections.observableArrayList();

    @FXML
    void addCustomer(ActionEvent event) throws IOException {
    	mainApp.addCustomer(event);
    }

    @FXML
    void deleteCustomer(ActionEvent event) {
    	int index = customertable.getSelectionModel().getSelectedIndex();
    	int cnr = mainApp.getCustomerlist().get(index).getCnr();
    	CustomerDAO cust = new CustomerDAO();
    	cust.deleteCustomer(cnr);
    	mainApp.setCustomerlist();
    }

    @FXML
    void changeCustomer(ActionEvent event) throws IOException {

    	int index = customertable.getSelectionModel().getSelectedIndex();
    	int cnr = mainApp.getCustomerlist().get(index).getCnr();
    	CustomerDAO cust = new CustomerDAO();
    	Customer customer = cust.getSingleCustomer(cnr);
    	mainApp.changeCustomer(event, customer);
    }

    @FXML
    void searchCustomer(ActionEvent event) {

		customertable.setItems(mainApp.getCustomer(searchfield.getText()));
		colcnr.setCellValueFactory(new PropertyValueFactory <Customer,Integer>("cnr"));
		collname.setCellValueFactory(new PropertyValueFactory <Customer,String>("lname"));
		colfname.setCellValueFactory(new PropertyValueFactory <Customer,String>("fname"));
		colstreet.setCellValueFactory(new PropertyValueFactory <Customer,String>("street"));
		colnr.setCellValueFactory(new PropertyValueFactory <Customer,String>("nr"));
		colplz.setCellValueFactory(new PropertyValueFactory <Customer,String>("plz"));
		colcity.setCellValueFactory(new PropertyValueFactory <Customer,String>("city"));
		colphone.setCellValueFactory(new PropertyValueFactory <Customer,String>("telefon"));
    }

	@Override
	public void initialize(final URL location, final ResourceBundle resources){

		colcnr.setCellValueFactory(new PropertyValueFactory <Customer,Integer>("cnr"));
		collname.setCellValueFactory(new PropertyValueFactory <Customer,String>("lname"));
		colfname.setCellValueFactory(new PropertyValueFactory <Customer,String>("fname"));
		colstreet.setCellValueFactory(new PropertyValueFactory <Customer,String>("street"));
		colnr.setCellValueFactory(new PropertyValueFactory <Customer,String>("nr"));
		colplz.setCellValueFactory(new PropertyValueFactory <Customer,String>("plz"));
		colcity.setCellValueFactory(new PropertyValueFactory <Customer,String>("city"));
		colphone.setCellValueFactory(new PropertyValueFactory <Customer,String>("telefon"));

		orderonr.setCellValueFactory(new PropertyValueFactory <Order,Integer>("onr"));
		ordercnr.setCellValueFactory(new PropertyValueFactory <Order,Integer>("cnr"));


	}
	
	public void start(){

		colcnr.setCellValueFactory(new PropertyValueFactory <Customer,Integer>("cnr"));
		collname.setCellValueFactory(new PropertyValueFactory <Customer,String>("lname"));
		colfname.setCellValueFactory(new PropertyValueFactory <Customer,String>("fname"));
		colstreet.setCellValueFactory(new PropertyValueFactory <Customer,String>("street"));
		colnr.setCellValueFactory(new PropertyValueFactory <Customer,String>("nr"));
		colplz.setCellValueFactory(new PropertyValueFactory <Customer,String>("plz"));
		colcity.setCellValueFactory(new PropertyValueFactory <Customer,String>("city"));
		colphone.setCellValueFactory(new PropertyValueFactory <Customer,String>("telefon"));

		orderonr.setCellValueFactory(new PropertyValueFactory <Order,Integer>("onr"));
		ordercnr.setCellValueFactory(new PropertyValueFactory <Order,Integer>("cnr"));


	}

	@FXML
    public void gotoOrder(ActionEvent event) throws IOException {
		int selectedIndex = customertable.getSelectionModel().getSelectedIndex();
		int knr = 0;
    	if(selectedIndex >= 0){
    		knr = customertable.getItems().get(selectedIndex).getCnr();
    		CustomerDAO cust = new CustomerDAO();
    		cust.setCustomerForOrder(knr);
    		mainApp.openOrder(event);
    	}
	}

	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
		ordertable.setItems(mainApp.getOrderlist());
		customertable.setItems(mainApp.getCustomerlist());
	}

	public void refresh(){

		customertable.setItems(mainApp.getCustomerlist());
		ordertable.setItems(mainApp.getOrderlist());
//		System.out.println(mainApp.getOrderlist());



//		customertable.getItems().clear();
//		System.out.println(customertable.getItems());
//		CustomerDAO customer = new CustomerDAO();
//		customerlist.clear();
//		customerlist.addAll(customer.getCustomers());
//
//		System.out.println(customertable.getItems());

	}

	public void openBestellung(ObservableList<Order> orderlist){
		tabpane.getSelectionModel().select(1);
		ordertable.setItems(mainApp.getOrderlist());
		start();
	}

}
