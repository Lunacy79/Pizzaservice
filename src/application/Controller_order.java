package application;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.PizzaDAO;
import model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
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

	}

    public void getPizzas(){
		PizzaDAO pizza = new PizzaDAO();
		pizzalist.addAll(pizza.getPizzas());
		tableViewPizza.setItems(pizzalist);
	}



}
