package FoodHub.Control;
import FoodHub.Base.FoodType;
import FoodHub.Base.Restaurant;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.layout.HBox;
import org.controlsfx.control.CheckComboBox;

public class EditRestaurant {
    @FXML
    public TextField name;
    @FXML
    public Spinner<Integer> address, postCost;
    @FXML
    public HBox foodTypeBox;
    public CheckComboBox<String> checkFoodType;
    public static void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("EditRestaurant.fxml"));
        Scene scene = new Scene(loader.load());
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }
    @FXML
    public void initialize() {
        name.setText(Restaurant.currentRestaurant.name);
        SpinnerValueFactory<Integer> addressValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, Restaurant.currentRestaurant.getRestaurantAddress().node, 10),
                postCostValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1000, 100000, Restaurant.currentRestaurant.postCost, 500);
        address.setValueFactory(addressValueFactory);
        postCost.setValueFactory(postCostValueFactory);
        ArrayList<String> foodTypes = new ArrayList<>();
        for (FoodType foodType : FoodType.values())
            if (!foodType.getFoodType().equals("ALL"))
                foodTypes.add(foodType.getFoodType());
        checkFoodType = new CheckComboBox<>(FXCollections.observableArrayList(foodTypes));
        checkFoodType.setPrefSize(350, 50);
        for (FoodType foodType : Restaurant.currentRestaurant.foodTypes) {
            checkFoodType.getCheckModel().check(foodType.getFoodType());
        }
        foodTypeBox.getChildren().add(checkFoodType);
    }
    public void save(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid type input.");
        if (!name.getText().matches("\\w+")) {
            alert.setTitle("the name type must have more that one word include letters and numbers and '_'");
            alert.show();
        }
        else {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirm");
            alert.setHeaderText("All the restaurant's data will delete because of changing.");
            alert.setContentText("Are you sure?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                ArrayList<FoodType> foodTypes = new ArrayList<>();
                for (int i = 0; i < checkFoodType.getItems().size(); i++)
                    if (checkFoodType.getCheckModel().isChecked(i))
                        foodTypes.add(FoodType.stringToFoodType(checkFoodType.getCheckModel().getItem(i)));
                Main.sql.editRestaurant(Restaurant.currentRestaurant.id, Restaurant.currentRestaurant.owner.id, name.getText(), Restaurant.foodTypesToString(foodTypes, false), postCost.getValue());
                Restaurant.editRestaurantAddress(Restaurant.currentRestaurant.id, address.getValue());
                RestaurantOwnerPanel.show();
            }
        }
    }
    public void cancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("The changes will discard.");
        alert.setContentText("Are you sure you want to discard the changes?");
        if (alert.showAndWait().get() == ButtonType.NO) {
            RestaurantOwnerPanel.show();
        }
    }
}
