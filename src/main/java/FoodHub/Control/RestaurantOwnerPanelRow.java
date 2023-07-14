package FoodHub.Control;
import FoodHub.Base.Food;
import FoodHub.Base.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RestaurantOwnerPanelRow {
    @FXML
    public Label name, type, price, discount, rate;
    @FXML
    public Button activity;
    public int foodId;
    public void setDate(int foodId, String name, String type, int price, int discount, int rate) {
        this.foodId = foodId;
        this.name.setText(name);
        this.type.setText(type);
        this.price.setText(String.valueOf(price));
        this.discount.setText(String.valueOf(discount));
        this.rate.setText(String.valueOf(rate));
    }
    public void comment(ActionEvent event) {

    }
    public void editName(ActionEvent event) {

    }
    public void editType(ActionEvent event) {

    }
    public void editPrice(ActionEvent event) {

    }
    public void editDiscount(ActionEvent event) {

    }
    public void changeActivity(ActionEvent event) {
        activity.setText((activity.getText().equals("Active")) ? "Inactive" : "Active");
        Food.editFood(foodId, "isActive", (activity.getText().equals("Inactive")) ? "no" : "yes", 0, 0);
    }
    public void delete(ActionEvent event) {
        Alert alert;
        Restaurant.deleteRestaurant();
        = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("");
    }
}
