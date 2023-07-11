package FoodHub.Control;
import FoodHub.Base.Cart;
import FoodHub.Base.FoodType;
import FoodHub.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class Panel {
    @FXML
    public TextField search;
    @FXML
    public HBox box;
    public static Panel panel;
    public static void show() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("Panel.fxml"));
        Scene scene = new Scene(loader.load(), Main.primaryWidth, Main.primaryHeight);
        Main.primaryStage.setScene(scene);
        Main.primaryStage.show();
    }
    @FXML
    public void initialize() throws IOException {
        panel = this;
        for (FoodType foodType : FoodType.values()) {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("PanelColumn.fxml"));
            AnchorPane pane = loader.load();
            pane.setId("FoodType_" + foodType.getFoodType());
            PanelColumn column = loader.getController();
            column.setData(foodType.getFoodType());
            box.getChildren().add(pane);
        }
    }
    public void balance(ActionEvent event) {

    }
    public void cart(ActionEvent event) throws IOException {
        CartController.show();
    }
    public void orders(ActionEvent event) throws IOException {
        OrderController.show();
    }
    public void search(ActionEvent event) {

    }
}
