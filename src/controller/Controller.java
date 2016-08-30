package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.CustomerDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;



public class Controller implements Initializable {

	

	@FXML
    private TextField name;

    @FXML
    private Button searchbtn;
    
    @FXML
	private Button actiontarget;


    @FXML
    void customerSearch(ActionEvent event)  {

    CustomerDAO customer = new CustomerDAO();
	actiontarget.setText(customer.getCustomer(name.getText()).toString());
    	
	
    }

	@Override
	public void initialize(final URL location, final ResourceBundle resources){

	}

	
}
