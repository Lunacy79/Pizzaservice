package application;

import java.io.IOException;
import java.sql.SQLException;

import DAO.CustomerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;
import application.Main;

public class Controller_NewCustomer {



    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField nr;

    @FXML
    private TextField city;

    @FXML
    private TextField street;

    @FXML
    private TextField telefon;

    @FXML
    private Button addcustbtn;

    @FXML
    private TextField plz;
    private ObservableList<Customer> customerlist = FXCollections.observableArrayList();

    @FXML
    void submitNewCustomer(ActionEvent event) throws SQLException, IOException {
    	CustomerDAO customer = new CustomerDAO();
    	String name = lname.getText();
    	customer.addCustomer(fname.getText(), name, street.getText() , nr.getText(), plz.getText(), city.getText(), telefon.getText());

    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("test.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Controller controller = loader.getController();
		Scene scene = new Scene(root,1070,850);
		controller.refresh();
    	Controller.secondStage.close();
    }

}
