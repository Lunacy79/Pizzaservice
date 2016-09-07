package application;

import java.sql.SQLException;

import DAO.CustomerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;

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
    void submitNewCustomer(ActionEvent event) throws SQLException {
    	CustomerDAO customer = new CustomerDAO();
    	customer.addCustomer(fname.getText(), lname.getText(),street.getText() , nr.getText(), plz.getText(), city.getText(), telefon.getText());
    	
    	customerlist.addAll(customer.getCustomers());
    	Controller.secondStage.close();
    }

}
