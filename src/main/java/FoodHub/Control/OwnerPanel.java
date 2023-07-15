package FoodHub.Control;
import FoodHub.Base.*;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
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
        MainApplication.scene = "OwnerPanel";
    }
    @FXML
    public void initialize() throws IOException {
        ArrayList<Restaurant> restaurants = Main.sql.getRestaurant(User.currentUser.id, "ownerId", false, "");
        ownerPanel = this;
        for (Restaurant restaurant : restaurants) {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("OwnerPanelRow.fxml"));
            HBox hBox = loader.load();
            OwnerPanelRow ownerPanelRow = loader.getController();
            double average = Comment.averageRate(Main.sql.getComment(restaurant.id, "restaurantId", false));
            ownerPanelRow.setData(restaurant.id, restaurant.name, Restaurant.foodTypesToString(restaurant.foodTypes, true), Integer.toString(restaurant.postCost), Integer.toString(Address.getAddress(0, restaurant.id).node), (average > 0) ? String.valueOf(average) : "");
            hBox.setId("Restaurant_" + restaurant.id);
            box.getChildren().add(hBox);
        }
    }
    public void openOrders(ActionEvent event) {

    }
    public void allOrders(ActionEvent event) {

    }
    public void delete(int id) throws IOException {
        Alert alert;
        if (Order.openOrders(id).size() != 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Can't delete restaurant.");
            alert.setContentText("You have open orders in this restaurant and can't delete before finish your jobs.");
            alert.show();
        }
        else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm");
            alert.setHeaderText("Are you sure?");
            alert.setContentText("By doing this, all the data related to the restaurant will be deleted.");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Restaurant.deleteRestaurant(id);
                box.getChildren().removeAll();
                box.getChildren().clear();
                initialize();
            }
        }
    }
}
