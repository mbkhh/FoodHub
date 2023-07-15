package FoodHub.Control;
import FoodHub.Base.Order;
import FoodHub.Base.Restaurant;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class OwnerPanelRow {
    @FXML
    public Label name, foodType, postCost, address, rate;
    int restaurantId;
    public void select(ActionEvent event) {
        Restaurant.setCurrentRestaurant(restaurantId);
        RestaurantOwnerPanel.show();
    }
    public void setData(int restaurantId, String name, String foodType, String postCost, String address, String rate) {
        this.name.setText(name);
        this.foodType.setText(foodType);
        this.postCost.setText(postCost);
        this.address.setText(address);
        this.rate.setText(rate);
        this.restaurantId = restaurantId;
    }
    public void delete(ActionEvent event) throws IOException {
        OwnerPanel.ownerPanel.delete(restaurantId);
    }
}
