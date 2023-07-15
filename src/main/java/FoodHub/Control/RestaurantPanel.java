package FoodHub.Control;
import FoodHub.Base.Comment;
import FoodHub.Base.Food;
import FoodHub.Base.Restaurant;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class RestaurantPanel {
    @FXML
    public VBox box;
    @FXML
    public ImageView image;
    @FXML
    public Label name, address, rating, foodType, postCost;
    public RestaurantPanel restaurantPanel;
    public static void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("RestaurantPanel.fxml"));
        Scene scene = new Scene(loader.load());
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
        MainApplication.scene = "RestaurantPanel";
    }
    @FXML
    public void initialize() throws IOException {
        restaurantPanel = this;
        ArrayList<Food> foods = Main.sql.getFood(Restaurant.currentRestaurant.id, "restaurantId", false, "");
        for (Food food : foods){
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("RestaurantPanelRow.fxml"));
            HBox hBox = loader.load();
            RestaurantPanelRow restaurantPanelRow = loader.getController();
            restaurantPanelRow.setData(food.id, food.name, food.foodType.getFoodType(), food.getPrice()[0], food.getPrice()[0] - food.getPrice()[1], Comment.averageRate(Main.sql.getComment(food.id, "foodId", false)));
            hBox.setId("food_" + food.id);
            box.getChildren().add(hBox);
        }
        name.setText(Restaurant.currentRestaurant.name);
        address.setText(String.valueOf(Restaurant.currentRestaurant.getRestaurantAddress().node));
        foodType.setText(Restaurant.foodTypesToString(Restaurant.currentRestaurant.foodTypes, true));
        postCost.setText(String.valueOf(Restaurant.currentRestaurant.postCost));
        double averageRate = Comment.averageRate(Main.sql.getComment(Restaurant.currentRestaurant.id, "restaurantId", false));
        rating.setText((averageRate > 0) ? String.valueOf(averageRate) : "");
    }
    public void comments(ActionEvent event) {

    }
    public void balance(ActionEvent event) {

    }
    public void cart(ActionEvent event) throws IOException {
        CartController.show();
    }
    public void order(ActionEvent event) throws IOException {
        OrderController.show();
    }
    public void back(ActionEvent event) throws IOException {
        Restaurant.currentRestaurant = null;
        Panel.show();
    }
}
