package application;

import java.awt.Checkbox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.TreeView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Pizza;

public class Controller_order implements Initializable {

    @FXML
    private TextField custshow;

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
    private TreeView<Order> listview;

    @FXML
    private Button pizzaconfirm;
    private ObservableList<Pizza> pizzalist = FXCollections.observableArrayList();


    @FXML
    void choosePizza(ActionEvent event) {

    }

    @Override
	public void initialize(final URL location, final ResourceBundle resources){

		colpizza.setCellValueFactory(new PropertyValueFactory <Pizza,String>("size"));
		colprice.setCellValueFactory(new PropertyValueFactory <Pizza,Double>("price"));
		getPizzas();

		ToppingDAO topping = new ToppingDAO();
		toppinglist1 = topping.getToppings1();
		for( int i = 0; i<toppinglist1.size(); i++){
			CheckBox check = new CheckBox(toppinglist1.get(i));
			containertoppings.getChildren().add(check);
		}
		toppingprice1.setText("Hallo");
		toppinglist2 = topping.getToppings2();
		for( int i = 0; i<toppinglist2.size(); i++){
			CheckBox check = new CheckBox(toppinglist2.get(i));
			containertoppings2.getChildren().add(check);
		}
	}

    public void getPizzas(){
		PizzaDAO pizza = new PizzaDAO();
		pizzalist.addAll(pizza.getPizzas());
		tableViewPizza.setItems(pizzalist);
	}



}
