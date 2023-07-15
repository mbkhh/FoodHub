package FoodHub.Control;
import FoodHub.Base.Comment;
import FoodHub.Base.Food;
import FoodHub.Base.Order;
import FoodHub.Base.Restaurant;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class RestaurantOwnerPanel {
    @FXML
    public VBox box;
    @FXML
    public ImageView image;
    @FXML
    public Label name, address, postCost, rating, foodType;
    public static RestaurantOwnerPanel restaurantOwnerPanel;
    public static void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("RestaurantOwnerPanel.fxml"));
        Scene scene = new Scene(loader.load());
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
        MainApplication.scene = "RestaurantOwnerPanel";
    }
    @FXML
    public void initialize() throws IOException {
        restaurantOwnerPanel = this;
        ArrayList<Food> foods = Main.sql.getFood(Restaurant.currentRestaurant.id, "restaurantId", false, "");
        for (Food food : foods){
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("RestaurantOwnerPanelRow.fxml"));
            HBox hBox = loader.load();
            RestaurantOwnerPanelRow restaurantOwnerPanelRow = loader.getController();
            restaurantOwnerPanelRow.setData(food.id, food.name, food.foodType.getFoodType(), food.getPrice()[0], food.getPrice()[0] - food.getPrice()[1], Comment.averageRate(Main.sql.getComment(food.id, "foodId", false)));
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
    public void comments(ActionEvent event) throws IOException{
        System.out.println("sffdsfds");
        CoResownerRController.show();
    }
    public void foodcom() throws IOException
    {
        CoResownerFController.show();
    }
    public void allOrders(ActionEvent event) throws IOException{
        RestaurantOrder.show(Restaurant.currentRestaurant.id);
    }
    public void back(ActionEvent event) throws IOException {
        Restaurant.currentRestaurant = null;
        OwnerPanel.show();
    }
    public void edit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (Order.openOrders(Restaurant.currentRestaurant.id).size() != 0) {
            alert.setTitle("Error");
            alert.setHeaderText("Can't edit restaurant.");
            alert.setContentText("You have open orders in this restaurant and can't edit before finish your jobs.");
            alert.show();
        }
        else {
//            box.getChildren().removeAll();
//            box.getChildren().clear();
            EditRestaurant.show();
        }
    }
    public void addFood(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (Order.openOrders(Restaurant.currentRestaurant.id).size() != 0) {
            alert.setTitle("Error");
            alert.setHeaderText("Can't edit restaurant.");
            alert.setContentText("You have open orders in this restaurant and can't edit before finish your jobs.");
            alert.show();
        }
        else {
//            box.getChildren().removeAll();
//            box.getChildren().clear();
            EditRestaurant.show();
        }
    }
    public void delete(int id) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (Order.openOrders(Restaurant.currentRestaurant.id).size() != 0) {
            alert.setTitle("Error");
            alert.setHeaderText("Can't delete foods.");
            alert.setContentText("You have open orders in this restaurant and can't delete before finish your jobs.");
            alert.show();
        }
        else {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("By doing this, all the data related to the food will be deleted.");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Food.deleteFood(id);
                box.getChildren().removeAll();
                box.getChildren().clear();
                initialize();
            }
        }

    }
}
