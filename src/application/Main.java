package application;

import java.io.IOException;
import java.util.ArrayList;

import DAO.CustomerDAO;
import DAO.PizzaDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;
import model.Customer;
import model.Pizza;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;




public class Main extends Application {

	private ObservableList<Customer> customerlist = FXCollections.observableArrayList();

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		CustomerDAO cust = new CustomerDAO();
		customerlist.addAll(cust.getCustomers());
		loader.setLocation(getClass().getResource("test.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Controller controller = loader.getController();
		controller.setMainApp(this);

		Scene scene = new Scene(root,1070,850);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Pizzaservice Alberto");
		primaryStage.show();


	}

	public static void main(String[] args) {
		launch(args);
	}

	public ObservableList<Customer> getCustomers(){

		return this.customerlist;
	}

	public ObservableList<Customer> getCustomer(String name){
		CustomerDAO cust = new CustomerDAO();
		customerlist.removeAll(getCustomers());
		customerlist.addAll(cust.getCustomer(name));
		return customerlist;
	}

}
