package application;

import java.awt.Checkbox;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DAO.CustomerDAO;
import DAO.OrderDAO;
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
import javafx.scene.layout.Pane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;
import model.Pizza;

public class Controller_order implements Initializable {

	private OrderDAO order = new OrderDAO();
	
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
    private CheckBox[] cbs1;

    @FXML
    private FlowPane containertoppings2;
    private ArrayList<String> toppinglist2 = new ArrayList<String>();
    private CheckBox[] cbs2;

    @FXML
    private Label toppingprice1;

    @FXML
    private Label toppingprice2;

    @FXML
    private Pane orderpane;
    
    @FXML
    private TreeView<String> orderlist;
    TreeItem<String> root = new TreeItem<>("root");
    

    @FXML
    private Button pizzaconfirm;
    private ObservableList<Pizza> pizzalist = FXCollections.observableArrayList();
    PizzaDAO pizza = new PizzaDAO();
    private int onr;


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
    
    @FXML
    void orderPizza(ActionEvent event) {
    	int selectedIndex = tableViewPizza.getSelectionModel().getSelectedIndex();
    	String size = pizza.getPizzas().get(selectedIndex).getSize();
    	order.setPizza(onr,size);
    	int pnr = order.getPnr(onr);
    	ArrayList<String> orderedtoppings = new ArrayList<String>();
    	for(int i=0; i<toppinglist1.size(); i++){
	    	if(cbs1[i].isSelected()==true){
	    		orderedtoppings.add(cbs1[i].getText());
	    	}
    	}
    	for(int i = 0; i<toppinglist2.size(); i++){
    		if(cbs2[i].isSelected()==true){
        		orderedtoppings.add(cbs2[i].getText());
        	}
    	}
    	order.setToppings(pnr,orderedtoppings);
    	
    	root.setExpanded(true);
    	orderlist.setRoot(root);
    	orderlist.setShowRoot(false);
    	TreeItem<String> neu = new TreeItem<> (size);
    	root.getChildren().add(neu);
    	for(int i = 0; i<orderedtoppings.size();i++){
    		neu.getChildren().add(new TreeItem<>(orderedtoppings.get(i)));
    	}
    	
		
    }


    @Override
	public void initialize(final URL location, final ResourceBundle resources){
    	
    	custshow.setText(order.getCustomerForOrder());
    	onr = order.getOnr();

		colpizza.setCellValueFactory(new PropertyValueFactory <Pizza,String>("size"));
		colprice.setCellValueFactory(new PropertyValueFactory <Pizza,Double>("price"));
		PizzaDAO pizza = new PizzaDAO();
		pizzalist.addAll(pizza.getPizzas());
		getPizzas();

		ToppingDAO topping = new ToppingDAO();
		toppinglist1 = topping.getToppings1();
		cbs1 = new CheckBox[toppinglist1.size()];
		for( int i = 0; i<toppinglist1.size(); i++){
			CheckBox check = cbs1[i] = new CheckBox(toppinglist1.get(i));
			containertoppings.getChildren().add(cbs1[i]);
		}
		
		toppinglist2 = topping.getToppings2();
		cbs2 = new CheckBox[toppinglist2.size()];
		for( int i = 0; i<toppinglist2.size(); i++){
			CheckBox check = cbs2[i] = new CheckBox(toppinglist2.get(i));
			containertoppings2.getChildren().add(cbs2[i]);
		}
		
    	root.setExpanded(true);
    	orderlist.setRoot(root);
    	orderlist.setShowRoot(false);
	}

    public void getPizzas(){
		tableViewPizza.setItems(pizzalist);
	}



}
