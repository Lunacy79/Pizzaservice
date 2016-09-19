package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
    private FlowPane containerdrinks;
    private ArrayList<Drinks> drinks = new ArrayList<>();
    private ArrayList<Drinks> drinkslist = new ArrayList<>();
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
    	Order top = null;
    	
    	//Auslesen aus Treetableview
    	for(int i = 0; i<toplist.size();i++){
    		top = neu.getChildren().get(i).getValue();
    		tops.add(top);
    	}

    	//Sortierung der Beläge in Preiskategorien 1 und 2
    	ArrayList<Topping> toplist2 = new ArrayList<>();
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
    	
    	//?
    	ArrayList<Topping> topps = new ArrayList<>(toplist);
    	
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
//    	value = value + price;
    	
    	//Berechnung der Belagpreise; ab dem 3. Belag gibts 10% Rabatt
    	for(int i = 0; i<toplist.size();i++){
    		ordereditems.add(top);
    		if(i>=2){
    			tops.get(i).setPrice(tops.get(i).getPrice()*0.9);
    		}
    		neu2.getChildren().add(new TreeItem<>(tops.get(i)));
    		value = value + top.getPrice();
    	}
    	totalcost.setText(Double.toString(value));
    	
    	//Speichern der Pizza mit Belägen in Pizza-Arraylist
    	orderedpizza.add(new Pizza(position.size(),size,price,topps));
    	
    	//zur Positionsbestimmung wird der Index in der Tabelle in dieser ArrayList gespeichert
    	position.add(new Order(size,price));
    	
    	//Leeren des Treetableviews und der Pizza-Radiobuttons
		pizzaroot.getChildren().clear();
		toplist.clear();
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
    	if(orderlist.getTreeItemLevel(orderlist.getTreeItem(index))==1){
	    	for(int i = 0;i<root.getChildren().size();i++){
	    		root.getChildren().get(i).setExpanded(false);
	    	}
	    	int index2 = orderlist.getSelectionModel().getSelectedIndex();
	    	for(int i = 0;i<root.getChildren().size();i++){
	    		root.getChildren().get(i).setExpanded(true);
	    	}
	    	int index3 = root.getChildren().get(index2).getChildren().size();

	    	group.getToggles().get(1).setSelected(true);
//	    	pizzaroot.getChildren().clear();
//	    	pizzaroot.getChildren().add(new TreeItem(ordereditems.get(index)));
	    	pizzaroot.getChildren().get(0).setExpanded(true);
	    	for(int i = 1;i<=index3;i++){
	    		pizzaroot.getChildren().get(0).getChildren().add(new TreeItem<Order>(ordereditems.get(index+i)));
	    	}
	    	for(int i = 0;i<=index3;i++){
	    		ordereditems.remove(index);
	    	}
	    	root.getChildren().remove(index2);
	    	orderedpizza.remove(index2);
	    	position.remove(index2);

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
	    	root.getChildren().remove(index2);
	    	for(int i = 0;i<orderedpizza.size();i++){
	    		if(orderedpizza.get(i).getNr().equals(index2)){
	    			orderedpizza.remove(i);
	    		}
	    	}
	    	position.remove(index2);
	    	ordereditems.remove(index);
    	}
    }

    @FXML
    void orderitems(ActionEvent event) throws IOException {
    	String size = "";
    	int pnr = -1;
    	String name = "";
    	for(int i = 0; i<orderedpizza.size();i++){
    		size = orderedpizza.get(i).getSize();
    		order.setPizza(onr, size);
    		pnr = order.getPnr(onr);
    		order.setToppings(pnr, orderedpizza.get(i).getToppings());
    	}
    	for(int i = 0; i<drinkslist.size();i++){
    		name = drinkslist.get(i).getName();
    		order.setDrink(onr, name);
    	}
    	mainApp.goBack(event,order.getOrders());
		mainApp.showPrint(event,onr);
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
			Label lbl = lbl1[i] = new Label(toppinglist1.get(i).getName());
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
			Label lbl = lbl2[i] = new Label(toppinglist2.get(i).getName());
			Button mns = mns2[i] = new Button("-");
			mns2[i].setPadding(Insets.EMPTY);
			mns2[i].setStyle("-fx-margin:0 10px 0 0; -fx-pref-height:15px; -fx-pref-width:15px;");
			Label elbl = new Label("");
			elbl.setStyle("-fx-padding: 0 10px 0 10px;");
			containertoppings2.getChildren().addAll(pls2[i],lbl2[i],mns2[i],elbl);
		}

		DrinkDAO drink = new DrinkDAO();
		drinks = drink.getDrinks();
		pls = new Button[drinks.size()];
		lbl = new Label[drinks.size()];
		mns = new Button[drinks.size()];
		for( int i = 0; i<drinks.size(); i++){
			Button plus = pls[i] = new Button("+");
			pls[i].setPadding(Insets.EMPTY);
			pls[i].setStyle("-fx-margin:0 0 0 10px; -fx-pref-height:15px; -fx-pref-width:15px;");
			Label lable = lbl[i] = new Label(drinks.get(i).getName());
			Button minus = mns[i] = new Button("-");
			mns[i].setPadding(Insets.EMPTY);
			mns[i].setStyle("-fx-margin:0 10px 0 0; -fx-pref-height:15px; -fx-pref-width:15px;");
			Label elbl = new Label("");
			elbl.setStyle("-fx-padding: 0 10px 0 10px;");
			containerdrinks.getChildren().addAll(pls[i],lbl[i],mns[i],elbl);
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
