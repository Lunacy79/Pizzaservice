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

	private Stage primaryStage;
	private ObservableList<Customer>customerlist;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage=primaryStage;
		
		FXMLLoader loader = new FXMLLoader();
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

	public Stage getPrimaryStage(){
		return this.primaryStage;
	}
	
	public ObservableList<Customer> getCustomers(){
		return this.customerlist;
	}

}
