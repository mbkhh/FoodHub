package FoodHub.Control;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class PanelColumn {
    @FXML
    public Button foodType;
    public void setData(String foodType) {
        this.foodType.setText(foodType);
    }
    public void select(ActionEvent event) throws IOException {
        Restaurants.show(foodType.getText());
    }
}
