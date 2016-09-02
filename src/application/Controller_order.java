package application;

import java.awt.Checkbox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DAO.CustomerDAO;
import DAO.PizzaDAO;
import DAO.ToppingDAO;
import model.Order;
import model.Topping;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;
import model.Pizza;

public class Controller_order implements Initializable {

    @FXML
    private Label custshow;

    @FXML
    private TableView<Pizza> tableViewPizza;

    @FXML
    private TableColumn<Pizza, String> colpizza;

    @FXML
    private TableColumn<Pizza, Double> colprice;

    @FXML
    private FlowPane containertoppings;
    private ArrayList<String> toppinglist1 = new ArrayList<String>();

    @FXML
    private FlowPane containertoppings2;
    private ArrayList<String> toppinglist2 = new ArrayList<String>();

    @FXML
    private Label toppingprice1;

    @FXML
    private Label toppingprice2;

    @FXML
    private TreeView<String> orderlist;

    @FXML
    private Button pizzaconfirm;
    private ObservableList<Pizza> pizzalist = FXCollections.observableArrayList();
    PizzaDAO pizza = new PizzaDAO();


    @FXML
    void choosePizza(MouseEvent event) {
    	int selectedIndex = tableViewPizza.getSelectionModel().getSelectedIndex();
    	double topping1 = 0;
    	double topping2 = 0;
    	if(selectedIndex >= 0){
    		topping1 = pizza.getPizzas().get(selectedIndex).getTopping1();
    		topping2 = pizza.getPizzas().get(selectedIndex).getTopping2();
    		toppingprice1.setText("je " + topping1 + " Euro");
    		toppingprice2.setText("je " + topping2 + " Euro");
    	}
    }

    @Override
	public void initialize(final URL location, final ResourceBundle resources){

    	CustomerDAO cust = new CustomerDAO();

    	custshow.setText(cust.getCustomerForOrder());

		colpizza.setCellValueFactory(new PropertyValueFactory <Pizza,String>("size"));
		colprice.setCellValueFactory(new PropertyValueFactory <Pizza,Double>("price"));
		PizzaDAO pizza = new PizzaDAO();
		pizzalist.addAll(pizza.getPizzas());
		getPizzas();

		ToppingDAO topping = new ToppingDAO();
		toppinglist1 = topping.getToppings1();
		for( int i = 0; i<toppinglist1.size(); i++){
			CheckBox check = new CheckBox(toppinglist1.get(i));
			containertoppings.getChildren().add(check);
		}

		toppinglist2 = topping.getToppings2();
		for( int i = 0; i<toppinglist2.size(); i++){
			CheckBox check = new CheckBox(toppinglist2.get(i));
			containertoppings2.getChildren().add(check);
		}

		TreeItem<String> root = new TreeItem<>("Root Node");
		root.setExpanded(true);
		root.getChildren().addAll(
				new TreeItem<>("Belag"),new TreeItem<>("Belag2")
				);
		orderlist.setRoot(root);
	}

    public void getPizzas(){
		tableViewPizza.setItems(pizzalist);
	}



}
