package FoodHub.Control;
import FoodHub.Base.Food;
import FoodHub.Base.FoodType;
import FoodHub.Base.Restaurant;
import FoodHub.Base.User;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;

public class AddFood {
    @FXML
    public TextField name;
    @FXML
    public HBox foodTypeBox;
    @FXML
    public Spinner<Integer> price, discountPercent, discountTime;
    @FXML
    public ChoiceBox<String> choiceFoodType;
    public static void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("AddFood.fxml"));
        Scene scene = new Scene(loader.load());
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }
    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> priceValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1000, 1000000, 1000, 500),
                discountPercentValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 0, 1),
                discountTimeValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 0, 10);
        price.setValueFactory(priceValueFactory);
        discountPercent.setValueFactory(discountPercentValueFactory);
        discountTime.setValueFactory(discountTimeValueFactory);
        ArrayList<String> foodTypes = new ArrayList<>();
        for (FoodType foodType : FoodType.values())
            if (!foodType.getFoodType().equals("ALL"))
                foodTypes.add(foodType.getFoodType());
        choiceFoodType = new ChoiceBox<>(FXCollections.observableArrayList(foodTypes));
        choiceFoodType.setPrefSize(350, 50);
        foodTypeBox.getChildren().add(choiceFoodType);
    }
    public void add(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid type input.");
        if (!name.getText().matches("\\w+")) {
            alert.setTitle("the name type must have more that one word include letters and numbers and '_'");
            alert.show();
        } else {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirm");
            alert.setHeaderText("The new food will add.");
            alert.setContentText("Are you sure?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Food.addFood(Restaurant.currentRestaurant.id, name.getText(), price.getValue(), (String) choiceFoodType.getValue(), discountPercent.getValue(), discountTime.getValue(), "yes");
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
