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


public class ControllerChangeCustomer {

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

    private Main mainApp;
    private Customer customer;

    @FXML
    void submitNewCustomer(ActionEvent event) throws SQLException, IOException {
    	CustomerDAO cust = new CustomerDAO();
    	String name = lname.getText();
    	cust.addCustomer(fname.getText(), name, street.getText() , nr.getText(), plz.getText(), city.getText(), telefon.getText());
		int cnr = customer.getCnr();
		cust.changeCustomer(cnr, fname.getText(), name, street.getText() , nr.getText(), plz.getText(), city.getText(), telefon.getText());
		mainApp.setCustomerlist();
		((Node)(event.getSource())).getScene().getWindow().hide();
    }	

    public void getCustomer(Customer customer){
    	this.customer = customer;
    }

    public void setMainApp(Main mainApp){
		this.mainApp = mainApp;
		customer = mainApp.getCustomer();
		fname.setText(this.customer.getFname());
    	lname.setText(this.customer.getLname());
    	street.setText(this.customer.getStreet());
    	nr.setText(this.customer.getNr());
    	plz.setText(this.customer.getPlz());
    	city.setText(this.customer.getCity());
    	telefon.setText(this.customer.getTelefon());
	}

}