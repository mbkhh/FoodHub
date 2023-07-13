package FoodHub.Control;
import FoodHub.Base.*;
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
        restaurants = Main.sql.getRestaurant(0, "", true, search);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("Restaurants.fxml"));
        Scene scene = new Scene(loader.load(), FoodHub.Main.primaryWidth, FoodHub.Main.primaryHeight);
        FoodHub.Main.primaryStage.setScene(scene);
        FoodHub.Main.primaryStage.show();
    }
    @FXML
    public void initialize() throws IOException {
        restaurant = this;
        for (Restaurant rowRestaurant : restaurants) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("RestaurantsRow.fxml"));
            AnchorPane row = loader.load();
            RestaurantRow restaurantRow = loader.getController();
            restaurantRow.setData(rowRestaurant.id, rowRestaurant.name, rowRestaurant.foodTypesToString(), rowRestaurant.postCost, Address.getAddress(0, rowRestaurant.id).node, Comment.getComment());
        }
    }
}
