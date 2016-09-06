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
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
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
    private TreeTableView<Order> orderlist;
    private TreeItem<Order> root = new TreeItem<>(new Order("root", 0.00));

    @FXML
    private TreeTableColumn<Order,String> ordercol;


    @FXML
    private TreeTableColumn<Order, Double> pricecol;


    @FXML
    private Label totalcost;
    double value = 0;

    @FXML
    private Button addbtn;

    @FXML
    private Button deletebtn;

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
    	double price = pizza.getPizzas().get(selectedIndex).getPrice();
    	tableViewPizza.getSelectionModel().clearSelection();
    	order.setPizza(onr,size);
    	int pnr = order.getPnr(onr);
    	ArrayList<String> orderedtoppings = new ArrayList<String>();
    	orderedtoppings.clear();
    	for(int i=0; i<toppinglist1.size(); i++){
	    	if(cbs1[i].isSelected()==true){
	    		orderedtoppings.add(cbs1[i].getText());
	    		cbs1[i].setSelected(false);
	    	}
    	}
    	for(int i = 0; i<toppinglist2.size(); i++){
    		if(cbs2[i].isSelected()==true){
        		orderedtoppings.add(cbs2[i].getText());
        		cbs2[i].setSelected(false);
        	}
    	}
    	order.setToppings(pnr,orderedtoppings);


    	root.setExpanded(true);
    	orderlist.setRoot(root);
    	orderlist.setShowRoot(false);
    	TreeItem<Order> neu = new TreeItem<> (new Order(size,price));
    	root.getChildren().add(neu);
    	orderlist.getColumns().setAll(ordercol,pricecol);
    	ordercol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Order, String> param) ->
        new ReadOnlyStringWrapper(param.getValue().getValue().getItem()));
    	pricecol.setCellValueFactory(new Callback<CellDataFeatures<Order, Double>, ObservableValue<Double>>() {
    		  @Override
    		  public ObservableValue<Double> call(CellDataFeatures<Order, Double> p)          {

    		      return p.getValue().getValue().priceProperty().asObject();
    		 }
    		 });

    	ArrayList<Order> toppings = new ArrayList<Order>();
    	toppings=order.getToppings(pnr);
    	value = value + price;
    	for(int i = 0; i<orderedtoppings.size();i++){
    		neu.getChildren().add(new TreeItem<>(toppings.get(i)));
    		value = value + toppings.get(i).getPrice();
    	}

    	totalcost.setText(Double.toString(value));

    }

    @FXML
    void addTopping(ActionEvent event) {

    }

    @FXML
    void deleteItem(ActionEvent event) {

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
