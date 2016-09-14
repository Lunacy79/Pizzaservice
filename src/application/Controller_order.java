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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;
import model.Pizza;

public class Controller_order implements Initializable {

	private OrderDAO order = new OrderDAO();

    @FXML
    private Label custshow;

    @FXML
    private FlowPane pizzacontainer;

    @FXML
    private RadioButton[] rbtn;
    ToggleGroup group = new ToggleGroup();

    @FXML
    private FlowPane containertoppings;
    private ArrayList<Topping> toppinglist1 = new ArrayList<Topping>();
    private Button[] pls1;
    private Button[] mns1;
    private Label[] lbl1;

    @FXML
    private FlowPane containertoppings2;
    private ArrayList<Topping> toppinglist2 = new ArrayList<Topping>();
    private Button[] pls2;
    private Button[] mns2;
    private Label[] lbl2;

    @FXML
    private Label toppingprice1;

    @FXML
    private Label toppingprice2;

    @FXML
    private Pane orderpane;

    @FXML
    private TreeTableView<Order> pizzaorder;
    private TreeItem<Order> pizzaroot = new TreeItem<>(new Order("pizzaroot", 0.00));
    TreeItem<Order> neu = new TreeItem<> ();
    int top;
    ArrayList<String> toplist = new ArrayList<>();

    @FXML
    private TreeTableColumn<Order, String> colpizza;

    @FXML
    private TreeTableColumn<Order, Double> colprice;

    @FXML
    private Label pizzacost;

    @FXML
    private TreeTableView<Order> orderlist;
    private TreeItem<Order> root = new TreeItem<>(new Order("root", 0.00));
    int opizza;
    ArrayList<Order> opizzas;

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
    private Main mainApp;
    private int items;



    void choosePizza() {
    	String size = "";
    	double price = 0.00;
    	double tprice1 = 0.00;
    	double tprice2 = 0.00;
    	for(int i = 0; i<pizzalist.size();i++){
    		if(rbtn[i].isSelected()==true){
        		tprice1 = pizzalist.get(i).getTopping1();
        		tprice2 = pizzalist.get(i).getTopping2();
        		toppingprice1.setText("je " + tprice1 + " Euro");
        		toppingprice2.setText("je " + tprice2 + " Euro");
        		size = pizzalist.get(i).getSize();
        		price = pizzalist.get(i).getPrice();
    		}
    	}
    	neu=new TreeItem<>(new Order(size,price));
    	neu.setExpanded(true);
    	pizzaroot.getChildren().add(neu);

    	for( int i = 0; i<toppinglist1.size(); i++){
    		String name1 = toppinglist1.get(i).getName();
    		double price1 = tprice1;
			pls1[i].setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	chooseTopping(name1, price1);
	            }
	        });
			mns1[i].setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	deleteTopping(name1, price1);
	            }
	        });
    	}

    	for( int i = 0; i<toppinglist2.size(); i++){
    		String name2 = toppinglist2.get(i).getName();
    		double price2 = tprice2;
			pls2[i].setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	                chooseTopping(name2, price2);
	            }
	        });
			mns2[i].setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	                deleteTopping(name2, price2);
	            }
	        });
    	}
    }

    void chooseTopping(String name, double price){
    	toplist.add(name);
    	items=items+1;
    	neu.getChildren().add(new TreeItem(new Order(name,price)));
    }

    void deleteTopping(String name, double price){
    	top= toplist.indexOf(name);
    	System.out.println(topps);
    	if(topps>=0){
	    	neu.getChildren().remove(top);
	    	toplist.remove(top);
	    	items=items-1;
    	}
    }

    @FXML
    void orderPizza(ActionEvent event) {
    	ArrayList<String> orderedtoppings = new ArrayList<String>();
    	ArrayList<Order> tops = new ArrayList<Order>();
    	orderedtoppings.clear();
    	Order piz = pizzaroot.getChildren().get(0).getValue();
    	opizzas.add(piz);
    	String size = piz.getItem();
    	double price = piz.getPrice();
    	order.setPizza(onr,size);
    	int pnr = order.getPnr(onr);
    	Order top = null;
    	neu2 = new TreeItem<> (piz);
    	neu2.setValue(piz);
    	neu2.setExpanded(true);
    	root.getChildren().add(neu2);
    	value = value + price;
    	for(int i = 0; i<items;i++){
    		top = neu.getChildren().get(i).getValue();
    		tops.add(top);
    		opizzas.add(top);
    		orderedtoppings.add(top.getItem());
    		value = value + top.getPrice();
    	}
    	for(int i = 0; i<tops.size();i++){
    		neu2.getChildren().add(new TreeItem<>(tops.get(i)));
    	}
    	items=0;
    	order.setToppings(pnr,orderedtoppings);
    	totalcost.setText(Double.toString(value));
		neu.getChildren().clear();
		if(group.getSelectedToggle()!= null){
			group.getSelectedToggle().setSelected(false);
		}
		toppslist.clear();
    }

    @FXML
    void addTopping(ActionEvent event) {
    	int index = orderlist.getSelectionModel().getSelectedIndex();
    	int pizzas = order.getPizzas(onr).size();
    	System.out.println(opizzas);
		if(orderlist.getTreeItemLevel(orderlist.getTreeItem(index))==1){
			neu.setValue(opizzas.get(index));
			pizzaroot.getChildren().add(neu);
			int j = index+1;
			int count= 0;
			while(orderlist.getTreeItemLevel(orderlist.getTreeItem(j))==2){
				neu.getChildren().add(new TreeItem(opizzas.get(j)));
				j++;
				count=count + 1;
			}
		}
    }

    @FXML
    void deleteItem(ActionEvent event) {

    }


    @Override
	public void initialize(final URL location, final ResourceBundle resources){

    	custshow.setText(order.getCustomerForOrder());
    	onr = order.getOnr();
		PizzaDAO pizza = new PizzaDAO();
		pizzalist.addAll(pizza.getPizzas());
		rbtn = new RadioButton[pizzalist.size()];
		for(int i=0;i<pizzalist.size();i++){
			RadioButton radiobtn = rbtn[i] = new RadioButton(pizzalist.get(i).getSize() + ", " + pizzalist.get(i).getPrice());
			pizzacontainer.getChildren().add(rbtn[i]);
			rbtn[i].setToggleGroup(group);
			rbtn[i].setUserData(i);
		}
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		      public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
		    	  if (group.getSelectedToggle() != null) {
		    		pizzaroot.getChildren().clear();
//		    		neu.getChildren().clear();
		          	choosePizza();
		          }
		      }
		    });

		ToppingDAO topping = new ToppingDAO();
		toppinglist1 = topping.getToppings1();
		pls1 = new Button[toppinglist1.size()];
		lbl1 = new Label[toppinglist1.size()];
		mns1 = new Button[toppinglist1.size()];
		for( int i = 0; i<toppinglist1.size(); i++){
			Button pls = pls1[i] = new Button("+");
			pls1[i].setPadding(Insets.EMPTY);
			pls1[i].setStyle("-fx-margin:0 0 0 10px; -fx-pref-height:15px; -fx-pref-width:15px;");
			Label lbl = lbl1[i] = new Label(toppinglist1.get(i));
			Button mns = mns1[i] = new Button("-");
			mns1[i].setPadding(Insets.EMPTY);
			mns1[i].setStyle("-fx-margin:0 10px 0 0; -fx-pref-height:15px; -fx-pref-width:15px;");
			Label elbl = new Label("");
			elbl.setStyle("-fx-padding: 0 10px 0 10px;");
			containertoppings.getChildren().addAll(pls1[i],lbl1[i],mns1[i],elbl);
		}

		toppinglist2 = topping.getToppings2();
		pls2 = new Button[toppinglist2.size()];
		lbl2 = new Label[toppinglist2.size()];
		mns2 = new Button[toppinglist2.size()];
		for( int i = 0; i<toppinglist2.size(); i++){
			Button pls = pls2[i] = new Button("+");
			pls2[i].setPadding(Insets.EMPTY);
			pls2[i].setStyle("-fx-margin:0 0 0 10px; -fx-pref-height:15px; -fx-pref-width:15px;");
			Label lbl = lbl2[i] = new Label(toppinglist2.get(i));
			Button mns = mns2[i] = new Button("-");
			mns2[i].setPadding(Insets.EMPTY);
			mns2[i].setStyle("-fx-margin:0 10px 0 0; -fx-pref-height:15px; -fx-pref-width:15px;");
			Label elbl = new Label("");
			elbl.setStyle("-fx-padding: 0 10px 0 10px;");
			containertoppings2.getChildren().addAll(pls2[i],lbl2[i],mns2[i],elbl);
		}

		pizzaroot.setExpanded(true);
    	pizzaorder.setRoot(pizzaroot);
    	pizzaorder.setShowRoot(false);
//    	pizzaorder.getColumns().setAll(colpizza,colprice);
    	colpizza.setCellValueFactory((TreeTableColumn.CellDataFeatures<Order, String> param) ->
        new ReadOnlyStringWrapper(param.getValue().getValue().getItem()));
    	colprice.setCellValueFactory(new Callback<CellDataFeatures<Order, Double>, ObservableValue<Double>>() {
    		  @Override
    		  public ObservableValue<Double> call(CellDataFeatures<Order, Double> p)          {
    		      return p.getValue().getValue().priceProperty().asObject();
    		 }
    		 });
    	neu = new TreeItem<>();

    	root.setExpanded(true);
    	orderlist.setRoot(root);
    	orderlist.setShowRoot(false);
//    	orderlist.getColumns().setAll(ordercol,pricecol);
    	ordercol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Order, String> param) ->
        new ReadOnlyStringWrapper(param.getValue().getValue().getItem()));
    	pricecol.setCellValueFactory(new Callback<CellDataFeatures<Order, Double>, ObservableValue<Double>>() {
    		  @Override
    		  public ObservableValue<Double> call(CellDataFeatures<Order, Double> p)          {
    		      return p.getValue().getValue().priceProperty().asObject();
    		 }
    		 });
    	opizzas=new ArrayList<Order>();
	}

    public void setMainApp(Main mainApp){
		this.mainApp = mainApp;

	}
    
    



}
