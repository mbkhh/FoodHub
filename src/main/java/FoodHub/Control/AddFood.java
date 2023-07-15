package FoodHub.Control;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class AddFood {
    @FXML
    public TextField name;
    @FXML
    public HBox foodTypeBox;
    @FXML
    public Spinner address, postCost, postCost1;
    public void add(ActionEvent event) {
    }

    public void cancel(ActionEvent event) {
    }
}
