package FoodHub.Control;
import FoodHub.Base.Food;
import FoodHub.Base.FoodType;
import FoodHub.Base.Restaurant;
import FoodHub.Main;
import FoodHub.MainApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.util.ArrayList;

public class EditFood {
    @FXML
    public TextField name;
    @FXML
    public HBox foodTypeBox;
    @FXML
    public Spinner<Integer> price, discountPercent, discountTime;
    @FXML
    public ChoiceBox<String> choiceFoodType;
    public static void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("EditFood.fxml"));
        Scene scene = new Scene(loader.load());
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }
    @FXML
    public void initialize() {
        name.setText(Food.currentFood.name);
        SpinnerValueFactory<Integer> priceValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1000, 1000000, Food.currentFood.price, 500),
                discountPercentValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, Food.currentFood.discountPercent, 1),
                discountTimeValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, (int) Food.currentFood.discountTime.getTime() / 3600000, 10);
        price.setValueFactory(priceValueFactory);
        discountPercent.setValueFactory(discountPercentValueFactory);
        discountTime.setValueFactory(discountTimeValueFactory);
        ArrayList<String> foodTypes = new ArrayList<>();
        for (FoodType foodType : FoodType.values())
            if (!foodType.getFoodType().equals("ALL"))
                foodTypes.add(foodType.getFoodType());
        choiceFoodType = new ChoiceBox<>(FXCollections.observableArrayList(foodTypes));
        choiceFoodType.setPrefSize(350, 50);
        choiceFoodType.setValue(Food.currentFood.foodType.getFoodType());
        foodTypeBox.getChildren().add(choiceFoodType);
    }
    public void edit(ActionEvent event) throws IOException {
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
            alert.setHeaderText("All the food's data will delete because of changing.");
            alert.setContentText("Are you sure?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Main.sql.editFood(Food.currentFood.id, Restaurant.currentRestaurant.id, name.getText(), price.getValue(), choiceFoodType.getValue(), discountPercent.getValue(), discountTime.getValue(), (Food.currentFood.isActive) ? "yes" : "no");
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
