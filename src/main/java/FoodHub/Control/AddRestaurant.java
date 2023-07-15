package FoodHub.Control;
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
import org.controlsfx.control.CheckComboBox;

import java.io.IOException;
import java.util.ArrayList;

public class AddRestaurant {
    @FXML
    public TextField name;
    @FXML
    public Spinner<Integer> address, postCost;
    @FXML
    public HBox foodTypeBox;
    public CheckComboBox<String> checkFoodType;
    public static void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("AddRestaurant.fxml"));
        Scene scene = new Scene(loader.load());
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
    }
    @FXML
    public void initialize() {
        SpinnerValueFactory<Integer> addressValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1, 10),
                postCostValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1000, 100000, 1000, 500);
        address.setValueFactory(addressValueFactory);
        postCost.setValueFactory(postCostValueFactory);
        ArrayList<String> foodTypes = new ArrayList<>();
        for (FoodType foodType : FoodType.values())
            if (!foodType.getFoodType().equals("ALL"))
                foodTypes.add(foodType.getFoodType());
        checkFoodType = new CheckComboBox<>(FXCollections.observableArrayList(foodTypes));
        checkFoodType.setPrefSize(350, 50);
        foodTypeBox.getChildren().add(checkFoodType);
    }
    public void add(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid type input.");
        if (!name.getText().matches("\\w+")) {
            alert.setContentText("the name type must have more that one word include letters and numbers and '_'");
            alert.show();
        }
        ArrayList<FoodType> foodTypes = new ArrayList<>();
        for (int i = 0; i < checkFoodType.getItems().size(); i++)
            if (checkFoodType.getCheckModel().isChecked(i))
                foodTypes.add(FoodType.stringToFoodType(checkFoodType.getCheckModel().getItem(i)));
        if (foodTypes.size() == 0) {
            alert.setContentText("the types must not be null.");
            alert.show();
        }
        else {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirm");
            alert.setHeaderText("The new restaurant will add.");
            alert.setContentText("Are you sure?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                Restaurant.addRestaurant(User.currentUser.id, name.getText(), postCost.getValue(), Restaurant.foodTypesToString(foodTypes, false), address.getValue());
                OwnerPanel.show();
            }
        }
    }
    public void cancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("The changes will discard.");
        alert.setContentText("Are you sure you want to discard the changes?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            OwnerPanel.show();
        }
    }
}
