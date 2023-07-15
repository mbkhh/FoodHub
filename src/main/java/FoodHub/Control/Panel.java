package FoodHub.Control;
import FoodHub.Base.FoodType;
import FoodHub.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class Panel {
    @FXML
    public TextField searchText;
    @FXML
    public HBox box;
    public static Panel panel;
    public static void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("Panel.fxml"));
        Scene scene = new Scene(loader.load());
        MainApplication.primaryStage.setScene(scene);
        MainApplication.primaryStage.show();
        MainApplication.scene = "Panel";
    }
    @FXML
    public void initialize() throws IOException {
        panel = this;
        for (FoodType foodType : FoodType.values()) {
            FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("PanelColumn.fxml"));
            AnchorPane pane = loader.load();
            pane.setId("FoodType_" + foodType.getFoodType());
            PanelColumn column = loader.getController();
            column.setData(foodType.getFoodType());
            box.getChildren().add(pane);
        }
    }
    public void balance(ActionEvent event) throws IOException {
        ChargeController.show();
    }
    public void cart(ActionEvent event) throws IOException {
        CartController.show();
    }
    public void orders(ActionEvent event) throws IOException {
        OrderController.show();
    }
    public void search(ActionEvent event) throws IOException {
        Restaurants.show(searchText.getText());
    }
}
