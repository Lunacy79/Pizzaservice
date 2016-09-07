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
	    private TabPane tabpane;

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
	    static Stage secondStage = new Stage();

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
	    	openNewStage(event);
	    }

	    @FXML
	    void deleteCustomer(ActionEvent event) {
	    	int index = customertable.getSelectionModel().getSelectedIndex();
	    	int cnr = customerlist.get(index).getCnr();
	    	CustomerDAO cust = new CustomerDAO();
	    	cust.deleteCustomer(cnr);
	    	customertable.getItems().clear();
	    	customertable.setItems(getCustomers());
	    }

	    @FXML
	    void changeCustomer(ActionEvent event) {

	    }

	    @FXML
	    void searchCustomer(ActionEvent event) {

			customertable.setItems(getCustomer(searchfield.getText()));
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
		customertable.setItems(getCustomers());
	}

	@FXML
    public void gotoOrder(ActionEvent event) throws IOException {
		int selectedIndex = customertable.getSelectionModel().getSelectedIndex();
		int knr = 0;
    	if(selectedIndex >= 0){
    		knr = customertable.getItems().get(selectedIndex).getCnr();
    		CustomerDAO cust = new CustomerDAO();
    		cust.setCustomerForOrder(knr);
    		openNewScene(event);
    	}
	}

	public void openNewScene(ActionEvent event) throws IOException{

		FXMLLoader loader = new FXMLLoader();
		Parent parent_order = FXMLLoader.load(Main.class.getResource("Order.fxml"));
		Scene order = new Scene(parent_order,1070,850);
		Controller_order controller = loader.getController();
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(order);
		primaryStage.show();

	}

	public ObservableList<Customer> getCustomers(){
		CustomerDAO cust = new CustomerDAO();
		customerlist.addAll(cust.getCustomers());
		return customerlist;
	}

	public ObservableList<Customer> getCustomer(String name){
		CustomerDAO cust = new CustomerDAO();
		customerlist.removeAll(getCustomers());
		customerlist.addAll(cust.getCustomer(name));
		return customerlist;
	}

	public void openNewStage(ActionEvent event) throws IOException{
		FXMLLoader loader = new FXMLLoader();
		Parent parent_order = FXMLLoader.load(Main.class.getResource("NewCustomer.fxml"));
		Scene order = new Scene(parent_order,750,450);
		Controller_NewCustomer controller = loader.getController();
		secondStage.setScene(order);
//		secondStage.initOwner(mainApp.getPrimaryStage());
		secondStage.show();
	}

	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;

	}

	public void refresh(){

		System.out.println(customertable.getItems());

//		customertable.getItems().clear();
//		System.out.println(customertable.getItems());
//		CustomerDAO customer = new CustomerDAO();
//		customerlist.clear();
//		customerlist.addAll(customer.getCustomers());

//		System.out.println(customertable.getItems());

	}

}
