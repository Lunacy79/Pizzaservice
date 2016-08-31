package application;

import java.util.ArrayList;

import DAO.CustomerDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;
import model.Customer;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;




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

		Scene scene = new Scene(root,600,800);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Hallo");
		primaryStage.show();


	}

	public static void main(String[] args) {
		launch(args);
	}

	public ObservableList<Customer> getCustomers(){

		return this.customerlist;
	}
}
