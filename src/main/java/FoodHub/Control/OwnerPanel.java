package FoodHub.Control;
import FoodHub.Base.*;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class OwnerPanel {
    @FXML
    public VBox box;
    public static OwnerPanel ownerPanel;
    public static void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("OwnerPanel.fxml"));
        Scene scene = new Scene(loader.load());
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }
    @FXML
    public void initialize() throws IOException {
        ArrayList<Restaurant> restaurants = Main.sql.getRestaurant(User.currentUser.id, "ownerId", false, "");
        ownerPanel = this;
        for (Restaurant restaurant : restaurants) {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("OwnerPanelRow.fxml"));
            AnchorPane pane = loader.load();
            OwnerPanelRow ownerPanelRow = loader.getController();
            double average = Comment.averageRate(Main.sql.getComment(restaurant.id, "restaurantId", false));
            ownerPanelRow.setData(restaurant.id, restaurant.name, restaurant.foodTypesToString(), Integer.toString(restaurant.postCost), Integer.toString(Address.getAddress(0, restaurant.id).node), (average > 0) ? String.valueOf(average) : "");
            pane.setId("Restaurant_" + restaurant.id);
            box.getChildren().add(pane);
        }
    }
}
