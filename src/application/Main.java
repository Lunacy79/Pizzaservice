package application;

import java.io.IOException;
import java.util.ArrayList;

import DAO.CustomerDAO;
import DAO.OrderDAO;
import DAO.PizzaDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;
import model.Customer;
import model.Pizza;
import model.Order;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

public class Main extends Application {

	private Stage primaryStage;
	private Stage secondStage;
	private ObservableList<Customer> customerlist = FXCollections.observableArrayList();
	private CustomerDAO cust = new CustomerDAO();
	private Customer customer;

	private ObservableList<Order> orderlist = FXCollections.observableArrayList();
	private OrderDAO orders = new OrderDAO();
	private Order order = new Order();
	private int onr;

	@Override
	public void start(Stage primaryStage) throws Exception {
		customerlist.addAll(cust.getCustomers());
		orderlist.addAll(orders.getOrders());
		System.out.println(orders.getOrders());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("test.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Controller controller = loader.getController();
		Scene scene = new Scene(root,1070,850);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pizzaservice Alberto");
		primaryStage.show();
		controller.setMainApp(this);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage(){
		return this.primaryStage;
	}

	public Stage getSecondStage(){
		return this.secondStage;
	}

	public ObservableList<Customer> getCustomerlist(){
		return this.customerlist;
	}

	public void setCustomerlist(){
		CustomerDAO cust = new CustomerDAO();
		customerlist.clear();
		customerlist.addAll(cust.getCustomers());
	}

	public ObservableList<Order> getOrderlist(){
		return this.orderlist;
	}

	public void setOrderlist(ObservableList<Order> orderlist){
		this.orderlist=orderlist;

	}

	public ObservableList<Customer> getCustomer(String name){
		CustomerDAO cust = new CustomerDAO();
		customerlist.clear();
		customerlist.addAll(cust.getCustomer(name));
		return customerlist;
	}

	public void openOrder(ActionEvent event) throws IOException{

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Order.fxml"));
		Parent parent_order = loader.load();
		Scene order = new Scene(parent_order,1070,850);

		Controller_order controller = loader.getController();
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(order);
		primaryStage.show();
		controller.setMainApp(this);

	}

	public void goBack(ActionEvent event,ArrayList<Order> orders) throws IOException{
		orderlist.addAll(orders);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("test.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Scene scene = new Scene(root,1070,850);
		Controller controller = loader.getController();
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
		controller.setMainApp(this);
		controller.openBestellung(orderlist);
	}

	public void showPrint(ActionEvent event, int onr) throws IOException{
		setOnr(onr);
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Print.fxml"));
		AnchorPane parent_print =(AnchorPane) loader.load();
		Scene print = new Scene(parent_print,500,600);

		PrintController controller = loader.getController();
		Stage thirdStage = new Stage();
		thirdStage.setScene(print);
		thirdStage.show();
		controller.setMainApp(this);
	}

	public int getOnr() {
		return onr;
	}

	public void setOnr(int onr) {
		this.onr = onr;
	}

	public void setCustomer(Customer customer){
		this.customer = customer;
	}

	public Customer getCustomer(){
		return this.customer;
	}

	public void changeCustomer(ActionEvent event, Customer customer) throws IOException {
		setCustomer(customer);
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ChangeCustomer.fxml"));
		AnchorPane change =(AnchorPane) loader.load();
		Scene order = new Scene(change,750,450);
		ControllerChangeCustomer controller = loader.getController();
		Stage thirdStage = new Stage();
		thirdStage.setScene(order);
		thirdStage.show();
		controller.getCustomer(customer);
		controller.setMainApp(this);
    }

	public void addCustomer(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("NewCustomer.fxml"));
		Parent parent_order = loader.load();
		Scene order = new Scene(parent_order,750,450);
		Controller_NewCustomer controller = loader.getController();
		Stage secondStage = new Stage();
		secondStage.setScene(order);
//		secondStage.initOwner(mainApp.getPrimaryStage());
		secondStage.show();
		controller.setMainApp(this);
    }
}
