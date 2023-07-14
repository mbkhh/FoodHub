package FoodHub.Control;
import FoodHub.Base.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RestaurantsRow {
    @FXML
    public Label name, foodType, postCost, address, rate;
    int restaurantId;
    public void select(ActionEvent event) {
        Restaurant.setCurrentRestaurant(restaurantId);
        RestaurantPanel.show();
    }
    public void setData(int restaurantId, String name, String foodType, String postCost, String address, String rate) {
        this.name.setText(name);
        this.foodType.setText(foodType);
        this.postCost.setText(postCost);
        this.address.setText(address);
        this.rate.setText(rate);
        this.restaurantId = restaurantId;
    }

}
