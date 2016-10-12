package application;

import java.io.IOException;
import DAO.CustomerDAO;
import application.Main;
import model.Customer;
import model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class Controller  {

	public Controller(){

	}

	@FXML
    private TabPane tabpane;

	@FXML
    private Tab bestellungstab;

	@FXML
    private Tab custtab;

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
    private TableColumn<Order, Integer> closed;

	@FXML
    public TableView<Customer> customertable;
    public ObservableList<Customer> customerlist = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Customer,Integer> colcnr;

    @FXML
    private TableColumn<Customer,String> collname;

    @FXML
    private TableColumn<Customer,String> colfname;

    @FXML
    private TableColumn<Customer,String> colstreet;

    @FXML
    private TableColumn<Customer,String> colnr;

	@FXML
    private TableColumn<Customer,String> colplz;

	@FXML
	private TableColumn<Customer,String> colcity;

    @FXML
    private TableColumn<Customer,String> colphone;

    @FXML
    private Button custdel;

    @FXML
    private Button custadd;

    @FXML
    private Button custchange;

    @FXML
    private Button custlistorder;

    @FXML
    private Button searchbtn;

    @FXML
    private TextField searchfield;

    public Main mainApp;

    @FXML
    void addCustomer(ActionEvent event) throws IOException {
    	mainApp.addCustomer(event);
    }

    @FXML
    void deleteCustomer(ActionEvent event) {
    	int index = customertable.getSelectionModel().getSelectedIndex();
		int cnr = 0;
    	if(index >= 0){
	    	cnr = mainApp.getCustomerlist().get(index).getCnr();
	    	CustomerDAO cust = new CustomerDAO();
	    	cust.deleteCustomer(cnr);
	    	mainApp.setCustomerlist();
    	}
    }

    @FXML
    void changeCustomer(ActionEvent event) throws IOException {
    	int index = customertable.getSelectionModel().getSelectedIndex();
		int cnr = 0;
    	if(index >= 0){
	    	cnr = mainApp.getCustomerlist().get(index).getCnr();
	    	CustomerDAO cust = new CustomerDAO();
	    	Customer customer = cust.getSingleCustomer(cnr);
	    	mainApp.changeCustomer(event, customer);
    	}
    }

    @FXML
    void showPrint(ActionEvent event) throws IOException {
    	int index = ordertable.getSelectionModel().getSelectedIndex();
		int onr = 0;
    	if(index >= 0){
	    	onr = mainApp.getOrderlist().get(index).getOrdernumber();
	    	mainApp.showPrint(event, onr);
    	}
    }

    @FXML
    void searchCustomer(ActionEvent event) {
		customertable.setItems(mainApp.getCustomer(searchfield.getText()));
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

		orderonr.setCellValueFactory(new PropertyValueFactory <Order,Integer>("ordernumber"));
		ordercnr.setCellValueFactory(new PropertyValueFactory <Order,Integer>("cnr"));
		orderlname.setCellValueFactory(new PropertyValueFactory <Order,String>("lname"));
		orderfname.setCellValueFactory(new PropertyValueFactory <Order,String>("fname"));
		closed.setCellValueFactory(new PropertyValueFactory <Order,Integer>("closed"));
	}

	@FXML
    public void gotoOrder(ActionEvent event) throws IOException {
		int selectedIndex = customertable.getSelectionModel().getSelectedIndex();
		int knr = 0;
    	if(selectedIndex >= 0){
    		knr = customertable.getItems().get(selectedIndex).getCnr();
    		System.out.println(customertable.getItems().get(selectedIndex).getCnr());
    		CustomerDAO cust = new CustomerDAO();
    		cust.setCustomerForOrder(knr);
    		mainApp.openOrder(event);
    	}
	}

	public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
		ordertable.setItems(mainApp.getOrderlist());
		customertable.setItems(mainApp.getCustomerlist());
		start();
	}

	public void refresh(){
		customertable.setItems(mainApp.getCustomerlist());
		ordertable.setItems(mainApp.getOrderlist());
	}

	public void openBestellung(Main mainApp, ObservableList<Order> orderlist){
		this.mainApp = mainApp;
		tabpane.getSelectionModel().select(1);
		customertable.setItems(mainApp.getCustomerlist());
		ordertable.setItems(mainApp.getOrderlist());
		start();
	}

}
