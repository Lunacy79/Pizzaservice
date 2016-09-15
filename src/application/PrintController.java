package application;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import model.Order;

public class PrintController implements Initializable {

    @FXML
    private TreeTableColumn<Order, String> ordercol;

    @FXML
    private RadioButton customerradiobtn;

    @FXML
    private Label custshow;

    @FXML
    private Label onrlabel;

    @FXML
    private CheckBox paidcheckbox;

    @FXML
    private TreeTableColumn<Order, Double> pricecol;

    @FXML
    private RadioButton kitchenradiobtn;
    private ToggleGroup group = new ToggleGroup();

    @FXML
    private TreeTableView<Order> orderlist;

    private Controller_order mainApp;
    
    @Override
	public void initialize(final URL location, final ResourceBundle resources){
    	kitchenradiobtn.setToggleGroup(group);
    	customerradiobtn.setToggleGroup(group);
    	kitchenradiobtn.setSelected(true);
    	
    	group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		      public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
		    	  if (group.getSelectedToggle() != null) {
		    		choosePrint();
		          }
		      }
		    });
    }
    
    public void choosePrint(){
    	if(customerradiobtn.isSelected() == true){
    		paidcheckbox.setDisable(true);
    	}
    	else{
    		paidcheckbox.setDisable(false);
    	}
    }
    
    public void setMainApp(Controller_order mainApp){
    	this.mainApp = mainApp;
    }
}