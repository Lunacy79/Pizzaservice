package application;

import java.io.IOException;
import java.sql.SQLException;
import DAO.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;
import application.Main;


public class Controller_NewCustomer {

	private Main mainApp;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;
    
    @FXML
    private TextField street;

    @FXML
    private TextField nr;
    
    @FXML
    private TextField plz;

    @FXML
    private TextField city;

    @FXML
    private TextField telefon;

    @FXML
    private Button addcustbtn;
    
    @FXML
    void submitNewCustomer(ActionEvent event) throws SQLException, IOException {
    	CustomerDAO customer = new CustomerDAO();
    	String name = lname.getText();
    	customer.addCustomer(fname.getText(), name, street.getText() , nr.getText(), plz.getText(), city.getText(), telefon.getText());
		mainApp.getCustomerlist().add(new Customer(fname.getText(), name, street.getText() , nr.getText(), plz.getText(), city.getText(), telefon.getText()));
		((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void setMainApp(Main mainApp){
		this.mainApp = mainApp;

	}
}
