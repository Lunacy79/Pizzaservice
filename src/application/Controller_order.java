package application;

import java.awt.Checkbox;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private ArrayList<Pizza> orderedpizza = new ArrayList<Pizza>();
    Pizza piz;

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

    @FXML
    private TreeTableView<Order> pizzaorder;
    private TreeItem<Order> pizzaroot = new TreeItem<>(new Order("pizzaroot", 0.00));
    private TreeItem<Order> neu = new TreeItem<> ();
    private Topping top;
    private ArrayList<Topping> toplist = new ArrayList<>();
    private ArrayList<Order> topslist = new ArrayList<>();

    @FXML
    private TreeTableColumn<Order, String> colpizza;

    @FXML
    private TreeTableColumn<Order, Double> colprice;

    @FXML
    private Label pizzacost;

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

    @FXML
    private Button addbtn;

    @FXML
    private Button deletebtn;

    @FXML
    private Button orderbtn;

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
    	piz=new Pizza(size,price);
    	neu=new TreeItem<>(new Order(size,price));
    	neu.setExpanded(true);
    	pizzaroot.getChildren().add(neu);

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
	            	System.out.println(topp);
	                chooseTopping(name2, price2, topp);
	            }
	        });
			mns2[i].setOnAction(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent e) {
	            	int index = toplist.indexOf(topp);
	            	toplist.remove(topp);
	            	System.out.println(topp);
	                deleteTopping(name2, price2, index);
	            }
	        });
    	}
    }

    void chooseTopping(String name, double price, Topping topp){

    	neu.getChildren().add(new TreeItem<Order>(new Order(name,price)));
    	System.out.println(top);
    }

    void deleteTopping(String name, double price, int index){
    	neu.getChildren().remove(index);

    }

    @FXML
    void orderPizza(ActionEvent event) {
    	System.out.println(toplist);
    	ArrayList<Order> tops = new ArrayList<Order>();
    	ArrayList<Order> tops1 = new ArrayList<Order>();
    	ArrayList<Order> tops2 = new ArrayList<Order>();
    	ArrayList<Topping> toplist1 = new ArrayList<>();
    	Order top = null;
    	for(int i = 0; i<toplist.size();i++){
    		top = neu.getChildren().get(i).getValue();
    		tops.add(top);

    	}

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
    	toplist.clear();
    	toplist.addAll(toplist1);
    	toplist.addAll(toplist2);
    	tops.clear();
    	tops.addAll(tops1);
    	tops.addAll(tops2);
    	ArrayList<Topping> topps = new ArrayList<>(toplist);
    	System.out.println(toplist);
    	Order pizza = pizzaroot.getChildren().get(0).getValue();
    	ordereditems.add(pizza);
    	String size = pizza.getItem();
    	double price = pizza.getPrice();
    	neu2 = new TreeItem<> ();
    	neu2.setValue(pizza);
    	neu2.setExpanded(true);
    	root.getChildren().add(neu2);
    	value = value + price;
    	for(int i = 0; i<toplist.size();i++){
    		ordereditems.add(top);
    		if(i>=3){
    			tops.get(i).setPrice(tops.get(i).getPrice()*0.9);
    		}
    		neu2.getChildren().add(new TreeItem<>(tops.get(i)));
    		value = value + top.getPrice();
    	}
    	totalcost.setText(Double.toString(value));
		neu.getChildren().clear();
		pizzaroot.getChildren().clear();
		toplist.clear();
		if(group.getSelectedToggle()!= null){
			group.getSelectedToggle().setSelected(false);
		}
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
	    	orderedpizza.remove(index2);
	    	for(int i = 0;i<=index3;i++){
	    		ordereditems.remove(index);
	    	}
    	}
    }

    @FXML
    void orderitems(ActionEvent event) throws IOException {
    	String size = "";
    	int pnr = -1;
    	for(int i = 0; i<orderedpizza.size();i++){
    		size = orderedpizza.get(i).getSize();
    		order.setPizza(onr, size);
    		System.out.println(order.getOrders());
    		pnr = order.getPnr(onr);
    		System.out.println(orderedpizza.get(i).getToppings());
    		order.setToppings(pnr, orderedpizza.get(i).getToppings());
    	}
    	mainApp.goBack(event);
    	
    	FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("Print.fxml"));
		AnchorPane parent_print = (AnchorPane)loader.load();
		Scene print = new Scene(parent_print,500,600);

		PrintController controller = loader.getController();
		Stage thirdStage = new Stage();
		thirdStage.setScene(print);
		thirdStage.show();
		controller.setMainApp(this);
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
    	ordereditems=new ArrayList<Order>();
	}

    public void setMainApp(Main mainApp){
		this.mainApp = mainApp;

	}





}
