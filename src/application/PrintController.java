package application;

import java.util.ArrayList;
import DAO.OrderDAO;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.util.Callback;
import model.Drinks;
import model.Order;
import model.Pizza;


public class PrintController {
	
	@FXML
    private Label custshow;
	
	@FXML
    private Label onrlabel;
	
	@FXML
    private TreeTableView<Order> orderlist;
    TreeItem<Order> root = new TreeItem<> (new Order("root",0.00));

    @FXML
    private TreeTableColumn<Order, String> ordercol;
    
    @FXML
    private TreeTableColumn<Order, Double> pricecol;
    
    @FXML
    private CheckBox paidcheckbox;

    @FXML
    private RadioButton customerradiobtn;
    
    @FXML
    private RadioButton kitchenradiobtn;
    
    private ToggleGroup group = new ToggleGroup();
    ArrayList<Pizza> pizzas;
    private Main mainApp;
    private int onr;
    OrderDAO order = new OrderDAO();


	public void setPage(){
		onr = mainApp.getOnr();
    	custshow.setText(order.getCustomerForOrder(onr));
    	onrlabel.setText(""+onr);
    	ArrayList<Pizza> pizzas = new ArrayList<>(order.getPizzas(onr));
    	ArrayList<Drinks> drinks = new ArrayList<>(order.getDrinks(onr));
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
    	for(int i=0;i<pizzas.size();i++){
    		TreeItem<Order> neu = new TreeItem<> (new Order(pizzas.get(i).getSize(), pizzas.get(i).getPrice()));
    		root.getChildren().add(neu);
    		neu.setExpanded(true);
    		for(int j = 0; j<pizzas.get(i).getToppings().size();j++){
    			neu.getChildren().add(new TreeItem<Order>(new Order(pizzas.get(i).getToppings().get(j).getName(), pizzas.get(i).getToppings().get(j).getPrice())));
    		}
    	}
    	for(int i=0;i<drinks.size();i++){
    		TreeItem<Order> neu = new TreeItem<> (new Order(drinks.get(i).getName(), drinks.get(i).getPrice()));
    		root.getChildren().add(neu);
    	}

    	kitchenradiobtn.setToggleGroup(group);
    	customerradiobtn.setToggleGroup(group);
    	kitchenradiobtn.setSelected(true);

    	if(order.getClosedOrder(onr)==1){
    		paidcheckbox.setSelected(true);
    	}

    	group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		      public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
		    	  if (group.getSelectedToggle() != null) {
		    		choosePrint();
		          }
		      }
		    });
    	
    	paidcheckbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
    	        closeOrder();
    	    }
    	});

    }

	@FXML
    void closeOrder() {
		 if(paidcheckbox.isSelected()){
	        	order.closeOrder(onr);
	     }
		 else{
			 order.openOrder(onr);
		 }
		 mainApp.setOrderlist();
    }

    public void choosePrint(){
    	if(customerradiobtn.isSelected() == true){
    		paidcheckbox.setDisable(true);
    		paidcheckbox.setVisible(false);
    	}
    	else{
    		paidcheckbox.setDisable(false);
    		paidcheckbox.setVisible(true);
    	}
    }

    public void setMainApp(Main mainApp){
    	this.mainApp = mainApp;
    	setPage();
    }

    public void setOnr(int onr){
    	this.onr=onr;
    }
}