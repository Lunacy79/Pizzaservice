package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import DAO.CustomerDAO;
import DAO.DrinkDAO;
import DAO.OrderDAO;
import DAO.PizzaDAO;
import DAO.ToppingDAO;
import model.Drinks;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import model.Pizza;

public class Controller_order implements Initializable {

	private OrderDAO order = new OrderDAO();
	private CustomerDAO cust = new CustomerDAO();
	private ToppingDAO topping = new ToppingDAO();

    @FXML
    private Label custshow;

    // pizzacontainer enthält die drei Radiobuttons zur Pizzawahl
    @FXML
    private FlowPane pizzacontainer;

    @FXML
    private RadioButton[] rbtn;
    ToggleGroup group = new ToggleGroup();
    private ArrayList<Pizza> orderedpizza = new ArrayList<Pizza>();
    Pizza piz;

    // enthält die Plus- und Minus-Buttons zu den jeweiligen Belägen
    @FXML
    private FlowPane containertoppings;
    private ArrayList<Topping> toppinglist1 = new ArrayList<Topping>();
    private HBox[] hbox1;
    private Button[] pls1;
    private Button[] mns1;
    private Label[] lbl1;

    @FXML
    private FlowPane containertoppings2;
    private ArrayList<Topping> toppinglist2 = new ArrayList<Topping>();
    private HBox[] hbox2;
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
    private FlowPane containerdrinks;
    private ArrayList<Drinks> drinks = new ArrayList<>();
    private ArrayList<Drinks> drinkslist = new ArrayList<>();
    private HBox[] drinkhbox;
    private Button[] pls;
    private Button[] mns;
    private Label[] lbl;

    //Treetableview für die Zusammenstellung der Pizza
    @FXML
    private TreeTableView<Order> pizzaorder;
    private TreeItem<Order> pizzaroot = new TreeItem<>(new Order("pizzaroot", 0.00));
    private TreeItem<Order> neu = new TreeItem<> ();
    private Topping top;
    private ArrayList<Topping> toplist = new ArrayList<>();
    private ArrayList<Order> topslist = new ArrayList<>();
    private ArrayList<Order> position = new ArrayList<>();

    @FXML
    private TreeTableColumn<Order, String> colpizza;

    @FXML
    private TreeTableColumn<Order, Double> colprice;

    @FXML
    private Label pizzacost;

    //Treetableview für die Bestellung
    @FXML
    private TreeTableView<Order> orderlist;
    private TreeItem<Order> root = new TreeItem<>(new Order("root", 0.00));
    TreeItem<Order> neu2 = new TreeItem<> ();
    ArrayList<Order> ordereditems;

    @FXML
    private TreeTableColumn<Order,String> ordercol;

    @FXML
    private TreeTableColumn<Order, Double> pricecol;

    @FXML
    private Label totalcost;
    double value = 0;

    //Button zur Bearbeitung einer Pizza in der Bestellung
    @FXML
    private Button addbtn;

  //Button zur Löschung einer Pizza in der Bestellung
    @FXML
    private Button deletebtn;

    //Übernahme der zusammengestellten Pizza in die Bestellliste
    @FXML
    private Button orderbtn;

    //Bestellung der Artikel in der Bestellliste
    @FXML
    private Button pizzaconfirm;
    private ObservableList<Pizza> pizzalist = FXCollections.observableArrayList();
    PizzaDAO pizza = new PizzaDAO();
    private int onr;
    private Main mainApp;

    void choosePizza() {

    	//Auswahl einer Pizza und Anzeige der entsprechenden Belagpreise
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

    	//Zwischenspeicherung der gewählten Pizzagröße
    	piz=new Pizza(size,price);

    	//Pizza -> Treetableview
    	neu=new TreeItem<>(new Order(size,price));
    	neu.setExpanded(true);
    	pizzaroot.getChildren().add(neu);

    	//Beläge -> Treetableview
    	for( int i = 0; i<toppinglist1.size(); i++){
    		String name1 = toppinglist1.get(i).getName();
    		double price1 = tprice1;
    		Topping topp = toppinglist1.get(i);
			pls1[i].setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	toplist.add(topp);
	            	chooseTopping(name1, price1, topp);
	            }
	        });
			mns1[i].setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	int index = toplist.indexOf(topp);
	            	toplist.remove(topp);
	            	deleteTopping(name1, price1, index);
	            }
	        });
    	}

    	for( int i = 0; i<toppinglist2.size(); i++){
    		String name2 = toppinglist2.get(i).getName();
    		double price2 = tprice2;
    		Topping topp = toppinglist2.get(i);
			pls2[i].setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	toplist.add(topp);
	                chooseTopping(name2, price2, topp);
	            }
	        });
			mns2[i].setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	int index = toplist.indexOf(topp);
	            	toplist.remove(topp);
	                deleteTopping(name2, price2, index);
	            }
	        });
    	}
    }

    void chooseTopping(String name, double price, Topping topp){

    	neu.getChildren().add(new TreeItem<Order>(new Order(name,price)));
    }

    void deleteTopping(String name, double price, int index){
    	if(index>=0){
    		neu.getChildren().remove(index);
    	}
    }

    @FXML
    void orderPizza(ActionEvent event) {

    	//Pizza aus Treetableview in Bestellliste
    	ArrayList<Order> tops = new ArrayList<Order>();
    	ArrayList<Order> tops1 = new ArrayList<Order>();
    	ArrayList<Order> tops2 = new ArrayList<Order>();
    	ArrayList<Topping> toplist1 = new ArrayList<>();
    	ArrayList<Topping> toplist2 = new ArrayList<>();
    	Order top = null;

    	//Auslesen aus Treetableview
    	for(int i = 0; i<toplist.size();i++){
    		top = neu.getChildren().get(i).getValue();
    		tops.add(top);
    	}

    	//Sortierung der Beläge in Preiskategorien 1 und 2
    	for(int i = 0;i<toplist.size();i++){
    		if(toplist.get(i).getPriceclass() == 1){
    			toplist1.add(toplist.get(i));
    			tops1.add(tops.get(i));
    		}
    		else{
    			toplist2.add(toplist.get(i));
    			tops2.add(tops.get(i));
    		}
    	}

    	//Speicherung in Topping-Array für eine Pizza
    	toplist.clear();
    	toplist.addAll(toplist1);
    	toplist.addAll(toplist2);

    	//Speicherung in Order-Array für eine Pizza
    	tops.clear();
    	tops.addAll(tops1);
    	tops.addAll(tops2);

    	//Auslesen der Grundpizza aus Treetableview und Speicherung in Pizza-Order-Array
    	Order pizza = pizzaroot.getChildren().get(0).getValue();
    	ordereditems.add(pizza);
    	String size = pizza.getItem();
    	double price = pizza.getPrice();

    	//Einfügen der Pizza und Beläge in Bestellungsliste
    	neu2 = new TreeItem<> ();
    	neu2.setValue(pizza);
    	neu2.setExpanded(true);
    	root.getChildren().add(neu2);
    	value = value + price;

    	//Berechnung der Belagpreise; ab dem 3. Belag gibts 10% Rabatt
    	for(int i = 0; i<toplist.size();i++){
    		top = new Order(tops.get(i).getItem(),tops.get(i).getPrice());
    		ordereditems.add(top);
    		if(i>=2){
    			tops.get(i).setPrice(tops.get(i).getPrice()*0.9);
    		}
    		neu2.getChildren().add(new TreeItem<>(tops.get(i)));
    		value = value + tops.get(i).getPrice();
    	}
    	totalcost.setText(Double.toString(value));

    	ArrayList<Topping> topps = new ArrayList<>();
    	for(int i = 0;i<tops.size();i++){
    		topps.add(new Topping(tops.get(i).getItem(),tops.get(i).getPrice()));
    	}

    	//Speichern der Pizza mit Belägen in Pizza-Arraylist
    	orderedpizza.add(new Pizza(position.size(),size,price,topps));

    	//zur Positionsbestimmung wird der Index in der Tabelle in dieser ArrayList gespeichert
    	position.add(new Order(size,price));

    	//Leeren des Treetableviews und der Pizza-Radiobuttons
		pizzaroot.getChildren().clear();
		toplist.clear();
		tops.clear();
		toplist1.clear();
		toplist2.clear();
		tops1.clear();
		tops2.clear();
		if(group.getSelectedToggle()!= null){
			group.getSelectedToggle().setSelected(false);
		}
    }

    public int getOnr() {
		return this.onr;
	}

	public void setOnr(int onr) {
		this.onr = onr;
	}

	@FXML
    void addTopping(ActionEvent event) {
    	int index = orderlist.getSelectionModel().getSelectedIndex();
    	for(int j = 0; j<orderedpizza.size(); j++){
    		if(orderlist.getSelectionModel().getModelItem(index).getValue().getItem().equalsIgnoreCase(orderedpizza.get(j).getSize())){
		    	if(orderlist.getTreeItemLevel(orderlist.getTreeItem(index))==1){
			    	for(int i = 0;i<root.getChildren().size();i++){
			    		root.getChildren().get(i).setExpanded(false);
			    	}
			    	int index2 = orderlist.getSelectionModel().getSelectedIndex();
			    	for(int i = 0;i<root.getChildren().size();i++){
			    		root.getChildren().get(i).setExpanded(true);
			    	}
			    	int index3 = root.getChildren().get(index2).getChildren().size();
			    	int pizzaindex=0;
			    	for(int i = 0;i<pizzalist.size();i++){
			    		if(orderlist.getSelectionModel().getModelItem(index).getValue().getItem().equalsIgnoreCase(pizzalist.get(i).getSize())){
			    			pizzaindex = i;
			    		}
			    	}
			    	group.getToggles().get(pizzaindex).setSelected(true);
		//	    	pizzaroot.getChildren().clear();
		//	    	pizzaroot.getChildren().add(new TreeItem(ordereditems.get(index)));
			    	pizzaroot.getChildren().get(0).setExpanded(true);
			    	neu = pizzaroot.getChildren().get(0);
			    	for(int i = 1;i<=index3;i++){
			    		neu.getChildren().add(new TreeItem<Order>(ordereditems.get(index+i)));
			    		System.out.println(ordereditems.get(index+i));
			    		for(int x=0;x<toppinglist1.size();x++){
			    			if(ordereditems.get(index+i).getItem().equalsIgnoreCase(toppinglist1.get(x).getName())){
			    				toplist.add(toppinglist1.get(x));
			    			}
			    		}
		    			for(int x=0;x<toppinglist2.size();x++){
			    			if(ordereditems.get(index+i).getItem().equalsIgnoreCase(toppinglist2.get(x).getName())){
			    				toplist.add(toppinglist2.get(x));
			    			}
			    		}
			    	}
			    	for(int i = 0;i<=index3;i++){
			    		ordereditems.remove(index);
			    	}
			    	double min = orderedpizza.get(index2).getPrice();
			    	for (int i = 0;i<index3;i++){
			    		min = min + orderedpizza.get(index2).getToppings().get(i).getPrice();
			    	}
			    	value = value-min;
			    	totalcost.setText(Double.toString(value));
			    	root.getChildren().remove(index2);
			    	orderedpizza.remove(index2);
			    	position.remove(index2);
		    	}
			}
		}
    }

    @FXML
    void deleteItem(ActionEvent event) {
    	int index = orderlist.getSelectionModel().getSelectedIndex();
    	if(orderlist.getTreeItemLevel(orderlist.getTreeItem(index))==1){
	    	for(int i = 0;i<root.getChildren().size();i++){
	    		root.getChildren().get(i).setExpanded(false);
	    	}
	    	int index2 = orderlist.getSelectionModel().getSelectedIndex();
	    	for(int i = 0;i<root.getChildren().size();i++){
	    		root.getChildren().get(i).setExpanded(true);
	    	}
	    	int index3 = root.getChildren().get(index2).getChildren().size();
	    	for(int i = 0;i<pizzalist.size();i++){
	    		if(orderlist.getSelectionModel().getModelItem(index).getValue().getItem().equalsIgnoreCase(pizzalist.get(i).getSize())){
	    			for(int j = 0;j<orderedpizza.size();j++){
	    	    		if(orderedpizza.get(j).getNr().getValue()==index2){
	    	    			orderedpizza.remove(j);
	    	    		}
	    	    	}
	    		}
	    		else{
	    			for(int j = 0;j<drinkslist.size();j++){
	    	    		if(drinkslist.get(j).getName().equalsIgnoreCase(root.getChildren().get(index2).getValue().getItem())){
	    	    			drinkslist.remove(j);
	    	    		}
	    	    	}
	    		}
	    	}
	    	root.getChildren().remove(index2);

	    	position.remove(index2);
	    	ordereditems.remove(index);
    	}
    }

    @FXML
    void orderitems(ActionEvent event) throws IOException {
    	DrinkDAO drink = new DrinkDAO();
    	String size = "";
    	int pnr = -1;
    	String name = "";
    	double price = 0;
    	if(value>=20){
    		drinkslist.add(new Drinks("Cola",0.00));
    	}
    	if (value>=40){
    		drinkslist.add(new Drinks("Wein",0.00));
    	}
    	for(int i = 0; i<orderedpizza.size();i++){
    		size = orderedpizza.get(i).getSize();
    		price = orderedpizza.get(i).getPrice();
    		pizza.setPizza(onr, size, price);
    		pnr = pizza.getPnr(onr);
    		topping.setToppings(pnr, orderedpizza.get(i).getToppings());
    	}
    	for(int i = 0; i<drinkslist.size();i++){
    		name = drinkslist.get(i).getName();
    		price = drinkslist.get(i).getPrice();
    		drink.setDrink(onr, name, price);
    	}

    	mainApp.goBack(event,order.getOrders());
		mainApp.showPrint(event,onr);
    }

    @Override
	public void initialize(final URL location, final ResourceBundle resources){
System.out.println(cust.getCustomerForOrder());
    	custshow.setText(cust.getCustomerForOrder());
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
		          	choosePizza();
		          }
		      }
		    });

		ToppingDAO topping = new ToppingDAO();
		toppinglist1 = topping.getToppings1();
		hbox1 = new HBox[toppinglist1.size()];
		pls1 = new Button[toppinglist1.size()];
		lbl1 = new Label[toppinglist1.size()];
		mns1 = new Button[toppinglist1.size()];
		for( int i = 0; i<toppinglist1.size(); i++){
			HBox hb =hbox1[i] = new HBox(5);
			hbox1[i].setStyle("-fx-padding:2px; -fx-border-color: B2C0C0; -fx-border-radius:4;");
			Button pls = pls1[i] = new Button("+");
			pls1[i].setPadding(Insets.EMPTY);
			pls1[i].setStyle("-fx-margin:0 0 0 10px; -fx-pref-height:15px; -fx-pref-width:15px;");
			Label lbl = lbl1[i] = new Label(toppinglist1.get(i).getName());
			Button mns = mns1[i] = new Button("-");
			mns1[i].setPadding(Insets.EMPTY);
			mns1[i].setStyle("-fx-margin:0 10px 0 0; -fx-pref-height:15px; -fx-pref-width:15px;");
			hb.getChildren().addAll(pls1[i],lbl1[i],mns1[i]);
			containertoppings.getChildren().addAll(hbox1[i]);
		}

		toppinglist2 = topping.getToppings2();
		hbox2 = new HBox[toppinglist2.size()];
		pls2 = new Button[toppinglist2.size()];
		lbl2 = new Label[toppinglist2.size()];
		mns2 = new Button[toppinglist2.size()];
		for( int i = 0; i<toppinglist2.size(); i++){
			HBox hb =hbox2[i] = new HBox(5);
			hbox2[i].setStyle("-fx-padding:2px; -fx-border-color: B2C0C0; -fx-border-radius:4;");
			Button pls = pls2[i] = new Button("+");
			pls2[i].setPadding(Insets.EMPTY);
			pls2[i].setStyle("-fx-margin:0 0 0 10px; -fx-pref-height:15px; -fx-pref-width:15px;");
			Label lbl = lbl2[i] = new Label(toppinglist2.get(i).getName());
			Button mns = mns2[i] = new Button("-");
			mns2[i].setPadding(Insets.EMPTY);
			mns2[i].setStyle("-fx-margin:0 10px 0 0; -fx-pref-height:15px; -fx-pref-width:15px;");
			hb.getChildren().addAll(pls2[i],lbl2[i],mns2[i]);
			containertoppings2.getChildren().addAll(hbox2[i]);
		}

		DrinkDAO drink = new DrinkDAO();
		drinks = drink.getDrinks();
		drinkhbox = new HBox[drinks.size()];
		pls = new Button[drinks.size()];
		lbl = new Label[drinks.size()];
		mns = new Button[drinks.size()];
		for( int i = 0; i<drinks.size(); i++){
			HBox hb =drinkhbox[i] = new HBox(5);
			drinkhbox[i].setStyle("-fx-padding:2px; -fx-border-color: B2C0C0; -fx-border-radius:4;");
			Button plus = pls[i] = new Button("+");
			pls[i].setPadding(Insets.EMPTY);
			pls[i].setStyle("-fx-pref-height:15px; -fx-pref-width:15px;");
			Label lable = lbl[i] = new Label(drinks.get(i).getName());
			Button minus = mns[i] = new Button("-");
			mns[i].setPadding(Insets.EMPTY);
			mns[i].setStyle("-fx-pref-height:15px; -fx-pref-width:15px;");
			hb.getChildren().addAll(pls[i],lbl[i],mns[i]);
			containerdrinks.getChildren().addAll(drinkhbox[i]);
		}

		for( int i = 0; i<drinks.size(); i++){
    		String name = drinks.get(i).getName();
    		double price = drinks.get(i).getPrice();
    		System.out.println(price);
    		Drinks thisdrink = drinks.get(i);
    		System.out.println(thisdrink);
			pls[i].setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	drinkslist.add(new Drinks(name,price));
	            	ordereditems.add(new Order(name,price));
	            	root.getChildren().add(new TreeItem<Order>(new Order(name,price)));
	            }
	        });
			mns[i].setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	int index = 0;
	            	for(int j = 0;j<ordereditems.size();j++){
	            		if(ordereditems.get(j).getItem().equalsIgnoreCase(thisdrink.getName())){
		            		index = j;
		            	}
	            	}
	            	int dindex = 0;
	            	for(int j = 0;j<drinkslist.size();j++){
	            		if(drinkslist.get(j).getName().equalsIgnoreCase(thisdrink.getName())){
		            		dindex = j;
		            	}
	            	}
	            	int lindex=0;
	            	for(int j = 0;j<root.getChildren().size();j++){
	            		if(root.getChildren().get(j).getValue().getItem().equalsIgnoreCase(thisdrink.getName())){
	            			lindex = j;
		            	}
	            	}
	            	System.out.println(thisdrink);
	            	System.out.println(index);
	            	System.out.println(dindex);
	            	if(index>0){
	            		root.getChildren().remove(lindex);
	            		drinkslist.remove(dindex);
		            	ordereditems.remove(index);
	            	}
	            }
	        });
    	}

		pizzaroot.setExpanded(true);
    	pizzaorder.setRoot(pizzaroot);
    	pizzaorder.setShowRoot(false);
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
    	ordercol.setCellValueFactory((TreeTableColumn.CellDataFeatures<Order, String> param) ->
        new ReadOnlyStringWrapper(param.getValue().getValue().getItem()));
    	pricecol.setCellValueFactory(new Callback<CellDataFeatures<Order, Double>, ObservableValue<Double>>() {
    		  @Override
    		  public ObservableValue<Double> call(CellDataFeatures<Order, Double> p)          {
    		      return p.getValue().getValue().priceProperty().asObject();
    		 }
    		 });
    	ordereditems=new ArrayList<Order>();
	}

    public void setMainApp(Main mainApp){
		this.mainApp = mainApp;

	}

}
