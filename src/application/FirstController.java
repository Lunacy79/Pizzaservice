package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import DAO.CustomerDAO;
import javafx.fxml.Initializable;


public class FirstController {


	  @FXML
	  private TableView<Customer> customertable;
	  private TableColumn<Customer, String> colcnr = new TableColumn("Kundennummer");
	  private TableColumn<Customer, String> collname = new TableColumn("Nachname");
	  private TableColumn<Customer, String> colfname = new TableColumn("Vorname");
	  private TableColumn<Customer, String> colstreet = new TableColumn("Straﬂe");
	  private TableColumn<Customer, String> colnr = new TableColumn("Hausnr");
	  private TableColumn<Customer, String> colplz = new TableColumn("Postleitzahl");
	  private TableColumn<Customer, String> colcity = new TableColumn("Ort");
	  private TableColumn<Customer, String> colphone = new TableColumn("Telefon");




	    @FXML
	    private Button custdel;





	    @FXML
	    private TextField searchfield;

	    @FXML
	    private Tab menutab;

	    @FXML
	    private Button custadd;

	    @FXML
	    private Tab ordertab;

	    @FXML
	    private Tab custtab;

	    @FXML
	    private Button custchange;

	    @FXML
	    void addCustomer(ActionEvent event) {

	    }

	    @FXML
	    void deleteCustomer(ActionEvent event) {

	    }

	    @FXML
	    void changeCustomer(ActionEvent event) {

	    }

	    @FXML
	    void searchCustomer(ActionEvent event) {

	    }


		public void initialize(final URL location, final ResourceBundle resources){
		  ArrayList<Customer> customers = new ArrayList<Customer>();
		  CustomerDAO customer = new CustomerDAO();
		  customers = customer.getCustomers();
		  ObservableList<Customer> customerlist = FXCollections.observableArrayList(customers);

		  TableView tableView = new TableView();
		  tableView.getColumns().addAll(colcnr,collname,colfname,colstreet,colnr,colplz,colcity,colphone);
		  tableView.setItems(customerlist);

		  colcnr.setCellValueFactory(new PropertyValueFactory <Customer,String>("Kundennummer"));
		  collname.setCellValueFactory(new PropertyValueFactory <Customer,String>("Nachname"));
		  colfname.setCellValueFactory(new PropertyValueFactory <Customer,String>("Vorname"));
		  colstreet.setCellValueFactory(new PropertyValueFactory <Customer,String>("Straﬂe"));
		  colnr.setCellValueFactory(new PropertyValueFactory <Customer,String>("Hausnr"));
		  colplz.setCellValueFactory(new PropertyValueFactory <Customer,String>("Postleitzahl"));
		  colcity.setCellValueFactory(new PropertyValueFactory <Customer,String>("Ort"));
		  colphone.setCellValueFactory(new PropertyValueFactory <Customer,String>("Telefonnummer"));

		}

}
