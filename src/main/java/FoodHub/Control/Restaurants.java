package FoodHub.Control;
import FoodHub.Base.*;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class Restaurants {
    @FXML
    public VBox box;
    public static Restaurants restaurant;
    public static ArrayList<Restaurant> restaurants;
    public static void show(String search) throws IOException {
        restaurants = Address.findNearRestaurant(Main.sql.getRestaurant(0, "", true, (!search.equals("ALL")) ? search : ""), User.currentUser.id);
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Restaurants.fxml"));
        Scene scene = new Scene(loader.load());
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
        MainApplication.scene = "Restaurants";
    }
    @FXML
    public void initialize() throws IOException {
        restaurant = this;
        for (Restaurant temp : restaurants) {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("RestaurantsRow.fxml"));
            AnchorPane row = loader.load();
            row.setId("Restaurant_" + temp.id);
            RestaurantsRow restaurantsRow = loader.getController();
            double average = Comment.averageRate(Main.sql.getComment(temp.id, "restaurantId", false));
            restaurantsRow.setData(temp.id, temp.name, Restaurant.foodTypesToString(temp.foodTypes, true), Integer.toString(temp.postCost), Integer.toString(Address.getAddress(0, temp.id).node), (average > 0) ? String.valueOf(average) : "");
            box.getChildren().add(row);
        }
    }
    public void balance(ActionEvent event) throws IOException{
        ChargeController.show();
    }
    public void cart(ActionEvent event) throws IOException {
        CartController.show();
    }
    public void orders(ActionEvent event) throws IOException {
        OrderController.show();
    }
    public void back(ActionEvent event) throws IOException {
        Panel.show();
    }
}
