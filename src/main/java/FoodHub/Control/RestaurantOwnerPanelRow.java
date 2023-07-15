package FoodHub.Control;
import FoodHub.Base.Food;
import FoodHub.Base.Order;
import FoodHub.Base.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.io.IOException;
import java.lang.annotation.ElementType;

public class RestaurantOwnerPanelRow {
    @FXML
    public Label name, type, price, discount, rate;
    @FXML
    public Button activity;
    public int foodId;
    public void setData(int foodId, String name, String type, int price, int discount, double rate) {
        this.foodId = foodId;
        this.name.setText(name);
        this.type.setText(type);
        this.price.setText(String.valueOf(price));
        this.discount.setText((discount > 0) ? String.valueOf(discount) : "");
        this.rate.setText((rate > 0) ? String.valueOf(rate) : "");
    }
    public void changeActivity(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (Order.openOrders(Restaurant.currentRestaurant.id).size() != 0) {
            alert.setTitle("Error");
            alert.setHeaderText("Can't edit food.");
            alert.setContentText("You have open orders in this restaurant and can't delete before finish your jobs.");
            alert.show();
        }
        else {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("By doing this, no one can buy this good.");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Food.editFood(foodId, "isActive", (activity.getText().equals("Inactive")) ? "no" : "yes", 0, 0);
                activity.setText((activity.getText().equals("Active")) ? "Inactive" : "Active");
            }
        }
    }
    public void delete(ActionEvent event) throws IOException {
        RestaurantOwnerPanel.restaurantOwnerPanel.delete(foodId);
    }

    public void edit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (Order.openOrders(Restaurant.currentRestaurant.id).size() != 0) {
            alert.setTitle("Error");
            alert.setHeaderText("Can't edit food.");
            alert.setContentText("You have open orders in this restaurant and can't edit before finish your jobs.");
            alert.show();
        }
        else {
//            box.getChildren().removeAll();
//            box.getChildren().clear();
            Food.currentFood = Food.getFood(foodId);
            EditFood.show();
        }
    }
}
