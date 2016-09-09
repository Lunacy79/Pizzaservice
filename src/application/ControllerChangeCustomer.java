package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.CustomerDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;
import application.Main;

public class ControllerChangeCustomer implements Initializable {

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
    private Main mainApp;
    private Customer customer;

    @FXML
    void submitNewCustomer(ActionEvent event) throws SQLException, IOException {
    	CustomerDAO cust = new CustomerDAO();
    	String name = lname.getText();
    	cust.addCustomer(fname.getText(), name, street.getText() , nr.getText(), plz.getText(), city.getText(), telefon.getText());
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("test.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Controller controller = loader.getController();
		Scene scene = new Scene(root,1070,850);
		int cnr = customer.getCnr();
		cust.changeCustomer(cnr, fname.getText(), name, street.getText() , nr.getText(), plz.getText(), city.getText(), telefon.getText());
		mainApp.setCustomerlist();
		((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @Override
	public void initialize(final URL location, final ResourceBundle resources){

    	System.out.println("hhwe" + this.customer);



	}

    public void getCustomer(Customer customer){
    	this.customer = customer;
    	System.out.println(customer);
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